package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.CompanyForm;
import com.ams.util.Common;



public class CompanyInsertDao {
	CompanyForm form =new CompanyForm();
	public CompanyInsertDao(CompanyForm form)
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
			sqlStatement.setString(1,Common.getDateCurrent("YYYYMMddHHmmSS"));//COMPANY_CD
			sqlStatement.setString(2,form.getName());//COMPANY_NAME
			sqlStatement.setString(3,form.getAddress());//COMPANY_ADDRESS
			sqlStatement.setString(4,form.getFile_name());//COMPANY_LOGO
			sqlStatement.setString(5,form.getEmail());//COMPANY_EMAIL
			sqlStatement.setString(6,form.getPhone());//COMPANY_PHONE
			sqlStatement.setString(7,form.getTax());//COMPANY_TAX
			sqlStatement.setString(8,form.getWebsite());//COMPANY_WEBISTE
			sqlStatement.setString(9,form.getLevel());//COMPANY_LEVEL
			sqlStatement.setString(10,form.getDesciption());//COMPANY_DESCRIPTION
			sqlStatement.setString(11,form.getUserInsert());//USER_INSTERT
			sqlStatement.setString(12,Common.getDateCurrent("YYYYMMDD"));//INSTER_DT
			sqlStatement.setString(13,"");//USER_UPDATE is EMPTY
			sqlStatement.setString(14,"");//USER_UPDATE is EMPTY
			sqlStatement.setString(15,form.getShortName());//SHORTNAME
			sqlStatement.setString(16,"0");//SHORTNAME
	//System.out.println(sqlStatement.getParameterMetaData());
			result = sqlStatement.executeUpdate();
		
		
		return result;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" INSERT INTO");
		sql.append(" COMPANY");
		sql.append(" (");
		sql.append(" 	CMPN_CD");
		sql.append(" 	,CMPN_NAME");
		sql.append(" 	,CMPN_ADDRESS");
		sql.append(" 	,CMPN_LOGO");
		sql.append(" 	,CMPN_EMAIL");
		sql.append(" 	,CMPN_PHONE");
		sql.append(" 	,CMPN_TAX");
		sql.append(" 	,CMPN_WEBSITE");
		sql.append(" 	,CMPN_LEVEL");
		sql.append(" 	,CMPN_DESCRIPTION");
		sql.append(" 	,USER_INSERT");
		sql.append(" 	,INSERT_DT");
		sql.append(" 	,USER_UPDATE");
		sql.append(" 	,LAST_UPDATE_DT");
		sql.append(" 	,CMPN_SHORT_NAME");
		sql.append(" 	,DELETE_FG ");
		sql.append(" )");
		sql.append(" VALUES");
		sql.append(" (");
		sql.append(" 	 ?");//COMPANY_CD
		sql.append(" 	,?");//COMPANY_NAME
		sql.append(" 	,?");//COMPANY_ADDRESS
		sql.append(" 	,?");//COMPANY_LOGO
		sql.append(" 	,?");//COMPANY_EMAIL
		sql.append(" 	,?");//COMPANY_PHONE
		sql.append(" 	,?");//COMPANY_TAX
		sql.append(" 	,?");//COMPANY_WEBISTE
		sql.append(" 	,?");//COMPANY_LEVEL
		sql.append(" 	,?");//COMPANY_DESCRIPTION
		sql.append(" 	,?");//USER_INSTERT
		sql.append(" 	,?");//INSTER_DT
		sql.append(" 	,?");//USER_UPDATE
		sql.append(" 	,?");//LAST_UPDATE_DT
		sql.append(" 	,?");//SHORT_NAME
		sql.append(" 	,?");//SHORT_NAME
		sql.append(" )");
		
		return sql.toString();
	}

}
