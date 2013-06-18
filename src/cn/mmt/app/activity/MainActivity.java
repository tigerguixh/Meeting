package cn.mmt.app.activity;



import cn.mmt.app.widget.ActionBar;
import cn.mmt.app.widget.ActionBar.AbstractAction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.Toast;
import cn.mmt.app.widget.ActionBar.IntentAction;
/**
 * @author Adil Soomro
 * 
 */
public class MainActivity extends FragmentActivity implements OnTabChangeListener {
	private ActionBar actionBar;
	/** Called when the activity is first created. */
	private TabHost tabHost;
	/** 底部tab标示符 */
	private int tab_id=1;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		init();
	}

	private void init() {
		actionBar = (ActionBar) findViewById(R.id.actionbar);
		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		//璁剧疆actionbar鐩稿叧灞炴�
		
		
		tabHost.setOnTabChangedListener(this);
		tabHost.setup();
		setTabs();
	}
	/**
	 * 重定向actionBar
	 */
	
	private void setTabs() {
		addTab("find", R.drawable.tab_find, R.id.find);
		addTab("meeting", R.drawable.tab_meeting, R.id.meeting);
		addTab("friend", R.drawable.tab_friend, R.id.friend);
		addTab("account", R.drawable.tab_account, R.id.meeting);
	}

	private void addTab(String labelId, int drawableId, int c) {
		TabHost.TabSpec spec = tabHost.newTabSpec(labelId);
		View tabIndicator = LayoutInflater.from(this).inflate(
				R.layout.tab_indicator, tabHost.getTabWidget(), false);
		ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icon);
		icon.setImageResource(drawableId);
		spec.setIndicator(tabIndicator);
		spec.setContent(c);
		tabHost.addTab(spec);
	}
	
	public static Intent createIntent(Context context) {
		Intent i = new Intent(context, MainActivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		return i;
	}
	private class SearchAction extends AbstractAction {

		public SearchAction() {
			super(R.drawable.btn_bar_switch);
		}

		public void performAction(View view) {
			Intent i = new Intent(getApplicationContext(), SearchActivity.class);
			startActivity(i);

		}

	}
	public void onClick(View v) {
//		tabHost.setCurrentTab(0); 
//		initBar(R.id.friend);
	}

	public void onTabChanged(String tabId) {
		// TODO Auto-generated method stub
		if ("find".equals(tabId)) {
			actionBar.removeAllActions();
			actionBar.setBtnText("城市", "搜索");
			actionBar.setHomeAction(new IntentAction(this, new Intent(this, CityActivity.class), R.drawable.btn_bar_switch));
			actionBar.addAction(new SearchAction());
			actionBar.setTitle(getResources().getText(R.string.app_name));
		}else if ("meeting".equals(tabId)){
			actionBar.removeAllActions();
			actionBar.setBtnText("管理", "");
			actionBar.setHomeAction(new IntentAction(this, new Intent(this, CityActivity.class), R.drawable.btn_bar_switch));
			actionBar.setTitle(getResources().getText(R.string.my_meeting));
		}else if ("friend".equals(tabId)){
			actionBar.removeAllActions();
			actionBar.setBtnText("＋", "");
			actionBar.setHomeAction(new IntentAction(this, new Intent(this, CityActivity.class), R.drawable.btn_bar_switch));
		}else if ("account".equals(tabId)){
			actionBar.removeAllActions();
			actionBar.clearHomeAction();
			actionBar.setTitle(getResources().getText(R.string.account_number));
		}
	}

}