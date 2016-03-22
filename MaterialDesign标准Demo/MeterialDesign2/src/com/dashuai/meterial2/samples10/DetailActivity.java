package com.dashuai.meterial2.samples10;

import org.dashuai.d.image.DBitmap;

import uk.co.senab.photoview.PhotoView;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.dashuai.meterial2.R;

public class DetailActivity extends AppCompatActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ac_detail);

		PhotoView photoView = (PhotoView) findViewById(R.id.detail_imageview);

		Intent intent = getIntent();

		String url = intent.getStringExtra("imageUrl");

		DBitmap.create(this)
				.configLoadfailImage(R.drawable.big_loadpic_fail_listpage)
				.configLoadingImage(R.drawable.empty_photo)
				.display(photoView, url);

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(0, R.anim.ac_slide_left_out);
	}

}
