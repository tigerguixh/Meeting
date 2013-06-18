package cn.mmt.app.activity;

import cn.mmt.app.common.Common;
import cn.mmt.app.model.AppKey;
import cn.mmt.app.utils.AccessTokenKeeper;

import com.tencent.weibo.sdk.android.api.util.Util;
import com.tencent.weibo.sdk.android.component.Authorize;
import com.tencent.weibo.sdk.android.component.sso.AuthHelper;
import com.tencent.weibo.sdk.android.component.sso.OnAuthListener;
import com.tencent.weibo.sdk.android.component.sso.WeiboToken;
import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.Weibo;
import com.weibo.sdk.android.api.StatusesAPI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private Button btn_try_out;
	private TextView txt_phone_number, txt_xl_weibo, txt_tx_weibo;
	private Oauth2AccessToken oauth2Token;
	private Weibo weibo;
	private boolean isReady = false;
	private StatusesAPI statuses;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		/** 全屏显示 */
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		weibo = Weibo.getInstance(Common.APP_KEY, Common.OK_CALLBACK);
		setContentView(R.layout.login);
		init();
	}

	private void init() {
		btn_try_out = (Button) findViewById(R.id.btn_try_out);
		findViewById(R.id.txt_phone_number).setOnClickListener(
				new TxtLoginListener());
		findViewById(R.id.txt_xl_weibo).setOnClickListener(
				new TxtLoginListener());
		findViewById(R.id.txt_tx_weibo).setOnClickListener(
				new TxtLoginListener());

		btn_try_out.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LoginActivity.this,
						MainActivity.class);
				startActivity(intent);
			}
		});
	}

	private class TxtLoginListener implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.txt_phone_number:
				// Toast.makeText(LoginActivity.this, "电话",
				// Toast.LENGTH_SHORT).show();
				startActivity(new Intent(LoginActivity.this,
						MobileLoginActivity.class));
				break;
			case R.id.txt_xl_weibo:
				if (!LoginSinaWeiBo.isAccessTokenExsited(oauth2Token, isReady,
						LoginActivity.this)) {
					LoginSinaWeiBo.login(weibo, LoginActivity.this, statuses);
					isReady = true;
					
				}
				break;
			case R.id.txt_tx_weibo:
				String key=Util.getSharePersistent(LoginActivity.this, "ACCESS_TOKEN");
				if ("".equals(key)||"0".equals(key)) {
					long appid = Long.valueOf(Util.getConfig().getProperty(
							"APP_KEY"));

					String app_secket = Util.getConfig().getProperty("APP_KEY_SEC");
					auth(appid, app_secket);
				}else {
					startActivity(new Intent(LoginActivity.this, MainActivity.class));
				}
				
				
				break;

			}
		}

	}
	private void auth(long appid, String app_secket) {
		final Context context = this.getApplicationContext();
		AuthHelper.register(this, appid, app_secket, new OnAuthListener() {
			
			public void onWeiboVersionMisMatch() {
				// TODO Auto-generated method stub
				Toast.makeText(LoginActivity.this, "微博版本太低",
						Toast.LENGTH_SHORT).show();
				Intent i = new Intent(LoginActivity.this, Authorize.class);
				startActivity(i);
			}
			
			public void onWeiBoNotInstalled() {
				// TODO Auto-generated method stub
				Toast.makeText(LoginActivity.this,  "未安装腾讯微博", Toast.LENGTH_SHORT)
				.show();
		Intent i = new Intent(LoginActivity.this, Authorize.class);
		startActivity(i);
			}
			
			public void onAuthPassed(String name, WeiboToken token) {
				// TODO Auto-generated method stub
				Toast.makeText(LoginActivity.this, "授权成功" , Toast.LENGTH_SHORT).show();
				//
				Util.saveSharePersistent(context, "ACCESS_TOKEN",
						token.accessToken);
				Util.saveSharePersistent(context, "EXPIRES_IN",
						String.valueOf(token.expiresIn));
				Util.saveSharePersistent(context, "OPEN_ID", token.openID);
				// Util.saveSharePersistent(context, "OPEN_KEY", token.omasKey);
				Util.saveSharePersistent(context, "REFRESH_TOKEN", "");
				// Util.saveSharePersistent(context, "NAME", name);
				// Util.saveSharePersistent(context, "NICK", name);
				Util.saveSharePersistent(context, "CLIENT_ID", Util.getConfig()
						.getProperty("APP_KEY"));
				Util.saveSharePersistent(context, "AUTHORIZETIME",
						String.valueOf(System.currentTimeMillis() / 1000l));
				startActivity(new Intent(LoginActivity.this, MainActivity.class));
				
			}
			
			public void onAuthFail(int result, String err) {
				// TODO Auto-generated method stub
				Toast.makeText(LoginActivity.this, "登陆失败" , Toast.LENGTH_SHORT)
				.show();
			}
		});
		AuthHelper.auth(this, "");
	}
}
