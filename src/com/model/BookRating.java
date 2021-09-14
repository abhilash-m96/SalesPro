package com.model;

import java.io.Serializable;

public class BookRating implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int bookId;
	
	
	
	public int getRating() { 
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	
	
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}



	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}



	private int rating;
	
	
	private String bookName;
 
}
