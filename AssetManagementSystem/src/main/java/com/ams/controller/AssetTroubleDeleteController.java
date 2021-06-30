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
import com.ams.dao.AssetTroubleDeleteDao;
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
@RequestMapping("AssetTroubleDelete")
public class AssetTroubleDeleteController {
	  String TITLESCREEN = "MÀN HÌNH QUẢN LÝ SỰ CỐ";
	    
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request)
	{
		
		String error = "";
		
		String ID=request.getParameter("TRID");
		System.out.println("ID:"+ID);
		
		ModelAndView mv =new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLESCREEN);
		TroubleAssetModel asset = new TroubleAssetModel();
		asset.setTroubleID(ID);
		asset.getAsset().setCompany_CD(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		AssetTroubleSelectDao select  =new AssetTroubleSelectDao(asset);
		
		AssetTroubleDeleteDao delete=new AssetTroubleDeleteDao(asset);
		
		TroubleAssetModel lst = new TroubleAssetModel();
		
		try {
			lst = select.FindTroubleByID(ID);
			if(lst.getTroubleStatus()=="nostatus"||lst.getTroubleStatus()=="")
				delete.excuteDeleteTrouble(ID);
			else
			{
				
				error += "KHÔNG ĐƯỢC XÓA MỤC NÀYC<br>"; 
				mv.addObject(ParamsConstants.MESSAGE_ERROR, error);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.setViewName("redirect:/AssetTroubleManagement");
		return mv;
		
	}
	
	
	
	


}
