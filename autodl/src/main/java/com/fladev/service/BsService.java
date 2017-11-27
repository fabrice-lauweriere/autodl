package com.fladev.service;

import java.time.LocalDate;
import java.util.List;

import com.fladev.model.BsCalendar;
import com.fladev.model.Episode;

public interface BsService {
	public BsCalendar getBsCalendar(String connexionToken, int monthOfYear);
	public List<Episode> getEpisodesOfDay(String connexionToken, LocalDate aDate);
	public String authenticateUser(String apiKey, String username, String password);
}
