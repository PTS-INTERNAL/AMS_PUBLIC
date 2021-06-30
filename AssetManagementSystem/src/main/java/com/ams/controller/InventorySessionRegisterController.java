package com.ams.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ams.dao.InventorySessionInsertDao;
import com.ams.dao.PermissionInventorySessionInsertDao;
import com.ams.dao.UserSelectDao;
import com.ams.model.InventorySessionModel;
import com.ams.model.PermissionInventorySession;
import com.ams.model.User;
import com.ams.model.UserModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;


@Controller
@RequestMapping("InventorySessionRegister")
public class InventorySessionRegisterController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request)
	{
		String TITLESCREEN = "MÀN HÌNH ĐĂNG KÝ PHIÊN KIỂM KÊ";
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLESCREEN);
		mv.setViewName("views/InventorySessionRegister.jsp");
		return mv;
	}
	
	@RequestMapping(params="save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, RedirectAttributes att)
	{
		String TITLESCREEN = "MÀN HÌNH ĐĂNG KÝ PHIÊN KIỂM KÊ";
		ModelAndView mv = new ModelAndView();
		InventorySessionModel inSes = new InventorySessionModel();
		inSes.setInventorySessionName(request.getParameter("name"));
		inSes.setInventorySessionShortCD(request.getParameter("code"));
		String startDate="";
		try {
			startDate = Common.ConvertStringToDateStr(request.getParameter("startdate"), "yyyy-mm-dd","dd/mm/yyyy");
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		inSes.setInventorySessionStart(startDate);
		String EndDate="";
		try {
			EndDate = Common.ConvertStringToDateStr(request.getParameter("enddate"), "yyyy-mm-dd","dd/mm/yyyy");
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		inSes.setInventorySessionEnd(EndDate);
		inSes.setInventorySessionCompanyCD(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		
		/*
		String str_count_employee = request.getParameter("count_employee");
		int count_employee = Integer.parseInt(str_count_employee);
		
		List<UserModel> lstUser = new ArrayList<UserModel>();
		for(int i =0;i<=count_employee;i++)
		{
			UserModel userModel = new UserModel();
			try
			{
				userModel.setEmployee_cd(request.getParameter("item.emloyee["+i+"]"));
				if(userModel.getEmployee_cd() !=null && userModel.getEmployee_cd().trim().length()>0)
				{
					lstUser.add(userModel);
				}
				
			}
			catch (Exception e)
			{
				System.out.println("Khong tim thay user");
			}
		}
		*/
		
		String error = "";
		if(inSes.getInventorySessionName()==null || inSes.getInventorySessionName().trim().length()==0)
		{
			error +="TÊN PHIÊN KIỂM KÊ LÀ BẮT BUỘC<br>";
		}
		if(inSes.getInventorySessionShortCD()==null || inSes.getInventorySessionShortCD().trim().length()==0)
		{
			error +="MÃ QUẢN LÝ KIỂM KÊ LÀ BẮT BUỘC<br>";
		}
		if(inSes.getInventorySessionStart()==null || inSes.getInventorySessionStart().trim().length()==0)
		{
			error +="NGÀY BẮT ĐẦU LÀ BẮT BUỘC<br>";
		}
		if(inSes.getInventorySessionEnd()==null || inSes.getInventorySessionEnd().trim().length()==0)
		{
			error +="NGÀY KẾT THÚC LÀ BẮT BUỘC<br>";
		}
		if(error.trim().length()>0)
		{
			mv.addObject(ParamsConstants.MESSAGE_ERROR, error);
			mv.addObject("inSes", inSes);
		}
		else
		{
			inSes.setInvenotrySessionCD(Common.getDateCurrent(ParamsConstants.CD_FULL_FORMAT));
			inSes.getUserInsert().setEmployee_cd(Common.getSessionValue(request, SessionConstants.SESSION_USER_ID));
			inSes.getUserUpdate().setEmployee_cd(Common.getSessionValue(request, SessionConstants.SESSION_USER_ID));
			inSes.setDate_insert(Common.getDateCurrent("YYYYMMdd"));
			inSes.setDate_update(Common.getDateCurrent("YYYYMMdd"));
			
			InventorySessionInsertDao invenInsert = new InventorySessionInsertDao(inSes);
			try {
				invenInsert.excute();
				mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "ĐĂNG KÝ PHIÊN KIỂM KÊ THÀNH CÔNG");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			/*
			if(lstUser.size()>0)
			{
				for(int i =0;i<lstUser.size();i++)
				{
					PermissionInventorySession permiss = new PermissionInventorySession();
					permiss.setUser(lstUser.get(i));
					permiss.getInventorySession().setInventory_id(inSes.getInvenotrySessionCD());
					permiss.setUserInsert(Common.getSessionValue(request, SessionConstants.SESSION_USER_ID));
					permiss.setUserUpdate(Common.getSessionValue(request, SessionConstants.SESSION_USER_ID));
					permiss.setInsert_dt(Common.getDateCurrent("YYYYMMdd"));
					permiss.setUpdate_dt(Common.getDateCurrent("YYYYMMdd"));
					
					PermissionInventorySessionInsertDao inser = new PermissionInventorySessionInsertDao(permiss);
					try {
						inser.excute();
						mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "ĐĂNG KÝ PHIÊN KIỂM KÊ THÀNH CÔNG");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			*/
			
		}
		
		
		
		
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLESCREEN);
		mv.setViewName("views/InventorySessionRegister.jsp");
		return mv;
	}
	
	@RequestMapping(params="back", method = RequestMethod.POST)
	public ModelAndView back(HttpServletRequest request, RedirectAttributes att)
	{
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/InventorySessionManagement");
		return mv;
	}
	
	

}
