package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.AssetGeneralModel;



public class AssetGeneralUpdateDao {
	public AssetGeneralUpdateDao()
	{
		
	}
	public int excuteData(AssetGeneralModel asset) throws SQLException
	{
		int result = 0;
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSql(asset));
		//System.out.println(getSql(asset));
		
			
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
	
	public String getSql(AssetGeneralModel asset)
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" UPDATE");
		sql.append(" 	ASSETS_GENERAL");
		sql.append(" SET ");
		sql.append(" 	ASSET_NAME = ").append("N'" + asset.getName() + "'");
		sql.append(" 	,GROUP_CD = ").append("N'" + asset.getGroup_id() + "'");
		sql.append(" 	,ASSET_SERIES = ").append("N'" + asset.getSeries() + "'");
		sql.append(" 	,ASSET_MODEL = ").append("N'" + asset.getModel() + "'");
		sql.append(" 	,ASSET_DEPARTMENT = ").append("N'" + asset.getDepartment_cd() + "'");
		sql.append(" 	,ASSET_ACCOUNTANT = ").append("N'" + asset.getAccountant_CD() + "'");
		sql.append(" 	,ASSET_DATE_INVEST = ").append("N'" + asset.getDateStart() + "'");
		sql.append(" 	,ASSET_DATE_END = ").append("N'" + asset.getDateEnd() + "'");
		sql.append(" 	,ASSET_PRICE = ").append("N'" + asset.getPrice() + "'");
		sql.append(" 	,ASSET_NOTE = ").append("N'" + asset.getNote() + "'");
		sql.append(" 	,USER_UPDATE = ").append("N'" + asset.getUser_update_cd() + "'");
		sql.append(" 	,UPDATE_DT = ").append("N'" + asset.getUpdate_dt() + "'");
		sql.append(" 	,ASSET_ORIGINAL = ").append("N'" + asset.getOriginal() + "'");
		sql.append(" 	,ASSET_MAINTAINCE = ").append("N'" + asset.getMaintaince() + "'");
		sql.append(" 	,CUSTOMER = ").append("N'" + asset.getCustomer() + "'");
		sql.append(" 	,CONTRACT = ").append("N'" + asset.getContract() + "'");
		
		sql.append(" WHERE ");
		sql.append(" 	ASSET_RFID = ").append("'" + asset.getRFID() + "'");
		sql.append(" 	AND ASSET_CD = ").append("'" + asset.getId() + "'");
		return sql.toString();
	}

}
