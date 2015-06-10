package com.pot.gathering.activitys;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.pot.gathering.BaseActivity;
import com.pot.gathering.BaseApplication;
import com.pot.gathering.R;
import com.pot.gathering.Bean.InviteBean;
import com.pot.gathering.adapter.MyInviteGridAdapter;
import com.pot.gathering.view.MyGridView;

public class InviteActivity extends BaseActivity {
	
	private ImageView imgAdd;
	private MyGridView gridInvite;
	private MyGridView gridTheme;
	private MyGridView gridGame;
	private EditText edtAddress;
	
	private ArrayList<InviteBean> mArrayList;
	private MyInviteGridAdapter mInviteAdapter;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.invite);
		
		initObj();
		
		initView();
	}
	
	private void initObj(){
		mArrayList = new ArrayList<InviteBean>();
		mInviteAdapter = new MyInviteGridAdapter(this, mArrayList);
	}
	
	private void initView(){
		
		setTitleStyle(true);
		
		setTitle(true, getResources().getString(R.string.invite));
		
		imgAdd 		= (ImageView) findViewById(R.id.img_add);
		gridInvite 	= (MyGridView) findViewById(R.id.grid_invite);
		gridTheme 	= (MyGridView) findViewById(R.id.grid_theme);
		gridGame 	= (MyGridView) findViewById(R.id.grid_game);
		edtAddress 	= (EditText) findViewById(R.id.edt_address);
		
		imgAdd.setOnClickListener(this);
		
		gridInvite.setAdapter(mInviteAdapter);
		
		testData();
		
	}
	
	private void testData(){
		for(int i = 0; i < 6; i++){
			InviteBean bean = new InviteBean();
			bean.setName("name"+i);
			bean.setImgHead("");
			mArrayList.add(bean);
		}
		mInviteAdapter.notifyDataSetChanged();
		
		calculateGridViewWidth(gridInvite, 6);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.img_add:
			addItem(""+mArrayList.size());
			break;
		default:
			break;
		}
	}
	
	private void addItem(String name){
		InviteBean bean = new InviteBean();
		bean.setName("name"+name);
		bean.setImgHead("");
		mArrayList.add(0,bean);// 在首位添加数据
		
		mInviteAdapter.notifyDataSetChanged();
		
		calculateGridViewWidth(gridInvite, mArrayList.size());
	}
	
	private void calculateGridViewWidth(GridView gridView, int count){
		float itemWidth = getResources().getDimension(R.dimen.gridviewitem);
		float itemSpace = getResources().getDimension(R.dimen.dp10);
		int maxCount = (int) (BaseApplication.screenWidth/(itemWidth+itemSpace));
		if(count > maxCount){
			count = maxCount;
		}
		int width = (int) (count *itemWidth + (count - 1) * itemSpace);
		gridView.setNumColumns(count);
		RelativeLayout.LayoutParams params = new LayoutParams(width, LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
		params.addRule(RelativeLayout.BELOW, R.id.img_add);
		gridView.setLayoutParams(params);
	}
}
