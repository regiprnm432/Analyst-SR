package com.mba.analyst_sr;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class is used for read a .csv file
 * the reading method is row by row
 * 
 * @author Sendi Setiawan
 *
 */
public class CSV {
	private Scanner scan;
	
	/**
	 * This method will make a new Scanner for 
	 * the included file and apply "," delimiter
	 * for easy reading csv file
	 * 
	 * @param file
	 */
	public void readCSV(File file) {
		try {
			scan = new Scanner(file);
			scan.useDelimiter(",");
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method will read next row 
	 * from the scanner that has been made
	 * 
	 * @return String[] as representation of cells in row
	 */
	public String[] getNextRow() {
		String[] row = {};
		try {
			row = scan.nextLine().split(",");
		} catch (Exception e) {
		}
		return row;
	}
	
	/**
	 * close the scanner file stream
	 */
	public void closeStream() {
		scan.close();
	}
}