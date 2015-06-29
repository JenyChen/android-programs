package com.pot.gathering.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pot.gathering.R;
import com.pot.gathering.Bean.ContactBean;

public class ContactAdapter extends BaseAdapter {

	private ArrayList<ContactBean> mArrayList;
	private LayoutInflater mInflater;
	private Context mContext;
	
	public ContactAdapter(Context context, ArrayList<ContactBean> arrayList) {
		mArrayList = arrayList;
		mInflater = LayoutInflater.from(context);
		mContext = context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mArrayList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mArrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder viewH = null;
		if(convertView == null){
			viewH = new ViewHolder();
			convertView = mInflater.inflate(R.layout.contact_list_item, null);
			viewH.textName = (TextView) convertView.findViewById(R.id.text_name);
			convertView.setTag(viewH);
		}else{
			viewH = (ViewHolder) convertView.getTag();
		}
		
		ContactBean bean = mArrayList.get(position);
		if(bean != null){
			if(bean.isTitle()){
				viewH.textName.setBackgroundResource(R.drawable.contact_list_title_bg);
				viewH.textName.setTextSize(mContext.getResources().getDimension(R.dimen.minsize_8));
				viewH.textName.setTextColor(mContext.getResources().getColor(R.color.grey_light));
			}
			viewH.textName.setText(bean.getName());
		}
		return convertView;
	}
	
	class ViewHolder{
		protected TextView textName;
	}

}
