package cn.mmt.app.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import cn.mmt.app.activity.R;
import android.view.View;
import android.widget.TextView;

public class FriendFragment extends Fragment {
	private TextView textView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View view=inflater.inflate(R.layout.rightlayout, null,false);
		textView=(TextView)view.findViewById(R.id.txtright);
		textView.setText("掌上");
		return view;
	}
}