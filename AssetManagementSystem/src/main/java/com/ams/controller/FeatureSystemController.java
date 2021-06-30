package com.ams.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.ams.dao.CompanySelectDao;
import com.ams.dao.UserRoleMappingSelectDao;
import com.ams.dao.UserRoleMappingSelectToInsertDao;
import com.ams.model.CompanyModel;
import com.ams.model.UserRoleMapping;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;


@Controller
@RequestMapping("/FeatureSystem")
public class FeatureSystemController {
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request, RedirectAttributes redirect)
	{
		ModelAndView mv = new ModelAndView();
		String user_cd = (String) request.getSession().getAttribute(SessionConstants.SESSION_USER_ID);
		String company_cd = (String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD);
		
		if(user_cd != null && user_cd.trim().length()>0 && company_cd != null && company_cd.trim().length()>0)
		{
			UserRoleMapping userRole = new UserRoleMapping();
			userRole.setUser_cd(user_cd);
			userRole.setCompany_cd(company_cd);
			userRole.setR_access("1");
			userRole.setHide("0");
			UserRoleMappingSelectToInsertDao urmsd = new UserRoleMappingSelectToInsertDao(userRole);
			try {
				List<UserRoleMapping> lstfeature = urmsd.excute();
				if(lstfeature.size()==0)
				{
					mv.addObject(ParamsConstants.MESSAGE_ERROR, "BẠN KHÔNG CÓ QUYỀN TRUY CẬP HỆ THỐNG");
				}
				else
				{
					mv.addObject("lstf", lstfeature);
				}
				mv.setViewName("views/featureSystem.jsp");
				mv.addObject(ParamsConstants.TITLE_SCREEN, "MÀN HÌNH CHỨC NĂNG HỆ THỐNG");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				redirect.addFlashAttribute(ParamsConstants.MESSAGE_ERROR, e.toString());
				mv.setViewName("redirect:error");
			}
			
		}
		else
		{
			mv.setViewName("redirect:login");
		}
		return mv;		
	}
}
