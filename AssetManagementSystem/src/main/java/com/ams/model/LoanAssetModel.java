package com.ams.model;

public class LoanAssetModel {
	
	private AssetGeneralModel asset;
	private CompanyModel cmpn_master;
	private Department dept_master;
	private Department dept_client;
	private CompanyModel cmpn_client;
	private String date_start;
	private String date_end;
	private String date_pay;
	private String id;
	private String reason;
	private String note;
	private String number_on;
	private String reason_not_allow;
	private LoanCouponModel loanCoupon = new LoanCouponModel();
	private String delete_fg;
	private String asseseries;
	
	public LoanCouponModel getLoanCoupon() {
		return loanCoupon;
	}
	public void setLoanCoupon(LoanCouponModel loanCoupon) {
		this.loanCoupon = loanCoupon;
	}
	public String getDelete_fg() {
		return delete_fg;
	}
	public void setDelete_fg(String delete_fg) {
		this.delete_fg = delete_fg;
	}
	public String getAsseseries() {
		return asseseries;
	}
	public void setAsseseries(String asseseries) {
		this.asseseries = asseseries;
	}
	public String getReason_not_allow() {
		return reason_not_allow;
	}
	public void setReason_not_allow(String reason_not_allow) {
		this.reason_not_allow = reason_not_allow;
	}
	public String getNumber_on() {
		return number_on;
	}
	public void setNumber_on(String number_on) {
		this.number_on = number_on;
	}
	private String date_start_end;
	
	private String status;
	
	private String userInsert;
	private String insertDt;
	
	private String userUpdate;
	private String updateDt;
	
	private String userApprove;
	private String approveDt;
	
	private String userConfirm;
	public String getUserReturn() {
		return userReturn;
	}
	public void setUserReturn(String userReturn) {
		this.userReturn = userReturn;
	}
	public String getReturnDt() {
		return returnDt;
	}
	public void setReturnDt(String returnDt) {
		this.returnDt = returnDt;
	}
	private String confirmDt;
	
	private String userReturn;
	private String returnDt;
	
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getUserApprove() {
		return userApprove;
	}
	public void setUserApprove(String userApprove) {
		this.userApprove = userApprove;
	}
	public String getApproveDt() {
		return approveDt;
	}
	public void setApproveDt(String approveDt) {
		this.approveDt = approveDt;
	}
	public String getUserConfirm() {
		return userConfirm;
	}
	public void setUserConfirm(String userConfirm) {
		this.userConfirm = userConfirm;
	}
	public String getConfirmDt() {
		return confirmDt;
	}
	public void setConfirmDt(String confirmDt) {
		this.confirmDt = confirmDt;
	}
	public String getDate_start_end() {
		return date_start_end;
	}
	public void setDate_start_end(String date_start_end) {
		this.date_start_end = date_start_end;
	}
	public LoanAssetModel()
	{
		this.asset = new AssetGeneralModel();
		this.cmpn_master = new CompanyModel();
		this.cmpn_client = new CompanyModel();
		this.dept_client = new Department();
		this.dept_master = new Department();
	}
	public AssetGeneralModel getAsset() {
		return asset;
	}
	public void setAsset(AssetGeneralModel asset) {
		this.asset = asset;
	}
	public CompanyModel getCmpn_master() {
		return cmpn_master;
	}
	public void setCmpn_master(CompanyModel cmpn_master) {
		this.cmpn_master = cmpn_master;
	}
	public Department getDept_master() {
		return dept_master;
	}
	public void setDept_master(Department dept_master) {
		this.dept_master = dept_master;
	}
	public Department getDept_client() {
		return dept_client;
	}
	public void setDept_client(Department dept_client) {
		this.dept_client = dept_client;
	}
	public CompanyModel getCmpn_client() {
		return cmpn_client;
	}
	public void setCmpn_client(CompanyModel cmpn_client) {
		this.cmpn_client = cmpn_client;
	}
	public String getDate_start() {
		return date_start;
	}
	public void setDate_start(String date_start) {
		this.date_start = date_start;
	}
	public String getDate_end() {
		return date_end;
	}
	public void setDate_end(String date_end) {
		this.date_end = date_end;
	}
	public String getDate_pay() {
		return date_pay;
	}
	public void setDate_pay(String date_pay) {
		this.date_pay = date_pay;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	
	
}
