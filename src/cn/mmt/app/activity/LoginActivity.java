package cn.mmt.app.activity;

import cn.mmt.app.common.Common;
import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.Weibo;
import com.weibo.sdk.android.api.StatusesAPI;

import android.app.Activity;
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
					startActivity(new Intent(LoginActivity.this,
							MobileLoginActivity.class));
				}
				break;
			case R.id.txt_tx_weibo:
				Toast.makeText(LoginActivity.this, "腾讯", Toast.LENGTH_SHORT)
						.show();
				break;

			}
		}

	}
}
