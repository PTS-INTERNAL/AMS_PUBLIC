package com.ams.model;

public class InventorySessionModel {
	
	private String invenotrySessionCD;
	private String inventorySessionCompanyCD;
	private String inventorySessionName;
	public String getInventorySessionCompanyCD() {
		return inventorySessionCompanyCD;
	}

	public void setInventorySessionCompanyCD(String inventorySessionCompanyCD) {
		this.inventorySessionCompanyCD = inventorySessionCompanyCD;
	}

	private String inventorySessionShortCD;
	private String inventorySessionStart;
	private String inventorySessionEnd;
	private UserModel userInsert;
	private UserModel userUpdate;
	private String date_insert;
	private String date_update;
	
	public String getDate_insert() {
		return date_insert;
	}

	public void setDate_insert(String date_insert) {
		this.date_insert = date_insert;
	}

	public String getDate_update() {
		return date_update;
	}

	public void setDate_update(String date_update) {
		this.date_update = date_update;
	}

	public InventorySessionModel()
	{
		this.userInsert = new UserModel();
		this.userUpdate = new UserModel();
	}

	public String getInvenotrySessionCD() {
		return invenotrySessionCD;
	}

	public void setInvenotrySessionCD(String invenotrySessionCD) {
		this.invenotrySessionCD = invenotrySessionCD;
	}

	public String getInventorySessionName() {
		return inventorySessionName;
	}

	public void setInventorySessionName(String inventorySessionName) {
		this.inventorySessionName = inventorySessionName;
	}

	public String getInventorySessionShortCD() {
		return inventorySessionShortCD;
	}

	public void setInventorySessionShortCD(String inventorySessionShortCD) {
		this.inventorySessionShortCD = inventorySessionShortCD;
	}

	public String getInventorySessionStart() {
		return inventorySessionStart;
	}

	public void setInventorySessionStart(String inventorySessionStart) {
		this.inventorySessionStart = inventorySessionStart;
	}

	public String getInventorySessionEnd() {
		return inventorySessionEnd;
	}

	public void setInventorySessionEnd(String inventorySessionEnd) {
		this.inventorySessionEnd = inventorySessionEnd;
	}

	public UserModel getUserInsert() {
		return userInsert;
	}

	public void setUserInsert(UserModel userInsert) {
		this.userInsert = userInsert;
	}

	public UserModel getUserUpdate() {
		return userUpdate;
	}

	public void setUserUpdate(UserModel userUpdate) {
		this.userUpdate = userUpdate;
	}
	
	

}
