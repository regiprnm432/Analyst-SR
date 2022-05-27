import java.util.ArrayList;

public class Subset {
    public static char[] transactionList;
    public static ArrayList<String> subs = new ArrayList<>();
    public static char[] stri;
    public static int transactionLen;

    public static void subset(int start, int jmlA, int length) {
        for(int i = start + length; i < transactionLen -  jmlA + start; i++) {
            stri[start] = transactionList[i];
            if(start < jmlA) {
                subset(start + 1, jmlA, i - start); 
            } else {
                String str = new String(stri);
                subs.add(str);
            }
        }
    }

    public static ArrayList<String> getSubset(char[] transaction) {
    	Subset.transactionList = transaction;
    	Subset.transactionLen = transaction.length;
        for(int l = 0; l < transaction.length; l++) {
			Subset.stri = new char[l+1];
            subset(0, l, 0);
        }
        return subs;
    }
}