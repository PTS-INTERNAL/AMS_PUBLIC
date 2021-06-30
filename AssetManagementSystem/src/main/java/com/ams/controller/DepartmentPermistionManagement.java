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

import com.ams.dao.DepartmentInsertDao;
import com.ams.dao.DepartmentSelectDao;
import com.ams.dao.UserDeptPermissionInsertDao;
import com.ams.dao.UserDeptPermissionSelectDao;
import com.ams.dao.UserDeptPermissionUpdateDao;
import com.ams.model.Department;
import com.ams.model.UserDeptPermissionModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;


@Controller
@RequestMapping("/DepartmentPermistionManagement")
public class DepartmentPermistionManagement {
	String TITLE = "MÀN HÌNH QUẢN LÝ PHÂN QUYỀN ĐƠN VỊ";
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		mv.setViewName("views/DepartmentPermistionManagement.jsp");
		return mv;
	}
	
	@RequestMapping(params = "search" , method = RequestMethod.POST)
	public ModelAndView search(HttpServletRequest request, Model model)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		
		String cmpn_cd = request.getParameter("cmpn_cd");
		String cmpn_na = request.getParameter("cmpn_na");
		String user_cd = request.getParameter("user_cd");
		String user_na = request.getParameter("user_na");
		
		mv.addObject("cmpn_cd", cmpn_cd);
		mv.addObject("cmpn_na", cmpn_na);
		mv.addObject("user_cd", user_cd);
		mv.addObject("user_na", user_na);
		
		String error = "";
		
		if(Common.isEmpty(cmpn_cd))
		{
			error += "Vui lòng chọn công ty phần quyền<br>";
		}
		if(Common.isEmpty(user_cd))
		{
			error += "Vui lòng chọn người dùng phần quyền";
		}
		
		if(error.trim().length()>0)
		{
			mv.addObject(ParamsConstants.MESSAGE_ERROR, error.trim());
		} else {
			UserDeptPermissionModel userDept = new UserDeptPermissionModel();
			userDept.setCmpn_cd(cmpn_cd);
			userDept.setUser_cd(user_cd);
			
			UserDeptPermissionSelectDao userDelect = new UserDeptPermissionSelectDao(userDept);
			try {
				List<UserDeptPermissionModel> lst = userDelect.excute();
				
				if(lst.size()>0)
				{
					mv.addObject("lst", lst);
				} else {
					mv.addObject(ParamsConstants.MESSAGE_ERROR, "KHÔNG TÌM THẤY ĐƠN VỊ PHÂN QUYỀN NÀO");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		mv.setViewName("views/DepartmentPermistionManagement.jsp");
			
			
		
		
		
		
		return mv;
	}
	
	
	@RequestMapping(params = "btnSave" , method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, Model model)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		
		String cmpn_cd = request.getParameter("cmpn_cd");
		String cmpn_na = request.getParameter("cmpn_na");
		String user_cd = request.getParameter("user_cd");
		String user_na = request.getParameter("user_na");
		String isAllow = request.getParameter("isAllow");
		String perCd = request.getParameter("per_cd");
		String deptCd = request.getParameter("dept_cd");
		
		mv.addObject("cmpn_cd", cmpn_cd);
		mv.addObject("cmpn_na", cmpn_na);
		mv.addObject("user_cd", user_cd);
		mv.addObject("user_na", user_na);
		
		String error = "";
		
		if(Common.isEmpty(cmpn_cd))
		{
			error += "Vui lòng chọn công ty phần quyền<br>";
		}
		if(Common.isEmpty(user_cd))
		{
			error += "Vui lòng chọn người dùng phần quyền";
		}
		
		if(error.trim().length()>0)
		{
			mv.addObject(ParamsConstants.MESSAGE_ERROR, error.trim());
		} else {
			UserDeptPermissionModel user = new UserDeptPermissionModel();
			if(Common.isEmpty(perCd))
			{
				//INSERT
				user.setUser_cd(user_cd);
				user.setDept_cd(deptCd);
				user.setCmpn_cd(cmpn_cd);
				user.setPerCd(Common.getDateCurrent(ParamsConstants.CD_FULL_FORMAT));
				
				if(Common.isEmpty(isAllow)){
					user.setValue("0");
				}
				else{
					user.setValue(isAllow);
				}
				
				UserDeptPermissionInsertDao userInsert = new UserDeptPermissionInsertDao(user);
				try {
					userInsert.excute();
					mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "CẬP NHẬT THÀNH CÔNG");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					mv.addObject(ParamsConstants.MESSAGE_ERROR, "MỘT LỖI XẢY RA KHI CẬP NHẬT");
				}
				
				
			} else {
				//UPDATE
				user.setPerCd(perCd);

				if(Common.isEmpty(isAllow)){
					user.setValue("0");
				}
				else{
					user.setValue(isAllow);
				}
				
				UserDeptPermissionUpdateDao update  = new UserDeptPermissionUpdateDao(user);
				try {
					update.excute();
					mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "CẬP NHẬT THÀNH CÔNG");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					mv.addObject(ParamsConstants.MESSAGE_ERROR, "MỘT LỖI XẢY RA KHI CẬP NHẬT");

				}
			}
			
			UserDeptPermissionModel userDept = new UserDeptPermissionModel();
			userDept.setCmpn_cd(cmpn_cd);
			userDept.setUser_cd(user_cd);
			
			UserDeptPermissionSelectDao userDelect = new UserDeptPermissionSelectDao(userDept);
			try {
				List<UserDeptPermissionModel> lst = userDelect.excute();
				
				if(lst.size()>0)
				{
					mv.addObject("lst", lst);
				} else {
					mv.addObject(ParamsConstants.MESSAGE_ERROR, "KHÔNG TÌM THẤY ĐƠN VỊ PHÂN QUYỀN NÀO");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		mv.setViewName("views/DepartmentPermistionManagement.jsp");
			
			
		
		
		
		
		return mv;
	}
	
	//CompanySettingManagement
	@RequestMapping(params = "back" , method = RequestMethod.POST)
	public String back(HttpServletRequest request, Model model)
	{
		return "redirect:/SystemManagement";
	}
}
