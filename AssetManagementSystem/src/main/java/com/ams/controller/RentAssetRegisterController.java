package com.ams.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.RentAssetInsertDao;
import com.ams.model.RentAsset;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;


@Controller
@RequestMapping("/RentAssetRegister")
public class RentAssetRegisterController {
	
	@RequestMapping(method =  RequestMethod.GET)
	public ModelAndView init()
	{
		String TITLE = "MÀN HÌNH ĐĂNG KÝ TÀI SẢN THUÊ BÊN NGOÀI";
		ModelAndView mv  = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		mv.setViewName("views/RentAssetRegister.jsp");
		return mv;
	}
	@RequestMapping(params = "save",method = RequestMethod.POST)
	public ModelAndView Create(HttpServletRequest request) throws UnsupportedEncodingException
	{
		request.setCharacterEncoding("UTF-8");
		ModelAndView mv = new ModelAndView();
		RentAsset rent = new RentAsset();
		// rent_cd;
		rent.setRent_cd(Common.getDateCurrent("YYYYMMddHHmmSS"));
		// cmpn_cd;
		
		rent.getCompany_master().setCompany_cd(Common.GetCompanyCDAction(request));
		// dept_cd;
		rent.getDept_master().setDept_cd(request.getParameter("department_cd"));
		// asset_name;
		rent.getAsset().setName(request.getParameter("asset_name"));
		// accountant_cd;
		rent.getAsset().setAccountant_CD(request.getParameter("accountan_cd"));
		// accountant_cd;
		rent.getAsset().setModel(request.getParameter("asset_model"));
		// accountant_cd;
		rent.getAsset().setSeries(request.getParameter("asset_series"));
		// bussiness_name;
		rent.getComany_client().setCompany_name(request.getParameter("company_name"));
		// bussiness_address;
		rent.getComany_client().setCompany_address(request.getParameter("company_address"));
		// rent_date;
		rent.setDate_start(request.getParameter("date_rent"));
		// paid_1;
		rent.setDate_end_schedual(request.getParameter("date_paid"));
		
		
		RentAssetInsertDao rentAssetInsertDao = new RentAssetInsertDao(rent);
		try {
			rentAssetInsertDao.excute();
			mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "ĐĂNG KÝ DỮ LIỆU THUÊ TÀI SẢN THÀNH CÔNG");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mv.addObject(ParamsConstants.MESSAGE_ERROR, e.toString());
			mv.setViewName( "redirect:/RentAssetRegister");
			
		}
		mv.setViewName( "redirect:/RentAssetManagement");
		return mv;
	}
	
	@RequestMapping(params = "back",method = RequestMethod.POST)
	public String back()
	{
		return "redirect:/RentAssetManagement";
	}

}
