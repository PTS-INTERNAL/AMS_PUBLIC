package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ams.database.DatabaseConnection;
import com.ams.model.AssetGeneralModel;



public class AssetGeneralDeleteUpdateDao {
	
	AssetGeneralModel asset;
	public AssetGeneralDeleteUpdateDao(AssetGeneralModel asset)
	{
		this.asset = asset;
	}
	
	
	public int excuteUpdateDeleteData() throws SQLException
	{
		int result = 0;
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSql());
		//System.out.println(getSql());
		sqlStatement.setString(1, asset.getDelete_Fg());
		sqlStatement.setString(2, asset.getUser_update_cd());
		sqlStatement.setString(3, asset.getUpdate_dt());
		sqlStatement.setString(4, asset.getRFID());
		sqlStatement.setString(5, asset.getSeries());
		sqlStatement.setString(6, asset.getModel());
		sqlStatement.setString(7, asset.getCompany_CD());
		sqlStatement.setString(8, asset.getDepartment_cd());
		
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
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" UPDATE");
		sql.append(" 	ASSETS_GENERAL");
		sql.append(" SET ");
		sql.append(" 	DELETE_FG = ?");
		sql.append(" 	,USER_UPDATE = ?");
		sql.append(" 	,UPDATE_DT = ?");
		sql.append(" WHERE ");
		sql.append(" 	ASSET_RFID = ?");
		sql.append(" 	AND ASSET_SERIES = ?");
		sql.append(" 	AND ASSET_MODEL = ?");
		sql.append(" 	AND CMPN_CD = ?");
		sql.append(" 	AND ASSET_DEPARTMENT = ?");
		
		
		return sql.toString();
	}

}
