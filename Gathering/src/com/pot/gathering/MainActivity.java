package com.pot.gathering;

import org.apache.http.Header;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.pot.gathering.http.AsynHttpManager;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		test();
	}
	
	private void test(){
		String url = "http://10.24.50.14:8550/user/GetUsers";
		RequestParams params = new RequestParams();
		params.put("name", "admin");
		params.setContentEncoding("utf-8");
		AsynHttpManager.getInstance().get(this, url, params, new AsyncHttpResponseHandler(){

			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2,
					Throwable arg3) {
				// TODO Auto-generated method stub
				String s = new String(arg2);
			}

			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
				// TODO Auto-generated method stub
				String s = new String(arg2);
				Log.v("Jeny", "s=="+s);
			}
		});
	}
}
