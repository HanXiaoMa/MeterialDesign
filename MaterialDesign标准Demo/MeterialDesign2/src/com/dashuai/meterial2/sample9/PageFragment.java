package com.dashuai.meterial2.sample9;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dashuai.material.widget.recyclermanger.MyLinearLayoutManager;
import com.dashuai.material.widget.recyclermanger.OnRecyclerViewScrollLocationListener;
import com.dashuai.meterial2.R;
import com.dashuai.meterial2.samples789adapter.Hero;
import com.dashuai.meterial2.samples789adapter.MyAdapter;

/**
 * Created by Administrator on 2015/9/12 0012.
 */
public class PageFragment extends Fragment implements OnRefreshListener,
		OnRecyclerViewScrollLocationListener {

	public static final String ARG_PAGE = "ARG_PAGE";
	private int mPage;

	public static PageFragment newInstance(int page) {
		Bundle args = new Bundle();
		args.putInt(ARG_PAGE, page);
		PageFragment pageFragment = new PageFragment();
		pageFragment.setArguments(args);
		return pageFragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPage = getArguments().getInt(ARG_PAGE);
	}

	public SwipeRefreshLayout mSwipeRefresh;
	private RecyclerView mRecyclerView;
	private MyAdapter mAdapter;
	private MyLinearLayoutManager mLayoutManager;

	// private int lastVisibleItem = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_page, container, false);

		mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
		mSwipeRefresh = (SwipeRefreshLayout) view
				.findViewById(R.id.swipe_refresh_widget);

		mSwipeRefresh.setColorScheme(R.color.holo_blue_light,
				R.color.holo_red_light, R.color.holo_orange_light,
				R.color.holo_green_light);
		mSwipeRefresh.setOnRefreshListener(this);

		// 这句话是为了，第一次进入页面的时候显示加载进度条
		mSwipeRefresh.setProgressViewOffset(false, 0, (int) TypedValue
				.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getActivity()
						.getResources().getDisplayMetrics()));

		mRecyclerView.setHasFixedSize(true);

		mLayoutManager = new MyLinearLayoutManager(getActivity());
		mRecyclerView.setLayoutManager(mLayoutManager);

		mAdapter = new MyAdapter(getActivity());

		mRecyclerView.setAdapter(mAdapter);

		// com.dashuai.meterial2.sample7.CoordinatorLayoutAct类中的代码优化
		mLayoutManager.setOnRecyclerViewScrollLocationListener(mRecyclerView,
				this);

		mSwipeRefresh.setRefreshing(true);
		onRefresh();

		return view;
	}

	List<Hero> list = new ArrayList<Hero>();

	public void addData(int i) {
		list.add(new Hero(i + " 扁鹊", R.mipmap.bianque));
		list.add(new Hero(i + " 李逵", R.mipmap.likui));
		list.add(new Hero(i + " 李师师", R.mipmap.lishishi));
		list.add(new Hero(i + " 李世民", R.mipmap.lisiming));
		list.add(new Hero(i + " 刘伯温", R.mipmap.liubowen));
		list.add(new Hero(i + " 武则天", R.mipmap.wuzetian));
		list.add(new Hero(i + " 小乔", R.mipmap.xiaoqiao));
		list.add(new Hero(i + " 岳飞", R.mipmap.yuefei));

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
			addData(mPage);
			mAdapter.setDataList(list);
			mAdapter.notifyDataSetChanged();
			mSwipeRefresh.setRefreshing(false);
		};

	};

	@Override
	public void onTopWhenScrollIdle(RecyclerView recyclerView) {

	}

	@Override
	public void onBottomWhenScrollIdle(RecyclerView recyclerView) {
		mSwipeRefresh.setRefreshing(true);
		handler.sendEmptyMessageDelayed(1, 3000);
	}

}
