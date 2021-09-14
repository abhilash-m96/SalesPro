package com.model;

public class KMeansExtendedOutput extends KMeansOutput {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double distanceC1;
	
	public double getDistanceC1() {
		return distanceC1;
	}

	public void setDistanceC1(double distanceC1) {
		this.distanceC1 = distanceC1;
	}

	public double getDistanceC2() {
		return distanceC2;
	}

	public void setDistanceC2(double distanceC2) {
		this.distanceC2 = distanceC2;
	}

	public double getDistanceC3() {
		return distanceC3;
	}

	public void setDistanceC3(double distanceC3) {
		this.distanceC3 = distanceC3;
	}

	public double getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(double minDistance) {
		this.minDistance = minDistance;
	}

	public int getClusterNo() {
		return clusterNo;
	}

	public void setClusterNo(int clusterNo) {
		this.clusterNo = clusterNo;
	}

	private double distanceC2;
	
	private double distanceC3;
	
	private double minDistance;
	
	private int clusterNo;

}
