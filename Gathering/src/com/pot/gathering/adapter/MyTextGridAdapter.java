package com.pot.gathering.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pot.gathering.R;
import com.pot.gathering.Bean.InviteBean;
import com.pot.gathering.utils.ViewHolder;
import com.pot.gathering.view.CircleImageView;

public class MyTextGridAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private ArrayList<String> mArrayList;
	
	public MyTextGridAdapter(Context context, ArrayList<String> arrayList) {
		mArrayList = arrayList;
		mInflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		return mArrayList.size();
	}

	@Override
	public Object getItem(int position) {
		return mArrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = (View) mInflater.inflate(R.layout.text_layout, null);
		}
		TextView textName = ViewHolder.get(convertView, R.id.text_content);
		String content = mArrayList.get(position);
		if(content != null){
			textName.setText(content);
		}
		return convertView;
	}

}
