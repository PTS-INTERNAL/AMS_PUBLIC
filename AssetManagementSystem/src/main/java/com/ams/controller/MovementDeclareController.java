package com.ams.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.FormSubmitEvent.MethodType;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.AssetGeneralIncludeSelectDao;
import com.ams.dao.AssetGeneralSelectDao;
import com.ams.dao.BorrowAssetInsertDao;
import com.ams.dao.BorrowAssetSelectDao;
import com.ams.dao.MovementAssetInsertDao;
import com.ams.dao.MovementAssetSelectDao;
import com.ams.dao.MovementAssetUpdateDao;
import com.ams.dao.MovementCouponCountSelectDao;
import com.ams.dao.MovementCouponSelectDao;
import com.ams.dao.MovementCouponUpdateStatusDao;
import com.ams.dao.SystemControlUpdateDao;
import com.ams.dao.borrow.BorrowAssetUpdateDao;
import com.ams.dao.borrow.BorrowAssetUpdateDeleteDao;
import com.ams.dao.borrow.BorrowCouponSelectDao;
import com.ams.dao.borrow.MovementAssetUpdateDeleteDao;
import com.ams.model.AssetGeneralFormSearch;
import com.ams.model.AssetGeneralModel;
import com.ams.model.BorrowAssetModel;
import com.ams.model.BorrowCouponModel;
import com.ams.model.MovementAssetModel;
import com.ams.model.MovementCouponModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;
import com.ams.util.SystemControl;

@Controller
@RequestMapping("MovementDeclare")
public class MovementDeclareController {
	String TITLE = "MÀN HÌNH KHAI BÁO TÀI SẢN ĐIỀU ĐỘNG";

	@RequestMapping(params = "declare", method = RequestMethod.POST)
	public ModelAndView init(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("views/MovementDeclare.jsp");
		mv.addObject("TittleScreen", TITLE);
		String coupon_cd = request.getParameter("coupon_cd");

		// Set view layout
		SystemControl system = new SystemControl(request);
		String number_row = system.getParameter(ParamsConstants.ROW_BORROW_NUMBER);

		mv.addObject("number_row", number_row.trim());

		MovementCouponModel movementCoupon = new MovementCouponModel();

		movementCoupon.setCoupon_cd(coupon_cd);

		MovementCouponSelectDao selct = new MovementCouponSelectDao(movementCoupon);

		try {
			List<MovementCouponModel> lst = selct.excute();

			if (lst.size() == 1) {
				int i = 0;
				if (Common.isNotEmpty(lst.get(i).getDate_start())) {
					try {
						String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_start(), "yyyy-mm-dd",
								"dd/mm/yyyy");
						lst.get(i).setDate_start(dateEndConvert);
					} catch (ParseException e1) {
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
					}
				}
				if (Common.isNotEmpty(lst.get(i).getDate_end_schedule())) {
					try {
						String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_end_schedule(),
								"yyyy-mm-dd", "dd/mm/yyyy");
						lst.get(i).setDate_end_schedule(dateEndConvert);
					} catch (ParseException e1) {
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
					}
				}
				if (Common.isNotEmpty(lst.get(i).getDate_end_real())) {
					try {
						String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_end_real(),
								"yyyy-mm-dd", "dd/mm/yyyy");
						lst.get(i).setDate_end_real(dateEndConvert);
					} catch (ParseException e1) {
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
					}
				}

				mv.addObject("coupon", lst.get(0));
				
				MovementAssetModel mov= new MovementAssetModel();
				mov.setMovementCoupon(lst.get(0));
				
				MovementAssetSelectDao selecMov = new MovementAssetSelectDao(mov);
				List<MovementAssetModel> lstMov = selecMov.excute();
				
				while(lstMov.size() < Integer.parseInt(number_row.trim()))
				{
					lstMov.add(new MovementAssetModel());
				}
				
				
				mv.addObject("lstBr", lstMov);
				
			} else {
				if (lst.size() > 1) {
					mv.addObject(ParamsConstants.MESSAGE_ERROR, "TÌM THẤY HƠN 1 LỆNH ĐIỀU ĐỘNG");
				} else {
					mv.addObject(ParamsConstants.MESSAGE_ERROR, "KHÔNG TIM THẤY LỆNH ĐIỀU ĐỘNG");
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

		return mv;

	}

	@RequestMapping(params = "GetImfor", method = RequestMethod.POST)
	public ModelAndView getImfor(HttpServletRequest request) throws SQLException {
		ModelAndView mv = new ModelAndView("views/MovementDeclare.jsp");
		mv.addObject("TittleScreen", TITLE);
		String coupon_cd = request.getParameter("coupon_cd");

		// Set view layout
		SystemControl system = new SystemControl(request);
		String number_row = system.getParameter(ParamsConstants.ROW_BORROW_NUMBER);

		mv.addObject("number_row", number_row.trim());

		MovementCouponModel movementCoupon = new MovementCouponModel();

		movementCoupon.setCoupon_cd(coupon_cd);

		MovementCouponSelectDao selct = new MovementCouponSelectDao(movementCoupon);

		List<MovementCouponModel> lst = null;

		try {
			lst = selct.excute();

			if (lst.size() == 1) {
				int i = 0;
				if (Common.isNotEmpty(lst.get(i).getDate_start())) {
					try {
						String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_start(), "yyyy-mm-dd",
								"dd/mm/yyyy");
						lst.get(i).setDate_start(dateEndConvert);
					} catch (ParseException e1) {
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
					}
				}
				if (Common.isNotEmpty(lst.get(i).getDate_end_schedule())) {
					try {
						String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_end_schedule(),
								"yyyy-mm-dd", "dd/mm/yyyy");
						lst.get(i).setDate_end_schedule(dateEndConvert);
					} catch (ParseException e1) {
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
					}
				}
				if (Common.isNotEmpty(lst.get(i).getDate_end_real())) {
					try {
						String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_end_real(),
								"yyyy-mm-dd", "dd/mm/yyyy");
						lst.get(i).setDate_end_real(dateEndConvert);
					} catch (ParseException e1) {
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
					}
				}

				mv.addObject("coupon", lst.get(0));
			} else {
				if (lst.size() > 1) {
					mv.addObject(ParamsConstants.MESSAGE_ERROR, "TÌM THẤY HƠN 1 LỆNH ĐIỀU ĐỘNG");
				} else {
					mv.addObject(ParamsConstants.MESSAGE_ERROR, "KHÔNG TIM THẤY LỆNH ĐIỀU ĐỘNG");
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (lst.size() == 1) {
			int limt_row = Integer.parseInt(number_row.trim());

			List<MovementAssetModel> lstAsset = new ArrayList<>();
			for (int i = 1; i <= limt_row; i++) {
				MovementAssetModel ass = new MovementAssetModel();
				ass.setId(request.getParameter("borrow[" + i + "]_cd"));
				ass.getAsset().setRFID(request.getParameter("rfid[" + i + "]"));
				ass.getAsset().setModel(request.getParameter("model[" + i + "]"));
				ass.getAsset().setSeries(request.getParameter("series[" + i + "]"));
				ass.getAsset().setName(request.getParameter("name[" + i + "]"));
				ass.getAsset().setDepartment_name(request.getParameter("dept[" + i + "]"));
				ass.setStatus(request.getParameter("status[" + i + "]"));
				ass.setAsseseries(request.getParameter("asseseries[" + i + "]"));
				ass.setOuter(request.getParameter("outer[" + i + "]"));
				ass.getAsset().setCompany_CD(lst.get(0).getCmpn_master().getCompany_cd());
				lstAsset.add(ass);
			}
			String Error = "";
			if (lstAsset.size() > 0) {
				for (int i = 0; i < lstAsset.size(); i++) {

					if (lstAsset.get(i).getAsset().isEmpty() == false) {
						if ("on".equals(lstAsset.get(i).getOuter()) == false) {
							
							lstAsset.get(i).getAsset().setDepartment_cd("");
							AssetGeneralSelectDao assSelect = new AssetGeneralSelectDao(lstAsset.get(i).getAsset());

							try {
								List<AssetGeneralModel> lstSearch = assSelect.excute();
								if (lstSearch.size() == 1) {
									if ("1".equals(lstSearch.get(0).getStatus())) {
										lstAsset.get(i).setAsset(lstSearch.get(0));
									} else {
										Error += "KHÔNG TÌM THẤY TÀI SẢN DÒNG: " + (i + 1) + " KHÔNG NẰM Ở ĐƠN VỊ<br>";
									}
								} else {
									Error += "KHÔNG TÌM THẤY TÀI SẢN DÒNG: " + (i + 1) + "<br>";
								}

							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
				if (Error.trim().length() > 0) {
					Common.showMessageError(mv, Error);
				}
				mv.addObject("lstBr", lstAsset);
			}
		}
//		AssetGeneralFormSearch form = new AssetGeneralFormSearch();
//		form.setDepartment_cd(lst.get(0).getDept_client().getDept_cd());
//		form.setCompany_CD((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
//		AssetGeneralIncludeSelectDao AssetSelectDaoSearch = new AssetGeneralIncludeSelectDao(form);
//		List<AssetGeneralModel> lstAsset= AssetSelectDaoSearch.excute();
//		
//		System.out.println(lst.get(0).getDept_client().getDept_cd());
//		
//		mv.addObject("listAssetSearch", lstAsset);
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
//		mv.setViewName("views/BorrowAssetDeclare.jsp");
		return mv;
	}
	
	
	@RequestMapping(params = "updateRowCount", method = RequestMethod.POST)
	public ModelAndView updateRow(HttpServletRequest request) throws SQLException {
		ModelAndView mv = new ModelAndView("views/MovementDeclare.jsp");
		mv.addObject("TittleScreen", TITLE);
		String coupon_cd = request.getParameter("coupon_cd");
		String num = request.getParameter("count_row");
		if(Common.isNotEmpty(num)) {
			int so  = Integer.parseInt(num);
			
			if(so>0)
			{
				SystemControlUpdateDao update = new SystemControlUpdateDao("ROW_BORROW_NUMBER", so+"");
				update.excute();
			}
		}
		// Set view layout
		SystemControl system = new SystemControl(request);
		String number_row = system.getParameter(ParamsConstants.ROW_BORROW_NUMBER);

		mv.addObject("number_row", number_row.trim());

		MovementCouponModel movementCoupon = new MovementCouponModel();

		movementCoupon.setCoupon_cd(coupon_cd);

		MovementCouponSelectDao selct = new MovementCouponSelectDao(movementCoupon);

		List<MovementCouponModel> lst = null;

		try {
			lst = selct.excute();

			if (lst.size() == 1) {
				int i = 0;
				if (Common.isNotEmpty(lst.get(i).getDate_start())) {
					try {
						String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_start(), "yyyy-mm-dd",
								"dd/mm/yyyy");
						lst.get(i).setDate_start(dateEndConvert);
					} catch (ParseException e1) {
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
					}
				}
				if (Common.isNotEmpty(lst.get(i).getDate_end_schedule())) {
					try {
						String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_end_schedule(),
								"yyyy-mm-dd", "dd/mm/yyyy");
						lst.get(i).setDate_end_schedule(dateEndConvert);
					} catch (ParseException e1) {
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
					}
				}
				if (Common.isNotEmpty(lst.get(i).getDate_end_real())) {
					try {
						String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_end_real(),
								"yyyy-mm-dd", "dd/mm/yyyy");
						lst.get(i).setDate_end_real(dateEndConvert);
					} catch (ParseException e1) {
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
					}
				}

				mv.addObject("coupon", lst.get(0));
			} else {
				if (lst.size() > 1) {
					mv.addObject(ParamsConstants.MESSAGE_ERROR, "TÌM THẤY HƠN 1 LỆNH ĐIỀU ĐỘNG");
				} else {
					mv.addObject(ParamsConstants.MESSAGE_ERROR, "KHÔNG TIM THẤY LỆNH ĐIỀU ĐỘNG");
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (lst.size() == 1) {
			int limt_row = Integer.parseInt(number_row.trim());

			List<MovementAssetModel> lstAsset = new ArrayList<>();
			for (int i = 1; i <= limt_row; i++) {
				MovementAssetModel ass = new MovementAssetModel();
				ass.setId(request.getParameter("borrow[" + i + "]_cd"));
				ass.getAsset().setRFID(request.getParameter("rfid[" + i + "]"));
				ass.getAsset().setModel(request.getParameter("model[" + i + "]"));
				ass.getAsset().setSeries(request.getParameter("series[" + i + "]"));
				ass.getAsset().setName(request.getParameter("name[" + i + "]"));
				ass.getAsset().setDepartment_name(request.getParameter("dept[" + i + "]"));
				ass.setStatus(request.getParameter("status[" + i + "]"));
				ass.setAsseseries(request.getParameter("asseseries[" + i + "]"));
				ass.setOuter(request.getParameter("outer[" + i + "]"));
				ass.getAsset().setCompany_CD(lst.get(0).getCmpn_master().getCompany_cd());
				lstAsset.add(ass);
			}
			String Error = "";
			if (lstAsset.size() > 0) {
				for (int i = 0; i < lstAsset.size(); i++) {

					if (lstAsset.get(i).getAsset().isEmpty() == false) {
						if ("on".equals(lstAsset.get(i).getOuter()) == false) {
							
							lstAsset.get(i).getAsset().setDepartment_cd("");
							AssetGeneralSelectDao assSelect = new AssetGeneralSelectDao(lstAsset.get(i).getAsset());

							try {
								List<AssetGeneralModel> lstSearch = assSelect.excute();
								if (lstSearch.size() == 1) {
									if ("1".equals(lstSearch.get(0).getStatus())) {
										lstAsset.get(i).setAsset(lstSearch.get(0));
									} else {
										Error += "KHÔNG TÌM THẤY TÀI SẢN DÒNG: " + (i + 1) + " KHÔNG NẰM Ở ĐƠN VỊ<br>";
									}
								} else {
									Error += "KHÔNG TÌM THẤY TÀI SẢN DÒNG: " + (i + 1) + "<br>";
								}

							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
				if (Error.trim().length() > 0) {
					Common.showMessageError(mv, Error);
				}
				mv.addObject("lstBr", lstAsset);
			}
		}
//		AssetGeneralFormSearch form = new AssetGeneralFormSearch();
//		form.setDepartment_cd(lst.get(0).getDept_client().getDept_cd());
//		form.setCompany_CD((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
//		AssetGeneralIncludeSelectDao AssetSelectDaoSearch = new AssetGeneralIncludeSelectDao(form);
//		List<AssetGeneralModel> lstAsset= AssetSelectDaoSearch.excute();
//		
//		System.out.println(lst.get(0).getDept_client().getDept_cd());
//		
//		mv.addObject("listAssetSearch", lstAsset);
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
//		mv.setViewName("views/BorrowAssetDeclare.jsp");
		return mv;
	}


	@RequestMapping(params = "save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request) throws SQLException {
		ModelAndView mv = new ModelAndView("views/MovementDeclare.jsp");
		mv.addObject("TittleScreen", TITLE);
		String coupon_cd = request.getParameter("coupon_cd");

		// Set view layout
		SystemControl system = new SystemControl(request);
		String number_row = system.getParameter(ParamsConstants.ROW_BORROW_NUMBER);

		mv.addObject("number_row", number_row.trim());

		MovementCouponModel movementCoupon = new MovementCouponModel();

		movementCoupon.setCoupon_cd(coupon_cd);

		MovementCouponSelectDao selct = new MovementCouponSelectDao(movementCoupon);

		List<MovementCouponModel> lst = null;

		try {
			lst = selct.excute();

			if (lst.size() == 1) {
				int i = 0;
				if (Common.isNotEmpty(lst.get(i).getDate_start())) {
					try {
						String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_start(), "yyyy-mm-dd",
								"dd/mm/yyyy");
						lst.get(i).setDate_start(dateEndConvert);
					} catch (ParseException e1) {
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
					}
				}
				if (Common.isNotEmpty(lst.get(i).getDate_end_schedule())) {
					try {
						String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_end_schedule(),
								"yyyy-mm-dd", "dd/mm/yyyy");
						lst.get(i).setDate_end_schedule(dateEndConvert);
					} catch (ParseException e1) {
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
					}
				}
				if (Common.isNotEmpty(lst.get(i).getDate_end_real())) {
					try {
						String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_end_real(),
								"yyyy-mm-dd", "dd/mm/yyyy");
						lst.get(i).setDate_end_real(dateEndConvert);
					} catch (ParseException e1) {
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
					}
				}
				String status1 = request.getParameter("status_asset"); 
				lst.get(0).setApprove_comment(status1);
				mv.addObject("coupon", lst.get(0));
			} else {
				if (lst.size() > 1) {
					mv.addObject(ParamsConstants.MESSAGE_ERROR, "TÌM THẤY HƠN 1 LỆNH ĐIỀU ĐỘNG");
				} else {
					mv.addObject(ParamsConstants.MESSAGE_ERROR, "KHÔNG TIM THẤY LỆNH ĐIỀU ĐỘNG");
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (lst.size() == 1) {
			int limt_row = Integer.parseInt(number_row.trim());

			List<MovementAssetModel> lstAsset = new ArrayList<>();
			for (int i = 1; i <= limt_row; i++) {
				MovementAssetModel ass = new MovementAssetModel();
				ass.setId(request.getParameter("borrow[" + i + "]_cd"));
				ass.getAsset().setRFID(request.getParameter("rfid[" + i + "]"));
				ass.getAsset().setModel(request.getParameter("model[" + i + "]"));
				ass.getAsset().setSeries(request.getParameter("series[" + i + "]"));
				ass.getAsset().setName(request.getParameter("name[" + i + "]"));
				ass.getAsset().setDepartment_name(request.getParameter("dept[" + i + "]"));
				ass.setStatus(request.getParameter("status[" + i + "]"));
				ass.setAsseseries(request.getParameter("asseseries[" + i + "]"));
				ass.setOuter(request.getParameter("outer[" + i + "]"));
				ass.getAsset().setCompany_CD(lst.get(0).getCmpn_master().getCompany_cd());
				lstAsset.add(ass);
			}
			String Error = "";
			if (lstAsset.size() > 0) {
				for (int i = 0; i < lstAsset.size(); i++) {
					if (lstAsset.get(i).getAsset().isEmpty() == false) {
						if ("on".equals(lstAsset.get(i).getOuter()) == false) {
							lstAsset.get(i).getAsset().setDepartment_cd(lst.get(0).getDept_master().getDept_cd());
							AssetGeneralSelectDao assSelect = new AssetGeneralSelectDao(lstAsset.get(i).getAsset());

							try {
								List<AssetGeneralModel> lstSearch = assSelect.excute();
								if (lstSearch.size() == 1) {
									if ("1".equals(lstSearch.get(0).getStatus())) {
										lstAsset.get(i).setAsset(lstSearch.get(0));
									} else {
										Error += "KHÔNG TÌM THẤY TÀI SẢN DÒNG: " + (i + 1) + " KHÔNG NẰM Ở ĐƠN VỊ<br>";
									}
								} else {
									Error += "KHÔNG TÌM THẤY TÀI SẢN DÒNG: " + (i + 1) + "<br>";
								}

							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
				if (Error.trim().length() > 0) {
					Common.showMessageError(mv, Error);
				} else 
				{
					int count =0; 
					for(int i =0; i<lstAsset.size(); i++)
					{
						if(Common.isEmpty(lstAsset.get(i).getId()))
						{						
							//Thêm mới
							if(lstAsset.get(i).getAsset().isEmpty() == false)
							{
								
								lstAsset.get(i).setMovementCoupon(lst.get(0));
								lstAsset.get(i).setInsertDt(Common.getDateCurrent(ParamsConstants.CD_FULL_DATE));
								lstAsset.get(i).setUserInsert(SystemControl.EmployeeCD);
								lstAsset.get(i).setDelete_fg("0");
								lstAsset.get(i).setStatus("1");
								lstAsset.get(i).setId(Common.getDateCurrent("YYYYMMddHHmmSS"));
								
								MovementAssetInsertDao insert = new MovementAssetInsertDao(lstAsset.get(i));
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
							//update
							lstAsset.get(i).setMovementCoupon(lst.get(0));
							lstAsset.get(i).setUpdateDt(Common.getDateCurrent(ParamsConstants.CD_FULL_DATE));
							lstAsset.get(i).setUserUpdate(SystemControl.EmployeeCD);
							lstAsset.get(i).setDelete_fg("0");
							
							MovementAssetUpdateDao update = new MovementAssetUpdateDao(lstAsset.get(i));
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
						
						String status = request.getParameter("status_asset"); 
						movementCoupon.setApprove_comment(status);
						MovementCouponUpdateStatusDao ex = new MovementCouponUpdateStatusDao(movementCoupon);
						ex.excute();
						
					} else {
						Common.showMessageError(mv, "KHÔNG CÓ TÀI SẢN NÀO ĐƯỢC KHAI BÁO" );
					}
				}
				mv.addObject("lstBr", lstAsset);
			}
		}
	
		
		
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		return mv;
	}

	@RequestMapping(params = "delete", method = RequestMethod.POST)
	public ModelAndView delete(HttpServletRequest request) {
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
			MovementAssetUpdateDeleteDao updateDel = new MovementAssetUpdateDeleteDao(deleteId);
			try {
				updateDel.excute();
				Common.showMessageNotification(mv, "THỰC THI XÓA THÀNH CÔNG");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		mv.addObject("number_row", number_row.trim());
		MovementAssetModel itemBorrow = new MovementAssetModel(); 
		itemBorrow.setMovementCoupon(brCoupn);
		
		MovementAssetSelectDao lstBrrow = new MovementAssetSelectDao(itemBorrow);
		List<MovementAssetModel> lstSearch = new ArrayList<>();	
		try {
			lstSearch = lstBrrow.excute();
			
			if(lstSearch.size()< Integer.parseInt( number_row.trim())) {
				while(lstSearch.size()< Integer.parseInt( number_row.trim()))
				{
					MovementAssetModel item = new MovementAssetModel();
					lstSearch.add(item);
				}
			}
			
			mv.addObject("lstBr", lstSearch);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		mv.setViewName("views/MovementDeclare.jsp");
		return mv;
	}
	@RequestMapping(params = "back", method = RequestMethod.POST)
	public String back() {
		return "redirect:/MovementManagement";
	}

}
