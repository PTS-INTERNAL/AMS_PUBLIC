package com.ams.model;

public class HistoryAssetUpdateModel {
	private String update_cd;
	private String update_asset_cd;
	private String update_asset_rfid;
	private String update_cmpn_cd;
	private String update_user_insert;
	public String getUpdate_cd() {
		return update_cd;
	}
	public String getUpdate_asset_cd() {
		return update_asset_cd;
	}
	public void setUpdate_asset_cd(String update_asset_cd) {
		this.update_asset_cd = update_asset_cd;
	}
	public void setUpdate_cd(String update_cd) {
		this.update_cd = update_cd;
	}
	public String getUpdate_asset_rfid() {
		return update_asset_rfid;
	}
	public void setUpdate_asset_rfid(String update_asset_rfid) {
		this.update_asset_rfid = update_asset_rfid;
	}
	public String getUpdate_cmpn_cd() {
		return update_cmpn_cd;
	}
	public void setUpdate_cmpn_cd(String update_cmpn_cd) {
		this.update_cmpn_cd = update_cmpn_cd;
	}
	public String getUpdate_user_insert() {
		return update_user_insert;
	}
	public void setUpdate_user_insert(String update_user_insert) {
		this.update_user_insert = update_user_insert;
	}
	public String getUpdate_insert_dt() {
		return update_insert_dt;
	}
	public void setUpdate_insert_dt(String update_insert_dt) {
		this.update_insert_dt = update_insert_dt;
	}
	public String getUpdate_reason() {
		return update_reason;
	}
	public void setUpdate_reason(String update_reason) {
		this.update_reason = update_reason;
	}
	private String update_insert_dt;
	private String update_reason;

}
