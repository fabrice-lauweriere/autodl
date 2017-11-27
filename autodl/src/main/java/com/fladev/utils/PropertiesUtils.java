package com.fladev.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.fladev.exception.AutoDlException;

public class PropertiesUtils {
	public static Properties loadPropertiesFile(String aFileName) {
		Properties iProperties = new Properties();
		FileInputStream input = null;
		try {
			input = new FileInputStream(".\\src\\main\\resources\\".concat(aFileName));
			iProperties.load(input);
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
		return iProperties;
	}
}
