package com.ams.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.AssetGeneralIncludeSelectDao;
import com.ams.model.AssetGeneralFormSearch;
import com.ams.model.AssetGeneralModel;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;

@Controller
@RequestMapping("/PopupAddAsset")
public class PopupAddAssetController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		
		mv.setViewName("views/PopupAddAsset.jsp");
		return mv;
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
		
		String error = "";
		System.out.println("Page_id:");
		System.out.println(request.getParameter("Page_id"));
		String page=request.getParameter("Page_id");

		//######################################
		AssetGeneralFormSearch form1 = form;
	
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
		mv.addObject("formSearch",form);
		mv.addObject("TittleScreen","MÀN HÌNH QUẢN LÝ TÀI SẢN CHUNG");
		
		mv.setViewName("views/PopupAddAsset.jsp");
		return mv;
	}

	
	

}
