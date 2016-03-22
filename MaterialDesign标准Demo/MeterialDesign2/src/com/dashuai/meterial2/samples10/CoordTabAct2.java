package com.dashuai.meterial2.samples10;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;

import com.dashuai.meterial2.R;
import com.dashuai.meterial2.samples10.adapter.MyPageAdapter2;
import com.dashuai.meterial2.samples10.adapter.PageFragment2;

public class CoordTabAct2 extends AppCompatActivity implements
		AppBarLayout.OnOffsetChangedListener, OnClickListener {

	MyPageAdapter2 pagerAdapter;

	private AppBarLayout appBarLayout;

	private ViewPager viewPager;

	private TabLayout tabLayout;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main10);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("会消失的ToolBar");
		setSupportActionBar(toolbar);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		toolbar.setNavigationOnClickListener(this);

		pagerAdapter = new MyPageAdapter2(getSupportFragmentManager());
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

		PageFragment2 p = pagerAdapter.getCurrentFragment();

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
