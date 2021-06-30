package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ams.database.DatabaseConnection;
import com.ams.model.LoanAssetModel;
import com.ams.util.Common;



public class LoanAssetConfirmDao {
	LoanAssetModel bam = null;
	
	public LoanAssetConfirmDao()
	{
		
	}
	
	public LoanAssetConfirmDao(LoanAssetModel bams)
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
		sql.append(" LOAN_ASSET");
		sql.append(" SET");	
		sql.append(" 	STATUS = ").append("'"+bam.getStatus()+"'");
		sql.append(" 	,USER_CONFIRM = ").append("'"+bam.getUserConfirm()+"'");
		sql.append(" 	,CONFIRM_DT = ").append("'"+bam.getConfirmDt()+"'");
		sql.append(" 	,REASON_NOT_ALLOW = ").append("'"+bam.getReason_not_allow()+"'");
		sql.append(" 	WHERE");
		sql.append(" 	LOAN_CD = ").append("'"+bam.getId()+"'");
		
		return sql.toString();
	}

}
