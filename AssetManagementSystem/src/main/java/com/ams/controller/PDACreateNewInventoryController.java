package com.ams.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.CheckingAssetNewInsertDao;
import com.ams.model.CheckingAssetNew;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;



@Controller
@RequestMapping("/PDACreateNewInventory")
public class PDACreateNewInventoryController {
	
	@RequestMapping(method =RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request) throws UnsupportedEncodingException, ParseException
	{
		ModelAndView mv = new ModelAndView();
		String Session_ID = request.getParameter("session_id");
		mv.addObject("session_id", Session_ID);
		
		mv.setViewName("views/PDACreateNewInventory.jsp");
		return mv;
	}
	
	@RequestMapping(params = "save",method =RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request) throws UnsupportedEncodingException, ParseException
	{
		ModelAndView mv = new ModelAndView();
		String Session_ID = request.getParameter("InventorySessionCD");
		mv.addObject("session_id", Session_ID);
		
		request.setCharacterEncoding("UTF-8");
		CheckingAssetNew assetNew = new CheckingAssetNew();
		assetNew.setAsset_id(Common.getDateCurrent("YYYYMMddHHmmSS"));
		assetNew.setName(request.getParameter("asset_name"));
		assetNew.setRfid(request.getParameter("rfid"));
		assetNew.setDepartment(request.getParameter("department"));
		assetNew.setReason(request.getParameter("reason"));
		assetNew.setNote(request.getParameter("note"));
		assetNew.setChecking_session(request.getParameter("InventorySessionCD"));
		assetNew.setDateCreate(Common.getDateCurrent("dd/MM/YYYY"));
		assetNew.setUser((String)request.getSession().getAttribute(SessionConstants.SESSION_USER_ID));
		assetNew.setStatus("2");
		assetNew.setCmpn_cd((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
		CheckingAssetNewInsertDao checkingAssetNewInsertDao = new CheckingAssetNewInsertDao(assetNew);
		try {
			checkingAssetNewInsertDao.excute();
			mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "Thêm thành công");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			mv.addObject(ParamsConstants.MESSAGE_ERROR, e.toString());
		}
		
		
		mv.setViewName("views/PDACreateNewInventory.jsp");
		return mv;
	}

}
