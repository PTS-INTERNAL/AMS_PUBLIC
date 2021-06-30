package com.ams.model;

public class UpdateAssetHistoryModel {
	
	private String updateCd;
	private AssetGeneralModel asset;
	private String reason;
	private UserModel user;
	private String date;
	
	public UpdateAssetHistoryModel()
	{
		this.user = new UserModel();
		this.asset = new AssetGeneralModel();
	}
	
	
	public String getUpdateCd() {
		return updateCd;
	}
	public void setUpdateCd(String updateCd) {
		this.updateCd = updateCd;
	}
	public AssetGeneralModel getAsset() {
		return asset;
	}
	public void setAsset(AssetGeneralModel asset) {
		this.asset = asset;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public UserModel getUser() {
		return user;
	}
	public void setUser(UserModel user) {
		this.user = user;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	

}
