package com.dashuai.meterial2.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.dashuai.meterial2.R;
import com.dashuai.meterial2.main.MyAAdapter2.OnItemClickListerner;
import com.dashuai.meterial2.sample2.TextInputActivity;
import com.dashuai.meterial2.sample3.DrawerLayoutActivity;
import com.dashuai.meterial2.sample4.TabLayoutAct;
import com.dashuai.meterial2.sample5.TabLayoutAct2;
import com.dashuai.meterial2.sample6.NavigationViewAct;
import com.dashuai.meterial2.sample7.RecylerviewAct;
import com.dashuai.meterial2.sample8.CoordinatorLayoutAct;
import com.dashuai.meterial2.sample9.CoordTabAct;
import com.dashuai.meterial2.samples10.CoordTabAct2;
import com.dashuai.meterial2.samples12.GridRecyclerAct;
import com.dashuai.meterial2.samples13.NestedScrollViewAct;

public class MainActivity extends AppCompatActivity implements OnClickListener,
		OnMenuItemClickListener, OnItemClickListerner {

	final String[] items = new String[] { "ToolBar控件\nSnackBar控件",
			"TextInputLayout控件", "DrawerLayout控件", "TabLayout和Tabs",
			"TabLayout和ViewPager", "NavigationView控件\nFloatingActionButton控件",
			"SwipeRefreshLayout控件\nRecyclerView控件\nCardView控件",
			"CoordinatorLayout控件\nAppBarLayout控件\nCollapsingToolbarLayout控件",
			"CoordinatorLayout控件\nTabLayout控件",
			"CoordinatorLayout控件\nToolBar会消失\n瀑布流效果",
			"GridRecyclerview\n斜对角的动画效果", 
			"NestedScrollView控件" };

	final Class[] clazz = { TextInputActivity.class,
			DrawerLayoutActivity.class, TabLayoutAct.class,
			TabLayoutAct2.class, NavigationViewAct.class, RecylerviewAct.class,
			CoordinatorLayoutAct.class, CoordTabAct.class, CoordTabAct2.class,

			GridRecyclerAct.class, NestedScrollViewAct.class };

	RecyclerView mRecyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.carbon);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

		toolbar.setTitle("MeterialDesign");// 标题的文字需在setSupportActionBar之前，不然会无效

		setSupportActionBar(toolbar);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		toolbar.setNavigationOnClickListener(this);

		toolbar.setOnMenuItemClickListener(this);

		mRecyclerView = (RecyclerView) findViewById(R.id.recylerlist);

		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

		MyAAdapter2 aAdapter = new MyAAdapter2();
		aAdapter.setDataList(items);

		mRecyclerView.setAdapter(aAdapter);

		aAdapter.setOnItemClickListerner(this);

	}

	@Override
	public void onClick(View arg0) {
		onBackPressed();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);

		SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu
				.findItem(R.id.menu_search));
		searchView.setQueryHint("请输入搜索关键字");
		// searchView.setSuggestionsAdapter(); // 提示匹配

		searchView.setOnQueryTextListener(mOnQueryTextListener);

		return true;
	}

	private final SearchView.OnQueryTextListener mOnQueryTextListener = new SearchView.OnQueryTextListener() {
		@Override
		public boolean onQueryTextChange(String newText) {
			// newText = TextUtils.isEmpty(newText) ? "" : "Query so far: " +
			// newText;

			return true;
		}

		@Override
		public boolean onQueryTextSubmit(String query) {
			Toast.makeText(MainActivity.this,
					"Searching for: " + query + "...", Toast.LENGTH_SHORT)
					.show();
			return true;
		}
	};

	@Override
	public boolean onMenuItemClick(MenuItem item) {

		if (item.getItemId() == R.id.menu_search) {
			Toast.makeText(this, "点击了 搜索菜单", 0).show();
		} else if (item.getItemId() == R.id.action_settings) {
			Snackbar.make(mRecyclerView, "点击了 删除菜单", 0).show();
		}

		return true;
	}

	private void skipActivity(Class clazz) {

		Intent intent = new Intent(this, clazz);

		startActivity(intent);

	}

	private void showSnackBar() {

		final Snackbar snackbar = Snackbar.make(mRecyclerView, "结束当前Activity",
				Snackbar.LENGTH_SHORT);

		snackbar.setAction("确定", new View.OnClickListener() {
			public void onClick(View v) {
				snackbar.dismiss();
				onBackPressed();
			}
		});

		snackbar.show();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		System.exit(0);
		android.os.Process.killProcess(android.os.Process.myPid());
	}

	@Override
	public void onItemClick(int position) {
		if (position == 0) {
			showSnackBar();
		} else {
			skipActivity(clazz[position - 1]);
		}
	}

}
