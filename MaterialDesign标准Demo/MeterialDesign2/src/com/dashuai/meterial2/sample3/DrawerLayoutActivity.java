package com.dashuai.meterial2.sample3;

import com.dashuai.meterial2.R;
import com.dashuai.meterial2.R.id;
import com.dashuai.meterial2.R.layout;
import com.dashuai.meterial2.R.string;
import com.dashuai.meterial2.base.BaseActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DrawerLayoutActivity extends BaseActivity {

	@Override
	public void youShouldSetContentView() {
		setContentView(R.layout.activity_main3);
		setToolBarTitle("DrawerLayout"); // ����������
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
		ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,
				mDrawerLayout, getToolbar(), R.string.drawer_open,
				R.string.drawer_close);
		mDrawerToggle.syncState();
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		ListView listView = (ListView) findViewById(R.id.mymylistview2);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.item, R.id.item_text, new String[] { "�˵�1", "�˵�2",
						"�˵�1", "�˵�2", "�˵�1", "�˵�2", "�˵�1", "�˵�2", });

		listView.setAdapter(adapter);

	}

}
