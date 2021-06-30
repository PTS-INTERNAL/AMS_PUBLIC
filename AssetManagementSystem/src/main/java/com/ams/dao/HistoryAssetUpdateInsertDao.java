package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ams.database.DatabaseConnection;
import com.ams.model.HistoryAssetUpdateModel;
import com.ams.model.InventoryChecking;
import com.ams.util.Common;

public class HistoryAssetUpdateInsertDao {
	
	HistoryAssetUpdateModel history = null;
	
	public HistoryAssetUpdateInsertDao(HistoryAssetUpdateModel history) {
		this.history = history;
	}
	
	public int excute() throws SQLException
	{
		int result = 0;
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSql());
		//System.out.println(getSql());
			sqlStatement.setString(1,history.getUpdate_asset_cd());//INVENTORY_SESSION_CD
			sqlStatement.setString(2,history.getUpdate_asset_rfid());//ASSET_RFID
			sqlStatement.setString(3,history.getUpdate_cmpn_cd());//INVENTORY_CHECK
			sqlStatement.setString(4,history.getUpdate_reason());//INVENTORY_CHECK_DATE
			sqlStatement.setString(5,history.getUpdate_user_insert());//USER_CHECK
			sqlStatement.setString(6,history.getUpdate_insert_dt());//USER_CHECK
			sqlStatement.setString(7,history.getUpdate_cd());//USER_CHECK
			result = sqlStatement.executeUpdate();
		return result;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" INSERT INTO");
		sql.append(" HIS_ASSET_UPDATE ");
		sql.append(" (");
		sql.append(" 	ASSET_CD");
		sql.append(" 	,ASSET_RFID");
		sql.append(" 	,CMPN_CD");
		sql.append(" 	,REASON_UPDATE");
		sql.append(" 	,USER_INSERT");
		sql.append(" 	,INSERT_DT");
		sql.append(" 	,UPDATE_CD");
		sql.append(" )");
		sql.append(" VALUES");
		sql.append(" (");
		sql.append(" 	 ?");//INVENTORY_SESSION_CD
		sql.append(" 	,?");//ASSET_RFID
		sql.append(" 	,?");//INVENTORY_CHECK
		sql.append(" 	,?");//INVENTORY_DATE
		sql.append(" 	,?");//USER_CHECK
		sql.append(" 	,?");//USER_CHECK
		sql.append(" 	,?");//USER_CHECK
		sql.append(" )");
		
		return sql.toString();
	}
}
