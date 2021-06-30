package com.ams.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.AssetGeneralSelectDao;
import com.ams.dao.BorrowAssetInsertDao;
import com.ams.dao.BorrowAssetSelectDao;
import com.ams.dao.CompanySelectDao;
import com.ams.dao.borrow.BorrowCouponCountSelectDao;
import com.ams.dao.borrow.BorrowCouponInsertDao;
import com.ams.model.AssetGeneralFormSearch;
import com.ams.model.AssetGeneralModel;
import com.ams.model.BorrowAssetModel;
import com.ams.model.BorrowCouponModel;
import com.ams.model.CompanyModel;
import com.ams.model.Department;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;
import com.ams.util.SystemControl;
@Controller
@RequestMapping("/BorrowCouponRegister")
public class BorrowCouponRegisterController {
	String TITLE = "MÀN HÌNH ĐĂNG KÝ PHIẾU CHO MƯỢN TÀI SẢN SẢN";
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request)
	{
		//Khoi tao
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		String error = "";
		BorrowCouponModel BorrowCoupon = new BorrowCouponModel();
		String cmpn_cd = Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD);
		
		
		//Tìm công ty sở hữu
		try {
			CompanyModel cmpnMdel = new CompanyModel();
			cmpnMdel.setCompany_cd(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
			CompanySelectDao companySelectDao = new CompanySelectDao(cmpnMdel);
			List<CompanyModel> lstcmp = companySelectDao.excute();
			if(lstcmp.size()==1)
			{
				BorrowCoupon.setCmpn_master(lstcmp.get(0));
			}
			else
			{
				error = error + "KHÔNG TỒN TẠI CÔNG TY SỞ HỮU";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			error = error + e.toString() + "<br>LỖI TÌM KIẾM CÔNG TY SỞ HỮU <br>";
		}
		
		//Lấy số mã lệnh
		try {
		BorrowCouponCountSelectDao BorrowCountSelect = new BorrowCouponCountSelectDao(cmpn_cd);
			String  number_no = BorrowCountSelect.getBorrowCouponCount()+"";
			BorrowCoupon.setNumber_no(Integer.parseInt(number_no.trim())+1+"");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		mv.addObject("object", BorrowCoupon);
		
		
		if(error.trim().length()>0)
		{
			mv.addObject(ParamsConstants.MESSAGE_ERROR, error);
		}
		mv.setViewName("views/BorrowCouponRegister.jsp");
		return mv;
	}
	
	@RequestMapping(params = "save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request) throws UnsupportedEncodingException
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		
		request.setCharacterEncoding("UTF8");
		
		String error = "";
		BorrowCouponModel BorrowCoupon = new BorrowCouponModel();
		
		Department dept_master = new Department();
		dept_master.setDept_cd(request.getParameter("department_cd_master"));
		dept_master.setDept_name(request.getParameter("department_name_master"));
		BorrowCoupon.setDept_master(dept_master);
		
		Department dept_client = new Department();
		dept_client.setDept_cd(request.getParameter("department_cd_client"));
		dept_client.setDept_name(request.getParameter("department_name_client"));
		BorrowCoupon.setDept_client(dept_client);
		
		CompanyModel cmpn_master = new CompanyModel();
		cmpn_master.setCompany_name(request.getParameter("cmpn_na_master"));
		cmpn_master.setCompany_cd(request.getParameter("cmpn_cd_master"));
		BorrowCoupon.setCmpn_master(cmpn_master);
		
		CompanyModel cmpn_client = new CompanyModel();
		cmpn_client.setCompany_name(request.getParameter("cmpn_na_client"));
		cmpn_client.setCompany_cd(request.getParameter("cmpn_cd_client"));
		BorrowCoupon.setCmpn_client(cmpn_client);
		

		BorrowCoupon.setDate_start(request.getParameter("borrow_date"));
		BorrowCoupon.setDate_end_schedule(request.getParameter("pay_date_schedual"));
	//	bam.setDate_pay(request.getParameter("pay_date"));
		BorrowCoupon.setReason(request.getParameter("borrow_reason"));
		BorrowCoupon.setNumber_no(request.getParameter("number_on"));
		
		BorrowCoupon.setInsert_user(Common.getSessionValue(request,  SessionConstants.SESSION_USER_ID));
		BorrowCoupon.setInsert_dt(Common.getDateCurrent(ParamsConstants.CD_FULL_DATE));
		
		mv.addObject("object", BorrowCoupon);

		if(Common.isEmpty(BorrowCoupon.getCmpn_master().getCompany_cd()))
		{
			error += "Vui lòng chọn công ty cho mượn <br>";
		}
		
		if(Common.isEmpty(BorrowCoupon.getDate_start()))
		{
			error += "Vui lòng nhập ngày bắt đầu cho mượn <br>";
		}
		
		if(Common.isEmpty(BorrowCoupon.getNumber_no()))
		{
			error += "Vui lòng nhập mã lệnh <br>";
		}
		
		if(Common.isEmpty(BorrowCoupon.getCmpn_client().getCompany_cd()))
		{
			error += "Vui lòng chọn công ty mượn <br>";
		}
		
		
		if(BorrowCoupon.getCmpn_master().getCompany_cd().equals(BorrowCoupon.getCmpn_client().getCompany_cd()))
		{
			if(Common.isEmpty(BorrowCoupon.getDept_master().getDept_cd()))
			{
				error += "Vui lòng nhập đơn vị cho mượn <br>";
			}
			if(Common.isEmpty(BorrowCoupon.getDept_client().getDept_cd()))
			{
				error += "Vui lòng nhập đơn vị mượn <br>";
			}
		}
		
		if(Common.isEmpty(BorrowCoupon.getDate_end_schedule()))
		{
			error += "Vui lòng nhập ngày trả theo kế hoạch <br>";
		}
		if(Common.isEmpty(BorrowCoupon.getReason()))
		{
			error += "Vui lòng nhập lý do cho mượn <br>";
		}
		
		try {
			if(Common.CompareDate(BorrowCoupon.getDate_start(), 
									"yyyy-MM-dd", 
									BorrowCoupon.getDate_end_schedule(), 
									"yyyy-MM-dd") == false)
			{
				error += "NGÀY BẮT ĐẦU LỚN HƠN NGÀY KẾT THÚC";
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error += "LỖI NHẬP NGÀY KHÔNG CHÍNH XÁC";
		}
		
		
		
		if(error.trim().length()>0)
		{
			mv.addObject(ParamsConstants.MESSAGE_ERROR, error);
		} else {
			BorrowCouponInsertDao BorrowCouponInsert = new BorrowCouponInsertDao(BorrowCoupon);
			try {
				BorrowCouponInsert.excute();
				mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "ĐĂNG KÝ PHIẾU CHO MƯỢN THÀNH CÔNG");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mv.addObject(ParamsConstants.MESSAGE_ERROR, "LỖI ĐĂNG KÝ PHIẾU");
			}
		}
		mv.setViewName("views/BorrowCouponRegister.jsp");
		return mv;
	}
	
	@RequestMapping(params = "getImfor", method = RequestMethod.POST)
	public ModelAndView getImfor(HttpServletRequest request) throws UnsupportedEncodingException
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		
		request.setCharacterEncoding("UTF8");
		
		BorrowAssetModel bam = new BorrowAssetModel();
		AssetGeneralModel asset = new AssetGeneralModel();
		asset.setRFID(request.getParameter("asset_rfid"));
		asset.setModel(request.getParameter("asset_model"));
		asset.setName(request.getParameter("asset_name"));
		asset.setSeries(request.getParameter("asset_series"));
		bam.setAsset(asset);
		
		Department dept_master = new Department();
		dept_master.setDept_cd(request.getParameter("department_cd_master"));
		dept_master.setDept_name(request.getParameter("department_name_master"));
		bam.setDept_master(dept_master);
		
		Department dept_client = new Department();
		dept_client.setDept_cd(request.getParameter("department_cd_client"));
		dept_client.setDept_name(request.getParameter("department_name_client"));
		bam.setDept_client(dept_client);
		
		CompanyModel cmpn_master = new CompanyModel();
		cmpn_master.setCompany_name(request.getParameter("cmpn_na_master"));
		cmpn_master.setCompany_cd(request.getParameter("cmpn_cd_master"));
		bam.setCmpn_master(cmpn_master);
		
		CompanyModel cmpn_client = new CompanyModel();
		cmpn_client.setCompany_name(request.getParameter("cmpn_na_client"));
		cmpn_client.setCompany_cd(request.getParameter("cmpn_cd_client"));
		bam.setCmpn_client(cmpn_client);
		

		bam.setDate_start(request.getParameter("borrow_date"));
		bam.setDate_end(request.getParameter("pay_date_schedual"));
	//	bam.setDate_pay(request.getParameter("pay_date"));
		bam.setReason(request.getParameter("borrow_reason"));
		bam.setNumber_on(request.getParameter("number_on"));
		
		bam.setUserInsert(Common.getSessionValue(request,  SessionConstants.SESSION_USER_ID));
		bam.setInsertDt(Common.getDateCurrent("YYYYMMdd"));
		
		
		
		if(Common.isNotEmpty(bam.getAsset().getSeries()))
		{
		
		AssetGeneralFormSearch as = new AssetGeneralFormSearch();
	    as.setSeries(bam.getAsset().getSeries());
		as.setCompany_CD((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
	    AssetGeneralSelectDao  selectAsset = new AssetGeneralSelectDao(as);
		List<AssetGeneralModel> lst = null;
		try {
			lst = selectAsset.excute();
			if(lst.size()==1)
			{
				bam.getAsset().setName(lst.get(0).getName());
				bam.getAsset().setModel(lst.get(0).getModel());
				bam.getAsset().setRFID(lst.get(0).getRFID());
			}
			else
			{
				mv.addObject(ParamsConstants.MESSAGE_ERROR, "KHÔNG TÌM THẤY TÀI SẢN");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mv.addObject(ParamsConstants.MESSAGE_ERROR, "LỖI XẢY RA TRONG LÚC TÌM KIẾM");

		}
		}
		else
		{
			mv.addObject(ParamsConstants.MESSAGE_ERROR, "VUI LÒNG NHẬP SERIES");
		}
		
		
		
		mv.addObject("asset", bam);
		mv.setViewName("views/BorrowAssetRegister.jsp");
		return mv;
	}
	
	@RequestMapping(params = "back", method = RequestMethod.POST)
	public String back()
	{
		return "redirect:/BorrowCouponManagement";
	}
}
