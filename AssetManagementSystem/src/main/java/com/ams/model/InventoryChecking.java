package com.ams.model;

public class InventoryChecking {
	
	private String InventorySessionChecking_CD;
	public String getInventorySessionChecking_CD() {
		return InventorySessionChecking_CD;
	}
	public void setInventorySessionChecking_CD(String inventorySession_CD) {
		InventorySessionChecking_CD = inventorySession_CD;
	}
	public String getAsset_Rfid() {
		return Asset_Rfid;
	}
	public void setAsset_Rfid(String asset_Rfid) {
		Asset_Rfid = asset_Rfid;
	}
	public String getInventorySessionCD() {
		return Inventory_Session_CD;
	}
	public void setInventorySessionCD(String inventory_Checking) {
		Inventory_Session_CD = inventory_Checking;
	}
	public String getInventory_Date() {
		return Inventory_Date;
	}
	public void setInventory_Date(String inventory_Date) {
		Inventory_Date = inventory_Date;
	}
	public String getUserChecking() {
		return UserChecking;
	}
	public void setUserChecking(String userChecking) {
		UserChecking = userChecking;
	}
	private String Asset_Rfid;
	private String Inventory_Session_CD;
	private String Inventory_Date;
	private String UserChecking;
	private String Status;
	private String Company_cd;
	private String Asset_name;
	public String getAsset_name() {
		return Asset_name;
	}
	public void setAsset_name(String asset_name) {
		Asset_name = asset_name;
	}
	public String getCompany_cd() {
		return Company_cd;
	}
	public void setCompany_cd(String company_cd) {
		Company_cd = company_cd;
	}
	public String getInventory_Session_CD() {
		return Inventory_Session_CD;
	}
	public void setInventory_Session_CD(String inventory_Session_CD) {
		Inventory_Session_CD = inventory_Session_CD;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public InventoryChecking() {
		super();
	}
	
	private String status_name;
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	
	public String department;
	public String hientrang;
	public String ngayCapNhat;
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getHientrang() {
		return hientrang;
	}
	public void setHientrang(String hientrang) {
		this.hientrang = hientrang;
	}
	public String getNgayCapNhat() {
		return ngayCapNhat;
	}
	public void setNgayCapNhat(String ngayCapNhat) {
		this.ngayCapNhat = ngayCapNhat;
	}
	
	
	
	

}
