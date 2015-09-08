package com.pot.gathering.bmobmanage;

import java.util.List;

import android.content.Context;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobPushManager;
import cn.bmob.v3.BmobQuery;

public class BmobManage {

	private static BmobManage mBmobManage;
	
	public BmobManage(){
		
	}
	
	public static BmobManage getInstance(){
		mBmobManage = new BmobManage();
		return mBmobManage;
	}
	
	/**
	 * 获取设备
	* @Title: getInstallationId
	* @Description: TODO
	* @param @param context
	* @param @return
	* @return String
	* @throws
	 */
	public static String getInstallationId(Context context){
		return BmobInstallation.getInstallationId(context);
	}
	
	/**
	 * 发送给单个客户端
	* @Title: pushMsgToSingle
	* @Description: TODO
	* @param @param context
	* @param @param installationId
	* @param @param message
	* @return void
	* @throws
	 */
	public static void pushMsgToSingle(Context context, String installationId,
										String message){
		BmobPushManager<BmobInstallation> bmobPush = new BmobPushManager<BmobInstallation>(context);
		BmobQuery<BmobInstallation> query = BmobInstallation.getQuery();
		query.addWhereEqualTo("installationId", installationId);
		bmobPush.setQuery(query);
		bmobPush.pushMessage(message);
	}
	
	/**
	 * 发送给多个客户端
	* @Title: pushMsgToMult
	* @Description: TODO
	* @param @param context
	* @param @param installationId
	* @param @param message
	* @return void
	* @throws
	 */
	public static void pushMsgToMult(Context context, List<String> installationIdList ,
										String message){
		BmobPushManager<BmobInstallation> bmobPush = new BmobPushManager<BmobInstallation>(context);
		BmobQuery<BmobInstallation> query = BmobInstallation.getQuery();
		for (int i = 0; i < installationIdList.size(); i++) {
			query.addWhereEqualTo("installationId", installationIdList.get(i));
		}
		
		bmobPush.setQuery(query);
		bmobPush.pushMessage(message);
	}
	
	/**
	 * 给Android平台的终端推送
	* @Title: pushMsgToAndroid
	* @Description: TODO
	* @param @param context
	* @param @param message
	* @return void
	* @throws
	 */
	public static void pushMsgToAndroid(Context context, String message){
		BmobPushManager<BmobInstallation> bmobPush = new BmobPushManager<BmobInstallation>(context);
		BmobQuery<BmobInstallation> query = BmobInstallation.getQuery();
		query.addWhereEqualTo("deviceType", "android");
		bmobPush.setQuery(query);
		bmobPush.pushMessage(message);
	}
	
}
