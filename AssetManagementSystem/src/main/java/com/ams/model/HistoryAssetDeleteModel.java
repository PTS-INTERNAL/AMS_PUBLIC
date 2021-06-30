package com.ams.model;

public class HistoryAssetDeleteModel {
	private String delete_cd;
	private AssetGeneralModel asset;
	private String delete_user_insert;
	
	public HistoryAssetDeleteModel()
	{
		this.asset = new AssetGeneralModel();
	}
	
	public String getDelete_cd() {
		return delete_cd;
	}

	public void setDelete_cd(String delete_cd) {
		this.delete_cd = delete_cd;
	}

	public String getDelete_user_insert() {
		return delete_user_insert;
	}

	public void setDelete_user_insert(String delete_user_insert) {
		this.delete_user_insert = delete_user_insert;
	}

	public String getDelete_insert_dt() {
		return delete_insert_dt;
	}

	public void setDelete_insert_dt(String delete_insert_dt) {
		this.delete_insert_dt = delete_insert_dt;
	}

	public String getDelete_reason() {
		return delete_reason;
	}

	public void setDelete_reason(String delete_reason) {
		this.delete_reason = delete_reason;
	}

	public AssetGeneralModel getAsset() {
		return asset;
	}
	public void setAsset(AssetGeneralModel asset) {
		this.asset = asset;
	}

	private String delete_insert_dt;
	private String delete_reason;

}
