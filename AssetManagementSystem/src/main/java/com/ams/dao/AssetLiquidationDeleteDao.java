
package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ams.database.DatabaseConnection;
import com.ams.model.AssetGeneralModel;
import com.ams.model.LiquidationListModel;
import com.ams.model.TroubleAssetModel;



public class AssetLiquidationDeleteDao  {
	
	AssetLiquidationModel  asset;
	public AssetLiquidationDeleteDao()
	{
	}
	
	
	public void excuteDeleteLiquidation(String liduid_cd) throws SQLException
	{
		int result = 0;
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSql(liduid_cd));
		System.out.println("ID1:"+liduid_cd);
		System.out.println(getSql(liduid_cd));
		
		result = sqlStatement.executeUpdate();
		
	}
	
	

	public String getSql(String liquid_cd)
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append("DELETE FROM ASSETS_LIQUIDATION");
		sql.append("   WHERE  1=1 ");
		sql.append("   AND   LIQUIDATION_CD = ").append("'"+liquid_cd+"'");
		return sql.toString();
	}



}
