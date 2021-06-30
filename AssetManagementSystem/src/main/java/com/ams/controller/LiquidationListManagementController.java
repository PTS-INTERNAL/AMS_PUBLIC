
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

import com.ams.dao.AssetGeneralIncludeSelectDao;
import com.ams.dao.ListLiquidationDeleteDao;
import com.ams.dao.ListLiquidationSelectDao;
import com.ams.helper.ExelAssetMaintainanceListReportView;
import com.ams.helper.ExelAssetTroubleListReportView;
import com.ams.helper.PdfAssetMotherAndChildView;
import com.ams.helper.PdfAssetTroubleView;
import com.ams.helper.PdfUserListReportView;
import com.ams.model.AssetGeneralFormSearch;
import com.ams.model.AssetGeneralModel;
import com.ams.model.LiquidationListModel;

import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;

@Controller
@RequestMapping("AssetLiquidationManagement")
public class LiquidationListManagementController {
     String TITLESCREEN = "MÀN HÌNH QUẢN LÝ DANH SÁCH THANH LÝ";
    
    @RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request)
	{
		ModelAndView mv =new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLESCREEN);
		
		
		LiquidationListModel asset = new LiquidationListModel();
		asset.setCompary_cd(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		
		asset.getList_id();
		asset.getListName();
		asset.getUserCreate();
		asset.getDateCreate();
		
		ListLiquidationSelectDao select  =new ListLiquidationSelectDao(asset);
		List<LiquidationListModel> Listlst;
		
		try {
			Listlst = select.excute();
			mv.addObject("ListLstSearch", Listlst);
			System.out.println("Thành công   "+Listlst.get(0).getListName()+ Listlst.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("views/ListLiquidationManagement.jsp");
		return mv;
	}
    
    @RequestMapping(params="register", method = RequestMethod.POST)
    public ModelAndView register(HttpServletRequest request)
    {
    	ModelAndView mv =new ModelAndView();
		mv.setViewName("redirect:/LiquidationListRegister");
		return mv;
    }
    
    @RequestMapping(params = "search", method = RequestMethod.POST)
	public ModelAndView search(ModelMap modelMap, HttpServletRequest request) throws SQLException 
	{
		
    	ModelAndView mv =new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLESCREEN);
		
		
		LiquidationListModel asset = new LiquidationListModel();
		asset.setCompary_cd(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		asset.setList_id(request.getParameter("text_list_id"));
		asset.setDateCreate(request.getParameter("text_date_create"));
		asset.setListName(request.getParameter("text_list_name"));
		asset.setUserCreate(request.getParameter("text_user_create"));
		
		mv.addObject("ListSearchCondition",asset);
		asset.getListName();
		asset.getList_id();
		asset.getUserCreate();
		asset.getDateCreate();
		
		ListLiquidationSelectDao select  =new ListLiquidationSelectDao(asset);
		List<LiquidationListModel> Listlst;
		
		try {
			Listlst = select.excute();
			mv.addObject("ListLstSearch", Listlst);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("views/ListLiquidationManagement.jsp");
		return mv;
	}
    @RequestMapping(params = "delete", method = RequestMethod.POST)
  	public ModelAndView delete(ModelMap modelMap, HttpServletRequest request) throws SQLException 
  	{
  		
      	ModelAndView mv =new ModelAndView();
  		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLESCREEN);
  		
  		String lst_cd = request.getParameter("List_id");
  		ListLiquidationDeleteDao delete =new ListLiquidationDeleteDao();
  		delete.excuteDeleteListLiquidation(lst_cd);
  		
 
  		LiquidationListModel asset = new LiquidationListModel();
  		asset.setCompary_cd(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
  		asset.setList_id(request.getParameter("text_list_id"));
  		asset.setDateCreate(request.getParameter("text_date_create"));
  		asset.setListName(request.getParameter("text_list_name"));
  		asset.setUserCreate(request.getParameter("text_user_create"));
  		
  		mv.addObject("ListSearchCondition",asset);
  		asset.getListName();
  		asset.getList_id();
  		asset.getUserCreate();
  		asset.getDateCreate();
  		
  		ListLiquidationSelectDao select  =new ListLiquidationSelectDao(asset);
  		List<LiquidationListModel> Listlst;
  		
  		try {
  			Listlst = select.excute();
  			mv.addObject("ListLstSearch", Listlst);
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  		mv.setViewName("views/ListLiquidationManagement.jsp");
  		return mv;
  	}
    
    @RequestMapping(params="back", method = RequestMethod.POST)
    public ModelAndView back(HttpServletRequest request)
    {
    	ModelAndView mv =new ModelAndView();
		mv.setViewName("redirect:/FeatureSystem");
		return mv;
    }
    

   
}
