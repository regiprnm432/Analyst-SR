package com.mba.analyst_sr;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This class is implementation of non binary tree
 * 
 * @author Sendi Setiawan
 * @author Regi Purnama
 */
public class NBTree {
	Node root;
	
	//Constructor
	public NBTree() {
		Integer[] initial = new Integer[1];
		initial[0] = -1;
		this.root = new Node(initial, 0, null, null);
	}
	
	//Reset Tree
	public void resetTree() {
		Integer[] initial = new Integer[1];
		initial[0] = -1;
		this.root = new Node(initial, 0, null, null);
	}
	
	//validator
	public boolean isEmptyTree() {
		return root.fChild == null;
	}
	
	/**
	 * This method will compare two array, is one of the arrays 
	 * is a subset of the other array
	 * 
	 * @param subset 
	 * @param superset
	 * @return true if @param subset is subset of superset
	 */
	public boolean isSubset(Integer[] subset, Integer[] superset) {
		for(int i = 0; i < subset.length; i++) {
			if(subset[i] != superset[i]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * This method will compare two array, is both array 
	 * contains same value?
	 * 
	 * @param id1
	 * @param id2
	 * @return true if both array contains same values
	 */
	public boolean isEqual(Integer[] id1, Integer[] id2) {
		if(id1.length != id2.length) {
			return false;
		} else {
			for(int i = 0; i < id2.length; i++) {
				if(!id1[i].equals(id2[i])) {
					return false;
				}
			}
		}
		return true;
	}	
	
	/**
	 * This method will insert new node to tree
	 * new node will be child of a node that has
	 * the same id[0] to id[n-1]
	 * 
	 * @param id
	 */
	public void insert(Integer[] id) {
		Node currNode = this.root;
		int level = 0;
		while(true) {
			if(isSubset(currNode.id, id) || level == 0) {
				level += 1;
				if(currNode.fChild == null && level == id.length) {
					currNode.fChild = new Node(id, 0, null, null);
					break;
				} else if(id.length == level){
					Node siblings = currNode.fChild;
					while(siblings.nSibling != null) {
						siblings = siblings.nSibling;
					}
					siblings.nSibling = new Node(id, 0, null, null);
					break;
				}
				currNode = currNode.fChild;
			} else {
				currNode = currNode.nSibling;
			}
		}
	}
	
	/**
	 * This method will increment support of the 
	 * passing parameter node
	 * 
	 * @param id is id of node that will be incremented
	 */
	public void addSupport(Integer[] id) {
		Node currNode = this.root;
		int level = 0;
		while(level < id.length) {
			if(isSubset(currNode.id, id) || level == 0) {
				level += 1;
				currNode = currNode.fChild;
			} else {
				currNode = currNode.nSibling;
			}
		}
		while(true && level > 0) {
			if(isSubset(currNode.id, id)) {
				currNode.support++;
				break;
			} else {
				currNode = currNode.nSibling;
			}
		}
	}

	/**
	 * This method will create all rules 
	 * from all subset that has a support >= threshold
	 * 
	 * @param threshold
	 * @param numberOfTransaction
	 * @return all possible rules
	 */
	public ArrayList<Rules> getAllRules(int threshold, int numberOfTransaction) {
		ArrayList<Rules> rules = new ArrayList<>();
		LinkedList<Node> queue = new LinkedList<>();
		queue.add(root);
		Node temp;
		while(queue.size() != 0) {
			temp = queue.poll();
			if(temp.support >= threshold && temp.id.length > 1) {
				Integer[] antecedents = new Integer[1];
				antecedents[0] = temp.id[0];
				Integer[] consequents = new Integer[1];
				consequents[0] = temp.id[1];

				double antecedentsSupp = (double) getSupport(antecedents) / (double) numberOfTransaction;
				double consequentsSupp = (double) getSupport(consequents) / (double) numberOfTransaction;

				double rulesSupp = (double) temp.support / (double) numberOfTransaction;
				
				Rules newRules = new Rules(antecedents, consequents, antecedentsSupp, consequentsSupp, rulesSupp);
				rules.add(newRules);
				newRules = new Rules(consequents, antecedents, consequentsSupp, antecedentsSupp, rulesSupp);
				rules.add(newRules);

			}
			temp = temp.fChild;
			while(temp != null) {
				queue.add(temp);
				temp = temp.nSibling;
			}
		}
		return rules;
	}
	
	/**
	 * Method to get support value of a node
	 * 
	 * @param subsetId
	 * @return value of support
	 */
	public int getSupport(Integer[] subsetId) {

		Node currNode = this.root;
		int level = 0;
		while(true) {
			if(isSubset(currNode.id, subsetId) || level == 0) {
				level += 1;
				if(subsetId.length == level){
					Node siblings = currNode.fChild;
					while(!isEqual(subsetId, siblings.id)) {
						siblings = siblings.nSibling;
					}
					return siblings.support;
				}
				currNode = currNode.fChild;
			} else {
				currNode = currNode.nSibling;
			}
		}
	}
}
