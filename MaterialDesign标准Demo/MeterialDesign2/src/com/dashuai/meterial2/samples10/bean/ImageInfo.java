package com.dashuai.meterial2.samples10.bean;

import java.util.List;

public class ImageInfo {

	private String tag1;

	private String tag2;

	private int totalNum;

	private int start_index;

	private int return_number;

	private List<MyImage> data;

	public String getTag1() {
		return tag1;
	}

	public void setTag1(String tag1) {
		this.tag1 = tag1;
	}

	public String getTag2() {
		return tag2;
	}

	public void setTag2(String tag2) {
		this.tag2 = tag2;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public int getStart_index() {
		return start_index;
	}

	public void setStart_index(int start_index) {
		this.start_index = start_index;
	}

	public int getReturn_number() {
		return return_number;
	}

	public void setReturn_number(int return_number) {
		this.return_number = return_number;
	}

	public List<MyImage> getData() {
		return data;
	}

	public void setData(List<MyImage> data) {
		this.data = data;
	}

}
