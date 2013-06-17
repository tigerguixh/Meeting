package cn.mmt.app.model;

public class Conference {
	private int id;
	private int conferenceId;
	private String img;
	private int conferenceType ;//// 1 大会，2 沙龙，3讲堂
	private String time;
	private String sponsor;//举办方
	private String title;//会议名称
	private String synopsis;//简介，最大500字
	private String locale;//会议地址
	private int charge;//费用
	private int status;//1是正在报名中；2是报名结束；3是会议进行中；4是已取消；（默认1）
	public int getConferenceId() {
		return conferenceId;
	}
	public void setConferenceId(int conferenceId) {
		this.conferenceId = conferenceId;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getConferenceType() {
		return conferenceType;
	}
	public void setConferenceType(int conferenceType) {
		this.conferenceType = conferenceType;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSponsor() {
		return sponsor;
	}
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public int getCharge() {
		return charge;
	}
	public void setCharge(int charge) {
		this.charge = charge;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
