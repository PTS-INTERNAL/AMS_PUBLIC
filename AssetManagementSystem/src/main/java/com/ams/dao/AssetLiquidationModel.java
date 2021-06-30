package com.ams.dao;

import com.ams.model.AssetGeneralModel;
import com.ams.model.GroupAsset;

public class AssetLiquidationModel {
	private AssetGeneralModel asset;
	private GroupAsset group;
	private String dateProposal;
	private String userProposal;
	private String dateApprove;
	private String status;
	private String reason;
	private String note;
	private String insertDT;
	private String userUpdate;
	private String updateDT;
	private String userApprove;
	private String ApproveDT;
	private String List_id;
	public String getList_ID() {
		return List_id;
	}

	
	
	public void setList_ID(String id) {
		this.List_id = id;
	}

	
	public String getUserProposal() {
		return userProposal;
	}

	public void setUserProposal(String userProposal) {
		this.userProposal = userProposal;
	}

	public String getDateApprove() {
		return dateApprove;
	}

	public void setDateApprove(String dateApprove) {
		this.dateApprove = dateApprove;
	}
	private String reason_not_allow;
		public String getReason_not_allow() {
		return reason_not_allow;
	}

	public void setReason_not_allow(String reason_not_allow) {
		this.reason_not_allow = reason_not_allow;
	}
	private String number;
	private String userInsert;
	private String liquidation_Cd;
	public String getLiquidation_Cd() {
		return liquidation_Cd;
	}

	public void setLiquidation_Cd(String liquidation_Cd) {
		this.liquidation_Cd = liquidation_Cd;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	
	
	public AssetLiquidationModel()
	{
		this.asset = new AssetGeneralModel();
		this.group = new GroupAsset();
	}

	public AssetGeneralModel getAsset() {
		return asset;
	}

	public void setAsset(AssetGeneralModel asset) {
		this.asset = asset;
	}

	public GroupAsset getGroup() {
		return group;
	}

	public void setGroup(GroupAsset group) {
		this.group = group;
	}

	public String getDateProposal() {
		return dateProposal;
	}

	public void setDateProposal(String dateProposal) {
		this.dateProposal = dateProposal;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getUserInsert() {
		return userInsert;
	}

	public void setUserInsert(String userInsert) {
		this.userInsert = userInsert;
	}

	public String getInsertDT() {
		return insertDT;
	}

	public void setInsertDT(String insertDT) {
		this.insertDT = insertDT;
	}

	public String getUserUpdate() {
		return userUpdate;
	}

	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}

	public String getUpdateDT() {
		return updateDT;
	}

	public void setUpdateDT(String updateDT) {
		this.updateDT = updateDT;
	}

	public String getUserApprove() {
		return userApprove;
	}

	public void setUserApprove(String userApprove) {
		this.userApprove = userApprove;
	}

	public String getApproveDT() {
		return ApproveDT;
	}

	public void setApproveDT(String approveDT) {
		ApproveDT = approveDT;
	}
	
	

}
