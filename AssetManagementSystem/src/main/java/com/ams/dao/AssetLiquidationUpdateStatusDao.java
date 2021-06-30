package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ams.database.DatabaseConnection;
import com.ams.model.CheckingAssetNew;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;

public class AssetLiquidationUpdateStatusDao {
	AssetLiquidationModel asset = null;
	
	public AssetLiquidationUpdateStatusDao( AssetLiquidationModel asset )
	{
		this.asset = asset;
	}
	
	public int excute() throws SQLException
	{
		int result = 0;
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSql());
		System.out.println(getSql());
			result = sqlStatement.executeUpdate();
		return result;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" UPDATE ASSETS_LIQUIDATION ");
		sql.append("       SET ");
		sql.append("       		STATUS = ").append("N'"+asset.getStatus()+"'");
		sql.append("       		, REASON_NOT_ALLOW = ").append("'"+asset.getReason_not_allow()+"'");
		sql.append("       		, USER_APPROVE = ").append("'"+asset.getUserApprove()+"'");
		sql.append("       		, APPROVE_DT = ").append("'"+Common.getDateCurrent(ParamsConstants.CD_FULL_DATE)+"'");
		sql.append("  WHERE ");
		sql.append("        	LIQUIDATION_CD =").append("'"+asset.getLiquidation_Cd()+"'");
		return sql.toString();
	}


}
