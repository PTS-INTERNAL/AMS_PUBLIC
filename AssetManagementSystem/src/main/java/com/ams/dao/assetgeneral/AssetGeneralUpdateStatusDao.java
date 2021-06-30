package com.ams.dao.assetgeneral;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ams.database.DatabaseConnection;
import com.ams.model.AssetGeneralModel;
import com.ams.model.BorrowCouponModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;

public class AssetGeneralUpdateStatusDao {

	AssetGeneralModel ass=null;
	public AssetGeneralUpdateStatusDao(AssetGeneralModel ass) {
		this.ass = ass;
	}
	
	public int excute() throws SQLException
	{
		int result = 0;
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSql());
		
			sqlStatement.setString(1, ass.getStatus());
			sqlStatement.setString(2,ass.getId());
			
		result = sqlStatement.executeUpdate();
		return result;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" 	UPDATE ASSETS_GENERAL ");		
		sql.append("       SET				");
		sql.append("       		STATUS 			= ?		");		
		sql.append("       WHERE ASSET_CD 		= ?		");	
		
		return sql.toString();
	}
}
