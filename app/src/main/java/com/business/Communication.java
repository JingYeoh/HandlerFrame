package com.business;

import com.business.config.MsgIDs;
import com.business.config.PIDs;
import com.jkb.handler.logic.LgcLogin;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/**
 * 动作的处理类
 * 
 * 执行PID=Control的handler执行的动作
 * 
 * @author J
 * 
 */
public class Communication extends Thread {

	private Handler handler;
	private static final String TAG = "Communication";

	@Override
	public void run() {
		Looper.prepare();// 准备消息队列
		// 内部处理
		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				Log.i(TAG, "-------我接受到了Message--------");
				switch (msg.what) {
				case MsgIDs.ReqLogin:
					Log.i(TAG, "-------我要执行Login的请求--------");
					LgcLogin lgcLogin = new LgcLogin(AppElevator.getContext(),
							msg);
					lgcLogin.login();
					break;
				case MsgIDs.ResLogin:
					Log.i(TAG, "-------我要执行Login的响应--------");
					break;
				case MsgIDs.ReqRegister:

					break;
				case MsgIDs.ResRegister:

					break;
				}
			}
		};
		HandlerManager.getInstance().registerHandler(PIDs.Control, handler);// 注册到handler管理类中
		Looper.loop();// 执行循环取消息的操作
	}
}
