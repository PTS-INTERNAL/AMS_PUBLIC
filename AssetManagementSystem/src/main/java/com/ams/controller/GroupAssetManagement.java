package com.ams.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.GroupAssetInsertDao;
import com.ams.dao.GroupAssetSelectDao;
import com.ams.model.GroupAsset;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;



@Controller
@RequestMapping("/GroupAssetManagement")
public class GroupAssetManagement {
	String TITLE = "MÀN HÌNH QUẢN LÝ NHÓM TÀI SẢN";
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		mv.setViewName("views/GroupAssetManagement.jsp");
		GroupAsset groupAsset = new GroupAsset();
	
		groupAsset.setCompany_cd((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
		GroupAssetSelectDao groupAssetSelectDao = new GroupAssetSelectDao(groupAsset);
		List<GroupAsset> lstDept = new ArrayList<GroupAsset>();
		
		try {
			lstDept = groupAssetSelectDao.excute();
			mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "TÌM THẤY " + lstDept.size() + " NHÓM TÀI SẢN");
			if(lstDept.size()>0)
			{
				mv.addObject("lst", lstDept);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			mv.addObject(ParamsConstants.MESSAGE_ERROR, e.toString());
		}
		return mv;
	}
	
	@RequestMapping(params = "save" , method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, Model model)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		GroupAsset dept = new GroupAsset();
		dept.setCompany_cd((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
		dept.setGroup_id(Common.getDateCurrent("YYYYMMddHHmmSS"));
		dept.setGroup_name(request.getParameter("department_name"));
		dept.setGroup_desciption(request.getParameter("department_describe"));
		if(Common.isNotCheckEmpty(dept.getGroup_name()) == false)
		{
			mv.addObject(ParamsConstants.MESSAGE_ERROR, "KHÔNG ĐƯỢC BỎ TRỐNG TÊN NHÓM TÀI SẢN");
		}
		else
		{
			GroupAssetInsertDao insert = new GroupAssetInsertDao(dept);
			try {
				insert.excute();
				mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "THÊM THÀNH CÔNG");
				mv.setViewName("redirect:/GroupAssetManagement");
				return mv;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				mv.addObject(ParamsConstants.MESSAGE_ERROR, "THÊM ĐƠN VỊ BỊ LỖI");
				e.printStackTrace();
			}
			
		}
		mv.setViewName("views/GroupAssetManagement.jsp");
			
			
		
		
		
		
		return mv;
	}
	
	//CompanySettingManagement
	@RequestMapping(params = "back" , method = RequestMethod.POST)
	public String back(HttpServletRequest request, Model model)
	{
		return "redirect:/FeatureSystem";
	}
}
