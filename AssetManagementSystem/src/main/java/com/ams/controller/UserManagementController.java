package com.ams.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ams.dao.CompanyPermisionInsertDao;
import com.ams.dao.CompanyPermisionUpdateDao;
import com.ams.dao.CompanySelectDao;
import com.ams.dao.ServiceSelectDao;
import com.ams.dao.UserDeleteDao;
import com.ams.dao.UserInsertDao;
import com.ams.dao.UserRoleMappingInsertDao;
import com.ams.dao.UserSelectDao;
import com.ams.model.CompanyModel;
import com.ams.model.CompanyPermision;
import com.ams.model.ServiceModel;
import com.ams.model.UserModel;
import com.ams.model.UserRoleMapping;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("UserManagement")
public class UserManagementController {
	
	String TITLE = "MÀN HÌNH QUẢN LÝ NGƯỜI DÙNG DOANH NGHIỆP ";
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request,HttpServletResponse response) 
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		UserModel user = new UserModel();
		user.setCompany_cd((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
		UserSelectDao userselect = new UserSelectDao(user);
		List<String> lstname = new ArrayList<>();
		try {
			List<UserModel> lsst = userselect.excute();
			
			if(lsst.size()>0)
			{
				String temp_cmpn_cd ="";
				String temp_cmpn_na="";
				for(int i=0;i<lsst.size();i++)
				{
					if(lsst.get(i).getCompany_cd().trim().equals(temp_cmpn_cd.trim())==false)
					{
						temp_cmpn_cd = lsst.get(i).getCompany_cd().trim();
						CompanyModel cmpn = new CompanyModel();
						cmpn.setCompany_cd(temp_cmpn_cd);
						CompanySelectDao selectCompant = new CompanySelectDao(cmpn);
						List<CompanyModel> lstcmon = selectCompant.excute();
						if(lstcmon.size()>0)
						{
							temp_cmpn_na = lstcmon.get(0).getCompany_name().trim();
						    lstname.add(temp_cmpn_na);
						}
					}
					else
					{
						lstname.add(temp_cmpn_na);
					}
				}
			}
			
			mv.addObject("lst" ,lsst);
			mv.addObject("lstNae" ,lstname);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("views/UserManagement.jsp");
		return mv;
		
	
	}
	@RequestMapping(params = "search", method = RequestMethod.POST)
	public String Search(HttpServletRequest request,HttpServletResponse response, RedirectAttributes ra) throws UnsupportedEncodingException 
	{
		request.setCharacterEncoding("UTF-8");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/UserManagement");
		//Get data from form
		UserModel user = new UserModel();
		user.setCompany_cd(request.getParameter("cmpn_cd"));
		user.setEmployee_cd(request.getParameter("employee_code"));
		user.setName(request.getParameter("employee_name"));
		user.setPasword(request.getParameter("employee_pass"));
		user.setEmployment_CD(request.getParameter("employee_code"));
		user.setPhone(request.getParameter("employee_phone"));
		user.setCompany_name(request.getParameter("cmpn_na"));
		user.setDate_expire(request.getParameter("date_expire"));
		user.setTime_expire(request.getParameter("time_expire"));
		
		UserSelectDao userSelectDao = new UserSelectDao(user);
		
		List<UserModel> lst;
		try {
			lst = userSelectDao.excute();
			if(lst.size()>0)
			{
				ra.addFlashAttribute("lst", lst);		
				ra.addFlashAttribute(ParamsConstants.MESSAGE_NOTIFICATION, "TÌM THẤY "+lst.size()+" TÀI KHOẢN");
			}
			else
			{
				ra.addFlashAttribute(ParamsConstants.MESSAGE_NOTIFICATION, "KHÔNG TÌM THẤY TÀI KHOẢN, HÃY THAY ĐỔI ĐIỀU KIỆN TÌM KIẾM");				
			}
			
			ra.addFlashAttribute("user", user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ra.addFlashAttribute(ParamsConstants.MESSAGE_ERROR, "XÃ RA MỘT LỖI HỆ THỐNG, LIÊN HỆ QUẢN TRỊ VIÊN");
			e.printStackTrace();
		}
	
		
		return "redirect:/UserManagement";
	}
	@RequestMapping(params = "register", method = RequestMethod.POST)
	public String Insert(HttpServletRequest request,HttpServletResponse response, RedirectAttributes ra) 
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/UserManagement");
		//Get data from form
		UserModel user = new UserModel();
		user.setCompany_cd(request.getParameter("cmpn_cd"));
		user.setEmployee_cd(request.getParameter("employee_code"));
		user.setName(request.getParameter("employee_name"));
		user.setPasword(request.getParameter("employee_pass"));
		user.setEmployment_CD(request.getParameter("employee_code"));
		user.setPhone(request.getParameter("employee_phone"));
		user.setCompany_name(request.getParameter("cmpn_na"));
		user.setPassword_Default("123456");
		String role = request.getParameter("role");
		user.setDate_expire(request.getParameter("date_expire"));
		user.setTime_expire(request.getParameter("time_expire"));
		
		if(Common.isNotCheckEmpty(role))
		{
			user.setRole(role);
		}
		else
		{
			user.setRole("member");
		}
		
		user.setUser_Insert(Common.getSessionValue(request, SessionConstants.SESSION_USER_ID));
		user.setInsert_Dt(Common.getDateCurrent(ParamsConstants.CD_FULL_DATE));
		user.setUser_Update(Common.getSessionValue(request, SessionConstants.SESSION_USER_ID));
		user.setUpdate_Dt(Common.getDateCurrent(ParamsConstants.CD_FULL_DATE));
		//Validate
		String Error= "";
		if(CheckIsNull(user.getCompany_cd())) {
			Error = Error + "CÔNG TY LÀ BẮT BUỘC<br>";
		}
		if(CheckIsNull(user.getEmployee_cd())) {
			Error = Error + "MÃ NHÂN VIÊN LÀ BẮT BUỘC<br>";
		}
		if(CheckIsNull(user.getName())) {
			Error = Error + "TÊN NHÂN VIÊN LÀ BẮT BUỘC<br>";
		}
		if(CheckIsNull(user.getPasword())) {
			Error = Error + "MẬT KHẨU LÀ BẮT BUỘC<br>";
		}
		
		if(Error.trim().length()>0)
		{
			mv.addObject(ParamsConstants.MESSAGE_ERROR, Error);
			ra.addFlashAttribute(ParamsConstants.MESSAGE_ERROR, Error);
			ra.addFlashAttribute("user", user);
		}
		else
		{
			UserModel userCheck = new UserModel();
			userCheck.setEmployee_cd(user.getEmployee_cd());
			UserSelectDao userSelectDao = new UserSelectDao(userCheck);
			try {
				List<UserModel> lst = userSelectDao.excute();
				if(lst.size()==0)
				{
					UserInsertDao usi = new UserInsertDao(user);
					try {
						usi.excute();
						ra.addFlashAttribute(ParamsConstants.MESSAGE_NOTIFICATION, "THÊM THÀNH CÔNG");
						CompanyPermision comPer =new CompanyPermision();
						comPer.getCompany().setCompany_cd(user.getCompany_cd());
						comPer.getCompany().setCompany_user(user.getEmployee_cd());
						comPer.setValueCheck("0");
						CompanyPermisionInsertDao insert = new CompanyPermisionInsertDao(comPer);
						insert.excute();
						
						ServiceSelectDao  select = new ServiceSelectDao();
						List<ServiceModel> lstRole= null;
						lstRole = select.excute();
						for( int f= 0; f<lstRole.size();f++)
						{
							UserRoleMapping roleItem  =new UserRoleMapping();
							roleItem.setCompany_cd(user.getCompany_cd());
							roleItem.setUser_cd(user.getEmployee_cd());
							roleItem.setSerive_cd(lstRole.get(f).getService_id());
							roleItem.setR_access(null);
							roleItem.setR_delete(null);
							roleItem.setR_export(null);
							roleItem.setR_print(null);
							roleItem.setR_update(null);
							roleItem.setR_read(null);
							roleItem.setR_write(null);
							
							roleItem.setRole_cd(Common.getDateCurrent("YYYYMMddHHmmSS"));
							UserRoleMappingInsertDao userUpdate = new UserRoleMappingInsertDao(roleItem);
							userUpdate.excute();
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						ra.addFlashAttribute(ParamsConstants.MESSAGE_ERROR, "TÁC VỤ THỰC HIỆN KHÔNG THÀNG CÔNG");
						ra.addFlashAttribute("user", user);
						e.printStackTrace();
					}
				}
				else
				{
					ra.addFlashAttribute(ParamsConstants.MESSAGE_ERROR, "ĐÃ TỒN TẠI TÀI KHOẢN NGƯỜI DÙNG NÀY");
					ra.addFlashAttribute("user", user);
				}
			} catch (SQLException e1) {
				ra.addFlashAttribute(ParamsConstants.MESSAGE_ERROR, "TÁC VỤ THỰC HIỆN KHÔNG THÀNG CÔNG");
				ra.addFlashAttribute("user", user);
				e1.printStackTrace();
			}
			
		}
		return "redirect:/UserManagement";
		
	
	}
	@RequestMapping(params = "back", method = RequestMethod.POST)
	public ModelAndView Back(HttpServletRequest request,HttpServletResponse response) 
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/admin-manager");
		return mv;
		
	
	}
	
	@RequestMapping(params = "delete", method = RequestMethod.POST)
	public ModelAndView delete(HttpServletRequest request,HttpServletResponse response) 
	{
		ModelAndView mv = new ModelAndView();
		String employee_cd  =request.getParameter("employee_cd");
		if(Common.isNotCheckEmpty(employee_cd))
		{
			UserDeleteDao udel  = new UserDeleteDao(employee_cd);
			try {
				udel.excute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		mv.setViewName("redirect:/UserManagement");
		return mv;
		
	
	}
	
	public boolean CheckIsNull(String object)
	{
		if(object == null || object.trim().length()==0)
		{
			return true;
		}
		return false;
	}


}
