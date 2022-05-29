package com.mba.analyst_sr;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class DriverClass {
	static ArrayList<Rules> analysisResult;
	
	public static void printAnalysis() {
		Collections.sort(analysisResult);
		for(Rules rule : analysisResult) {
			System.out.println(rule.toString());
		}
	}

	public static void main(String[] args) {

		File data = new File("D:\\dataTransaction.csv");
		MarketBasketAnalysis analysis = new MarketBasketAnalysis();

		analysis.setThreshold(300);
		analysis.setFile(data);
		analysis.processFile();
		analysis.makeInitialTree();
		analysis.countAllItemsSupport();
		
		
		analysisResult = analysis.getAnalysisResult();
		
		printAnalysis();
	}

}
