package com.jkb.handler.atys;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.business.config.Config;
import com.business.config.MsgIDs;
import com.business.config.PIDs;
import com.jkb.handler.R;
import com.jkb.handler.base.BaseActivity;

public class AtyLogin extends BaseActivity {

	private Button button;
	private EditText etUserName;
	private EditText etPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		PID = PIDs.AtyLogin;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	@Override
	protected void init() {
		context = this;
		initView();
	}

	@Override
	protected void initView() {
		button = (Button) findViewById(R.id.bt_login);
		etUserName = (EditText) findViewById(R.id.et_user);
		etPassword = (EditText) findViewById(R.id.et_pw);
		initListener();
	}

	@Override
	protected void initListener() {
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				sendMessage(MsgIDs.ReqLogin);
			}
		});
	}

	@Override
	protected void initData() {

	}

	/**
	 * 发送消息
	 * 
	 * @param what
	 */
	public void sendMessage(int what) {
		switch (what) {
		case MsgIDs.ReqLogin:
			reqLogin(what);
			break;
		}
	}

	/**
	 * 请求登录
	 * 
	 * @param what
	 */
	private void reqLogin(int what) {
		Bundle bundle = new Bundle();
		bundle.putString(Config.KEY_USERNAME, etUserName.getText().toString());
		bundle.putString(Config.KEY_PASSWORD, etPassword.getText().toString());
		baseSendMessage(what, bundle);
	}

	@Override
	protected void handleRstMessage(int what, Object obj) {
		Bundle bundle = new Bundle();
		bundle = (Bundle) obj;
		int rst = bundle.getInt(MsgIDs.LogicResult);
		switch (rst) {
		case MsgIDs.LoginRst_NonDatas:
			Toast.makeText(context, "请输入全部内容！", 0).show();
			break;
		case MsgIDs.LoginRst_Success:
			Toast.makeText(context, "登录成功！", 0).show();
			break;
		case MsgIDs.LoginRst_WrongDatas:
			Toast.makeText(context, "用户名或者密码错误！", 0).show();
			break;
		default:
			Toast.makeText(context, "异常信息处理！", 0).show();
			break;
		}
	}
}
