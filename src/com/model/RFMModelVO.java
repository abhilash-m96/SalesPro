package com.model;

import java.io.Serializable;

public class RFMModelVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int rfmId;

	private double frequency;

	public double getFrequency() {
		return frequency;
	}

	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}

	public double getRecency() {
		return recency;
	}

	public void setRecency(double recency) {
		this.recency = recency;
	}

	public double getMonetory() {
		return monetory;
	}

	public void setMonetory(double monetory) {
		this.monetory = monetory;
	}

	private double recency;

	private double monetory;

	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public double getFreqWeight() {
		return freqWeight;
	}

	public void setFreqWeight(double freqWeight) {
		this.freqWeight = freqWeight;
	}

	public double getRecencyWeight() {
		return recencyWeight;
	}

	public void setRecencyWeight(double recencyWeight) {
		this.recencyWeight = recencyWeight;
	}

	public double getMonetoryWeight() {
		return monetoryWeight;
	}

	public void setMonetoryWeight(double monetoryWeight) {
		this.monetoryWeight = monetoryWeight;
	}

	public double getRfmWeight() {
		return rfmWeight;
	}

	public void setRfmWeight(double rfmWeight) {
		this.rfmWeight = rfmWeight;
	}

	public int getRfmId() {
		return rfmId;
	}

	public void setRfmId(int rfmId) {
		this.rfmId = rfmId;
	}

	private double freqWeight;

	private double recencyWeight;

	private double monetoryWeight;

	private double rfmWeight;
}
