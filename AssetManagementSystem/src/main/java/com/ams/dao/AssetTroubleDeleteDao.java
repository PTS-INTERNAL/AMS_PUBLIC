package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ams.database.DatabaseConnection;
import com.ams.model.AssetGeneralModel;
import com.ams.model.TroubleAssetModel;



public class AssetTroubleDeleteDao {
	
	TroubleAssetModel asset;
	public AssetTroubleDeleteDao(TroubleAssetModel asset)
	{
		this.asset = asset;
	}
	
	
	public int excuteDeleteTrouble(String TRID) throws SQLException
	{
		int result = 0;
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSql(TRID));
		System.out.println("ID1:"+TRID);
		System.out.println(getSql(TRID));
		
		result = sqlStatement.executeUpdate();
		
		return result;
	}
	
	

	public String getSql(String TRID)
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append("DELETE FROM TROUBLE_ASSET ");
		sql.append("   WHERE  1=1 ");
		sql.append("   AND   TROUBLE_CD = ").append("'"+TRID+"'");
		return sql.toString();
	}

}
