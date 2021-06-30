package com.ams.model;

public class UserModel {
	public String name;
	public String employee_cd;
	public String department;
	public String company_cd;
	public String company_name;
	private String date_expire;
	private String time_expire;
	public String getDate_expire() {
		return date_expire;
	}

	public void setDate_expire(String date_expire) {
		this.date_expire = date_expire;
	}

	public String getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}

	public String getUser_Insert() {
		return user_Insert;
	}

	public void setUser_Insert(String user_Insert) {
		this.user_Insert = user_Insert;
	}

	public String getInsert_Dt() {
		return insert_Dt;
	}

	public void setInsert_Dt(String insert_Dt) {
		this.insert_Dt = insert_Dt;
	}

	public String getUser_Update() {
		return user_Update;
	}

	public void setUser_Update(String user_Update) {
		this.user_Update = user_Update;
	}

	public String getUpdate_Dt() {
		return update_Dt;
	}

	public void setUpdate_Dt(String update_Dt) {
		this.update_Dt = update_Dt;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword_Default() {
		return password_Default;
	}

	public void setPassword_Default(String password_Default) {
		this.password_Default = password_Default;
	}
	public String user_Insert;
	public String insert_Dt;
	public String user_Update;
	public String update_Dt;
	public String role;
	public String password_Default;
	
	
	
	
	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String phone;
	public String getEmployee_cd() {
		return employee_cd;
	}

	public void setEmployee_cd(String employee_cd) {
		this.employee_cd = employee_cd;
	}

	public String getCompany_cd() {
		return company_cd;
	}

	public void setCompany_cd(String company_cd) {
		this.company_cd = company_cd;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	public String position;
	public String pasword;
	public String getName() {
		return name;
	}
	
	public  UserModel() {
		
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmployment_CD() {
		return employee_cd;
	}
	public void setEmployment_CD(String usermame) {
		this.employee_cd = usermame;
	}
	public String getPasword() {
		return pasword;
	}
	public void setPasword(String pasword) {
		this.pasword = pasword;
	}

}
