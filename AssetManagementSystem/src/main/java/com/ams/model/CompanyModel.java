package com.ams.model;

public class CompanyModel {
	
	private String company_cd;
	private String company_name;
	private String company_address;
	private String company_shortname;
	private String company_website;
	private String company_fg;
	private String company_logo;
	private String company_description;
	private String company_tax_number;
	private String company_email;
	private String company_user;
	
	public String getCompany_user() {
		return company_user;
	}
	public void setCompany_user(String company_user) {
		this.company_user = company_user;
	}
	public String getCompany_phone() {
		return company_phone;
	}
	public void setCompany_phone(String company_phone) {
		this.company_phone = company_phone;
	}
	private String company_phone;
	
	public String getCompany_website() {
		return company_website;
	}
	public void setCompany_website(String company_website) {
		this.company_website = company_website;
	}
	public String getCompany_fg() {
		return company_fg;
	}
	public void setCompany_fg(String company_fg) {
		this.company_fg = company_fg;
	}
	public String getCompany_logo() {
		return company_logo;
	}
	public void setCompany_logo(String company_logo) {
		this.company_logo = company_logo;
	}
	
	
	
	
	public String getCompany_file_image() {
		return company_file_image;
	}
	public String getCompany_description() {
		return company_description;
	}
	public void setCompany_description(String company_description) {
		this.company_description = company_description;
	}
	public String getCompany_tax_number() {
		return company_tax_number;
	}
	public void setCompany_tax_number(String company_tax_number) {
		this.company_tax_number = company_tax_number;
	}
	public String getCompany_email() {
		return company_email;
	}
	public void setCompany_email(String company_email) {
		this.company_email = company_email;
	}
	public void setCompany_file_image(String company_file_image) {
		this.company_file_image = company_file_image;
	}
	private String company_delete;
	private String company_file_image;
	
	public String getCompany_delete() {
		return company_delete;
	}
	public void setCompany_delete(String company_delete) {
		this.company_delete = company_delete;
	}
	
	public String getCompany_cd() {
		return company_cd;
	}
	public void setCompany_cd(String company_cd) {
		this.company_cd = company_cd;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCompany_address() {
		return company_address;
	}
	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}
	public String getCompany_shortname() {
		return company_shortname;
	}
	public void setCompany_shortname(String company_shortname) {
		this.company_shortname = company_shortname;
	}

	
	public CompanyModel() {
		super();
	}
	public CompanyModel(String company_cd, String company_name, String company_address) {
		super();
		this.company_cd = company_cd;
		this.company_name = company_name;
		this.company_address = company_address;
	}
}
