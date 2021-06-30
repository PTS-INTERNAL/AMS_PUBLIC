package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.User;

import com.ams.database.DatabaseConnection;
import com.ams.model.UserModel;



public class UserDeleteDao {

	String  Cd;
	
	public UserDeleteDao(String  Cd)
	{
		this.Cd = Cd;
	}
	
	
	
	public int excute() throws SQLException
	{
		//Kết nối cơ sở dữ liệu
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		
		PreparedStatement sqlStatement = connectString.prepareStatement(getSql());
		//---
		//System.out.println(getSql());
		//---
		
		
		
		return sqlStatement.executeUpdate();
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append("DELETE ");

		sql.append(" USER_SYSTEM");
		sql.append(" WHERE");
		
			
		sql.append("  USER_EMPLOYEE_CD = ").append("'" + Cd + "'");
			

		return sql.toString();
	}
}
