package com.dashuai.meterial2.sample6;

import com.dashuai.meterial2.R;
import com.dashuai.meterial2.R.id;
import com.dashuai.meterial2.R.layout;
import com.dashuai.meterial2.R.string;
import com.dashuai.meterial2.base.BaseActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class NavigationViewAct extends BaseActivity implements
		OnNavigationItemSelectedListener {

	@Override
	public void youShouldSetContentView() {
		setContentView(R.layout.activity_main6);
		setToolBarTitle("NavigationView");
	}

	DrawerLayout mDrawerLayout;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
		ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,
				mDrawerLayout, getToolbar(), R.string.drawer_open,
				R.string.drawer_close);
		mDrawerToggle.syncState();
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		NavigationView view = (NavigationView) findViewById(R.id.navigation_view);
		view.setNavigationItemSelectedListener(this);

		FloatingActionButton button = (FloatingActionButton) findViewById(R.id.floating_btn);

		button.setOnClickListener(this);

	}

	@Override
	public boolean onNavigationItemSelected(MenuItem menuItem) {
		menuItem.setChecked(true);
		mDrawerLayout.closeDrawers();
		Toast.makeText(this, menuItem.getTitle() + " pressed",
				Toast.LENGTH_SHORT).show();
		return true;
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.floating_btn) {

			Toast.makeText(this, "µã»÷ÁËFloatBar", Toast.LENGTH_SHORT).show();

		} else {
			super.onClick(v);
		}
	}

}
