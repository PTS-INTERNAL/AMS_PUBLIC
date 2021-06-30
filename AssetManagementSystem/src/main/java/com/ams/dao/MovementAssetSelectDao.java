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
import com.ams.model.MovementAssetModel;
import com.ams.util.Common;



public class MovementAssetSelectDao {

	MovementAssetModel mov = null;

	public MovementAssetSelectDao() {

	}

	public MovementAssetSelectDao(MovementAssetModel mov) {
		this.mov = mov;
	}

	public List<MovementAssetModel> excute() throws SQLException {
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		System.out.println(getSQL());
		result = stmt.executeQuery(getSQL());
		List<MovementAssetModel> lstAsset =new ArrayList<MovementAssetModel>();
		while (result.next()) {
			
			MovementAssetModel item = new MovementAssetModel();
			item.setId(result.getString("MOVEMENT_CD"));
		      item.setNumber_on(result.getString("NUMBER_ON"));
		      item.getCmpn_master().setCompany_cd(result.getString("CMPN_MASTER"));
		      item.getCmpn_master().setCompany_name(result.getString("CMPN_NAME_MASTER"));
		      item.getDept_master().setDept_cd(result.getString("DEPT_MASTER"));
		      item.getDept_master().setDept_name(result.getString("DEPT_NAME_MASTER"));
		      item.getCmpn_client().setCompany_cd(result.getString("CMPN_CLIENT"));
		      item.getCmpn_client().setCompany_name(result.getString("CMPN_NAME_CLIENT"));
		      item.getDept_client().setDept_cd(result.getString("DEPT_CLIENT"));
		      item.getDept_client().setDept_name(result.getString("DEPT_NAME_CLIENT"));
		      item.getAsset().setId(result.getString("ASSET_CD"));
		      item.getAsset().setRFID(result.getString("ASSET_RFID"));
		      item.getAsset().setName(result.getString("ASSET_NAME"));
		      item.getAsset().setSeries(result.getString("ASSET_SERIES"));
		      item.getAsset().setModel(result.getString("ASSET_MODEL"));
		      item.getAsset().setAccountant_CD(result.getString("ASSET_ACCOUNTANT"));
		      item.setDate_start(result.getString("DATE_START"));
		      item.setDate_end(result.getString("DATE_END"));
		      item.setDate_pay(result.getString("DATE_PAY"));
		      item.setNote(result.getString("NOTE"));
		      item.setReason(result.getString("REASON"));
		      item.setAsseseries(result.getString("ASSESSERIES"));
		      item.setUserInsert(result.getString("USER_INSERT"));
		      item.setInsertDt(result.getString("INSERT_DT"));
		      item.setUserUpdate(result.getString("USER_UPDATE"));
		      item.setUpdateDt(result.getString("UPDATE_DT"));
//		      result.getString("USER_APPROVE"));
//		      result.getString("APPROVE_DT"));
//		      result.getString("USER_ACCOUNTANT_APPROVE"));
//		      result.getString("ACCOUNTANT_APPROVE_DT"));
//		      result.getString("USER_STOCK_APPROVE"));
//		      result.getString("STOCK_APPROVE_DT"));
		      item.setStatus(result.getString("STATUS"));
		      item.setReason_not_allow(result.getString("REASON_NOT_ALLOW"));
//		      result.getString("STATUS_ALLOW"));
		      item.getMovementCoupon().setCoupon_cd(result.getString("COUPON_CD"));
		      item.setDelete_fg(result.getString("DELETE_FG"));
//		      result.getString("COUPON_CD_PALLENT"));
		      
		      if(Common.isEmpty(item.getAsset().getId()))
		      {
		    	  item.setOuter("on");
		      }

			lstAsset.add(item);
		}
		return lstAsset;
	}

	public String getSQL()
	{
		StringBuilder sql = new StringBuilder();
		
			  sql.append(" SELECT MOVEMENT_CD ");
		      sql.append(" ,NUMBER_ON ");
		      sql.append(" ,CMPN_MASTER ");
		      sql.append(" ,CMPN_NAME_MASTER ");
		      sql.append(" ,DEPT_MASTER ");
		      sql.append(" ,DEPT_NAME_MASTER ");
		      sql.append(" ,CMPN_CLIENT ");
		      sql.append(" ,CMPN_NAME_CLIENT ");
		      sql.append(" ,DEPT_CLIENT ");
		      sql.append(" ,DEPT_NAME_CLIENT ");
		      sql.append(" ,ASSET_CD ");
		      sql.append(" ,ASSET_RFID ");
		      sql.append(" ,ASSET_NAME ");
		      sql.append(" ,ASSET_SERIES ");
		      sql.append(" ,ASSET_MODEL ");
		      sql.append(" ,ASSET_ACCOUNTANT ");
		      sql.append(" ,DATE_START ");
		      sql.append(" ,DATE_END ");
		      sql.append(" ,DATE_PAY ");
		      sql.append(" ,NOTE ");
		      sql.append(" ,REASON ");
		      sql.append(" ,ASSESSERIES ");
		      sql.append(" ,USER_INSERT ");
		      sql.append(" ,INSERT_DT ");
		      sql.append(" ,USER_UPDATE ");
		      sql.append(" ,UPDATE_DT ");
		      sql.append(" ,USER_APPROVE ");
		      sql.append(" ,APPROVE_DT ");
		      sql.append(" ,USER_ACCOUNTANT_APPROVE ");
		      sql.append(" ,ACCOUNTANT_APPROVE_DT ");
		      sql.append(" ,USER_STOCK_APPROVE ");
		      sql.append(" ,STOCK_APPROVE_DT ");
		      sql.append(" ,STATUS ");
		      sql.append(" ,REASON_NOT_ALLOW ");
		      sql.append(" ,STATUS_ALLOW ");
		      sql.append(" ,COUPON_CD ");
		      sql.append(" ,DELETE_FG ");
		      sql.append(" ,COUPON_CD_PALLENT ");
			  sql.append(" FROM MOVEMENT_ASSET ");
			  sql.append(" WHERE 1=1 AND DELETE_FG = '0'");
			  if(mov != null)
			  {
				  if(Common.isNotEmpty(mov.getMovementCoupon().getCoupon_cd()))
				  {
					  sql.append(" AND  COUPON_CD = '").append(mov.getMovementCoupon().getCoupon_cd()).append("'");
				  }
			  }
		
		
		return sql.toString();
	}
}
