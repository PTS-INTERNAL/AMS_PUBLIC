package com.ams.dao;




import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.AssetGeneralModel;
import com.ams.model.BorrowAssetModel;
import com.ams.model.CompanyModel;
import com.ams.model.Department;
import com.ams.model.TroubleAssetModel;
import com.ams.util.Common;

public class AssetTroubleUpdateDao {
	TroubleAssetModel asset = null;

	public AssetTroubleUpdateDao() {

	}
	public AssetTroubleUpdateDao(TroubleAssetModel asset) {
		this.asset = asset;
	}
	
	public Boolean  UpdateTroubleByID(TroubleAssetModel trouble, String newtrouble,String troubleStatus) throws SQLException {
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		
		System.out.println(getSQL(trouble.getTroubleID(),newtrouble,troubleStatus));
		try {
			stmt.executeQuery(getSQL(trouble.getTroubleID(),newtrouble,troubleStatus));
			trouble.setTrouble(newtrouble);
		}
		catch(Exception e)
		{
			System.out.println("CẬP NHẬT KHÔNG THÀNH CÔNG");
		}
		return false;
	}

	public String getSQL(String ID,String newtrouble,String troubleStatus)
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE TROUBLE_ASSET SET");
		sql.append("   TROUBLE =");
		sql.append("N'"+newtrouble+"'");
		sql.append("   ,TROUBLE_STATUS =");
		sql.append("N'"+troubleStatus+"'");
		sql.append("   WHERE  1=1 ");
		sql.append("   AND   TROUBLE_CD = ").append(ID);
		
	
		return sql.toString();
	}
}
