package com.dashuai.meterial2.samples10.adapter;

import com.dashuai.meterial2.base.ExFragmentPagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class MyPageAdapter2 extends ExFragmentPagerAdapter {

	private String tabTitles[] = new String[] { "��Ů", "˧��", "����" };

	public MyPageAdapter2(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		
		if(0==position){
			return PageFragment2.newInstance("��Ů", "�崿");
		}else if(1==position){
			return PageFragment2.newInstance("��ֽ", "����");
		}else{
			return PageFragment2.newInstance("��ֽ", "Ӱ��");
		}
		
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
