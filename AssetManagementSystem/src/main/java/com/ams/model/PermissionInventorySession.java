package com.ams.model;

public class PermissionInventorySession {
	private UserModel user;
	private InventorySession inventorySession;
	private String userInsert;
	private String insert_dt;
	private String userUpdate;
	private String update_dt;
	
	public PermissionInventorySession()
	{
		this.inventorySession = new InventorySession();
		this.user = new UserModel();
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public InventorySession getInventorySession() {
		return inventorySession;
	}

	public void setInventorySession(InventorySession inventorySession) {
		this.inventorySession = inventorySession;
	}

	public String getUserInsert() {
		return userInsert;
	}

	public void setUserInsert(String userInsert) {
		this.userInsert = userInsert;
	}

	public String getInsert_dt() {
		return insert_dt;
	}

	public void setInsert_dt(String insert_dt) {
		this.insert_dt = insert_dt;
	}

	public String getUserUpdate() {
		return userUpdate;
	}

	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}

	public String getUpdate_dt() {
		return update_dt;
	}

	public void setUpdate_dt(String update_dt) {
		this.update_dt = update_dt;
	}
	
	

}
