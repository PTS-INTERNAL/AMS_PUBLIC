package com.ams.model;

public class InventoryCheckingUhf {
	
	private CompanyModel company;
	private AssetGeneralModel asset;
	private Department deptChecking;
	private InventorySessionModel inventorySession;
	private String  userInsert;
	private String insertDt;
	private String note;
	public InventorySessionModel getInventorySession() {
		return inventorySession;
	}
	public void setInventorySession(InventorySessionModel inventorySession) {
		this.inventorySession = inventorySession;
	}
	private String inventoryChekingUhf_cd;
	private String status;
	
	public InventoryCheckingUhf()
	{
		this.asset = new AssetGeneralModel();
		this.deptChecking = new Department();
		this.company = new CompanyModel();
		this.inventorySession = new InventorySessionModel();
	}
	public CompanyModel getCompany() {
		return company;
	}
	public void setCompany(CompanyModel company) {
		this.company = company;
	}
	public AssetGeneralModel getAsset() {
		return asset;
	}
	public void setAsset(AssetGeneralModel asset) {
		this.asset = asset;
	}
	public Department getDeptChecking() {
		return deptChecking;
	}
	public void setDeptChecking(Department deptChecking) {
		this.deptChecking = deptChecking;
	}
	public String getUserInsert() {
		return userInsert;
	}
	public void setUserInsert(String userInsert) {
		this.userInsert = userInsert;
	}
	public String getInsertDt() {
		return insertDt;
	}
	public void setInsertDt(String insertDt) {
		this.insertDt = insertDt;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getInventoryChekingUhf_cd() {
		return inventoryChekingUhf_cd;
	}
	public void setInventoryChekingUhf_cd(String inventoryChekingUhf_cd) {
		this.inventoryChekingUhf_cd = inventoryChekingUhf_cd;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
