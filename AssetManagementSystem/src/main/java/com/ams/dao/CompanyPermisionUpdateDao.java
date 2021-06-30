package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ams.database.DatabaseConnection;
import com.ams.model.CompanyPermision;
import com.ams.util.Common;

public class CompanyPermisionUpdateDao {
	
	CompanyPermision company;
	
	public CompanyPermisionUpdateDao(CompanyPermision company)
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
		result = sqlStatement.executeUpdate();
		
		return result;
	}
	
	public String getSQL()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" UPDATE");
		sql.append(" COMPANY_PERMISSION");
		sql.append(" SET");
		sql.append(" 	DELETE_FG=").append("'"+company.getValueCheck()+"'");
		sql.append(" WHERE");
	
		sql.append(" 	CMPN_CD=").append("'"+company.getCompany().getCompany_cd()+"'");
		sql.append(" 	AND EMPLOYEE_CD=").append("'"+company.getCompany().getCompany_user()+"'");
		
		
		return sql.toString();
	}
}
