package com.pot.gathering;

import android.app.Application;
import android.content.Context;
import android.view.WindowManager;

public class BaseApplication extends Application {

	public static int screenWidth = 0;
	public static int screenHeight = 0;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		getScreenWidth();
	}
	
	private void getScreenWidth(){
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		screenWidth = wm.getDefaultDisplay().getWidth();
		screenHeight = wm.getDefaultDisplay().getHeight();
	}
}
