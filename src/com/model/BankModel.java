package com.model;

public class BankModel {
	
	private String accountNo;
	
	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getiPin() {
		return iPin;
	}

	public void setiPin(String iPin) {
		this.iPin = iPin;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getAmount() {
		return amount;
	}

	public void setPackId(int packId) {
		this.packId = packId;
	}

	public int getPackId() {
		return packId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerId() {
		return customerId;
	}

	private String iPin;
	
	private double amount;
	
	private int packId;
	
	private String customerId;

}
