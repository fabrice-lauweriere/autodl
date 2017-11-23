package com.fladev.dao;

import java.io.File;
import java.nio.file.Path;

public interface FileSystemDao {
	public void saveFile(File aFile, Path aFolderPath);
}
