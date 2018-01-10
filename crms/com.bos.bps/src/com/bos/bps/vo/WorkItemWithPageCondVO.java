package com.bos.bps.vo;

import java.util.List;

public class WorkItemWithPageCondVO {
	
	private List list;
	private List maps;
	
	private int count;
	
	public int getCount() {
		return count;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List getMaps() {
		return maps;
	}

	public void setMaps(List map) {
		this.maps = map;
	}

}
