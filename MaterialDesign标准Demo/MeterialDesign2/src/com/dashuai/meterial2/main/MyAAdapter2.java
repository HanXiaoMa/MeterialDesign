package com.dashuai.meterial2.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dashuai.meterial2.R;

public class MyAAdapter2 extends RecyclerView.Adapter<MyAAdapter2.ViewHolder>
		implements OnClickListener {

	public class ViewHolder extends RecyclerView.ViewHolder {

		public TextView mTextView;

		public ViewHolder(View v) {
			super(v);
			mTextView = (TextView) v.findViewById(R.id.item_text);
			mTextView.setOnClickListener(MyAAdapter2.this);
		}

	}

	private String[] dataList;

	public String[] getDataList() {
		return dataList;
	}

	public void setDataList(String[] dataList) {
		this.dataList = dataList;
	}

	@Override
	public int getItemCount() {
		if (null != dataList) {
			return dataList.length;
		}
		return 0;
	}

	@Override
	public void onBindViewHolder(ViewHolder arg0, int arg1) {
		arg0.mTextView.setText(dataList[arg1]);
		arg0.mTextView.setTag(arg1);
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
		View v = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.carbon_item, parent, false);
		// set the view's size, margins, paddings and layout parameters
		return new MyAAdapter2.ViewHolder(v);
	}

	@Override
	public void onClick(View v) {
		if (null != onItemClickListerner) {
			int position = (Integer) ((TextView) v).getTag();
			onItemClickListerner.onItemClick(position);
		}
	}

	private OnItemClickListerner onItemClickListerner;

	public void setOnItemClickListerner(
			OnItemClickListerner onItemClickListerner) {
		this.onItemClickListerner = onItemClickListerner;
	}

	public interface OnItemClickListerner {

		public void onItemClick(int position);

	}

}
