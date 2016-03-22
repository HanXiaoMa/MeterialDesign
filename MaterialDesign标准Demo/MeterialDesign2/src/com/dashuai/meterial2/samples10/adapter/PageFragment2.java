package com.dashuai.meterial2.samples10.adapter;

import java.util.ArrayList;
import java.util.List;

import org.dashuai.d.http.DHttp;
import org.dashuai.d.http.core.HttpCallBack;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dashuai.material.widget.recyclermanger.MyStaggeredGridLayoutManager;
import com.dashuai.material.widget.recyclermanger.OnRecyclerViewScrollLocationListener;
import com.dashuai.meterial2.R;
import com.dashuai.meterial2.samples10.bean.ImageInfo;
import com.dashuai.meterial2.samples10.bean.MyImage;
import com.dashuai.meterial2.samples10.util.UrlUtil;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2015/9/12 0012.
 */
public class PageFragment2 extends Fragment implements OnRefreshListener,
		OnRecyclerViewScrollLocationListener {

	private String tag1;
	private String tag2;

	public static PageFragment2 newInstance(String tag1, String tag2) {
		Bundle args = new Bundle();
		args.putString("tag1", tag1);
		args.putString("tag2", tag2);
		PageFragment2 pageFragment = new PageFragment2();
		pageFragment.setArguments(args);
		return pageFragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tag1 = getArguments().getString("tag1");
		tag2 = getArguments().getString("tag2");
	}

	public SwipeRefreshLayout mSwipeRefresh;
	private RecyclerView mRecyclerView;
	private MyAdapter2 mAdapter;
	private MyStaggeredGridLayoutManager mLayoutManager;
	
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

		mLayoutManager = new MyStaggeredGridLayoutManager(2,
				StaggeredGridLayoutManager.VERTICAL);
		mRecyclerView.setLayoutManager(mLayoutManager);

		mAdapter = new MyAdapter2(getActivity());

		mRecyclerView.setAdapter(mAdapter);

		mLayoutManager.setOnRecyclerViewScrollListener(mRecyclerView, this);

		onRefresh();

		return view;
	}

	List<MyImage> list = new ArrayList<MyImage>();

	@Override
	public void onRefresh() {
		// 此处进行刷新
		pn = 0;
		list.clear();
		DHttp dHttp = new DHttp();

		dHttp.get(UrlUtil.appendUrl(tag1, tag2, pn * 20, 20), new MyCallback());

	}

	int pn = 0;

	private class MyCallback extends HttpCallBack {

		@Override
		public void onSuccess(String t) {
			mSwipeRefresh.setRefreshing(false);
			ImageInfo info = gson.fromJson(t, ImageInfo.class);

			List<MyImage> images = info.getData();

			if (images.size() >= 2) {
				images.remove(images.size() - 1);

				list.addAll(images);
				mAdapter.setDataList(list);
				mAdapter.notifyDataSetChanged();

			} else {
				Toast.makeText(getActivity(), "没有更多数据了!", Toast.LENGTH_SHORT)
						.show();
			}

		}

		@Override
		public void onFailure(int errorNo, String strMsg) {
			mSwipeRefresh.setRefreshing(false);
			System.out.println(strMsg);
			Toast.makeText(getActivity(), "请检查网络是否可用", Toast.LENGTH_SHORT)
					.show();
		}

		@Override
		public void onPreStart() {
			mSwipeRefresh.setRefreshing(true);
		}

	}

	private final Gson gson = new Gson();

	@Override
	public void onTopWhenScrollIdle(RecyclerView recyclerView) {

	}

	@Override
	public void onBottomWhenScrollIdle(RecyclerView recyclerView) {

		pn++;
		DHttp dHttp = new DHttp();

		dHttp.get(UrlUtil.appendUrl(tag1, tag2, pn * 20, 20), new MyCallback());
	}

}
