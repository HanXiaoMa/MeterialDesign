package com.dashuai.meterial2.sample4;

import com.dashuai.meterial2.R;
import com.dashuai.meterial2.R.id;
import com.dashuai.meterial2.R.layout;
import com.dashuai.meterial2.base.BaseActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TabLayoutAct extends BaseActivity implements OnTabSelectedListener {

	@Override
	public void youShouldSetContentView() {
		setContentView(R.layout.activity_main4);
		setToolBarTitle("TabLayout-1");
	}

	TabLayout tabLayout;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		tabLayout = (TabLayout) findViewById(R.id.tablayout);
		tabLayout.setTabTextColors(Color.WHITE, Color.RED);// 设置文本在选中和为选中时候的颜色
		tabLayout.addTab(tabLayout.newTab().setText("第一个"), true);// 添加 Tab,默认选中
		tabLayout.addTab(tabLayout.newTab().setText("第二个"), false);// 添加
																	// Tab,默认不选中
		tabLayout.addTab(tabLayout.newTab().setText("第三个"), false);// 添加
		tabLayout.addTab(tabLayout.newTab().setText("第四个"), false);// 添加

		tabLayout.setOnTabSelectedListener(this);
		MyFragment fragment = MyFragment.newInstance(0);

		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragment_layout, fragment).commit();

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
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
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

			return textView;
		}

	}

	@Override
	public void onTabReselected(Tab arg0) {

	}

	@Override
	public void onTabSelected(Tab tab) {

		MyFragment fragment = MyFragment.newInstance(tab.getPosition());

		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragment_layout, fragment).commit();

	}

	@Override
	public void onTabUnselected(Tab arg0) {

	}

}
