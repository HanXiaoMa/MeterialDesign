package com.dashuai.meterial2.sample5;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dashuai.meterial2.R;
import com.dashuai.meterial2.base.BaseActivity;

public class TabLayoutAct2 extends BaseActivity {

	@Override
	public void youShouldSetContentView() {
		setContentView(R.layout.activity_main5);
		setToolBarTitle("TabLayout-2");
	}

	TabLayout tabLayout;
	ViewPager pager;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		tabLayout = (TabLayout) findViewById(R.id.tablayout);
		tabLayout.setTabTextColors(Color.WHITE, Color.RED);// 设置文本在选中和为选中时候的颜色

		pager = (ViewPager) findViewById(R.id.viewpager);

		MyAdapter adapter = new MyAdapter(getSupportFragmentManager());

		pager.setAdapter(adapter);

		tabLayout.setupWithViewPager(pager);
		tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
		
//		tabLayout.setTabMode(TabLayout.MODE_FIXED);
		tabLayout.setTabsFromPagerAdapter(adapter);

	}

	public static class MyFragment extends Fragment {

		public static final String ARG_PAGE = "ARG_PAGE";
		private int mPage;

		public static MyFragment newInstance(int page) {
			Bundle args = new Bundle();
			args.putInt(ARG_PAGE, page);
			MyFragment pageFragment = new MyFragment();
			pageFragment.setArguments(args);
			return pageFragment;
		}

		@Override
		public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

			if (mPage % 2 == 0) {
				inflater.inflate(R.menu.menu_main, menu);
				SearchView searchView = (SearchView) MenuItemCompat
						.getActionView(menu.findItem(R.id.menu_search));
				searchView.setQueryHint("请输入搜索关键字");
			} else {
				inflater.inflate(R.menu.menu_main2, menu);
			}

			super.onCreateOptionsMenu(menu, inflater);
		}

		@Override
		public boolean onOptionsItemSelected(MenuItem item) {

			if (item.getItemId() == R.id.action_settings22) {
				Toast.makeText(getActivity(), "点击了 删除 菜单", Toast.LENGTH_SHORT)
						.show();
				return true;
			}

			return super.onOptionsItemSelected(item);
		}

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setHasOptionsMenu(true);
			mPage = getArguments().getInt(ARG_PAGE);
		}

		@Override
		@Nullable
		public View onCreateView(LayoutInflater inflater,
				@Nullable ViewGroup container,
				@Nullable Bundle savedInstanceState) {

			TextView textView = new TextView(getActivity());

			ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.MATCH_PARENT);
			textView.setGravity(Gravity.CENTER);
			textView.setLayoutParams(lp);
			textView.setText("Fragment # " + mPage);
			textView.setTextSize(30);
			textView.setTextColor(Color.BLACK);

			return textView;
		}

	}

	public static class MyAdapter extends FragmentPagerAdapter {

		private String tabTitles[] = new String[] { "  新闻     ", "  娱乐   ", "  科技    ", "  图片    ","  体育     ","  军事     ","  农业     ","  社会     ","  美女    " };

		public MyAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return MyFragment.newInstance(position + 1);
		}

		@Override
		public int getCount() {
			return tabTitles.length;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return tabTitles[position];
		}

	}

}
