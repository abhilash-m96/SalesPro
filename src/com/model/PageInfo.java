package com.model;

import java.io.Serializable;

/**
 * The Class PageInfo.
 */
public class PageInfo implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** The page name. */
    private Integer pageName;
    
  

    public Double getBestCost() {
        return bestCost;
    }

    public void setBestCost(Double bestCost) {
        this.bestCost = bestCost;
    }

    public Integer getPageName() {
		return pageName;
	}

	public void setPageName(Integer pageName) {
		this.pageName = pageName;
	}

	/** The best cost. */
    private Double bestCost;

}
