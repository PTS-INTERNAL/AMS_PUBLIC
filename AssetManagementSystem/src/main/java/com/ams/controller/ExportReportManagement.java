package com.ams.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ams.util.AuthenticationLogin;
import com.ams.util.ParamsConstants;
import com.ams.util.RoleCommon;
import com.ams.util.SessionConstants;
import com.ams.util.UrlCommon;

@Controller
@RequestMapping("/ExportReportManagement")
public class ExportReportManagement {
	
String TITLE = "MÀN HÌNH QUẢN LÝ XUẤT BÁO CÁO";
	
	@RequestMapping(method =  RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request, RedirectAttributes redirect)
	{
		ModelAndView mv = new ModelAndView();
//		AuthenticationLogin auth = new AuthenticationLogin(request);
//		if(auth.isLogin())
//		{
//			String user_cd = (String) request.getSession().getAttribute(SessionConstants.SESSION_USER_ID);
//			String company_cd = (String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD);
//			boolean isPermission = auth.CheckPermissionIsTrue(company_cd, user_cd, RoleCommon.R_WRITE, ServicesConstants.ASSET_MANAGEMENT);
//			//Kiểm tra quyền hạn
//			if(isPermission)
//			{
				mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
				mv.setViewName("views/ExportReportManagement.jsp");
//						
//			}
//			else
//			{
//				redirect.addFlashAttribute(ParamsConstants.MESSAGE_ERROR, "BẠN KHÔNG CÓ QUYỀN THỰC HIỆN CHỨC NĂNG NÀY");
//				mv.setViewName(UrlCommon.ErrorUrl);
//			}	
//		}
//		else
//		{
//			
//			mv.setViewName(auth.logout());
//		}
		return mv;
	}

}
