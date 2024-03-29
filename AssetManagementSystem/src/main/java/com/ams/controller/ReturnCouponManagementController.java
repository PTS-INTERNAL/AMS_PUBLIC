package com.ams.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.AssetGeneralIncludeSelectDao;
import com.ams.dao.BorrowAssetSelectDao;
import com.ams.dao.borrow.BorrowCouponSelectDao;
import com.ams.dao.borrow.BorrowCouponUpdateDeleteDao;
import com.ams.helper.ExcelAssetGeneralListReportView;
import com.ams.helper.ExcelBorrowListReportView;
import com.ams.helper.PdfUserListReportView;
import com.ams.model.AssetGeneralFormSearch;
import com.ams.model.AssetGeneralModel;
import com.ams.model.BorrowAssetModel;
import com.ams.model.ReturnCouponModel;
import com.ams.returnasset.ReturnCouponSelectDao;
import com.ams.returnasset.ReturnCouponUpdateDeleteDao;
import com.ams.model.ReturnCouponModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;
import com.ams.util.SystemControl;

@Controller
@RequestMapping("/ReturnCouponManagement")
public class ReturnCouponManagementController {
	String TITLE = "MÀN HÌNH QUẢN LÝ PHIẾU TRẢ TÀI SẢN SẢN";
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		ReturnCouponModel br  = new ReturnCouponModel();
		br.getCmpn_master().setCompany_cd((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
		
		
		//######################################
		System.out.println("Page_id:");
		System.out.println(request.getParameter("Current_Page"));
		String page=request.getParameter("Current_Page");

		
		ReturnCouponModel br1 = br;
		if(page==null||page.trim()=="") 
			page="1";
		br1.setRowPage(page);	

		//###################
		ReturnCouponSelectDao BASD1 = new ReturnCouponSelectDao(br1);
		
		ReturnCouponSelectDao BASD = new ReturnCouponSelectDao(br);
		
		try {
			List<ReturnCouponModel> lst = BASD.excute();
			mv.addObject("numberofrow", lst.size());
			
			List<ReturnCouponModel> lst1 = BASD1.excute();
			if(lst.size()==0)
			{
				mv.addObject(ParamsConstants.MESSAGE_ERROR, "KHÔNG CÓ THẤY PHIẾU TRẢ NÀO ĐƯỢC TÌM THẤY");
			}
			else
			{
				mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "TÌM THẤY "+lst.size()+ " PHIẾU TRẢ ĐƯỢC TÌM THẤY");
				for(int i =0;i<lst.size();i++)
				{
					if(Common.isNotEmpty(lst.get(i).getDate_start()))
					{
						try {
						String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_start(), "yyyy-mm-dd","dd/mm/yyyy");
						lst.get(i).setDate_start(dateEndConvert);
						} catch (ParseException e1) {
							mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
						}
					}
					if(Common.isNotEmpty(lst.get(i).getDate_end_schedule()))
					{
						try {
						String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_end_schedule(), "yyyy-mm-dd","dd/mm/yyyy");
						lst.get(i).setDate_end_schedule(dateEndConvert);
						} catch (ParseException e1) {
							mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
						}
					}
					if(Common.isNotEmpty(lst.get(i).getDate_end_real()))
					{
						try {
						String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_end_real(), "yyyy-mm-dd","dd/mm/yyyy");
						lst.get(i).setDate_end_real(dateEndConvert);
						} catch (ParseException e1) {
							mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
						}
					}
				}
				
				request.setAttribute("pagenum", Integer.parseInt(page));
				mv.addObject("lst", lst1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			mv.addObject(ParamsConstants.MESSAGE_ERROR, "LỖI TÌM DỮ LIỆU : " + e.toString());
		}
		
		mv.setViewName("views/ReturnCouponManagement.jsp");
		return mv;
	}

	@RequestMapping(params = "delete",method = RequestMethod.POST)
	public ModelAndView delete(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		
		String coupon_cd = request.getParameter("coupon_cd");
		ReturnCouponModel brDel = new ReturnCouponModel();
		brDel.setCoupon_cd(coupon_cd);
		if(Common.isNotEmpty(coupon_cd))
		{
			ReturnCouponUpdateDeleteDao delte = new ReturnCouponUpdateDeleteDao(brDel);
			try {
				delte.excute();
				Common.showMessageNotification(mv, "XÓA THÀNH CÔNG PHIẾU TRẢ");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		ReturnCouponModel br  = new ReturnCouponModel();
		br.getCmpn_master().setCompany_cd((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
		ReturnCouponSelectDao BASD = new ReturnCouponSelectDao(br);
		
		try {
			List<ReturnCouponModel> lst = BASD.excute();
			if(lst.size()==0)
			{
				mv.addObject(ParamsConstants.MESSAGE_ERROR, "KHÔNG CÓ THẤY PHIẾU TRẢ NÀO ĐƯỢC TÌM THẤY");
			}
			else
			{
				mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "TÌM THẤY "+lst.size()+ " PHIẾU TRẢ ĐƯỢC TÌM THẤY");
				for(int i =0;i<lst.size();i++)
				{
					if(Common.isNotEmpty(lst.get(i).getDate_start()))
					{
						try {
						String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_start(), "yyyy-mm-dd","dd/mm/yyyy");
						lst.get(i).setDate_start(dateEndConvert);
						} catch (ParseException e1) {
							mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
						}
					}
					if(Common.isNotEmpty(lst.get(i).getDate_end_schedule()))
					{
						try {
						String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_end_schedule(), "yyyy-mm-dd","dd/mm/yyyy");
						lst.get(i).setDate_end_schedule(dateEndConvert);
						} catch (ParseException e1) {
							mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
						}
					}
					if(Common.isNotEmpty(lst.get(i).getDate_end_real()))
					{
						try {
						String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_end_real(), "yyyy-mm-dd","dd/mm/yyyy");
						lst.get(i).setDate_end_real(dateEndConvert);
						} catch (ParseException e1) {
							mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
						}
					}
				}
				mv.addObject("lst", lst);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			mv.addObject(ParamsConstants.MESSAGE_ERROR, "LỖI TÌM DỮ LIỆU : " + e.toString());
		}
		
		
		
		mv.setViewName("views/ReturnCouponManagement.jsp");
		return mv;
	}
	
	@RequestMapping(params = "search",method = RequestMethod.POST)
	public ModelAndView Search(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		
		ReturnCouponModel coupon = new ReturnCouponModel();
		coupon.getDept_master().setDept_cd(request.getParameter("department_cd_master"));
		coupon.getDept_master().setDept_name(request.getParameter("department_name_master"));
		coupon.getCmpn_client().setCompany_cd(request.getParameter("cmpn_cd"));
		coupon.getCmpn_client().setCompany_name(request.getParameter("cmpn_na"));
		coupon.setDate_start(request.getParameter("text_start_date"));
		coupon.setDate_start_e(request.getParameter("text_end_date"));
		coupon.setNumber_no(request.getParameter("number_no"));
		coupon.setStatus(request.getParameter("status_coupon"));
		
		if(Common.isNotEmpty(coupon.getStatus()))
		{
			if(coupon.getStatus().trim().equals("9999"))
			{
				coupon.setStatus(null);
			}
		}
		
		
		mv.addObject("coupon", coupon);
		
		coupon.getCmpn_master().setCompany_cd((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
		ReturnCouponSelectDao BASD = new ReturnCouponSelectDao(coupon);
		

		
		try {
			List<ReturnCouponModel> lst = BASD.excute();
			mv.addObject("numberofrow", lst.size());
			
			String page=request.getParameter("Current_Page");
			ReturnCouponModel coupon1 =coupon;
			if(page==null||page.trim()=="") 
				page="1";
			coupon1.setRowPage(page);
			coupon.getCmpn_master().setCompany_cd((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
			ReturnCouponSelectDao BASD1 = new ReturnCouponSelectDao(coupon1);
			List<ReturnCouponModel> lst1 = BASD1.excute();
			request.setAttribute("pagenum", Integer.parseInt(page));
			
			if(lst.size()==0)
			{
				mv.addObject(ParamsConstants.MESSAGE_ERROR, "KHÔNG CÓ THẤY PHIẾU TRẢ NÀO ĐƯỢC TÌM THẤY");
			}
			else
			{
				mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "TÌM THẤY "+lst.size()+ " PHIẾU TRẢ ĐƯỢC TÌM THẤY");
				for(int i =0;i<lst.size();i++)
				{
					if(Common.isNotEmpty(lst.get(i).getDate_start()))
					{
						try {
						String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_start(), "yyyy-mm-dd","dd/mm/yyyy");
						lst.get(i).setDate_start(dateEndConvert);
						} catch (ParseException e1) {
							mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
						}
					}
					if(Common.isNotEmpty(lst.get(i).getDate_end_schedule()))
					{
						try {
						String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_end_schedule(), "yyyy-mm-dd","dd/mm/yyyy");
						lst.get(i).setDate_end_schedule(dateEndConvert);
						} catch (ParseException e1) {
							mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
						}
					}
					if(Common.isNotEmpty(lst.get(i).getDate_end_real()))
					{
						try {
						String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_end_real(), "yyyy-mm-dd","dd/mm/yyyy");
						lst.get(i).setDate_end_real(dateEndConvert);
						} catch (ParseException e1) {
							mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
						}
					}
				}
				
				mv.addObject("lst", lst1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			mv.addObject(ParamsConstants.MESSAGE_ERROR, "LỖI TÌM DỮ LIỆU : " + e.toString());
		}
		
		mv.setViewName("views/ReturnCouponManagement.jsp");
		return mv;
	}
	
	@RequestMapping(params = "create",method = RequestMethod.POST)
	public String Create()
	{
		return "redirect:/ReturnCouponRegister";
	}
	
	@RequestMapping(params = "back",method = RequestMethod.POST)
	public String back()
	{
		return "redirect:/FeatureSystem";
	}
	
	@RequestMapping(params = "reportExcel", method = RequestMethod.POST)
	public ModelAndView excel(ModelMap modelMap, HttpServletRequest request) throws SQLException 
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		BorrowAssetModel borrow = new BorrowAssetModel();
		borrow.getAsset().setName(request.getParameter("text_asset_name"));
		borrow.getAsset().setModel(request.getParameter("text_model"));
		borrow.getAsset().setSeries(request.getParameter("text_series"));
		borrow.getAsset().setRFID(request.getParameter("text_rfid"));
		borrow.setDate_start_end(request.getParameter("text_end_date"));
		borrow.setDate_start(request.getParameter("text_start_date"));
		borrow.getDept_master().setDept_cd(request.getParameter("department_cd_master"));
		borrow.getDept_master().setDept_name(request.getParameter("department_name_master"));
		borrow.getDept_client().setDept_cd(request.getParameter("department_cd_client"));
		borrow.getDept_client().setDept_name(request.getParameter("department_name_client"));
		borrow.setNumber_on(request.getParameter("number_on"));
		borrow.getCmpn_master().setCompany_cd(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		BorrowAssetSelectDao BASD = new BorrowAssetSelectDao(borrow);
		
		try {
			List<BorrowAssetModel> lst = BASD.excute();
			if(lst.size()==0)
			{
				mv.addObject(ParamsConstants.MESSAGE_ERROR, "KHÔNG CÓ THẤY TÀI SẢN MƯỢN NÀO ĐƯỢC TÌM THẤY");
			}
			else
			{
				mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "TÌM THẤY "+lst.size()+ " TÀI SẢN MƯỢN ĐƯỢC TÌM THẤY");
				mv.addObject("lst", lst);
				return new ModelAndView(new ExcelBorrowListReportView(), "userList", lst);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			mv.addObject(ParamsConstants.MESSAGE_ERROR, "LỖI TÌM DỮ LIỆU : " + e.toString());
		}
		mv.addObject("borrow", borrow);
		mv.setViewName("views/AssetManagementGeneral.jsp");
		return mv;
	}
}
