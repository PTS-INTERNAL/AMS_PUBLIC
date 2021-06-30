package com.ams.dao.borrow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ams.database.DatabaseConnection;
import com.ams.model.BorrowCouponModel;
import com.ams.model.MovementCouponModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;

public class MovementCouponUpdateDeleteDao {

	MovementCouponModel br=null;
	public MovementCouponUpdateDeleteDao(MovementCouponModel br) {
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
		
		sql.append(" 	UPDATE MOVEMENT ");		
		sql.append("       SET				");
		sql.append("       		DELETE_FG 		= ?		");		
		sql.append("       WHERE MOVEMENT_ID 		= ?		");	
		
		return sql.toString();
	}
}
