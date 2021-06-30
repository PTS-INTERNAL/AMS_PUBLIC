package com.ams.dao.borrow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ams.database.DatabaseConnection;
import com.ams.model.BorrowAssetModel;
import com.ams.util.Common;



public class BorrowAssetUpdateDeleteDao {
	String  id = null;
	
	public BorrowAssetUpdateDeleteDao()
	{
		
	}
	
	public BorrowAssetUpdateDeleteDao(String id)
	{
		this.id = id;
	}
	
	public int excute() throws SQLException
	{
		int result = 0;
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSQL());
		//System.out.println(getSQL());
		
		
		sqlStatement.setString(1,"1");	
		sqlStatement.setString(2,id);
		
		result = sqlStatement.executeUpdate();
		
		return result;
	}
	
	public String getSQL()
	{
StringBuilder sql = new StringBuilder();
		
		sql.append(" UPDATE ");
		sql.append(" BORROW_ASSET");
		sql.append(" SET");
		sql.append(" 	DELETE_FG= ?"); 
		sql.append(" WHERE 	BORROW_CD = ?");
		
		
		return sql.toString();
	}

}
