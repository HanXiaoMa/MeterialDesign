package com.dashuai.meterial2.sample8;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;

import com.dashuai.meterial2.R;
import com.dashuai.meterial2.samples789adapter.Hero;
import com.dashuai.meterial2.samples789adapter.MyAdapter;

public class CoordinatorLayoutAct extends AppCompatActivity implements
		OnRefreshListener,AppBarLayout.OnOffsetChangedListener, OnClickListener {

	SwipeRefreshLayout mSwipeRefresh;
	RecyclerView mRecyclerView;

	LinearLayoutManager mLayoutManager;

	int lastVisibleItem = 0;

	MyAdapter adapter;

	private AppBarLayout appBarLayout;
	
	
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main8);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		toolbar.setNavigationOnClickListener(this);
		
		CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
		mCollapsingToolbarLayout.setTitle("花千骨");
		// 通过CollapsingToolbarLayout修改字体颜色
		mCollapsingToolbarLayout.setExpandedTitleColor(Color.RED);// 设置还没收缩时状态下字体颜色
		mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);// 设置收缩后Toolbar上字体的颜色

		mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);
		mRecyclerView = (RecyclerView) findViewById(R.id.recylerlist);

		// 2.2 模拟器就必须用这个过时的方法。
		mSwipeRefresh.setColorScheme(R.color.holo_blue_light,
				R.color.holo_red_light, R.color.holo_orange_light,
				R.color.holo_green_light);
		mSwipeRefresh.setOnRefreshListener(this);

		// 这句话是为了，第一次进入页面的时候显示加载进度条
		mSwipeRefresh.setProgressViewOffset(false, 0, (int) TypedValue
				.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
						.getDisplayMetrics()));

		mRecyclerView.setHasFixedSize(true);
		mLayoutManager = new LinearLayoutManager(this);
		mRecyclerView.setLayoutManager(mLayoutManager);

		appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
		
		adapter = new MyAdapter(this);

		mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

			@Override
			public void onScrollStateChanged(RecyclerView recyclerView,
					int newState) {
				super.onScrollStateChanged(recyclerView, newState);
				if (newState == RecyclerView.SCROLL_STATE_IDLE
						&& lastVisibleItem + 1 == adapter.getItemCount()) {
					// 此处进行上拉加载更多
					mSwipeRefresh.setRefreshing(true);
					handler.sendEmptyMessageDelayed(1, 3000);
				}
			}

			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);
				lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
			}

		});

		mRecyclerView.setAdapter(adapter);
		mSwipeRefresh.setRefreshing(true);
		onRefresh();

	}

	@Override
	public void onRefresh() {
		// 此处进行刷新
		handler.sendEmptyMessageDelayed(0, 3000);
	}

	private Handler handler = new Handler() {

		public void handleMessage(android.os.Message msg) {

			if (msg.what == 0) {
				list.clear();
			}
			addData();
			adapter.setDataList(list);
			adapter.notifyDataSetChanged();
			mSwipeRefresh.setRefreshing(false);
		};

	};

	List<Hero> list = new ArrayList<Hero>();

	public void addData() {
		list.add(new Hero("扁鹊", R.mipmap.bianque));
		list.add(new Hero("李逵", R.mipmap.likui));
		list.add(new Hero("李师师", R.mipmap.lishishi));
		list.add(new Hero("李世民", R.mipmap.lisiming));
		list.add(new Hero("刘伯温", R.mipmap.liubowen));
		list.add(new Hero("武则天", R.mipmap.wuzetian));
		list.add(new Hero("小乔", R.mipmap.xiaoqiao));
		list.add(new Hero("岳飞", R.mipmap.yuefei));

	}

	// 解决SwipeRefreshLayout和CollapsingToolbarLayout冲突问题
	@Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
		mSwipeRefresh.setEnabled(i == 0);
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
