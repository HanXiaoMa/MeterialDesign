package com.dashuai.meterial2.samples789adapter;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dashuai.meterial2.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
		implements OnClickListener {

	private Context context;

	public MyAdapter(Context context) {
		super();
		this.context = context;
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		public TextView mTextView;
		public ImageView mImageView;
		public LinearLayout layout;

		public ViewHolder(View v) {
			super(v);
			mTextView = (TextView) v.findViewById(R.id.recy_nameText);
			mImageView = (ImageView) v.findViewById(R.id.recy_imageview);
			layout = (LinearLayout) v.findViewById(R.id.item_root);
			layout.setOnClickListener(MyAdapter.this); // Item ¼àÌý
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
	public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
			int viewType) {

		View v = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.recy_item, parent, false);
		// set the view's size, margins, paddings and layout parameters
		return new MyAdapter.ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {

		Hero hero = dataList.get(position);

		holder.mTextView.setText(hero.getName());
		holder.mImageView.setImageResource(hero.getResId());
		holder.layout.setTag(position);
	}

	@Override
	public int getItemCount() {

		if (null != dataList) {
			return dataList.size();
		}

		return 0;
	}

	@Override
	public void onClick(View v) {

		LinearLayout layout = (LinearLayout) v;

		int p = (Integer) layout.getTag();

		Toast.makeText(context, p+"=="+dataList.get(p).getName() + " click",
				Toast.LENGTH_SHORT).show();

	}
}
