package com.ams.model;

public class TroubleAssetModel {
	
	private AssetGeneralModel asset;
	private Department dept;
	private String userUse;
	private String dateTrouble;
	private String dateTroubleEnd;
	private String Trouble_CD;
	private String Trouble_Status;

	public String getDateTroubleEnd() {
		return dateTroubleEnd;
	}

	public void setDateTroubleEnd(String dateTroubleEnd) {
		this.dateTroubleEnd = dateTroubleEnd;
	}

	private String timeTrouble;
	private String trouble;
	private String reason;
	private String note;
	private String user_insert;
	private String insert_dt;
	private String user_update;
	public String getUser_insert() {
		return user_insert;
	}

	public void setUser_insert(String user_insert) {
		this.user_insert = user_insert;
	}

	public String getInsert_dt() {
		return insert_dt;
	}

	public void setInsert_dt(String insert_dt) {
		this.insert_dt = insert_dt;
	}

	public String getUser_update() {
		return user_update;
	}

	public void setUser_update(String user_update) {
		this.user_update = user_update;
	}

	public String getUpdate_dt() {
		return update_dt;
	}

	public void setUpdate_dt(String update_dt) {
		this.update_dt = update_dt;
	}

	private String update_dt;
	
	
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public AssetGeneralModel getAsset() {
		return asset;
	}

	public void setAsset(AssetGeneralModel asset) {
		this.asset = asset;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public String getUserUse() {
		return userUse;
	}

	public void setUserUse(String userUse) {
		this.userUse = userUse;
	}

	public String getDateTrouble() {
		return dateTrouble;
	}

	public void setDateTrouble(String dateTrouble) {
		this.dateTrouble = dateTrouble;
	}
	
	public String getTroubleID() {
		return Trouble_CD;
	}

	public void setTroubleID(String TroubleID) {
		this.Trouble_CD = TroubleID;
	}
	
	
	
	public String getTroubleStatus() {
		return Trouble_Status;
	}

	public void setTroubleStatus(String TroubleStatus) {
		this.Trouble_Status = TroubleStatus;
	}
	
	
	public String getTimeTrouble() {
		return timeTrouble;
	}
	

	public void setTimeTrouble(String timeTrouble) {
		this.timeTrouble = timeTrouble;
	}

	public String getTrouble() {
		return trouble;
	}

	public void setTrouble(String trouble) {
		this.trouble = trouble;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public TroubleAssetModel()
	{
		this.asset = new AssetGeneralModel();
		this.dept = new Department();
	}

}
