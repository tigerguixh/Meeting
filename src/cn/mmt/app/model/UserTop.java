package cn.mmt.app.model;

public class UserTop {
	private int rank;//每次
	private long userId;//用户ID
	private String name;
	private long liveness;//活跃度
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getLiveness() {
		return liveness;
	}
	public void setLiveness(long liveness) {
		this.liveness = liveness;
	}
	
}
