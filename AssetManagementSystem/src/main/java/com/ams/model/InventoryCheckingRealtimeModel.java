package com.ams.model;

public class InventoryCheckingRealtimeModel {

	private AssetGeneralModel asset;
	private InventorySessionModel inventorySession;
	private String status;
	private String userInsert;
	private String insertDt;
	private String userUpdate;
	private String ivn_Session_Cd;
	
	private String explain;
	private String user_explain;
	private String expain_dt;
	private String status_Explain;
	
	public String getStatus_Explain() {
		return status_Explain;
	}

	public void setStatus_Explain(String status_Explain) {
		this.status_Explain = status_Explain;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getUser_explain() {
		return user_explain;
	}

	public void setUser_explain(String user_explain) {
		this.user_explain = user_explain;
	}

	public String getExpain_dt() {
		return expain_dt;
	}

	public void setExpain_dt(String expain_dt) {
		this.expain_dt = expain_dt;
	}

	public String getIvn_Session_Cd() {
		return ivn_Session_Cd;
	}

	public void setIvn_Session_Cd(String ivn_Session_Cd) {
		this.ivn_Session_Cd = ivn_Session_Cd;
	}

	public String getInventory_Checking_Cd() {
		return inventory_Checking_Cd;
	}

	public void setInventory_Checking_Cd(String inventory_Checking_Cd) {
		this.inventory_Checking_Cd = inventory_Checking_Cd;
	}

	private String updateDt;
	private GroupAsset group;
	private Department deptChecking;
	private Department deptUsing;
	private String inventory_Checking_Cd;
	
	
	
	
	public GroupAsset getGroup() {
		return group;
	}

	public void setGroup(GroupAsset group) {
		this.group = group;
	}

	public Department getDeptChecking() {
		return deptChecking;
	}

	public void setDeptChecking(Department deptChecking) {
		this.deptChecking = deptChecking;
	}

	public Department getDeptUsing() {
		return deptUsing;
	}

	public void setDeptUsing(Department deptUsing) {
		this.deptUsing = deptUsing;
	}

	public InventorySessionModel getInventorySession() {
		return inventorySession;
	}

	public void setInventorySession(InventorySessionModel inventorySession) {
		this.inventorySession = inventorySession;
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

	public String getUserUpdate() {
		return userUpdate;
	}

	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}

	public String getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(String updateDt) {
		this.updateDt = updateDt;
	}

	public InventoryCheckingRealtimeModel()
	{
		this.asset = new AssetGeneralModel();
		this.inventorySession = new InventorySessionModel();
		this.group = new GroupAsset();
		this.deptChecking = new Department();
		this.deptUsing = new Department();
	}

	public AssetGeneralModel getAsset() {
		return asset;
	}

	public void setAsset(AssetGeneralModel asset) {
		this.asset = asset;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
