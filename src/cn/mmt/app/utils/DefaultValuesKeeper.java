package cn.mmt.app.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import cn.mmt.app.model.AppKey;
import cn.mmt.app.model.DefaultValues;

import com.weibo.sdk.android.Oauth2AccessToken;

public class DefaultValuesKeeper {
		private static final String PREFERENCES_NAME = "default";
		private static String CITY_ID="cityID";
		/**
		 * 保存默认参数到SharedPreferences
		 * @param context Activity 上下文环境
		 * @param token Oauth2AccessToken
		 */
		public static void keepDefaultValues(Context context, DefaultValues values) {
			SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
			Editor editor = pref.edit();
			editor.putString(CITY_ID, values.getCity_id());
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
		 * 从SharedPreferences读取默认参数
		 * @param context
		 * @return Oauth2AccessToken
		 */
		public static DefaultValues readDefaultValues(Context context){
			DefaultValues values=new DefaultValues();
			SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
			values.setCity_id(pref.getString(CITY_ID, ""));
			return values;
		}
}
