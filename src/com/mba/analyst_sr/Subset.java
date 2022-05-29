package com.mba.analyst_sr;
import java.util.ArrayList;

/**
 * This class is used for making subset from
 * a transaction. The max length of the subset
 * can be set
 * 
 * @author Sendi Setiawan
 *
 */
final class Subset {

	/**
	 * This method will create all subset from
	 * transaction with subset size of subsetSize
	 * 
	 * @param start
	 * @param subsetSize
	 * @param length
	 */
    public static void subset(int start, int subsetSize, int length) {
        for(int i = start + length; i < transactionLen -  subsetSize + start; i++) {
            subset[start] = transaction.get(i);
            if(start < subsetSize) {
                subset(start + 1, subsetSize, i - start); 
            } else {
                ArrayList<Integer> newSubset = new ArrayList<>();
                for(int j = 0; j < subset.length; j++) {
                    newSubset.add(subset[j]);
                }
                allSubset.add(newSubset);
            }
        }
    }

    /**
     * This method is controller for the above method.
     * It will control the maximum subset size to create
     * and initialize field of the class
     * 
     * @param transaction is a set which each subset will be made
     * @param subsetLength is a maximum length of subset that will be made
     * @return
     */
    public static ArrayList<ArrayList<Integer>> getSubset(ArrayList<Integer> transaction, int subsetLength) {
    	Subset.transaction = transaction;
    	Subset.transactionLen = transaction.size();
        int maxSubsetSize = transactionLen;
        if(subsetLength < maxSubsetSize) {
            maxSubsetSize = subsetLength;
        }
        for(int l = 0; l < maxSubsetSize; l++) {
			Subset.subset= new Integer[l+1];
            subset(0, l, 0);
        }
        return allSubset;
    }

    public static ArrayList<Integer> transaction;
    public static ArrayList<ArrayList<Integer>> allSubset = new ArrayList<>();
    public static Integer[] subset;
    public static int transactionLen;
}