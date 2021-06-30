package com.ams.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.CompanyInsertDao;
import com.ams.dao.CompanySelectDao;
import com.ams.dao.CompanyUpdateDeleteDao;
import com.ams.model.CompanyForm;
import com.ams.model.CompanyModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;



@Controller
@RequestMapping("CompanyManagement")
public class CompanyManagementController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String init(ModelMap modelMap, HttpServletRequest request) 
	{
		modelMap.addAttribute(ParamsConstants.TITLE_SCREEN,"MÀN HÌNH QUẢN LÝ CÔNG TY");
		CompanyModel cmpnMel = new CompanyModel();
		cmpnMel.setCompany_delete("0");
		CompanySelectDao companySelectDao = new CompanySelectDao(cmpnMel);
		List<CompanyModel> lstCompany = null;
		try {
			lstCompany = companySelectDao.excute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(lstCompany.size()>0)
		{
			modelMap.addAttribute("lstCompany", lstCompany);
		}
		
		return "views/CompanyManagement.jsp";
	}
	
	@RequestMapping(params ="create", method=RequestMethod.POST)
	public String CompanyInsert(ModelMap modelMap, HttpServletRequest request) 
	{
		return "redirect:/CompanyInsetInit";
	}
	
	@RequestMapping(value = {"{id}"}, method=RequestMethod.GET)
	public String ViewCompany(@PathVariable("id") String id, ModelMap modelMap, HttpServletRequest request) throws SQLException 
	{ 
		
		CompanySelectDao companySelect = new CompanySelectDao();
		CompanyModel form = companySelect.excuteById(id);
		modelMap.addAttribute("TittleScreen","MÀN HÌNH XEM THÔNG TIN CÔNG TY"); 
		modelMap.addAttribute("form",form);
		return "/views/CompanyView.jsp";
		  
	}
	@RequestMapping(params="back",  method=RequestMethod.POST)
	public ModelAndView back( ModelMap modelMap, HttpServletRequest request) 
	{ 
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/SystemManagement");
		return mv;
	}
	
	@RequestMapping(params="search",  method=RequestMethod.POST)
	public String search( ModelMap modelMap, HttpServletRequest request) 
	{ 
		modelMap.addAttribute(ParamsConstants.TITLE_SCREEN,"MÀN HÌNH QUẢN LÝ CÔNG TY");
		String company_cd = request.getParameter("cmpn_cd");
		String company_na= request.getParameter("cmpn_na");
		String company_short = request.getParameter("shortName");
		modelMap.addAttribute("cmpn_cd", company_cd);
		modelMap.addAttribute("cmpn_na", company_na);
		modelMap.addAttribute("shortName", company_short);

		CompanyModel cmpnModel = new CompanyModel();
		cmpnModel.setCompany_cd(company_cd);
		cmpnModel.setCompany_shortname(company_short);
		cmpnModel.setCompany_delete("0");

		CompanySelectDao companySelectDao = new CompanySelectDao(cmpnModel);
		List<CompanyModel> lstCompany = null;
		try {
			lstCompany = companySelectDao.excute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(lstCompany.size()>0)
		{
			modelMap.addAttribute("lstCompany", lstCompany);
		}
		
		return "views/CompanyManagement.jsp";
	}
	@RequestMapping(params = "delete", method = RequestMethod.POST)
	public ModelAndView delete(HttpServletRequest request,HttpServletResponse response) 
	{
		ModelAndView mv = new ModelAndView();
		String employee_cd  =request.getParameter("cmpn_cd");
		if(Common.isNotCheckEmpty(employee_cd))
		{
			CompanyUpdateDeleteDao udel  = new CompanyUpdateDeleteDao(employee_cd);
			try {
				udel.excute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		mv.setViewName("redirect:/CompanyManagement");
		return mv;
		
	
	}
	
}
