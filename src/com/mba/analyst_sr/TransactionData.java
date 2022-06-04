package com.mba.analyst_sr;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to control the file
 * and cleaning the data from file
 * 
 * @author Sendi Setiawan
 *
 */
public class TransactionData {

	//Getter and Setter
	public void setFile(File file) {
		this.file = file;
	}
	
	public ArrayList<Integer> getItemSet() {
		return itemSet;
	}
	
	public int getNumberOfTransaction() {
		return numberOfTransactions;
	}
	
	//Make pointer in file go back to initial position
	public void rewind() {
		readFile();
	}

	//read file to scanner
	public void readFile() {
		csv.readCSV(file);
	}
	
	//close file stream
	public void closeSteram() {
		csv.closeStream();
	}

	/**
	 * This method will make an id for all item
	 * id will be an Integer type
	 */
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
	
	/**
	 * This method will convert all items name (string)
	 * in a single transaction to its correspondent id
	 * 
	 * @param items is array that contains name of items
	 * @return array of id (integer)
	 */
	public ArrayList<Integer> convertItemsToId(String[] items) {
		ArrayList<Integer> result = new ArrayList<>();
		for(String item : items) {
			if(items[0] != null) {
				result.add(idReference.get(item));
			}
		}
		return result;
	}
	
	/**
	 * This method will convert id (integer) to
	 * its correspondent name (string)
	 * 
	 * @param id is variable that contains id of item
	 * @return item name (string)
	 */
	public static String convertIdToItem(Integer id) {
		String result = null;
		for(java.util.Map.Entry<String, Integer> entry : idReference.entrySet()) {
			if(entry.getValue().equals(id)) {
				result = entry.getKey();
			}
		}
		return result;
	}
	
	/**
	 * This method will read next row of transaction
	 * and return it in id form
	 * 
	 * @return
	 */
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
	
	/**
	 * This method will count how many time each item 
	 * appear in all transaction
	 */
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
	
	/**
	 * This method will create an initial item set
	 * all item from item set will have support that 
	 * above the threshold
	 * 
	 * @param threshold is minimum support
	 */
	public void createItemSet(int threshold) {
		for(String item : itemCount.keySet()) {
			int support = itemCount.get(item);
			if(support >= threshold) {
				itemSet.add(idReference.get(item));
			}
		}
	}
	
	private CSV csv = new CSV();
	private String[] dataCells;
	private File file;
	public static Map<String, Integer> idReference = new HashMap<>();
	static Map<String, Integer> itemCount = new HashMap<>();
	private ArrayList<Integer> itemSet = new ArrayList<>();
	public static int numberOfTransactions;
}