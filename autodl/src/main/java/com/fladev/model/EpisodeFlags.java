package com.fladev.model;

public class EpisodeFlags {


	private boolean seen;
	private boolean downloaded;
	private boolean special;

	public EpisodeFlags(boolean seen, boolean downloaded, boolean special) {
		this.seen = seen;
		this.downloaded = downloaded;
		this.special = special;
	}

	public EpisodeFlags() {
	}


	public boolean isSeen() {
		return seen;
	}

	public boolean isDownloaded() {
		return downloaded;
	}

	public boolean isSpecial() {
		return special;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	public void setDownloaded(boolean downloaded) {
		this.downloaded = downloaded;
	}

	public void setSpecial(boolean special) {
		this.special = special;
	}

}
