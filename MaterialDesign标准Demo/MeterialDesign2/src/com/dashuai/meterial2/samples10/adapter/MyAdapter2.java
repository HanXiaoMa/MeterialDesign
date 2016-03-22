package com.dashuai.meterial2.samples10.adapter;

import java.util.List;

import org.dashuai.d.image.DBitmap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dashuai.meterial2.R;
import com.dashuai.meterial2.samples10.DetailActivity;
import com.dashuai.meterial2.samples10.bean.MyImage;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder>
		implements OnClickListener {

	private DBitmap dBitmap;
	private Context context;

	public MyAdapter2(Context context) {
		super();
		this.context = context;
		dBitmap = DBitmap.create(context)
				.configLoadfailImage(R.drawable.big_loadpic_fail_listpage)
				.configLoadingImage(R.drawable.empty_photo);
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		public ImageView mImageView;

		public ViewHolder(View v) {
			super(v);
			mImageView = (ImageView) v.findViewById(R.id.recy_imageview);
			mImageView.setOnClickListener(MyAdapter2.this);
		}

	}

	private List<MyImage> dataList;

	public List<MyImage> getDataList() {
		return dataList;
	}

	public void setDataList(List<MyImage> dataList) {
		this.dataList = dataList;
	}

	@Override
	public MyAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent,
			int viewType) {

		View v = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.baidu_item, parent, false);
		// set the view's size, margins, paddings and layout parameters
		return new MyAdapter2.ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {

		MyImage image = dataList.get(position);

		holder.mImageView.setTag(image.getThumbnail_url());
		dBitmap.display(holder.mImageView, image.getThumbnail_url());

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

		Intent intent = new Intent(context, DetailActivity.class);

		intent.putExtra("imageUrl", (String) ((ImageView) v).getTag());

		Activity ac = (Activity) context;

		ac.startActivity(intent);
		ac.overridePendingTransition(R.anim.ac_slide_left_in,
				android.R.anim.fade_out);

	}

}
