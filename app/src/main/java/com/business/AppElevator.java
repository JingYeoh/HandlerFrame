package com.business;

import android.app.Application;
import android.content.Context;

/**
 * 基础的Application类，用于初始化所有实例
 * 
 * 完成各个实例的持久化
 * 
 * @author Administrator
 * 
 */
public class AppElevator extends Application {

	private static AppElevator instance;
	private Communication communication;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		// 初始化handleManager
		HandlerManager.Builder.init(getContext());

		// 开启逻辑处理线程
		communication = new Communication();
		communication.start();
	}

	/**
	 * 返回全局的context
	 * 
	 * @return
	 */
	public static Context getContext() {
		return instance;
	}
}
