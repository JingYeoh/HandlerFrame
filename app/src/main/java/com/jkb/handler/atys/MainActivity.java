package com.jkb.handler.atys;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.jkb.handler.R;

public class MainActivity extends Activity {

	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button) findViewById(R.id.bt_login);
	}
}
