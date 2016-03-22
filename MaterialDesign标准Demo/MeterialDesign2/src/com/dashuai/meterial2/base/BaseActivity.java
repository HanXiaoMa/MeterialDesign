package com.dashuai.meterial2.base;

import com.dashuai.meterial2.R;
import com.dashuai.meterial2.R.id;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;

public abstract class BaseActivity extends AppCompatActivity implements
		OnClickListener {

	private String toolBarTitle;
	private Toolbar toolbar;

	public Toolbar getToolbar() {
		return toolbar;
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		youShouldSetContentView();
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle(toolBarTitle);// 标题的文字需在setSupportActionBar之前，不然会无效
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		toolbar.setNavigationOnClickListener(this);
	}

	public abstract void youShouldSetContentView();

	public void setToolBarTitle(String toolBarTitle) {
		this.toolBarTitle = toolBarTitle;
	}

	@Override
	public void onClick(View v) {
		onBackPressed();
	}

}
