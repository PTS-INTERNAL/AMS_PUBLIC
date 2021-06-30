package com.ams.dao.borrow;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.Department;

public class BorrowCouponCountSelectDao {
	String cmpn_cd = "";
	public BorrowCouponCountSelectDao(String cmpn_cd) {
		this.cmpn_cd = cmpn_cd;
	}
	
	
	
	public int getBorrowCouponCount() throws SQLException
	{
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		System.out.println(getSql());
		try {
			result = stmt.executeQuery(getSql());
			int count = 0;
			while (result.next()) {
				count = Integer.parseInt(result.getString("NUMBER"));
			}
			
			return count;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Integer) null;
		}
		
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ISNULL(max(TOTAL.NUMBER),0) as NUMBER ");
		sql.append(" from ");
		sql.append(" ( ");
		sql.append(" select MAX(SUBSTRING(BC.NUMBER_NO, 1, 2)) as NUMBER FROM BORROW_COUPON BC ");
		sql.append(" WHERE  ");
		sql.append(" 	BC.DELETE_FG = '0'   ");
		sql.append(" 	AND BC.CMPN_CD_MASTER  =").append("'"+ cmpn_cd +"'");
		sql.append(" union all ");
		sql.append(" select MAX(SUBSTRING(LC.NUMBER_NO, 1, 2)) as NUMBER FROM LOAN_COUPON LC ");
		sql.append(" WHERE  ");
		sql.append(" 	LC.DELETE_FG = '0'   ");
		sql.append(" 	AND LC.CMPN_CD_MASTER  =").append("'"+ cmpn_cd +"'");
		sql.append(" union all ");
		sql.append(" select MAX(SUBSTRING(RC.NUMBER_NO, 1, 2)) as NUMBER FROM RETURN_COUPON RC ");
		sql.append(" WHERE  ");
		sql.append(" 	RC.DELETE_FG = '0'   ");
		sql.append(" 	AND RC.CMPN_CD_MASTER  =").append("'"+ cmpn_cd +"'");
		sql.append(" ) as TOTAL ");
		
		
//		sql.append(" SELECT COUNT (*) AS NUMBER ");
//		sql.append(" FROM BORROW_COUPON BC,LOAN_COUPON LC,RETURN_COUPON RC");
//		sql.append(" WHERE  ");
//		sql.append(" 	BC.DELETE_FG = '0'   ");
//		sql.append(" 	AND BC.CMPN_CD_MASTER  =").append("'"+ cmpn_cd +"'");
//		sql.append(" 	OR LC.DELETE_FG = '0'   ");
//		sql.append(" 	AND LC.CMPN_CD_MASTER  =").append("'"+ cmpn_cd +"'");
//		sql.append(" 	OR RC.DELETE_FG = '0'   ");
//		sql.append(" 	AND RC.CMPN_CD_MASTER  =").append("'"+ cmpn_cd +"'");
		return sql.toString();
	}
}
