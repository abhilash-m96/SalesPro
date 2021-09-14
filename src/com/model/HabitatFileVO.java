package com.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class HabitatFileVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int sessionId;
	
	private String sessionName;
	
	private String actionName;
	
	private String actionType;
	
	private String ipAddress;

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Date getTimeValue() {
		return timeValue;
	}

	public void setTimeValue(Date timeValue) {
		this.timeValue = timeValue;
	}

	public String getTimeStampInStr() {
		return timeStampInStr;
	}

	public void setTimeStampInStr(String timeStampInStr) {
		this.timeStampInStr = timeStampInStr;
	}

	private String userName;
	
	private String date;
	
	private Date timeValue;
	
	private String timeStampInStr;


}
