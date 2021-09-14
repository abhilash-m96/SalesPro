package com.model;

import java.io.Serializable;

public class ClassifyCustomerOutput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int noOfUsers;

	private String catName;

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public int getNoOfUsers() {
		return noOfUsers;
	}

	public void setNoOfUsers(int noOfUsers) {
		this.noOfUsers = noOfUsers;
	}

}
