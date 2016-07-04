package com.jkb.handler.logic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;

import com.business.HandlerManager;
import com.business.config.Config;
import com.business.config.MsgIDs;
import com.business.config.PIDs;
import com.jkb.handler.base.BaseLogic;

/**
 * 登录的逻辑类
 * 
 * @author Administrator
 * 
 */
public class LgcLogin extends BaseLogic {

	private String userName;
	private String passWord;

	public LgcLogin(Context context, Message msg) {
		super(context);
		PID = PIDs.Login;
		if (msg.obj != null) {
			bun = (Bundle) msg.obj;
			userName = bun.getString(Config.KEY_USERNAME);
			passWord = bun.getString(Config.KEY_PASSWORD);
		}
		msgSource = msg.arg1;
		HandlerManager.getInstance().registerHandler(PID, handler);
	}

	public LgcLogin(Context context) {
		super(context);
	}

	@SuppressLint("NewApi")
	public void login() {
		if (userName.isEmpty() | passWord.isEmpty() | userName.equals("none")) {
			sendMsg(MsgIDs.ResLogin, MsgIDs.LoginRst_NonDatas);
		} else if (userName.equals(Config.VALUE_USERNAME)
				&& passWord.equals(Config.KEY_PASSWORD)) {
			sendMsg(MsgIDs.ResLogin, MsgIDs.LoginRst_Success);
		} else if (!userName.equals(Config.VALUE_USERNAME)
				|| !passWord.equals(Config.KEY_PASSWORD)) {
			sendMsg(MsgIDs.ResLogin, MsgIDs.LoginRst_WrongDatas);
		}
	}

}
