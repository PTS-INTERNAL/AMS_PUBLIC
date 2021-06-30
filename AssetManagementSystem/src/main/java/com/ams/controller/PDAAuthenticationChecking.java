package com.ams.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.InventorySessionSelectDao;
import com.ams.model.InventorySessionModel;
import com.ams.util.Common;
import com.ams.util.SessionConstants;

@Controller
@RequestMapping("/PDAInventoryChecking")
public class PDAAuthenticationChecking {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		InventorySessionSelectDao inventorySessionSelectDao = new InventorySessionSelectDao();

		List<InventorySessionModel> lstInventory = new ArrayList<InventorySessionModel>();
		try {
			lstInventory = inventorySessionSelectDao.excute();
			if (lstInventory.size() > 0) {
				mv.addObject("lstInventory", lstInventory);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mv.addObject("company", Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_NAME));
		mv.setViewName("views/PDAInventory.jsp");
		return mv;
	}

}
