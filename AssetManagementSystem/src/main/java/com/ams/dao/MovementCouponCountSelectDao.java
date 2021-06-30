package com.ams.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.Department;

public class MovementCouponCountSelectDao {
	String cmpn_cd = "";
	public MovementCouponCountSelectDao(String cmpn_cd) {
		this.cmpn_cd = cmpn_cd;
	}
	
	
	
	public int getBorrowCouponCount() throws SQLException
	{
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		System.out.println(getSql());
		try {
			result = stmt.executeQuery(getSql());
			int count = 0;
			while (result.next()) {
				count = Integer.parseInt(result.getString("NUMBER"));
			}
			
			return count;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Integer) null;
		}
		
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		
		sql.append(" SELECT ISNULL(MAX(MOVEMENT_NUMBER),0) AS NUMBER ");
		sql.append(" FROM MOVEMENT");
		sql.append(" WHERE  ");
		sql.append(" 	MOVEMENT.DELETE_FG = '0'   ");
//		sql.append(" 	AND MOVEMENT.MOVEMENT_CMPN_MASTER  =").append("'"+ cmpn_cd +"'");
//		sql.append(" 	OR MOVEMENT.DELETE_FG = '0'");
//		sql.append(" 	AND MOVEMENT.MOVEMENT_CMPN_CLIENT  =").append("'"+ cmpn_cd +"'");
//		sql.append(" 	AND MOVEMENT.MOVEMENT_STICKER  =").append("'TH'");
//		sql.append(" 	OR MOVEMENT.DELETE_FG = '0'");
//		sql.append(" 	AND MOVEMENT.MOVEMENT_CMPN_CLIENT  =").append("'"+ cmpn_cd +"'");
//		sql.append(" 	AND MOVEMENT.MOVEMENT_STICKER  =").append("'T'");

		return sql.toString();
	}
}
