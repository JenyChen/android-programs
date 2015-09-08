package com.pot.gathering.messagemanage;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import cn.bmob.push.PushConstants;

import com.pot.gathering.R;
import com.pot.gathering.messagemanage.inf.IReceiverMessage;

public class MyPushMessageReceiver extends BroadcastReceiver{

	private IReceiverMessage mIReceiverMessage;
	public MyPushMessageReceiver() {
		// TODO Auto-generated constructor stub
	}
	
	public MyPushMessageReceiver(IReceiverMessage iReceiverMessage) {
		// TODO Auto-generated constructor stub
		mIReceiverMessage = iReceiverMessage;
	}
	
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        if(intent.getAction().equals(PushConstants.ACTION_MESSAGE)){
        	//{"alert":"测试"}
            Log.d("bmob", "客户端收到推送内容："+intent.getStringExtra(PushConstants.EXTRA_PUSH_MESSAGE_STRING));
            String message = intent.getStringExtra(PushConstants.EXTRA_PUSH_MESSAGE_STRING);
            
            if(mIReceiverMessage != null){
            	mIReceiverMessage.success(message);
            }
            
            // 发送通知
    		NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    		
    		Notification n = new Notification();  
            n.icon = R.drawable.ic_launcher;  
            n.tickerText = "团聚收到消息";  
            n.when = System.currentTimeMillis();  
            //n.flags=Notification.FLAG_ONGOING_EVENT;  
            Intent i = new Intent();  
            PendingIntent pi = PendingIntent.getActivity(context, 0, i, 0);  
            n.setLatestEventInfo(context, "消息", message, pi);  
            n.defaults |= Notification.DEFAULT_SOUND;
            n.flags = Notification.FLAG_AUTO_CANCEL;
            nm.notify(1, n);
        }
    }

}