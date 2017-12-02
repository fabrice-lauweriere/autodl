package com.fladev.model;

public class Episode implements Comparable<Episode> {
	private int id;
	private String showTitle;
	private String code;
	private boolean seen;
	private boolean downloaded;
	
	
	
	
	
	
	
	// Getters and Setters
	public int getId() {
		return id;
	}
	public String getShowTitle() {
		return showTitle;
	}
	public String getCode() {
		return code;
	}
	public boolean isSeen() {
		return seen;
	}
	public boolean isDownloaded() {
		return downloaded;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setShowTitle(String showTitle) {
		this.showTitle = showTitle;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setSeen(boolean seen) {
		this.seen = seen;
	}
	public void setDownloaded(boolean downloaded) {
		this.downloaded = downloaded;
	}
	public int compareTo(Episode e2) {
		int result = 0;
		if (this.getId() == e2.getId()) {
			result = 0;
		} else if (this.getShowTitle().equals(e2.getShowTitle())){
			result = this.getCode().compareTo(e2.getCode());
		} else {
			result = this.getShowTitle().compareTo(e2.getShowTitle());
		}
		return result;
	}
	@Override
	public String toString() {
		return "Episode " + id + "\n"+
					"\t" + showTitle + " " + code + 
					"\n\t seen=" + seen + ", dl=" + downloaded + "\n";
	}
	

}
