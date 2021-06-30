package com.ams.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.InventoryCheckingSelectDao;
import com.ams.dao.InventorySessionSelectDao;
import com.ams.model.AssetManagementTable;
import com.ams.model.InventoryChecking;
import com.ams.model.InventorySession;
import com.ams.model.InventorySessionModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;

@Controller
@RequestMapping("InventorySessionManagement")
public class InventorySessionManagementContorller {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView CompanyInsert(ModelMap modelMap, HttpServletRequest request) throws SQLException
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject("TittleScreen","MÀN HÌNH QUẢN LÝ PHIÊN KIỂM KÊ");
		mv.setViewName("views/InventorySessionManagement.jsp");
		InventorySessionModel ivnModel = new InventorySessionModel();
		ivnModel.setInventorySessionCompanyCD(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		InventorySessionSelectDao inventorySessionSelectDao = new InventorySessionSelectDao(ivnModel);
		try {
			List<InventorySessionModel> lstInventorySession = inventorySessionSelectDao.excute();
			if(lstInventorySession.size() > 0)
			{
				mv.addObject("lstInventorySession",lstInventorySession);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			mv.addObject("message",e.toString());
		}
		return mv;
	}
	
	@RequestMapping(params = "search", method = RequestMethod.POST)
	public ModelAndView search(ModelMap modelMap, HttpServletRequest request) throws SQLException
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject("TittleScreen","MÀN HÌNH QUẢN LÝ KIỂM KÊ");
		String session_id = request.getParameter("InventoryID");
		mv.addObject("InventoryID", session_id);
		InventoryChecking inventoryChecking = new InventoryChecking();
		inventoryChecking.setInventorySessionCD(session_id);
	    inventoryChecking.setCompany_cd((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
	    inventoryChecking.setDepartment(request.getParameter("select_department"));
	    inventoryChecking.setAsset_name(request.getParameter("text_asset_name"));
		InventoryCheckingSelectDao inventoryCheckingSelectDao = new InventoryCheckingSelectDao(inventoryChecking);
		List<AssetManagementTable> lstChecking = inventoryCheckingSelectDao.excuteAssetManagementTable();
	//	CheckingAssetNew checkingAssetNew = new CheckingAssetNew();
	//	checkingAssetNew.setChecking_session(inventoryChecking.getInventory_Session_CD());
	//	CheckingAsssetNewSelectDao checkingAsssetNewSelectDao = new CheckingAsssetNewSelectDao(checkingAssetNew);
	//	List<AssetManagementTable> lstChecking2 = checkingAsssetNewSelectDao.excuteAssetManagementTable();
//		if(lstChecking2.size()>0)
//		{
//			for(int i=0;i<lstChecking2.size();i++)
//			{
//				lstChecking.add(lstChecking2.get(i));
//			}
//		}
		if(lstChecking.size() > 0)
		{
			mv.addObject("lstChecking", lstChecking);
			mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "TÌM THẤY  "+ lstChecking.size() +" TÀI SẢN KIỂM KÊ");
		}
		else
		{
			mv.addObject(ParamsConstants.MESSAGE_ERROR, "KHÔNG TÌM THẤY MÃ KIỂM KÊ NÀO");
		}
		
		
		mv.setViewName("views/InventoryManagement.jsp");
		
		return mv;
	}
	@RequestMapping(params = "register", method = RequestMethod.POST)
	public ModelAndView register(ModelMap modelMap, HttpServletRequest request) throws SQLException
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/InventorySessionRegister");
		return mv;
	}
	
	@RequestMapping(params = "back", method = RequestMethod.POST)
	public ModelAndView back(ModelMap modelMap, HttpServletRequest request) throws SQLException
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/FeatureSystem");
			
		return mv;
	}
	
	
}
