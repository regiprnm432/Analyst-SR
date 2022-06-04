package com.mba.analyst_sr;

import java.text.DecimalFormat;

/**
 * This class represent record data for Rules.
 * Contains primitive method and other support
 * method
 * 
 * @author Sendi Setiawan
 *
 */
public class Rules implements Comparable<Rules> {

	//Getter and Setter
	public double getConfidence() {
		return confidence;
	}

	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}

	public double getLift() {
		return lift;
	}

	public void setLift(double lift) {
		this.lift = lift;
	}

	public Integer[] getAntecedents() {
		return antecedents;
	}

	public Integer[] getConsequents() {
		return consequents;
	}

	public double getAntecedentsSupport() {
		return antecedentsSupport;
	}

	public double getConsequentsSupport() {
		return consequentsSupport;
	}

	public Double getRulesSupport() {
		return rulesSupport;
	}

	//Constructor
	public Rules(Integer[] antecedents, Integer[] consequents, double antecedentsSupport, double consequentsSupport,
			Double rulesSupport) {
		super();
		this.antecedents = antecedents;
		this.consequents = consequents;
		this.antecedentsSupport = antecedentsSupport;
		this.consequentsSupport = consequentsSupport;
		this.rulesSupport = rulesSupport;
	}

	//Override method from Comparable interface so that object from this class can be compared
	@Override
    public int compareTo(Rules other) {
        return rulesSupport.compareTo(other.rulesSupport);
    }
	
	/**
	 * Calculate confidence of this rules
	 */
	public void calculateConfidence() {
		confidence = Apriori.calculateConfidence(antecedentsSupport, rulesSupport);
	}
	
	/**
	 * Calculate lift of this rules
	 */
	public void calculateLift() {
		lift = Apriori.calculateLift(antecedentsSupport, consequentsSupport, rulesSupport);
	}
	
	/**
	 * This method will convert all data fields from this class 
	 * into an array of String (to represent a single row)
	 * 
	 * @return array of String that contains all fields of this class
	 */
	public String[] getResultInRowFormat() {
		String[] result = new String[8];
		String antecedents = TransactionData.convertIdToItem(this.antecedents[0]);
		String consequents = TransactionData.convertIdToItem(this.consequents[0]);

		result[0] = " " + antecedents;
		result[1] = " " + consequents;
		result[2] = " " + antecedents + " -> " + consequents;
		result[3] = "<html><center> " + format.format(antecedentsSupport) + " </center></html>";
		result[4] = "<html><center> " + format.format(consequentsSupport) + " </center></html>";
		result[5] = "<html><center> " + format.format(rulesSupport) + " </center></html>";
		result[6] = "<html><center> " + format.format(confidence) + " </center></html>";
		result[7] = "<html><right> " + format.format(lift) + " </right></html>";
		return result;
	}
	
	private Integer[] antecedents;
	private Integer[] consequents;
	private double antecedentsSupport;
	private double consequentsSupport;
	private Double rulesSupport;
	private double confidence;
	private double lift;
	
	//format number for double type variable
	public static DecimalFormat format =  new DecimalFormat("0.0000");

}
