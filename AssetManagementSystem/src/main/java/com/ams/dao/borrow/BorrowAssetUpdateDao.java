package com.ams.dao.borrow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ams.database.DatabaseConnection;
import com.ams.model.BorrowAssetModel;
import com.ams.util.Common;



public class BorrowAssetUpdateDao {
	BorrowAssetModel bam = null;
	
	public BorrowAssetUpdateDao()
	{
		
	}
	
	public BorrowAssetUpdateDao(BorrowAssetModel bams)
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
		
		
		sqlStatement.setString(1,bam.getCmpn_master().getCompany_cd());
		sqlStatement.setString(2,bam.getDept_master().getDept_cd());
		//System.out.println(bam.getName());
		sqlStatement.setString(3,bam.getAsset().getId());
		sqlStatement.setString(4,bam.getAsset().getRFID());
		sqlStatement.setString(5,bam.getCmpn_client().getCompany_cd());
		sqlStatement.setString(6,bam.getDept_client().getDept_cd());
		sqlStatement.setString(7,bam.getDate_start());
		sqlStatement.setString(8,bam.getDate_end());
		sqlStatement.setString(9,bam.getDate_pay());
		sqlStatement.setString(10,bam.getNote());
		sqlStatement.setString(11,bam.getReason());
		sqlStatement.setString(12,bam.getUserUpdate());
		sqlStatement.setString(13,bam.getUpdateDt());
		sqlStatement.setString(14,bam.getStatus());
		sqlStatement.setString(15,bam.getNumber_on());
		sqlStatement.setString(16,bam.getBorrowCoupon().getCoupon_cd());
		sqlStatement.setString(17,bam.getDelete_fg());
		sqlStatement.setString(18,bam.getAsseseries());
		sqlStatement.setString(19,bam.getId());
		
		result = sqlStatement.executeUpdate();
		
		return result;
	}
	
	public String getSQL()
	{
StringBuilder sql = new StringBuilder();
		
		sql.append(" UPDATE ");
		sql.append(" BORROW_ASSET");
		sql.append(" SET");
		sql.append(" 	CMPN_MASTER= ?");
		sql.append(" 	,DEPT_MASTER= ?");
		sql.append(" 	,ASSET_CD= ?");
		sql.append(" 	,ASSET_RFID= ?");
		sql.append(" 	,CMPN_CLIENT= ?");
		sql.append(" 	,DEPT_CLIENT= ?");
		sql.append(" 	,DATE_START= ?");
		sql.append(" 	,DATE_END= ?");
		sql.append(" 	,DATE_PAY= ?");
		sql.append(" 	,NOTE= ?");
		sql.append(" 	,REASON= ?");
		sql.append(" 	,USER_UPDATE= ?");
		sql.append(" 	,UPDATE_DT= ?");
		sql.append(" 	,STATUS= ?");
		sql.append(" 	,NUMBER_ON= ?");
		sql.append(" 	,COUPON_CD= ?");
		sql.append(" 	,DELETE_FG= ?"); 
		sql.append(" 	,ASSESSERIES= ?");
		sql.append(" WHERE 	BORROW_CD = ?");
		
		
		return sql.toString();
	}

}
