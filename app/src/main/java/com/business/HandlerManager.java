package com.business;

import java.util.ArrayList;
import java.util.HashMap;

import dalvik.annotation.TestTarget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Handler的一个管理类
 * 
 * 用于管理所有的handler，包装到本身的容器中，让其所在类的handler持久，可以被外来者访问到
 * 
 * 相当于一个中转站，用于处理所有的handler和消息的分发
 * 
 * 设计模式：享元模式
 * 
 * @author JustKiddingBaby
 * 
 */
public class HandlerManager {

	private HashMap<Integer, Handler> hm;
	private ArrayList<HashMap<Integer, Handler>> mHandlers;
	private Context context;
	private static HandlerManager instance;

	private static final String TAG = "HandlerManager";

	private HandlerManager(Context context) {
		hm = new HashMap<Integer, Handler>();
		mHandlers = new ArrayList<HashMap<Integer, Handler>>();
		this.context = context;
	}

	/**
	 * 返回一个实例
	 * 
	 * @return
	 */
	public static HandlerManager getInstance() {
		return instance;
	}

	/**
	 * 内部的为了构件HandleManager的builder类
	 * 
	 * @author Administrator
	 * 
	 */
	public static class Builder {
		/**
		 * 初始化对象
		 * 
		 * @param context
		 * @return
		 */
		public static void init(Context context) {
			instance = new HandlerManager(context);
		}
	}

	/**
	 * 发送消息
	 * 
	 * 根据消息里面包含的参数判断是哪个handler发送的消息
	 * 
	 * what 执行动作
	 * 
	 * arg1 PID,消息来源
	 * 
	 * arg2 执行的handler,key
	 * 
	 * obj 传递的数据
	 * 
	 * @param msg
	 */
	public void sendMessage(Message msg) {
		Handler handler = new Handler(AppElevator.getContext().getMainLooper());
		ArrayList<HashMap<Integer, Handler>> cHandlers = new ArrayList<HashMap<Integer, Handler>>();
		cHandlers = (ArrayList<HashMap<Integer, Handler>>) mHandlers.clone();
		for (HashMap<Integer, Handler> mhandler : mHandlers) {
			if (mhandler.containsKey(msg.arg2)) {
				handler = (Handler) mhandler.get(msg.arg2);
			} else {
				Log.w(TAG, "----没有key为" + msg.arg2 + "的handler");
				return;
			}
		}
		Log.i(TAG, "----HandlerManager.sendMessage----key=" + msg.arg2);
		Message message = Message.obtain();
		message.copyFrom(msg);
		handler.sendMessage(message);
	}

	/**
	 * 注册一个handler到管理类中
	 * 
	 * @param key
	 *            持有的key,用于注册，获取、销毁handler
	 * @param handler
	 *            handler本身
	 */
	public void registerHandler(int key, Handler handler) {
		synchronized (mHandlers) {
			if (mHandlers != null) {
				for (HashMap<Integer, Handler> mhandler : mHandlers) {
					if (mhandler.containsKey(key)) {
						mHandlers.remove(mhandler);
						hm.put(key, handler);
						mHandlers.add(hm);
						return;
					}
				}
				hm.put(key, handler);
				mHandlers.add(hm);
			}
		}
	}

	/**
	 * 取消handler的注册
	 * 
	 * @param key
	 *            注册时候用到的key
	 */
	public void unRegisterHandler(int key) {
		synchronized (mHandlers) {
			if (mHandlers != null && mHandlers.size() != 0) {
				for (HashMap<Integer, Handler> handler : mHandlers) {
					if (handler.containsKey(key)) {
						mHandlers.remove(handler);
					}
				}
			}
		}
	}

	/**
	 * 销毁所有Handler数据
	 */
	public void clearHandlers() {
		mHandlers.clear();
		hm.clear();
	}
}
