package com.ams.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.ams.dao.AssetGeneralSelectDao;
import com.ams.dao.AssetMaintainInsertDao;
import com.ams.dao.AssetMaintainUpdateDao;
import com.ams.dao.AssetMaintanceSelectDao;
import com.ams.dao.AssetTroubleInsertDao;
import com.ams.dao.AssetTroubleSelectDao;
import com.ams.dao.AssetTroubleUpdateDao;
import com.ams.helper.ExcelAssetGeneralListReportView;
import com.ams.helper.PdfUserListReportView;
import com.ams.model.AssetGeneralFormSearch;
import com.ams.model.AssetGeneralModel;
import com.ams.model.AssetMaintanceModel;
import com.ams.model.TroubleAssetModel;
import com.ams.util.AuthenticationLogin;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;
import com.ams.util.UrlCommon;

@Controller
@RequestMapping("/AssetmaintainEdit")
public class AssetMaintainceUpdateController {
	String TITLESCREEN = "MÀN HÌNH CHỈNH SỬA ";
	

	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView add(ModelMap modelMap, HttpServletRequest request) throws SQLException 
	{
		String ID=request.getParameter("MFID");
		ModelAndView mv = new ModelAndView();
		
		
		modelMap.addAttribute("TittleScreen","MÀN HÌNH QUẢN LÝ BẢO TRÌ TÀI SẢN");
		List<AssetMaintanceModel> lstMain =null;
		AssetMaintanceModel asset = new AssetMaintanceModel(); 
		asset.setYear("2021");
		asset.setQuy("2");
		asset.getAsset().setCompany_CD((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
		asset.getAsset().setRFID(ID);
		AssetMaintanceSelectDao select = new AssetMaintanceSelectDao(asset);
		lstMain=select.excute();
		mv.addObject("AssetSearch",lstMain.get(0));
		System.out.println("###################"+lstMain.get(0).getContent());
		System.out.println("###################"+lstMain.get(0).getDay());
		System.out.println("###################"+lstMain.get(0).getUserInsert());
		
		mv.setViewName("views/AssetMaintainEdit.jsp");
		return mv;
		
	}
	
	@RequestMapping(params="save", method = RequestMethod.POST)
	public ModelAndView save(ModelMap modelMap,HttpServletRequest request, RedirectAttributes att) throws SQLException
	{
		
		HttpSession session =request.getSession();
		String page=session.getAttribute("Upage").toString();
		
		System.out.println("#######"+session.getAttribute("Umodel").toString());
		System.out.println("#######"+session.getAttribute("Udeptname").toString());
		
		
		ModelAndView mv = new ModelAndView();
		AuthenticationLogin auth = new AuthenticationLogin(request);
		if(auth.isLogin())
		{
			modelMap.addAttribute("TittleScreen","MÀN HÌNH QUẢN LÝ BẢO TRÌ TÀI SẢN");
			List<AssetMaintanceModel> lstMain =null;
			AssetMaintanceModel asset = new AssetMaintanceModel(); 
			asset.setYear("2021");
			asset.setQuy("2");
			asset.getAsset().setCompany_CD((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
			asset.getAsset().setRFID(request.getParameter("asset_rfit"));
			AssetMaintanceSelectDao select = new AssetMaintanceSelectDao(asset);
			lstMain=select.excute();
			if(lstMain.get(0).getDay()==null||lstMain.get(0).getDay()=="")
			{
				asset.setAsset(lstMain.get(0).getAsset());
				asset.setDay(request.getParameter("asset_maintain_DT"));
				asset.setUserInsert(request.getParameter("asset_maintain_user"));
				asset.SetContent(request.getParameter("asset_maintain"));
				
				AssetMaintainInsertDao insert =new AssetMaintainInsertDao(asset);
				insert.excute();
			}
			else
			{
				
				  asset.setAsset(lstMain.get(0).getAsset());
				  asset.setDay(request.getParameter("asset_maintain_DT"));
				  asset.setUserInsert(request.getParameter("asset_maintain_user"));
				  asset.SetContent(request.getParameter("asset_maintain"));
				  
				  AssetMaintainUpdateDao update =new AssetMaintainUpdateDao(asset);
				  update.UpdateMainTainance();
				 
			}
			//###############################
			List<AssetMaintanceModel> lstSearch =null;
			AssetMaintanceModel mtn = new AssetMaintanceModel(); 
			mtn.setYear("2021");
			mtn.setQuy("2");
			
			mtn.getAsset().setCompany_CD((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
			mtn.getAsset().setModel(session.getAttribute("Umodel").toString());
			mtn.getAsset().setSeries(session.getAttribute("Useries").toString());
			mtn.getAsset().setDepartment_cd(session.getAttribute("Udept").toString());
			mtn.getAsset().setGroup_id(session.getAttribute("Ugroup").toString());
			mtn.getAsset().setRFID(session.getAttribute("Ufid").toString());
			AssetMaintanceSelectDao selectSearch = new AssetMaintanceSelectDao(mtn);
			
			lstSearch = selectSearch.excute();
			request.setAttribute("numberofrow", lstSearch.size());
			if(page==null||page.trim()=="") 
				page="1";
			
			List<AssetMaintanceModel> lstSearchdetail =null;
			AssetMaintanceModel mtndetail = mtn;
			mtndetail.setRowPage(page);		
			mtndetail.getAsset().setCompany_CD((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
			AssetMaintanceSelectDao selectDetail = new AssetMaintanceSelectDao(mtndetail);
			lstSearchdetail = selectDetail.excute();
			
			
			
			request.setAttribute("pagenum", Integer.parseInt(page));
			
			if(lstSearch.size()>0)
			{
				mv.addObject("lst",lstSearchdetail);
			}
			
			
			AssetGeneralFormSearch form = new AssetGeneralFormSearch();
			form.setModel(session.getAttribute("Umodel").toString());
			form.setSeries(session.getAttribute("Useries").toString());
			form.setGroup_id(session.getAttribute("Ugroup").toString());
			form.setGroup_name(session.getAttribute("Ugroupname").toString());
			form.setDepartment_cd(session.getAttribute("Udept").toString());
			form.setDepartment_name(session.getAttribute("Udeptname").toString());
			form.setCompany_CD((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
			
			mv.addObject("formSearch",form);
			
			/* mv.setViewName("redirect:/AssetMaintainceManagement"); */
			mv.setViewName("views/AssetMaintainceManagement.jsp");
			
			return mv;
		}
		else
		{
			mv.setViewName(UrlCommon.LoginUrl);
			return mv;
		}
		

		
		
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
