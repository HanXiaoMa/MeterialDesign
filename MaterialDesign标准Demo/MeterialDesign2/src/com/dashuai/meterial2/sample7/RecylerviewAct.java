package com.dashuai.meterial2.sample7;

import java.util.ArrayList;
import java.util.List;

import com.dashuai.meterial2.R;
import com.dashuai.meterial2.R.color;
import com.dashuai.meterial2.R.id;
import com.dashuai.meterial2.R.layout;
import com.dashuai.meterial2.R.mipmap;
import com.dashuai.meterial2.base.BaseActivity;
import com.dashuai.meterial2.samples789adapter.Hero;
import com.dashuai.meterial2.samples789adapter.MyAdapter;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

/**��Recylerviewȡ��ListView
 * 
 * ʵ�ֹ��ܣ� �������أ�����ˢ�£� Item������ EmptyView
 * 
 * @author Administrator
 *
 */
public class RecylerviewAct extends BaseActivity implements OnRefreshListener {

	@Override
	public void youShouldSetContentView() {
		setContentView(R.layout.activity_main7);
		setToolBarTitle("Recylerview");
	}

	SwipeRefreshLayout mSwipeRefresh;
	RecyclerView mRecyclerView;

	LinearLayoutManager mLayoutManager;

	int lastVisibleItem = 0;

	MyAdapter adapter;

	View emptyView;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);
		mRecyclerView = (RecyclerView) findViewById(R.id.recylerlist);

		// 2.2 ģ�����ͱ����������ʱ�ķ�����
		mSwipeRefresh.setColorScheme(R.color.holo_blue_light,
				R.color.holo_red_light, R.color.holo_orange_light,
				R.color.holo_green_light);
		mSwipeRefresh.setOnRefreshListener(this);

		// ��仰��Ϊ�ˣ���һ�ν���ҳ���ʱ����ʾ���ؽ�����
		mSwipeRefresh.setProgressViewOffset(false, 0, (int) TypedValue
				.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
						.getDisplayMetrics()));

		mRecyclerView.setHasFixedSize(true);
		mLayoutManager = new LinearLayoutManager(this);
		mRecyclerView.setLayoutManager(mLayoutManager);
		
		adapter = new MyAdapter(this);

		mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

			@Override
			public void onScrollStateChanged(RecyclerView recyclerView,
					int newState) {
				super.onScrollStateChanged(recyclerView, newState);
				if (newState == RecyclerView.SCROLL_STATE_IDLE
						&& lastVisibleItem + 1 == adapter.getItemCount()) {
					// �˴������������ظ���
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

		FloatingActionButton button = (FloatingActionButton) findViewById(R.id.floating_btn);

		button.setOnClickListener(this);
		emptyView = findViewById(R.id.empty_view);
	}

	@Override
	public void onRefresh() {
		// �˴�����ˢ��
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

		if (v.getId() == R.id.floating_btn) {

			Toast.makeText(this, "Ϊ�˲���EmptyView", Toast.LENGTH_SHORT).show();

			mSwipeRefresh.setVisibility(View.GONE);
			emptyView.setVisibility(View.VISIBLE);

		} else {
			super.onClick(v);
		}
	}

}
