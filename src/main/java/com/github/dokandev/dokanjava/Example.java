package com.github.dokandev.dokanjava;

import com.github.dokandev.dokanjava.util.FileAttribute;
import com.github.dokandev.dokanjava.util.FileInfo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;

@SuppressWarnings("unused")
public class Example {
    static public void main(String[] args) throws Throwable {
        System.out.println("hello");
        System.out.println(Dokan.version());
        System.out.println(Dokan.driverVersion());
        Dokan.unmount('M');
        Dokan.main("M:\\", new DokanFilesystem<NodeFileHandle>() {
            @Override
            public boolean getDebug() {
                return true;
            }

            @Override
            public boolean getDebugStderrOutput() {
                return true;
            }

            @Override
            protected NodeFileHandle createHandle(String fileName) throws IOException {
                return new NodeFileHandle(fileName, root.find(fileName, false));
            }

            @Override
            public NodeFileHandle createFile(String fileName, int securityContext, int rawDesiredAccess, int rawFileAttributes, int rawShareAccess, int rawCreateDisposition, int rawCreateOptions) throws IOException {
                //CreationDisposition.CREATE_NEW
                //super.createFile(fileName, securityContext, rawDesiredAccess, rawFileAttributes, rawShareAccess, rawCreateDisposition, rawCreateOptions, dokanFileInfo);
                //throw new DokanException(NtStatus.NoSuchFile);
                //throw new DokanException(NtStatus.CrmProtocolAlreadyExists);
                //throw new FileAlreadyExistsException("exists");
                //throw new FileNotFoundException();
                //throw new DokanException(NtStatus.UserExists);
                root.find(fileName, false);
                return createHandle(fileName);
            }

            @Override
            //public FileInfo getFileInformation(NodeFileHandle file) throws IOException {
            public FileInfo getFileInformation(String fileName) throws IOException {
                //return file.node.toFileInfo();
                return root.find(fileName, false).toFileInfo();
            }

            @Override
            public void findFiles(NodeFileHandle file, FileEmitter emitter) throws IOException {
                for (Node child : file.node.children.values()) {
                    emitter.emit(child.toFileInfo());
                }
            }

            @Override
            public void deleteFile(String fileName) throws IOException {
                root.find(fileName, false).delete();
            }

            @Override
            public void deleteDirectory(String fileName) throws IOException {
                root.find(fileName, false).delete();
            }

            @Override
            public void moveFile(String oldFileName, String newFileName, boolean replaceIfExisting) throws IOException {
                root.find(newFileName, true).replaceWith(root.find(oldFileName, false));
            }

            @Override
            public int readFile(NodeFileHandle file, long fileOffset, byte[] data, int dataLength) throws IOException {
                return file.node.read(fileOffset, data, dataLength);
            }

            @Override
            public int writeFile(NodeFileHandle file, long fileOffset, byte[] data, int dataLength) throws IOException {
                return file.node.write(fileOffset, data, dataLength);
            }

            @Override
            public long getUsedBytes() {
                return 256L * 1024 * 1024;
            }

            Node root = new Node();

            {
                root.find("HELLO.TXT", true).set(new byte[]{'H', 'E', 'L'});
                root.find("demo\\HELLO.TXT", true).set("OTHER".getBytes("UTF-8"));
            }
        });


        //System.out.println(NativeMethods.INSTANCE.DokanRemoveMountPoint(new WString("M")));
        //DokanNative.INSTANCE.printf("hello %s", "world");
    }

    static class NodeFileHandle extends DokanFileHandle {
        public final Node node;

        public NodeFileHandle(String fileName, Node node) {
            super(fileName);
            this.node = node;
        }
    }

    @SuppressWarnings("WeakerAccess")
    static class Node {
        public long inode;
        public String name;
        public Node parent;
        public boolean isDirectory = true;
        public LinkedHashMap<String, Node> children = new LinkedHashMap<String, Node>();
        public byte[] data = null;
        public Date date = new Date();

        public Node createChild(String name) {
            Node child = new Node();
            child.parent = this;
            child.name = name;
            this.children.put(name, child);
            return child;
        }

        public void delete() {
            if (this.parent != null) {
                this.parent.children.remove(this.name);
            }
        }

        public Node find(String path, boolean create) throws IOException {
            String normalized = path.replace('\\', '/');
            int index = normalized.indexOf('/');
            if (index < 0) {
                return child(path, create);
            } else {
                return child(normalized.substring(0, index), create).find(normalized.substring(index + 1), create);
            }
        }

        public Node child(String childName, boolean create) throws IOException {
            if (childName.equals("..")) return this.parent;
            if (childName.equals(".")) return this;
            if (childName.equals("")) return this;
            if (children.containsKey(childName)) return children.get(childName);
            if (!create) throw new FileNotFoundException();
            return createChild(childName);
        }

        public int read(long loffset, byte[] out, int len) {
            int offset = (int) loffset;
            if (data == null) throw new DokanException(ErrorCodes.ERROR_READ_FAULT);
            int readlen = Math.min(len, data.length - offset);
            System.arraycopy(this.data, offset, out, 0, readlen);
            return readlen;
        }

        public void set(byte[] bytes) {
            isDirectory = false;
            this.data = Arrays.copyOf(bytes, bytes.length);
        }

        public FileInfo toFileInfo() {
            return new FileInfo(inode, name, (data != null) ? data.length : 0L, isDirectory ? FileAttribute.FILE_ATTRIBUTE_DIRECTORY : FileAttribute.FILE_ATTRIBUTE_NORMAL, date, date, date);
        }

        public void replaceWith(Node node) {
            node.delete();
            this.delete();
            node.name = this.name;
            this.parent.children.put(node.name, node);
        }

        public int write(long loffset, byte[] data, int dataLength) {
            int offset = (int) loffset;
            if (this.data == null) this.data = new byte[0];
            this.data = Arrays.copyOf(this.data, Math.max(this.data.length, offset + dataLength));
            System.arraycopy(data, 0, this.data, offset, data.length);
            return dataLength;
        }
    }
}
