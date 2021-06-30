package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.CompanyForm;
import com.ams.util.Common;



public class CompanyUpdateDao {
	CompanyForm form =new CompanyForm();
	public CompanyUpdateDao(CompanyForm form)
	{
		this.form = form;
	}
	
	public int excute() throws SQLException
	{
		int result = 0;
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSql());
		//System.out.println(getSql());
			
			sqlStatement.setString(1,form.getName());//COMPANY_NAME
			sqlStatement.setString(2,form.getAddress());//COMPANY_ADDRESS
			if(Common.isNotEmpty(form.getFile_name()))
			{
			sqlStatement.setString(3,form.getFile_name());//COMPANY_LOGO
			sqlStatement.setString(4,form.getEmail());//COMPANY_EMAIL
			sqlStatement.setString(5,form.getPhone());//COMPANY_PHONE
			sqlStatement.setString(6,form.getTax());//COMPANY_TAX
			sqlStatement.setString(7,form.getWebsite());//COMPANY_WEBISTE
			sqlStatement.setString(8,form.getLevel());//COMPANY_LEVEL
			sqlStatement.setString(9,form.getDesciption());//COMPANY_DESCRIPTION
			sqlStatement.setString(10,form.getUserInsert());//USER_INSTERT
			sqlStatement.setString(11,Common.getDateCurrent("YYYYMMDD"));//INSTER_DT
			sqlStatement.setString(12,form.getShortName());//SHORTNAME
			sqlStatement.setString(13,form.getCmpn_cd());//COMPANY_CD
			} else {
				sqlStatement.setString(3,form.getEmail());//COMPANY_EMAIL
				sqlStatement.setString(4,form.getPhone());//COMPANY_PHONE
				sqlStatement.setString(5,form.getTax());//COMPANY_TAX
				sqlStatement.setString(6,form.getWebsite());//COMPANY_WEBISTE
				sqlStatement.setString(7,form.getLevel());//COMPANY_LEVEL
				sqlStatement.setString(8,form.getDesciption());//COMPANY_DESCRIPTION
				sqlStatement.setString(9,form.getUserInsert());//USER_INSTERT
				sqlStatement.setString(10,Common.getDateCurrent("YYYYMMDD"));//INSTER_DT
				sqlStatement.setString(11,form.getShortName());//SHORTNAME
				sqlStatement.setString(12,form.getCmpn_cd());//COMPANY_CD
			}
			result = sqlStatement.executeUpdate();
		
		
		return result;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" UPDATE ");
		sql.append(" COMPANY");
		sql.append(" SET ");
		
		sql.append(" 	CMPN_NAME = ? ");
		sql.append(" 	,CMPN_ADDRESS = ? ");
		if(Common.isNotEmpty(form.getFile_name()))
		{
			sql.append(" 	,CMPN_LOGO = ? ");
		}
		sql.append(" 	,CMPN_EMAIL = ? ");
		sql.append(" 	,CMPN_PHONE = ? ");
		sql.append(" 	,CMPN_TAX = ? ");
		sql.append(" 	,CMPN_WEBSITE = ? ");
		sql.append(" 	,CMPN_LEVEL = ? ");
		sql.append(" 	,CMPN_DESCRIPTION = ? ");
		sql.append(" 	,USER_UPDATE  = ? ");
		sql.append(" 	,LAST_UPDATE_DT  = ? ");
		sql.append(" 	,CMPN_SHORT_NAME  = ? ");
		sql.append(" WHERE 	CMPN_CD = ? ");
		
		
		return sql.toString();
	}

}
