package cn.mmt.app.activity;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import cn.mmt.app.model.AppKey;
import cn.mmt.app.utils.AccessTokenKeeper;

import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.Weibo;
import com.weibo.sdk.android.WeiboAuthListener;
import com.weibo.sdk.android.WeiboDialogError;
import com.weibo.sdk.android.WeiboException;
import com.weibo.sdk.android.api.AccountAPI;
import com.weibo.sdk.android.api.StatusesAPI;
import com.weibo.sdk.android.net.RequestListener;

public class LoginSinaWeiBo {
	public static void login(Weibo weibo, final Context context,
			StatusesAPI statuses) {
		weibo.authorize(context, new WeiboAuthListener() {

			public void onWeiboException(WeiboException arg0) {
				// TODO Auto-generated method stub

			}

			public void onError(WeiboDialogError arg0) {
				// TODO Auto-generated method stub

			}

			public void onComplete(Bundle data) {
				String token = data.getString("access_token");
				AppKey key = new AppKey();
				String expire = data.getString("expires_in");
				// String refresh = data.getString("refresh_token");
				Oauth2AccessToken oauth2Token = new Oauth2AccessToken(token,
						expire);
				key.setSinaKey(oauth2Token);
				AccessTokenKeeper.keepAccessToken(context, key);
				if (oauth2Token.isSessionValid()) {
					saveAccessToken(token, expire, context);
					// 获取用户UID
					AccountAPI account = new AccountAPI(oauth2Token);
					account.getUid(new RequestListener() {

						public void onIOException(IOException arg0) {
							// TODO Auto-generated method stub

						}

						public void onError(WeiboException arg0) {
							// TODO Auto-generated method stub

						}

						public void onComplete(String arg0) {
							// {"uid":"xxxxxxx"}
							try {
								JSONObject obj = new JSONObject(arg0);
								String uid = obj.getString("uid");
								context.getSharedPreferences("DATA",
										Context.MODE_PRIVATE).edit()
										.putString("uid", uid).commit();
							} catch (JSONException e) {
								e.printStackTrace();
							}

						}
					});
					Toast.makeText(context, "授权成功", Toast.LENGTH_SHORT).show();
				}
			}

			public void onCancel() {
				// TODO Auto-generated method stub

			}
		});
	}

	public static void saveAccessToken(String token, String expire,
			Context context) {
		context.getSharedPreferences("DATA", Context.MODE_PRIVATE).edit()
				.putString("token", token).putString("expire", expire).commit();
	}

	public static boolean isAccessTokenExsited(Oauth2AccessToken oauth2Token,
			boolean isReady, Context context) {
		String token = context.getSharedPreferences("DATA",
				Context.MODE_PRIVATE).getString("token", null);
		String expire = context.getSharedPreferences("DATA",
				Context.MODE_PRIVATE).getString("expire", null);

		if (token != null && expire != null) {
			oauth2Token = new Oauth2AccessToken(token, expire);
			isReady = oauth2Token.isSessionValid();
			return isReady;
		}

		return token != null && expire != null;
	}

	public static void update(Context context, String msg,
			StatusesAPI statuses, Oauth2AccessToken oauth2Token) {
		// msg = URLEncoder.encode(msg);

		if (msg.length() > 140) {
			msg = msg.substring(0, 140);
		}
		String token = context.getSharedPreferences("DATA",
				Context.MODE_PRIVATE).getString("token", null);
		String expire = context.getSharedPreferences("DATA",
				Context.MODE_PRIVATE).getString("expire", null);
		oauth2Token = new Oauth2AccessToken(token, expire);
		if (statuses == null) {
			statuses = new StatusesAPI(oauth2Token);
		}

		statuses.update(msg, "0", "0", new MyRequestListener());
	}
}
