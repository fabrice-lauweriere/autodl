package com.fladev.dao;

import java.io.File;
import java.net.URL;

public interface WebDao {
	public String getWebPageHtmlCode(URL url);
	public File downloadFile(URL url);
	public boolean isLinkDead(URL url);
}
