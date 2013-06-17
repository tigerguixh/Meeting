package cn.mmt.app.activity;

import java.io.IOException;

import android.util.Log;

import com.weibo.sdk.android.WeiboException;
import com.weibo.sdk.android.net.RequestListener;

public class MyRequestListener implements RequestListener {

	public void onComplete(String arg0) {
		// TODO Auto-generated method stub

	}

	public void onError(WeiboException arg0) {
		// TODO Auto-generated method stub
		Log.e("onError()", arg0.getMessage());
	}

	public void onIOException(IOException arg0) {
		// TODO Auto-generated method stub
		Log.d("onComplete", arg0.toString());
	}

}
