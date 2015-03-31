package com.pot.gathering.http;

import org.apache.http.HttpEntity;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;


public class AsynHttpManager {

	private static AsynHttpManager mAsynHttpManager = new AsynHttpManager();
	private static AsyncHttpClient httpClient;
	
	public AsynHttpManager(){
		httpClient = new AsyncHttpClient();
	}
	
	public static AsynHttpManager getInstance(){
		return mAsynHttpManager;
	}
	
	public void post(String url, RequestParams params, ResponseHandlerInterface responseHandler){
		httpClient.post(url, params, responseHandler);
	}
	
	public void post(Context context, String url, HttpEntity entity, ResponseHandlerInterface responseHandler){
		httpClient.post(context, url, entity, "application/json", responseHandler);
	}
	
	public void get(Context context, String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
		httpClient.get(context, url, params, responseHandler);
	}
}
