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
import com.ams.model.InventorySessionModel;
import com.ams.model.UserModel;



public class InventorySessionDeleteDao {

	InventorySessionModel ivn;
	
	public InventorySessionDeleteDao(InventorySessionModel ivn)
	{
		this.ivn = ivn;
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

		sql.append(" INVENTORY_SESSION");
		sql.append(" WHERE");
		
			
		sql.append("  INVENTORY_SESSION_CD = ").append("'" + ivn.getInvenotrySessionCD() + "'");
			

		return sql.toString();
	}
}
