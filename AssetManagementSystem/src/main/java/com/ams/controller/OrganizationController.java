package com.ams.controller;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ams.dao.CompanyPermisionSelectDao;
import com.ams.dao.CompanySelectDao;
import com.ams.dao.UserSelectDao;
import com.ams.database.DatabaseConnection;
import com.ams.model.CompanyModel;
import com.ams.model.UserModel;
import com.ams.util.Common;
import com.ams.util.DatabaseCommon;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;



@Controller
@RequestMapping("/organization")
public class OrganizationController {
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String NAME = "MÀN HÌNH QUẢN LÝ TỔ CHỨC";
		ModelAndView mv = new ModelAndView();
		
		Common.removeSessionValue(request, SessionConstants.SUB_SYSTEM_CD);
		Common.removeSessionValue(request, SessionConstants.SUB_SYSTEM_NAME);
		
		String userCd = (String) request.getSession().getAttribute(SessionConstants.SESSION_USER_ID);
		if(userCd!=null && userCd.trim().length()>0)
		{
			CompanyModel cmpn = new CompanyModel();
			cmpn.setCompany_user(userCd);
			cmpn.setCompany_delete("0");
			CompanyPermisionSelectDao UserCmpnPermiss = new CompanyPermisionSelectDao(cmpn);
			List<CompanyModel> lstCompany = null;
			try {
				lstCompany = UserCmpnPermiss.excute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mv.setViewName("redirect:login");
				
			}
			
			UserModel userLogin = new UserModel();
			userLogin.setEmployee_cd(userCd);
			UserSelectDao userSelect = new UserSelectDao(userLogin);
			try {
				List<UserModel> lst = userSelect.excute();
				if(lst.size()>0)
				{
					mv.addObject("role", lst.get(0).getRole().trim());
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(lstCompany.size()>0)
			{
				List<CompanyModel> lstCMPN = new ArrayList<>();
				for(int i = 0;i<lstCompany.size();i++)
				{
					CompanyModel cmpnModel = null;
					lstCompany.get(i).setCompany_delete("0");
					CompanySelectDao companySelectDao = new CompanySelectDao(lstCompany.get(i));
					try {
						List<CompanyModel> lstModel = companySelectDao.excute();
						if(lstModel.size()>0)
						{
							if(Common.isNotEmpty(lstModel.get(0).getCompany_name()))
							{
								lstCMPN.add(lstModel.get(0));
							}
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						redirectAttributes.addFlashAttribute(ParamsConstants.MESSAGE_ERROR, e.toString());
						mv.setViewName("redirect:error");
						return mv;
					}
					
				}
				for(int i=0;i<lstCMPN.size();i++)
				{
					lstCMPN.get(i).setCompany_file_image("http://"+DatabaseCommon.DB_IP+":"+DatabaseCommon.DB_PORT+lstCMPN.get(i).getCompany_file_image());
				}
				if(lstCMPN.size()>0)
				{
					mv.addObject("lstCompany", lstCMPN);

				}
				mv.addObject(ParamsConstants.TITLE_SCREEN, NAME);
				mv.setViewName("/views/organization.jsp");
				return mv;
			}
			else
			{
				mv.addObject(ParamsConstants.TITLE_SCREEN, NAME);
				mv.addObject(ParamsConstants.MESSAGE_ERROR,"KHÔNG TÌM ĐƯỢC DOANH NGHIỆP THUỘC QUẢN LÝ CỦA BẠN");
				mv.setViewName("/views/organization.jsp");
				return mv;
			}
				
		}
		else
		{
			mv.setViewName("redirect:login");
			
		}
		mv.setViewName("redirect:login");
		return mv;
			
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView SetupOrganization(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView();
		
		String company_cd = request.getParameter("company_cd");
		
		if(company_cd !=null && company_cd.trim().length()>0)
		{
			CompanySelectDao cmpnSelect = new CompanySelectDao();
			CompanyModel cmpn;
			try {
				cmpn = cmpnSelect.excuteById(company_cd.trim());
				request.getSession().setAttribute(SessionConstants.SUB_SYSTEM_CD, cmpn.getCompany_cd());
				request.getSession().setAttribute(SessionConstants.SUB_SYSTEM_NAME, cmpn.getCompany_name());
				mv.setViewName("redirect:FeatureSystem");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				redirectAttributes.addFlashAttribute(ParamsConstants.MESSAGE_NOTIFICATION, e.toString());
				mv.setViewName("redirect:error");
			}
			
			
		}
		else
		{
			redirectAttributes.addFlashAttribute(ParamsConstants.MESSAGE_NOTIFICATION, "VUI LÒNG ĐĂNG NHẬP");
			mv.setViewName("redirect:login");
		}
		
		return mv;
			
	}
}
