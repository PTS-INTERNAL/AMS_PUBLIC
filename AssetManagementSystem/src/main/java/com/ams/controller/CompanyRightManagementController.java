package com.ams.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.CompanyPermisionInsertDao;
import com.ams.dao.CompanyPermisionSelectDao;
import com.ams.dao.CompanyPermisionUpdateDao;
import com.ams.dao.CompanySelectDao;
import com.ams.dao.ServiceSelectDao;
import com.ams.dao.UserRoleMappingInsertDao;
import com.ams.dao.UserRoleMappingSelectDao;
import com.ams.dao.UserRoleMappingUpdateDao;
import com.ams.dao.UserSelectDao;
import com.ams.model.CompanyModel;
import com.ams.model.CompanyPermision;
import com.ams.model.ServiceModel;
import com.ams.model.UserCompanyPermision;
import com.ams.model.UserModel;
import com.ams.model.UserRoleMapping;
import com.ams.util.Common;
import com.ams.util.RoleCommon;
import com.ams.util.ServicesConstants;
import com.ams.util.SessionConstants;
import com.sun.xml.internal.bind.v2.model.core.ID;

@Controller
@RequestMapping("/CompanyRightManagement")
public class CompanyRightManagementController {
	
	String TITLE = "MÀN HÌNH QUẢN LÝ QUYỀN CÔNG TY";
	@RequestMapping(params="setUserRight",  method=RequestMethod.POST)
	public ModelAndView ViewCompany(ModelMap modelMap, HttpServletRequest request) 
	{ 
		ModelAndView mv = new ModelAndView();
		modelMap.addAttribute("TittleScreen",TITLE); 
		String id  =request.getParameter("cmpn_cd");
		CompanyModel cmpn = new CompanyModel();
		
		CompanySelectDao cmpnSelect = new CompanySelectDao();
		try {
			cmpn =cmpnSelect.excuteById(id);
			mv.addObject("cmpn",cmpn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CompanyModel cm =new CompanyModel();
		cm.setCompany_cd(id);
		CompanyPermisionSelectDao coslec = new CompanyPermisionSelectDao(cm);
		try {
			List<CompanyPermision> lstus = coslec.excuteCompanyPermission();
			mv.addObject("lstuserpermis",lstus);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		mv.setViewName("/views/CompanyRightManagement.jsp");
		return mv;
		  
	}
	
	@RequestMapping(params="btnSave",  method=RequestMethod.POST)
	public ModelAndView UserRightSave( ModelMap modelMap, HttpServletRequest request) 
	{ 
		ModelAndView mv = new ModelAndView();
		modelMap.addAttribute("TittleScreen",TITLE); 
		String id  =request.getParameter("cmpn_cd");
		String valueCheck = request.getParameter("valueCheck");
		String employee_cd = request.getParameter("employee_cd");
		String company_cd = request.getParameter("cmpn_cd");
		CompanyPermision comPer = new CompanyPermision();
		
		CompanyModel company =new CompanyModel();
		company.setCompany_user(employee_cd);
		company.setCompany_cd(company_cd);
		comPer.setCompany(company);
		if(valueCheck!=null && valueCheck.trim().length()>0&&valueCheck.trim().equals("1"))
		{
			comPer.setValueCheck("0");
		}
		else
		{
			comPer.setValueCheck("1");
		}
		CompanyPermisionSelectDao cpmPer = new CompanyPermisionSelectDao(company);
		try {
			List<CompanyModel> lstper = cpmPer.excute();
			if(lstper.size()==0)
			{
				//Insert
				CompanyPermisionInsertDao insert = new CompanyPermisionInsertDao(comPer);
				insert.excute();
				UserRoleMapping userRole = new UserRoleMapping();
				userRole.setCompany_cd(comPer.getCompany().getCompany_cd());
				ServiceModel service = new ServiceModel();
				service.setService_tx(ServicesConstants.COMPANY_MANAGER);
				ServiceSelectDao selectSerr = new ServiceSelectDao(service);
				List<ServiceModel> lstser = selectSerr.excute();
				userRole.setRole_cd(Common.getDateCurrent("YYYYMMddHHmmSS"));
				userRole.setSerive_cd(lstser.get(0).getService_id());
				userRole.setR_access("1");
				userRole.setR_delete("1");
				userRole.setR_write("1");
				userRole.setR_update("1");
				userRole.setUser_cd(comPer.getCompany().getCompany_user());
				UserRoleMappingInsertDao userUpdate = new UserRoleMappingInsertDao(userRole);
				userUpdate.excute();
			}
			else
			{
				//Update
				CompanyPermisionUpdateDao insert = new CompanyPermisionUpdateDao(comPer);
				insert.excute();
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		CompanyModel cmpn = new CompanyModel();
		CompanySelectDao cmpnSelect = new CompanySelectDao();
		try {
			cmpn =cmpnSelect.excuteById(id);
			mv.addObject("cmpn",cmpn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CompanyModel cm =new CompanyModel();
		cm.setCompany_cd(id);
		CompanyPermisionSelectDao coslec = new CompanyPermisionSelectDao(cm);
		try {
			List<CompanyPermision> lstus = coslec.excuteCompanyPermission();
			mv.addObject("lstuserpermis",lstus);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		mv.setViewName("/views/CompanyRightManagement.jsp");
		return mv;
		  
	}
	
	@RequestMapping(params="back",  method=RequestMethod.POST)
	public ModelAndView back( ModelMap modelMap, HttpServletRequest request) 
	{ 
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/CompanyManagement");
		return mv;
	}
	

}
