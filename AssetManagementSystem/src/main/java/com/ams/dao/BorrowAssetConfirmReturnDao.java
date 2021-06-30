package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ams.database.DatabaseConnection;
import com.ams.model.BorrowAssetModel;
import com.ams.util.Common;



public class BorrowAssetConfirmReturnDao {
	BorrowAssetModel bam = null;
	
	public BorrowAssetConfirmReturnDao()
	{
		
	}
	
	public BorrowAssetConfirmReturnDao(BorrowAssetModel bams)
	{
		this.bam = bams;
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
		
		sql.append(" UPDATE ");
		sql.append(" BORROW_ASSET");
		sql.append(" SET");	
		sql.append(" 	STATUS = '5'");
		sql.append(" 	,USER_CONFIRM = ").append("'"+bam.getUserConfirm()+"'");
		sql.append(" 	,CONFIRM_DT = ").append("'"+bam.getConfirmDt()+"'");
		sql.append(" 	WHERE");
		sql.append(" 	BORROW_CD = ").append("'"+bam.getId()+"'");
		
		return sql.toString();
	}

}
