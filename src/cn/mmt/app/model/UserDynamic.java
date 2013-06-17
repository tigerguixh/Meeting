package cn.mmt.app.model;

public class UserDynamic {
	private String name;
	private int dynamicType;// 1、收藏。2、分享。3、报名。
	private String content;
	private String headUrl;//大会图片（dType=0时，会用到）
	private String img;
	private String title;//大会名称
	private String time;//大会时间（dType=0时，会用到）
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDynamicType() {
		return dynamicType;
	}
	public void setDynamicType(int dynamicType) {
		this.dynamicType = dynamicType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getHeadUrl() {
		return headUrl;
	}
	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
}
