package com.ams.returnasset;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.AssetGeneralModel;
import com.ams.model.BorrowAssetModel;
import com.ams.model.CompanyModel;
import com.ams.model.Department;
import com.ams.model.ReturnAssetModel;
import com.ams.util.Common;



public class ReturnAssetSelectDao {

	ReturnAssetModel borrowAssetModel = null;

	public ReturnAssetSelectDao() {

	}

	public ReturnAssetSelectDao(ReturnAssetModel borrowAssetModel) {
		this.borrowAssetModel = borrowAssetModel;
	}

	public List<ReturnAssetModel> excute() throws SQLException {
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		System.out.println(getSQL());
		result = stmt.executeQuery(getSQL());
		List<ReturnAssetModel> lstAsset =new ArrayList<ReturnAssetModel>();
		while (result.next()) {
			ReturnAssetModel bam = new ReturnAssetModel();
			AssetGeneralModel asset = new AssetGeneralModel();
			asset.setRFID(result.getString("RFID"));
			asset.setName(result.getString("ASSET_NAME"));
			asset.setSeries(result.getString("ASSET_SERIES"));
			asset.setId(result.getString("ASSET_CD"));
			asset.setModel(result.getString("ASSET_MODEL"));
			bam.setAsset(asset);
			
			bam.setId(result.getString("BORROW_CD"));
			bam.setUserInsert(result.getString("USER_INSERT"));
			bam.setUserUpdate(result.getString("USER_UPDATE"));
			bam.setUserApprove(result.getString("USER_APPROVE"));
			bam.setUserConfirm(result.getString("USER_CONFIRM"));
			
			bam.setInsertDt(result.getString("INSERT_DT"));
			bam.setUpdateDt(result.getString("UPDATE_DT"));
			bam.setApproveDt(result.getString("APPROVE_DT"));
			bam.setConfirmDt(result.getString("CONFIRM_DT"));
			
			
			Department dept_master = new Department();
//			dept_master.setDept_cd(result.getString("DEPT_MASTER"));
//			dept_master.setDept_name(result.getString("DEPT_MASTER_NAME"));
			bam.setDept_master(dept_master);
			
			Department dept_client = new Department();
//			dept_client.setDept_cd(result.getString("DEPT_CLIENT"));
//			dept_client.setDept_name(result.getString("DEPT_CLIENT_NAME"));
			bam.setDept_client(dept_client);
			
			CompanyModel cmpn_master = new CompanyModel();
//			cmpn_master.setCompany_cd(result.getString("CMPN_MASTER"));
//			cmpn_master.setCompany_shortname(result.getString("CMPN_MASTER_SHORT_NAME"));
			bam.setCmpn_master(cmpn_master);
			
			CompanyModel cmpn_client = new CompanyModel();
//			cmpn_client.setCompany_cd(result.getString("CMPN_CLIENT"));
//			cmpn_client.setCompany_shortname(result.getString("CMPN_CLIENT_SHORT_NAME"));
			bam.setCmpn_client(cmpn_client);
			

			bam.setDate_start(result.getString("DATE_START"));
			bam.setDate_end(result.getString("DATE_END"));
			bam.setDate_pay(result.getString("DATE_PAY"));
			bam.setReason(result.getString("REASON"));
			bam.setStatus(result.getString("STATUS"));
			bam.setNumber_on(result.getString("NUMBER_ON"));
			if(Common.isNotEmpty(result.getString("REASON_NOT_ALLOW")))
			{
				bam.setReason_not_allow(result.getString("REASON_NOT_ALLOW"));
			} else {
				bam.setReason_not_allow("");
			}
			
			
			bam.getBorrowCoupon().setCoupon_cd(result.getString("COUPON_CD"));
			bam.getAsset().setDepartment_name(result.getString("DEPARTMENT_NAME"));
			bam.getAsset().setStatus(result.getString("ASSET_STATUS"));
			bam.setId(result.getString("BORROW_CD"));
			bam.setAsseseries(result.getString("ASSESSERIES"));
			lstAsset.add(bam);
		}
		return lstAsset;
	}

	public String getSQL()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT ");
		sql.append("   CMPN_MASTER,");
		sql.append("   BORROW_CD ,");
		sql.append("   DEPT_MASTER ,");
		sql.append("   DATE_START ,");
		sql.append("   DATE_END,");
		sql.append("   DATE_PAY,");
		sql.append("   ASSET_NAME ,");
		sql.append("   RS.ASSET_RFID AS RFID,");
		sql.append("   RS.ASSET_CD AS ASSET_CD,");
		sql.append("   ASSET_SERIES ,");
		sql.append("   ASSET_MODEL ,");
		sql.append("   CMPN_CLIENT,");
		sql.append("   DEPT_CLIENT,");
		sql.append("  REASON, ");
		sql.append("  STATUS, ");
		sql.append("  RS.USER_INSERT, ");
		sql.append("  RS.INSERT_DT, ");
		sql.append("  RS.USER_UPDATE, ");
		sql.append("  RS.UPDATE_DT, ");
		sql.append("  RS.USER_APPROVE, ");
		sql.append("  RS.APPROVE_DT, ");
		sql.append("  RS.USER_CONFIRM, ");
		sql.append("  RS.CONFIRM_DT, ");
		sql.append("  RS.NUMBER_ON, ");
		sql.append("  RS.REASON_NOT_ALLOW, ");
		sql.append("  RS.COUPON_CD, ");
		sql.append("  DPT.DEPARTMENT_NAME, ");
		sql.append("  AG.ASSET_STATUS, ");  
		sql.append("  RS.ASSESSERIES ");
		
		sql.append(" FROM RETURN_ASSET RS");		
		sql.append(" INNER JOIN ASSETS_GENERAL AG ");
		sql.append(" ON AG.ASSET_RFID =RS.ASSET_RFID ");
		sql.append(" AND AG.ASSET_CD = RS.ASSET_CD");
		sql.append(" INNER JOIN DEPRATMENT DPT ON DPT.DEPT_CD = AG.ASSET_DEPARTMENT ");
		
		sql.append(" WHERE 1=1 AND RS.DELETE_FG = '0' ");
		if(borrowAssetModel!=null)
		{
			if(borrowAssetModel.getCmpn_master().getCompany_cd() != null)
			{
				sql.append("AND RS.CMPN_MASTER = ").append("'"+borrowAssetModel.getCmpn_master().getCompany_cd()+"'");
			}
			if(borrowAssetModel.getCmpn_client().getCompany_cd() != null)
			{
				sql.append("AND RS.CMPN_CLIENT= ").append("'"+borrowAssetModel.getCmpn_client().getCompany_cd()+"'");
			}
			if(borrowAssetModel.getId() != null && borrowAssetModel.getId().trim().length()>0)
			{
				sql.append("AND RS.BORROW_CD = ").append("'"+ borrowAssetModel.getId()+"'");
			}
			if(borrowAssetModel.getAsset().getName()!=null && borrowAssetModel.getAsset().getName().trim().length()>0)
			{
				sql.append("AND AG.ASSET_NAME = ").append("'"+ borrowAssetModel.getAsset().getName()+"'");
			}
			if(borrowAssetModel.getAsset().getSeries()!=null && borrowAssetModel.getAsset().getSeries().trim().length()>0)
			{
				sql.append("AND AG.ASSET_SERIES = ").append("'"+ borrowAssetModel.getAsset().getSeries()+"'");
			}
			if(borrowAssetModel.getAsset().getModel()!=null && borrowAssetModel.getAsset().getModel().trim().length()>0)
			{
				sql.append("AND AG.ASSET_MODEL = ").append("'"+ borrowAssetModel.getAsset().getModel()+"'");
			}
			if(borrowAssetModel.getDate_start()!=null && borrowAssetModel.getDate_start().trim().length()>0)
			{
				sql.append("AND RS.DATE_START >= ").append("'"+ borrowAssetModel.getDate_start()+"'");
			}
			if(borrowAssetModel.getDate_start_end()!=null && borrowAssetModel.getDate_start_end().trim().length()>0)
			{
				sql.append("AND RS.DATE_START <= ").append("'"+ borrowAssetModel.getDate_start_end()+"'");
			}
			if(borrowAssetModel.getDept_master().getDept_cd()!=null && borrowAssetModel.getDept_master().getDept_cd().trim().length()>0)
			{
				sql.append("AND RS.DEPT_MASTER = ").append("'"+ borrowAssetModel.getDept_master().getDept_cd()+"'");
			}
			if(borrowAssetModel.getDept_client().getDept_cd()!=null && borrowAssetModel.getDept_client().getDept_cd().trim().length()>0)
			{
				sql.append("AND RS.DEPT_CLIENT = ").append("'"+ borrowAssetModel.getDept_client().getDept_cd()+"'");
			}
			
			if(Common.isNotEmpty(borrowAssetModel.getNumber_on()))
			{
				sql.append("AND RS.NUMBER_ON = ").append("'"+ borrowAssetModel.getNumber_on()+"'");
			}
			if(Common.isNotEmpty(borrowAssetModel.getBorrowCoupon().getCoupon_cd()))
			{
				sql.append("AND RS.COUPON_CD = ").append("'"+ borrowAssetModel.getBorrowCoupon().getCoupon_cd()+"'");
			}
		}
		
		
		return sql.toString();
	}
}
