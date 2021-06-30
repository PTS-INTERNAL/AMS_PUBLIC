package com.ams.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ams.dao.AssetGeneralSelectDao;
import com.ams.dao.UpdateAssetHistorySelectDao;
import com.ams.model.AssetGeneralFormSearch;
import com.ams.model.AssetGeneralModel;
import com.ams.model.UpdateAssetHistoryModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;



@Controller
@RequestMapping("/AssetGeneralView")
public class AssetGeneralViewController {
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView init(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, "MÀN HÌNH XEM THÔNG TIN TÀI SẢN");
		String RFID_CD = request.getParameter("rfid_code");
		if(Common.isEmpty(RFID_CD))
		{
			RFID_CD = (String) request.getSession().getAttribute("RFID_CD");
		}
		if(Common.isEmpty(RFID_CD))
		{
			RFID_CD = request.getParameter("asset_rfid");
		}
		if(RFID_CD != null && RFID_CD.length() >0)
		{
			AssetGeneralFormSearch asset = new AssetGeneralFormSearch();
			asset.setRFID(RFID_CD.trim());
			asset.setCompany_CD((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
			AssetGeneralSelectDao assetGeneralSelectDao = new AssetGeneralSelectDao(asset);
			try {
				List<AssetGeneralModel> lst = assetGeneralSelectDao.excute();
				if(lst.size() == 0)
				{
					mv.addObject(ParamsConstants.MESSAGE_ERROR,"Không tìm thấy tài sản khớp với mã");
				}
				else
				{
					if(lst.size()>1)
					{
						mv.addObject(ParamsConstants.MESSAGE_ERROR,"Tìm thấy 2 tài sản tương tự<br>Hãy kiểm tra lại");
					}
					else
					{
						AssetGeneralModel agm = lst.get(0);
						String DateStart = agm.getDateStart();
						if(!"null".equals(DateStart) && DateStart != null && DateStart.trim().length()>0)
						{
							try {
								String dateEndConvert = Common.ConvertStringToDateStr(DateStart, "dd/mm/yyyy","yyyy-mm-dd");
								lst.get(0).setDateStart(dateEndConvert);
								} catch (ParseException e1) {
									e1.printStackTrace();
								}
						}
						String DateEnd = agm.getDateEnd();
						if(!"null".equals(DateEnd) && DateEnd != null && DateEnd.trim().length()>0)
						{
							try {
								String dateEndConvert = Common.ConvertStringToDateStr(DateEnd, "dd/mm/yyyy","yyyy-mm-dd");
								lst.get(0).setDateEnd(dateEndConvert);
								} catch (ParseException e1) {
									e1.printStackTrace();
								}
						}
						mv.addObject("asset",lst.get(0));
						UpdateAssetHistoryModel assetHis = new UpdateAssetHistoryModel();
						assetHis.getAsset().setId(lst.get(0).getId());
						assetHis.getAsset().setRFID(lst.get(0).getRFID());
						UpdateAssetHistorySelectDao  hisSelect = new UpdateAssetHistorySelectDao(assetHis);
						List<UpdateAssetHistoryModel> lstHis = hisSelect.excute();
						if (lstHis.size()>0) {
							
							for(int i =0;i<lstHis.size();i++)
							{
								try {
									String dateEndConvert = Common.ConvertStringToDateStr(lstHis.get(i).getDate(), "yyyymmdd","dd/mm/yyyy");
									lstHis.get(i).setDate(dateEndConvert);
									} catch (ParseException e1) {
										e1.printStackTrace();
									}
							}
							mv.addObject("lstHis",lstHis);

						}
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				mv.addObject(ParamsConstants.MESSAGE_ERROR,"Lỗi trong lúc tìm tài sản");
				e.printStackTrace();
				
			}
		}
		else
		{
			mv.addObject(ParamsConstants.MESSAGE_ERROR,"Không truyền mã quản lý RFID cho màn hình");
		}
		mv.setViewName("views/AssetGeneralView.jsp");
		
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView initGET(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, "MÀN HÌNH XEM THÔNG TIN TÀI SẢN");
		String RFID_CD = Common.getSessionValue(request, "RFID_CD");
		if(Common.isEmpty(RFID_CD))
		{
			RFID_CD = (String) request.getSession().getAttribute("RFID_CD");
		}
		if(RFID_CD != null && RFID_CD.length() >0)
		{
			Common.removeSessionValue(request, "RFID_CD");
			AssetGeneralFormSearch asset = new AssetGeneralFormSearch();
			asset.setRFID(RFID_CD.trim());
			asset.setCompany_CD((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
			AssetGeneralSelectDao assetGeneralSelectDao = new AssetGeneralSelectDao(asset);
			try {
				List<AssetGeneralModel> lst = assetGeneralSelectDao.excute();
				if(lst.size() == 0)
				{
					mv.addObject(ParamsConstants.MESSAGE_ERROR,"Không tìm thấy tài sản khớp với mã");
				}
				else
				{
					if(lst.size()>1)
					{
						mv.addObject(ParamsConstants.MESSAGE_ERROR,"Tìm thấy 2 tài sản tương tự<br>Hãy kiểm tra lại");
					}
					else
					{
						AssetGeneralModel agm = lst.get(0);
						String DateStart = agm.getDateStart();
						if(!"null".equals(DateStart) && DateStart != null && DateStart.trim().length()>0)
						{
							try {
								String dateEndConvert = Common.ConvertStringToDateStr(DateStart,  "dd/mm/yyyy","yyyy-mm-dd");
								lst.get(0).setDateStart(dateEndConvert);
								} catch (ParseException e1) {
									e1.printStackTrace();
								}
						}
						String DateEnd = agm.getDateEnd();
						if(!"null".equals(DateEnd) && DateEnd != null && DateEnd.trim().length()>0)
						{
							try {
								String dateEndConvert = Common.ConvertStringToDateStr(DateEnd, "dd/mm/yyyy","yyyy-mm-dd");
								lst.get(0).setDateEnd(dateEndConvert);
								} catch (ParseException e1) {
									e1.printStackTrace();
								}
						}
						mv.addObject("asset",lst.get(0));
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				mv.addObject(ParamsConstants.MESSAGE_ERROR,"Lỗi trong lúc tìm tài sản");
				e.printStackTrace();
				
			}
		}
		else
		{
			mv.addObject(ParamsConstants.MESSAGE_ERROR,"Không truyền mã quản lý RFID cho màn hình");
		}
		mv.setViewName("views/AssetGeneralView.jsp");
		
		return mv;
	}
	
	@RequestMapping(params="update", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, Model model) 
	{
		ModelAndView mv = new ModelAndView();
		String RFID_CD = request.getParameter("rfid_code");
		request.getSession().setAttribute("RFID_CD", RFID_CD);
		mv.setViewName("redirect:/AssetGeneralUpdate");
		return mv;
	}
	
	@RequestMapping(params="delete", method = RequestMethod.POST)
	public String delete(HttpServletRequest request, RedirectAttributes redirect) 
	{
		String RFID_CD = request.getParameter("rfid_code");
		request.getSession().setAttribute("RFID_CD", RFID_CD);
		return "redirect:/AssetGeneralDelete";
	}
	
	@RequestMapping(params="back", method = RequestMethod.POST)
	public String back(HttpServletRequest request) 
	{
		return "redirect:/AssetManagementGeneral";
	}

}
