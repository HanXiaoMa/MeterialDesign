package com.dashuai.meterial2.sample2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dashuai.meterial2.R;
import com.dashuai.meterial2.base.BaseActivity;

public class TextInputActivity extends BaseActivity {

	TextInputLayout layout1;
	TextInputLayout layout2;

	@Override
	public void youShouldSetContentView() {
		setContentView(R.layout.activity_main2);
		setToolBarTitle("TextInputLayout"); // 必须先设置
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		layout1 = (TextInputLayout) findViewById(R.id.et_username);
		layout2 = (TextInputLayout) findViewById(R.id.et_password);
		
		layout1.setErrorEnabled(true);
		layout2.setErrorEnabled(true);
		
	}

	public void btnClick(View v) {

		EditText e1 = layout1.getEditText();
		EditText e2 = layout2.getEditText();

		if (e1.getEditableText().toString().equals("")) {
			layout1.setError("请输入QQ号");
			layout2.setError(null);
		} else if (e2.getEditableText().toString().equals("")) {
			layout2.setError("请输入密码");
			layout1.setError(null);
		} else {
			layout1.setError(null);
			layout2.setError(null);
			Toast.makeText(this, "登陆成功", 0).show();
		}

	}

	@Override
	public void onClick(View arg0) {
		onBackPressed();
	}

}
