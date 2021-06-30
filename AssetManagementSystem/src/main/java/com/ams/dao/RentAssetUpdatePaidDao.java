package com.ams.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ams.database.DatabaseConnection;
import com.ams.model.RentAsset;

public class RentAssetUpdatePaidDao {
      public RentAsset asset;
      
      public RentAssetUpdatePaidDao(RentAsset asset)
      {
    	  this.asset = asset;
      }
      
      public int excute() throws SQLException
  		{
  		DatabaseConnection conn = new DatabaseConnection();
  		Connection connectString = conn.getConnection();
  		Statement stmt = connectString.createStatement();
  		ResultSet result = null;
  		//System.out.println(getSql());
  		return stmt.executeUpdate(getSql());
  		
  		}
  	
  	public String getSql()
  	{
  		StringBuilder sql = new StringBuilder();
  		
  		sql.append("UPDATE 	RENT_ASSET	");
  		sql.append("SET		");
  		sql.append(" 	STATUS_RENT =  '2'");
  		sql.append(" 	,DATE_END_REAL = ").append("'" + asset.getDate_end_real()+"'");
  		sql.append("WHERE 	 ");
  		sql.append(" 	  RENT_CD = ").append("'" + asset.getRent_cd() +"'");

  	
  		return sql.toString();
  	}
}
