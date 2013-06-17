package cn.mmt.app.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.mmt.app.common.Common_Data_Url;
import cn.mmt.app.common.Common_Info;
import cn.mmt.app.http.HttpUtils;
import cn.mmt.app.widget.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import cn.mmt.app.widget.ActionBar.IntentAction;

public class CityActivity extends Activity {
	private ActionBar actionBar;
	private ListView city_list;
	private Map<String, String> map_data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.city_list);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		actionBar = (ActionBar) findViewById(R.id.actionbar);
		city_list = (ListView) findViewById(R.id.list_city);
		map_data=new HashMap<String, String>();
		actionBar.setBtnText("返回", "");
		actionBar.setHomeAction(new IntentAction(this, MainActivity
				.createIntent(this), R.drawable.btn_bar_switch));
		actionBar.setTitle(getResources().getText(R.string.choice_city));
		city_list.setVerticalScrollBarEnabled(false);
		
		
		
		new AsyncCityList().execute(Common_Data_Url.CITY_LIST_URL);
	}

	public static Intent createIntent(Context context) {
		Intent i = new Intent(context, CityActivity.class);

		return i;
	}

	public class AsyncCityList extends AsyncTask<String, Void, List<String>> {

		@Override
		protected List<String> doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<String> citydata = new ArrayList<String>();
			String city_json=HttpUtils.postByHttpClient(CityActivity.this, Common_Data_Url.CITY_LIST_URL);
			try {
				JSONArray ciry_array=new JSONObject(city_json).getJSONObject(Common_Info.data).getJSONArray(Common_Info.list);
				for (int i = 0; i < ciry_array.length(); i++) {
					JSONObject jObject=ciry_array.getJSONObject(i);
					map_data.put(jObject.getString("id"), jObject.getString("name"));
					citydata.add(jObject.getString("name"));   
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return citydata;
		}
		@Override
		protected void onPostExecute(List<String> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			city_list.setAdapter(new ArrayAdapter<String>(CityActivity.this,
					R.layout.city_list_item, result));
			
		}

	}

}
