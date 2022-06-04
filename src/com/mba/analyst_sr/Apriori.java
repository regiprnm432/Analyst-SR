package com.mba.analyst_sr;

/**
 * This class is used for calculating apriori property
 * (confidence and lift)
 * 
 * @author Sendi Setiawan
 */
final class Apriori {

	/**
	 * This method will calculate the confidence of a rule
	 * confidence is percentage of all item bought together
	 * rather than purchased separately
	 * 
	 * @param antecedentsSupport
	 * @param rulesSupport is support for all item bought together
	 * @return calculation result in double type
	 */
	public static double calculateConfidence(double antecedentsSupport, double rulesSupport) {
		return rulesSupport/antecedentsSupport;
	}
	
	/**
	 * This method will calculate lift of a rule
	 * lift indicate how strong association of the rule
	 * higher lift indicates higher chances of buying
	 * the items together
	 * 
	 * @param antecedentsSupport
	 * @param consequentsSupport
	 * @param rulesSupport
	 * @return calculateion result in double type
	 */
	public static double calculateLift(double antecedentsSupport, double consequentsSupport, double rulesSupport) {
		return rulesSupport/(antecedentsSupport*consequentsSupport);
	}
}
