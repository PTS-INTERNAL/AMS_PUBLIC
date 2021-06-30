package com.ams.dao;




import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.AssetGeneralModel;
import com.ams.model.AssetMaintanceModel;
import com.ams.model.BorrowAssetModel;
import com.ams.model.CompanyModel;
import com.ams.model.Department;
import com.ams.model.TroubleAssetModel;
import com.ams.util.Common;

public class AssetMaintainUpdateDao {
	AssetMaintanceModel  asset = null;

	public AssetMaintainUpdateDao() {

	}
	public AssetMaintainUpdateDao(AssetMaintanceModel asset) {
		this.asset = asset;
	}
	
	public Boolean  UpdateMainTainance() throws SQLException {
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		
		System.out.println(getSQL());
		try {
			stmt.executeQuery(getSQL());
			System.out.println("CẬP NHẬT THÀNH CÔNG");
		}
		catch(Exception e)
		{
			System.out.println("CẬP NHẬT KHÔNG THÀNH CÔNG");
		}
		return false;
	}

	public String getSQL()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE MAINTAINCE SET");
		sql.append("   MAINTAINCE_DT =");
		sql.append("'"+asset.getDay()+"'");
		sql.append("   ,MAINTAIN_CONTENT =");
		sql.append("N'"+asset.getContent()+"'");
		sql.append("   ,USER_INSERT =");
		sql.append("N'"+asset.getUserInsert()+"'");
		sql.append("   WHERE  1=1 ");
		sql.append("   AND   ASSET_RFID = ").append("'"+asset.getAsset().getRFID()+"'");
		return sql.toString();
	}
}
