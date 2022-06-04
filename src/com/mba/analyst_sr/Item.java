package com.mba.analyst_sr;

/**
 * This class will represent data structure for an item
 * 
 * @author Sendi Setiawan
 * @author Regi Purnama
 *
 */
public class Item {
	
	//Constructor
	public Item() {
		this.support = 0;
	}

	//Getter and setter
	public Integer[] getId() {
		return id;
	}
	public void setId(Integer[] id) {
		this.id = id;
	}
	public int getSupport() {
		return support;
	}
	
	/**
	 * This method will increment value of support by 1
	 */
	public void addSupport() {
		this.support++;
	}

	private Integer[] id;
	private int support;
}
