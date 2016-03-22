package com.dashuai.meterial2.sample9;

import com.dashuai.meterial2.R;
import com.dashuai.meterial2.R.id;
import com.dashuai.meterial2.R.layout;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;

public class CoordTabAct extends AppCompatActivity implements
		AppBarLayout.OnOffsetChangedListener, OnClickListener {

	MyPageAdapter pagerAdapter;

	private AppBarLayout appBarLayout;

	private ViewPager viewPager;

	private TabLayout tabLayout;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main9);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		toolbar.setNavigationOnClickListener(this);

		CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
		mCollapsingToolbarLayout.setTitle("花千骨");
		// 通过CollapsingToolbarLayout修改字体颜色
		mCollapsingToolbarLayout.setExpandedTitleColor(Color.RED);// 设置还没收缩时状态下字体颜色
		mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);// 设置收缩后Toolbar上字体的颜色

		pagerAdapter = new MyPageAdapter(getSupportFragmentManager());
		viewPager = (ViewPager) findViewById(R.id.viewpager);

		viewPager.setOffscreenPageLimit(4); // 防止Fragment被销毁的简单处理。

		viewPager.setAdapter(pagerAdapter);
		tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
		tabLayout.setTabTextColors(Color.BLACK, Color.RED);
		tabLayout.setBackgroundColor(Color.WHITE);
		tabLayout.setupWithViewPager(viewPager);
		tabLayout.setTabMode(TabLayout.MODE_FIXED);
		tabLayout.setTabsFromPagerAdapter(pagerAdapter);

		appBarLayout = (AppBarLayout) findViewById(R.id.appbar);

	}

	// 解决SwipeRefreshLayout和CollapsingToolbarLayout冲突问题
	@Override
	public void onOffsetChanged(AppBarLayout appBarLayout, int i) {

		PageFragment p = pagerAdapter.getCurrentFragment();

		if (null != p) {
			p.mSwipeRefresh.setEnabled(i == 0);
		}

	}

	@Override
	protected void onResume() {
		super.onResume();
		appBarLayout.addOnOffsetChangedListener(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		appBarLayout.removeOnOffsetChangedListener(this);
	}

	@Override
	public void onClick(View v) {
		onBackPressed();
	}

}
