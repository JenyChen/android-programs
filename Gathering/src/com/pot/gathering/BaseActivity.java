package com.pot.gathering;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BaseActivity extends Activity implements OnClickListener{

	private TextView textTitle;
	private LinearLayout layoutBack;
	private ImageView imgBack;
	private TextView textTitleLeft;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
	}
	
	/**
	 * 设置标题样式
	* @Title: setTitleStyle
	* @Description: TODO
	* @param @param includBack 如果需要返回键，则为true；不然就显示为中间标题
	* @return void
	* @throws
	 */
	protected void setTitleStyle(boolean includBack){
		textTitle = (TextView) findViewById(R.id.text_title);
		layoutBack = (LinearLayout) findViewById(R.id.layout_back);
		imgBack = (ImageView) findViewById(R.id.img_back);
		textTitleLeft = (TextView) findViewById(R.id.text_title_left);
		
		if(includBack){
			textTitle.setVisibility(View.GONE);
			layoutBack.setVisibility(View.VISIBLE);
		}else{
			textTitle.setVisibility(View.VISIBLE);
			layoutBack.setVisibility(View.GONE);
		}
		
		layoutBack.setOnClickListener(this);
	}
	
	protected void setTitle(boolean includBack, String title){
		if(includBack){
			textTitleLeft.setText(title);
		}else{
			textTitle.setText(title);
		}
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_back:
			finish();
			break;

		default:
			break;
		}
	}
}
