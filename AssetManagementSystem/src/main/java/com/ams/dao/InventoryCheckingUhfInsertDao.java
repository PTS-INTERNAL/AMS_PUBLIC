package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.User;

import com.ams.database.DatabaseConnection;
import com.ams.model.InventoryCheckingUhf;
import com.ams.model.UserModel;
import com.ams.util.Common;



public class InventoryCheckingUhfInsertDao {

	InventoryCheckingUhf ivn;
	
	public InventoryCheckingUhfInsertDao()
	{
		
	}
	
	public InventoryCheckingUhfInsertDao(	InventoryCheckingUhf ivn)
	{
		this.ivn = ivn;
	}
	
	public int excute() throws SQLException
	{
		//Kết nối cơ sở dữ liệu
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSql());
		//---
		//System.out.println(getSql());
		//---
		sqlStatement.setString(1,ivn.getInventoryChekingUhf_cd());
		sqlStatement.setString(2,ivn.getInventorySession().getInvenotrySessionCD());
		sqlStatement.setString(3,ivn.getDeptChecking().getDept_cd());
		//System.out.println(bam.getName());
		sqlStatement.setString(4,ivn.getAsset().getRFID());
		sqlStatement.setString(5,ivn.getAsset().getName());
		sqlStatement.setString(6,ivn.getAsset().getModel());
		sqlStatement.setString(7,ivn.getAsset().getSeries());
		sqlStatement.setString(8,ivn.getNote());
		sqlStatement.setString(9,ivn.getUserInsert());
		sqlStatement.setString(10,ivn.getInsertDt());
		sqlStatement.setString(11,"");
		sqlStatement.setString(12,"");
		sqlStatement.setString(13,ivn.getStatus());
		
		return sqlStatement.executeUpdate();
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO ");
		sql.append(" INVENTORY_CHECKING");
		sql.append(" (");
		sql.append(" INV_CHECKING_CD");
		sql.append(" ,IVN_SESS_CD");
		sql.append(" ,DEPT_CHECKING");
		sql.append(" ,ASSET_RFID");
		sql.append(" ,ASSET_NAME");
		sql.append(" ,ASSET_MODEL");
		sql.append(" ,ASSET_SERIES");
		sql.append(" ,NOTE");
		sql.append(" ,USER_INSERT");
		sql.append(" ,INSERT_DT");
		sql.append(" ,USER_UPDATE");
		sql.append(" ,UPDATE_DT");
		sql.append(" ,STATUS_CHECKING");
		sql.append(" )");
		sql.append(" VALUES (");
		sql.append(" ?");
		sql.append(" ,?");
		sql.append(" ,?");
		sql.append(" ,?");
		sql.append(" ,?");
		sql.append(" ,?");
		sql.append(" ,?");
		sql.append(" ,?");
		sql.append(" ,?");
		sql.append(" ,?");
		sql.append(" ,?");
		sql.append(" ,?");
		sql.append(" ,?");
		sql.append(" )");
		
		
		return sql.toString();
	}
}
