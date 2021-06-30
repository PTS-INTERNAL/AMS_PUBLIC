package com.ams.model;



public class CheckingAssetNew {
	public String getAsset_id() {
		return asset_id;
	}
	public void setAsset_id(String asset_id) {
		this.asset_id = asset_id;
	}
	public String getRfid() {
		return rfid;
	}
	public void setRfid(String rfid) {
		this.rfid = rfid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(String dateCreate) {
		this.dateCreate = dateCreate;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getChecking_session() {
		return checking_session;
	}
	public void setChecking_session(String checking_session) {
		this.checking_session = checking_session;
	}
	private String checking_session;
	
	private String asset_id;
	private String rfid;
	private String name;
	private String department;
	private String reason;
	private String note;
	private String dateCreate;
	private String user;
	private String status;
	private String cmpn_cd;
	public String getCmpn_cd() {
		return cmpn_cd;
	}
	public void setCmpn_cd(String cmpn_cd) {
		this.cmpn_cd = cmpn_cd;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	private String status_name;
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;

	}
	
	

}
