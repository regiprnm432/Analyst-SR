import java.io.File;
import java.util.ArrayList;

public class MarketBasketAnalysis {

	public static void main(String[] args) {
		File data = new File("D:\\groceries3.csv");
		NBTree tree = new NBTree();
		TransactionData transactionData = new TransactionData();
		
		//Reading file and make id for all items
		System.out.println("reading file");
		transactionData.readFile(data);
		transactionData.makeId();
		char[] transaction = transactionData.getNextTransaction();
		System.out.println("file read successfully");
		
		//Make subset from all item
		System.out.println("Make subset");
		ArrayList<String> subset = new ArrayList<>();
		subset = Subset.getSubset(transaction);
		System.out.println("Successfully created");

		//Insert all subset to Tree
		System.out.println("Inserting all subset to tree");
		for(String element : subset) {
			tree.insert(element);
		}
		System.out.println("Insertion to tree success");
		
		//count and add support
		System.out.println("Adding support");
		transaction = transactionData.getNextTransaction();
		while(transaction != null) {
			subset.clear();
			subset = Subset.getSubset(transaction);
			for(String element : subset) {
				tree.addSupport(element);
			}
			transaction = transactionData.getNextTransaction();
		}
		
		tree.levelOrderTransversal();
		
	}

}
