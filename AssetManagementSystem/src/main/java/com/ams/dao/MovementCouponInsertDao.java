package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ams.database.DatabaseConnection;
import com.ams.model.BorrowCouponModel;
import com.ams.model.MovementCouponModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;

public class MovementCouponInsertDao {

	MovementCouponModel br=null;
	public MovementCouponInsertDao(	MovementCouponModel br) {
		this.br = br;
	}
	
	public int excute() throws SQLException
	{
		int result = 0;
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSql());
		sqlStatement.setString(1,Common.getDateCurrent("YYYYMMddHHmmSS")); //MOVEMENT_ID
		sqlStatement.setString(2, br.getNumber_no());//MOVEMENT_NUMBER
		sqlStatement.setString(3, br.getMovement_sticker());//MOVEMENT_STICKER
		sqlStatement.setString(4, br.getReason());//MOVEMENT_REASON
		sqlStatement.setString(5, br.getDate_start());//MOVEMENT_START
		sqlStatement.setString(6, br.getDate_end_schedule());//MOVEMENT_END_SCHEDULE
		sqlStatement.setString(7, "");//MOVEMENT_END_REAL
		sqlStatement.setString(8, br.getCmpn_master().getCompany_cd());//MOVEMENT_CMPN_MASTER
		sqlStatement.setString(9, br.getCmpn_client().getCompany_cd());//MOVEMENT_CMPN_CLIENT
		sqlStatement.setString(10, br.getDept_master().getDept_cd());//MOVEMENT_DEPT_MASTER
		sqlStatement.setString(11, br.getDept_client().getDept_cd());//MOVEMENT_DEPT_CLIENT
		sqlStatement.setString(12, "0");//DELETE_FG
		sqlStatement.setString(13, br.getInsert_user());//USER_CREATE
		sqlStatement.setString(14, "");//DEPT_APPROVE
		sqlStatement.setString(15, "");//ACCOUNTANT_APPROVE
		sqlStatement.setString(16, "");//STOCK_APPROVE
		sqlStatement.setString(17, br.getInsert_dt());//DT_CREATE
		sqlStatement.setString(18, "");//DT_DEPT
		sqlStatement.setString(19, "");//DT_ACCOUNT
		sqlStatement.setString(20, "");//DT_STOCK
		sqlStatement.setString(21, "1");//STATUS
		sqlStatement.setString(22, "");//STATUS_COMMENT
			
		result = sqlStatement.executeUpdate();
		return result;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" INSERT INTO MOVEMENT ");
		sql.append(" (MOVEMENT_ID ");
		sql.append(" ,MOVEMENT_NUMBER ");
		sql.append(" ,MOVEMENT_STICKER ");
		sql.append(" ,MOVEMENT_REASON ");
		sql.append(" ,MOVEMENT_START ");
		sql.append(" ,MOVEMENT_END_SCHEDULE ");
		sql.append(" ,MOVEMENT_END_REAL ");
		sql.append(" ,MOVEMENT_CMPN_MASTER ");
		sql.append(" ,MOVEMENT_CMPN_CLIENT ");
		sql.append(" ,MOVEMENT_DEPT_MASTER ");
		sql.append(" ,MOVEMENT_DEPT_CLIENT ");
		sql.append(" ,DELETE_FG ");
		sql.append(" ,USER_CREATE ");
		sql.append(" ,DEPT_APPROVE ");
		sql.append(" ,ACCOUNTANT_APPROVE ");
		sql.append(" ,STOCK_APPROVE ");
		sql.append(" ,DT_CREATE ");
		sql.append(" ,DT_DEPT ");
		sql.append(" ,DT_ACCOUNT ");
		sql.append(" ,DT_STOCK ");
		sql.append(" ,STATUS ");
		sql.append(" ,STATUS_COMMENT) ");
		sql.append(" VALUES ");
		sql.append(" (? ");
		sql.append(" ,? ");
		sql.append(" ,? ");
		sql.append(" ,? ");
		sql.append(" ,? ");
		sql.append(" ,? ");
		sql.append(" ,? ");
		sql.append(" ,? ");
		sql.append(" ,? ");
		sql.append(" ,? ");
		sql.append(" ,? ");
		sql.append(" ,? ");
		sql.append(" ,? ");
		sql.append(" ,? ");
		sql.append(" ,? ");
		sql.append(" ,? ");
		sql.append(" ,? ");
		sql.append(" ,? ");
		sql.append(" ,? ");
		sql.append(" ,? ");
		sql.append(" ,? ");
		sql.append(" ,?) ");
		return sql.toString();
	}
}
