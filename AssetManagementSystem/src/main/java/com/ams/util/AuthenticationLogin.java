package com.ams.util;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ams.dao.UserRoleMappingSelectDao;
import com.ams.dao.UserRoleMappingSelectToInsertDao;
import com.ams.model.UserRoleMapping;

public class AuthenticationLogin {
	HttpServletRequest requests;
	
	public AuthenticationLogin(HttpServletRequest request)
	{
		requests = request;
	}
	
	public boolean isLogin()
	{
		boolean result =false;
		HttpSession session =  requests.getSession();
		String EMPLOYEE_CD = (String) session.getAttribute(SessionConstants.SESSION_USER_ID);
		if(EMPLOYEE_CD !=null && EMPLOYEE_CD.trim().length()>0)
		{
			String EMPLOYEE_NAME =  (String) session.getAttribute(SessionConstants.SESSION_USER_NAME);
			if(EMPLOYEE_NAME !=null && EMPLOYEE_NAME.trim().length()>0)
			{
				result = true;
			}
			else
			{
				result= false;
			}
		}
		else
		{
			result = false;
		}
		return result;
	}
	
	public String logout()
	{
		return "redirect:/";
	}
	
	public boolean CheckPermissionIsTrue(String cmpn, String user, String role, String service_tx)
	{
		UserRoleMapping userRole = new UserRoleMapping();
		userRole.setUser_cd(user);
		userRole.setCompany_cd(cmpn);
		userRole.setService_tx(service_tx);
		userRole.setR_access("1");
		switch (role) {
		case RoleCommon.R_DELETE:
			 userRole.setR_delete("1");
			break;
		case RoleCommon.R_EXPORT:
			 userRole.setR_export("1");
			break;
		case RoleCommon.R_PRINT:
			 userRole.setR_print("1");
			break;
		case RoleCommon.R_READ:
			 userRole.setR_read("1");
			break;
		case RoleCommon.R_UPDATE:
			 userRole.setR_update("1");
			break;
		case RoleCommon.R_WRITE:
			 userRole.setR_write("1");
			break;

		default: 
		 userRole.setR_access("1");	
		}
		
		UserRoleMappingSelectToInsertDao urmsd = new UserRoleMappingSelectToInsertDao(userRole);
		
		try {
			List<UserRoleMapping> lstRole = urmsd.excute();
			if(lstRole.size()==1)
			{
					return true;	
			}
			else
			{
			 return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return false;
	}

}
