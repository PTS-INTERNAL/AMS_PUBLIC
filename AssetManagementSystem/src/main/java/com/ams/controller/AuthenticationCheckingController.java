package com.ams.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.DepartmentSelectDao;
import com.ams.dao.InventorySessionPermissionSelectDao;
import com.ams.dao.InventorySessionSelectDao;
import com.ams.dao.UserSelectDao;
import com.ams.model.Department;
import com.ams.model.InventorySession;
import com.ams.model.InventorySessionModel;
import com.ams.model.InventorySessionPermission;
import com.ams.model.PermissionInventorySession;
import com.ams.model.UserModel;
import com.ams.util.Common;
import com.ams.util.SessionConstants;

@Controller
@RequestMapping("/AuthenticationChecking")
public class AuthenticationCheckingController {
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView init(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		String Session_ID = request.getParameter("id_session");
		String usn = request.getParameter("username");
		String pwd = request.getParameter("password");

		// Kiểm tra là có đúng password hay không?
		// Kiểm tra tính hợp lệ
		// Kiểm tra username rỗng
		
		if (usn != null && usn.length() > 0) {
			// Kiểm tra password rỗng
			if (pwd != null && pwd.length() > 0) {

				UserModel user = new UserModel();
				user.setEmployee_cd(usn);
				user.setPasword(pwd);
				UserSelectDao userSelectDao = new UserSelectDao(user);

				List<UserModel> userModel;
				try {
					userModel = userSelectDao.excute();
					if (userModel.size() > 0) {
						PermissionInventorySession inventorySessionPermission = new PermissionInventorySession();
						inventorySessionPermission.getUser().setEmployment_CD(userModel.get(0).employee_cd);
						inventorySessionPermission.getInventorySession().setInventory_id(Session_ID);
						InventorySessionPermissionSelectDao inventorySessionPermissionSelectDao = new InventorySessionPermissionSelectDao(
								inventorySessionPermission);
						List<PermissionInventorySession> lst = inventorySessionPermissionSelectDao.excute();
						if (lst.size() > 0) {
							
							InventorySessionModel ivn = new InventorySessionModel();
							ivn.setInvenotrySessionCD(Session_ID);
							InventorySessionSelectDao selectSess = new InventorySessionSelectDao(ivn);
							List<InventorySessionModel> lstIsm = selectSess.excute();
							mv.addObject("sess", lstIsm.get(0));
							Department dept  = new Department();
							dept.setCompany_cd(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
							DepartmentSelectDao selectDept = new DepartmentSelectDao(dept);
							List<Department> lstDept = selectDept.excute();
							mv.addObject("lstDept", lstDept);
							mv.setViewName("views/PDAMenuChecking.jsp");
						} else {
							InventorySessionSelectDao inventorySessionSelectDao = new InventorySessionSelectDao();

							List<InventorySessionModel> lstInventory = new ArrayList<InventorySessionModel>();
							lstInventory = inventorySessionSelectDao.excute();

							if (lstInventory.size() > 0) {
								mv.addObject("lstInventory", lstInventory);
							}
							mv.addObject("message", "Bạn không có quyền sử dụng chức năng này!!");
							mv.setViewName("views/PDAInventory.jsp");
						}
					} else {
						InventorySessionSelectDao inventorySessionSelectDao = new InventorySessionSelectDao();

						List<InventorySessionModel> lstInventory = new ArrayList<InventorySessionModel>();
						lstInventory = inventorySessionSelectDao.excute();

						if (lstInventory.size() > 0) {
							mv.addObject("lstInventory", lstInventory);
						}
						mv.addObject("message", "Mật khẩu và tài khoản không đúng");
						mv.setViewName("views/PDAInventory.jsp");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
			}
			else
			{
				InventorySessionSelectDao inventorySessionSelectDao = new InventorySessionSelectDao();

				List<InventorySessionModel> lstInventory = new ArrayList<InventorySessionModel>();
				try {
					lstInventory = inventorySessionSelectDao.excute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (lstInventory.size() > 0) {
					mv.addObject("lstInventory", lstInventory);
				}
				mv.addObject("message", "Mật khẩu là bắt buộc");
				mv.setViewName("views/PDAInventory.jsp");
			}
		}
		else
		{
			InventorySessionSelectDao inventorySessionSelectDao = new InventorySessionSelectDao();

			List<InventorySessionModel> lstInventory = new ArrayList<InventorySessionModel>();
			try {
				lstInventory = inventorySessionSelectDao.excute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (lstInventory.size() > 0) {
				mv.addObject("lstInventory", lstInventory);
			}
			mv.addObject("message", "Tên đăng nhập là bắt buộc");
			mv.setViewName("views/PDAInventory.jsp");
		}
		mv.addObject("company", Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_NAME));
		mv.addObject("session_id", Session_ID);
		
		return mv;
	}
	
	@RequestMapping(params="checking", method=RequestMethod.POST)
	public ModelAndView Checking(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		String SessionInventoryCD = request.getParameter("inventoryCD");
		String DeptChecking =  request.getParameter("select_group");
		request.getSession().setAttribute("INVENTORY_CD", SessionInventoryCD);
		request.getSession().setAttribute("DEPT_CD", DeptChecking);
		mv.setViewName("redirect:/InventoryChecking");
		return mv;
	}
	@RequestMapping(params="create_new", method=RequestMethod.POST)
	public ModelAndView create_new(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		String SessionInventoryCD = request.getParameter("inventoryCD");
		String DeptChecking =  request.getParameter("select_group");
		request.getSession().setAttribute("INVENTORY_CD", SessionInventoryCD);
		request.getSession().setAttribute("DEPT_CD", DeptChecking);
		mv.setViewName("redirect:/InventoryChecking");
		return mv;
	}
	@RequestMapping(params="logout", method=RequestMethod.POST)
	public ModelAndView logout(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/PDAInventoryChecking");
		return mv;
	}

}
