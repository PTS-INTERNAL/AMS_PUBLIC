package com.ams.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ams.database.DatabaseConnection;

import com.ams.model.LiquidationListModel;

import com.ams.util.Common;



public class ListLiquidationSelectDao {

	LiquidationListModel LiquidationModel = null;

	public ListLiquidationSelectDao() {

	}

	public ListLiquidationSelectDao(LiquidationListModel liquidationListModel) {
		this.LiquidationModel = liquidationListModel;
	}

	public List<LiquidationListModel> excute() throws SQLException {
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		System.out.println(getSQL()); 
		result = stmt.executeQuery(getSQL());
		List<LiquidationListModel> Listlst =new ArrayList<LiquidationListModel>();
		
		while (result.next()) {
			LiquidationListModel lst = new LiquidationListModel();
			lst.setList_id(result.getString("LIST_ID"));
			lst.setListName(result.getString("LISTNAME"));
			lst.setDateCreate(result.getString("DATECREATE"));
			lst.setUserCreate(result.getString("USERCREATE"));

			Listlst.add(lst);
		}
		return Listlst;
	}

	public String getSQL()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT ");
		sql.append("   LIST_ID,");
		sql.append("   LISTNAME,");
		sql.append("   DATECREATE ,");
		sql.append("   USERCREATE ,");
		sql.append("   COMPANY_CD ");
		sql.append(" FROM LIQUIDATION_LIST ");		
		sql.append(" WHERE 1=1");
		if(LiquidationModel!=null)
		{
			
			if(Common.isNotEmpty(LiquidationModel.getCompary_cd())) {
			  sql.append("   AND COMPANY_CD = ").append("'"+LiquidationModel.getCompary_cd(
			  )+"'"); }
			 
			if(LiquidationModel.getList_id() != null && LiquidationModel.getList_id().trim().length()>0)
			{
				sql.append("AND LIST_ID = ").append("'"+ LiquidationModel.getList_id()+"'");
			}
			if(LiquidationModel.getListName()!=null && LiquidationModel.getListName().trim().length()>0)
			{
				sql.append("AND LISTNAME = ").append("'"+ LiquidationModel.getListName()+"'");
			}
			if(LiquidationModel.getUserCreate() != null && LiquidationModel.getUserCreate().trim().length()>0)
			{
				sql.append("AND USERCREATE = ").append("'"+ LiquidationModel.getUserCreate()+"'");
			}
			if(LiquidationModel.getDateCreate()!=null && LiquidationModel.getDateCreate().trim().length()>0)
			{
				sql.append("AND DATECREATE = ").append("'"+ LiquidationModel.getDateCreate()+"'");
			}
		}
		return sql.toString();
	}
}
