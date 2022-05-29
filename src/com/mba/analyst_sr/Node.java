package com.mba.analyst_sr;

/**
 * This class represent node data structure for non
 * binary tree. Reference to parent is not included
 * because we want to save more memory and we don't 
 * really need it.
 * 
 * @author sendis
 *
 */
public class Node {

	public Node(Integer[] id, int support, Node fChild, Node nSibling) {
		this.id = id;
		this.support = support;
		this.fChild = fChild;
		this.nSibling = nSibling;
	}

	Integer[] id;
	int support;
	Node fChild;
	Node nSibling;
}