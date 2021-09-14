package com.model;

import java.io.Serializable;

public class ClassifyCustomerVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String customer;
	
	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public double getRfm() {
		return rfm;
	}

	public void setRfm(double rfm) {
		this.rfm = rfm;
	}

	public int getClusterNo() {
		return clusterNo;
	}

	public void setClusterNo(int clusterNo) {
		this.clusterNo = clusterNo;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	private double rfm;
	
	private int  clusterNo;
	
	private String clusterName;
	
}
