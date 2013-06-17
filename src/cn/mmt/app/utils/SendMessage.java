package cn.mmt.app.utils;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import android.content.Context;
import cn.mmt.app.common.Common_Data_Url;
import cn.mmt.app.http.HttpUtils;
import cn.mmt.app.http.ResolveJson;

public class SendMessage {
	public interface CallBackMessage{
		public void sendCallBackMessage(List<String> list);
	}
	public static void sendMessageAgainst(final String phoneNum,final Context context,final CallBackMessage callBackMessage){
		new Thread(new Runnable() {
			public void run() {
				// TODO Auto-generated method stub
				NameValuePair nameValuePair = new BasicNameValuePair("phone",phoneNum);
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
}
