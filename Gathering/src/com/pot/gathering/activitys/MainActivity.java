package com.pot.gathering.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pot.gathering.BaseActivity;
import com.pot.gathering.R;

public class MainActivity extends BaseActivity {

	private TextView textNotify;// 通知栏
	private TextView textInvite;// 邀约
	private TextView textCeng;// 蹭饭
	private TextView textContact;// 联系人
	private RelativeLayout layoutMessage;// 邀信
	private TextView textMessageCount;// 信息数
	private TextView textOther;// 其他
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		initObj();
		
		initView();
	}
	/**
	 * 初始化对象
	* @Title: initObj
	* @Description: TODO
	* @param 
	* @return void
	* @throws
	 */
	private void initObj(){
		
	}
	
	/**
	 * 初始化view
	* @Title: initView
	* @Description: TODO
	* @param 
	* @return void
	* @throws
	 */
	private void initView(){
		textNotify  = (TextView) findViewById(R.id.text_notify);
		textInvite  = (TextView) findViewById(R.id.text_invite);
		textCeng    = (TextView) findViewById(R.id.text_ceng);
		textContact = (TextView) findViewById(R.id.text_contact);
		textOther	= (TextView) findViewById(R.id.text_other);
		
		layoutMessage = (RelativeLayout) findViewById(R.id.layout_message);
		textMessageCount = (TextView) findViewById(R.id.text_msg_count);
		
		textInvite.setOnClickListener(this);
		textCeng.setOnClickListener(this);
		textContact.setOnClickListener(this);
		textOther.setOnClickListener(this);
		layoutMessage.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.text_invite:// 跳转到邀约界面
			break;
		case R.id.text_ceng:// 跳转到蹭饭界面
			break;
		case R.id.text_contact:// 跳转到联系人界面
			break;
		case R.id.text_other:// 跳转到其他界面
			break;
		case R.id.layout_message:// 跳转到邀信界面
			break;
		default:
			break;
		}
	}
	
}
