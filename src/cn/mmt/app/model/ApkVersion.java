package cn.mmt.app.model;

public class ApkVersion {
	private String isForce ;//0是不强制更新1是强制更新
	private String apk_url;
	private String isUpdate ;//0是不更新1是更新
	public String getIsForce() {
		return isForce;
	}
	public void setIsForce(String isForce) {
		this.isForce = isForce;
	}
	public String getApk_url() {
		return apk_url;
	}
	public void setApk_url(String apk_url) {
		this.apk_url = apk_url;
	}
	public String getIsUpdate() {
		return isUpdate;
	}
	public void setIsUpdate(String isUpdate) {
		this.isUpdate = isUpdate;
	}
	
}
