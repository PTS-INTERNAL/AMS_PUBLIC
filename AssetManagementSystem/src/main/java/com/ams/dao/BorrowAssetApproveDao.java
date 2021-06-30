package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ams.database.DatabaseConnection;
import com.ams.model.BorrowAssetModel;
import com.ams.util.Common;



public class BorrowAssetApproveDao {
	BorrowAssetModel bam = null;
	
	public BorrowAssetApproveDao()
	{
		
	}
	
	public BorrowAssetApproveDao(BorrowAssetModel bams)
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
		sql.append(" 	STATUS =").append("'"+bam.getStatus()+"'");
		sql.append(" 	,USER_APPROVE = ").append("'"+bam.getUserApprove()+"'");
		sql.append(" 	,APPROVE_DT = ").append("'"+bam.getApproveDt()+"'");
		sql.append(" 	,REASON_NOT_ALLOW = ").append("'"+bam.getReason_not_allow()+"'");
		sql.append(" 	WHERE");
		sql.append(" 	BORROW_CD = ").append("'"+bam.getId()+"'");
		
		return sql.toString();
	}

}
