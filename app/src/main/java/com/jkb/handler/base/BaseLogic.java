package com.jkb.handler.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.business.HandlerManager;
import com.business.config.MsgIDs;

/**
 * 网络逻辑处理的基类
 * 
 * @author Administrator
 * 
 */
public class BaseLogic {

	protected int PID;// 表示当前的PID标识
	protected int msgSource;// 表示来源的PID标识
	protected Context context;
	protected static String TAG;
	protected Bundle bun;

	protected Handler handler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			handleRstMessage(msg.what, msg.obj);
			TAG = getClass().getSimpleName();
		};
	};

	public BaseLogic(Context context) {
		this.context = context;
	}

	/**
	 * 处理消息类
	 * 
	 * @param what
	 * @param obj
	 */
	protected void handleRstMessage(int what, Object obj) {
		Log.i(TAG, "----------handleRstMessage--------");
	}

	/**
	 * 发送消息
	 * 
	 * @param what
	 * @param result
	 */
	public void sendMsg(int what, int result) {
		Message msgSend = new Message();
		msgSend.what = what;
		msgSend.arg2 = msgSource;
		bun = new Bundle();
		bun.putInt(MsgIDs.LogicResult, result);
		msgSend.obj = bun;
		HandlerManager.getInstance().sendMessage(msgSend);
	}
}
