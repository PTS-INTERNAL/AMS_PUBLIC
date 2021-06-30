package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.BorrowCouponModel;
import com.ams.model.MovementCouponModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;

public class MovementCouponSelectDao {

	MovementCouponModel br=null;
	String cmpn_cd = "";
	public MovementCouponSelectDao(MovementCouponModel br) {
		this.br = br;
	}
	
	
	
	public List<MovementCouponModel> excute() throws SQLException
	{
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		System.out.println(getSql());
		
		result = stmt.executeQuery(getSql());
		List<MovementCouponModel> lstbr = new ArrayList<>();
		while (result.next()) {
			MovementCouponModel brItem = new MovementCouponModel();
			
			brItem.setCoupon_cd(result.getString("MOVEMENT_ID"));
			brItem.setNumber_no(result.getString("MOVEMENT_NUMBER"));	
			//brItem.setNo_cd(result.getString("NO_CD"));	
			brItem.setStatus(result.getString("STATUS"));	
			brItem.getCmpn_master().setCompany_cd(result.getString("MOVEMENT_CMPN_MASTER"));	
			brItem.getDept_master().setDept_cd(result.getString("MOVEMENT_DEPT_MASTER"));	
			brItem.getCmpn_client().setCompany_cd(result.getString("MOVEMENT_CMPN_CLIENT"));	
			brItem.getDept_client().setDept_cd(result.getString("MOVEMENT_DEPT_CLIENT"));	
			brItem.setDate_start(result.getString("MOVEMENT_START"));	
			brItem.setDate_end_schedule(result.getString("MOVEMENT_END_SCHEDULE"));	
			brItem.setDate_end_real(result.getString("MOVEMENT_END_REAL"));	
			brItem.setReason(result.getString("MOVEMENT_REASON"));	
			brItem.setApprove_comment(result.getString("STATUS_COMMENT"));	
			brItem.setInsert_dt(result.getString("DT_CREATE"));	
			brItem.setInsert_user(result.getString("USER_CREATE"));	
			brItem.setUpdate_dt(result.getString("UPDATE_DT"));	
			brItem.setUpdate_user(result.getString("USER_UPDATE"));	
			brItem.getCmpn_master().setCompany_name(result.getString("CMPN_MASTER_NAME"));	
			brItem.getCmpn_master().setCompany_shortname(result.getString("CMPN_MASTER_SHORT"));	
			brItem.getCmpn_client().setCompany_name(result.getString("CMPN_CLIENT_NAME"));	
			brItem.getCmpn_client().setCompany_shortname(result.getString("CMPN_CLIENT_SHORT"));	
			brItem.getDept_master().setDept_name(result.getString("DEPT_MASTER_NAME"));	
			brItem.getDept_client().setDept_name(result.getString("DEPT_CLIENT_NAME"));	
			brItem.setUser_insert_name(result.getString("USER_INSERT_NAME"));	
			brItem.setUser_update_name(result.getString("USER_UPDATE_NAME"));	
			brItem.setReasonNotAllow(result.getString("REASON_NOT_ALLOW"));
			brItem.setUser_approve_name(result.getString("DEPT_APPROVE_USER_NAME"));
			brItem.setUser_accountant_name(result.getString("ACCOUNT_APROVE_USER_NAME"));
			brItem.setUser_stock_name(result.getString("STOCK_APPROVE_USER_NAME"));
			brItem.setDt_approve(result.getString("DT_DEPT"));
			brItem.setDt_accountant(result.getString("DT_ACCOUNT"));
			brItem.setDt_stock(result.getString("DT_STOCK"));
			brItem.setReasonNotAllow(result.getString("REASON_NOT_ALLOW"));
			//brItem.setCoupon_loan_cd(result.getString("COUPON_LOAN_CD"));
			brItem.setMovement_sticker(result.getString("MOVEMENT_STICKER"));
			brItem.setReasonNotAllow(result.getString("REASON_NOT_ALLOW"));
			lstbr.add(brItem);
		}
		
		return lstbr;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT MV.* , ");
		sql.append("    CMPN1.CMPN_NAME AS CMPN_MASTER_NAME , ");
		sql.append("    CMPN1.CMPN_SHORT_NAME AS CMPN_MASTER_SHORT , ");
		sql.append("    CMPN2.CMPN_NAME AS CMPN_CLIENT_NAME , ");
		sql.append("    CMPN2.CMPN_SHORT_NAME AS CMPN_CLIENT_SHORT , ");
		sql.append("    DEPT1.DEPARTMENT_NAME AS DEPT_MASTER_NAME , ");
		sql.append("    DEPT2.DEPARTMENT_NAME AS DEPT_CLIENT_NAME , ");
		sql.append("    US1.USER_NAME AS USER_INSERT_NAME , ");
		sql.append("    US2.USER_NAME AS USER_UPDATE_NAME , ");
		sql.append("    US3.USER_NAME AS DEPT_APPROVE_USER_NAME , ");
		sql.append("    US4.USER_NAME AS ACCOUNT_APROVE_USER_NAME , ");
		sql.append("    US5.USER_NAME AS STOCK_APPROVE_USER_NAME ");
		sql.append(" FROM MOVEMENT MV ");
		sql.append(" LEFT JOIN COMPANY CMPN1 ON MV.MOVEMENT_CMPN_MASTER = CMPN1.CMPN_CD ");
		sql.append(" LEFT JOIN COMPANY CMPN2 ON MV.MOVEMENT_CMPN_CLIENT = CMPN2.CMPN_CD ");
		sql.append(" LEFT JOIN DEPRATMENT DEPT1 ON MV.MOVEMENT_DEPT_MASTER = DEPT1.DEPT_CD ");
		sql.append(" LEFT JOIN DEPRATMENT DEPT2 ON MV.MOVEMENT_DEPT_CLIENT = DEPT2.DEPT_CD ");
		sql.append(" LEFT JOIN USER_SYSTEM US1 ON MV.USER_CREATE = US1.USER_EMPLOYEE_CD ");
		sql.append(" LEFT JOIN USER_SYSTEM US2 ON MV.USER_UPDATE = US2.USER_EMPLOYEE_CD ");
		sql.append(" LEFT JOIN USER_SYSTEM US3 ON MV.DEPT_APPROVE = US3.USER_EMPLOYEE_CD ");
		sql.append(" LEFT JOIN USER_SYSTEM US4 ON MV.ACCOUNTANT_APPROVE = US4.USER_EMPLOYEE_CD ");
		sql.append(" LEFT JOIN USER_SYSTEM US5 ON MV.STOCK_APPROVE = US5.USER_EMPLOYEE_CD ");
		
		sql.append("  WHERE 1=1 AND MV.DELETE_FG = '0'");
		if(br != null)
		{
			if(Common.isNotEmpty(br.getCmpn_master().getCompany_cd()))
			{
				sql.append("  AND MV.MOVEMENT_CMPN_MASTER =").append("'"+ br.getCmpn_master().getCompany_cd()+"'");
			}
			if(Common.isNotEmpty(br.getCmpn_client().getCompany_cd()))
			{
				sql.append("  AND MV.MOVEMENT_CMPN_CLIENT =").append("'"+ br.getCmpn_client().getCompany_cd()+"'");
			}
			if(Common.isNotEmpty(br.getCoupon_cd()))
			{
				sql.append("  AND MV.MOVEMENT_ID =").append("'"+ br.getCoupon_cd()+"'");
			}
			if(Common.isNotEmpty(br.getDept_master().getDept_cd()))
			{
				sql.append("  AND MV.MOVEMENT_DEPT_MASTER =").append("'"+ br.getDept_master().getDept_cd()+"'");
			}
			
			//REPLACE(CONVERT(varchar,'2017-08-25', 111), '-','')
			if(Common.isNotEmpty(br.getDate_start()))
			{
				sql.append("  AND MV.MOVEMENT_START >= ").append("REPLACE(CONVERT(varchar,'"+br.getDate_start()+"', 111), '-','')");
			}
			if(Common.isNotEmpty(br.getDate_start_e()))
			{
				sql.append("  AND MV.MOVEMENT_START <= ").append("REPLACE(CONVERT(varchar,'"+br.getDate_start_e()+"', 111), '-','')");
			}
			if(Common.isNotEmpty(br.getStatus()))
			{
				sql.append("  AND MV.STATUS >= ").append("'"+br.getStatus()+"'");
			}
			
			
		}
		sql.append("  ORDER BY MV.MOVEMENT_NUMBER ");
		
		
		
		
		return sql.toString();
	}
}
