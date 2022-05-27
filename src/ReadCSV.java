import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadCSV {
	private Scanner scan;
	
	//Membuka file dan menerapkan delimiter "," (memudahkan pembacaan file .csv)
	public void readCSV(File data) {
		try {
			scan = new Scanner(data);
			scan.useDelimiter(",");
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Function untuk membaca row selanjutnya
	 * Nilai kembailan berupa array String (String disini merepresentasikan sebuah cell)
	 */
	public String[] getNextRow() {
		String[] row = {};
		try {
			row = scan.nextLine().split(",");
		} catch (Exception e) {
		}
		return row;
	}
}
