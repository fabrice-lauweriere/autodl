package com.fladev.model;

public class Episode {
	private String TvShowName;
	private int seasonNbr;
	private int episodeNbr;
	private boolean retrieved;
	private boolean viewed;
	
	
	
	
	
	
	
	
	
	
	
	
	
	// Getters and Setters
	public String getTvShowName() {
		return TvShowName;
	}
	public int getSeasonNbr() {
		return seasonNbr;
	}
	public int getEpisodeNbr() {
		return episodeNbr;
	}
	public boolean isRetrieved() {
		return retrieved;
	}
	public boolean isViewed() {
		return viewed;
	}
	public void setTvShowName(String tvShowName) {
		TvShowName = tvShowName;
	}
	public void setSeasonNbr(int seasonNbr) {
		this.seasonNbr = seasonNbr;
	}
	public void setEpisodeNbr(int episodeNbr) {
		this.episodeNbr = episodeNbr;
	}
	public void setRetrieved(boolean retrieved) {
		this.retrieved = retrieved;
	}
	public void setViewed(boolean viewed) {
		this.viewed = viewed;
	}
}
