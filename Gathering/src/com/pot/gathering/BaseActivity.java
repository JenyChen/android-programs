package com.pot.gathering;

import net.hockeyapp.android.FeedbackManager;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pot.gathering.config.Comment;
import com.pot.gathering.tool.DeviceUuidFactory;

public class BaseActivity extends Activity implements OnClickListener {

	private TextView textTitle;
	private LinearLayout layoutBack;
	private ImageView imgBack;
	private TextView textTitleLeft;
	private DeviceUuidFactory mDeviceUuidFactory;
	private String APP_ID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		mDeviceUuidFactory = new DeviceUuidFactory(this);
		APP_ID = mDeviceUuidFactory.getDeviceUuid().toString();
		checkForUpdates();
	}

	/**
	 * 设置标题样式
	 * 
	 * @Title: setTitleStyle
	 * @Description: TODO
	 * @param @param includBack 如果需要返回键，则为true；不然就显示为中间标题
	 * @return void
	 * @throws
	 */
	protected void setTitleStyle(boolean includBack) {
		textTitle = (TextView) findViewById(R.id.text_title);
		layoutBack = (LinearLayout) findViewById(R.id.layout_back);
		imgBack = (ImageView) findViewById(R.id.img_back);
		textTitleLeft = (TextView) findViewById(R.id.text_title_left);

		if (includBack) {
			textTitle.setVisibility(View.GONE);
			layoutBack.setVisibility(View.VISIBLE);
		} else {
			textTitle.setVisibility(View.VISIBLE);
			layoutBack.setVisibility(View.GONE);
		}

		layoutBack.setOnClickListener(this);
	}

	protected void setTitle(boolean includBack, String title) {
		if (includBack) {
			textTitleLeft.setText(title);
		} else {
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

	@Override
	public void onResume() {
		super.onResume();
		checkForCrashes();
	}

	@Override
	public void onPause() {
		super.onPause();
		unregisterManagers();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		unregisterManagers();
	}

	private void checkForCrashes() {
//		CrashManager.register(this, APP_ID);
	}

	private void checkForUpdates() {
		// Remove this for store builds!
//		UpdateManager.register(this, APP_ID);
	}

	private void unregisterManagers() {
//		UpdateManager.unregister();
		// unregister other managers if necessary...
	}

	/**
	 * Call the method showFeedbackActivity in an onClick,
	 * onMenuItemSelected, or onOptionsItemSelected listener method.
	* @Title: showFeedbackActivity
	* @Description: TODO
	* @param 
	* @return void
	* @throws
	 */
	public void showFeedbackActivity() {
		FeedbackManager.register(this, APP_ID, null);
		FeedbackManager.showFeedbackActivity(this);
	}
	
}
