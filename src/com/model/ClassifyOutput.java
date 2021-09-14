package com.model;

import java.io.Serializable;

public class ClassifyOutput implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int noOfProducts;
	
	private String catName;

	public int getNoOfProducts() {
		return noOfProducts;
	}

	public void setNoOfProducts(int noOfProducts) {
		this.noOfProducts = noOfProducts;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}
	

}
