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
import com.ams.model.Department;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;


@Controller
@RequestMapping("/DepartmentManagement")
public class DepartmentManagement {
	String TITLE = "MÀN HÌNH ĐƠN VỊ CỦA DOANH NGHIỆP";
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		mv.setViewName("views/DepartmentManagement.jsp");
		Department dept = new Department();
		
		dept.setCompany_cd((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
		DepartmentSelectDao departmentSelectDao = new DepartmentSelectDao(dept);
		List<Department> lstDept = new ArrayList<Department>();
		
		try {
			lstDept = departmentSelectDao.excute();
			mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "Tìm thấy " + lstDept.size() + " đơn vị");
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
		Department dept = new Department();
		dept.setCompany_cd((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
		dept.setDept_cd(Common.getDateCurrent("YYYYMMddHHmmSS"));
		dept.setDept_name(request.getParameter("department_name"));
		dept.setDept_desciption(request.getParameter("department_describe"));
		if(Common.isNotCheckEmpty(dept.getDept_name()) == false)
		{
			mv.addObject(ParamsConstants.MESSAGE_ERROR, "KHÔNG ĐƯỢC BỎ TRỐNG TÊN ĐƠN VỊ");
		}
		else
		{
			DepartmentInsertDao insert = new DepartmentInsertDao(dept);
			try {
				insert.excute();
				mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "THÊM THÀNH CÔNG");
				mv.setViewName("redirect:/DepartmentManagement");
				return mv;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				mv.addObject(ParamsConstants.MESSAGE_ERROR, "THÊM ĐƠN VỊ BỊ LỖI");
				e.printStackTrace();
			}
			
		}
		mv.setViewName("views/DepartmentManagement.jsp");
			
			
		
		
		
		
		return mv;
	}
	
	//CompanySettingManagement
	@RequestMapping(params = "back" , method = RequestMethod.POST)
	public String back(HttpServletRequest request, Model model)
	{
		return "redirect:/CompanySettingManagement";
	}
}
