package com.fladev.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import com.fladev.exception.AutoDlException;
import com.fladev.model.BsCalendar;
import com.fladev.model.BsCall;
import com.fladev.model.Constants;
import com.fladev.model.Episode;
import com.fladev.model.EpisodeFlags;
import com.fladev.utils.CallExecUtils;
import com.fladev.utils.JsonUtils;
import com.fladev.utils.PropertiesUtils;

public class BsService {

	private String token = Constants.EMPTY_STRING;

	private BsCallBuilderService callBuilder;

	public BsService() {
		Properties properties = PropertiesUtils.getProperties();
		String apiVersion = properties.getProperty("bs.api.version");
		String apiKey = properties.getProperty("bs.api.key");
		if (apiVersion != null && apiKey != null) {
			callBuilder = new BsCallBuilderService(apiVersion, apiKey);
			String login = properties.getProperty("bs.user.login");
			String password = properties.getProperty("bs.user.password");
			if (login != null && password != null) {
				BsCall authenticationCall = callBuilder.buildAuthCall(properties.getProperty("bs.user.login"),
						properties.getProperty("bs.user.password"));
				this.token = JsonUtils.getAccessToken(CallExecUtils.executeCall(authenticationCall));
				if (token.isEmpty()) {
					throw new AutoDlException("Unable to get connexion token. Token is empty");
				}
			} else {
				throw new AutoDlException("Unable to get user login and/or password from the properties file");
			}
		} else {
			throw new AutoDlException("Unable to get api version and/or key from the properties file");
		}
	}

	public Set<Episode> getEpisodesOfDay(LocalDate aStartDate, LocalDate aEndDate) {
		Set<Episode> episodes = new TreeSet<Episode>();
		LocalDate endDate = aEndDate != null ? aEndDate : aStartDate;
		BsCall planningCall = callBuilder.buildPlanningCall(token, aStartDate, endDate);
		HashMap<Integer, Episode> episodesMap = JsonUtils.getListOfEpisodes(CallExecUtils.executeCall(planningCall));
		String episodesIds = Constants.EMPTY_STRING;
		if (!episodesMap.isEmpty()) {
			for (int key : episodesMap.keySet()) {
				episodesIds = episodesIds.concat(Constants.COMA).concat(Integer.toString(key));
			}
			episodesIds = episodesIds.substring(1);
			BsCall episodeDetailCall = callBuilder.buildEpisodeDetailCall(token, episodesIds);
			HashMap<Integer, EpisodeFlags> flags = JsonUtils
					.getEpisodeFlags(CallExecUtils.executeCall(episodeDetailCall));
			EpisodeFlags defaultFlags = new EpisodeFlags(false, false, false);
			for (int key : episodesMap.keySet()) {
				episodesMap.get(key).setFlags(flags.getOrDefault(key, defaultFlags));
				episodes.add(episodesMap.get(key));
			}
		}
		return episodes;
	}

	public void markEpisodeAsDownloaded(Episode episode) {
		throw new UnsupportedOperationException();
	}

	public String authenticateUser(String apiKey, String username, String password) {
		throw new UnsupportedOperationException();
	}

	public BsCalendar getBsCalendar(String connexionToken, int monthOfYear) {
		throw new UnsupportedOperationException();
	}
}
