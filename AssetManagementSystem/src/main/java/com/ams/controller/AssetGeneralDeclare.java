package com.ams.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Url;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ams.dao.AssetGeneralInsertDao;
import com.ams.dao.UserRoleMappingSelectDao;
import com.ams.model.AssetGeneralModel;
import com.ams.model.PermissionService;
import com.ams.model.UserRoleMapping;
import com.ams.util.AuthenticationLogin;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.RoleCommon;
import com.ams.util.ServicesConstants;
import com.ams.util.SessionConstants;
import com.ams.util.SystemControl;
import com.ams.util.UrlCommon;



@Controller
@RequestMapping("/AssetGeneralRegisterDeclare")
public class AssetGeneralDeclare {
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request, RedirectAttributes redirect)
	{
		ModelAndView mv = new ModelAndView();
		AuthenticationLogin auth = new AuthenticationLogin(request);
		if(auth.isLogin())
		{
			String user_cd = (String) request.getSession().getAttribute(SessionConstants.SESSION_USER_ID);
			String company_cd = (String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD);
			boolean isPermission = auth.CheckPermissionIsTrue(company_cd, user_cd, RoleCommon.R_WRITE, ServicesConstants.ASSET_MANAGEMENT);
			//Kiểm tra quyền hạn
			if(isPermission)
			{
				mv.addObject(ParamsConstants.TITLE_SCREEN, "MÀN HÌNH TẠO MỚI TÀI SẢN");
				mv.setViewName("views/AssetGeneralRegisterDeclare.jsp");			
			}
			else
			{
				redirect.addFlashAttribute(ParamsConstants.MESSAGE_ERROR, "BẠN KHÔNG CÓ QUYỀN THỰC HIỆN CHỨC NĂNG NÀY");
				mv.setViewName(UrlCommon.ErrorUrl);
			}	
		}
		else
		{
			
			mv.setViewName(auth.logout());
		}
	
		return mv;
	}
	@RequestMapping(method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response, RedirectAttributes rediect) throws UnsupportedEncodingException
	{
		AuthenticationLogin auth = new AuthenticationLogin(request);
		ModelAndView mv = new ModelAndView();
		//Kiểm tra đăng nhập
		if(auth.isLogin())
		{
			String user_cd = (String) request.getSession().getAttribute(SessionConstants.SESSION_USER_ID);
			String company_cd = (String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD);
			boolean isPermission = auth.CheckPermissionIsTrue(company_cd, user_cd, RoleCommon.R_WRITE, ServicesConstants.ASSET_MANAGEMENT);
			//Kiểm tra quyền hạn
			if(isPermission)
			{
				request.setCharacterEncoding("UTF-8");
				String CMPN_CD = (String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD);
				
				
				AssetGeneralModel asset = new AssetGeneralModel();
				asset.setCompany_CD(CMPN_CD);
				request.setCharacterEncoding("UTF-8");
				
				asset.setRFID(request.getParameter("asset_rfid"));
			
				
				String error = "";
				
				if(Common.isEmpty(asset.getRFID()))
				{
					error = error + "Mã quản lý tài sản (RFID) là bắt buộc <br>";
				}
				if(Common.isNotEmpty(error))
				{
					mv.addObject(ParamsConstants.MESSAGE_ERROR, error);
					mv.setViewName("/views/AssetGeneralRegisterDeclare.jsp");
				}
				else
				{
					request.getSession().setAttribute("object", asset);
					mv.setViewName("redirect:/AssetGeneralRegister");
				}
			}
		}
		else
		{
			mv.setViewName(UrlCommon.LoginUrl);
		}
			
		
		return mv;
	}
	@RequestMapping(params="save", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
	public ModelAndView saveAs(HttpServletRequest request, HttpServletResponse response, RedirectAttributes rediect) throws UnsupportedEncodingException
	{
		AuthenticationLogin auth = new AuthenticationLogin(request);
		ModelAndView mv = new ModelAndView();
		//Kiểm tra đăng nhập
		if(auth.isLogin())
		{
			String user_cd = (String) request.getSession().getAttribute(SessionConstants.SESSION_USER_ID);
			String company_cd = (String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD);
			boolean isPermission = auth.CheckPermissionIsTrue(company_cd, user_cd, RoleCommon.R_WRITE, ServicesConstants.ASSET_MANAGEMENT);
			//Kiểm tra quyền hạn
			if(isPermission)
			{
				request.setCharacterEncoding("UTF-8");
				String CMPN_CD = (String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD);
				
				
				AssetGeneralModel asset = new AssetGeneralModel();
				asset.setCompany_CD(CMPN_CD);
				request.setCharacterEncoding("UTF-8");
				
				asset.setRFID(request.getParameter("asset_rfid"));
			
				
				String error = "";
				
				if(Common.isEmpty(asset.getRFID()))
				{
					error = error + "Mã quản lý tài sản (RFID) là bắt buộc <br>";
				}
				if(Common.isNotEmpty(error))
				{
					mv.addObject(ParamsConstants.MESSAGE_ERROR, error);
					mv.setViewName("/views/AssetGeneralRegisterDeclare.jsp");
				}
				else
				{
					request.getSession().setAttribute("object", asset);
					mv.setViewName("redirect:/AssetGeneralRegister");
				}
			}
		}
		else
		{
			mv.setViewName(UrlCommon.LoginUrl);
		}
			
		
		return mv;
	}
	@RequestMapping(params="back", method=RequestMethod.POST)
	public String back(HttpServletRequest request, HttpServletResponse response)
	{
		return "redirect:/FeatureSystem";
	}
}
