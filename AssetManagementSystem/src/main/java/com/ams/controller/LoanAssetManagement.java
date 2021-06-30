package com.ams.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.BorrowAssetSelectDao;
import com.ams.dao.LoanAssetSelectDao;
import com.ams.model.BorrowAssetModel;
import com.ams.model.LoanAssetModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;



@Controller
@RequestMapping("/LoanAssetManagement")
public class LoanAssetManagement {
	String TITLE = "MÀN HÌNH QUẢN LÝ MƯỢN TÀI SẢN ";
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request)
	{
		ModelAndView mv =new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		LoanAssetModel br  = new LoanAssetModel();
		br.getCmpn_master().setCompany_cd((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD)); 
		LoanAssetSelectDao BASD = new LoanAssetSelectDao(br);
		
		try {
			List<LoanAssetModel> lst = BASD.excute();
			if(lst.size()==0)
			{
				mv.addObject(ParamsConstants.MESSAGE_ERROR, "KHÔNG CÓ THẤY TÀI SẢN MƯỢN NÀO ĐƯỢC TÌM THẤY");
			}
			else
			{
				mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "TÌM THẤY "+lst.size()+ " TÀI SẢN MƯỢN ĐƯỢC TÌM THẤY");
				mv.addObject("lst", lst);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			mv.addObject(ParamsConstants.MESSAGE_ERROR, "LỖI TÌM DỮ LIỆU : " + e.toString());
		}
		mv.setViewName("views/LoanAssetManagement.jsp");
		return mv;
	}
	@RequestMapping(params = "search",method = RequestMethod.POST)
	public ModelAndView Search(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		LoanAssetModel loan = new LoanAssetModel();
		loan.getAsset().setName(request.getParameter("text_asset_name"));
		loan.getAsset().setModel(request.getParameter("text_model"));
		loan.getAsset().setSeries(request.getParameter("text_series"));
		loan.getAsset().setRFID(request.getParameter("text_rfid"));
		loan.setDate_start_end(request.getParameter("text_end_date"));
		loan.setDate_start(request.getParameter("text_start_date"));
		loan.getDept_master().setDept_cd(request.getParameter("department_cd_master"));
		loan.getDept_master().setDept_name(request.getParameter("department_name_master"));
		loan.getDept_client().setDept_cd(request.getParameter("department_cd_client"));
		loan.getDept_client().setDept_name(request.getParameter("department_name_client"));
		loan.getCmpn_master().setCompany_cd(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		LoanAssetSelectDao LASD = new LoanAssetSelectDao(loan);
		
		try {
			List<LoanAssetModel> lst = LASD.excute();
			if(lst.size()==0)
			{
				mv.addObject(ParamsConstants.MESSAGE_ERROR, "KHÔNG CÓ THẤY TÀI SẢN MƯỢN NÀO ĐƯỢC TÌM THẤY");
			}
			else
			{
				mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "TÌM THẤY "+lst.size()+ " TÀI SẢN MƯỢN ĐƯỢC TÌM THẤY");
				mv.addObject("lst", lst);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			mv.addObject(ParamsConstants.MESSAGE_ERROR, "LỖI TÌM DỮ LIỆU : " + e.toString());
		}
		mv.addObject("loan", loan);
		mv.setViewName("views/LoanAssetManagement.jsp");
		return mv;
	}
	@RequestMapping(params = "create",method = RequestMethod.POST)
	public String Create()
	{
		return "redirect:/LoanAssetRegister";
	}
	
	@RequestMapping(params = "back",method = RequestMethod.POST)
	public String back()
	{
		return "redirect:/FeatureSystem";
	}

}
