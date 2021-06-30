package com.ams.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.MovementAssetSelectDao;
import com.ams.dao.MovementCouponSelectDao;
import com.ams.dao.MovementCouponUpdateApproveDeptDao;
import com.ams.dao.MovementCouponUpdateDeleteDao;
import com.ams.dao.borrow.BorrowCouponSelectDao;
import com.ams.model.BorrowCouponModel;
import com.ams.model.MovementAssetModel;
import com.ams.model.MovementCouponModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;
import com.ams.util.SystemControl;

@Controller
@RequestMapping("/MovementManagement")
public class MovementManagement {
	String TITLE = "MÀN HÌNH QUẢN LÝ ĐIỀU ĐỘNG";
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView init()
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject("TittleScreen",TITLE); 

		mv.setViewName("views/MovementCouponManagement.jsp");
		return mv;
	}
	
	@RequestMapping(params="create", method=RequestMethod.POST)
	public String Create(HttpServletRequest request, HttpServletResponse response)
	{
		return "redirect:/MovementCouponRegister";
	}
	
	@RequestMapping(params = "view",method = RequestMethod.POST)
	public ModelAndView approve(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		String coupon_cd = request.getParameter("coupon_cd");

		MovementCouponModel brCoupn = new MovementCouponModel();
		brCoupn.setCoupon_cd(coupon_cd);
		
		
		MovementCouponSelectDao selectBorrow = new MovementCouponSelectDao(brCoupn);
		List<MovementCouponModel> lst;
		try {
			lst = selectBorrow.excute();
			if (lst.size() == 1) {
				int i = 0;
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
				
				if(Common.isNotEmpty(lst.get(i).getDt_approve()))
				{
					try {
					String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDt_approve(), "yyyymmdd","dd/mm/yyyy");
					lst.get(i).setDt_approve(dateEndConvert);
					} catch (ParseException e1) {
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
					}
				}
				if(Common.isNotEmpty(lst.get(i).getDt_accountant()))
				{
					try {
					String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDt_accountant(), "yyyymmdd","dd/mm/yyyy");
					lst.get(i).setDt_accountant(dateEndConvert);
					} catch (ParseException e1) {
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
					}
				}
				if(Common.isNotEmpty(lst.get(i).getDt_stock()))
				{
					try {
					String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDt_stock(), "yyyymmdd","dd/mm/yyyy");
					lst.get(i).setDt_stock(dateEndConvert);
					} catch (ParseException e1) {
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
					}
				}

				mv.addObject("coupon", lst.get(0));
			} else {
				Common.showMessageError(mv, "KHÔNG TÌM THẤY PHIẾU CHO MƯỢN");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Common.showMessageError(mv, "LỖI TÌM PHIẾU CHO MƯỢN");
		}
		
		
		//Set view layout
		SystemControl system = new SystemControl(request);
		String number_row = system.getParameter(ParamsConstants.ROW_BORROW_NUMBER);
		
		mv.addObject("number_row", number_row.trim());
		MovementAssetModel itemBorrow = new MovementAssetModel(); 
		itemBorrow.setMovementCoupon(brCoupn);
		
		MovementAssetSelectDao lstBrrow = new MovementAssetSelectDao(itemBorrow);
		List<MovementAssetModel> lstSearch = new ArrayList<>();	
		try {
			lstSearch = lstBrrow.excute();
			mv.addObject("lstBr", lstSearch);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		mv.setViewName("views/MovementCouponManagmentView.jsp");
		return mv;
	}
	@RequestMapping(params="back", method = RequestMethod.POST)
	public String back(HttpServletRequest request) 
	{
		return "redirect:/FeatureSystem";
	}

	@RequestMapping(params = "search",method = RequestMethod.POST)
	public ModelAndView Search(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		
		MovementCouponModel coupon = new MovementCouponModel();
		coupon.getDept_master().setDept_cd(request.getParameter("department_cd_master"));
		coupon.getDept_master().setDept_name(request.getParameter("department_name_master"));
		
		coupon.getCmpn_client().setCompany_name(request.getParameter("cmpn_na"));
		if(Common.isNotEmpty(coupon.getCmpn_client().getCompany_name()))
		coupon.getCmpn_client().setCompany_cd(request.getParameter("cmpn_cd"));
		
		
		coupon.getCmpn_master().setCompany_name(request.getParameter("cmpn_master_na"));
		if(Common.isNotEmpty(coupon.getCmpn_master().getCompany_name()))
		coupon.getCmpn_master().setCompany_cd(request.getParameter("cmpn_master_cd"));
		coupon.setDate_start(request.getParameter("text_start_date"));
		coupon.setDate_start_e(request.getParameter("text_end_date"));
		coupon.setNumber_no(request.getParameter("number_no"));
		coupon.setStatus(request.getParameter("status_coupon"));
		
		mv.addObject("coupon", coupon);
//		if(Common.isEmpty(coupon.getCmpn_master().getCompany_cd())) {
//			coupon.getCmpn_master().setCompany_cd((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
//		}
//		if(Common.isEmpty(coupon.getCmpn_client().getCompany_cd())) {
//			coupon.getCmpn_client().setCompany_cd((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
//		}
		MovementCouponSelectDao BASD = new MovementCouponSelectDao(coupon);
		
		try {
			List<MovementCouponModel> lst = BASD.excute();
			if(lst.size()==0)
			{
				mv.addObject(ParamsConstants.MESSAGE_ERROR, "KHÔNG CÓ THẤY LỆNH ĐIỀU ĐỘNG NÀO ĐƯỢC TÌM THẤY");
			}
			else
			{
				//mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "TÌM THẤY "+lst.size()+ " LỆNH ĐIỀU ĐỘNG ĐƯỢC TÌM THẤY");
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
		
		
		
		mv.setViewName("views/MovementCouponManagement.jsp");
		return mv;
	}
	
	@RequestMapping(params = "delete",method = RequestMethod.POST)
	public ModelAndView delete(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		
		String coupon_cd  = request.getParameter("coupon_cd");
		MovementCouponModel coupn = new MovementCouponModel();
		coupn.setCoupon_cd(coupon_cd);
		MovementCouponUpdateDeleteDao update = new MovementCouponUpdateDeleteDao(coupn);
		try {
			update.excute();
			mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "XÓA THÀNH CÔNG LỆNH ĐIỀU ĐỘNG");

		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		MovementCouponModel coupon = new MovementCouponModel();
		coupon.getDept_master().setDept_cd(request.getParameter("department_cd_master"));
		coupon.getDept_master().setDept_name(request.getParameter("department_name_master"));
		
		coupon.getCmpn_client().setCompany_name(request.getParameter("cmpn_na"));
		if(Common.isNotEmpty(coupon.getCmpn_client().getCompany_name()))
		coupon.getCmpn_client().setCompany_cd(request.getParameter("cmpn_cd"));
		
		
		coupon.getCmpn_master().setCompany_name(request.getParameter("cmpn_master_na"));
		if(Common.isNotEmpty(coupon.getCmpn_master().getCompany_name()))
		coupon.getCmpn_master().setCompany_cd(request.getParameter("cmpn_master_cd"));
		coupon.setDate_start(request.getParameter("text_start_date"));
		coupon.setDate_start_e(request.getParameter("text_end_date"));
		coupon.setNumber_no(request.getParameter("number_no"));
		coupon.setStatus(request.getParameter("status_coupon"));
		
	
		
		
		mv.addObject("coupon", coupon);
//		if(Common.isEmpty(coupon.getCmpn_master().getCompany_cd())) {
////			coupon.getCmpn_master().setCompany_cd((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
//		}
//		if(Common.isEmpty(coupon.getCmpn_client().getCompany_cd())) {
//			coupon.getCmpn_client().setCompany_cd((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
//		}
		MovementCouponSelectDao BASD = new MovementCouponSelectDao(coupon);
		
		try {
			List<MovementCouponModel> lst = BASD.excute();
			if(lst.size()==0)
			{
				mv.addObject(ParamsConstants.MESSAGE_ERROR, "KHÔNG CÓ THẤY LỆNH ĐIỀU ĐỘNG NÀO ĐƯỢC TÌM THẤY");
			}
			else
			{
				mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "TÌM THẤY "+lst.size()+ " LỆNH ĐIỀU ĐỘNG ĐƯỢC TÌM THẤY");
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
		
		
		
		mv.setViewName("views/MovementCouponManagement.jsp");
		return mv;
	}
	

	

}
