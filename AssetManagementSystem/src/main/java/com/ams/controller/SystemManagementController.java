package com.ams.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.util.ParamsConstants;

@Controller
@RequestMapping("SystemManagement")
public class SystemManagementController {
	String TITLE = "MÀN HÌNH QUẢN LÝ HỆ THỐNG";
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request)
	{
		
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		mv.setViewName("views/SystemManagement.jsp");
		return mv;
	}

}
