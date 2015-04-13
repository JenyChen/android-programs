package com.pot.gathering;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

public class LoginActivity extends BaseActivity {

	private DrawerLayout mDrawerLayout;
	private LinearLayout layoutRegist;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		initObject();
		
		initView();
	}
	
	public void initObject() {
		
	}
	
	public void initView() {
		
		mDrawerLayout 	= (DrawerLayout) findViewById(R.id.drawer_layout);
		layoutRegist 	= (LinearLayout) findViewById(R.id.layout_regist);
		
		layoutRegist.setOnClickListener(this);
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.layout_regist:
			mDrawerLayout.openDrawer(Gravity.RIGHT);
			break;

		default:
			break;
		}
	}
}
