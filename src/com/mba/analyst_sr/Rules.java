package com.mba.analyst_sr;

import java.text.DecimalFormat;

public class Rules implements Comparable<Rules> {

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

	public Rules(Integer[] antecedents, Integer[] consequents, double antecedentsSupport, double consequentsSupport,
			Double rulesSupport) {
		super();
		this.antecedents = antecedents;
		this.consequents = consequents;
		this.antecedentsSupport = antecedentsSupport;
		this.consequentsSupport = consequentsSupport;
		this.rulesSupport = rulesSupport;
	}

	@Override
    public int compareTo(Rules other) {
        return rulesSupport.compareTo(other.rulesSupport);
    }
	
	public void calculateConfidence() {
		confidence = Apriori.calculateConfidence(antecedentsSupport, rulesSupport);
	}
	
	public void calculateLift() {
		lift = Apriori.calculateLift(antecedentsSupport, consequentsSupport, rulesSupport);
	}
	
	@Override
	public String toString() {
		String result = "";
		result += TransactionData.convertIdToItem(antecedents[0]) + " -> ";
		result += TransactionData.convertIdToItem(consequents[0]) + " : \t";
		result += format.format(antecedentsSupport) + " ";
		result += format.format(consequentsSupport) + " ";
		result += format.format(rulesSupport) + " ";
		result += format.format(confidence) + " ";
		result += format.format(lift) + "\n";
		return result;
	}

	private Integer[] antecedents;
	private Integer[] consequents;
	private double antecedentsSupport;
	private double consequentsSupport;
	private Double rulesSupport;
	private double confidence;
	private double lift;
	
	public static DecimalFormat format =  new DecimalFormat("0.000");

}
