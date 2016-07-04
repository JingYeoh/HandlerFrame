package com.jkb.handler.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.business.HandlerManager;
import com.business.config.PIDs;

/**
 * Activity的基类
 * 
 * @author Administrator
 * 
 */
public abstract class BaseActivity extends Activity {

	protected static String TAG;
	protected Context context;
	protected int PID;

	private Handler handler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			handleRstMessage(msg.what, msg.obj);
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TAG = this.getClass().getSimpleName();
		HandlerManager.getInstance().registerHandler(PID, handler);// 注册handler
	}

	/**
	 * 初始化方法
	 */
	protected abstract void init();

	/**
	 * 初始化View
	 */
	protected abstract void initView();

	/**
	 * 初始化监听器
	 */
	protected abstract void initListener();

	/**
	 * 初始化数据
	 */
	protected abstract void initData();

	/**
	 * 处理消息类
	 * 
	 * @param what
	 * @param obj
	 */
	protected abstract void handleRstMessage(int what, Object obj);

	/**
	 * 发送消息，使用Control的Handler
	 * 
	 * @param what
	 * @param bun
	 */
	public void baseSendMessage(int what, Bundle bun) {
		Message msg = new Message();
		msg.what = what;
		msg.arg1 = PID;
		msg.arg2 = PIDs.Control;
		msg.obj = bun;
		HandlerManager.getInstance().sendMessage(msg);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		HandlerManager.getInstance().unRegisterHandler(PID);
	}
}
