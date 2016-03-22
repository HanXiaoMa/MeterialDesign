package com.dashuai.meterial2.samples12;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;

import com.dashuai.material.widget.GridRecyclerView;
import com.dashuai.meterial2.R;
import com.dashuai.meterial2.R.id;
import com.dashuai.meterial2.R.layout;
import com.dashuai.meterial2.R.mipmap;
import com.dashuai.meterial2.samples789adapter.Hero;

public class GridRecyclerAct extends AppCompatActivity implements
		OnClickListener {

	GridRecyclerView mRecyclerView;
	GridLayoutManager mLayoutManager;
	MyGridAdapter adapter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.gridrecyler);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		toolbar.setNavigationOnClickListener(this);

		CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
		mCollapsingToolbarLayout.setTitle("��ǧ��");
		// ͨ��CollapsingToolbarLayout�޸�������ɫ
		mCollapsingToolbarLayout.setExpandedTitleColor(Color.RED);// ���û�û����ʱ״̬��������ɫ
		mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);// ����������Toolbar���������ɫ

		mRecyclerView = (GridRecyclerView) findViewById(R.id.grid_recycler);
		mRecyclerView.setHasFixedSize(true);
		mLayoutManager = new GridLayoutManager(this, 3);
		mRecyclerView.setLayoutManager(mLayoutManager);

		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
			setAdapter();
		}

	}

	@Override
	public void onEnterAnimationComplete() {
		// TODO Auto-generated method stub
		super.onEnterAnimationComplete();
		setAdapter();
		
		mRecyclerView.startLayoutAnimation();
	}

	private void setAdapter() {
		adapter = new MyGridAdapter();
		addData();
		addData();
		adapter.setDataList(list);
		mRecyclerView.setAdapter(adapter);
	}

	List<Hero> list = new ArrayList<Hero>();

	public void addData() {
		list.add(new Hero("��ȵ", R.mipmap.bianque));
		list.add(new Hero("����", R.mipmap.likui));
		list.add(new Hero("��ʦʦ", R.mipmap.lishishi));
		list.add(new Hero("������", R.mipmap.lisiming));
		list.add(new Hero("������", R.mipmap.liubowen));
		list.add(new Hero("������", R.mipmap.wuzetian));
		list.add(new Hero("С��", R.mipmap.xiaoqiao));
		list.add(new Hero("����", R.mipmap.yuefei));

	}

	@Override
	public void onClick(View v) {
		onBackPressed();
	}

}
