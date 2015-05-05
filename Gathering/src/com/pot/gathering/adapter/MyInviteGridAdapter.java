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
import com.pot.gathering.view.CircleImageView;

public class MyInviteGridAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private ArrayList<InviteBean> mArrayList;
	
	public MyInviteGridAdapter(Context context, ArrayList<InviteBean> arrayList) {
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
		ViewHolder viewH = null;
		if(convertView == null){
			viewH = new ViewHolder();
			convertView = (View) mInflater.inflate(R.layout.invite_item, null);
			viewH.imgHead = (CircleImageView) convertView.findViewById(R.id.img_head);
			viewH.textName = (TextView) convertView.findViewById(R.id.text_name);
			convertView.setTag(viewH);
		}else{
			viewH = (ViewHolder) convertView.getTag();
		}
		
		InviteBean bean = mArrayList.get(position);
		if(bean != null){
			viewH.textName.setText(bean.getName());
		}
		return convertView;
	}

	class ViewHolder{
		protected CircleImageView imgHead;
		protected TextView textName;
	}
}
