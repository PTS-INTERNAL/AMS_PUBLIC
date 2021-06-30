
package com.ams.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ams.dao.AssetGeneralSelectDao;
import com.ams.dao.AssetTroubleInsertDao;
import com.ams.dao.ListLiquidationInsertDao;
import com.ams.model.AssetGeneralModel;
import com.ams.model.LiquidationListModel;
import com.ams.model.TroubleAssetModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;
import com.ams.util.UrlCommon;

@Controller
@RequestMapping("LiquidationListRegister")
public class LiquidationListRegisterController {
	
	String TITLESCREEN = "MÀN HÌNH ĐĂNG KÝ DANH SÁCH";
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request)
	{
		ModelAndView mv =new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLESCREEN);
		LiquidationListModel liquidlst = new LiquidationListModel();
		liquidlst.setCompary_cd(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		mv.setViewName("views/ListLiquidationRegister.jsp");
		
		return mv;
	}
	
	@RequestMapping(params="save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, RedirectAttributes att)
	{
		ModelAndView mv =new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLESCREEN);
		
		LiquidationListModel liquidList =new LiquidationListModel();
		
		liquidList.setCompary_cd(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		liquidList.setListName(request.getParameter("Liquid_List_name"));
		liquidList.setUserCreate(request.getParameter("user_create_liquid"));
		liquidList.setDateCreate(request.getParameter("date_create"));
		
		
		//validation
		String error = "";
		if(Common.isEmpty(liquidList.getListName()))
		{
			error += "TÊN DANH SÁCH LÀ BẮT BUỘC<br>"; 
		}
		if(Common.isEmpty(liquidList.getUserCreate()))
		{
			error += "NGƯỜI TẠO DANH SÁCH LÀ BẮT BUỘC<br>"; 
		}
		if(Common.isEmpty(liquidList.getDateCreate()))
		{
			error += "NGÀY TẠO TÀI SẢN LÀ BẮT BUỘC<br>"; 
		}
		
		
				
		if(error.trim().length()==0)
		{
			ListLiquidationInsertDao insert=new ListLiquidationInsertDao(liquidList);
			try {
				insert.excute();
				mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "ĐĂNG KÝ SỰ CỐ THÀNH CÔNG");
			}
			catch (Exception e) {
				// TODO: handle exception
				mv.addObject(ParamsConstants.MESSAGE_ERROR, e);
			}	
		}
		else
		{
			mv.addObject("List", liquidList);
			mv.addObject(ParamsConstants.MESSAGE_ERROR, error);
		}
				
		mv.setViewName("views/AssetTroubleRegister.jsp");
		return mv;
	}
	
	@RequestMapping(params="back", method = RequestMethod.POST)
	public ModelAndView back(HttpServletRequest request)
	{
		ModelAndView mv =new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLESCREEN);
		
		
		
		mv.setViewName("redirect:/AssetLiquidationManagement");
		return mv;
	}


}
