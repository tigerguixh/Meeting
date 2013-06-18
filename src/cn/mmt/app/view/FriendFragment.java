package cn.mmt.app.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import cn.mmt.app.activity.R;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FriendFragment extends Fragment {
	private ListView listView;
	private MyAdapter myAdapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		myAdapter = new MyAdapter();
		init();
	}
	//初始化数据
	private void init() {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();;
		for(int i = 0; i < 20; i++){
			list.add("test" + i);
		}
		myAdapter.setList(list);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View view=inflater.inflate(R.layout.friend, null,false);
		listView = (ListView) view.findViewById(R.id.listView1);
		listView.setAdapter(myAdapter);
		myAdapter.notifyDataSetChanged();
		return view;
	}
	public class MyAdapter extends BaseAdapter{
		private List<String> list = new ArrayList<String>();
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}
		public void setList(List<String> data){
			if(!list.contains(data)){
				list.addAll(data);
			}
		}
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHodler viewHodler = null;
			if(convertView == null){
				viewHodler = new ViewHodler();
				convertView = LayoutInflater.from(getActivity()).inflate(R.layout.rightlayout, null);
				viewHodler.headImg = (ImageView) convertView.findViewById(R.id.touxiang);
				viewHodler.picuter = (ImageView) convertView.findViewById(R.id.imageView1);
				viewHodler.nickName = (TextView) convertView.findViewById(R.id.txtright);
				viewHodler.shareTextView = (TextView) convertView.findViewById(R.id.txtright1);
				viewHodler.timeTextView = (TextView) convertView.findViewById(R.id.txtright2);
				viewHodler.contentView = (TextView) convertView.findViewById(R.id.textView1);
				viewHodler.logContent = (TextView) convertView.findViewById(R.id.textView2);
				viewHodler.logTimeTextView = (TextView) convertView.findViewById(R.id.textView3);
				convertView.setTag(viewHodler);
			}else{
				viewHodler = (ViewHodler) convertView.getTag();
			}
			viewHodler.nickName.setText(list.get(position));
			return convertView;
		}
	}
	public static class ViewHodler{
		public ImageView headImg,picuter;
		public TextView nickName,shareTextView,timeTextView,contentView,logContent,logTimeTextView;
	}
}