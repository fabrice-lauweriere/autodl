package com.fladev.model;

public class Episode implements Comparable<Episode> {
	private int id;
	private String showTitle;
	private String code;
	private EpisodeFlags flags = new EpisodeFlags();

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

	public void setId(int id) {
		this.id = id;
	}

	public void setShowTitle(String showTitle) {
		this.showTitle = showTitle;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public void setSeen(Boolean seen) {
		this.flags.setSeen(seen);
	}
	
	public void setDownloaded(Boolean dl) {
		this.flags.setDownloaded(dl);
	}

	public void setSpecial(Boolean spe) {
		this.flags.setSpecial(spe);
	}

	public EpisodeFlags getFlags() {
		return flags;
	}

	public void setFlags(EpisodeFlags flags) {
		this.flags = flags;
	}

	public int compareTo(Episode e2) {
		int result = 0;
		if (this.getId() == e2.getId()) {
			result = 0;
		} else if (this.getShowTitle().equals(e2.getShowTitle())) {
			result = this.getCode().compareTo(e2.getCode());
		} else {
			result = this.getShowTitle().compareTo(e2.getShowTitle());
		}
		return result;
	}

	@Override
	public String toString() {
		return "\t" + showTitle + " " + code + " ("+id+")\n\t   seen=" + flags.isSeen() + ", dl=" + flags.isDownloaded()+ ", spe=" + flags.isSpecial()
				+ "\n";
	}

}
