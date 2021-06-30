package com.ams.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.ams.dao.AssetGeneralSelectDao;
import com.ams.dao.AssetTroubleInsertDao;
import com.ams.dao.AssetTroubleSelectDao;
import com.ams.dao.AssetTroubleUpdateDao;
import com.ams.model.AssetGeneralModel;
import com.ams.model.TroubleAssetModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;
import com.ams.util.UrlCommon;

@Controller
@RequestMapping("AssetTroubleEdit")
public class AssetTroubleEditController {
	String TITLESCREEN = "MÀN HÌNH CHỈNH SỬA SỰ CỐ";
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request)
	{
		
		String ID=request.getParameter("TRID");
		System.out.println(ID);
		
		ModelAndView mv =new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLESCREEN);
		TroubleAssetModel asset = new TroubleAssetModel();
		asset.getAsset().setCompany_CD(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		AssetTroubleSelectDao select  =new AssetTroubleSelectDao(asset);
		TroubleAssetModel lst = new TroubleAssetModel();
		try {
			lst = select.FindTroubleByID(ID);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(lst.getTimeTrouble());
		mv.addObject("AssetSearch", lst);
		mv.setViewName("views/AssetTroubleEdit.jsp");
		return mv;
	}
	
	@RequestMapping(params="save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, RedirectAttributes att)
	{
		
		String ID=request.getParameter("asset_troubleID");
		String newtrouble=request.getParameter("asset_trouble");
		String newtroubleStatus=request.getParameter("trouble_status");
		ModelAndView mv =new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLESCREEN);
		
		TroubleAssetModel asset = new TroubleAssetModel();
		asset.getAsset().setCompany_CD(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		AssetTroubleSelectDao select  =new AssetTroubleSelectDao(asset);
		AssetTroubleUpdateDao update=new AssetTroubleUpdateDao();
		
		TroubleAssetModel lst = new TroubleAssetModel();
		try {
			lst = select.FindTroubleByID(ID);
			update.UpdateTroubleByID(lst, newtrouble,newtroubleStatus);
			lst.setTrouble(newtrouble);
			lst.setTroubleStatus(newtroubleStatus);
			System.out.println("Cập nhật thành công");
			mv.setViewName("redirect:/AssetTroubleManagement");
		} catch (SQLException e) {
			System.out.println("Cập nhật không thành công");	
			mv.setViewName("views/AssetTroubleRegister.jsp");
		}
		return mv;
	}
	
	@RequestMapping(params="back", method = RequestMethod.POST)
	public ModelAndView back(HttpServletRequest request)
	{
		ModelAndView mv =new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLESCREEN);
		mv.setViewName("redirect:/AssetTroubleManagement");
		return mv;
	}


}
