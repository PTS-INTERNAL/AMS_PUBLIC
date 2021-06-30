package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.CompanyForm;
import com.ams.util.Common;



public class SystemControlUpdateDao {
	// TODO Auto-generated constructor stub
	String id = "";
	String value = "";
	public SystemControlUpdateDao(String ID , String value)
	{
		this.id = ID;
		this.value = value;
	}
	
	public int excute() throws SQLException
	{
		int result = 0;
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSql());
		
			result = sqlStatement.executeUpdate();
		
		
		return result;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" UPDATE ");
		sql.append(" SYSTEM_CONTROL");
		sql.append(" SET ");	
		sql.append(" 	CONTROL_VALUE =").append("'"+this.value+"'");
		sql.append(" WHERE 	CONTROL_TX = ").append("'"+this.id+"'");
		
		
		return sql.toString();
	}

}
