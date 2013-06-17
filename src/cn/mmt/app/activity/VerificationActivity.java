package cn.mmt.app.activity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import cn.mmt.app.activity.MobileRegistActivity.MyHandler;
import cn.mmt.app.common.Common_Data_Url;
import cn.mmt.app.http.HttpUtils;
import cn.mmt.app.http.ResolveJson;
import cn.mmt.app.utils.SendMessage;
import cn.mmt.app.utils.SendMessage.CallBackMessage;
import cn.mmt.app.widget.ActionBar;
import cn.mmt.app.widget.ActionBar.IntentAction;
import android.app.Activity;
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

public class VerificationActivity extends Activity implements OnClickListener{
	private TextView textView,textView3;
	private EditText mobilenum1,EditText01,EditText02;
	private ActionBar actionBar;
	private MyHandler myHandler;
	private Button button;
	private static final int AGAINST = 1;
	private static final int LOGIN = 2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.verification);
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		myHandler = new MyHandler(this);
		actionBar = (ActionBar) this.findViewById(R.id.actionbar);
		textView = (TextView) this.findViewById(R.id.textView1);
		textView3 = (TextView) this.findViewById(R.id.textView3);
		mobilenum1 = (EditText) this.findViewById(R.id.mobilenum1);
		EditText01 = (EditText) this.findViewById(R.id.EditText01);
		EditText02 = (EditText) this.findViewById(R.id.EditText02);
		button = (Button) this.findViewById(R.id.next);
		button.setOnClickListener(this);
		textView.setText("您的手机号：" + getIntent().getStringExtra("mobile_num"));
		actionBar.setBtnText("返回", "");
		actionBar.setHomeAction(new IntentAction(this, new Intent(this, LoginActivity.class), R.drawable.btn_bar_switch));
		actionBar.setTitle(getResources().getText(R.string.check_number));
		textView3.setOnClickListener(this);
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.textView3:
			mobilenum1.setText("");
			EditText01.setText("");
			EditText02.setText("");
			SendMessage.sendMessageAgainst(getIntent().getStringExtra("mobile_num"), this, new CallBackMessage() {
				
				public void sendCallBackMessage(List<String> list) {
					// TODO Auto-generated method stub
					Message message = Message.obtain(myHandler);
					message.what = AGAINST;
					message.obj = list;
					message.sendToTarget();
				}
			});
			break;
		case R.id.next:
			String phone = mobilenum1.getText().toString();
			String name = EditText01.getText().toString();
			String pwd = EditText02.getText().toString();
			if("".equals(phone) || "".equals(name) || "".equals(pwd)){
				Toast.makeText(this, "字段不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
			if(pwd.length() < 6){
				Toast.makeText(this, "密码最少为六位，请重新输入", Toast.LENGTH_SHORT).show();
				return;
			}
			SendMessage.loginByMobile(phone,name, pwd,this, new CallBackMessage() {
				
				public void sendCallBackMessage(List<String> list) {
					// TODO Auto-generated method stub
					Message message = Message.obtain(myHandler);
					message.what = LOGIN;
					message.obj = list;
					message.sendToTarget();
				}
			});
			break;
		}
	}
	public static  class MyHandler extends Handler{
		private final WeakReference<VerificationActivity> reference;
		public MyHandler(VerificationActivity mainActivity){
			reference = new WeakReference<VerificationActivity>(mainActivity);
		}
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			VerificationActivity mainActivity = reference.get();
			List<String> list = (List<String>)msg.obj;
			if(mainActivity != null){
					switch (msg.what) {
					case AGAINST:
						if(list.size() == 0){
							Toast.makeText(mainActivity, "网络连接错误", Toast.LENGTH_SHORT).show();
						}else{
							Toast.makeText(mainActivity, "message_success", Toast.LENGTH_SHORT).show();
						}
						break;
					case LOGIN:
						if(list.size() == 0){
							Toast.makeText(mainActivity, "网络连接错误", Toast.LENGTH_SHORT).show();
						}else{
							Toast.makeText(mainActivity, "login_success", Toast.LENGTH_SHORT).show();
						}
						break;
					}
				}
			}
		}
	}
