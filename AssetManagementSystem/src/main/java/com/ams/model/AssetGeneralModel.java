package com.ams.model;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.ams.util.Common;


public class AssetGeneralModel {
	
	private String group_name;
	private String RFID;
	private String Name;
	private String Model;
	private String Series;
	private String Department_name;
	private String Accountant_CD;
	private String DateStart;
	private String  Price;
	private String maintaince;
	private String Note;
	private String original;	
	
	private String id;
	private String Company_CD;
	private String group_id;
	private String Department_cd;
	private String DateEnd;
	
	private String user_insert_cd;
	private String user_update_cd;
	private String insert_dt;
	private String update_dt;
	
	private String mode;
	
	
	
	
	
	private String delete_Fg;


	private String PriceStart;
	private String PriceEnd;
	private String DateStart_Start;
	private String DateStart_End;
	
	private String status;
	
	private String inline;
	private String customer; 
	private String contract;
	
	
	private String STT;
	
	public String getSTT() {
		return STT;
	}
	public void SetSTT(String st) {
		this.STT = st;
	}
	
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public String getInline() {
		return inline;
	}
	public void setInline(String inline) {
		this.inline = inline;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPriceStart() {
		return PriceStart;
	}
	public void setPriceStart(String priceStart) {
		PriceStart = priceStart;
	}
	public String getPriceEnd() {
		return PriceEnd;
	}
	public void setPriceEnd(String priceEnd) {
		PriceEnd = priceEnd;
	}

	public String getDelete_Fg() {
		return delete_Fg;
	}

	public String getDateStart_Start() {
		return DateStart_Start;
	}
	public void setDateStart_Start(String dateStart_Start) {
		DateStart_Start = dateStart_Start;
	}
	public String getDateStart_End() {
		return DateStart_End;
	}
	public void setDateStart_End(String dateStart_End) {
		DateStart_End = dateStart_End;
	}

	public void setDelete_Fg(String delete_Fg) {
		this.delete_Fg = delete_Fg;
	}

	public String getOriginal() {
		return original;
	}



	public void setOriginal(String original) {
		this.original = original;
	}



	public String getMaintaince() {
		return maintaince;
	}



	public void setMaintaince(String maintaince) {
		this.maintaince = maintaince;
	}



	public String getMode() {
		return mode;
	}



	public void setMode(String mode) {
		this.mode = mode;
	}



	public AssetGeneralModel(HttpServletRequest request) throws UnsupportedEncodingException, ParseException
	{
		
	}
	
	
	
	public String getUser_insert_cd() {
		return user_insert_cd;
	}



	public void setUser_insert_cd(String user_insert_cd) {
		this.user_insert_cd = user_insert_cd;
	}



	public String getUser_update_cd() {
		return user_update_cd;
	}



	public void setUser_update_cd(String user_update_cd) {
		this.user_update_cd = user_update_cd;
	}



	public String getInsert_dt() {
		return insert_dt;
	}



	public void setInsert_dt(String insert_dt) {
		this.insert_dt = insert_dt;
	}



	public String getUpdate_dt() {
		return update_dt;
	}



	public void setUpdate_dt(String update_dt) {
		this.update_dt = update_dt;
	}



	public String getRFID() {
		return RFID;
	}
	public void setRFID(String rFID) {
		RFID = rFID;
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getModel() {
		return Model;
	}
	public void setModel(String model) {
		Model = model;
	}
	public String getSeries() {
		return Series;
	}
	public void setSeries(String series) {
		Series = series;
	}
	
	public String getAccountant_CD() {
		return Accountant_CD;
	}
	public void setAccountant_CD(String accountant_CD) {
		Accountant_CD = accountant_CD;
	}
	public String getDateStart() {
		return DateStart;
	}
	public void setDateStart(String dateStart) {
		DateStart = dateStart;
	}
	public String getDepartment_cd() {
		return Department_cd;
	}



	public void setDepartment_cd(String department_cd) {
		Department_cd = department_cd;
	}



	public String getGroup_id() {
		return group_id;
	}



	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}



	public String getGroup_name() {
		return group_name;
	}



	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}



	public String getDepartment_name() {
		return Department_name;
	}



	public void setDepartment_name(String department_name) {
		Department_name = department_name;
	}



	public String getDateEnd() {
		return DateEnd;
	}



	public void setDateEnd(String dateEnd) {
		DateEnd = dateEnd;
	}



	public String getPrice() {
		return Price;
	}
	public void setPrice(String price) {
		Price = price;
	}
	public String getNote() {
		return Note;
	}
	public void setNote(String note) {
		Note = note;
	}
	public String getId() {
		return id;
	}
	public void setId(String Id) {
		id = Id;
	}
	public String getCompany_CD() {
		return Company_CD;
	}



	public void setCompany_CD(String company_CD) {
		Company_CD = company_CD;
	}
	public AssetGeneralModel(String rFID, String name, String model, String series, String department, String accountant_CD,
			String dateStart, String price, String note) {
		super();
		RFID = rFID;
		Name = name;
		Model = model;
		Series = series;
	
		Accountant_CD = accountant_CD;
		DateStart = dateStart;
		Price = price;
		Note = note;
	}
	public AssetGeneralModel() {
		super();
	}
	
	public boolean isEmpty()
	{
		if(Common.isEmpty(this.RFID) && Common.isEmpty(this.Series))
			return true;
		else
			return false;
	}
	
	public String getEqual()
	{
		return this.RFID+this.Name+this.Accountant_CD+this.Model+this.Series;
	}
	
	
	
	

}
