package com.ams.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ams.dao.AssetGeneralDeleteUpdateDao;
import com.ams.dao.AssetGeneralSelectDao;
import com.ams.dao.HistoryAssetDeleteInsertDao;
import com.ams.model.AssetGeneralFormSearch;
import com.ams.model.AssetGeneralModel;
import com.ams.model.HistoryAssetDeleteModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;


@Controller
@RequestMapping("/AssetGeneralDelete")
public class AssetGeneralDelete {
	
	String TITLESCREEN = "MÀN HÌNH XÓA THÔNG TIN TÀI SẢN";
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request, RedirectAttributes redirect)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLESCREEN);
		//LẤY RFID
		String RFID_CD = Common.getSessionValue(request, "RFID_CD");
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
						if(lst.size()==1)
						{

							if(lst.get(0).getDateStart()!=null && lst.get(0).getDateStart().trim().length()>0)
							{
								String date = lst.get(0).getDateStart();
								String dateNew;
								try {
									dateNew = Common.ConvertStringToDateStr(date, "dd/mm/yyyy", "yyyy-mm-dd");
									lst.get(0).setDateStart(dateNew);
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
							if(lst.get(0).getDateEnd()!=null && lst.get(0).getDateEnd().trim().length()>0)
							{
								String date = lst.get(0).getDateEnd();
								String dateNew;
								try {
									dateNew = Common.ConvertStringToDateStr(date, "dd/mm/yyyy", "yyyy-mm-dd");
									lst.get(0).setDateEnd(dateNew);
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
							mv.addObject("asset",lst.get(0));
							
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
		
		mv.setViewName("views/AssetGeneralDelete.jsp");
		
		return mv;
	}
	@RequestMapping(params="delete", method=RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response, RedirectAttributes rect) throws UnsupportedEncodingException
	{
		ModelAndView mv = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
		//LẤY RFID
		String RFID_CD = request.getParameter("rfid_code");
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
						if(lst.size()==1)
						{
							HistoryAssetDeleteModel historyDel = new HistoryAssetDeleteModel();
							historyDel.setDelete_reason(request.getParameter("delete_reason"));
							if(historyDel.getDelete_reason().trim().length()>0)
							{
								historyDel.setAsset(lst.get(0));
								historyDel.setDelete_cd(Common.getDateCurrent("YYYYMMddHHmmSS"));
								historyDel.setDelete_insert_dt(Common.getDateCurrent("YYYYHHdd"));
								historyDel.setDelete_user_insert((String) request.getSession().getAttribute(SessionConstants.SESSION_USER_ID));
								
								lst.get(0).setUser_update_cd(Common.getSessionValue(request, SessionConstants.SESSION_USER_ID));
								lst.get(0).setUpdate_dt(Common.getDateCurrent("YYYYMMdd"));
								lst.get(0).setDelete_Fg("1");

								AssetGeneralDeleteUpdateDao  updateDelete = new AssetGeneralDeleteUpdateDao(lst.get(0));
								updateDelete.excuteUpdateDeleteData();
								mv.addObject("notification", "Xóa tài sản thành công");
								
								HistoryAssetDeleteInsertDao hisDel =new HistoryAssetDeleteInsertDao(historyDel);
								hisDel.excute();
							}
							else
							{
								mv.addObject("asset",lst.get(0));
								mv.addObject(ParamsConstants.MESSAGE_ERROR,"LÝ DO XÓA TÀI SẢN LÀ ĐIỀU RẤT QUAN TRỌNG");
								mv.setViewName("views/AssetGeneralDelete.jsp");
								return mv;
							}
						
						}
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				rect.addFlashAttribute(ParamsConstants.MESSAGE_ERROR,e.toString());
				
				e.printStackTrace();
				mv.setViewName("redirect:/Error");
				return mv;
				
			}
		}
		else
		{
			mv.addObject(ParamsConstants.MESSAGE_ERROR,"Không truyền mã quản lý RFID cho màn hình");
		}
		mv.setViewName("views/AssetGeneralDelete.jsp");
		return mv;
	}
	
	@RequestMapping(params="back", method=RequestMethod.POST)
	public String back(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException
	{
		String RFID_CD = request.getParameter("rfid_code");
		request.getSession().setAttribute("RFID_CD", RFID_CD);
		return "redirect:/AssetGeneralView" ;
	}

}
