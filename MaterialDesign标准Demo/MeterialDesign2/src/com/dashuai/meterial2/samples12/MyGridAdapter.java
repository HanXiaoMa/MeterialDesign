package com.dashuai.meterial2.samples12;

import java.util.List;

import com.dashuai.meterial2.R;
import com.dashuai.meterial2.R.id;
import com.dashuai.meterial2.R.layout;
import com.dashuai.meterial2.samples789adapter.Hero;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MyGridAdapter extends
		RecyclerView.Adapter<MyGridAdapter.ViewHolder> {

	public class ViewHolder extends RecyclerView.ViewHolder {
		public TextView mTextView;
		public ImageView mImageView;

		public ViewHolder(View v) {
			super(v);
			mTextView = (TextView) v.findViewById(R.id.recy_nameText);
			mImageView = (ImageView) v.findViewById(R.id.recy_imageview);
		}

	}

	private List<Hero> dataList;

	public List<Hero> getDataList() {
		return dataList;
	}

	public void setDataList(List<Hero> dataList) {
		this.dataList = dataList;
	}

	@Override
	public MyGridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
			int viewType) {

		View v = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.recy_gird_item, parent, false);
		// set the view's size, margins, paddings and layout parameters
		return new MyGridAdapter.ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {

		Hero hero = dataList.get(position);

		holder.mTextView.setText(hero.getName());
		holder.mImageView.setImageResource(hero.getResId());
	}

	@Override
	public int getItemCount() {

		if (null != dataList) {
			return dataList.size();
		}

		return 0;
	}

}
