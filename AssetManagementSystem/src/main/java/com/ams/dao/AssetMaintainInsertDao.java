package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ams.database.DatabaseConnection;
import com.ams.model.AssetMaintanceModel;
import com.ams.model.TroubleAssetModel;
import com.ams.util.Common;

public class AssetMaintainInsertDao {
	
	AssetMaintanceModel asset = null;
	
	public AssetMaintainInsertDao(AssetMaintanceModel asset )
	{
		this.asset = asset;
	}
	
	public int excute() throws SQLException
	{
		int result = 0;
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSQL());
		System.out.println(getSQL());
		String ID = getIDSetup();
		sqlStatement.setString(1,ID);
		sqlStatement.setString(2,asset.getAsset().getId());
		sqlStatement.setString(3,asset.getAsset().getRFID());
		sqlStatement.setString(4,asset.getAsset().getModel());
		sqlStatement.setString(5,asset.getAsset().getSeries());
		sqlStatement.setString(6,asset.getDay());
		sqlStatement.setString(7,asset.getUserInsert());
		sqlStatement.setString(8,asset.getInsertDT());
		sqlStatement.setString(9,asset.getUserUpdate());
		sqlStatement.setString(10,asset.getUpdateDT());
		sqlStatement.setString(11,asset.getContent());
		result = sqlStatement.executeUpdate();
		return result;
	}
	public String getIDSetup()
	{
		DateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmSS");
		Date date = new Date();
        String ID = sdf.format(date);
		return ID;
	}
	public String getSQL()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" INSERT INTO");
		sql.append(" MAINTAINCE");
		sql.append(" (");
		sql.append(" 	 MAINTAINCE_CD");
		sql.append(" 	,ASSET_CD");
		sql.append(" 	,ASSET_RFID");
		sql.append(" 	,ASSET_MODEL");
		sql.append(" 	,ASSET_SERIES");
		sql.append(" 	,MAINTAINCE_DT");
		sql.append(" 	,USER_INSERT");
		sql.append(" 	,INSERT_DT");
		sql.append(" 	,USER_UPDATE");
		sql.append(" 	,UPDATE_DT");
		sql.append(" 	,MAINTAIN_CONTENT");

		sql.append(" )");
		sql.append(" VALUES");
		sql.append(" (");
		sql.append(" 	?");//BORROW_CD
		sql.append(" 	,?");//LOAN_CMPN_CD
		sql.append(" 	,?");//LOAN_DEPT
		sql.append(" 	,?");//ASSET_RFID
		sql.append(" 	,?");//BORROW_CMPN_CD
		sql.append(" 	,?");//USER_TS
		sql.append(" 	,?");//INSERT_DT
		sql.append(" 	,?");//STATUS
		sql.append(" 	,?");//STATUS
		sql.append(" 	,?");//STATUS
		sql.append(" 	,?");//STATUS
		sql.append(" )");
		
		
		return sql.toString();
	}
	
	

}
