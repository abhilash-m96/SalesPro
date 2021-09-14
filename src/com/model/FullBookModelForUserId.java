package com.model;

import java.util.List;

public class FullBookModelForUserId {
	
	private String userId;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<BookPackModel> getBooksList() {
		return booksList;
	}

	public void setBooksList(List<BookPackModel> booksList) {
		this.booksList = booksList;
	}

	public String getAdv() {
		return adv;
	}

	public void setAdv(String adv) {
		this.adv = adv;
	}

	private List<BookPackModel> booksList;
	
	
	private String adv;
	
	

}
