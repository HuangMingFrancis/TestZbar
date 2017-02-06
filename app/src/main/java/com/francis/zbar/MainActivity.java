package com.francis.zbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zbar.lib.CaptureActivity;


public class MainActivity extends Activity {
	private final static int SCANNIN_GREQUEST_CODE = 1;
	TextView tvResult;
	Button btnCheck;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setView();
		setListener();
	}

	private void setView() {
		tvResult = (TextView) findViewById(R.id.tv_result);
		btnCheck = (Button) findViewById(R.id.btn_check);
	}

	private void setListener() {
		btnCheck.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, CaptureActivity.class);
				// intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case SCANNIN_GREQUEST_CODE:
			if (resultCode == RESULT_OK) {
				String result = data.getStringExtra("QR_CODE");
				// TODO 获取结果，做逻辑操作
				tvResult.setText(result);
			} else {
				Toast.makeText(this, "无法获取扫码结果", Toast.LENGTH_SHORT).show();
			}
			break;
		}
	}
}
