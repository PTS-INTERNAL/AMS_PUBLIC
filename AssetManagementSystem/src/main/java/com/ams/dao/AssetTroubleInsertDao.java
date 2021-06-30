package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ams.database.DatabaseConnection;
import com.ams.model.TroubleAssetModel;
import com.ams.util.Common;

public class AssetTroubleInsertDao {
	
	TroubleAssetModel asset = null;
	
	public AssetTroubleInsertDao(TroubleAssetModel asset )
	{
		this.asset = asset;
	}
	
	public int excute() throws SQLException
	{
		int result = 0;
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSQL());
		//System.out.println(getSQL());
		
		sqlStatement.setString(1,Common.getDateCurrent("YYYYMMddHHmmSS"));
		sqlStatement.setString(2,asset.getAsset().getId());
		sqlStatement.setString(3,asset.getUserUse());
		sqlStatement.setString(4,asset.getDateTrouble());
		sqlStatement.setString(5,asset.getTimeTrouble());
		sqlStatement.setString(6,asset.getTrouble());
		sqlStatement.setString(7,asset.getReason());
		sqlStatement.setString(8,asset.getNote());
		sqlStatement.setString(9,asset.getUser_insert());
		sqlStatement.setString(10,asset.getInsert_dt());
		sqlStatement.setString(11,asset.getUser_update());
		sqlStatement.setString(12,asset.getUpdate_dt());
		sqlStatement.setString(13,asset.getAsset().getCompany_CD());
		sqlStatement.setString(14,asset.getAsset().getRFID());

		result = sqlStatement.executeUpdate();
		return result;
	}
	
	public String getSQL()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" INSERT INTO");
		sql.append(" TROUBLE_ASSET");
		sql.append(" (");
		sql.append(" 	 TROUBLE_CD");
		sql.append(" 	,ASSET_CD");
		sql.append(" 	,USER_USE");
		sql.append(" 	,DATE_TROUBLE");
		sql.append(" 	,TIME_TROUBLE");
		sql.append(" 	,TROUBLE");
		sql.append(" 	,REASON");
		sql.append(" 	,NOTE");
		sql.append(" 	,USER_INSERT");
		sql.append(" 	,INSERT_DT");
		sql.append(" 	,USER_UPDATE");
		sql.append(" 	,UPDATE_DT");
		sql.append(" 	,CMPN_CD");
		sql.append(" 	,ASSET_RFID");


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
		sql.append(" 	,?");//STATUS
		sql.append(" 	,?");//STATUS
		sql.append(" )");
		
		return sql.toString();
	}
	
	

}
