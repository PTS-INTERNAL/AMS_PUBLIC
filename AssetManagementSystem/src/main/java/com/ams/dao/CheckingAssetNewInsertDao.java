package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ams.database.DatabaseConnection;
import com.ams.model.CheckingAssetNew;
import com.ams.util.Common;



public class CheckingAssetNewInsertDao {
	
		CheckingAssetNew checkingAsset = null;
	
		public CheckingAssetNewInsertDao(CheckingAssetNew checkingAsset)
		{
			this.checkingAsset = checkingAsset;
		}
		
		public int excute() throws SQLException
		{
			int result = 0;
			
			DatabaseConnection conn = new DatabaseConnection();
			Connection connectString = conn.getConnection();
			PreparedStatement sqlStatement = connectString.prepareStatement(getSql());
			//System.out.println(getSql());
				sqlStatement.setString(1,Common.getDateCurrent("YYYYMMddHHmmSS"));//ASSET_CD
				sqlStatement.setString(2, checkingAsset.getRfid());
				sqlStatement.setString(3, checkingAsset.getName());
				sqlStatement.setString(4, checkingAsset.getChecking_session());
				sqlStatement.setString(5, checkingAsset.getDepartment());
				sqlStatement.setString(6, checkingAsset.getReason());
				sqlStatement.setString(7, checkingAsset.getNote());
				sqlStatement.setString(8, checkingAsset.getDateCreate());
				sqlStatement.setString(9, checkingAsset.getDateCreate());
				sqlStatement.setString(10,checkingAsset.getUser());
				sqlStatement.setString(11,checkingAsset.getStatus());
				sqlStatement.setString(12,checkingAsset.getCmpn_cd());
	
				result = sqlStatement.executeUpdate();
			return result;
		}
		
		public String getSql()
		{
			StringBuilder sql = new StringBuilder();
			
			sql.append(" INSERT INTO");
			sql.append(" CHECKING_ASSET_NEW ");
			sql.append(" (");
			sql.append(" 	ASSET_CD");
			sql.append(" 	,ASSET_RFID");
			sql.append(" 	,ASSET_NAME");
			sql.append(" 	,CHECKING_CD");
			sql.append(" 	,DEPARTMENT");
			sql.append(" 	,REASON");
			sql.append(" 	,NOTE");
			sql.append(" 	,DATE_CREATE");
			sql.append(" 	,DATE_INSERT");
			sql.append(" 	,USER_INSERT");
			sql.append(" 	,STATUS");
			sql.append(" 	,CMPN_CD");
			sql.append(" )");
			sql.append(" VALUES");
			sql.append(" (");
			sql.append(" 	 ?");//ASSET_CD
			sql.append(" 	,?");//ASSET_RFID
			sql.append(" 	,?");//ASSET_NAME
			sql.append(" 	,?");//CHECKING_CD
			sql.append(" 	,?");//DEPARTMENT
			sql.append(" 	,?");//REASON
			sql.append(" 	,?");//NOTE
			sql.append(" 	,?");//DATE_CREATE
			sql.append(" 	,?");//DATE_INSERT
			sql.append(" 	,?");//USER_INSERT
			sql.append(" 	,?");//STATUS
			sql.append(" 	,?");//STATUS
			sql.append(" )");
			
			return sql.toString();
		}
	

	
	

}
