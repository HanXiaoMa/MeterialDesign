package com.dashuai.material.widget.recyclermanger;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MyLinearLayoutManager extends LinearLayoutManager implements
		RecyclerViewScrollManager.OnScrollManagerLocation {

	private RecyclerViewScrollManager recyclerViewScrollManager;

	public void setOnRecyclerViewScrollLocationListener(
			RecyclerView recyclerView,
			OnRecyclerViewScrollLocationListener onRecyclerViewScrollLocationListener) {
		ensureRecyclerViewScrollManager();
		recyclerViewScrollManager
				.setOnRecyclerViewScrollLocationListener(onRecyclerViewScrollLocationListener);
		recyclerViewScrollManager.setOnScrollManagerLocation(this);
		recyclerViewScrollManager.registerScrollListener(recyclerView);
	}

	public MyLinearLayoutManager(Context context) {
		super(context);
	}

	public MyLinearLayoutManager(Context context, int orientation,
			boolean reverseLayout) {
		super(context, orientation, reverseLayout);
	}

	public boolean isScrolling() {
		if (null != recyclerViewScrollManager) {
			return recyclerViewScrollManager.isScrolling();
		}
		return false;
	}

	public RecyclerViewScrollManager getRecyclerViewScrollManager() {
		ensureRecyclerViewScrollManager();
		return recyclerViewScrollManager;
	}

	private void ensureRecyclerViewScrollManager() {
		if (null == recyclerViewScrollManager) {
			recyclerViewScrollManager = new RecyclerViewScrollManager();
		}
	}

	@Override
	public boolean isTop(RecyclerView recyclerView) {
		return 0 == findFirstVisibleItemPosition();
	}

	@Override
	public boolean isBottom(RecyclerView recyclerView) {
		int lastVisiblePosition = findLastCompletelyVisibleItemPosition();
		int lastPosition = recyclerView.getAdapter().getItemCount() - 1;
		return lastVisiblePosition == lastPosition;
	}
}
