package dev.dokan.java.sample;

import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinBase;
import dev.dokan.java.DokanFileSystem;
import dev.dokan.java.NTStatus;
import dev.dokan.java.constants.CreateOptions;
import dev.dokan.java.enums.CreateDisposition;
import dev.dokan.java.nativeannotations.EnumSet;
import dev.dokan.java.nativeannotations.Out;
import dev.dokan.java.structures.ByHandleFileInformation;
import dev.dokan.java.structures.DokanFileInfo;
import dev.dokan.java.structures.DokanIOSecurityContext;
import dev.dokan.java.structures.DokanOperations;

import java.util.concurrent.atomic.AtomicLong;


public class MemoryFs implements DokanFileSystem {

	private final ResourceManager resourceManager;
	private final AtomicLong handleGenerator = new AtomicLong(0);

	MemoryFs() {
		this.resourceManager = new ResourceManager();
	}

	MemoryFs(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;
	}

	@Override
	public int zwCreateFile(WString p, DokanIOSecurityContext securityContext, @EnumSet int desiredAccess, @EnumSet int fileAttributes, @EnumSet int shareAccess, int createDisposition, @EnumSet int createOptions, DokanFileInfo dokanFileInfo) {
		final MemoryPath path = MemoryPath.of(p.toString());
		if (!isValid(path)) {
			return NTStatus.OBJECT_NAME_INVALID;
		}
		/*
			We just count every zwCreateFile call.
			Long stores up to 2^64 values.
			If we assume 2^20 createFile calls per second (~ 1 million/s), we can continue counting without duplicates for 557844 yrs
		 */
		dokanFileInfo.context = handleGenerator.incrementAndGet();

		var crtDspstn = CreateDisposition.of(createDisposition);
		Resource r = resourceManager.get(path);

		//TODO: validate input
		//TODO: ensure that file names can be at most MAX_PATH-1 long

		if (r != null) {
			return switch (r.getType()) {
				case FILE -> handleExistingFile(path, (File) r, crtDspstn, createOptions, fileAttributes);
				case DIR -> handleExistingDir(path, (Directory) r, crtDspstn, createOptions, fileAttributes, dokanFileInfo);
			};
		} else {
			boolean createDir = (createOptions & CreateOptions.FILE_DIRECTORY_FILE) != 0;
			boolean createFile = (createOptions & CreateOptions.FILE_NON_DIRECTORY_FILE) != 0;
			if (createFile && createDir) {
				return NTStatus.INVALID_PARAMETER;
			} else if (createDir) {
				return handleNewDirectory(path, crtDspstn, createOptions, fileAttributes);
			} else if (createFile) {
				return handleNewFile(path, crtDspstn, createOptions, fileAttributes);
			} else {
				return NTStatus.UNSUCCESSFUL;
			}
		}

	}

	private int handleExistingFile(MemoryPath path, File file, CreateDisposition createDisposition, int createOptions, int fileAttributes) {
		return switch (createDisposition) {
			case CREATE -> NTStatus.OBJECT_NAME_COLLISION;
			case OPEN, OPEN_IF -> {
				//open file
				yield NTStatus.STATUS_SUCCESS;
			}
			case OVERWRITE, OVERWRITE_IF -> {
				file.wipe();
				file.setAttributes(fileAttributes);
				yield NTStatus.STATUS_SUCCESS;
			}
			case SUPERSEDE -> {
				switch (createOptions & CreateOptions.FILE_DIRECTORY_FILE) {
					case 0 -> resourceManager.put(path, new File(path.getFileName().toString(), fileAttributes));
					default -> resourceManager.put(path, new Directory(path.getFileName().toString(), fileAttributes));
				}
				yield NTStatus.STATUS_SUCCESS;
			}
		};
	}

	private int handleExistingDir(MemoryPath path, Directory r, CreateDisposition createDisposition, int createOptions, int fileAttributes, DokanFileInfo dokanFileInfo) {
		if ((createOptions & CreateOptions.FILE_NON_DIRECTORY_FILE) != 0) {
			return NTStatus.FILE_IS_A_DIRECTORY;
		}

		dokanFileInfo.setIsDirectory(true); //according to the docs, this must be set
		return switch (createDisposition) {
			case CREATE -> NTStatus.OBJECT_NAME_COLLISION;
			case OPEN, OPEN_IF -> NTStatus.STATUS_SUCCESS;
			case OVERWRITE, OVERWRITE_IF, SUPERSEDE -> NTStatus.STATUS_ACCESS_DENIED;
		};
	}

	private int handleNewFile(MemoryPath path, CreateDisposition createDisposition, int createOptions, int fileAttributes) {
		return switch (createDisposition) {
			case CREATE, OPEN_IF, OVERWRITE_IF, SUPERSEDE -> {
				resourceManager.put(path, new File(path.getFileName().toString(), fileAttributes));
				yield NTStatus.STATUS_SUCCESS;
			}
			case OPEN, OVERWRITE -> NTStatus.NO_SUCH_FILE;
		};
	}

	private int handleNewDirectory(MemoryPath path, CreateDisposition createDisposition, int createOptions, int fileAttributes) {
		return switch (createDisposition) {
			case CREATE, OPEN_IF -> {
				resourceManager.put(path, new Directory(path.getFileName().toString(), fileAttributes));
				yield NTStatus.STATUS_SUCCESS;
			}
			case OPEN -> NTStatus.NO_SUCH_FILE;
			case OVERWRITE, OVERWRITE_IF, SUPERSEDE -> NTStatus.STATUS_ACCESS_DENIED;
		};
	}

	@Override
	public void cleanup(WString path, DokanFileInfo dokanFileInfo) {
		final MemoryPath p = MemoryPath.of(path.toString());
		if (dokanFileInfo.context == 0) {
			return;
		}

		if (dokanFileInfo.getDeleteOnClose()) {
			resourceManager.remove(p);
		}
	}

	@Override
	public void closeFile(WString path, DokanFileInfo dokanFileInfo) {
		dokanFileInfo.context = 0L;
	}

	@Override
	public int findFiles(WString path, DokanOperations.FillWin32FindData fillFindDataCallback, DokanFileInfo dokanFileInfo) {
		final MemoryPath p = MemoryPath.of(path.toString());
		if (resourceManager.get(p) instanceof Directory d) {
			d.list().forEach(resource -> {
				fillFindDataCallback.invoke(resource.toFIND_DATAStruct(), dokanFileInfo);
			});
			return NTStatus.STATUS_SUCCESS;
		} else {
			return NTStatus.UNSUCCESSFUL;
		}
	}

	@Override
	public int getFileInformation(WString path, @Out ByHandleFileInformation handleFileInfo, DokanFileInfo dokanFileInfo) {
		var p = MemoryPath.of(path.toString());
		Resource r = resourceManager.get(p);
		if (r != null) {
			r.writeTo(handleFileInfo);
			return NTStatus.STATUS_SUCCESS;
		} else {
			return NTStatus.NO_SUCH_FILE;
		}
	}

	private boolean isValid(MemoryPath path) {
		try {
			return MemoryPath.ROOT.equals(path) || path.getFileName().toString().length() < WinBase.MAX_PATH;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

}
