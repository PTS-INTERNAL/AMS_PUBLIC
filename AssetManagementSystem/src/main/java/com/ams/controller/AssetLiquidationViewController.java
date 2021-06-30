package com.ams.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.AssetGeneralDeleteUpdateDao;
import com.ams.dao.AssetGeneralSelectDao;
import com.ams.dao.AssetLiquidationInsertDao;
import com.ams.dao.AssetLiquidationModel;
import com.ams.dao.AssetLiquidationSelectDao;
import com.ams.dao.AssetLiquidationUpdateStatusDao;
import com.ams.dao.HistoryAssetDeleteInsertDao;
import com.ams.model.AssetGeneralFormSearch;
import com.ams.model.AssetGeneralModel;
import com.ams.model.HistoryAssetDeleteModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;

@Controller
@RequestMapping("/AssetLiquidationView")
public class AssetLiquidationViewController {
	
	String TTITLE = "MÀN HÌNH XEM THÔNG TIN THANH LÝ TÀI SẢN";
	
	@RequestMapping(params="view_asset" ,method = RequestMethod.POST)
	public ModelAndView init( HttpServletRequest request)
	{
		ModelAndView mv  =new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TTITLE);
		String liquiCd = request.getParameter("liquidation_cd");
		if(Common.isNotEmpty(liquiCd))
		{
			AssetLiquidationModel assets = new AssetLiquidationModel();
			assets.setLiquidation_Cd(liquiCd);
			
			AssetLiquidationSelectDao select = new AssetLiquidationSelectDao(assets);
			
			try {
				List<AssetLiquidationModel> lstAsset = select.excute();
				if(lstAsset.size()==1)
				{
					AssetLiquidationModel asset = lstAsset.get(0);
					mv.addObject("asset", lstAsset.get(0));
					mv.addObject("asset_name", asset.getAsset().getName());
					mv.addObject("asset_series", asset.getAsset().getSeries());
					mv.addObject("asset_model", asset.getAsset().getModel());
					mv.addObject("group_asset_cd", asset.getAsset().getGroup_id());
					mv.addObject("department_cd", asset.getAsset().getDepartment_cd());
					mv.addObject("reason", asset.getReason());
					mv.addObject("asset_note", asset.getNote());
					mv.addObject("asset_number", asset.getNumber());
					mv.addObject("group_asset_na", asset.getAsset().getGroup_name());
					mv.addObject("department_name", asset.getAsset().getDepartment_name());
					mv.addObject("asset_rfid",asset.getAsset().getRFID());
					mv.addObject("asset", asset);
				}
				else
				{
					mv.addObject(ParamsConstants.MESSAGE_ERROR, "Không tìm thấy tài sản");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		mv.setViewName("views/AssetLiquidationView.jsp");
		
		return mv;
	}
	
	@RequestMapping(params="save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request)
	{
		ModelAndView mv  =new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TTITLE);
		
		AssetGeneralModel asset = new AssetGeneralModel();
		
		asset.setName(request.getParameter("asset_name"));
		asset.setSeries(request.getParameter("asset_series"));
		asset.setModel(request.getParameter("asset_model"));
		asset.setGroup_id(request.getParameter("group_asset_cd"));
		asset.setDepartment_cd(request.getParameter("department_cd"));	
		String reason = request.getParameter("reason");
		String asset_note = request.getParameter("asset_note");	
		String asset_number = request.getParameter("asset_number");
		String group_name = request.getParameter("group_asset_na");
		String dept_name = request.getParameter("department_name");
		
		mv.addObject("asset_name", asset.getName());
		mv.addObject("asset_series", asset.getSeries());
		mv.addObject("asset_model", asset.getModel());
		mv.addObject("group_asset_cd", asset.getGroup_id());
		mv.addObject("department_cd", asset.getDepartment_cd());
		mv.addObject("reason", reason);
		mv.addObject("asset_note", asset_note);
		mv.addObject("asset_number", asset_number);
		mv.addObject("group_asset_na", group_name);
		mv.addObject("department_name", dept_name);
		
		AssetGeneralSelectDao selecAsset = new AssetGeneralSelectDao(asset);
		String message="";
		if(Common.isEmpty(asset.getName()))
		{
			message += "Tên tài sản là bắt buộc<br> ";
		}
		if(Common.isEmpty(asset.getSeries()))
		{
			message += "Số series là bắt buộc<br> ";
		}
		if(Common.isEmpty(asset.getModel()))
		{
			message += "Mã model là bắt buộc<br> ";
		}
		if(message.trim().length()==0)
		{
			try {
				List<AssetGeneralModel> lstAsset = selecAsset.excute();
				
				if(lstAsset.size()==1)
				{
					if(Common.isNotEmpty(reason))
					{
						AssetLiquidationModel assetLiqui = new AssetLiquidationModel();
						assetLiqui.setAsset(lstAsset.get(0));
						assetLiqui.setReason(reason);
						assetLiqui.setNote(asset_note);
						assetLiqui.setNumber(asset_number);
						assetLiqui.getAsset().setUser_insert_cd(Common.getSessionValue(request, SessionConstants.SESSION_USER_ID));
						
						AssetLiquidationInsertDao insertDao = new AssetLiquidationInsertDao(assetLiqui);
						insertDao.excute();
						mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "ĐĂNG KÝ THÀNH CÔNG");
					}
					else
					{
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "Lý do thanh lý tài sản là bắt buộc");
					}
				}
				else
				{
					mv.addObject(ParamsConstants.MESSAGE_ERROR, "Không tìm thấy tài sản");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mv.addObject(ParamsConstants.MESSAGE_ERROR, "LỖI khi đăng ký dữ liệu");

			}
		}
		else
		{
			mv.addObject(ParamsConstants.MESSAGE_ERROR, message);
		}
		
		mv.setViewName("views/AssetLiquidationRegister.jsp");
		
		return mv;
	}
	
	
	@RequestMapping(params="methodApprove" ,method = RequestMethod.POST)
	public ModelAndView methodApprove( HttpServletRequest request)
	{
		ModelAndView mv  =new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TTITLE);
		String liquiCd = request.getParameter("liquidation_cd");
		String ApproveRadio = request.getParameter("ApproveRadio");
		String reasonNotAllow = request.getParameter("reasonNotAlow");
		String rfid = request.getParameter("asset_rfid");
		mv.addObject("asset_rfid", rfid);
		
		boolean isValid = false;
		
		if(ApproveRadio.equals("3"))
		{
			if(Common.isEmpty(reasonNotAllow))
			{
				isValid = true;
				mv.addObject(ParamsConstants.MESSAGE_ERROR, "LÝ DO KHÔNG PHÊ DUYỆT LÀ BẮT BUỘC");
			}
				
		}
		else
		{
			if(ApproveRadio.equals("2"))
			{
				if(Common.isNotEmpty(reasonNotAllow))
				{
					isValid = true;
					mv.addObject(ParamsConstants.MESSAGE_ERROR, "Khi phê duyệt không cần lý do không duyệt");
				
					
				
				
				}
					
			}
		}
		if(!isValid)
		{
			//cap nhat giá trị
			AssetLiquidationModel assetes = new AssetLiquidationModel();
			assetes.setLiquidation_Cd(liquiCd);
			assetes.setStatus(ApproveRadio);
			assetes.setReason_not_allow(reasonNotAllow);
			assetes.setUserApprove(Common.getSessionValue(request, SessionConstants.SESSION_USER_ID));
			assetes.setDateApprove(Common.getDateCurrent(ParamsConstants.CD_FULL_DATE));
			AssetLiquidationUpdateStatusDao updateStatus = new AssetLiquidationUpdateStatusDao(assetes);
			try {
				updateStatus.excute();
				AssetGeneralFormSearch asset = new AssetGeneralFormSearch();
				
				asset.setRFID(rfid);
				asset.setCompany_CD((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
				AssetGeneralSelectDao assetGeneralSelectDao = new AssetGeneralSelectDao(asset);
				try {
					List<AssetGeneralModel> lst = assetGeneralSelectDao.excute();
					if(lst.size() == 0)
					{
						mv.addObject(ParamsConstants.MESSAGE_ERROR,"Không tìm thấy tài sản khớp với mã");
					}
					else
					{
						if(lst.size()>1)
						{
							mv.addObject(ParamsConstants.MESSAGE_ERROR,"Tìm thấy 2 tài sản tương tự<br>Hãy kiểm tra lại");
						}
						else
						{
							if(lst.size()==1)
							{
								HistoryAssetDeleteModel historyDel = new HistoryAssetDeleteModel();
								
								if(ApproveRadio.equals("2"))
								{
									historyDel.setDelete_reason("THANH LÝ TÀI SẢN");
								}
								else
								{
									historyDel.setDelete_reason("HỦY BỎ THANH LÝ TÀI SẢN");
								}
								if(historyDel.getDelete_reason().trim().length()>0)
								{
									historyDel.setAsset(lst.get(0));
									historyDel.setDelete_cd(Common.getDateCurrent("YYYYMMddHHmmSS"));
									historyDel.setDelete_insert_dt(Common.getDateCurrent("YYYYHHdd"));
									historyDel.setDelete_user_insert((String) request.getSession().getAttribute(SessionConstants.SESSION_USER_ID));
									
									lst.get(0).setUser_update_cd(Common.getSessionValue(request, SessionConstants.SESSION_USER_ID));
									lst.get(0).setUpdate_dt(Common.getDateCurrent("YYYYMMdd"));
									if(ApproveRadio.equals("2"))
									{
										lst.get(0).setDelete_Fg("1");
									}
									else
									{
										lst.get(0).setDelete_Fg("0");
									}

									AssetGeneralDeleteUpdateDao  updateDelete = new AssetGeneralDeleteUpdateDao(lst.get(0));
									updateDelete.excuteUpdateDeleteData();							
								}
								
							
							}
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					
					
					e.printStackTrace();
					mv.setViewName("redirect:/Error");
					return mv;
					
				}
				mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "XÉT DUYỆT THÀNH CÔNG");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		if(Common.isNotEmpty(liquiCd))
		{
			AssetLiquidationModel assets = new AssetLiquidationModel();
			assets.setLiquidation_Cd(liquiCd);
			
			AssetLiquidationSelectDao select = new AssetLiquidationSelectDao(assets);
			
			try {
				List<AssetLiquidationModel> lstAsset = select.excute();
				if(lstAsset.size()==1)
				{
					AssetLiquidationModel asset = lstAsset.get(0);
					mv.addObject("asset", lstAsset.get(0));
					mv.addObject("asset_name", asset.getAsset().getName());
					mv.addObject("asset_series", asset.getAsset().getSeries());
					mv.addObject("asset_model", asset.getAsset().getModel());
					mv.addObject("group_asset_cd", asset.getAsset().getGroup_id());
					mv.addObject("department_cd", asset.getAsset().getDepartment_cd());
					mv.addObject("reason", asset.getReason());
					mv.addObject("asset_note", asset.getNote());
					mv.addObject("asset_number", asset.getNumber());
					mv.addObject("group_asset_na", asset.getAsset().getGroup_name());
					mv.addObject("department_name", asset.getAsset().getDepartment_name());
					
					mv.addObject("asset", asset);
				}
				else
				{
					mv.addObject(ParamsConstants.MESSAGE_ERROR, "Không tìm thấy tài sản");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		mv.setViewName("views/AssetLiquidationView.jsp");
		return mv;
	}
	
	
	
	
	@RequestMapping(params="back", method = RequestMethod.POST)
	public ModelAndView back(HttpServletRequest request)
	{
		ModelAndView mv  =new ModelAndView();
		mv.setViewName("redirect:/AssetLiquidationManagement");
		
		return mv;
	}
}
