package cn.mmt.app.model;

import com.weibo.sdk.android.Oauth2AccessToken;

public class AppKey {
	private Oauth2AccessToken sinaKey;
	private String handMeeting;
	public Oauth2AccessToken getSinaKey() {
		return sinaKey;
	}
	public void setSinaKey(Oauth2AccessToken sinaKey) {
		this.sinaKey = sinaKey;
	}
	public String getHandMeeting() {
		return handMeeting;
	}
	public void setHandMeeting(String handMeeting) {
		this.handMeeting = handMeeting;
	}
	
}
