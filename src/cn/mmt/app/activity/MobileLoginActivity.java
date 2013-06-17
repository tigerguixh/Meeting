package cn.mmt.app.activity;

import cn.mmt.app.widget.ActionBar;
import cn.mmt.app.widget.ActionBar.IntentAction;
import android.app.Activity;
import android.app.SearchManager.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MobileLoginActivity extends Activity implements OnClickListener{
	private ActionBar actionBar;
	private EditText login;
	private EditText register;
	private TextView forgetPwd;
	private Button loginButton,registerButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mobile);
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		forgetPwd = (TextView) this.findViewById(R.id.forgetPwd);
		login = (EditText) this.findViewById(R.id.mobilenum);
		register = (EditText) this.findViewById(R.id.password);
		actionBar = (ActionBar) this.findViewById(R.id.actionbar);
		loginButton = (Button) this.findViewById(R.id.login);
		registerButton = (Button) this.findViewById(R.id.register);
		loginButton.setOnClickListener(this);
		registerButton.setOnClickListener(this);
		forgetPwd.setOnClickListener(this);
		actionBar.setBtnText("返回", "");
		actionBar.setHomeAction(new IntentAction(this, new Intent(this, LoginActivity.class), R.drawable.btn_bar_switch));
		actionBar.setTitle(getResources().getText(R.string.mobile_name));
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.login:
			
			break;
		case R.id.register:
			Intent intent = new Intent(new Intent(MobileLoginActivity.this,MobileRegistActivity.class));
			intent.putExtra("message", "注册");
			startActivity(intent);
			break;
		case R.id.forgetPwd:
			Intent intent2 = new Intent(new Intent(MobileLoginActivity.this,MobileRegistActivity.class));
			intent2.putExtra("message", "忘记密码");
			startActivity(intent2);
			break;
		}
	}
}
