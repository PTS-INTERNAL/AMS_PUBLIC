package com.ams.model;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;

public class CompanyForm {
	private String name;
	private String file_name;
	private String address;
	private String tax;
	private String phone;
	private String email;
	private String website;
	private String level;
	private String desciption;
	private String userInsert;
	private String shortName;
	private String cmpn_cd;
	public String getCmpn_cd() {
		return cmpn_cd;
	}

	public void setCmpn_cd(String cmpn_cd) {
		this.cmpn_cd = cmpn_cd;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
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
	private String insertDT;
	
	public CompanyForm() {
		super();
	}
	
	public CompanyForm(HttpServletRequest request) throws UnsupportedEncodingException {
		super();
		request.setCharacterEncoding("UTF-8");
		this.name = request.getParameter("name").trim();
		//this.file_name = request.getParameter("file-name").trim();
		this.address = request.getParameter("address").trim();
		this.tax = request.getParameter("tax").trim();
		this.phone = request.getParameter("phone").trim();
		this.email = request.getParameter("email").trim();
		this.website = request.getParameter("website").trim();
	//	this.level = request.getParameter("level").trim();
		this.desciption = request.getParameter("description").trim();
		this.shortName = request.getParameter("shortName").trim();
		HttpSession session_en=request.getSession();  
		this.userInsert =  session_en.getAttribute("NAME").toString().trim();
		
		
	}
	public CompanyForm(String name, String file_name, String address, String tax, String phone, String email,
			String website, String value, String desciption) {
		super();
		this.name = name;
		this.file_name = file_name;
		this.address = address;
		this.tax = tax;
		this.phone = phone;
		this.email = email;
		this.website = website;
		this.level = level;
		this.desciption = desciption;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String value) {
		this.level = value;
	}
	public String getDesciption() {
		return desciption;
	}
	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
	
	

}
