package com.ams.model;

public class AssetManagementTable extends InventoryChecking  {
	
	private String Asset_Name;
	private String UserName;
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getAsset_Name() {
		return Asset_Name;
	}
	public void setAsset_Name(String asset_Name) {
		Asset_Name = asset_Name;
	}
	public String getDepartment() {
		return Department;
	}
	public void setDepartment(String department) {
		Department = department;
	}
	private String Department;
	

}
