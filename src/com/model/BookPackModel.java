package com.model;

import java.io.Serializable;


public class BookPackModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
	
	private int bookId;
	
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

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	
	public String getBookOverview() {
		return bookOverview;
	}

	public void setBookOverview(String bookOverview) {
		this.bookOverview = bookOverview;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getBookLoc() {
		return bookLoc;
	}

	public void setBookLoc(String bookLoc) {
		this.bookLoc = bookLoc;
	}

	public double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}

	public String getSubCatId() {
		return subCatId;
	}

	public void setSubCatId(String subCatId) {
		this.subCatId = subCatId;
	}

	private String bookName;
	
	private int catId;
	
	private String subCatId;
	
	private String author;
	
	private String publisher;
	
	
	private String bookOverview;
	
	private String edition;
	
	private int rating;
	
	private String bookLoc;

	private double bookPrice;
	
}
