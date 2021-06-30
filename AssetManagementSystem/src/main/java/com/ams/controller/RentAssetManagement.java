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
import com.ams.model.RentAsset;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;

@Controller
@RequestMapping("/RentAssetManagement")
public class RentAssetManagement {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request) {
		String TITLE = "MÀN HÌNH QUẢN LÝ TÀI SẢN THUÊ BÊN NGOÀI";
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		RentAsset rentAsset = new RentAsset();
		rentAsset.getCompany_master().setCompany_cd(Common.GetCompanyCDAction(request));
		RentAssetSelectDao rentAssetSelectDao = new RentAssetSelectDao(rentAsset);
		try {
			List<RentAsset> lst = rentAssetSelectDao.excute();
			if (lst.size() == 0) {
				mv.addObject(ParamsConstants.MESSAGE_ERROR, "KHÔNG TÌM THẤY TÀI SẢN THUÊ");
			} else {
				if (lst.size() > 0) {
					mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "TÌM THẤY" + lst.size() + " TÀI SẢN THUÊ");
					for(int i=0;i<lst.size();i++)
					{
						try {
							if(Common.isNotEmpty(lst.get(i).getDate_end_real()))
							{
							lst.get(i).setDate_end_real(Common.ConvertStringToDateStr(lst.get(i).getDate_end_real(), ParamsConstants.DATE_DB_FORMAT, ParamsConstants.DATE_VIEW_TEXT_INPUT));
							}
							} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					mv.addObject("lst", lst);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mv.addObject(ParamsConstants.MESSAGE_ERROR, e.toString());
		}
		mv.setViewName("views/RentAssetManagement.jsp");
		return mv;
	}

	@RequestMapping(params = "search", method = RequestMethod.POST)
	public ModelAndView Search(HttpServletRequest request) {
		String TITLE = "MÀN HÌNH QUẢN LÝ TÀI SẢN THUÊ BÊN NGOÀI";
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		RentAsset rentAsset = new RentAsset();
		rentAsset.getCompany_master().setCompany_cd(Common.GetCompanyCDAction(request));
		rentAsset.getDept_master().setDept_cd(request.getParameter("department_cd"));
		rentAsset.getDept_master().setDept_name(request.getParameter("department_name"));

		rentAsset.getAsset().setName(request.getParameter("text_asset_name"));
		rentAsset.getAsset().setModel(request.getParameter("text_asset_model"));
		rentAsset.getAsset().setSeries(request.getParameter("texxt_assset_series"));
		rentAsset.getAsset().setAccountant_CD(request.getParameter("text_accountant"));
		
		rentAsset.getComany_client().setCompany_name(request.getParameter("company_name"));

		rentAsset.setDate_start(request.getParameter("text_start_date"));
		rentAsset.setDate_start_end(request.getParameter("text_date_start_end"));
		rentAsset.setDate_end_schedual(request.getParameter("text_end_schedual_date_start"));
		rentAsset.setDate_end_schedual_end(request.getParameter("text_end_schedual_date_end"));
		rentAsset.setDate_end_real(request.getParameter("text_end_date_real_start"));
		rentAsset.setDate_end_real_end(request.getParameter("text_end_date_real_end"));
		
		RentAssetSelectDao rentAssetSelectDao = new RentAssetSelectDao(rentAsset);
		try {
			List<RentAsset> lst = rentAssetSelectDao.excute();
			if (lst.size() == 0) {
				mv.addObject(ParamsConstants.MESSAGE_ERROR, "KHÔNG TÌM THẤY TÀI SẢN THUÊ");
			} else {
				if (lst.size() > 0) {
					mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "TÌM THẤY" + lst.size() + " TÀI SẢN THUÊ");
					mv.addObject("lst", lst);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mv.addObject(ParamsConstants.MESSAGE_ERROR, e.toString());
		}
		mv.addObject("rentAsset", rentAsset);
		mv.setViewName("views/RentAssetManagement.jsp");
		return mv;
	}

	@RequestMapping(params = "create", method = RequestMethod.POST)
	public String Create() {
		return "redirect:/RentAssetRegister";
	}

	@RequestMapping(params = "back", method = RequestMethod.POST)
	public String back() {
		return "redirect:/FeatureSystem";
	}

}
