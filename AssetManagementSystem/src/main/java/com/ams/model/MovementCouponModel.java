package com.ams.model;

import com.ams.util.Common;

public class MovementCouponModel {
	
	//Thong tin so bo
	private String coupon_cd;
	private String number_no;
	private String movement_sticker;
	private String no_cd;
	private String status;
	private String insert_dt;
	private String insert_user;
	private String update_dt;
	private String update_user;
	private CompanyModel cmpn_master = new CompanyModel();
	private CompanyModel cmpn_client = new CompanyModel();
	private Department dept_master = new Department();
	private Department dept_client = new Department();
	private String reason; 
	private String approve_comment;
	private String date_start;
	private String date_end_schedule;
	private String date_end_real;
	
	private String coupon_loan_cd;
	
	//Thêm trả tài sản (S)
	private String reasonReturnAsset;
	private String commentReturnAsset;
	
	private String userDeptReturnApprove;
	private String userAccountantReturnApprove;
	private String userStockReturnApprove;
	
	private String dtDeptReturnApprove;
	private String dtAccountantReturnApprove;
	public String getMovement_sticker() {
		return movement_sticker;
	}
	public void setMovement_sticker(String movement_sticker) {
		this.movement_sticker = movement_sticker;
	}
	private String dtStockReturnApprove;
	
	//Thêm trả tài sản (E)
	
	
	
	
	
	
	public String getCoupon_loan_cd() {
		return coupon_loan_cd;
	}
	public String getReasonReturnAsset() {
		return reasonReturnAsset;
	}
	public void setReasonReturnAsset(String reasonReturnAsset) {
		this.reasonReturnAsset = reasonReturnAsset;
	}
	public String getCommentReturnAsset() {
		return commentReturnAsset;
	}
	public void setCommentReturnAsset(String commentReturnAsset) {
		this.commentReturnAsset = commentReturnAsset;
	}
	public String getUserDeptReturnApprove() {
		return userDeptReturnApprove;
	}
	public void setUserDeptReturnApprove(String userDeptReturnApprove) {
		this.userDeptReturnApprove = userDeptReturnApprove;
	}
	public String getUserAccountantReturnApprove() {
		return userAccountantReturnApprove;
	}
	public void setUserAccountantReturnApprove(String userAccountantReturnApprove) {
		this.userAccountantReturnApprove = userAccountantReturnApprove;
	}
	public String getUserStockReturnApprove() {
		return userStockReturnApprove;
	}
	public void setUserStockReturnApprove(String userStockReturnApprove) {
		this.userStockReturnApprove = userStockReturnApprove;
	}
	public String getDtDeptReturnApprove() {
		return dtDeptReturnApprove;
	}
	public void setDtDeptReturnApprove(String dtDeptReturnApprove) {
		this.dtDeptReturnApprove = dtDeptReturnApprove;
	}
	public String getDtAccountantReturnApprove() {
		return dtAccountantReturnApprove;
	}
	public void setDtAccountantReturnApprove(String dtAccountantReturnApprove) {
		this.dtAccountantReturnApprove = dtAccountantReturnApprove;
	}
	public String getDtStockReturnApprove() {
		return dtStockReturnApprove;
	}
	public void setDtStockReturnApprove(String dtStockReturnApprove) {
		this.dtStockReturnApprove = dtStockReturnApprove;
	}
	public void setCoupon_loan_cd(String coupon_loan_cd) {
		this.coupon_loan_cd = coupon_loan_cd;
	}
	private String date_start_e;
	public String getDate_start_e() {
		return date_start_e;
	}
	public void setDate_start_e(String date_start_e) {
		this.date_start_e = date_start_e;
	}
	public String getDate_end_schedule_e() {
		return date_end_schedule_e;
	}
	public void setDate_end_schedule_e(String date_end_schedule_e) {
		this.date_end_schedule_e = date_end_schedule_e;
	}
	private String date_end_schedule_e;
	
	
	private String user_insert_name; 
	private String user_update_name; 
	private String cmpn_master_name; 
	private String cmpn_client_name; 
	private String cmpn_master_name_short; 
	private String cmpn_client_name_short;  
	private String dept_master_name; 
	private String dept_client_name;
	
	
	private String reasonNotAllow;
	
	private String user_approve_name;
	private String user_accountant_name;
	private String user_stock_name;
	
	private String dt_approve;
	private String dt_accountant;
	private String dt_stock;
	
	
	
	
	
	
	public String getDt_approve() {
		if(Common.isEmpty(dt_approve))
		{
			return "";
		}
		return dt_approve;
	}
	public void setDt_approve(String dt_approve) {
		this.dt_approve = dt_approve;
	}
	public String getDt_accountant() {
		if(Common.isEmpty(dt_accountant))
		{
			return "";
		}
		return dt_accountant;
	}
	public void setDt_accountant(String dt_accountant) {
		this.dt_accountant = dt_accountant;
	}
	public String getDt_stock() {
		if(Common.isEmpty(dt_stock))
		{
			return "";
		}
		return dt_stock;
	}
	public void setDt_stock(String dt_stock) {
		this.dt_stock = dt_stock;
	}
	public String getUser_approve_name() {
		if(Common.isEmpty(user_approve_name))
		{
			return "";
		}
		return user_approve_name;
	}
	public void setUser_approve_name(String user_approve_name) {
		this.user_approve_name = user_approve_name;
	}
	public String getUser_accountant_name() {
		if(Common.isEmpty(user_accountant_name))
		{
			return "";
		}
		return user_accountant_name;
	}
	public void setUser_accountant_name(String user_accountant_name) {
		this.user_accountant_name = user_accountant_name;
	}
	public String getUser_stock_name() {
		if(Common.isEmpty(user_stock_name))
		{
			return "";
		}
		return user_stock_name;
	}
	public void setUser_stock_name(String user_stock_name) {
		this.user_stock_name = user_stock_name;
	}
	public String getReasonNotAllow() {
		return reasonNotAllow;
	}
	public void setReasonNotAllow(String reasonNotAllow) {
		this.reasonNotAllow = reasonNotAllow;
	}
	public MovementCouponModel() {
		super();
	}
	public String getCoupon_cd() {
		return coupon_cd;
	}
	public void setCoupon_cd(String coupon_cd) {
		this.coupon_cd = coupon_cd;
	}
	public String getNumber_no() {
		return number_no;
	}
	public void setNumber_no(String number_no) {
		this.number_no = number_no;
	}
	public String getNo_cd() {
		return no_cd;
	}
	public void setNo_cd(String no_cd) {
		this.no_cd = no_cd;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getInsert_dt() {
		return insert_dt;
	}
	public void setInsert_dt(String insert_dt) {
		this.insert_dt = insert_dt;
	}
	public String getInsert_user() {
		return insert_user;
	}
	public void setInsert_user(String insert_user) {
		this.insert_user = insert_user;
	}
	public String getUpdate_dt() {
		return update_dt;
	}
	public void setUpdate_dt(String update_dt) {
		this.update_dt = update_dt;
	}
	public String getUpdate_user() {
		return update_user;
	}
	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}
	public CompanyModel getCmpn_master() {
		return cmpn_master;
	}
	public void setCmpn_master(CompanyModel cmpn_master) {
		this.cmpn_master = cmpn_master;
	}
	public CompanyModel getCmpn_client() {
		return cmpn_client;
	}
	public void setCmpn_client(CompanyModel cmpn_client) {
		this.cmpn_client = cmpn_client;
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
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getApprove_comment() {
		return approve_comment;
	}
	public void setApprove_comment(String approve_comment) {
		this.approve_comment = approve_comment;
	}
	public String getDate_start() {
		return date_start;
	}
	public void setDate_start(String date_start) {
		this.date_start = date_start;
	}
	public String getDate_end_schedule() {
		return date_end_schedule;
	}
	public void setDate_end_schedule(String date_end_schedule) {
		this.date_end_schedule = date_end_schedule;
	}
	public String getDate_end_real() {
		return date_end_real;
	}
	public void setDate_end_real(String date_end_real) {
		this.date_end_real = date_end_real;
	}
	public String getUser_insert_name() {
		return user_insert_name;
	}
	public void setUser_insert_name(String user_insert_name) {
		this.user_insert_name = user_insert_name;
	}
	public String getUser_update_name() {
		return user_update_name;
	}
	public void setUser_update_name(String user_update_name) {
		this.user_update_name = user_update_name;
	}
	public String getCmpn_master_name() {
		return cmpn_master_name;
	}
	public void setCmpn_master_name(String cmpn_master_name) {
		this.cmpn_master_name = cmpn_master_name;
	}
	public String getCmpn_client_name() {
		return cmpn_client_name;
	}
	public void setCmpn_client_name(String cmpn_client_name) {
		this.cmpn_client_name = cmpn_client_name;
	}
	public String getDept_master_name() {
		return dept_master_name;
	}
	public void setDept_master_name(String dept_master_name) {
		this.dept_master_name = dept_master_name;
	}
	public String getDept_client_name() {
		return dept_client_name;
	}
	public void setDept_client_name(String dept_client_name) {
		this.dept_client_name = dept_client_name;
	}
	public String getCmpn_master_name_short() {
		return cmpn_master_name_short;
	}
	public void setCmpn_master_name_short(String cmpn_master_name_short) {
		this.cmpn_master_name_short = cmpn_master_name_short;
	}
	public String getCmpn_client_name_short() {
		return cmpn_client_name_short;
	}
	public void setCmpn_client_name_short(String cmpn_client_name_short) {
		this.cmpn_client_name_short = cmpn_client_name_short;
	}
	
	
	private boolean isDeptApprove = false;
	private boolean isAccountantApprove = false;
	private boolean isStockApprrove = false;
	public boolean isDeptApprove() {
		return isDeptApprove;
	}
	public void setDeptApprove(boolean isDeptApprove) {
		this.isDeptApprove = isDeptApprove;
	}
	public boolean isAccountantApprove() {
		return isAccountantApprove;
	}
	public void setAccountantApprove(boolean isAccountantApprove) {
		this.isAccountantApprove = isAccountantApprove;
	}
	public boolean isStockApprrove() {
		return isStockApprrove;
	}
	public void setStockApprrove(boolean isStockApprrove) {
		this.isStockApprrove = isStockApprrove;
	}
	
	
	
	
	
	
	
}
