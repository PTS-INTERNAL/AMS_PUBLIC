package com.ams.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/MenuSystemManagement")
public class MenuSystemManagement {
	
	public ModelAndView init(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		
		
		mv.setViewName("views/MenuSystemManagement.jsp");
		return mv;
	}

}
