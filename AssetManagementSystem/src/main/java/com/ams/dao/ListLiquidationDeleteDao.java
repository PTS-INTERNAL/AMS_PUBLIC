
package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ams.database.DatabaseConnection;

import com.ams.model.LiquidationListModel;



public class ListLiquidationDeleteDao  {
	
	LiquidationListModel  asset;
	public ListLiquidationDeleteDao()
	{
	}
	
	
	public void excuteDeleteListLiquidation(String liduid_cd) throws SQLException
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
		
		sql.append("DELETE FROM LIQUIDATION_LIST");
		sql.append("   WHERE  1=1 ");
		sql.append("   AND   LIST_ID= ").append("'"+liquid_cd+"'");
		return sql.toString();
	}



}
