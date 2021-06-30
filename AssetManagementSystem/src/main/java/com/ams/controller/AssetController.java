package com.ams.controller;


import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.AssetGeneralIncludeSelectDao;
import com.ams.helper.ExcelAssetGeneralListReportView;
import com.ams.helper.PdfUserListReportView;
import com.ams.model.AssetGeneralFormSearch;
import com.ams.model.AssetGeneralModel;
import com.ams.util.AuthenticationLogin;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;
import com.ams.util.SystemControl;
import com.ams.util.UrlCommon;



@Controller
@RequestMapping("/AssetManagementGeneral")
public class AssetController {
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String add(ModelMap modelMap, HttpServletRequest request) 
	{
		AuthenticationLogin auth = new AuthenticationLogin(request);
		if(auth.isLogin())
		{
			AssetGeneralFormSearch form = new AssetGeneralFormSearch(request);
			form.setStatus("ALL");
			modelMap.addAttribute("TittleScreen","MÀN HÌNH QUẢN LÝ TÀI SẢN CHUNG");

			modelMap.addAttribute("formSearch",form);

			return "views/AssetManagementGeneral.jsp";
		}
		else
		{
			return UrlCommon.LoginUrl;
		}
		
	}
	
	@RequestMapping(params = "search", method = RequestMethod.POST)
	public ModelAndView search(ModelMap modelMap, HttpServletRequest request) throws SQLException 
	{
		
		ModelAndView mv = new ModelAndView();
		AssetGeneralFormSearch form = new AssetGeneralFormSearch();
		form.setRFID(request.getParameter("text_rfid"));
		form.setName(request.getParameter("text_asset_name"));
		form.setModel(request.getParameter("text_model"));
		form.setSeries(request.getParameter("text_series"));
		form.setAccountant_CD(request.getParameter("text_accountant"));
		form.setGroup_id(request.getParameter("group_asset_cd"));
		form.setGroup_name(request.getParameter("group_asset_na"));
		form.setDepartment_cd(request.getParameter("department_cd"));
		form.setDepartment_name(request.getParameter("department_name"));
		form.setPriceEnd(request.getParameter("text_end_price"));
		form.setPriceStart(request.getParameter("text_start_price"));
		form.setStatus(request.getParameter("optradio"));
		
		String setDateStart_Start = request.getParameter("text_start_date_s");
		String  setDateStart_End = request.getParameter("text_start_date_e");
		String  setDateEnd_Start = request.getParameter("text_end_date_s");
		String  setDateEnd_End = request.getParameter("text_end_date_e");
		String error = "";
		System.out.println("Page_id:");
		System.out.println(request.getParameter("Page_id"));
		String page=request.getParameter("Page_id");

		//######################################
		AssetGeneralFormSearch form1 = form;
		
		
		if(setDateStart_Start != null && setDateStart_Start.trim().length()>0)
		{
			try {
			String dateEndConvert = Common.ConvertStringToDateStr(setDateStart_Start, "yyyy-mm-dd","dd/mm/yyyy");
			form.setDateStart_Start(dateEndConvert);
			} catch (ParseException e1) {
				error = error + "Giá trị ngày không hợp lệ <br>";
			}
		}
		if(setDateStart_End != null && setDateStart_End.trim().length()>0)
		{
			try {
			String dateEndConvert = Common.ConvertStringToDateStr(setDateStart_End, "yyyy-mm-dd","dd/mm/yyyy");
			form.setDateStart_End(dateEndConvert);
			} catch (ParseException e1) {
				error = error + "Giá trị ngày không hợp lệ <br>";
			}
		}
		if(setDateEnd_Start != null && setDateEnd_Start.trim().length()>0)
		{
			try {
			String dateEndConvert = Common.ConvertStringToDateStr(setDateEnd_Start, "yyyy-mm-dd","dd/mm/yyyy");
			form.setDateEnd_Start(dateEndConvert);
			} catch (ParseException e1) {
				error = error + "Giá trị ngày không hợp lệ <br>";
			}
		}
		if(setDateEnd_End != null && setDateEnd_End.trim().length()>0)
		{
			try {
			String dateEndConvert = Common.ConvertStringToDateStr(setDateEnd_End, "yyyy-mm-dd","dd/mm/yyyy");
			form.setDateEnd_End(dateEndConvert);
			} catch (ParseException e1) {
				error = error + "Giá trị ngày không hợp lệ <br>";
			}
		}
		
		if(error.trim().length()==0)
		{
			AssetGeneralFormSearch asset = new AssetGeneralFormSearch();
			asset.setCompany_CD((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
			AssetGeneralIncludeSelectDao AssetSelectDao = new AssetGeneralIncludeSelectDao(asset);
			mv.addObject("listAssets",AssetSelectDao.excute() );
			
			form.setCompany_CD((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
			AssetGeneralIncludeSelectDao AssetSelectDaoSearch = new AssetGeneralIncludeSelectDao(form);
			List<AssetGeneralModel> lstAsset = AssetSelectDaoSearch.excute();
			request.setAttribute("numberofrow", lstAsset.size());
			//mv.addObject("listAssetSearch", lstAsset);
			
			
			//###################
			
			if(page==null||page.trim()=="") 
				page="1";
			 
			form1.setRowPage(page);		
			form1.setCompany_CD((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
			AssetGeneralIncludeSelectDao AssetSelectDaoSearchbyRow = new AssetGeneralIncludeSelectDao(form1);
			List<AssetGeneralModel> lstAsset1 = AssetSelectDaoSearchbyRow .excute();
			mv.addObject("listAssetSearch", lstAsset1);
			
			
			
			if(lstAsset ==null || lstAsset.size()==0)
			{
				modelMap.addAttribute("message","Không tìm thấy dữ liệu yêu cầu<br>Xin thay đổi điều kiện tìm kiếm");
			}
			System.out.println(lstAsset.size());
			
		}
		else
		{
			mv.addObject(ParamsConstants.MESSAGE_ERROR,error);
		}	
		request.setAttribute("pagenum", Integer.parseInt(page));
		form.setDateStart_Start(setDateStart_Start);
		form.setDateStart_End(setDateStart_End);
		form.setDateEnd_Start(setDateEnd_Start);
		form.setDateEnd_End(setDateEnd_End);
		mv.addObject("formSearch",form);
		mv.addObject("TittleScreen","MÀN HÌNH QUẢN LÝ TÀI SẢN CHUNG");
		
		mv.setViewName("views/AssetManagementGeneral.jsp");
		return mv;
	}
	
	@RequestMapping(params = "reportExcel", method = RequestMethod.POST)
	public ModelAndView excel(ModelMap modelMap, HttpServletRequest request) throws SQLException 
	{
		ModelAndView mv = new ModelAndView();
		
		
		AssetGeneralFormSearch form = new AssetGeneralFormSearch(request);
		
		form.setRFID(request.getParameter("text_rfid"));
		form.setName(request.getParameter("text_asset_name"));
		form.setModel(request.getParameter("text_model"));
		form.setSeries(request.getParameter("text_series"));
		form.setAccountant_CD(request.getParameter("text_accountant"));
		form.setGroup_id(request.getParameter("group_asset_cd"));
		form.setGroup_name(request.getParameter("group_asset_na"));
		form.setDepartment_cd(request.getParameter("department_cd"));
		form.setDepartment_name(request.getParameter("department_name"));
		form.setPriceEnd(request.getParameter("text_end_price"));
		form.setPriceStart(request.getParameter("text_start_price"));
		form.setStatus(request.getParameter("optradio"));
		
		String setDateStart_Start = request.getParameter("text_start_date_s");
		String  setDateStart_End = request.getParameter("text_start_date_e");
		String  setDateEnd_Start = request.getParameter("text_end_date_s");
		String  setDateEnd_End = request.getParameter("text_end_date_e");
		String error = "";
		if(setDateStart_Start != null && setDateStart_Start.trim().length()>0)
		{
			try {
			String dateEndConvert = Common.ConvertStringToDateStr(setDateStart_Start, "yyyy-mm-dd","dd/mm/yyyy");
			form.setDateStart_Start(dateEndConvert);
			} catch (ParseException e1) {
				error = error + "Giá trị ngày không hợp lệ <br>";
			}
		}
		if(setDateStart_End != null && setDateStart_End.trim().length()>0)
		{
			try {
			String dateEndConvert = Common.ConvertStringToDateStr(setDateStart_End, "yyyy-mm-dd","dd/mm/yyyy");
			form.setDateStart_End(dateEndConvert);
			} catch (ParseException e1) {
				error = error + "Giá trị ngày không hợp lệ <br>";
			}
		}
		if(setDateEnd_Start != null && setDateEnd_Start.trim().length()>0)
		{
			try {
			String dateEndConvert = Common.ConvertStringToDateStr(setDateEnd_Start, "yyyy-mm-dd","dd/mm/yyyy");
			form.setDateEnd_Start(dateEndConvert);
			} catch (ParseException e1) {
				error = error + "Giá trị ngày không hợp lệ <br>";
			}
		}
		if(setDateEnd_End != null && setDateEnd_End.trim().length()>0)
		{
			try {
			String dateEndConvert = Common.ConvertStringToDateStr(setDateEnd_End, "yyyy-mm-dd","dd/mm/yyyy");
			form.setDateEnd_End(dateEndConvert);
			} catch (ParseException e1) {
				error = error + "Giá trị ngày không hợp lệ <br>";
			}
		}
		
		if(error.trim().length()==0)
		{
			mv.addObject("TittleScreen","MÀN HÌNH QUẢN LÝ TÀI SẢN CHUNG");
			AssetGeneralFormSearch assetSearch = new AssetGeneralFormSearch();
			form.setCompany_CD((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
			AssetGeneralIncludeSelectDao AssetSelectDao = new AssetGeneralIncludeSelectDao(assetSearch);
			mv.addObject("listAssets",AssetSelectDao.excute() );
			AssetGeneralIncludeSelectDao AssetSelectDaoSearch = new AssetGeneralIncludeSelectDao(form);
			SystemControl systemControl = new SystemControl(request);
			List<AssetGeneralModel> lstAsset = AssetSelectDaoSearch.excute();
			mv.addObject("listAssetSearch", lstAsset);
			mv.addObject("formSearch",form);
			if(lstAsset ==null || lstAsset.size()==0)
			{
				modelMap.addAttribute("message","Không tìm thấy dữ liệu yêu cầu<br>Xin thay đổi điều kiện tìm kiếm");
			}
			else
			{
				if(request.getParameter("reportExcel")!=null)
				{
					//System.out.println("vô rồi nè");
					return new ModelAndView(new ExcelAssetGeneralListReportView(), "userList", lstAsset);
				}
				if(request.getParameter("reportPDF")!=null)
				{
					//System.out.println("vô rồi nè");
					return new ModelAndView(new PdfUserListReportView(), "userList", lstAsset);
				}
			}
		}
		else
		{
			mv.addObject(ParamsConstants.MESSAGE_ERROR,error);
		}
		form.setDateStart_Start(setDateStart_Start);
		form.setDateStart_End(setDateStart_End);
		form.setDateEnd_Start(setDateEnd_Start);
		form.setDateEnd_End(setDateEnd_End);
		mv.addObject("formSearch",form);
		mv.setViewName("views/AssetManagementGeneral.jsp");
		return mv;
	}
	
	@RequestMapping(params = "reportPDF", method = RequestMethod.POST)
	public ModelAndView PDF(ModelMap modelMap, HttpServletRequest request) throws SQLException 
	{
		ModelAndView mv = new ModelAndView();
		AssetGeneralFormSearch form = new AssetGeneralFormSearch(request);
		mv.addObject("TittleScreen","MÀN HÌNH QUẢN LÝ TÀI SẢN CHUNG");
		form.setCompany_CD((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));

		AssetGeneralIncludeSelectDao AssetSelectDao = new AssetGeneralIncludeSelectDao(form);
		mv.addObject("listAssets",AssetSelectDao.excute() );
		form.setCompany_CD((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
		AssetGeneralIncludeSelectDao AssetSelectDaoSearch = new AssetGeneralIncludeSelectDao(form);
		SystemControl systemControl = new SystemControl(request);
		List<AssetGeneralModel> lstAsset = AssetSelectDaoSearch.excute();
		mv.addObject("listAssetSearch", lstAsset);
		mv.addObject("formSearch",form);
		if(lstAsset ==null || lstAsset.size()==0)
		{
			modelMap.addAttribute("message","Không tìm thấy dữ liệu yêu cầu<br>Xin thay đổi điều kiện tìm kiếm");
		}
		else
		{
			if(request.getParameter("reportExcel")!=null)
			{
				//System.out.println("vô rồi nè");
				return new ModelAndView(new ExcelAssetGeneralListReportView(), "userList", lstAsset);
			}
			if(request.getParameter("reportPDF")!=null)
			{
				//System.out.println("vô rồi nè");
				return new ModelAndView(new PdfUserListReportView(), "userList", lstAsset);
			}
		}
		mv.setViewName("views/AssetManagementGeneral.jsp");
		return mv;
	}
	
	@RequestMapping(params = "back", method = RequestMethod.POST)
	public ModelAndView back(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/FeatureSystem");
			
		return mv;
	}
	@RequestMapping(params = "register", method = RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/AssetGeneralRegister");
			
		return mv;
	}
	@RequestMapping(params = "import", method = RequestMethod.POST)
	public ModelAndView importfile(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/ImportCSVAssetGenneral");
			
		return mv;
	}
}

	