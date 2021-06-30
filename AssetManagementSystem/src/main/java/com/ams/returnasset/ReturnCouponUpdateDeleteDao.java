package com.ams.returnasset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ams.database.DatabaseConnection;
import com.ams.model.ReturnCouponModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;

public class ReturnCouponUpdateDeleteDao {

	ReturnCouponModel br=null;
	public ReturnCouponUpdateDeleteDao(ReturnCouponModel br) {
		this.br = br;
	}
	
	public int excute() throws SQLException
	{
		int result = 0;
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSql());
		
			sqlStatement.setString(1, "1");
			sqlStatement.setString(2,br.getCoupon_cd());
			
		result = sqlStatement.executeUpdate();
		return result;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" 	UPDATE RETURN_COUPON ");		
		sql.append("       SET				");
		sql.append("       		DELETE_FG 		= ?		");		
		sql.append("       WHERE COUPON_CD 		= ?		");	
		
		return sql.toString();
	}
}
