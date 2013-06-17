package cn.mmt.app.utils;

import cn.mmt.app.model.AppKey;

import com.weibo.sdk.android.Oauth2AccessToken;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
/**
 * 该类用于保存Oauth2AccessToken到sharepreference，并提供读取功能
 * @author xiaowei6@staff.sina.com.cn
 *
 */
public class AccessTokenKeeper {
	private static final String PREFERENCES_NAME = "com_handmeeting";
	/**
	 * 保存accesstoken到SharedPreferences
	 * @param context Activity 上下文环境
	 * @param token Oauth2AccessToken
	 */
	public static void keepAccessToken(Context context, AppKey key) {
		SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
		Editor editor = pref.edit();
		editor.putString("sinatoken", key.getSinaKey().getToken());
		editor.putString("meetingkey", key.getHandMeeting());
		editor.commit();
	}
	/**
	 * 清空sharepreference
	 * @param context
	 */
	public static void clear(Context context){
	    SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
	    Editor editor = pref.edit();
	    editor.clear();
	    editor.commit();
	}

	/**
	 * 从SharedPreferences读取accessstoken
	 * @param context
	 * @return Oauth2AccessToken
	 */
	public static AppKey readAccessToken(Context context){
		AppKey appKey=new AppKey();
		Oauth2AccessToken token = new Oauth2AccessToken();
		SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
		appKey.setHandMeeting(pref.getString("meetingkey", ""));
		token.setToken(pref.getString("sinatoken", ""));
		appKey.setSinaKey(token);
		
		return appKey;
	}
}
