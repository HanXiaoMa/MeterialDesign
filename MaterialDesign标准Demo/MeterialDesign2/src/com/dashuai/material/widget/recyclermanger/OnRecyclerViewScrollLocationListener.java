package com.dashuai.material.widget.recyclermanger;

import android.support.v7.widget.RecyclerView;

public interface OnRecyclerViewScrollLocationListener {
	void onTopWhenScrollIdle(RecyclerView recyclerView);

	void onBottomWhenScrollIdle(RecyclerView recyclerView);
}
