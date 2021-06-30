package com.ams.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.COMM_FAILURE;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.AssetGeneralIncludeSelectDao;
import com.ams.dao.AssetGeneralSelectDao;
import com.ams.dao.BorrowAssetApproveDao;
import com.ams.dao.BorrowAssetInsertDao;
import com.ams.dao.BorrowAssetSelectDao;
import com.ams.dao.borrow.BorrowAssetUpdateDao;
import com.ams.dao.borrow.BorrowAssetUpdateDeleteDao;
import com.ams.dao.CompanySelectDao;
import com.ams.dao.InventoryCheckingResultSelectDao;
import com.ams.dao.LoanAssetInsertDao;
import com.ams.dao.UserSelectDao;
import com.ams.dao.assetgeneral.AssetGeneralUpdateStatusDao;
import com.ams.dao.borrow.BorrowCouponSelectDao;
import com.ams.helper.PdfChoMuon;
import com.ams.helper.PdfInventorySession;
import com.ams.model.AssetGeneralFormSearch;
import com.ams.model.AssetGeneralModel;
import com.ams.model.BorrowAssetModel;
import com.ams.model.BorrowCouponModel;
import com.ams.model.CompanyModel;
import com.ams.model.ExportBorrowMove;
import com.ams.model.InventoryCheckingRealtimeModel;
import com.ams.model.LoanAssetModel;
import com.ams.model.UserModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;
import com.ams.util.SystemControl;

@Controller
@RequestMapping("/BorrowAssetDeclare")
public class BorrowAssetDeclareController {
	String TITLE = "MÀN HÌNH KHAI BÁO TÀI SẢN CHO MƯỢN";

	@RequestMapping(params = "declare", method = RequestMethod.POST)
	public ModelAndView init(HttpServletRequest request) throws SQLException {
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
			
			if(lstSearch.size()< Integer.parseInt( number_row.trim())) {
				while(lstSearch.size()< Integer.parseInt( number_row.trim()))
				{
					BorrowAssetModel item = new BorrowAssetModel();
					lstSearch.add(item);
				}
			}
			
			mv.addObject("lstBr", lstSearch);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		AssetGeneralFormSearch form = new AssetGeneralFormSearch();
		form.setDepartment_cd(lst.get(0).getDept_client().getDept_cd());
		form.setCompany_CD((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
		AssetGeneralIncludeSelectDao AssetSelectDaoSearch = new AssetGeneralIncludeSelectDao(form);
		List<AssetGeneralModel> lstAsset= AssetSelectDaoSearch.excute();
		
		System.out.println(lst.get(0).getDept_client().getDept_cd());
		
		mv.addObject("listAssetSearch", lstAsset);
		//coupon.getDept_client().getDept_name()
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		mv.setViewName("views/BorrowAssetDeclare.jsp");
		return mv;
	}
	
	@RequestMapping(params = "delete", method = RequestMethod.POST)
	public ModelAndView delete(HttpServletRequest request) {
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
		
		String deleteId = request.getParameter("delete_id");
		if(Common.isNotEmpty(deleteId) && deleteId.equals("undefined")==false)
		{
			BorrowAssetUpdateDeleteDao updateDel = new BorrowAssetUpdateDeleteDao(deleteId);
			try {
				updateDel.excute();
				Common.showMessageNotification(mv, "THỰC THI XÓA THÀNH CÔNG");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		mv.addObject("number_row", number_row.trim());
		BorrowAssetModel itemBorrow = new BorrowAssetModel(); 
		itemBorrow.setBorrowCoupon(brCoupn);
		
		BorrowAssetSelectDao lstBrrow = new BorrowAssetSelectDao(itemBorrow);
		List<BorrowAssetModel> lstSearch = new ArrayList<>();	
		try {
			lstSearch = lstBrrow.excute();
			
			if(lstSearch.size()< Integer.parseInt( number_row.trim())) {
				while(lstSearch.size()< Integer.parseInt( number_row.trim()))
				{
					BorrowAssetModel item = new BorrowAssetModel();
					lstSearch.add(item);
				}
			}
			
			mv.addObject("lstBr", lstSearch);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		mv.setViewName("views/BorrowAssetDeclare.jsp");
		return mv;
	}
	
	@RequestMapping(params = "save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request) {
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
		
		
		
		
		if(lst.size()==1)
		{
			int limt_row = Integer.parseInt(number_row.trim());
			List<BorrowAssetModel> lstAsset = new ArrayList<>();
			for(int i=1; i<=limt_row; i++)
			{
				BorrowAssetModel ass = new BorrowAssetModel();
				ass.setId(request.getParameter("borrow["+i+"]_cd"));
				ass.getAsset().setRFID(request.getParameter("rfid["+i+"]"));
				ass.getAsset().setModel(request.getParameter("model["+i+"]"));
				ass.getAsset().setSeries(request.getParameter("series["+i+"]"));
				ass.getAsset().setName(request.getParameter("name["+i+"]"));
				ass.getAsset().setDepartment_name(request.getParameter("dept["+i+"]"));
				ass.getAsset().setStatus(request.getParameter("status["+i+"]"));
				ass.setAsseseries(request.getParameter("asseseries["+i+"]"));
				ass.getAsset().setCompany_CD(lst.get(0).getCmpn_master().getCompany_cd());
				ass.getAsset().setDepartment_cd(request.getParameter("master_dept_cd"));
				lstAsset.add(ass);
			}
			String Error = "";
			if(lstAsset.size()>0)
			{
				for(int i =0; i<lstAsset.size(); i++)
				{
					if(lstAsset.get(i).getAsset().isEmpty() == false)
					{
						if(isTrung(i,lstAsset)==false)
						{
						
							AssetGeneralSelectDao assSelect = new AssetGeneralSelectDao(lstAsset.get(i).getAsset());
							
							try {
								List<AssetGeneralModel> lstSearch = assSelect.excute();
								if(lstSearch.size()==1)
								{
									lstAsset.get(i).setAsset(lstSearch.get(0));
									if(!"1".equals(lstSearch.get(0).getStatus())) {
										Error += "TÀI SẢN TẠI DÒNG: " + (i+1) + " KHÔNG NẰM Ở ĐƠN VỊ<br>";
									}
								} else {
									Error += "KHÔNG TÌM THẤY TÀI SẢN DÒNG: " + (i+1)+"</br>";
								}
								
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}else {
							Error += "TÀI SẢN DÒNG " + (i+1) + " ĐƯỢC KHAI BÁO NHIỀU LẦN<br>";
						}
					} 
				}
				if(Error.trim().length()>0)
				{
					Common.showMessageError(mv, Error);
				}
				else
				{
					int count =0; 
					for(int i =0; i<lstAsset.size(); i++)
					{
						if(Common.isEmpty(lstAsset.get(i).getId()))
						{						
							if(lstAsset.get(i).getAsset().isEmpty() == false)
							{
								
								lstAsset.get(i).setBorrowCoupon(lst.get(0));
								lstAsset.get(i).setInsertDt(Common.getDateCurrent(ParamsConstants.CD_FULL_DATE));
								lstAsset.get(i).setUserInsert(SystemControl.EmployeeCD);
								lstAsset.get(i).setDelete_fg("0");
								lstAsset.get(i).setStatus("1");
								
								BorrowAssetInsertDao insert = new BorrowAssetInsertDao(lstAsset.get(i));
								try {
									insert.excute();
									count++;
	//								lstAsset.get(i).getAsset().setStatus("2");
	//								AssetGeneralUpdateStatusDao updateStt = new AssetGeneralUpdateStatusDao(lstAsset.get(i).getAsset());
	//								updateStt.excute();
									
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}						
							} 
						} else {
							lstAsset.get(i).setBorrowCoupon(lst.get(0));
							lstAsset.get(i).setUpdateDt(Common.getDateCurrent(ParamsConstants.CD_FULL_DATE));
							lstAsset.get(i).setUserUpdate(SystemControl.EmployeeCD);
							lstAsset.get(i).setDelete_fg("0");
							
							BorrowAssetUpdateDao update = new BorrowAssetUpdateDao(lstAsset.get(i));
							try {
								update.excute();
								count++;
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					
					if(count > 0)
					{
						Common.showMessageNotification(mv, "KHAI BÁO " + count+ " TÀI SẢN CHO MƯỢN" );
					} else {
						Common.showMessageError(mv, "KHÔNG CÓ TÀI SẢN NÀO ĐƯỢC KHAI BÁO" );
					}
				}
				mv.addObject("lstBr", lstAsset);
			}
		}
		
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		mv.setViewName("views/BorrowAssetDeclare.jsp");
		return mv;
	}
	
	boolean isTrung(int i, List<BorrowAssetModel> lstAsset)
	{
		int isr = 0;
		
		for(int j=0;j<lstAsset.size();j++)
		{
			if(lstAsset.get(i).getAsset().getEqual().equals(lstAsset.get(j).getAsset().getEqual()))
			{
				isr++;
			}
		}
		
		if(isr==2) {
			return true;
		}
		
		return false;
	}
	
	@RequestMapping(params = "GetImfor", method = RequestMethod.POST)
	public ModelAndView getImfor(HttpServletRequest request) throws SQLException {
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
		
		
		if(lst.size()==1)
		{
			int limt_row = Integer.parseInt(number_row.trim());
			List<BorrowAssetModel> lstAsset = new ArrayList<>();
			for(int i=1; i<=limt_row; i++)
			{
				BorrowAssetModel ass = new BorrowAssetModel();
				ass.setId(request.getParameter("borrow["+i+"]_cd"));
				ass.getAsset().setRFID(request.getParameter("rfid["+i+"]"));
				ass.getAsset().setModel(request.getParameter("model["+i+"]"));
				ass.getAsset().setSeries(request.getParameter("series["+i+"]"));
				ass.getAsset().setName(request.getParameter("name["+i+"]"));
				ass.getAsset().setDepartment_name(request.getParameter("dept["+i+"]"));
				ass.setStatus(request.getParameter("status["+i+"]"));
				ass.setAsseseries(request.getParameter("asseseries["+i+"]"));
				ass.getAsset().setCompany_CD(lst.get(0).getCmpn_master().getCompany_cd());
				lstAsset.add(ass);
			}
			String Error = "";
			if(lstAsset.size()>0)
			{
				for(int i =0; i<lstAsset.size(); i++)
				{					
					if(lstAsset.get(i).getAsset().isEmpty() == false)
					{
						AssetGeneralSelectDao assSelect = new AssetGeneralSelectDao(lstAsset.get(i).getAsset());
						
						try {
							List<AssetGeneralModel> lstSearch = assSelect.excute();
							if(lstSearch.size()==1)
							{
								lstAsset.get(i).setAsset(lstSearch.get(0));
							} else {
								Error += "KHÔNG TÌM THẤY TÀI SẢN DÒNG: " + (i+1);
							}
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} 			
				}
				if(Error.trim().length()>0)
				{
					Common.showMessageError(mv, Error);
				}
				mv.addObject("lstBr", lstAsset);
			}
		}
		AssetGeneralFormSearch form = new AssetGeneralFormSearch();
		form.setDepartment_cd(lst.get(0).getDept_client().getDept_cd());
		form.setCompany_CD((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
		AssetGeneralIncludeSelectDao AssetSelectDaoSearch = new AssetGeneralIncludeSelectDao(form);
		List<AssetGeneralModel> lstAsset= AssetSelectDaoSearch.excute();
		
		System.out.println(lst.get(0).getDept_client().getDept_cd());
		
		mv.addObject("listAssetSearch", lstAsset);
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		mv.setViewName("views/BorrowAssetDeclare.jsp");
		return mv;
	}
	
	@RequestMapping(params = "back",method = RequestMethod.POST)
	public String back()
	{
		return "redirect:/BorrowCouponManagement";
	}
	

}
