package com.fladev.service;

import java.time.LocalDate;

import com.fladev.model.BsCall;
import com.fladev.model.CallMethod;
import com.fladev.model.Constants;

public class BsCallBuilderService {

	private String apiVersion;

	private String apiKey;

	private static final String BS_AUTH_CALL = "http://api.betaseries.com/members/auth?key=" + Constants.TOKEN_KEY
			+ "&v=" + Constants.TOKEN_VERSION + "&login=" + Constants.TOKEN_LOGIN + "&password="
			+ Constants.TOKEN_PASSWORD;

	private static final String BS_TOKEN_DESTROY_CALL = "http://api.betaseries.com/members/destroy?key="
			+ Constants.TOKEN_KEY + "&v=" + Constants.TOKEN_VERSION + "&token=" + Constants.TOKEN_TOKEN;

	private static final String BS_PLANNING_CALL = "http://api.betaseries.com/planning/calendar?key="
			+ Constants.TOKEN_KEY + "&v=" + Constants.TOKEN_VERSION + "&token=" + Constants.TOKEN_TOKEN + "&start="
			+ Constants.TOKEN_START_DATE + "&end=" + Constants.TOKEN_END_DATE;

	private static final String BS_EPISODE_DETAIL_CALL = "http://api.betaseries.com/episodes/display?key="
			+ Constants.TOKEN_KEY + "&v=" + Constants.TOKEN_VERSION + "&token=" + Constants.TOKEN_TOKEN + "&id="
			+ Constants.TOKEN_ID;

	// CONSTRUCTOR
	public BsCallBuilderService(String apiVersion, String apiKey) {
		this.apiKey = apiKey;
		this.apiVersion = apiVersion;
	}

	// METHODS
	private String initKeyVersion(String callToInit) {
		return callToInit.replace(Constants.TOKEN_KEY, apiKey).replace(Constants.TOKEN_VERSION, apiVersion);
	}

	public BsCall buildAuthCall(String login, String password) {
		BsCall authCall = new BsCall(CallMethod.POST);
		authCall.setUri(initKeyVersion(BS_AUTH_CALL).replace(Constants.TOKEN_LOGIN, login)
				.replace(Constants.TOKEN_PASSWORD, password));
		return authCall;
	}

	public BsCall buildAuthTokenDestroyCall(String token) {
		BsCall destroyCall = new BsCall(CallMethod.POST);
		destroyCall.setUri(initKeyVersion(BS_TOKEN_DESTROY_CALL).replace(Constants.TOKEN_TOKEN, token));
		return destroyCall;
	}

	public BsCall buildPlanningCall(String aConnetionToken, LocalDate aStartDate, LocalDate aEndDate) {
		String startDateAsString = aStartDate.toString();
		String endDateAsString = aEndDate.toString();
		BsCall planningCall = new BsCall(CallMethod.GET);
		planningCall.setUri(initKeyVersion(BS_PLANNING_CALL).replace(Constants.TOKEN_TOKEN, aConnetionToken)
				.replace(Constants.TOKEN_START_DATE, startDateAsString)
				.replace(Constants.TOKEN_END_DATE, endDateAsString));
		return planningCall;
	}

	public BsCall buildEpisodeDetailCall(String aConnetionToken, String episodesIds) {
		BsCall episodeDetailCall = new BsCall(CallMethod.GET);
		episodeDetailCall.setUri(initKeyVersion(BS_EPISODE_DETAIL_CALL).replace(Constants.TOKEN_TOKEN, aConnetionToken)
				.replace(Constants.TOKEN_ID, episodesIds));
		return episodeDetailCall;
	}

}
