package com.pot.gathering;

import android.app.Application;
import android.content.Context;
import android.view.WindowManager;
import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;

import com.pot.gathering.config.Comment;
import com.pot.gathering.config.Config;
import com.pot.gathering.database.DBHelper;

public class BaseApplication extends Application {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		new DBHelper(this);
		getScreenWidth();
		initBmobSDK();
	}
	
	private void getScreenWidth(){
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Comment.SCREEN_WIDTH = wm.getDefaultDisplay().getWidth();
		Comment.SCREEN_HEIGTH = wm.getDefaultDisplay().getHeight();
	}
	
	private void initBmobSDK(){
		// 初始化BmobSDK
	    Bmob.initialize(this, Config.APPKEY);
	    // 使用推送服务时的初始化操作
	    BmobInstallation.getCurrentInstallation(this).save();
	    String uid = BmobInstallation.getInstallationId(this);
	    // 启动推送服务
	    BmobPush.startWork(this, Config.APPKEY);
	}
	
}
