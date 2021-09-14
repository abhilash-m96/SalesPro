package com.model;

import java.io.Serializable;
import java.util.List;

public class AdvitisementModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userId;
	
	private List<PageInfo> pageInfoList;
	
	private List<PageInfo> allPageInformation;
	
	private CrossOverObj crossOverObj;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<PageInfo> getPageInfoList() {
		return pageInfoList;
	}

	public void setPageInfoList(List<PageInfo> pageInfoList) {
		this.pageInfoList = pageInfoList;
	}

	public List<PageInfo> getAllPageInformation() {
		return allPageInformation;
	}

	public void setAllPageInformation(List<PageInfo> allPageInformation) {
		this.allPageInformation = allPageInformation;
	}

	public CrossOverObj getCrossOverObj() {
		return crossOverObj;
	}

	public void setCrossOverObj(CrossOverObj crossOverObj) {
		this.crossOverObj = crossOverObj;
	}
	
	public int getPageToPush() {
		return pageToPush;
	}

	public void setPageToPush(int pageToPush) {
		this.pageToPush = pageToPush;
	}

	public boolean isTwoPathPossible() {
		return isTwoPathPossible;
	}

	public void setTwoPathPossible(boolean isTwoPathPossible) {
		this.isTwoPathPossible = isTwoPathPossible;
	}

	private int pageToPush;
	
	private boolean isTwoPathPossible;

}
