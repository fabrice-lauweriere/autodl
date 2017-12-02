package com.fladev.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.fladev.exception.AutoDlException;
import com.fladev.model.Constants;

public class PropertiesUtils {
	
	public static Properties properties = new Properties();
	
	private static void loadPropertiesFile() {
		FileInputStream input = null;
		try {
			input = new FileInputStream(".\\src\\main\\resources\\".concat(Constants.APPLICATION_PROPERTIES_FILE_NAME));
			properties.load(input);
			input.close();
		} catch (IOException e) {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e1) {
					throw new AutoDlException(e1.getLocalizedMessage(), e1);
				}
			}
			throw new AutoDlException(e.getLocalizedMessage(), e);
		} 
	}
	
	public static Properties getProperties() {
		if (properties.isEmpty()) {
			loadPropertiesFile();
		}
		return properties;
	}
}
