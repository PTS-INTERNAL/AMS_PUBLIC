package com.ams.model;

public class UserDeptPermissionModel {
	
	private String cmpn_cd;
	private String dept_name;
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getCmpn_cd() {
		return cmpn_cd;
	}
	public void setCmpn_cd(String cmpn_cd) {
		this.cmpn_cd = cmpn_cd;
	}
	private String perCd;
	private String user_cd;
	private String dept_cd;
	private String value;
	public String getPerCd() {
		return perCd;
	}
	public void setPerCd(String perCd) {
		this.perCd = perCd;
	}
	public String getUser_cd() {
		return user_cd;
	}
	public void setUser_cd(String user_cd) {
		this.user_cd = user_cd;
	}
	public String getDept_cd() {
		return dept_cd;
	}
	public void setDept_cd(String dept_cd) {
		this.dept_cd = dept_cd;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	

}
