import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TransactionData {
	private ReadCSV readCSV = new ReadCSV();
	private String[] dataRows;
	private Map<String, Character> idReference = new HashMap<>();
	
	public void readFile(File data) {
		readCSV.readCSV(data);
	}

	public void makeId() {
		//id akan menggunakan character dimulai dari 'A' (ASCII codenya = 65)
		int ascii = 65;
		
		dataRows = readCSV.getNextRow();
		for(String column : dataRows) {
			idReference.put(column, (char) ascii);
			ascii++;
		}
	}
	
	public char[] getNextTransaction() {
		ArrayList<Character> transaction = new ArrayList<>();
		
		if(dataRows.length == 0) {
			return null;
		}
		for(String item : dataRows) {
			if(item != null) {
				transaction.add(idReference.get(item));
			}
		}
		dataRows = readCSV.getNextRow();
		
		char[] result = new char[transaction.size()];
		for(int idx = 0; idx < transaction.size(); idx++) {
			result[idx] = transaction.get(idx);
		}
		return result;
	}
}