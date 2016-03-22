package com.dashuai.meterial2.samples13;

import com.dashuai.meterial2.R;
import com.dashuai.meterial2.R.id;
import com.dashuai.meterial2.R.layout;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;

public class NestedScrollViewAct extends AppCompatActivity implements
		OnClickListener {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.nested_scroll);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		toolbar.setNavigationOnClickListener(this);

		CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
		mCollapsingToolbarLayout.setTitle("��ǧ��");
		// ͨ��CollapsingToolbarLayout�޸�������ɫ
		mCollapsingToolbarLayout.setExpandedTitleColor(Color.RED);// ���û�û����ʱ״̬��������ɫ
		mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);// ����������Toolbar���������ɫ

	}

	@Override
	public void onClick(View v) {
		onBackPressed();
	}
}
