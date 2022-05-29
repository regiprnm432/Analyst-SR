package com.mba.analyst_sr;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TransactionData {
	private CSV csv = new CSV();
	private String[] dataCells;
	private File file;
	private static Map<String, Integer> idReference = new HashMap<>();
	static Map<String, Integer> itemCount = new HashMap<>();
	private ArrayList<Integer> itemSet = new ArrayList<>();
	private int numberOfTransactions;
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public ArrayList<Integer> getItemSet() {
		return itemSet;
	}
	
	public int getNumberOfTransaction() {
		return numberOfTransactions;
	}
	
	public void rewind() {
		readFile();
	}

	public void readFile() {
		csv.readCSV(file);
	}
	
	public void closeSteram() {
		csv.closeStream();
	}

	public void makeId() {
		ArrayList<String> allItems = new ArrayList<>();
		
		for(String item : itemCount.keySet()) {
			allItems.add(item);
		}
		Collections.sort(allItems);
		for(Integer id = 0; id < allItems.size(); id++) {
			idReference.put(allItems.get(id), id + 1);
		}
	}
	
	public ArrayList<Integer> convertItemsToId(String[] items) {
		ArrayList<Integer> result = new ArrayList<>();
		for(String item : items) {
			if(items[0] != null) {
				result.add(idReference.get(item));
			}
		}
		return result;
	}
	
	public static String convertIdToItem(Integer id) {
		String result = null;
		for(java.util.Map.Entry<String, Integer> entry : idReference.entrySet()) {
			if(entry.getValue().equals(id)) {
				result = entry.getKey();
			}
		}
		return result;
	}
	
	public ArrayList<Integer> getNextTransaction() {
		dataCells = csv.getNextRow();
		if(dataCells.length == 0) {
			return null;
		}
		ArrayList<String> tempDataCells = new ArrayList<>();
		for(String cell : dataCells) {
			tempDataCells.add(cell);
		}
		Collections.sort(tempDataCells);
		for(int i = 0; i < tempDataCells.size(); i++) {
			dataCells[i] = tempDataCells.get(i);
		}
		return convertItemsToId(dataCells);
	}
	
	public void countAllItem() {
		do {
			dataCells = csv.getNextRow();
			for(int i = 0; i < dataCells.length; i++) {
				if(!itemCount.containsKey(dataCells[i])) {
					itemCount.put(dataCells[i], 0);
				}
				itemCount.put(dataCells[i], itemCount.get(dataCells[i]) + 1);
			}
			numberOfTransactions++;
		} while(dataCells.length != 0);
		numberOfTransactions--;
	}
	
	public void createItemSet(int threshold) {
		for(String item : itemCount.keySet()) {
			int support = itemCount.get(item);
			if(support >= threshold) {
				itemSet.add(idReference.get(item));
			}
		}
	}
	
}