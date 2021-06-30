package com.ams.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.BorrowAssetSelectDao;
import com.ams.dao.LoanAssetSelectDao;
import com.ams.model.BorrowAssetModel;
import com.ams.model.LoanAssetModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.RoleCommon;
import com.ams.util.ServicesConstants;

@Controller
@RequestMapping("/LoanAssetView")
public class LoanAssetView {
	
	@RequestMapping(params="view_asset", method = RequestMethod.POST)
	public ModelAndView init(HttpServletRequest request)
	{
		ModelAndView mv  = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, "MÀN HÌNH XEM THÔNG TIN TÀI SẢN MƯỢN");
		String id_borrow = request.getParameter("id_borrow");
		String isApprove = "0", isConfirm="0";
		if(Common.isHavePermission(request, RoleCommon.R_ACCESS, ServicesConstants.BORROW_APPROVE))
		{
			isApprove="1";
		}
		if(Common.isHavePermission(request, RoleCommon.R_ACCESS, ServicesConstants.BORROW_CONFIRM))
		{
			isConfirm="1";
		}
		
		mv.addObject("isApprove", isApprove);
		mv.addObject("isConfirm", isConfirm);
		if(id_borrow != null && id_borrow.length() >0)
		{
			LoanAssetModel asset = new LoanAssetModel();
			asset.setId(id_borrow.trim());
			LoanAssetSelectDao brsd = new LoanAssetSelectDao(asset);
			try {
				List<LoanAssetModel> lst = brsd.excute();
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
			mv.addObject(ParamsConstants.MESSAGE_ERROR,"Không truyền mã quản lý mượn cho màn hình");
		}
		mv.setViewName("views/LoanAssetView.jsp");
		return mv;
	}
	
	@RequestMapping(params = "back")
	public String back()
	{
		return "redirect:/LoanAssetManagement";
	}
	
	@RequestMapping(params="update", method = RequestMethod.POST)
	public String update(HttpServletRequest request) 
	{
		String id = request.getParameter("rfid_code");
		request.getSession().setAttribute("RFID_CD", id);
		return "redirect:/BorrowAssetUpdate";
	}

}
