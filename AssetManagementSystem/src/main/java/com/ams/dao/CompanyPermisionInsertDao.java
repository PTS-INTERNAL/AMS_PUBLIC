package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ams.database.DatabaseConnection;
import com.ams.model.CompanyPermision;
import com.ams.util.Common;

public class CompanyPermisionInsertDao {
	
	CompanyPermision company;
	
	public CompanyPermisionInsertDao(CompanyPermision company)
	{
		this.company = company;
	}
		
	
	
	public int excute() throws SQLException
	{
		int result = 0;
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSQL());
		//System.out.println(getSQL());
		
		sqlStatement.setString(1,Common.getDateCurrent("YYYYMMddHHmmSS"));
		sqlStatement.setString(2,company.getCompany().getCompany_user());
		sqlStatement.setString(3,company.getCompany().getCompany_cd());
		//System.out.println(bam.getName());
		sqlStatement.setString(4,company.getValueCheck());
		result = sqlStatement.executeUpdate();
		
		return result;
	}
	
	public String getSQL()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" INSERT INTO");
		sql.append(" COMPANY_PERMISSION");
		sql.append(" (");
		sql.append(" 	CMPN_PER_CD");
		sql.append(" 	,EMPLOYEE_CD");
		sql.append(" 	,CMPN_CD");
		sql.append(" 	,DELETE_FG");
		sql.append(" )");
		sql.append(" VALUES");
		sql.append(" (");
		sql.append(" 	?");//BORROW_CD
		sql.append(" 	,?");//LOAN_CMPN_CD
		sql.append(" 	,?");//LOAN_DEPT
		sql.append(" 	,?");//ASSET_RFID
		sql.append(" )");
		
		return sql.toString();
	}
}
