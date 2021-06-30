package com.ams.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.RentAssetSelectDao;
import com.ams.dao.RentAssetUpdatePaidDao;
import com.ams.model.RentAsset;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;

@Controller
@RequestMapping("/RentAssetView")
public class RentAssetView {
	
	@RequestMapping(params="view_asset", method = RequestMethod.POST)
	public ModelAndView init(HttpServletRequest request)
	{
		ModelAndView mv  = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, "MÀN HÌNH XEM THÔNG TIN TÀI SẢN THUÊ");
		String id_rent = request.getParameter("rent_id");
		
		RentAsset asset  =new RentAsset();
		asset.setRent_cd(id_rent);
		asset.getCompany_master().setCompany_cd(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		RentAssetSelectDao rentSelect = new RentAssetSelectDao(asset);
		try {
			List<RentAsset> lstRent = rentSelect.excute();
			
			if(lstRent.size()==1)
			{
				try {
					lstRent.get(0).setDate_start(Common.ConvertStringToDateStr(lstRent.get(0).getDate_start(),ParamsConstants.DATE_DB_FORMAT, ParamsConstants.DATE_VIEW_TEXT_INPUT));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					lstRent.get(0).setDate_end_schedual(Common.ConvertStringToDateStr(lstRent.get(0).getDate_end_schedual(),ParamsConstants.DATE_DB_FORMAT, ParamsConstants.DATE_VIEW_TEXT_INPUT));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mv.addObject("asset", lstRent.get(0));
			}
			else
			{
				mv.addObject(ParamsConstants.MESSAGE_ERROR, "KHÔNG TÌM THẤY TÀI SẢN");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("views/RentAssetView.jsp");
		return mv;
	}
	
	@RequestMapping(params="paid", method = RequestMethod.POST)
	public ModelAndView paid(HttpServletRequest request)
	{
		ModelAndView mv  = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, "MÀN HÌNH XEM THÔNG TIN TÀI SẢN THUÊ");
		String id_rent = request.getParameter("rent_id");
		
		RentAsset asset  =new RentAsset();
		asset.setRent_cd(id_rent);
		asset.getCompany_master().setCompany_cd(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		RentAssetSelectDao rentSelect = new RentAssetSelectDao(asset);
		try {
			List<RentAsset> lstRent = rentSelect.excute();
			
			if(lstRent.size()==1)
			{
				lstRent.get(0).setDate_end_real(Common.getDateCurrent(ParamsConstants.CD_FULL_DATE));
				RentAssetUpdatePaidDao updatePadi = new RentAssetUpdatePaidDao(lstRent.get(0));
				updatePadi.excute();
			}
			else
			{
				mv.addObject(ParamsConstants.MESSAGE_ERROR, "KHÔNG TÌM THẤY TÀI SẢN");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("redirect:/RentAssetManagement");
		return mv;
	}
	
	@RequestMapping(params="back", method = RequestMethod.POST)
	public ModelAndView back(HttpServletRequest request)
	{
		ModelAndView mv  = new ModelAndView();
		mv.setViewName("redirect:/RentAssetManagement");
		return mv;
	}

}
