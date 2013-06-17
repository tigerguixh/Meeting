package cn.mmt.app.activity;

import java.lang.ref.WeakReference;
import java.util.List;

import cn.mmt.app.activity.MobileRegistActivity.MyHandler;
import cn.mmt.app.utils.SendMessage;
import cn.mmt.app.utils.SendMessage.CallBackMessage;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MobileForgetPassword extends Activity implements OnClickListener{
	private TextView textView,textView3;
	private MyHandler myHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forgetpassword);
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		myHandler = new MyHandler(this);
		textView = (TextView) this.findViewById(R.id.textView1);
		textView3 = (TextView) this.findViewById(R.id.textView3);
		textView.setText("您的手机号：" + getIntent().getStringExtra("mobile_num"));
		textView3.setOnClickListener(this);
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.textView3:
			SendMessage.sendMessageAgainst(getIntent().getStringExtra("mobile_num"), this, new CallBackMessage() {
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
	public static class MyHandler extends Handler{
		private final WeakReference<MobileForgetPassword> reference;
		public MyHandler(MobileForgetPassword mainActivity){
			reference = new WeakReference<MobileForgetPassword>(mainActivity);
		}
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			MobileForgetPassword mainActivity = reference.get();
			List<String> list = (List<String>)msg.obj;
			if(mainActivity != null){
				if(list.size() == 0){
					Toast.makeText(mainActivity, "网络连接错误", Toast.LENGTH_SHORT).show();
				}
				Toast.makeText(mainActivity, "success", Toast.LENGTH_SHORT).show();
			}
		}
	}
}
