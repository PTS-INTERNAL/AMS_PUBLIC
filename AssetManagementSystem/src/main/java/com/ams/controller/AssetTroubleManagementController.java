package com.ams.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ams.dao.AssetGeneralDeleteUpdateDao;
import com.ams.dao.AssetGeneralSelectDao;
import com.ams.dao.AssetMaintanceSelectDao;
import com.ams.dao.AssetTroubleDeleteDao;
import com.ams.dao.AssetTroubleSelectDao;
import com.ams.dao.HistoryAssetDeleteInsertDao;
import com.ams.helper.ExelAssetMaintainanceListReportView;
import com.ams.helper.ExelAssetTroubleListReportView;
import com.ams.helper.PdfAssetMotherAndChildView;
import com.ams.helper.PdfAssetTroubleView;
import com.ams.helper.PdfUserListReportView;
import com.ams.model.AssetGeneralFormSearch;
import com.ams.model.AssetGeneralModel;
import com.ams.model.AssetMaintanceModel;
import com.ams.model.HistoryAssetDeleteModel;
import com.ams.model.TroubleAssetModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;

@Controller
@RequestMapping("AssetTroubleManagement")
public class AssetTroubleManagementController {
     String TITLESCREEN = "MÀN HÌNH QUẢN LÝ SỰ CỐ";
    
    @RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request)
	{
		ModelAndView mv =new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLESCREEN);
		
		
		TroubleAssetModel asset = new TroubleAssetModel();
		asset.getAsset().setCompany_CD(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		AssetTroubleSelectDao select  =new AssetTroubleSelectDao(asset);
		List<TroubleAssetModel> lst;
		try {
			lst = select.excute();
			mv.addObject("listAssetSearch", lst);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("views/AssetTroubleManagement.jsp");
		return mv;
	}
    
    @RequestMapping(params="register", method = RequestMethod.POST)
    public ModelAndView register(HttpServletRequest request)
    {
    	ModelAndView mv =new ModelAndView();
		mv.setViewName("redirect:/AssetTroubleRegister");
		return mv;
    }
    
    
    
    @RequestMapping(params="back", method = RequestMethod.POST)
    public ModelAndView back(HttpServletRequest request)
    {
    	ModelAndView mv =new ModelAndView();
		mv.setViewName("redirect:/FeatureSystem");
		return mv;
    }
    
    @RequestMapping(params="search", method = RequestMethod.POST)
    public ModelAndView search(HttpServletRequest request)
    {
    	ModelAndView mv =new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLESCREEN);
		
		TroubleAssetModel asset = new TroubleAssetModel();
		
		asset.getDept().setDept_cd(request.getParameter("department_cd"));
		asset.getDept().setDept_name(request.getParameter("department_name"));
		asset.getAsset().setDepartment_cd(request.getParameter("department_cd"));
		asset.getAsset().setGroup_id(request.getParameter("group_asset_cd"));
		
		asset.getAsset().setDepartment_name(request.getParameter("department_name"));
		asset.getAsset().setGroup_name(request.getParameter("group_asset_na"));
		asset.getAsset().setSeries(request.getParameter("text_series"));
		asset.getAsset().setModel(request.getParameter("text_model"));
		asset.setDateTrouble(request.getParameter("text_start_date_s"));
		asset.setDateTroubleEnd(request.getParameter("text_start_date_e"));
		asset.getAsset().setCompany_CD(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		
	
		System.out.println(request.getParameter("department_name"));
		System.out.println(asset.getAsset().getDepartment_cd());
		AssetTroubleSelectDao select  =new AssetTroubleSelectDao(asset);
		List<TroubleAssetModel> lst;
		try {
			lst = select.excute();
			mv.addObject("listAssetSearch", lst);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("formSearch", asset);
		mv.setViewName("views/AssetTroubleManagement.jsp");
		return mv;
    }
    @RequestMapping(params = "reportExcel", method = RequestMethod.POST)
	public ModelAndView excel(ModelMap modelMap, HttpServletRequest request) throws SQLException 
	{
    	ModelAndView mv =new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLESCREEN);
		
		TroubleAssetModel asset = new TroubleAssetModel();
		
		asset.getDept().setDept_cd(request.getParameter("department_cd"));
		asset.getDept().setDept_name(request.getParameter("department_name"));
		asset.getAsset().setDepartment_cd(request.getParameter("department_cd"));
		asset.getAsset().setGroup_id(request.getParameter("group_asset_cd"));
		
		asset.getAsset().setDepartment_name(request.getParameter("department_name"));
		asset.getAsset().setGroup_name(request.getParameter("group_asset_na"));
		asset.getAsset().setSeries(request.getParameter("text_series"));
		asset.getAsset().setModel(request.getParameter("text_model"));
		asset.setDateTrouble(request.getParameter("text_start_date_s"));
		asset.setDateTroubleEnd(request.getParameter("text_start_date_e"));
		asset.getAsset().setCompany_CD(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		
	
		System.out.println(request.getParameter("department_name"));
		System.out.println(asset.getAsset().getDepartment_cd());
		AssetTroubleSelectDao select  =new AssetTroubleSelectDao(asset);
		List<TroubleAssetModel> lst;
		lst = select.excute();
		mv.addObject("listAssetSearch", lst);
		if(lst ==null || lst.size()==0)
		{
			modelMap.addAttribute("message","Không tìm thấy dữ liệu yêu cầu<br>Xin thay đổi điều kiện tìm kiếm");
		}
		else
		{
			if(request.getParameter("reportExcel")!=null)
			{
				//System.out.println("vô rồi nè");
				return new ModelAndView(new ExelAssetTroubleListReportView(), "userList", lst);
			}
		}
		mv.addObject("formSearch", asset);
		mv.setViewName("views/AssetTroubleManagement.jsp");
		return mv;
	}
    @RequestMapping(params="reportPDF", method = RequestMethod.POST)
    public ModelAndView reportPDF(HttpServletRequest request)
    {
    	ModelAndView mv =new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLESCREEN);
		
		TroubleAssetModel asset = new TroubleAssetModel();
		asset.getDept().setDept_cd(request.getParameter("department_cd"));
		asset.getAsset().setName(request.getParameter("text_asset_name"));
		asset.getAsset().setGroup_id(request.getParameter("group_asset_cd"));
		asset.getAsset().setSeries(request.getParameter("text_series"));
		asset.getAsset().setModel(request.getParameter("text_model"));
		asset.setDateTrouble(request.getParameter("text_start_date_s"));
		asset.setDateTroubleEnd(request.getParameter("text_start_date_e"));
		
		asset.getAsset().setCompany_CD(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		AssetTroubleSelectDao select  =new AssetTroubleSelectDao(asset);
		List<TroubleAssetModel> lst = null;
		try {
			lst = select.excute();
			mv.addObject("listAssetSearch", lst);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView(new PdfAssetTroubleView(),"lst", lst);
    }
	
}
