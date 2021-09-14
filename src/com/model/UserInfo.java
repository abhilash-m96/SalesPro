package com.model;

public class UserInfo {
	
	private String loginId;
	
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public int getNoOfAdv() {
		return noOfAdv;
	}

	public void setNoOfAdv(int noOfAdv) {
		this.noOfAdv = noOfAdv;
	}

	public double getTimeOfStay() {
		return timeOfStay;
	}

	public void setTimeOfStay(double timeOfStay) {
		this.timeOfStay = timeOfStay;
	}

	public double getNoOfBytes() {
		return noOfBytes;
	}

	public void setNoOfBytes(double noOfBytes) {
		this.noOfBytes = noOfBytes;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	private int noOfAdv;
	
	private double timeOfStay;
	
	private double noOfBytes;
	
	private int pageId;
	
	private int frequency;

}
