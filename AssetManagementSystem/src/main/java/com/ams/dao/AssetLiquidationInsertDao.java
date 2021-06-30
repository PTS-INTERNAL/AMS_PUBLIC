package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import com.ams.database.DatabaseConnection;
import com.ams.model.CheckingAssetNew;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;

public class AssetLiquidationInsertDao {
	AssetLiquidationModel asset = null;
	
	public AssetLiquidationInsertDao( AssetLiquidationModel asset )
	{
		this.asset = asset;
	}
	
	public int excute() throws SQLException
	{
		int result = 0;
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSql());
		//System.out.println(getSql());
			Random generator = new Random(19900828);
			
			sqlStatement.setString(1,Common.getDateCurrent("YYYYMMddHHmmSS"+generator.nextInt(1000)));//ASSET_CD
			sqlStatement.setString(2,asset.getAsset().getRFID());
			sqlStatement.setString(3,asset.getUserInsert());//ASSET_CD
			sqlStatement.setString(4,Common.getDateCurrent(ParamsConstants.CD_FULL_DATE));
			sqlStatement.setString(5, asset.getUserUpdate());
			sqlStatement.setString(6, Common.getDateCurrent(ParamsConstants.CD_FULL_DATE));
			sqlStatement.setString(7, asset.getReason());
			sqlStatement.setString(8, asset.getNote());
			sqlStatement.setString(9, asset.getList_ID());
			sqlStatement.setString(10, asset.getStatus());
			result = sqlStatement.executeUpdate();
		return result;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" INSERT INTO ASSETS_LIQUIDATION ");
		sql.append("       (	LIQUIDATION_CD ");//1
		sql.append("       		,ASSET_RFID ");//3
		sql.append("      		,USER_INSERT ");//16
		sql.append("     		,INSERT_DT ");//17
		sql.append("      		,USER_UPDATE ");//18
		sql.append("      		,UPDATE_DT ");//19
		sql.append("      		,REASON ");//20
		sql.append("      		,NOTE ");//21
		sql.append("      		,LIST_ID ");//21
		sql.append("      		,STATUS )");
		sql.append("  VALUES ");
		sql.append("         (	 ? ");
		sql.append("        	,? ");
		sql.append("         	,? ");
		sql.append("        	,? ");
		sql.append("        	,? ");
		sql.append("        	,? ");
		sql.append("       		,? ");
		sql.append("       		,? ");
		sql.append("       		,? ");
		sql.append("       		,?	) ");
		return sql.toString();
	}


}
