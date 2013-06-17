package cn.mmt.app.model;

public class ConferenceTop {
	private int rank;
	private long conferenceId;
	private String title;
	private long liveness;//活跃度
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public long getConferenceId() {
		return conferenceId;
	}
	public void setConferenceId(long conferenceId) {
		this.conferenceId = conferenceId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getLiveness() {
		return liveness;
	}
	public void setLiveness(long liveness) {
		this.liveness = liveness;
	}
	
}
