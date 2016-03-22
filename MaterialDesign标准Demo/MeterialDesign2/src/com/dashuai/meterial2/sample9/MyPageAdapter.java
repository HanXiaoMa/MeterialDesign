package com.dashuai.meterial2.sample9;

import com.dashuai.meterial2.base.ExFragmentPagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class MyPageAdapter extends ExFragmentPagerAdapter {

	private String tabTitles[] = new String[] { "新闻", "娱乐", "科技", "图片" };

	public MyPageAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		return PageFragment.newInstance(position + 1);
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
