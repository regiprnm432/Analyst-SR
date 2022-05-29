package com.mba.analyst_sr;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class MarketBasketAnalysis {
	private File file;
	private NBTree tree;
	private TransactionData transactionData;
	private int threshold;
	
	public MarketBasketAnalysis() {
		this.tree = new NBTree();
		this.transactionData = new TransactionData();
	}
	
	public void processFile() {
		transactionData.setFile(file);
		transactionData.readFile();
		transactionData.countAllItem();
		transactionData.makeId();
		transactionData.createItemSet(threshold);
		transactionData.rewind();
	}
	
	public void makeInitialTree() {
		ArrayList<Integer> itemSet = transactionData.getItemSet();
		Collections.sort(itemSet);
		ArrayList<ArrayList<Integer>> subset = Subset.getSubset(itemSet, 3);
		for(ArrayList<Integer> subs : subset) {
				Integer[] sub = new Integer[subs.size()];
				sub = subs.toArray(sub);
				tree.insert(sub);
		}
	}
	
	public void countAllItemsSupport() {
		ArrayList<ArrayList<Integer>> subset = new ArrayList<>();
		ArrayList<Integer> transaction = transactionData.getNextTransaction();
		ArrayList<Integer> itemSet = transactionData.getItemSet();
		
		while(transaction != null) {
			subset = Subset.getSubset(transaction, 3);
			for(ArrayList<Integer> subs : subset) {
				
				//check if subset has all item that frequent
				if(itemSet.containsAll(subs)) {
					Integer[] sub = new Integer[subs.size()];
					sub = subs.toArray(sub);
					tree.addSupport(sub);
				}
			}
			transaction = transactionData.getNextTransaction();
			subset.clear();
		}	
	}
	
	public ArrayList<Rules> getAnalysisResult() {
		ArrayList<Rules> result = tree.getAllRules(threshold, transactionData.getNumberOfTransaction());
		for(Rules rule : result) {
			rule.calculateConfidence();
			rule.calculateLift();
		}
		return result;
	}

	public int getThreshold() {
		return threshold;
	}
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
}
