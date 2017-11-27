package com.fladev.utils;

import java.util.Properties;

import org.junit.Test;

import junit.framework.TestCase;

public class PropertiesUtilsTest extends TestCase {

	@Test
	public void test_propertiesLoading() {
		Properties p = PropertiesUtils.loadPropertiesFile("autodl.properties");
		assertEquals("046e30540ce7",p.getProperty("bs.api.key"));
	}
}
