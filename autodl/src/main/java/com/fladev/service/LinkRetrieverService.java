package com.fladev.service;

import java.util.List;

import com.fladev.model.Episode;
import com.fladev.model.RetrievedLink;

public interface LinkRetrieverService {
	public List<RetrievedLink> retrieveLink(Episode anEpisode);
}
