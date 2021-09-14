package com.utils;

import java.io.Serializable;

public class FormulaUtil implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public double computeDistance(double x1,double x2,double y1,double y2){
		
		return Math.sqrt(Math.pow((x2-x1),2)+Math.pow((y2-y1),2));
		
	}
	

}
