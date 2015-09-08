package com.pot.gathering.activitys;

import java.util.ArrayList;
import java.util.List;

import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;
import cn.bmob.push.PushConstants;

import com.pot.gathering.BaseActivity;
import com.pot.gathering.R;
import com.pot.gathering.adapter.MyImageGridAdapter;
import com.pot.gathering.adapter.MyTextGridAdapter;
import com.pot.gathering.bmobmanage.BmobManage;
import com.pot.gathering.config.Comment;
import com.pot.gathering.messagemanage.MyPushMessageReceiver;
import com.pot.gathering.messagemanage.inf.IReceiverMessage;
import com.pot.gathering.view.MyGridView;
import com.pot.gathering.view.SiteSelectView;
import com.pot.gathering.view.SiteSelectView.OnSelectListener;

public class InviteInfoActivity extends BaseActivity implements IReceiverMessage, OnSelectListener{

	private TextView textInvitor; // 邀请人
	private TextView textAddress;// 地点
	private MyGridView gridInvitedor;// 被邀请人
	private MyGridView gridGame;// 游戏
	private SiteSelectView viewSite;// 位置选择
	
	private ArrayList<String> mNameList; // 被邀人姓名列表
	private ArrayList<Integer> mGameList; // 游戏列表
	private MyTextGridAdapter mTextGridAdapter;// 被邀人适配器
	private MyImageGridAdapter mImageGridAdapter;// 游戏适配器
	
	private List<Integer> mSelectedSitePositions;// 已选位置集合
	private MyPushMessageReceiver mBroadcastReceiver;// 消息接收器
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.invite_info);
		
		initObj();
		
		initView();
		
		initViewSite();
		
		setAdapter();
		
		testDate();
	}
	
	private void initObj(){
		
		mNameList = new ArrayList<String>();
		mGameList = new ArrayList<Integer>();
		mTextGridAdapter = new MyTextGridAdapter(this, mNameList);
		mImageGridAdapter = new MyImageGridAdapter(this, mGameList);
		
		mSelectedSitePositions = new ArrayList<Integer>();
		for (int i = 0; i < 3; i++) {
			mSelectedSitePositions.add(i);
		}
		
		
		mBroadcastReceiver = new MyPushMessageReceiver(this);
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(PushConstants.ACTION_MESSAGE);
		registerReceiver(mBroadcastReceiver, intentFilter);
	}
	
	private void initView(){
		
		setTitleStyle(true);
		
		setTitle(true, getResources().getString(R.string.inviteinfo));
		
		textInvitor 	= (TextView) findViewById(R.id.text_invitor);
		textAddress 	= (TextView) findViewById(R.id.text_address);
		gridInvitedor 	= (MyGridView) findViewById(R.id.grid_invitedor);
		gridGame		= (MyGridView) findViewById(R.id.grid_game);
		viewSite 		= (SiteSelectView) findViewById(R.id.view_site);
	}
	
	private void initViewSite(){
		float space = getResources().getDimension(R.dimen.dp30)*2;
		viewSite.setXCenter((Comment.SCREEN_WIDTH-space)/2);
		viewSite.setSelectListener(this);
		viewSite.setSiteCount(8);
		viewSite.setSiteHasSelected(mSelectedSitePositions);
		viewSite.invalidate();
	}
	
	private void setAdapter(){
		gridInvitedor.setAdapter(mTextGridAdapter);
		gridGame.setAdapter(mImageGridAdapter);
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(mBroadcastReceiver);
	}

	@Override
	public void success(String message) {
		// TODO Auto-generated method stub
//		mSelectedSitePositions.add(5);
//		viewSite.setSiteHasSelected(mSelectedSitePositions);
//		viewSite.invalidate();
	}

	@Override
	public void selecte(int position) {
		// TODO Auto-generated method stub
		BmobManage.getInstance().pushMsgToSingle(this, BmobManage.getInstance().getInstallationId(this), "位置"+position+"已选");
	}
	
	private void testDate(){
		for (int i = 0; i < 8; i++) {
			mNameList.add("小红"+i);
		}
		
		mGameList.add(Comment.TYPE_PK);
		mGameList.add(Comment.TYPE_MJ);
		mGameList.add(Comment.TYPE_PK);
		
		updateData();
	}
	
	private void updateData(){
		mTextGridAdapter.notifyDataSetChanged();
		mImageGridAdapter.notifyDataSetChanged();
	}
	
}
