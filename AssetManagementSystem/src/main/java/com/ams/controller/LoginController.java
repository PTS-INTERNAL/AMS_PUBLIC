package com.ams.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ams.dao.CompanySelectDao;
import com.ams.dao.UserSelectDao;
import com.ams.model.CompanyModel;
import com.ams.model.UserModel;
import com.ams.util.Common;
import com.ams.util.SessionConstants;


@Controller
public class LoginController {
	@RequestMapping("/login")
	public ModelAndView init(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
	
		HttpSession session_en=request.getSession();  
		if(session_en.getAttribute("NAME")!=null && session_en.getAttribute("NAME").toString().trim().length()>0)
		{
			session_en.setAttribute("NAME",null);
			session_en.setAttribute(SessionConstants.SYSTEM_NAME,"ASSET MANAGEMENT" );
			
		
		}
			mv.setViewName("views/login.jsp");
		request.getSession().setAttribute(SessionConstants.SUB_SYSTEM_NAME,null);
		return mv;
	}
	
	@RequestMapping("/refesh")
	public ModelAndView initRefesh(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
	
		HttpSession session_en=request.getSession();  
		if(session_en.getAttribute("NAME")!=null && session_en.getAttribute("NAME").toString().trim().length()>0)
		{			
			mv.setViewName("redirect:/FeatureSystem");		
		}
		else
		{
			mv.setViewName("redirect:/login");
			request.getSession().setAttribute(SessionConstants.SUB_SYSTEM_NAME,null);
		}
			
		return mv;
	}
	
	@RequestMapping("/UserLogin")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attr) {
		ModelAndView mv = new ModelAndView();
		//Lấy giá trị nhập vào
		String usn = request.getParameter("usn");
		String pwd = request.getParameter("pswd");
		

		//Kiểm tra tính hợp lệ
		//Kiểm tra username rỗng
		if (usn != null && usn.length() > 0) {
			//Kiểm tra password rỗng
			if (pwd != null && pwd.length() > 0) {
				
				UserModel user = new UserModel();
				user.setPasword(pwd);
				user.setEmployee_cd(usn);
				UserSelectDao userSelectDao = new UserSelectDao(user);
				List<UserModel> lstUser;
				try {
					lstUser = userSelectDao.excute();
					//Tồn tại tài khoản
					if(lstUser.size() >0)
					{
						if(lstUser.get(0).getPassword_Default().trim().length()==0)
						{
							if(isExprire(lstUser.get(0)))
							{
								attr.addFlashAttribute("userNamepass", usn);
								attr.addFlashAttribute("passUserName", pwd);
								attr.addFlashAttribute("message","Tài khoản hết hạn sử dụng vui lòng liên hệ quản trị viên !"); 
								 mv.setViewName("redirect:/login"); 
								 return mv; 
							}
							else
							{
								 HttpSession session=request.getSession();
								  //Lưu tên trong session
								  session.setAttribute(SessionConstants.SESSION_USER_NAME,lstUser.get(0).getName());
								  //Lưu Mã nhân viên trong session
								  session.setAttribute(SessionConstants.SESSION_USER_ID,lstUser.get(0).getEmployment_CD()); 
								  //Lưu công Công ty của nhân viên
								  session.setAttribute(SessionConstants.SESSION_USER_CMPN_CD,lstUser.get(0).getCompany_cd()); 
								  
								  CompanySelectDao cmpnsd = new CompanySelectDao();
								  CompanyModel cmpn = cmpnsd.excuteById(lstUser.get(0).getCompany_cd());
								  //Lưu Tên Công ty
								  session.setAttribute(SessionConstants.SYSTEM_NAME,cmpn.getCompany_name());
								  //Chuyển đến trang quản lý tổ chức 
								  mv.setViewName("redirect:organization");
								  return mv;
							}
						 
						}
						else
						{
							  mv.setViewName("redirect:ChangePasswordDefault");
							  return mv;
						}
						
					}
					else 
					{
						attr.addFlashAttribute("userNamepass", usn);
						attr.addFlashAttribute("passUserName", pwd);
						attr.addFlashAttribute("message","Tài khoản đăng nhập không hợp lệ !"); 
						 mv.setViewName("redirect:/login"); 
						 return mv; 
					}
				} catch (SQLException e) {
					//Lỗi chạy chương trình
					e.printStackTrace();
					mv.setViewName("redirect:error");
					mv.addObject("message",e.toString());
					return mv;
				}
			}
		}
		attr.addFlashAttribute("userNamepass", usn);
		attr.addFlashAttribute("passUserName", pwd);
		attr.addFlashAttribute("message","Xin nhập đầy đủ thông tin!");
		mv.setViewName("redirect:/login"); 
		return mv;
	}
	
	public boolean isExprire(UserModel user)
	{
		//return true : het hang
		//false: con han
		if(Common.isNotCheckEmpty(user.getDate_expire()))
		{
			String date = user.getDate_expire().trim().replace("-", "");
			String dateCurren = Common.getDateCurrent("yyyyMMdd");
			if(Integer.parseInt(date)>Integer.parseInt(dateCurren))
			{
				//con han hang
				return false;
			}
			if(Integer.parseInt(date)<Integer.parseInt(dateCurren))
			{
				return true;
			}
			if(Integer.parseInt(date)==Integer.parseInt(dateCurren))
			{
				if(Common.isNotCheckEmpty(user.getTime_expire()))
				{
					String time = user.getTime_expire().trim().replace(":", "");
					String timeCurren = Common.getDateCurrent("HHMM");
					if(Integer.parseInt(time)>Integer.parseInt(timeCurren))
					{
						return false;
					}
					if(Integer.parseInt(time)==Integer.parseInt(timeCurren)||Integer.parseInt(time)<Integer.parseInt(timeCurren))
					{
						return true;
					}
				}
				return false;
			}
			
		}
		return false;
	}
}
	
