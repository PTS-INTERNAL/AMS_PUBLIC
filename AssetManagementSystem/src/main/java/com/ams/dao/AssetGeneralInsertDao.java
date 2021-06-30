package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.print.attribute.standard.DateTimeAtCompleted;

import com.ams.database.DatabaseConnection;
import com.ams.model.AssetGeneralModel;
import com.ams.util.SystemControl;



public class AssetGeneralInsertDao {
	
	public SystemControl systemControl = null;
	
	public AssetGeneralInsertDao()
	{
		
	}
	
	public int excuteListData(List<AssetGeneralModel> lstData) throws SQLException
	{
		int result = 0;
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSql());
		//System.out.println(getSql());
		
		for(int i=0;i<lstData.size();i++)
		{
			AssetGeneralModel assetObject = lstData.get(i);
			String ID = getIDSetup();
			sqlStatement.setString(1,ID);
			sqlStatement.setString(2,assetObject.getRFID());
			sqlStatement.setString(3,assetObject.getName());
			sqlStatement.setString(4,assetObject.getModel());
			sqlStatement.setString(5,assetObject.getSeries());
			sqlStatement.setString(6,assetObject.getDepartment_cd());
			sqlStatement.setString(7,assetObject.getAccountant_CD());
			sqlStatement.setString(8,assetObject.getDateStart());
			sqlStatement.setString(9,assetObject.getPrice());
			sqlStatement.setString(10,assetObject.getNote());
			
			 result = sqlStatement.executeUpdate();
		}
		
		return result;
	}
	
	public int excuteData(AssetGeneralModel asset) throws SQLException
	{
		int result = 0;
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSql());
		//System.out.println(getSql());
		
			sqlStatement.setString(1,asset.getId());
			sqlStatement.setString(2,asset.getRFID());
			sqlStatement.setString(3,asset.getName());
			sqlStatement.setString(4,asset.getModel());
			sqlStatement.setString(5,asset.getSeries());
			sqlStatement.setString(6,asset.getDepartment_cd());
			sqlStatement.setString(7,asset.getAccountant_CD());
			sqlStatement.setString(8,asset.getDateStart());
			sqlStatement.setString(9,asset.getPrice());
			sqlStatement.setString(10,asset.getNote());
			sqlStatement.setString(11,"0");
			sqlStatement.setString(12,asset.getCompany_CD());
			sqlStatement.setString(13,asset.getGroup_id());
			sqlStatement.setString(14,asset.getDateEnd());
			sqlStatement.setString(15,asset.getUser_insert_cd());
			sqlStatement.setString(16,asset.getInsert_dt());
			sqlStatement.setString(17,asset.getUser_update_cd());
			sqlStatement.setString(18,asset.getUpdate_dt());
			sqlStatement.setString(19,asset.getOriginal());
			sqlStatement.setString(20,asset.getMaintaince());
			sqlStatement.setString(21,asset.getCustomer());
			sqlStatement.setString(22,asset.getContract());
			
			
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
		
		sql.append(" INSERT INTO");
		sql.append(" ASSETS_GENERAL");
		sql.append(" (");
		sql.append(" 	ASSET_CD");
		sql.append(" 	,ASSET_RFID");
		sql.append(" 	,ASSET_NAME");
		sql.append(" 	,ASSET_MODEL");
		sql.append(" 	,ASSET_SERIES");
		sql.append(" 	,ASSET_DEPARTMENT");
		sql.append(" 	,ASSET_ACCOUNTANT");
		sql.append(" 	,ASSET_DATE_INVEST");
		sql.append(" 	,ASSET_PRICE");
		sql.append(" 	,ASSET_NOTE");
		sql.append(" 	,DELETE_FG");
		sql.append(" 	,CMPN_CD");
		sql.append(" 	,GROUP_CD");
		sql.append(" 	,ASSET_DATE_END");
		sql.append(" 	,USER_INSERT");
		sql.append(" 	,INSERT_DT");
		sql.append(" 	,USER_UPDATE");
		sql.append(" 	,UPDATE_DT");
		sql.append(" 	,ASSET_ORIGINAL");
		sql.append(" 	,ASSET_MAINTAINCE");
		sql.append(" 	,CUSTOMER");
		sql.append(" 	,CONTRACT");
		sql.append(" )");
		sql.append(" VALUES");
		sql.append(" (");
		sql.append(" 	?");//ASSET_CD
		sql.append(" 	,?");//ASSET_RFID
		sql.append(" 	,?");//ASSET_NAME
		sql.append(" 	,?");//ASSET_MODEL
		sql.append(" 	,?");//ASSET_SERIES
		sql.append(" 	,?");//ASSET_DEPARTMENT
		sql.append(" 	,?");//ASSET_ACCOUNTANT
		sql.append(" 	,?");//ASSET_DATE_INVEST
		sql.append(" 	,?");//ASSET_PRICE
		sql.append(" 	,?");//ASSET_NOTE
		sql.append(" 	,?");//DELETE_FG
		sql.append(" 	,?");//CMPN_CD
		sql.append(" 	,?");//DELETE_FG
		sql.append(" 	,?");//CMPN_CD
		sql.append(" 	,?");//DELETE_FG
		sql.append(" 	,?");//CMPN_CD
		sql.append(" 	,?");//DELETE_FG
		sql.append(" 	,?");//CMPN_CD
		sql.append(" 	,?");//DELETE_FG
		sql.append(" 	,?");//CMPN_CD
		sql.append(" 	,?");//DELETE_FG
		sql.append(" 	,?");//CMPN_CD
		sql.append(" )");
		
		return sql.toString();
	}
}
