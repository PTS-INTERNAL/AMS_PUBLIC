package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ams.database.DatabaseConnection;
import com.ams.model.BorrowAssetModel;
import com.ams.util.Common;



public class BorrowAssetInsertDao {
	BorrowAssetModel bam = null;
	
	public BorrowAssetInsertDao()
	{
		
	}
	
	public BorrowAssetInsertDao(BorrowAssetModel bams)
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
		
		sqlStatement.setString(1,Common.getDateCurrent("YYYYMMddHHmmSS"));
		sqlStatement.setString(2,bam.getCmpn_master().getCompany_cd());
		sqlStatement.setString(3,bam.getDept_master().getDept_cd());
		//System.out.println(bam.getName());
		sqlStatement.setString(4,bam.getAsset().getId());
		sqlStatement.setString(5,bam.getAsset().getRFID());
		sqlStatement.setString(6,bam.getCmpn_client().getCompany_cd());
		sqlStatement.setString(7,bam.getDept_client().getDept_cd());
		sqlStatement.setString(8,bam.getDate_start());
		sqlStatement.setString(9,bam.getDate_end());
		sqlStatement.setString(10,bam.getDate_pay());
		sqlStatement.setString(11,bam.getNote());
		sqlStatement.setString(12,bam.getReason());
		sqlStatement.setString(13,bam.getUserInsert());
		sqlStatement.setString(14,bam.getInsertDt());
		sqlStatement.setString(15,bam.getStatus());
		sqlStatement.setString(16,bam.getNumber_on());
		sqlStatement.setString(17,bam.getBorrowCoupon().getCoupon_cd());
		sqlStatement.setString(18,bam.getDelete_fg());
		sqlStatement.setString(19,bam.getAsseseries());
		
		result = sqlStatement.executeUpdate();
		
		return result;
	}
	
	public String getSQL()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" INSERT INTO");
		sql.append(" BORROW_ASSET");
		sql.append(" (");
		sql.append(" 	BORROW_CD");
		sql.append(" 	,CMPN_MASTER");
		sql.append(" 	,DEPT_MASTER");
		sql.append(" 	,ASSET_CD");
		sql.append(" 	,ASSET_RFID");
		sql.append(" 	,CMPN_CLIENT");
		sql.append(" 	,DEPT_CLIENT");
		sql.append(" 	,DATE_START");
		sql.append(" 	,DATE_END");
		sql.append(" 	,DATE_PAY");
		sql.append(" 	,NOTE");
		sql.append(" 	,REASON");
		sql.append(" 	,USER_INSERT");
		sql.append(" 	,INSERT_DT");
		sql.append(" 	,STATUS");
		sql.append(" 	,NUMBER_ON");
		sql.append(" 	,COUPON_CD");
		sql.append(" 	,DELETE_FG"); 
		sql.append(" 	,ASSESSERIES");
		sql.append(" )");
		sql.append(" VALUES");
		sql.append(" (");
		sql.append(" 	?");//BORROW_CD
		sql.append(" 	,?");//LOAN_CMPN_CD
		sql.append(" 	,?");//LOAN_DEPT
		sql.append(" 	,?");//ASSET_RFID
		sql.append(" 	,?");//BORROW_CMPN_CD
		sql.append(" 	,?");//BORROW_DEPT
		sql.append(" 	,?");//DATE_START
		sql.append(" 	,?");//DATE_END
		sql.append(" 	,?");//REASON
		sql.append(" 	,?");//USER_TS
		sql.append(" 	,?");//INSERT_DT
		sql.append(" 	,?");//STATUS
		sql.append(" 	,?");//INSERT_DT
		sql.append(" 	,?");//STATUS
		sql.append(" 	,?");//STATUS
		sql.append(" 	,?");//STATUS
		sql.append(" 	,?");//COUPON_CD
		sql.append(" 	,?");//DELETE_FG
		sql.append(" 	,?");//DELETE_FG
		sql.append(" )");
		
		return sql.toString();
	}

}
