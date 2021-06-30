package com.ams.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.CompanySelectDao;
import com.ams.dao.UserSelectDao;
import com.ams.model.CompanyForm;
import com.ams.model.CompanyModel;
import com.ams.model.UserModel;



@Controller
public class SettingController {
	
	@RequestMapping("/admin-manager")
	public ModelAndView init(HttpServletRequest request,HttpServletResponse response) 
	{
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		ArrayList<UserModel> lstUser =new ArrayList<UserModel>();
		String ID_employee = (String) session.getAttribute("ID");
		
		UserModel user = new UserModel();
		user.setEmployment_CD(ID_employee);
		
		UserSelectDao userSelectDao = new UserSelectDao(user);
		try {
			lstUser = (ArrayList<UserModel>) userSelectDao.excute();
		} catch (SQLException e) {
			//System.out.println("Error when search User in Setting contrller");
			e.printStackTrace();
		}
		
		//Tìm thấy có user này
		if(lstUser.size() > 0)
		{
			mv.addObject("user", lstUser.get(0));
			String Company_CD = lstUser.get(0).getCompany_cd();
			CompanyModel company = null;
			CompanySelectDao companySelectDao = new CompanySelectDao();
			try {
				company = companySelectDao.excuteById(Company_CD);
			} catch (SQLException e) {
				///System.out.println("Error when search Company by ID in Setting contrller");
				e.printStackTrace();
			}
			
			if(company != null)
			{
				mv.addObject("company", company);
			}
			
		}
		
		
		
		mv.setViewName("redirect:CompanyManagement");
		return mv;
		
	
	}

}
