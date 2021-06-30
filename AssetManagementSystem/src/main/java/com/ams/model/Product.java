package com.ams.model;

import java.util.Date;

public class Product {
	private String id;
	private String name;
	private long price;
	private int quanity;
	private boolean status;
	private String creationDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public int getQuanity() {
		return quanity;
	}
	public void setQuanity(int quanity) {
		this.quanity = quanity;
	}
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public Product(String id, String name, long price, int quanity, boolean status, String creationDate) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.quanity = quanity;
		this.status = status;
		this.creationDate = creationDate;
	}
	
	public Product() {
		super();
	}
	

}
