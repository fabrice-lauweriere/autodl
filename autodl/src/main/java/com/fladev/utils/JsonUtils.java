package com.fladev.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fladev.exception.AutoDlException;
import com.fladev.model.Constants;
import com.fladev.model.Episode;

public class JsonUtils {

	public static String getAccessToken(String json) {
		String tokenAsString = Constants.EMPTY_STRING;
		try {
			JSONParser parser = new JSONParser();
			Object resultObject = parser.parse(json);
			if (resultObject instanceof JSONObject) {
				JSONObject obj = (JSONObject) resultObject;
				tokenAsString = (String) obj.get("token");
			}
		} catch (ParseException e) {
			throw new AutoDlException(
					"Unable to parse json response from authentication call. Tried to parse : " + json, e);
		}
		return tokenAsString;
	}

	public static HashMap<Integer, Episode> getListOfEpisodes(String json) {
		HashMap<Integer, Episode> episodes = new HashMap<Integer, Episode>();
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(json);
			if (obj instanceof JSONObject) {
				JSONObject body = (JSONObject) obj;
				obj = body.get("days");
				if (obj instanceof JSONArray) {
					JSONArray days = (JSONArray) obj;
					for (Object dayObj : days) {
						if (dayObj instanceof JSONObject) {
							JSONObject day = (JSONObject) dayObj;
							obj = day.get("events");
							if (obj instanceof JSONArray) {
								JSONArray events = (JSONArray) obj;
								for (Object eventObj : events) {
									if (eventObj instanceof JSONObject) {
										JSONObject event = (JSONObject) eventObj;
										String eventType = (String) event.get("type");
										if (Constants.EPISODE_RELEASE_TYPE.equals(eventType)) {
											obj = event.get("payload");
											if (obj instanceof JSONObject) {
												JSONObject episode = (JSONObject) obj;
												Episode e = new Episode();
												e.setId(Math.toIntExact((Long) episode.get("id")));
												e.setShowTitle((String) episode.get("show_title"));
												e.setCode((String) episode.get("code"));
												e.setSeen((Boolean) episode.get("seen"));
												episodes.put(e.getId(), e);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (ParseException e) {
			throw new AutoDlException("Unable to parse json response from planning call. Tried to parse : " + json, e);
		}
		return episodes;
	}

	public static HashMap<Integer, Boolean> getDowloadedFlags(String json) {
		HashMap<Integer, Boolean> episodesDowloadedFlags = new HashMap<Integer, Boolean>();
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(json);
			if (obj instanceof JSONObject) {
				JSONObject body = (JSONObject) obj;
				obj = body.get("episodes");
				if (obj != null) { // case of multiple episode
					if (obj instanceof JSONArray) {
						JSONArray episodes = (JSONArray) obj;
						for (Object episodeObj : episodes) {
							if (episodeObj instanceof JSONObject) {
								JSONObject episode = (JSONObject) episodeObj;
								int episodeId = Math.toIntExact((Long) episode.get("id"));
								obj = episode.get("user");
								if (obj instanceof JSONObject) {
									JSONObject userInfo = (JSONObject) obj;
									Boolean dowloaded = (Boolean) userInfo.get("downloaded");
									episodesDowloadedFlags.put(episodeId, dowloaded);
								}
							}
						}
					}
				} else { // we try if it is only 1 episodes
					obj = body.get("episode");
					if (obj != null) { // case of 1 unique episode
						if (obj instanceof JSONObject) {
							JSONObject episode = (JSONObject) obj;
							int episodeId = Math.toIntExact((Long) episode.get("id"));
							obj = episode.get("user");
							if (obj instanceof JSONObject) {
								JSONObject userInfo = (JSONObject) obj;
								Boolean dowloaded = (Boolean) userInfo.get("downloaded");
								episodesDowloadedFlags.put(episodeId, dowloaded);
							}
						}
					} else { // Case of no episodes
						throw new IllegalArgumentException(
								"Json provided does not contain any episode information. Tried to parse : " + json);
					}
				}
			}
		} catch (ParseException e) {
			throw new AutoDlException(
					"Unable to parse json response from episode detail call. Tried to parse : " + json, e);
		}
		return episodesDowloadedFlags;
	}
}
