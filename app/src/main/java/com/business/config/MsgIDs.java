package com.business.config;

/**
 * 消息what信息，具体到执行不同的动作
 * 
 * @author Administrator
 * 
 */
public class MsgIDs {

	public static final int ReqLogin = 1010;
	public static final int ResLogin = 1011;
	public static final int ReqRegister = 1020;
	public static final int ResRegister = 1021;

	public static final String LogicResult = "Logicesult";
	public static final int LoginRst_NonDatas = 1;// 没有输入完全
	public static final int LoginRst_Success = 2;// 登录成功
	public static final int LoginRst_WrongDatas = 3;// 登录成功

}
