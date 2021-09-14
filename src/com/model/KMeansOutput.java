package com.model;

import java.io.Serializable;

public class KMeansOutput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int productId;

	public double getFrequency() {
		return frequency;
	}

	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}

	public double getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(double noOfDays) {
		this.noOfDays = noOfDays;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	private double frequency;

	private double noOfDays;
	
	private String productName;

}
