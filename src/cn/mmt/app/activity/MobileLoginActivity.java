package cn.mmt.app.activity;

import java.lang.ref.WeakReference;
import java.util.List;

import cn.mmt.app.utils.SendMessage;
import cn.mmt.app.utils.SendMessage.CallBackMessage;
import cn.mmt.app.widget.ActionBar;
import cn.mmt.app.widget.ActionBar.IntentAction;
import android.app.Activity;
import android.app.ActionBar.Tab;
import android.app.SearchManager.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MobileLoginActivity extends Activity implements OnClickListener{
	private ActionBar actionBar;
	private EditText login;
	private EditText password;
	private TextView forgetPwd;
	private Button loginButton,registerButton;
	private MyHandler myHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mobile);
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		myHandler = new MyHandler(this);
		forgetPwd = (TextView) this.findViewById(R.id.forgetPwd);
		login = (EditText) this.findViewById(R.id.mobilenum);
		password = (EditText) this.findViewById(R.id.password);
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
			String phoneNum = login.getText().toString();
			String pwd = password.getText().toString();
			if("".equals(phoneNum) || "".equals(pwd)){
				Toast.makeText(this, "字段不能为空",Toast.LENGTH_SHORT).show();
				return;
			}
			SendMessage.loginMobileNumber(phoneNum, pwd, this, new CallBackMessage() {
				
				public void sendCallBackMessage(List<String> list) {
					// TODO Auto-generated method stub
					Message message = Message.obtain(myHandler);
					message.obj = list;
					message.sendToTarget();
				}
			});
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
	public static class MyHandler extends Handler{
		private final WeakReference<MobileLoginActivity> reference;
		public MyHandler(MobileLoginActivity mainActivity){
			reference = new WeakReference<MobileLoginActivity>(mainActivity);
		}
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			MobileLoginActivity mainActivity = reference.get();
			List<String> list = (List<String>)msg.obj;
			if(mainActivity != null){
				if(list.size() == 0){
					Toast.makeText(mainActivity, "网络连接错误", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(mainActivity, "登陆成功", Toast.LENGTH_SHORT).show();
				}
			}
		}
	}
}
