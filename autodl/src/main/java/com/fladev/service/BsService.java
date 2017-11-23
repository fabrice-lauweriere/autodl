package com.fladev.service;

import java.time.LocalDate;
import java.util.List;

import com.fladev.model.BsCalendar;
import com.fladev.model.Episode;

public interface BsService {
	public BsCalendar getBsCalendar(int monthOfYear);
	public List<Episode> getEpisodeOfDay(LocalDate aDate);
}
