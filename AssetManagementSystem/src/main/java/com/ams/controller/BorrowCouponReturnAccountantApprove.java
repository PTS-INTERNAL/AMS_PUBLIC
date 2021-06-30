package com.ams.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.BorrowAssetSelectDao;
import com.ams.dao.assetgeneral.AssetGeneralUpdateStatusDao;
import com.ams.dao.borrow.BorrowCouponSelectDao;
import com.ams.dao.borrow.BorrowCouponUpdateApproveDeptDao;
import com.ams.dao.borrow.BorrowCouponUpdateDeleteDao;
import com.ams.dao.borrow.BorrowCouponUpdateDisApproveDeptDao;
import com.ams.dao.borrow.BorrowCouponUpdateStatusDao;
import com.ams.dao.loan.LoanCouponUpdateStatusDao;
import com.ams.helper.PdfChoMuon;
import com.ams.model.AssetGeneralModel;
import com.ams.model.BorrowAssetModel;
import com.ams.model.BorrowCouponModel;
import com.ams.model.ExportBorrowMove;
import com.ams.model.LoanCouponModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;
import com.ams.util.SystemControl;

@Controller
@RequestMapping("/BorrowCouponReturnAccountantApprove")
public class BorrowCouponReturnAccountantApprove {
	String TITLE = "MÀN HÌNH DUYỆT TRẢ CHO MƯỢN TÀI SẢN SẢN CỦA KẾ TOÁN";
	@RequestMapping(params = "init",method = RequestMethod.POST)
	public ModelAndView init(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		String coupon_cd = request.getParameter("coupon_cd");

		BorrowCouponModel brCoupn = new BorrowCouponModel();
		brCoupn.setCoupon_cd(coupon_cd);

		BorrowCouponSelectDao selectBorrow = new BorrowCouponSelectDao(brCoupn);
		List<BorrowCouponModel> lst;
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
		BorrowAssetModel itemBorrow = new BorrowAssetModel(); 
		itemBorrow.setBorrowCoupon(brCoupn);
		
		BorrowAssetSelectDao lstBrrow = new BorrowAssetSelectDao(itemBorrow);
		List<BorrowAssetModel> lstSearch = new ArrayList<>();	
		try {
			lstSearch = lstBrrow.excute();
			mv.addObject("lstBr", lstSearch);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		mv.setViewName("views/BorrowCouponReturnAccountantApprove.jsp");
		return mv;
	}
	
	@RequestMapping(params = "approveExport",method = RequestMethod.POST)
	public ModelAndView approveExport(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		String coupon_cd = request.getParameter("coupon_cd");

		BorrowCouponModel brCoupn = new BorrowCouponModel();
		brCoupn.setCoupon_cd(coupon_cd);

		BorrowCouponSelectDao selectBorrow = new BorrowCouponSelectDao(brCoupn);
		List<BorrowCouponModel> lst;
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
		BorrowAssetModel itemBorrow = new BorrowAssetModel(); 
		itemBorrow.setBorrowCoupon(brCoupn);
		
		BorrowAssetSelectDao lstBrrow = new BorrowAssetSelectDao(itemBorrow);
		List<BorrowAssetModel> lstSearch = new ArrayList<>();	
		try {
			lstSearch = lstBrrow.excute();
			mv.addObject("lstBr", lstSearch);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		request.getSession().setAttribute("isExport", "9999");
		mv.setViewName("views/BorrowCouponManagmentDeptApproveExport.jsp");
		return mv;
	}
	
	
	@RequestMapping(params = "approveExportPrint",method = RequestMethod.POST)
	public ModelAndView approveExportPrint(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		String coupon_cd = request.getParameter("coupon_cd");

		BorrowCouponModel brCoupn = new BorrowCouponModel();
		brCoupn.setCoupon_cd(coupon_cd);

		BorrowCouponSelectDao selectBorrow = new BorrowCouponSelectDao(brCoupn);
		List<BorrowCouponModel> lst = null;
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
		BorrowAssetModel itemBorrow = new BorrowAssetModel(); 
		itemBorrow.setBorrowCoupon(brCoupn);
		
		BorrowAssetSelectDao lstBrrow = new BorrowAssetSelectDao(itemBorrow);
		List<BorrowAssetModel> lstSearch = new ArrayList<>();	
		try {
			lstSearch = lstBrrow.excute();
			mv.addObject("lstBr", lstSearch);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		//ExportBorrowMove export = new ExportBorrowMove(lstSearch, lst.get(0));
		return new ModelAndView(new PdfChoMuon(), "object", null);
	}
	
	
	@RequestMapping(params = "approve",method = RequestMethod.POST)
	public ModelAndView approve(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		String coupon_cd = request.getParameter("coupon_cd");

		BorrowCouponModel brCoupn = new BorrowCouponModel();
		brCoupn.setCoupon_cd(coupon_cd);
		
		String status_device = request.getParameter("status_asset");
		if(Common.isEmpty(status_device))
		{
			Common.showMessageError(mv, "KHÔNG XÁC NHẬN TRẠNG THÁI CỦA TÀI SẢN TRƯỚC KHI DUYỆT");
		} else {
			brCoupn.setStatus("8");
			brCoupn.setApprove_comment(status_device);
			brCoupn.setUpdate_dt(Common.getDateCurrent(ParamsConstants.CD_FULL_DATE));
			SystemControl sys = new SystemControl(request);
			brCoupn.setUpdate_user(sys.EmployeeCD);
			BorrowCouponUpdateApproveDeptDao updateApprove = new BorrowCouponUpdateApproveDeptDao(brCoupn);
			try {
				updateApprove.excute();
				Common.showMessageNotification(mv, "PHÊ DUYỆT THÀNH CÔNG");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		BorrowCouponSelectDao selectBorrow = new BorrowCouponSelectDao(brCoupn);
		List<BorrowCouponModel> lst;
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

				mv.addObject("coupon", lst.get(0));
				
				LoanCouponModel loanr = new LoanCouponModel();
				loanr.setCoupon_cd(lst.get(0).getCoupon_loan_cd());
				loanr.setStatus("7");
				
				LoanCouponUpdateStatusDao updates = new LoanCouponUpdateStatusDao(loanr);
				updates.excute();
				
				
				
				
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
		BorrowAssetModel itemBorrow = new BorrowAssetModel(); 
		itemBorrow.setBorrowCoupon(brCoupn);
		
		BorrowAssetSelectDao lstBrrow = new BorrowAssetSelectDao(itemBorrow);
		List<BorrowAssetModel> lstSearch = new ArrayList<>();	
		try {
			lstSearch = lstBrrow.excute();
			mv.addObject("lstBr", lstSearch);
			
			for(int i=0;i<lstSearch.size();i++)
			{
				AssetGeneralModel ass = new AssetGeneralModel();
				ass.setStatus("1");
				ass.setId(lstSearch.get(i).getAsset().getId());
				AssetGeneralUpdateStatusDao updateAsssetGen = new AssetGeneralUpdateStatusDao(ass);
				updateAsssetGen.excute();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		mv.setViewName("views/BorrowCouponReturnAccountantApprove.jsp");
		return mv;
	}
	
	@RequestMapping(params = "disApprove",method = RequestMethod.POST)
	public ModelAndView disApprove(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		String coupon_cd = request.getParameter("coupon_cd");

		BorrowCouponModel brCoupn = new BorrowCouponModel();
		brCoupn.setCoupon_cd(coupon_cd);
		
		String reason_not_allow = request.getParameter("reason_not_allow");
		if(Common.isEmpty(reason_not_allow))
		{
			Common.showMessageError(mv, "VUI LÒNG NHẬP LÝ DO KHÔNG DUYỆT CHO MƯỢN");
		} else {
			brCoupn.setStatus("0");
			brCoupn.setReasonNotAllow(reason_not_allow);
			brCoupn.setUpdate_dt(Common.getDateCurrent(ParamsConstants.CD_FULL_DATE));
			SystemControl sys = new SystemControl(request);
			brCoupn.setUpdate_user(sys.EmployeeCD);
			BorrowCouponUpdateDisApproveDeptDao updateApprove = new BorrowCouponUpdateDisApproveDeptDao(brCoupn);
			try {
				updateApprove.excute();
				Common.showMessageNotification(mv, "XÁC NHẬN KHÔNG DUYỆT THÀNH CÔNG");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		BorrowCouponSelectDao selectBorrow = new BorrowCouponSelectDao(brCoupn);
		List<BorrowCouponModel> lst;
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
		BorrowAssetModel itemBorrow = new BorrowAssetModel(); 
		itemBorrow.setBorrowCoupon(brCoupn);
		
		BorrowAssetSelectDao lstBrrow = new BorrowAssetSelectDao(itemBorrow);
		List<BorrowAssetModel> lstSearch = new ArrayList<>();	
		try {
			lstSearch = lstBrrow.excute();
			mv.addObject("lstBr", lstSearch);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		mv.setViewName("views/BorrowCouponReturnAccountantApprove.jsp");
		return mv;
	}
	
	@RequestMapping(params = "back",method = RequestMethod.POST)
	public String back(HttpServletRequest request)
	{
		
		String ex = "";
		ex=(String) request.getSession().getAttribute("isExport");
		if(Common.isNotEmpty(ex))
		{
			request.getSession().removeAttribute("isExport");
			if(ex.trim().equals("9999"))
			{
				return "redirect:/BorrowCouponManagement";
			}
			if(ex.trim().equals("8888"))
			{
				return "redirect:/AccountantBorrowCouponManagement";
			}
			if(ex.trim().equals("77777"))
			{
				return "redirect:/StockBorrowCouponManagement";
			}
		}
			
		return "redirect:/BorrowCouponManagement";
	}

}
