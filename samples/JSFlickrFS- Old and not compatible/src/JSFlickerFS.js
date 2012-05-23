/*
The MIT License

Copyright (C) 2008 Yu Kobayashi http://yukoba.accelart.jp/

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */

importPackage(java.io);
importPackage(Packages.javax.xml.xpath);
importPackage(Packages.org.xml.sax);
importPackage(Packages.net.decasdev.dokan);
importClass(java.net.URL);
importClass(Packages.org.apache.commons.io.IOUtils);

var flicker_api_key = "d829ba352ece99817568c180f27e6972";

var xpath = XPathFactory.newInstance().newXPath();

function getPics(keyword) {
	var url = 'http://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=' + 
		flicker_api_key + '&per_page=20&tags=' + keyword;
	var xml = readUrl(url, "UTF-8");

	var result = xpath.evaluate("/rsp/photos/photo", new InputSource(new StringReader(xml)), 
		XPathConstants.NODESET);
	var pics = {};
	for(var i=0; i < result.getLength(); i++) {
		var attrs = result.item(i).getAttributes();
		pics[attrs.getNamedItem("title").getNodeValue()] = "http://farm" + 
			attrs.getNamedItem("farm").getNodeValue() +
			".static.flickr.com/" +
			attrs.getNamedItem("server").getNodeValue() +
			"/" + 
			attrs.getNamedItem("id").getNodeValue() +
			"_" + 
			attrs.getNamedItem("secret").getNodeValue() +
			"_t.jpg";
	}
	return pics;
}

function download(url) {
	var f = File.createTempFile("jsfl", ".jpg");
	f.deleteOnExit();
	var ins = new URL(url).openStream();
	var outs = new FileOutputStream(f);
	IOUtils.copy(ins, outs);
	outs.close();
	ins.close();
	return f;
}

var nextHandleNo = 1;
var nextFileIndex = 2;
var dirs = {};
var files = {};

var dokanOptions = new DokanOptions();
dokanOptions.driveLetter = 'T';

var operations = new DokanOperations() {
	onCreateFile : function(fileName, desiredAccess, shareMode, creationDisposition, flagsAndAttributes) {
		if(fileName == '\\') {
			switch (creationDisposition) {
			case CreationDisposition.CREATE_NEW:
			case CreationDisposition.CREATE_ALWAYS:
				throw new DokanOperationException(WinError.ERROR_ALREADY_EXISTS);
			case CreationDisposition.OPEN_ALWAYS:
			case CreationDisposition.OPEN_EXISTING:
			case CreationDisposition.TRUNCATE_EXISTING:
				return nextHandleNo++;
			}
		} else if(dirs[fileName] != null || files[fileName] != null) {
			switch (creationDisposition) {
			case CreationDisposition.CREATE_NEW:
				throw new DokanOperationException(ERROR_ALREADY_EXISTS);
			case CreationDisposition.OPEN_ALWAYS:
			case CreationDisposition.OPEN_EXISTING:
				return nextHandleNo++;
			case CreationDisposition.CREATE_ALWAYS:
			case CreationDisposition.TRUNCATE_EXISTING:
				throw new DokanOperationException(1);
			}
		} else {
			switch (creationDisposition) {
			case CreationDisposition.CREATE_NEW:
			case CreationDisposition.CREATE_ALWAYS:
			case CreationDisposition.OPEN_ALWAYS:
				throw new DokanOperationException(1);
			case CreationDisposition.OPEN_EXISTING:
			case CreationDisposition.TRUNCATE_EXISTING:
				throw new DokanOperationException(ERROR_FILE_NOT_FOUND);
			}
		}
		throw new DokanOperationException(1);
	},
	onOpenDirectory : function(fileName) {
		return nextHandleNo++;
	},
	onCreateDirectory : function(fileName) {
		if(fileName.length() <= 1 || fileName.substring(1).indexOf('\\') != -1) {
			throw new DokanOperationException(1);
		}
		if(dirs[fileName] == null) {
			dirs[fileName] = {
				fileAttributes: (0x10 + 0x80),
				fileIndex: nextFileIndex++
			};
			var pics = getPics(fileName.substring(1));
			var children = [];
			for(var title in pics) {
				print("Downloading " + title);
				var info = {
					title: title + ".jpg",
					url: pics[title],
					fileAttributes: 0x80,
					fileIndex: nextFileIndex++,
					contents: download(pics[title])
				};
				children.push(info);
				files[fileName + "\\" + title + ".jpg"] = info;
			}
			dirs[fileName].children = children;
		}
	},
	onReadFile : function(fileName, buffer, offset) {
		if (files[fileName] == null)
			throw new DokanOperationException(WinError.ERROR_FILE_NOT_FOUND);
		var ins = new FileInputStream(files[fileName].contents);
		var data = IOUtils.toByteArray(ins);
		var copySize = Math.min(buffer.capacity(), data.length - offset);
		if (copySize <= 0) return 0;
		buffer.put(data, offset, copySize);
		return copySize;
	},
	onGetFileInformation : function(fileName) {
		if(fileName == '\\') {
			return new ByHandleFileInformation(0x10 + 0x80, 0, 0, 0, 5858, 0, 1, 1);
		}
		var info = dirs[fileName];
		if(info != null) {
			return new ByHandleFileInformation(info.fileAttributes, 0, 0, 0, 5858, 0, 1, info.fileIndex);
		}
		info = files[fileName];
		if(info != null) {
			return new ByHandleFileInformation(info.fileAttributes, 0, 0, 0, 5858, info.contents.length(),
				1, info.fileIndex);
		}
		throw new DokanOperationException(WinError.ERROR_FILE_NOT_FOUND);
	},
	onFindFiles : function(pathName) {
		pathName = pathName.substring(0, pathName.length() - 1);
		if(pathName == '') {
			var ary = [];
			for(var key in dirs) {
				var info = dirs[key];
				ary.push(new Win32FindData(info.fileAttributes, 0, 0, 0, 0, 0, 0, key.substring(1), ""));
			}
			return ary;
		} else if(dirs[pathName] != null) {
			var ary = [];
			for(var i=0; i < dirs[pathName].children.length; i++) {
				var info = dirs[pathName].children[i];
				ary.push(new Win32FindData(info.fileAttributes, 0, 0, 0, info.contents.length(), 0, 0,
					info.title, ""));
			}
			return ary;
		} else {
			throw new DokanOperationException(WinError.ERROR_PATH_NOT_FOUND);
		}
	},
	onDeleteDirectory : function(fileName) { 
		if(dirs[fileName] != null) {
			delete dirs[fileName];
		}
	},
	onMoveFile : function(existingFileName, newFileName, replaceExisiting) { 
		throw new DokanOperationException(1);
		/*
		var existing = dirs[existingFileName];
		if (existing == null)
			throw new DokanOperationException(ERROR_FILE_NOT_FOUND);
		var newFile = dirs[newFileName];
		if (newFile != null && !replaceExisiting)
			throw new DokanOperationException(ERROR_FILE_EXISTS);
		delete dirs[existingFileName];
		dirs[newFileName] = existing;
		*/
	},
	// Not handling methods
	onFindFilesWithPattern : function() { return null; },
	onWriteFile : function() { throw new DokanOperationException(WinError.ERROR_WRITE_FAULT); },
	onSetEndOfFile : function() { throw new DokanOperationException(WinError.ERROR_WRITE_FAULT); },
	onSetFileAttributes : function() { throw new DokanOperationException(1); },
	onSetFileTime : function() { throw new DokanOperationException(1); },
	onDeleteFile : function() { throw new DokanOperationException(1); },
	onFlushFileBuffers : function() {},
	onCleanup : function() {},
	onCloseFile : function() {},
	onLockFile : function() {},
	onUnlockFile : function() {},
	onGetDiskFreeSpace : function() { return null; },
	onGetVolumeInformation : function() { return null; },
	onUnmount : function() {}
};

RhinoMountWrapper.mount(dokanOptions, operations);
quit();
