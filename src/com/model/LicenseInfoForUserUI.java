package com.model;

import java.io.Serializable;

public class LicenseInfoForUserUI implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public boolean isSports() {
		return sports;
	}

	public void setSports(boolean sports) {
		this.sports = sports;
	}

	public boolean isPolitics() {
		return politics;
	}

	public void setPolitics(boolean politics) {
		this.politics = politics;
	}

	public boolean isFlimfare() {
		return flimfare;
	}

	public void setFlimfare(boolean flimfare) {
		this.flimfare = flimfare;
	}

	public boolean isAnalytics() {
		return analytics;
	}

	public void setAnalytics(boolean analytics) {
		this.analytics = analytics;
	}

	public boolean isGreetings() {
		return greetings;
	}

	public void setGreetings(boolean greetings) {
		this.greetings = greetings;
	}

	public boolean isSettings() {
		return settings;
	}

	public void setSettings(boolean settings) {
		this.settings = settings;
	}

	public boolean isBudgetset() {
		return budgetset;
	}

	public void setBudgetset(boolean budgetset) {
		this.budgetset = budgetset;
	}

	public boolean isRankbooks() {
		return rankbooks;
	}

	public void setRankbooks(boolean rankbooks) {
		this.rankbooks = rankbooks;
	}

	public boolean isProgramming() {
		return programming;
	}

	public void setProgramming(boolean programming) {
		this.programming = programming;
	}

	private String loginId;

	private boolean sports;

	private boolean politics;

	private boolean flimfare;

	private boolean analytics;

	private boolean greetings;

	private boolean settings;
	
	private boolean budgetset;
	
	private boolean rankbooks;
	
	private boolean programming;
	

}
