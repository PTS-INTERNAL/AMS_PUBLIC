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



public class InventoryCheckingUhfSelectDao {

	InventoryCheckingUhf ivn;
	
	public InventoryCheckingUhfSelectDao()
	{
		
	}
	
	public InventoryCheckingUhfSelectDao(	InventoryCheckingUhf ivn)
	{
		this.ivn = ivn;
	}
	
	public List<InventoryCheckingUhf> excute() throws SQLException
	{
		//Kết nối cơ sở dữ liệu
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		//---
		//System.out.println(getSql());
		//---
		result = stmt.executeQuery(getSql());
		List<InventoryCheckingUhf> lstAsset =new ArrayList<InventoryCheckingUhf>();
		while (result.next()) {
			InventoryCheckingUhf ivnItem = new InventoryCheckingUhf();
		     ivn.setInventoryChekingUhf_cd(result.getString("INV_CHECKING_CD"));
		     ivn.getInventorySession().setInvenotrySessionCD(result.getString("IVN_SESS_CD"));
		     ivn.getDeptChecking().setDept_cd(result.getString("DEPT_CHECKING"));
		     ivn.getAsset().setName(result.getString("ASSET_NAME"));
		     ivn.getAsset().setModel(result.getString("ASSET_MODEL"));
		     ivn.getAsset().setSeries(result.getString("ASSET_SERIES"));
		     ivn.setNote(result.getString("NOTE"));
		     ivn.setInventoryChekingUhf_cd(result.getString("ASSET_RFID"));
		     
			lstAsset.add(ivnItem);
		}
		
		return lstAsset;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT *");
		sql.append(" FROM");
		sql.append(" INVENTORY_CHECKING");
		sql.append(" WHERE");
		sql.append(" 1=1 ");
		if(ivn != null)
		{
			if(Common.isNotCheckEmpty(ivn.getInventoryChekingUhf_cd()))
			{
				sql.append(" AND INV_CHECKING_CD = ").append("'" + ivn.getInventoryChekingUhf_cd() + "'");
			}
			if(Common.isNotCheckEmpty(ivn.getInventorySession().getInvenotrySessionCD()))
			{
				sql.append(" AND IVN_SESS_CD = ").append("'" + ivn.getInventorySession().getInvenotrySessionCD() + "'");
			}
			if(Common.isNotCheckEmpty(ivn.getDeptChecking().getDept_cd()))
			{
				sql.append(" AND DEPT_CHECKING = ").append("'" + ivn.getDeptChecking().getDept_cd() + "'");
			}
			if(Common.isNotCheckEmpty(ivn.getAsset().getRFID()))
			{
				sql.append(" AND ASSET_RFID = ").append("'" + ivn.getAsset().getRFID() + "'");
			}	
		}
		
		return sql.toString();
	}
}
