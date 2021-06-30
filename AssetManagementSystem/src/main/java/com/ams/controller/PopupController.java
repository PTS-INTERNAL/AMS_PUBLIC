package com.ams.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.AssetGeneralIncludeSelectDao;
import com.ams.dao.CompanySelectDao;
import com.ams.dao.DepartmentPermissionSelectDao;
import com.ams.dao.DepartmentSelectDao;
import com.ams.dao.GroupAssetSelectDao;
import com.ams.dao.UserSelectDao;
import com.ams.model.AssetGeneralFormSearch;
import com.ams.model.AssetGeneralModel;
import com.ams.model.CompanyModel;
import com.ams.model.Department;
import com.ams.model.GroupAsset;
import com.ams.model.UserModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;
import com.ams.util.SystemControl;

@Controller
public class PopupController {
	
	@RequestMapping("/GetListDepartment")
	public ModelAndView GetListDepartment(HttpServletRequest request)
	{
		String TITLE = "DANH SÁCH CÁC ĐƠN VỊ TRONG DOANH NGHIỆP";
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		Department dept = new Department();
		String cmpnd = request.getParameter("cmpn_cd");
		if(Common.isEmpty(cmpnd))
		{
			dept.setCompany_cd(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		}
		else
		{
			dept.setCompany_cd(cmpnd);

		}
		
		String userCd = (String) request.getSession().getAttribute(SessionConstants.SESSION_USER_ID);
		
		DepartmentPermissionSelectDao departmentSelectDao = new DepartmentPermissionSelectDao(dept,userCd);
		List<Department> lstDept = new ArrayList<Department>();
		
		try {
			lstDept = departmentSelectDao.excute();
			mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "TÌM THẤY " + lstDept.size() + " ĐƠN VỊ");
			if(lstDept.size()>0)
			{
				mv.addObject("lst", lstDept);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			mv.addObject(ParamsConstants.MESSAGE_ERROR, e.toString());
		}
		mv.setViewName("views/PopupGetListDepartment.jsp");
		
		return mv;
	}
	
	@RequestMapping("/GetListGroupAsset")
	public ModelAndView GetListGroupAsset(HttpServletRequest request)
	{
		String TITLE = "DANH SÁCH CÁC NHÓM TÀI SẢN ";
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		GroupAsset dept = new GroupAsset();
		dept.setCompany_cd(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		GroupAssetSelectDao departmentSelectDao = new GroupAssetSelectDao(dept);
		List<GroupAsset> lstDept = new ArrayList<GroupAsset>();
		
		try {
			lstDept = departmentSelectDao.excute();
			mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "TÌM THẤY" + lstDept.size() + "NHÓM TÀI SẢN");
			if(lstDept.size()>0)
			{
				mv.addObject("lst", lstDept);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			mv.addObject(ParamsConstants.MESSAGE_ERROR, e.toString());
		}
		mv.setViewName("views/PopupGetListGroupAsset.jsp");
		
		return mv;
	}
	
	@RequestMapping("/GetListCompanyTable")
	public ModelAndView GetListCompany(HttpServletRequest request)
	{
		String TITLE = "DANH SÁCH CÁC  DOANH NGHIỆP";
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		CompanySelectDao cmpny = new CompanySelectDao();
		List<CompanyModel> lstCmpn = new ArrayList<CompanyModel>();
		
		try {
			lstCmpn = cmpny.excute();
			mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "TÌM THẤY " + lstCmpn.size() + " DOANH NGHIỆP");
			if(lstCmpn.size()>0)
			{
				mv.addObject("lst", lstCmpn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			mv.addObject(ParamsConstants.MESSAGE_ERROR, e.toString());
		}
		mv.setViewName("views/PopupGetListCompanyTable.jsp");
		
		return mv;
	}
	@RequestMapping("/PopupCompanyInput")
	public ModelAndView PopupCompanyInput(HttpServletRequest request)
	{
		String TITLE = "DANH SÁCH CÁC  DOANH NGHIỆP";
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		CompanyModel cpm = new CompanyModel();
		cpm.setCompany_delete("0");
		CompanySelectDao cmpny = new CompanySelectDao(cpm);
		List<CompanyModel> lstCmpn = new ArrayList<CompanyModel>();
		
		try {
			lstCmpn = cmpny.excute();
			mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "TÌM THẤY " + lstCmpn.size() + " DOANH NGHIỆP");
			if(lstCmpn.size()>0)
			{
				mv.addObject("lst", lstCmpn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			mv.addObject(ParamsConstants.MESSAGE_ERROR, e.toString());
		}
		mv.setViewName("views/PopupCompanyInput.jsp");
		
		return mv;
	}
	
	@RequestMapping("/PopupUserInput")
	public ModelAndView PopupUserInput(HttpServletRequest request)
	{
		String TITLE = "DANH SÁCH CÁC NGƯỜI DÙNG TRONG CÔNG TY";
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		
		UserModel user = new UserModel();
		UserSelectDao userSelect = new UserSelectDao();
		
		try {
			List<UserModel> lstUser = userSelect.excute();
			mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "TÌM THẤY " + lstUser.size() + " NGƯỜI DÙNG");
			if(lstUser.size()>0)
			{
				mv.addObject("lst", lstUser);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			mv.addObject(ParamsConstants.MESSAGE_ERROR, e.toString());
		}
		mv.setViewName("views/PopupUserInput.jsp");
		
		return mv;
	}
	@RequestMapping("/UserRightManagement/PopupCompanyInput")
	public ModelAndView PopupCompanyInputTemp(HttpServletRequest request)
	{
		String TITLE = "DANH SÁCH CÁC  DOANH NGHIỆP";
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		CompanySelectDao cmpny = new CompanySelectDao();
		List<CompanyModel> lstCmpn = new ArrayList<CompanyModel>();
		
		try {
			lstCmpn = cmpny.excute();
			mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "TÌM THẤY " + lstCmpn.size() + " DOANH NGHIỆP");
			if(lstCmpn.size()>0)
			{
				mv.addObject("lst", lstCmpn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			mv.addObject(ParamsConstants.MESSAGE_ERROR, e.toString());
		}
		mv.setViewName("/views/PopupCompanyInput.jsp");
		
		return mv;
	}
	@RequestMapping( value = "/GetUserPermission", method = RequestMethod.GET, 
			produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String GetUserForPermission(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException
	{
		request.setCharacterEncoding("UTF-8");
		String employee_code = request.getParameter("employee_code");
		String employee_name = request.getParameter("employee_name");
		response.setContentType("text/plain;charset=UTF-8");
		UserModel user = new UserModel();
		user.setEmployee_cd(employee_code);
		user.setName(employee_name);
		if(employee_code==null || employee_code.trim().length()==0)
		{
			return "message_Mã nhân viên là bắt buộc";
		}
		UserSelectDao userSelectDao = new  UserSelectDao(user);
		
		try {
			List<UserModel> lstUser = userSelectDao.excute();
			if(lstUser.size() > 0)
			{
				UserModel userPermission = lstUser.get(0);
				return userPermission.getEmployment_CD().trim() + "_" + userPermission.getName().trim() + "_" +userPermission.getDepartment();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			//System.out.println("Lỗi lấy user gán quyền người dùng kiểm kê");
		}
		
		return "message_Không tìm thấy nhân viên này";
	}
	

}
