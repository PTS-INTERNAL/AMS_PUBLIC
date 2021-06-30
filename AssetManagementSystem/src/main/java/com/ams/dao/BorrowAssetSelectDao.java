package com.ams.dao;

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
import com.ams.util.Common;



public class BorrowAssetSelectDao {

	BorrowAssetModel borrowAssetModel = null;

	public BorrowAssetSelectDao() {

	}

	public BorrowAssetSelectDao(BorrowAssetModel borrowAssetModel) {
		this.borrowAssetModel = borrowAssetModel;
	}

	public List<BorrowAssetModel> excute() throws SQLException {
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		System.out.println(getSQL());
		result = stmt.executeQuery(getSQL());
		List<BorrowAssetModel> lstAsset =new ArrayList<BorrowAssetModel>();
		while (result.next()) {
			BorrowAssetModel bam = new BorrowAssetModel();
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
		sql.append("   BS.ASSET_RFID AS RFID,");
		sql.append("   BS.ASSET_CD AS ASSET_CD,");
		sql.append("   ASSET_SERIES ,");
		sql.append("   ASSET_MODEL ,");
		sql.append("   CMPN_CLIENT,");
		sql.append("   DEPT_CLIENT,");
		sql.append("  REASON, ");
		sql.append("  STATUS, ");
		sql.append("  BS.USER_INSERT, ");
		sql.append("  BS.INSERT_DT, ");
		sql.append("  BS.USER_UPDATE, ");
		sql.append("  BS.UPDATE_DT, ");
		sql.append("  BS.USER_APPROVE, ");
		sql.append("  BS.APPROVE_DT, ");
		sql.append("  BS.USER_CONFIRM, ");
		sql.append("  BS.CONFIRM_DT, ");
		sql.append("  BS.NUMBER_ON, ");
		sql.append("  BS.REASON_NOT_ALLOW, ");
		sql.append("  BS.COUPON_CD, ");
		sql.append("  DPT.DEPARTMENT_NAME, ");
		sql.append("  AG.ASSET_STATUS, ");  
		sql.append("  BS.ASSESSERIES ");
		
		sql.append(" FROM BORROW_ASSET BS");		
		sql.append(" INNER JOIN ASSETS_GENERAL AG ");
		sql.append(" ON AG.ASSET_RFID =BS.ASSET_RFID ");
		sql.append(" AND AG.ASSET_CD = BS.ASSET_CD");
		sql.append(" INNER JOIN DEPRATMENT DPT ON DPT.DEPT_CD = AG.ASSET_DEPARTMENT ");
		
		sql.append(" WHERE 1=1 AND BS.DELETE_FG = '0' ");
		if(borrowAssetModel!=null)
		{
			if(borrowAssetModel.getCmpn_master().getCompany_cd() != null)
			{
				sql.append("AND BS.CMPN_MASTER = ").append("'"+borrowAssetModel.getCmpn_master().getCompany_cd()+"'");
			}
			if(borrowAssetModel.getCmpn_client().getCompany_cd() != null)
			{
				sql.append("AND BS.CMPN_CLIENT= ").append("'"+borrowAssetModel.getCmpn_client().getCompany_cd()+"'");
			}
			if(borrowAssetModel.getId() != null && borrowAssetModel.getId().trim().length()>0)
			{
				sql.append("AND BS.BORROW_CD = ").append("'"+ borrowAssetModel.getId()+"'");
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
				sql.append("AND BS.DATE_START >= ").append("'"+ borrowAssetModel.getDate_start()+"'");
			}
			if(borrowAssetModel.getDate_start_end()!=null && borrowAssetModel.getDate_start_end().trim().length()>0)
			{
				sql.append("AND BS.DATE_START <= ").append("'"+ borrowAssetModel.getDate_start_end()+"'");
			}
			if(borrowAssetModel.getDept_master().getDept_cd()!=null && borrowAssetModel.getDept_master().getDept_cd().trim().length()>0)
			{
				sql.append("AND BS.DEPT_MASTER = ").append("'"+ borrowAssetModel.getDept_master().getDept_cd()+"'");
			}
			if(borrowAssetModel.getDept_client().getDept_cd()!=null && borrowAssetModel.getDept_client().getDept_cd().trim().length()>0)
			{
				sql.append("AND BS.DEPT_CLIENT = ").append("'"+ borrowAssetModel.getDept_client().getDept_cd()+"'");
			}
			
			if(Common.isNotEmpty(borrowAssetModel.getNumber_on()))
			{
				sql.append("AND BS.NUMBER_ON = ").append("'"+ borrowAssetModel.getNumber_on()+"'");
			}
			if(Common.isNotEmpty(borrowAssetModel.getBorrowCoupon().getCoupon_cd()))
			{
				sql.append("AND BS.COUPON_CD = ").append("'"+ borrowAssetModel.getBorrowCoupon().getCoupon_cd()+"'");
			}
			if(Common.isNotEmpty(borrowAssetModel.getAsset().getRFID()))
			{
				sql.append("AND BS.ASSET_RFID  = ").append("'"+ borrowAssetModel.getAsset().getRFID()+"'");
			}
			if(Common.isNotEmpty(borrowAssetModel.getMovementCoupon().getCoupon_cd()))
			{
				sql.append("AND BS.COUPON_CD = ").append("'"+ borrowAssetModel.getMovementCoupon().getCoupon_cd()+"'");
			}
		}
		
		
		return sql.toString();
	}
}
