package cn.mmt.app.model;

public class UserLogin {
	private int result;//0成功,1密码错误,2其他错误
	private int userId;//用户ID不为0
	private String headUrl;//头像地址
	private String userName;
	private String sign;//签名
	private String location;//所在地
	private int dynmicNum;//动态总数
	private int attentionNum;//关注数
	private int fansNum;//粉丝数
	
}
