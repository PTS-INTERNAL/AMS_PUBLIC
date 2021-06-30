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
import com.ams.model.LoanAssetModel;
import com.ams.util.Common;



public class LoanAssetSelectDao {

	LoanAssetModel borrowAssetModel = null;

	public LoanAssetSelectDao() {

	}

	public LoanAssetSelectDao(LoanAssetModel borrowAssetModel) {
		this.borrowAssetModel = borrowAssetModel;
	}

	public List<LoanAssetModel> excute() throws SQLException {
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		System.out.println(getSQL());
		result = stmt.executeQuery(getSQL());
		List<LoanAssetModel> lstAsset =new ArrayList<LoanAssetModel>();
		while (result.next()) {
			LoanAssetModel bam = new LoanAssetModel();
			AssetGeneralModel asset = new AssetGeneralModel();
			asset.setRFID(result.getString("RFID"));
			asset.setName(result.getString("ASSET_NAME"));
			asset.setSeries(result.getString("ASSET_SERIES"));
			asset.setId(result.getString("ASSET_CD"));
			asset.setModel(result.getString("ASSET_MODEL"));
			bam.setAsset(asset);
			
			bam.setId(result.getString("LOAN_CD"));
			
			Department dept_master = new Department();
			dept_master.setDept_cd(result.getString("DEPT_MASTER"));
			dept_master.setDept_name(result.getString("DEPT_MASTER_NAME"));
			bam.setDept_master(dept_master);
			
			Department dept_client = new Department();
			dept_client.setDept_cd(result.getString("DEPT_CLIENT"));
			dept_client.setDept_name(result.getString("DEPT_CLIENT_NAME"));
			bam.setDept_client(dept_client);
			
//			CompanyModel cmpn_master = new CompanyModel();
//			cmpn_master.setCompany_cd(result.getString("CMPN_MASTER"));
//			cmpn_master.setCompany_shortname(result.getString("CMPN_MASTER_SHORT_NAME"));
//			bam.setCmpn_master(cmpn_master);
			
//			CompanyModel cmpn_client = new CompanyModel();
//			cmpn_client.setCompany_cd(result.getString("CMPN_CLIENT"));
//			cmpn_client.setCompany_shortname(result.getString("CMPN_CLIENT_SHORT_NAME"));
//			bam.setCmpn_client(cmpn_client);
			

			bam.setDate_start(result.getString("DATE_START"));
			bam.setDate_end(result.getString("DATE_END"));
			bam.setDate_pay(result.getString("DATE_PAY"));
			bam.setReason(result.getString("REASON"));
			bam.setDate_start(result.getString("DATE_START"));
			bam.setDate_end(result.getString("DATE_END"));
			bam.setDate_pay(result.getString("DATE_PAY"));
			bam.setReason(result.getString("REASON"));
			bam.setStatus(result.getString("STATUS"));
			bam.getLoanCoupon().setCoupon_cd(result.getString("COUPON_CD"));
			bam.setAsseseries(result.getString("ASSESSERIES"));
			lstAsset.add(bam);
		}
		
		return lstAsset;

	}

	public String getSQL()
	{
	StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT ");
		//sql.append("   CMPN_MASTER,");
		//sql.append("   CMPN.CMPN_SHORT_NAME AS CMPN_MASTER_SHORT_NAME ,");
		//sql.append("   CMPNCL.CMPN_SHORT_NAME AS CMPN_CLIENT_SHORT_NAME ,");
		sql.append("   DEPT_MAS.DEPARTMENT_NAME AS DEPT_MASTER_NAME,");
		sql.append("   DEPT_CLI.DEPARTMENT_NAME AS DEPT_CLIENT_NAME,");
		sql.append("   LOAN_CD ,");
		sql.append("   DEPT_MASTER ,");
		sql.append("   DATE_START ,");
		sql.append("   DATE_END,");
		sql.append("   DATE_PAY,");
		sql.append("   ASSET_NAME ,");
		sql.append("   LS.ASSET_RFID AS RFID,");
		sql.append("   LS.ASSET_CD AS ASSET_CD,");
		sql.append("   ASSET_SERIES ,");
		sql.append("   ASSET_MODEL ,");
		sql.append("   CMPN_CLIENT,");
		sql.append("   DEPT_CLIENT,");
		sql.append("  REASON, ");
		sql.append("  LS.STATUS, ");
		sql.append("  LS.USER_INSERT, ");
		sql.append("  LS.INSERT_DT, ");
		sql.append("  LS.USER_UPDATE, ");
		sql.append("  LS.UPDATE_DT, ");
		sql.append("  LS.USER_APPROVE, ");
		sql.append("  LS.APPROVE_DT, ");
		sql.append("  LS.USER_CONFIRM, ");
		sql.append("  LS.CONFIRM_DT, ");
		sql.append("  LS.COUPON_CD, ");
		sql.append("  LS.ASSESSERIES ");
		sql.append(" FROM LOAN_ASSET LS");
		//sql.append(" INNER JOIN COMPANY CMPN ON CMPN.CMPN_CD = CMPN_MASTER");
		//sql.append(" LEFT JOIN COMPANY CMPNCL ON CMPNCL.CMPN_CD = CMPN_CLIENT");
		sql.append(" INNER JOIN ASSETS_GENERAL AG ");
		sql.append(" ON AG.ASSET_RFID =LS.ASSET_RFID ");
		sql.append(" AND AG.ASSET_CD = LS.ASSET_CD");
		sql.append(" LEFT JOIN DEPRATMENT DEPT_MAS ");
		sql.append(" ON DEPT_MAS.DEPT_CD = LS.DEPT_MASTER");
		sql.append(" LEFT JOIN  DEPRATMENT DEPT_CLI ");
		sql.append(" ON DEPT_CLI.DEPT_CD = LS.DEPT_CLIENT");
		sql.append(" WHERE 1=1 AND LS.DELETE_FG = '0' ");
		if(borrowAssetModel!=null)
		{
			if(borrowAssetModel.getStatus() != null)
			{
				sql.append("AND LS.STATUS = ").append("'"+borrowAssetModel.getStatus()+"'");
			}
			if(borrowAssetModel.getCmpn_master().getCompany_cd() != null)
			{
				sql.append("AND LS.CMPN_MASTER = ").append("'"+borrowAssetModel.getCmpn_master().getCompany_cd()+"'");
			}
			if(borrowAssetModel.getCmpn_client().getCompany_cd() != null)
			{
				sql.append("AND LS.CMPN_CLIENT= ").append("'"+borrowAssetModel.getCmpn_client().getCompany_cd()+"'");
			}
			if(borrowAssetModel.getId() != null && borrowAssetModel.getId().trim().length()>0)
			{
				sql.append("AND LS.LOAN_CD = ").append("'"+ borrowAssetModel.getId()+"'");
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
				sql.append("AND LS.DATE_START >= ").append("'"+ borrowAssetModel.getDate_start()+"'");
			}
			if(borrowAssetModel.getDate_start_end()!=null && borrowAssetModel.getDate_start_end().trim().length()>0)
			{
				sql.append("AND LS.DATE_START <= ").append("'"+ borrowAssetModel.getDate_start_end()+"'");
			}
			if(borrowAssetModel.getDept_master().getDept_cd()!=null && borrowAssetModel.getDept_master().getDept_cd().trim().length()>0)
			{
				sql.append("AND LS.DEPT_MASTER = ").append("'"+ borrowAssetModel.getDept_master().getDept_cd()+"'");
			}
			if(borrowAssetModel.getDept_client().getDept_cd()!=null && borrowAssetModel.getDept_client().getDept_cd().trim().length()>0)
			{
				sql.append("AND LS.DEPT_CLIENT = ").append("'"+ borrowAssetModel.getDept_client().getDept_cd()+"'");
			}
			if(Common.isNotEmpty(borrowAssetModel.getLoanCoupon().getCoupon_cd()))
			{
				sql.append("AND LS.COUPON_CD = ").append("'"+ borrowAssetModel.getLoanCoupon().getCoupon_cd()+"'");

			}
			if(Common.isNotEmpty(borrowAssetModel.getAsset().getRFID()))
			{
				sql.append("AND LS.ASSET_RFID  = ").append("'"+ borrowAssetModel.getAsset().getRFID()+"'");
			}
		}
		
		
		return sql.toString();
	}
}
