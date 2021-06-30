package com.ams.dao.loan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.BorrowCouponModel;
import com.ams.model.LoanCouponModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;

public class LoanCouponSelectDao {

	LoanCouponModel br=null;
	String cmpn_cd = "";
	public LoanCouponSelectDao(LoanCouponModel br) {
		this.br = br;
	}
	
	
	
	public List<LoanCouponModel> excute() throws SQLException
	{
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		System.out.println(getSql());
		
		result = stmt.executeQuery(getSql());
		List<LoanCouponModel> lstbr = new ArrayList<>();
		while (result.next()) {
			LoanCouponModel brItem = new LoanCouponModel();
			
			brItem.setCoupon_cd(result.getString("COUPON_CD"));
			brItem.setNumber_no(result.getString("NUMBER_NO"));	
			brItem.setNo_cd(result.getString("NO_CD"));	
			brItem.setStatus(result.getString("STATUS"));	
			brItem.getCmpn_master().setCompany_cd(result.getString("CMPN_CD_MASTER"));	
			brItem.getDept_master().setDept_cd(result.getString("DEPT_CD_MASTER"));	
			brItem.getCmpn_client().setCompany_cd(result.getString("CMPN_CD_CLIENT"));	
			brItem.getDept_client().setDept_cd(result.getString("DEPT_CD_CLIENT"));	
			brItem.setDate_start(result.getString("DATE_START"));	
			brItem.setDate_end_schedule(result.getString("DATE_END_SCHEDULE"));	
			brItem.setDate_end_real(result.getString("DATE_END_REAL"));	
			brItem.setReason(result.getString("REASON"));	
			brItem.setApprove_comment(result.getString("APPROVE_COMMENT"));	
			brItem.setInsert_dt(result.getString("INSERT_DT"));	
			brItem.setInsert_user(result.getString("INSERT_USER"));	
			brItem.setUpdate_dt(result.getString("UPDATE_DT"));	
			brItem.setUpdate_user(result.getString("UPDATE_USER"));	
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
			brItem.setDt_approve(result.getString("DEPT_APPROVE_DT"));
			brItem.setDt_accountant(result.getString("ACCOUNT_APPROVE_DT"));
			brItem.setDt_stock(result.getString("STOCK_APPROVE_DT"));
			brItem.setReasonNotAllow(result.getString("REASON_NOT_ALLOW"));
			brItem.setCoupon_borrow_cd(result.getString("COUPON_BORROW_CD"));
			
			brItem.setReasonReturnAsset(result.getString("REASON_RETURN"));
			brItem.setCommentReturnAsset(result.getString("COMMENT_RETURN"));
			
			brItem.setUserDeptReturnApprove(result.getString("RETURN_DEPT_APPROVE_USER"));
			brItem.setUserAccountantReturnApprove(result.getString("RETURN_ACCOUNT_APPROVE_USER"));
			brItem.setUserStockReturnApprove(result.getString("RETURN_STOCK_APPROVE_USER"));
			
			brItem.setDtDeptReturnApprove(result.getString("DT_RETURN_DEPT_APPROVE"));
			brItem.setDtAccountantReturnApprove(result.getString("DT_RETURN_ACCOUNT_APPROVE"));
			brItem.setDtStockReturnApprove(result.getString("DT_RETURN_STOCK_APPROVE"));
						
			lstbr.add(brItem);
		}
		
		return lstbr;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT LC.* ");
		sql.append("  , CMPN1.CMPN_NAME AS CMPN_MASTER_NAME ");
		sql.append("  , CMPN1.CMPN_SHORT_NAME AS CMPN_MASTER_SHORT ");
		sql.append("  , CMPN2.CMPN_NAME AS CMPN_CLIENT_NAME ");
		sql.append("  , CMPN2.CMPN_SHORT_NAME AS CMPN_CLIENT_SHORT ");
		sql.append("  , DEPT1.DEPARTMENT_NAME AS DEPT_MASTER_NAME ");
		sql.append("  , DEPT2.DEPARTMENT_NAME AS DEPT_CLIENT_NAME ");
		sql.append("  , US1.USER_NAME AS USER_INSERT_NAME ");
		sql.append("  , US2.USER_NAME AS USER_UPDATE_NAME ");
		
		sql.append("  , US3.USER_NAME AS DEPT_APPROVE_USER_NAME ");
		sql.append("  , US4.USER_NAME AS ACCOUNT_APROVE_USER_NAME ");
		sql.append("  , US5.USER_NAME AS STOCK_APPROVE_USER_NAME ");
		
		sql.append("  FROM  ");
		sql.append("  LOAN_COUPON LC ");
		sql.append("  LEFT JOIN COMPANY CMPN1 ON LC.CMPN_CD_MASTER = CMPN1.CMPN_CD ");
		sql.append("  LEFT JOIN COMPANY CMPN2 ON LC.CMPN_CD_CLIENT = CMPN2.CMPN_CD ");
		sql.append("  LEFT JOIN DEPRATMENT DEPT1 ON LC.DEPT_CD_MASTER = DEPT1.DEPT_CD ");
		sql.append("  LEFT JOIN DEPRATMENT DEPT2 ON LC.DEPT_CD_CLIENT = DEPT2.DEPT_CD ");
		sql.append("  LEFT JOIN USER_SYSTEM US1 ON LC.INSERT_USER = US1.USER_EMPLOYEE_CD ");
		sql.append("  LEFT JOIN USER_SYSTEM US2 ON LC.UPDATE_USER = US2.USER_EMPLOYEE_CD ");
		
		sql.append("  LEFT JOIN USER_SYSTEM US3 ON LC.DEPT_APPROVE_USER = US3.USER_EMPLOYEE_CD ");
		sql.append("  LEFT JOIN USER_SYSTEM US4 ON LC.ACCOUNT_APROVE_USER = US4.USER_EMPLOYEE_CD ");
		sql.append("  LEFT JOIN USER_SYSTEM US5 ON LC.STOCK_APPROVE_USER = US5.USER_EMPLOYEE_CD ");
		
		sql.append("  WHERE 1=1 AND LC.DELETE_FG = '0'");
		if(br != null)
		{
			if(Common.isNotEmpty(br.getCmpn_master().getCompany_cd()))
			{
				sql.append("  AND LC.CMPN_CD_MASTER =").append("'"+ br.getCmpn_master().getCompany_cd()+"'");
			}
			if(Common.isNotEmpty(br.getCmpn_client().getCompany_cd()))
			{
				sql.append("  AND LC.CMPN_CD_CLIENT =").append("'"+ br.getCmpn_client().getCompany_cd()+"'");
			}
			if(Common.isNotEmpty(br.getCoupon_cd()))
			{
				sql.append("  AND LC.COUPON_CD =").append("'"+ br.getCoupon_cd()+"'");
			}
			if(Common.isNotEmpty(br.getDept_master().getDept_cd()))
			{
				sql.append("  AND LC.DEPT_CD_MASTER =").append("'"+ br.getDept_master().getDept_cd()+"'");
			}
			
			//REPLACE(CONVERT(varchar,'2017-08-25', 111), '-','')
			if(Common.isNotEmpty(br.getDate_start()))
			{
				sql.append("  AND LC.DATE_START >= ").append("REPLACE(CONVERT(varchar,'"+br.getDate_start()+"', 111), '-','')");
			}
			if(Common.isNotEmpty(br.getDate_start_e()))
			{
				sql.append("  AND LC.DATE_START <= ").append("REPLACE(CONVERT(varchar,'"+br.getDate_start_e()+"', 111), '-','')");
			}
			if(Common.isNotEmpty(br.getNumber_no()))
			{
				sql.append("  AND LC.NUMBER_NO =").append("'"+ br.getNumber_no()+"'");
			}
			
			if(br.isAccountantApprove())
			{
				if(Common.isNotEmpty(br.getStatus())) {
					if(br.getStatus().trim().equals("8888"))
					{
						sql.append("  AND LC.STATUS =").append("'2'");
						sql.append("  OR LC.STATUS =").append("'9'");
						sql.append("  OR LC.STATUS =").append("'3'");
						sql.append("  OR LC.STATUS =").append("'4'");
					} else {
						sql.append("  AND LC.STATUS =").append("'"+ br.getStatus()+"'");
					}
				}
			} else {
				if(br.isStockApprrove())
				{
					if(Common.isNotEmpty(br.getStatus())) {
						if(br.getStatus().trim().equals("7777"))
						{
							sql.append("  AND LC.STATUS =").append("'3'");
							sql.append("  OR LC.STATUS =").append("'8'");
							sql.append("  OR LC.STATUS =").append("'4'");
						} else {
							sql.append("  AND LC.STATUS =").append("'"+ br.getStatus()+"'");
						}
					}
				} else {
					if(Common.isNotEmpty(br.getStatus())) {
						sql.append("  AND LC.STATUS =").append("'"+ br.getStatus()+"'");
					}
				}
			}
		}
		sql.append("  ORDER BY LC.NUMBER_NO ");
		
		
		
		
		return sql.toString();
	}
}
