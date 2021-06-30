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



public class CompanyUpdateDeleteDao {

	private String cmpn_cd;

	
	public CompanyUpdateDeleteDao( String cmpn_cd)
	{
		this.cmpn_cd = cmpn_cd;
	}
	
	public int excute() throws SQLException
	{
		//Kết nối cơ sở dữ liệu
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSql());
		int result = 0;
		//---
		//System.out.println(getSql());
		//---
		result = sqlStatement.executeUpdate();
		
		return result;
		
		
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE COMPANY ");
		sql.append(" SET ");
		sql.append("  DELETE_FG = '1'");
		sql.append(" WHERE ");
		sql.append(" CMPN_CD =  ").append("'"+cmpn_cd+"'");;
		
		return sql.toString();
	}
}
