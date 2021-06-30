package com.ams.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.util.ParamsConstants;


@Controller
@RequestMapping("/CompanySettingManagement")
public class CompanySettingManagementController {
	String TITLE = "MÀN HÌNH QUẢN TRỊ DOANH NGHIỆP";
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init()
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		mv.setViewName("views/CompanySettingManagement.jsp");
		return mv;
	}

}
