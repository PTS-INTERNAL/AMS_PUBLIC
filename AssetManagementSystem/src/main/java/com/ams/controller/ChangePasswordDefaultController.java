package com.ams.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ams.dao.UserSelectDao;
import com.ams.dao.UserUpdatePassworDefaultDao;
import com.ams.model.UserModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
@Controller
@RequestMapping("/ChangePasswordDefault")
public class ChangePasswordDefaultController {
	
	
		@RequestMapping(method=RequestMethod.GET)
		public ModelAndView init(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
			ModelAndView mv = new ModelAndView();
			
			mv.setViewName("views/ChangePasswordDefault.jsp");
			return mv;
		}
		
		@RequestMapping(params="save", method=RequestMethod.POST)
		public ModelAndView saveChange(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
			ModelAndView mv = new ModelAndView();
			String usn = request.getParameter("usn");
			String pwdOld = request.getParameter("pswdOld");
			String pwdNew = request.getParameter("pswdNew");
			String pwdReNew = request.getParameter("pswdReNew");
			mv.addObject("usn", usn);
			mv.addObject("pswdOld", pwdOld);
			mv.addObject("pswdNew", pwdNew);
			mv.addObject("pswdReNew", pwdReNew);
			
			String message="";
			if(Common.isEmpty(usn))
			{
				message += "Tên người dùng là bắt buộc<br>";
			}
			if(Common.isEmpty(pwdOld))
			{
				message += "Mật khẩu củ là bắt buộc<br>";
			}
			if(Common.isEmpty(pwdNew))
			{
				message += "Mật khẩu mới là bắt buộc<br>";
			}
			if(Common.isEmpty(pwdReNew))
			{
				message += "Xác nhận mật khẩu mới";
			}
			
			if(message.trim().length()>0)
			{
				mv.addObject(ParamsConstants.MESSAGE_ERROR, message);
			}
			else
			{
				if(!pwdNew.equals(pwdReNew))
				{
					mv.addObject(ParamsConstants.MESSAGE_ERROR, "MẬT KHẨU XÁC NHẬN KHÔNG CHÍNH XÁC");
				}
				else
				{
					UserModel user = new UserModel();
					user.setPasword(pwdOld);
					user.setEmployee_cd(usn);
					UserSelectDao userSelectDao = new UserSelectDao(user);
					List<UserModel> lstUser;
					try {
						lstUser = userSelectDao.excute();
						if(lstUser.size()==1)
						{
							UserModel userChange = new UserModel();
							userChange.setEmployee_cd(usn);
							userChange.setPasword(pwdNew);
							
							UserUpdatePassworDefaultDao update = new UserUpdatePassworDefaultDao(userChange);
							update.excute();
							mv.setViewName("redirect:/login");
							return mv;
							
						}
						else
						{
							mv.addObject(ParamsConstants.MESSAGE_ERROR, "KHÔNG TÌM THẤY TÀI KHOẢN");
						}
					     
					}
					catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
			
			mv.setViewName("views/ChangePasswordDefault.jsp");
			return mv;
		}
}
