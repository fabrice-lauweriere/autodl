package com.fladev.service;

import java.time.LocalDate;

import org.junit.Test;

import junit.framework.TestCase;

public class BsServiceTest extends TestCase{

	@Test
	public void test_getEpisodes() {
		BsService service = new BsService();
		LocalDate start = LocalDate.now();
		System.out.println(service.getEpisodesOfDay(start, null));
	}
}
