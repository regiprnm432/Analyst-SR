package com.mba.analyst_sr;

public class Item {
	private Integer[] id;
	private int support;
	
	public Item() {
		this.support = 0;
	}

	public Integer[] getId() {
		return id;
	}
	public void setId(Integer[] id) {
		this.id = id;
	}
	public int getSupport() {
		return support;
	}
	
	//Procedure untuk increment support item
	public void addSupport() {
		this.support++;
	}
}
