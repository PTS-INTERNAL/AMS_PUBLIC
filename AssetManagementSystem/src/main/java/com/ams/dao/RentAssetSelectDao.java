package com.ams.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.RentAsset;

public class RentAssetSelectDao {
	RentAsset rentAsset = null;

	public RentAssetSelectDao() {

	}

	public RentAssetSelectDao(RentAsset rentAsset) {
		this.rentAsset = rentAsset;
	}

	public List<RentAsset> excute() throws SQLException {
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		//System.out.println(getSQL());
		result = stmt.executeQuery(getSQL());
		List<RentAsset> lstAsset =new ArrayList<RentAsset>();
		while (result.next()) {
			RentAsset Asset = new RentAsset();
			Asset.setRent_cd(result.getString("RENT_CD"));
			Asset.getCompany_master().setCompany_cd(result.getString("COMPANY_CD"));
			Asset.getCompany_master().setCompany_name(result.getString("CMPN_SHORT_NAME"));
			Asset.getDept_master().setDept_cd(result.getString("DEPARTMENT_CD"));
			Asset.getDept_master().setDept_name(result.getString("DEPARTMENT_NAME"));
			Asset.getAsset().setName(result.getString("ASSET_NAME"));
			Asset.getAsset().setModel(result.getString("MODEL"));
			Asset.getAsset().setSeries(result.getString("SERIES"));
			Asset.getAsset().setAccountant_CD(result.getString("ACCOUNTANT_CD"));
			Asset.getComany_client().setCompany_name(result.getString("BUSSINESS_NAME"));
			Asset.getComany_client().setCompany_address(result.getString("BUSSINESS_ADDRESS"));
			Asset.setDate_start(result.getString("DATE_START"));
			Asset.setDate_end_schedual(result.getString("DATE_END_SCHEDUAL"));
			Asset.setDate_end_real(result.getString("DATE_END_REAL"));
			Asset.setStatus(result.getString("STATUS_RENT"));
			lstAsset.add(Asset);
		}
		
		return lstAsset;

	}

	public String getSQL()
	{
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append("    RENT_CD ");
		sql.append("   ,RA.CMPN_CD as COMPANY_CD ");
		sql.append("   ,CPN.CMPN_SHORT_NAME as CMPN_SHORT_NAME ");
		sql.append("   ,DEPARTMENT_CD ");
		sql.append("   ,DEPARTMENT_NAME ");
		sql.append("   ,ASSET_NAME ");
		sql.append("   ,MODEL ");
		sql.append("   ,SERIES ");
		sql.append("   ,STATUS_RENT ");
		sql.append("   ,ACCOUNTANT_CD ");
		sql.append("   ,BUSSINESS_NAME ");
		sql.append("   ,DATE_START ");
		sql.append("   ,BUSSINESS_ADDRESS ");
		sql.append("   ,DATE_END_SCHEDUAL ");
		sql.append("   ,DATE_END_REAL ");
		sql.append(" FROM RENT_ASSET RA,DEPRATMENT DT, COMPANY  CPN");
		sql.append(" WHERE RA.DEPARTMENT_CD = DT.DEPT_CD AND RA.CMPN_CD = CPN.CMPN_CD");
		if(rentAsset!=null)
		{
			if(rentAsset.getRent_cd() != null && rentAsset.getRent_cd().trim().length()>0)
			{
				sql.append(" AND RA.RENT_CD =  ").append("'"+ rentAsset.getRent_cd()+"'");
			}
			if(rentAsset.getCompany_master().getCompany_cd() != null && rentAsset.getCompany_master().getCompany_cd().trim().length()>0)
			{
				sql.append(" AND RA.CMPN_CD =  ").append("'"+ rentAsset.getCompany_master().getCompany_cd()+"'");
			}
			if(rentAsset.getDept_master().getDept_cd()!=null && rentAsset.getDept_master().getDept_cd().trim().length()>0)
			{
				sql.append(" AND RA.DEPARTMENT_CD =  ").append("'"+ rentAsset.getDept_master().getDept_cd()+"'");
			}
			if(rentAsset.getAsset().getName() !=null && rentAsset.getAsset().getName().trim().length()>0)
			{
				sql.append(" AND RA.ASSET_NAME =  ").append("'"+ rentAsset.getAsset().getName()+"'");
			}
			if(rentAsset.getAsset().getSeries() !=null && rentAsset.getAsset().getSeries().trim().length()>0)
			{
				sql.append(" AND RA.SERIES =  ").append("'"+ rentAsset.getAsset().getSeries()+"'");
			}
			if(rentAsset.getAsset().getModel() !=null && rentAsset.getAsset().getModel().trim().length()>0)
			{
				sql.append(" AND RA.MODEL =  ").append("'"+ rentAsset.getAsset().getModel()+"'");
			}
			if(rentAsset.getAsset().getAccountant_CD() !=null && rentAsset.getAsset().getAccountant_CD().trim().length()>0)
			{
				sql.append(" AND RA.ACCOUNTANT_CD =  ").append("'"+ rentAsset.getAsset().getAccountant_CD()+"'");
			}
			if(rentAsset.getComany_client().getCompany_name()!=null && rentAsset.getComany_client().getCompany_name().trim().length()> 0 )
			{
				sql.append(" AND CPN.CMPN_SHORT_NAME =  ").append("'"+ rentAsset.getComany_client().getCompany_name()+"'");
			}
			
			if(rentAsset.getDate_start()!=null &&  rentAsset.getDate_start().trim().length()>0)
			{
				sql.append(" AND RA.DATE_START >=  ").append("'"+ rentAsset.getDate_start()+"'");
			}
			if(rentAsset.getDate_start_end()!=null &&  rentAsset.getDate_start_end().trim().length()>0)
			{
				sql.append(" AND RA.DATE_START <=  ").append("'"+ rentAsset.getDate_start_end()+"'");
			}
			if(rentAsset.getDate_end_schedual()!=null &&  rentAsset.getDate_end_schedual().trim().length()>0)
			{
				sql.append(" AND RA.DATE_END_SCHEDUAL >=  ").append("'"+ rentAsset.getDate_end_schedual()+"'");
			}
			if(rentAsset.getDate_end_schedual_end()!=null &&  rentAsset.getDate_end_schedual_end().trim().length()>0)
			{
				sql.append(" AND RA.DATE_END_SCHEDUAL <=  ").append("'"+ rentAsset.getDate_end_schedual_end()+"'");
			}
			if(rentAsset.getDate_end_real()!=null &&  rentAsset.getDate_end_real().trim().length()>0)
			{
				sql.append(" AND RA.DATE_END_REAL >=  ").append("'"+ rentAsset.getDate_end_real()+"'");

			}
			if(rentAsset.getDate_end_real_end()!=null &&  rentAsset.getDate_end_real_end().trim().length()>0)
			{
				sql.append(" AND RA.DATE_END_REAL <=  ").append("'"+ rentAsset.getDate_end_real_end()+"'");
			}
			
			
		}
		
		
		return sql.toString();
	}
}
