package com.ams.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ams.dao.InventoryCheckingResultSelectDao;
import com.ams.dao.InventoryCheckingResultUpdateExpainDao;
import com.ams.model.InventoryCheckingRealtimeModel;
import com.ams.util.AuthenticationLogin;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.RoleCommon;
import com.ams.util.ServicesConstants;
import com.ams.util.SessionConstants;
import com.ams.util.UrlCommon;

@Controller
@RequestMapping("/InventoryCheckingResultView")
public class InventoryCheckingResultViewController {
	
	String TITLE = "MÀN HÌNH XEM THÔNG TIN TÀI SẢN KIỂM KÊ";
	
	@RequestMapping(params="view_asset" ,method=RequestMethod.POST)
	public ModelAndView init(HttpServletRequest request, RedirectAttributes redirect)
	{
		ModelAndView mv = new ModelAndView();		
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		String inventory_checking_cd = request.getParameter("inventory_checking_cd");
		
		mv.addObject("session_cd", inventory_checking_cd);
		
		String isExplain = "0";
		String isUpdate = "0";
		if(Common.isHavePermission(request, RoleCommon.R_ACCESS, ServicesConstants.EXPLAINT_INVENTORY))
		{
			isExplain="1";
		}
		if(Common.isHavePermission(request, RoleCommon.R_UPDATE, ServicesConstants.INVENTORY_CHECKING))
		{
			isUpdate="1";
		}
		mv.addObject("isExplain", isExplain);
		mv.addObject("isUpdate", isUpdate);
		
		if(Common.isNotCheckEmpty(inventory_checking_cd))
		{
			InventoryCheckingRealtimeModel asset  =new InventoryCheckingRealtimeModel();
			asset.getAsset().setCompany_CD(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
			asset.setInventory_Checking_Cd(inventory_checking_cd);
			InventoryCheckingResultSelectDao selectInv = new InventoryCheckingResultSelectDao(asset); 
			try {
				List<InventoryCheckingRealtimeModel> lstCheck = selectInv.excute();
				if(lstCheck.size()==1)
				{
					mv.addObject("asset", lstCheck.get(0));
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
		else
		{
			String asset_rfid = request.getParameter("asset_rfid");
			InventoryCheckingRealtimeModel asset  =new InventoryCheckingRealtimeModel();
			asset.getAsset().setCompany_CD(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
			asset.getAsset().setRFID(asset_rfid);
			InventoryCheckingResultSelectDao selectInv = new InventoryCheckingResultSelectDao(asset); 
			try {
				List<InventoryCheckingRealtimeModel> lstCheck = selectInv.excute();
				if(lstCheck.size()==1)
				{
					mv.addObject("asset", lstCheck.get(0));
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
		mv.setViewName("views/InventoryCheckingResultView.jsp");	
		return mv;
	}
	
	
	@RequestMapping(params="expain", method=RequestMethod.POST)
	public ModelAndView explain(HttpServletRequest request, RedirectAttributes redirect)
	{
		ModelAndView mv = new ModelAndView();		
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		String inventory_checking_cd = request.getParameter("inventory_checking_cd");
		mv.addObject("session_cd", inventory_checking_cd);
		String reason_expain = request.getParameter("reason_explain");
		String accept_radio = request.getParameter("accept_radio");
		String rfid = request.getParameter("asset_rfid");
		if(Common.isNotEmpty(inventory_checking_cd))
		{
			if(Common.isNotCheckEmpty(reason_expain))
			{
				
				InventoryCheckingRealtimeModel ivnModel = new InventoryCheckingRealtimeModel();
				ivnModel.setExpain_dt(Common.getDateCurrent(ParamsConstants.DATE_DB_FORMAT));
				ivnModel.setUser_explain(Common.getSessionValue(request, SessionConstants.SESSION_USER_ID));
				ivnModel.setInventory_Checking_Cd(inventory_checking_cd);
				ivnModel.setExplain(reason_expain);
				ivnModel.setStatus(accept_radio);
				InventoryCheckingResultUpdateExpainDao update  = new InventoryCheckingResultUpdateExpainDao(ivnModel);
				try {
					update.excute();
					mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "CẬP NHẬT GIẢI TRÌNH THÀNH CÔNG");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					mv.addObject(ParamsConstants.MESSAGE_ERROR, "CẬP NHẬT GIẢI TRÌNH KHÔNG THÀNH CÔNG");
				}
			}
			else
			{
				if(accept_radio.equals("1"))
				{
					mv.addObject(ParamsConstants.MESSAGE_ERROR, "LỖI KHÔNG CÓ LÝ DO GIẢI TRÌNH");
				}
			}
		}
		
		
	
		
		if(Common.isNotCheckEmpty(inventory_checking_cd))
		{
			InventoryCheckingRealtimeModel asset  =new InventoryCheckingRealtimeModel();
			asset.getAsset().setCompany_CD(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
			asset.setInventory_Checking_Cd(inventory_checking_cd);
			InventoryCheckingResultSelectDao selectInv = new InventoryCheckingResultSelectDao(asset); 
			try {
				List<InventoryCheckingRealtimeModel> lstCheck = selectInv.excute();
				if(lstCheck.size()==1)
				{
					mv.addObject("asset", lstCheck.get(0));
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
		else
		{
			String asset_rfid = request.getParameter("asset_rfid");
			InventoryCheckingRealtimeModel asset  =new InventoryCheckingRealtimeModel();
			asset.getAsset().setCompany_CD(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
			asset.getAsset().setRFID(asset_rfid);
			InventoryCheckingResultSelectDao selectInv = new InventoryCheckingResultSelectDao(asset); 
			try {
				List<InventoryCheckingRealtimeModel> lstCheck = selectInv.excute();
				if(lstCheck.size()==1)
				{
					mv.addObject("asset", lstCheck.get(0));
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
		mv.setViewName("views/InventoryCheckingResultView.jsp");	
		return mv;
	}

	
	
	@RequestMapping(params = "back", method = RequestMethod.POST)
	public ModelAndView back(ModelMap modelMap, HttpServletRequest request) throws SQLException
	{
		ModelAndView mv = new ModelAndView();
		request.getSession().setAttribute("session_cd", request.getParameter("session_cd"));
		mv.setViewName("redirect:/InventoryCheckingResult");
			
		return mv;
	}
	
	
	
}
