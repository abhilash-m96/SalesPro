package com.model;

import java.util.List;

public class AssociationRuleMiningResults {
	
	private List<BookPackModel> collabrativeBooks;
	
	public List<BookPackModel> getCollabrativeBooks() {
		return collabrativeBooks;
	}

	public void setCollabrativeBooks(List<BookPackModel> collabrativeBooks) {
		this.collabrativeBooks = collabrativeBooks;
	}

	public List<BookPackModel> getContentBasedBooks() {
		return contentBasedBooks;
	}

	public void setContentBasedBooks(List<BookPackModel> contentBasedBooks) {
		this.contentBasedBooks = contentBasedBooks;
	}

	public List<BookPackModel> getRuleMiningBooks() {
		return ruleMiningBooks;
	}

	public void setRuleMiningBooks(List<BookPackModel> ruleMiningBooks) {
		this.ruleMiningBooks = ruleMiningBooks;
	}

	private List<BookPackModel> contentBasedBooks;
	
	private List<BookPackModel> ruleMiningBooks;
	
	

}
