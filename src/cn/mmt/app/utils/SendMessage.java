package cn.mmt.app.utils;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import android.content.Context;
import cn.mmt.app.common.Common_Data_Url;
import cn.mmt.app.common.Common_Info;
import cn.mmt.app.http.HttpUtils;
import cn.mmt.app.http.ResolveJson;

public class SendMessage {
	public interface CallBackMessage{
		public void sendCallBackMessage(List<String> list);
	}
	/**
	 * 得到验证码
	 * @param phoneNum
	 * @param context
	 * @param callBackMessage
	 */
	public static void sendMessageAgainst(final String phoneNum,final Context context,final CallBackMessage callBackMessage){
		new Thread(new Runnable() {
			public void run() {
				// TODO Auto-generated method stub
				NameValuePair nameValuePair = new BasicNameValuePair(Common_Info.phone,phoneNum);
				String result = "";
				List<String> list = new ArrayList<String>();
				try {
					result = HttpUtils.postByHttpClient(context, Common_Data_Url.PHONE_URL, nameValuePair);
					list = ResolveJson.getPhoneMessage(result);
				} catch (Exception e) {
					
				}
				callBackMessage.sendCallBackMessage(list);
			}
		}).start();
	}
	/**
	 * 验证手机登陆
	 * @param phoneNum
	 * @param context
	 * @param callBackMessage
	 */
	public static void loginByMobile(final String phoneNum,final String nameMessage,final String pwdMessage,final Context context,final CallBackMessage callBackMessage){
		new Thread(new Runnable() {
			public void run() {
				// TODO Auto-generated method stub
				NameValuePair phone = new BasicNameValuePair(Common_Info.phone,phoneNum);
				NameValuePair name = new BasicNameValuePair(Common_Info.name,nameMessage);
				NameValuePair pwd = new BasicNameValuePair(Common_Info.pwd,pwdMessage);
				String result = "";
				List<String> list = new ArrayList<String>();
				try {
					result = HttpUtils.postByHttpClient(context, Common_Data_Url.PHONE_URL, phone,name,pwd);
					list = ResolveJson.getRegisterMessage(result);
				} catch (Exception e) {
					
				}
				callBackMessage.sendCallBackMessage(list);
			}
		}).start();
	}
	/**
	 * 手机登陆
	 * @param phoneNum
	 * @param pwdMessage
	 * @param context
	 * @param callBackMessage
	 */
	public static void loginMobileNumber(final String phoneNum,final String pwdMessage,final Context context,final CallBackMessage callBackMessage){
		new Thread(new Runnable() {
			public void run() {
				// TODO Auto-generated method stub
				NameValuePair phone = new BasicNameValuePair(Common_Info.phone,phoneNum);
				NameValuePair pwd = new BasicNameValuePair(Common_Info.pwd,pwdMessage);
				String result = "";
				List<String> list = new ArrayList<String>();
				try {
					result = HttpUtils.postByHttpClient(context, Common_Data_Url.PHONE_URL, phone,pwd);
					list = ResolveJson.getRegisterMessage(result);
				} catch (Exception e) {
					
				}
				callBackMessage.sendCallBackMessage(list);
			}
		}).start();
	}
}
