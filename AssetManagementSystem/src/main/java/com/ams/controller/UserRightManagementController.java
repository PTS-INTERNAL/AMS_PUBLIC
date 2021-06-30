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

import com.ams.dao.CompanySelectDao;
import com.ams.dao.ServiceSelectDao;
import com.ams.dao.UserRoleMappingInsertDao;
import com.ams.dao.UserRoleMappingSelectDao;
import com.ams.dao.UserRoleMappingSelectToInsertDao;
import com.ams.dao.UserRoleMappingUpdateDao;
import com.ams.dao.UserSelectDao;
import com.ams.model.CompanyModel;
import com.ams.model.ServiceModel;
import com.ams.model.UserModel;
import com.ams.model.UserRoleMapping;
import com.ams.util.Common;
import com.ams.util.RoleCommon;
import com.ams.util.ServicesConstants;
import com.ams.util.SessionConstants;
import com.sun.xml.internal.bind.v2.model.core.ID;

@Controller
@RequestMapping("/UserRightManagement")
public class UserRightManagementController {
	
	String TITLE = "MÀN HÌNH QUẢN LÝ QUYỀN NGƯỜI DÙNG";
	@RequestMapping(params="SetUserRight", method=RequestMethod.POST)
	public ModelAndView ViewCompany( ModelMap modelMap, HttpServletRequest request) 
	{ 
		ModelAndView mv = new ModelAndView();
		modelMap.addAttribute("TittleScreen",TITLE); 
		
		
		String id = request.getParameter("employee_cd");
		UserModel userModel = new UserModel();
		userModel.setEmployee_cd(id);
		UserSelectDao userSelect = new UserSelectDao(userModel);
		try {
			List<UserModel> lstUser = 	userSelect.excute();
			mv.addObject("user", lstUser.get(0));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String form_cmpn_cd = request.getParameter("cmpn_cd");
		String form_cmpn_na = request.getParameter("cmpn_na");
		UserRoleMapping user = new UserRoleMapping();
		if(Common.isNotCheckEmpty(form_cmpn_cd)&&Common.isNotCheckEmpty(form_cmpn_na))
		{
			user.setCompany_cd((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
			mv.addObject("cmpn_na", form_cmpn_na);
			mv.addObject("cmpn_cd", form_cmpn_cd);
			user.setCompany_cd(form_cmpn_cd);
		}
		else
		{
			user.setCompany_cd((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
			mv.addObject("cmpn_na", request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_NAME));
			mv.addObject("cmpn_cd", request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
			if(user.getCompany_cd()==null || user.getCompany_cd().trim().length()==0)
			{
				user.setCompany_cd((String) request.getSession().getAttribute(SessionConstants.SESSION_USER_CMPN_CD));
				mv.addObject("cmpn_na", request.getSession().getAttribute(SessionConstants.SYSTEM_NAME));
				mv.addObject("cmpn_cd", request.getSession().getAttribute(SessionConstants.SESSION_USER_CMPN_CD));
			}
		}

		
		
	
		
		
		user.setUser_cd(id);
		UserRoleMappingSelectDao selectRole = new UserRoleMappingSelectDao(user);
		try {
			List<UserRoleMapping> lstRoleMapping = selectRole.excute();
			mv.addObject("lstRoleMapping", lstRoleMapping);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("/views/UserRightManagement.jsp");
		return mv;
		  
	}
	@RequestMapping(params="reload", method=RequestMethod.POST)
	public ModelAndView reload( ModelMap modelMap, HttpServletRequest request) 
	{ 
		ModelAndView mv = new ModelAndView();
		modelMap.addAttribute("TittleScreen",TITLE); 
		
		
		String id = request.getParameter("employee_code");
		UserModel userModel = new UserModel();
		userModel.setEmployee_cd(id);
		UserSelectDao userSelect = new UserSelectDao(userModel);
		try {
			List<UserModel> lstUser = 	userSelect.excute();
			mv.addObject("user", lstUser.get(0));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String form_cmpn_cd = request.getParameter("cmpn_cd");
		String form_cmpn_na = request.getParameter("cmpn_na");
		UserRoleMapping user = new UserRoleMapping();
		if(Common.isNotCheckEmpty(form_cmpn_cd)&&Common.isNotCheckEmpty(form_cmpn_na))
		{
			user.setCompany_cd((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
			mv.addObject("cmpn_na", form_cmpn_na);
			mv.addObject("cmpn_cd", form_cmpn_cd);
			user.setCompany_cd(form_cmpn_cd);
		}
		else
		{
			user.setCompany_cd((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
			mv.addObject("cmpn_na", request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_NAME));
			mv.addObject("cmpn_cd", request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
			if(user.getCompany_cd()==null || user.getCompany_cd().trim().length()==0)
			{
				user.setCompany_cd((String) request.getSession().getAttribute(SessionConstants.SESSION_USER_CMPN_CD));
				mv.addObject("cmpn_na", request.getSession().getAttribute(SessionConstants.SYSTEM_NAME));
				mv.addObject("cmpn_cd", request.getSession().getAttribute(SessionConstants.SESSION_USER_CMPN_CD));
			}
		}

		
		
	
		
		
		user.setUser_cd(id);
		UserRoleMappingSelectDao selectRole = new UserRoleMappingSelectDao(user);
		try {
			List<UserRoleMapping> lstRoleMapping = selectRole.excute();
			mv.addObject("lstRoleMapping", lstRoleMapping);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("/views/UserRightManagement.jsp");
		return mv;
		  
	}
	
	@RequestMapping(params="save",  method=RequestMethod.POST)
	public ModelAndView UserRightSave( ModelMap modelMap, HttpServletRequest request) 
	{ 
		ModelAndView mv = new ModelAndView();
		modelMap.addAttribute("TittleScreen",TITLE); 
		String id = request.getParameter("employee_code");
		UserModel userModel = new UserModel();
		userModel.setEmployee_cd(id);
		UserSelectDao userSelect = new UserSelectDao(userModel);
		try {
			List<UserModel> lstUser = 	userSelect.excute();
			mv.addObject("user", lstUser.get(0));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ServiceSelectDao  select = new ServiceSelectDao();
		List<ServiceModel> lstRole= null;
		try {
			 lstRole = select.excute();
			mv.addObject("listRole", lstRole);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		UserRoleMapping Role = new UserRoleMapping();
		String cmpn_cd = request.getParameter("cmpn_cd");
		String employee_code = request.getParameter("employee_code");
		
		List<UserRoleMapping> lstRoleUser = new ArrayList<>();
		for(int i =0;i<lstRole.size();i++)
		{
			Role = new UserRoleMapping();
			Role.setCompany_cd(cmpn_cd);
			Role.setUser_cd(employee_code);
			Role.setService_tx(lstRole.get(i).getService_tx());
			Role.setSerive_cd(lstRole.get(i).getService_id());
			String idService = "["+lstRole.get(i).getService_id().trim()+"]";
			Role.setR_access(request.getParameter(RoleCommon.R_ACCESS+idService));
			Role.setR_delete(request.getParameter(RoleCommon.R_DELETE +idService));
			Role.setR_update(request.getParameter(RoleCommon.R_UPDATE+idService));
			Role.setR_write(request.getParameter(RoleCommon.R_WRITE+idService));
			Role.setR_export(request.getParameter(RoleCommon.R_EXPORT+idService));
			Role.setR_print(request.getParameter(RoleCommon.R_PRINT+idService));
			Role.setR_read(request.getParameter(RoleCommon.R_READ+idService));
			lstRoleUser.add(Role);
			
		}
		//Update Role user
		
		for(int e = 0; e<lstRoleUser.size();e++)
		{
			UserRoleMapping roleItem = new UserRoleMapping();
			roleItem.setCompany_cd(lstRoleUser.get(e).getCompany_cd());
			roleItem.setUser_cd(lstRoleUser.get(e).getUser_cd());
			roleItem.setSerive_cd(lstRoleUser.get(e).getSerive_cd());
			
			UserRoleMappingSelectToInsertDao selectRole  = new UserRoleMappingSelectToInsertDao(roleItem);
			try {
				List<UserRoleMapping> lstReady = selectRole.excute();
				roleItem.setR_access(lstRoleUser.get(e).getR_access());
				roleItem.setR_delete(lstRoleUser.get(e).getR_delete());
				roleItem.setR_export(lstRoleUser.get(e).getR_export());
				roleItem.setR_print(lstRoleUser.get(e).getR_print());
				roleItem.setR_update(lstRoleUser.get(e).getR_update());
				roleItem.setR_read(lstRoleUser.get(e).getR_read());
				roleItem.setR_write(lstRoleUser.get(e).getR_write());
				if(lstReady.size()>1)
				{
					//System.out.println("LỖI CẬP NHẬT ROLE CHO USERER: " + roleItem.getUser_cd());
				}
				//Không có role nào được áp dụng
				if(lstReady.size()==0)
				{
					lstRoleUser.get(e).setRole_cd(Common.getDateCurrent("YYYYMMddHHmmSS"));
					UserRoleMappingInsertDao userUpdate = new UserRoleMappingInsertDao(lstRoleUser.get(e));
					userUpdate.excute();
				}
				//Đã tồn tài rồi
				if(lstReady.size()==1)
				{
					lstRoleUser.get(e).setRole_cd(lstReady.get(0).getRole_cd());
					UserRoleMappingUpdateDao userUpdate = new UserRoleMappingUpdateDao(lstRoleUser.get(e));
					userUpdate.excute();
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
				
		UserRoleMapping user = new UserRoleMapping();
		String form_cmpn_cd = request.getParameter("cmpn_cd");
		String form_cmpn_na = request.getParameter("cmpn_na");
		if(Common.isNotCheckEmpty(form_cmpn_cd)&&Common.isNotCheckEmpty(form_cmpn_na))
		{
			user.setCompany_cd((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
			mv.addObject("cmpn_na", form_cmpn_na);
			mv.addObject("cmpn_cd", form_cmpn_cd);
			user.setCompany_cd(form_cmpn_cd);
		}
		else
		{
			user.setCompany_cd((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
			mv.addObject("cmpn_na", request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_NAME));
			mv.addObject("cmpn_cd", request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
			if(user.getCompany_cd()==null || user.getCompany_cd().trim().length()==0)
			{
				user.setCompany_cd((String) request.getSession().getAttribute(SessionConstants.SESSION_USER_CMPN_CD));
				mv.addObject("cmpn_na", request.getSession().getAttribute(SessionConstants.SYSTEM_NAME));
				mv.addObject("cmpn_cd", request.getSession().getAttribute(SessionConstants.SESSION_USER_CMPN_CD));
			}
		}
		user.setUser_cd(id);
		UserRoleMappingSelectDao selectRole = new UserRoleMappingSelectDao(user);
		try {
			List<UserRoleMapping> lstRoleMapping = selectRole.excute();
			mv.addObject("lstRoleMapping", lstRoleMapping);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("/views/UserRightManagement.jsp");
		return mv;
		  
	}
	@RequestMapping(params="back",  method=RequestMethod.POST)
	public ModelAndView back( ModelMap modelMap, HttpServletRequest request) 
	{ 
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/UserManagement");
		return mv;
	}
	

}
