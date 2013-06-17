package cn.mmt.app.activity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

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
import android.widget.Toast;

public class MobileRegistActivity extends Activity implements OnClickListener{
	private ActionBar actionBar;
	private EditText numbEditText;
	private Button nextButton;
	private MyHandler myHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registerm);
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		myHandler = new MyHandler(this);
		actionBar = (ActionBar) this.findViewById(R.id.actionbar);
		numbEditText = (EditText) this.findViewById(R.id.mobilenum);
		nextButton = (Button) this.findViewById(R.id.next);
		nextButton.setOnClickListener(this);
		actionBar.setBtnText("返回", "");
		actionBar.setHomeAction(new IntentAction(this, new Intent(this, LoginActivity.class), R.drawable.btn_bar_switch));
		actionBar.setTitle(getResources().getText(R.string.check_number));
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.next:
			if(numbEditText.getText() == null || "".equals(numbEditText.getText().toString())){
				Toast.makeText(this, "电话不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
			SendMessage.sendMessageAgainst(numbEditText.getText().toString(), this, new CallBackMessage() {
				public void sendCallBackMessage(List<String> list) {
					// TODO Auto-generated method stub
					Message message = Message.obtain(myHandler);
					message.obj = list;
					message.sendToTarget();
				}
			});
			break;
		}
	}
	public class MyHandler extends Handler{
		private final WeakReference<MobileRegistActivity> reference;
		public MyHandler(MobileRegistActivity mainActivity){
			reference = new WeakReference<MobileRegistActivity>(mainActivity);
		}
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			MobileRegistActivity mainActivity = reference.get();
			List<String> list = (List<String>)msg.obj;
			if(mainActivity != null){
				if(list.size() == 0){
					Toast.makeText(mainActivity, "网络连接错误", Toast.LENGTH_SHORT).show();
				}else{
					if(getIntent().getStringExtra("message").equals("注册")){
						Intent intent = new Intent(MobileRegistActivity.this,VerificationActivity.class);
						intent.putExtra("mobile_num", numbEditText.getText().toString());
						startActivity(intent);
					}else{
						Intent intent = new Intent(MobileRegistActivity.this,MobileForgetPassword.class);
						intent.putExtra("mobile_num", numbEditText.getText().toString());
						startActivity(intent);
					}
				}
			}
		}
	}
}
