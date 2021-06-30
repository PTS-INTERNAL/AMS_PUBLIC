package com.ams.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.CompanyInsertDao;
import com.ams.dao.CompanySelectDao;
import com.ams.dao.MovementCouponCountSelectDao;
import com.ams.dao.MovementCouponInsertDao;
import com.ams.dao.borrow.BorrowCouponCountSelectDao;
import com.ams.dao.borrow.BorrowCouponInsertDao;
import com.ams.model.BorrowCouponModel;
import com.ams.model.CompanyForm;
import com.ams.model.CompanyModel;
import com.ams.model.Department;
import com.ams.model.MovementCouponModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;

@Controller
@RequestMapping("/MovementCouponRegister")
public class MovementCouponRegister {
	
	public String TITLE = "MÀN HÌNH ĐĂNG KÝ LỆNH ĐIỀU ĐỘNG";
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject("TittleScreen",TITLE); 
		String error = "";
		MovementCouponModel moveCoupon = new MovementCouponModel();
		String cmpn_cd = Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD);
		
		
		//Tìm công ty sở hữu
		try {
			CompanyModel cmpnMdel = new CompanyModel();
			cmpnMdel.setCompany_cd(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
			CompanySelectDao companySelectDao = new CompanySelectDao(cmpnMdel);
			List<CompanyModel> lstcmp = companySelectDao.excute();
			if(lstcmp.size()==1)
			{
				moveCoupon.setCmpn_master(lstcmp.get(0));
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
		MovementCouponCountSelectDao MovementCountSelect = new MovementCouponCountSelectDao(cmpn_cd);
			String  number_no = MovementCountSelect.getBorrowCouponCount()+"";
			moveCoupon.setNumber_no(Integer.parseInt(number_no.trim())+1+"");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		mv.addObject("object", moveCoupon);
		
		
		if(error.trim().length()>0)
		{
			mv.addObject(ParamsConstants.MESSAGE_ERROR, error);
		}
		mv.setViewName("views/MovementCouponRegister.jsp");
		return mv;
	}
	
	@RequestMapping(params = "save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request) throws UnsupportedEncodingException, SQLException
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		
		request.setCharacterEncoding("UTF8");
		
		String error = "";
		MovementCouponModel movementCoupon = new MovementCouponModel();
		
		Department dept_master = new Department();
		dept_master.setDept_cd(request.getParameter("department_cd_master"));
		dept_master.setDept_name(request.getParameter("department_name_master"));
		movementCoupon.setDept_master(dept_master);
		
		Department dept_client = new Department();
		dept_client.setDept_cd(request.getParameter("department_cd_client"));
		dept_client.setDept_name(request.getParameter("department_name_client"));
		movementCoupon.setDept_client(dept_client);
		
		CompanyModel cmpn_master = new CompanyModel();
		cmpn_master.setCompany_name(request.getParameter("cmpn_na_master"));
		cmpn_master.setCompany_cd(request.getParameter("cmpn_cd_master"));
		movementCoupon.setCmpn_master(cmpn_master);
		
		CompanyModel cmpn_client = new CompanyModel();
		cmpn_client.setCompany_name(request.getParameter("cmpn_na_client"));
		cmpn_client.setCompany_cd(request.getParameter("cmpn_cd_client"));
		movementCoupon.setCmpn_client(cmpn_client);
		

		movementCoupon.setDate_start(request.getParameter("borrow_date"));
		movementCoupon.setDate_end_schedule(request.getParameter("pay_date_schedual"));
	//	bam.setDate_pay(request.getParameter("pay_date"));
		movementCoupon.setReason(request.getParameter("borrow_reason"));
		movementCoupon.setNumber_no(request.getParameter("number_on"));
		
		movementCoupon.setInsert_user(Common.getSessionValue(request,  SessionConstants.SESSION_USER_ID));
		movementCoupon.setInsert_dt(Common.getDateCurrent(ParamsConstants.CD_FULL_DATE));
		movementCoupon.setMovement_sticker(request.getParameter("move_sticker"));
		
		String modeInputNhap = request.getParameter("rdNhap");
		String modeInputXuat = request.getParameter("rdXuat");
		mv.addObject("inputNhap", modeInputNhap);
		mv.addObject("inputXuat", modeInputXuat);
		
		if("1".equals(modeInputNhap)) {
			CompanyModel cmpn = new CompanyModel();
			cmpn.setCompany_name(movementCoupon.getCmpn_client().getCompany_name());
			
			CompanySelectDao slecCmpn = new CompanySelectDao(cmpn);
			
			List<CompanyModel> lstCom = slecCmpn.excute();
			if(lstCom.size()>0)
			{
				//Đã tồn lại
				movementCoupon.getCmpn_client().setCompany_cd(lstCom.get(0).getCompany_cd());
			} else 
			{
				if(lstCom.size()==0)
				{
					//Không tồn tại
					CompanyForm form = new CompanyForm();
					form.setName(movementCoupon.getCmpn_client().getCompany_name());
					form.setShortName(movementCoupon.getCmpn_client().getCompany_name());
					CompanyInsertDao insertCompany = new CompanyInsertDao(form);
					
					try {
						insertCompany.excute();
						lstCom = slecCmpn.excute();
						movementCoupon.getCmpn_client().setCompany_cd(lstCom.get(0).getCompany_cd());						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}	
		}
		
		if("1".equals(modeInputXuat)) {
			CompanyModel cmpn = new CompanyModel();
			cmpn.setCompany_name(movementCoupon.getCmpn_master().getCompany_name());
			
			CompanySelectDao slecCmpn = new CompanySelectDao(cmpn);
			
			List<CompanyModel> lstCom = slecCmpn.excute();
			if(lstCom.size()>0)
			{
				//Đã tồn lại
				movementCoupon.getCmpn_master().setCompany_cd(lstCom.get(0).getCompany_cd());
			} else 
			{
				if(lstCom.size()==0)
				{
					//Không tồn tại
					CompanyForm form = new CompanyForm();
					form.setName(movementCoupon.getCmpn_master().getCompany_name());
					form.setShortName(movementCoupon.getCmpn_master().getCompany_name());
					CompanyInsertDao insertCompany = new CompanyInsertDao(form);
					
					try {
						insertCompany.excute();
						lstCom = slecCmpn.excute();
						movementCoupon.getCmpn_master().setCompany_cd(lstCom.get(0).getCompany_cd());						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}	
		}
		
		mv.addObject("object", movementCoupon);

		if(Common.isEmpty(movementCoupon.getCmpn_master().getCompany_cd()))
		{
			error += "Vui lòng chọn công ty cho mượn <br>";
		}
		
		if(Common.isEmpty(movementCoupon.getDate_start()))
		{
			error += "Vui lòng nhập ngày bắt đầu cho mượn <br>";
		}
		
		if(Common.isEmpty(movementCoupon.getNumber_no()))
		{
			error += "Vui lòng nhập mã lệnh <br>";
		}
		
		if(Common.isEmpty(movementCoupon.getCmpn_client().getCompany_cd()))
		{
			error += "Vui lòng chọn công ty mượn <br>";
		}
		
		
		if(movementCoupon.getCmpn_master().getCompany_cd().equals(movementCoupon.getCmpn_client().getCompany_cd()))
		{
			if(Common.isEmpty(movementCoupon.getDept_master().getDept_cd()))
			{
				error += "Vui lòng nhập đơn vị cho mượn <br>";
			}
			if(Common.isEmpty(movementCoupon.getDept_client().getDept_cd()))
			{
				error += "Vui lòng nhập đơn vị mượn <br>";
			}
		}
		
		if(Common.isEmpty(movementCoupon.getDate_end_schedule()))
		{
			error += "Vui lòng nhập ngày trả theo kế hoạch <br>";
		}
		if(Common.isEmpty(movementCoupon.getReason()))
		{
			error += "Vui lòng nhập lý do cho mượn <br>";
		}
		
		try {
			if(Common.CompareDate(movementCoupon.getDate_start(), 
									"yyyy-MM-dd", 
									movementCoupon.getDate_end_schedule(), 
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
			MovementCouponInsertDao CouponInsert = new MovementCouponInsertDao(movementCoupon);
			try {
				CouponInsert.excute();
				mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "ĐĂNG KÝ PHIẾU LỆNH ĐIỀU ĐỘNG THÀNH CÔNG");
				mv.setViewName("views/MovementCouponManagement.jsp");
				return mv;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mv.addObject(ParamsConstants.MESSAGE_ERROR, "LỖI ĐĂNG KÝ PHIẾU");
			}
		}
		mv.setViewName("views/MovementCouponRegister.jsp");
		return mv;
	}
	@RequestMapping(params = "back", method = RequestMethod.POST)
	public String back()
	{
		return "redirect:/MovementManagement";
	}

}
