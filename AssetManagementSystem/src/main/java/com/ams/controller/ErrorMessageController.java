package com.ams.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;


@Controller
public class ErrorMessageController {
	
	@RequestMapping("/error")
	public ModelAndView init(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		request.getSession().removeAttribute(SessionConstants.SESSION_USER_NAME);
		request.getSession().removeAttribute(SessionConstants.SESSION_USER_ID);
		request.getSession().removeAttribute(SessionConstants.SESSION_USER_CMPN_CD);
		request.getSession().removeAttribute(SessionConstants.SYSTEM_NAME);
		request.getSession().removeAttribute(SessionConstants.SUB_SYSTEM_NAME);
		request.getSession().removeAttribute(SessionConstants.SUB_SYSTEM_CD);
		String error = (String) request.getParameter("errorMessage");
		if(error != null && error.trim().length()>0)
		{
			mv.addObject(ParamsConstants.MESSAGE_ERROR, error);
		}
		mv.setViewName("views/error.jsp");
		return mv;
	}
}
