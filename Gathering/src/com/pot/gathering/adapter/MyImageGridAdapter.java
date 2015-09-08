package com.pot.gathering.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.pot.gathering.R;
import com.pot.gathering.Bean.InviteBean;
import com.pot.gathering.config.Comment;
import com.pot.gathering.utils.ViewHolder;
import com.pot.gathering.view.CircleImageView;

public class MyImageGridAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private ArrayList<Integer> mArrayList;
	private Context mContext;
	
	public MyImageGridAdapter(Context context, ArrayList<Integer> arrayList) {
		mArrayList = arrayList;
		mInflater = LayoutInflater.from(context);
		mContext = context;
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
			convertView = (View) mInflater.inflate(R.layout.image_layout, null);
		}
		ImageView img = ViewHolder.get(convertView, R.id.img_pic);
		
		float space = mContext.getResources().getDimension(R.dimen.dp5) * 2;
		float imgWidth = Comment.SCREEN_WIDTH / 3;
		int imgSize = (int) (imgWidth - space - 60);
		LinearLayout.LayoutParams params = new LayoutParams(imgSize, imgSize);
		img.setLayoutParams(params);
		
		int type = mArrayList.get(position);
		switch (type) {
		case Comment.TYPE_PK:
			img.setBackgroundResource(R.drawable.pk);
			break;
		case Comment.TYPE_MJ:
			img.setBackgroundResource(R.drawable.mj);
			break;
		default:
			break;
		}
		return convertView;
	}
}
