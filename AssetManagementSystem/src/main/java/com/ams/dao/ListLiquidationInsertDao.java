package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ams.database.DatabaseConnection;
import com.ams.model.LiquidationListModel;
import com.ams.model.TroubleAssetModel;
import com.ams.util.Common;

public class ListLiquidationInsertDao {
	
	LiquidationListModel asset = null;
	
	public ListLiquidationInsertDao(LiquidationListModel asset )
	{
		this.asset = asset;
	}
	
	public int excute() throws SQLException
	{
		int result = 0;
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSQL());
		System.out.println(getSQL());
		
		sqlStatement.setString(1,Common.getDateCurrent("YYYYMMddHHmmSS"));
		sqlStatement.setString(2,asset.getListName());
		sqlStatement.setString(3,asset.getDateCreate());
		sqlStatement.setString(4,asset.getUserCreate());
		sqlStatement.setString(5,asset.getCompary_cd());
		result = sqlStatement.executeUpdate();
		return result;
	}
	
	public String getSQL()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" INSERT INTO");
		sql.append(" LIQUIDATION_LIST ");
		sql.append(" (");
		sql.append(" 	 LIST_ID");
		sql.append(" 	,LISTNAME");
		sql.append(" 	,DATECREATE");
		sql.append(" 	,USERCREATE");
		sql.append(" 	,COMPANY_CD");
		sql.append(" )");
		sql.append(" VALUES");
		sql.append(" (");
		sql.append(" 	?");//BORROW_CD
		sql.append(" 	,?");//LOAN_CMPN_CD
		sql.append(" 	,?");//LOAN_DEPT
		sql.append(" 	,?");//ASSET_RFID
		sql.append(" 	,?");//STATUS
		sql.append(" )");
		return sql.toString();
	}
	
	

}
