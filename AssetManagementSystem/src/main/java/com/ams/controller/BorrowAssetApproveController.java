package com.ams.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.COMM_FAILURE;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.BorrowAssetApproveDao;
import com.ams.dao.BorrowAssetSelectDao;
import com.ams.dao.BorrowAssetUpdateDao;
import com.ams.dao.CompanySelectDao;
import com.ams.dao.InventoryCheckingResultSelectDao;
import com.ams.dao.LoanAssetInsertDao;
import com.ams.dao.UserSelectDao;
import com.ams.helper.PdfChoMuon;
import com.ams.helper.PdfInventorySession;
import com.ams.model.BorrowAssetModel;
import com.ams.model.CompanyModel;
import com.ams.model.ExportBorrowMove;
import com.ams.model.InventoryCheckingRealtimeModel;
import com.ams.model.LoanAssetModel;
import com.ams.model.UserModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;


@Controller
@RequestMapping("/BorrowAssetApprove")
public class BorrowAssetApproveController {
	String TITLE = "MÀN HÌNH XÁC PHÊ DUYỆT CHO MƯỢN TÀI SẢN";
	@RequestMapping(params="view", method=RequestMethod.POST)
	public ModelAndView init(HttpServletRequest request)
	{
		ModelAndView mv =new ModelAndView();
		String BorrowCD = request.getParameter("borrow_cd");
		mv.addObject("borrow_cd_exp", BorrowCD);
		BorrowAssetModel br = new BorrowAssetModel();
		br.setId(BorrowCD);
		br.getCmpn_master().setCompany_cd(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		BorrowAssetSelectDao selectBorrow = new BorrowAssetSelectDao(br);
		List<BorrowAssetModel> lstBr;
		try {
			lstBr = selectBorrow.excute();	
			if(lstBr.size()==1)
			{
				//mv.addObject("borrow", lstBr.get(0));
				
				BorrowAssetModel brSearch = new BorrowAssetModel();
				brSearch.setNumber_on(lstBr.get(0).getNumber_on());
				brSearch.getCmpn_master().setCompany_cd(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
				BorrowAssetSelectDao selectSearch = new BorrowAssetSelectDao(brSearch);
				List<BorrowAssetModel> lstBrSearch =null;
				lstBrSearch = selectSearch.excute();
				if(lstBrSearch.size() > 0)
				{
					mv.addObject("lstBr", lstBrSearch);
					mv.addObject("reason", lstBrSearch.get(0).getReason());
					mv.addObject("number_no", lstBrSearch.get(0).getNumber_on());
					
					BorrowAssetModel brIndex = lstBrSearch.get(0);
					CompanyModel comMaster = new CompanyModel();
					comMaster.setCompany_cd(brIndex.getCmpn_master().getCompany_cd());
					CompanySelectDao selectCompany = new CompanySelectDao(comMaster);
					List<CompanyModel> lstMaster = selectCompany.excute();
					if(lstMaster.size()==1)
					{
						mv.addObject("master", lstMaster.get(0));
					}
					
					CompanyModel comClient = new CompanyModel();
					comClient.setCompany_cd(brIndex.getCmpn_client().getCompany_cd());
					selectCompany  =new CompanySelectDao(comClient);
					List<CompanyModel> lstClient = selectCompany.excute();
					if(lstClient.size()==1)
					{
						mv.addObject("client", lstClient.get(0));
					}
					
					UserModel user = new UserModel();
					user.setEmployment_CD(brIndex.getUserInsert());
					
					UserSelectDao SelectUser  =  new UserSelectDao(user);
					
					user = SelectUser.excute().get(0);
					
					
					mv.addObject("userInsert", user);
					
					
					UserModel userApprove = new UserModel();
					userApprove.setEmployee_cd(Common.getSessionValue(request, SessionConstants.SESSION_USER_ID));
					UserSelectDao selectUserApprove = new UserSelectDao(userApprove);
					userApprove = selectUserApprove.excute().get(0);
					mv.addObject("userApprove", userApprove);
					mv.addObject("dateApprove", Common.getDateCurrent("YYYY/MM/dd"));
				}
			}
			
			
					
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		mv.setViewName("views/BorrowAssetApprove.jsp");
		return mv;
	}
	
	@RequestMapping(params="approve", method=RequestMethod.POST)
	public ModelAndView approve(HttpServletRequest request)
	{
		ModelAndView mv =new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		String borrow_cd = request.getParameter("borrow_cd");
		mv.addObject("borrow_cd_exp", borrow_cd);
			BorrowAssetModel br = new BorrowAssetModel();
			br.setId(borrow_cd);
			br.setApproveDt(Common.getDateCurrent("YYYY/MM/dd"));
			br.setStatus("2");
			br.setUserApprove(Common.getSessionValue(request, SessionConstants.SESSION_USER_ID));
			BorrowAssetApproveDao approveDao = new BorrowAssetApproveDao(br);
			try {
				approveDao.excute();
				
				BorrowAssetSelectDao SelectBorrow = new BorrowAssetSelectDao(br);
				BorrowAssetModel borrowAssert =  SelectBorrow.excute().get(0);
				LoanAssetModel loan = new LoanAssetModel();
				loan.setCmpn_master(borrowAssert.getCmpn_client());
				loan.setCmpn_client(borrowAssert.getCmpn_master());
				loan.setId(Common.getDateCurrent(ParamsConstants.CD_FULL_FORMAT));
				loan.setDept_client(borrowAssert.getDept_master());
				loan.setDept_master(borrowAssert.getDept_client());
				loan.setAsset(borrowAssert.getAsset());
				loan.setDate_start(borrowAssert.getDate_start());
				loan.setReason(borrowAssert.getReason());
				loan.setDate_end(borrowAssert.getDate_end());
				loan.setInsertDt(Common.getDateCurrent("YYYY/MM/dd"));
				loan.setUserInsert(Common.getSessionValue(request, SessionConstants.SESSION_USER_ID));
				loan.setStatus("1");
				LoanAssetInsertDao insert = new LoanAssetInsertDao(loan);
				insert.excute();
				
				
				BorrowAssetUpdateDao updateBorrow = new BorrowAssetUpdateDao();
				updateBorrow.execute(borrow_cd, "", 2);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String BorrowCD = request.getParameter("borrow_cd");
			BorrowAssetModel bra = new BorrowAssetModel();
			bra.setId(BorrowCD);
			bra.getCmpn_master().setCompany_cd(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
			BorrowAssetSelectDao selectBorrow = new BorrowAssetSelectDao(bra);
			List<BorrowAssetModel> lstBr;
			try {
				lstBr = selectBorrow.excute();	
				if(lstBr.size()==1)
				{
					//mv.addObject("borrow", lstBr.get(0));
					
					BorrowAssetModel brSearch = new BorrowAssetModel();
					brSearch.setNumber_on(lstBr.get(0).getNumber_on());
					brSearch.getCmpn_master().setCompany_cd(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
					BorrowAssetSelectDao selectSearch = new BorrowAssetSelectDao(brSearch);
					List<BorrowAssetModel> lstBrSearch =null;
					lstBrSearch = selectSearch.excute();
					if(lstBrSearch.size() > 0)
					{
						mv.addObject("lstBr", lstBrSearch);
						mv.addObject("reason", lstBrSearch.get(0).getReason());
						mv.addObject("number_no", lstBrSearch.get(0).getNumber_on());
						
						BorrowAssetModel brIndex = lstBrSearch.get(0);
						CompanyModel comMaster = new CompanyModel();
						comMaster.setCompany_cd(brIndex.getCmpn_master().getCompany_cd());
						CompanySelectDao selectCompany = new CompanySelectDao(comMaster);
						List<CompanyModel> lstMaster = selectCompany.excute();
						if(lstMaster.size()==1)
						{
							mv.addObject("master", lstMaster.get(0));
						}
						
						CompanyModel comClient = new CompanyModel();
						comClient.setCompany_cd(brIndex.getCmpn_client().getCompany_cd());
						selectCompany  =new CompanySelectDao(comClient);
						List<CompanyModel> lstClient = selectCompany.excute();
						if(lstClient.size()==1)
						{
							mv.addObject("client", lstClient.get(0));
						}
						
						UserModel user = new UserModel();
						user.setEmployment_CD(brIndex.getUserInsert());
						
						UserSelectDao SelectUser  =  new UserSelectDao(user);
						
						user = SelectUser.excute().get(0);
						
						
						mv.addObject("userInsert", user);
						
						
						UserModel userApprove = new UserModel();
						userApprove.setEmployee_cd(Common.getSessionValue(request, SessionConstants.SESSION_USER_ID));
						UserSelectDao selectUserApprove = new UserSelectDao(userApprove);
						userApprove = selectUserApprove.excute().get(0);
						mv.addObject("userApprove", userApprove);
						mv.addObject("dateApprove", Common.getDateCurrent("YYYY/MM/dd"));
					}
				}
				
				
						
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
			mv.setViewName("views/BorrowAssetApprove.jsp");
			return mv;
		
		
	}
	
	@RequestMapping(params="disApprove", method=RequestMethod.POST)
	public ModelAndView disApprove(HttpServletRequest request)
	{
		ModelAndView mv =new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		String borrow_cd = request.getParameter("borrow_cd");
		mv.addObject("borrow_cd_exp", borrow_cd);
		String reason = request.getParameter("reason");
		BorrowAssetModel br = new BorrowAssetModel();
		br.setId(borrow_cd);
		br.getCmpn_master().setCompany_cd(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		BorrowAssetSelectDao selectBorrow = new BorrowAssetSelectDao(br);
		BorrowAssetUpdateDao updateBorrow = new BorrowAssetUpdateDao();
		List<BorrowAssetModel> lstBr;
		try {
			if (Common.isEmpty(reason)) {
				mv.addObject(ParamsConstants.MESSAGE_ERROR, "Vui lòng cung cấp lý do không duyệt cho mượn");
			} else {
				updateBorrow.execute(borrow_cd, reason.trim(), 6);
			}
			lstBr = selectBorrow.excute();	
			if(lstBr.size()==1)
			{
				mv.addObject("borrow", lstBr.get(0));
			}
			
			BorrowAssetModel brIndex = lstBr.get(0);
			CompanyModel comMaster = new CompanyModel();
			comMaster.setCompany_cd(brIndex.getCmpn_master().getCompany_cd());
			CompanySelectDao selectCompany = new CompanySelectDao(comMaster);
			List<CompanyModel> lstMaster = selectCompany.excute();
			if(lstMaster.size()==1)
			{
				mv.addObject("master", lstMaster.get(0));
			}
			CompanyModel comClient = new CompanyModel();
			comClient.setCompany_cd(brIndex.getCmpn_client().getCompany_cd());
			selectCompany  =new CompanySelectDao(comClient);
			List<CompanyModel> lstClient = selectCompany.excute();
			if(lstClient.size()==1)
			{
				mv.addObject("client", lstClient.get(0));
			}
			
			UserModel user = new UserModel();
			user.setEmployment_CD(brIndex.getUserInsert());
			
			UserSelectDao SelectUser  =  new UserSelectDao(user);
			
			user = SelectUser.excute().get(0);
			
			
				mv.addObject("userInsert", user);
			
			
			UserModel userApprove = new UserModel();
			userApprove.setEmployee_cd(Common.getSessionValue(request, SessionConstants.SESSION_USER_ID));
			UserSelectDao selectUserApprove = new UserSelectDao(userApprove);
			userApprove = selectUserApprove.excute().get(0);
			mv.addObject("userApprove", userApprove);
			mv.addObject("dateApprove", Common.getDateCurrent("YYYY/MM/dd"));
					
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		String BorrowCD = request.getParameter("borrow_cd");
		BorrowAssetModel bra = new BorrowAssetModel();
		bra.setId(BorrowCD);
		bra.getCmpn_master().setCompany_cd(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		BorrowAssetSelectDao selectBorrows = new BorrowAssetSelectDao(bra);
		List<BorrowAssetModel> lstBrs;
		try {
			lstBrs= selectBorrows.excute();	
			if(lstBrs.size()==1)
			{
				//mv.addObject("borrow", lstBr.get(0));
				
				BorrowAssetModel brSearch = new BorrowAssetModel();
				brSearch.setNumber_on(lstBrs.get(0).getNumber_on());
				brSearch.getCmpn_master().setCompany_cd(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
				BorrowAssetSelectDao selectSearch = new BorrowAssetSelectDao(brSearch);
				List<BorrowAssetModel> lstBrSearch =null;
				lstBrSearch = selectSearch.excute();
				if(lstBrSearch.size() > 0)
				{
					mv.addObject("lstBr", lstBrSearch);
					mv.addObject("reason", lstBrSearch.get(0).getReason());
					mv.addObject("number_no", lstBrSearch.get(0).getNumber_on());
					
					BorrowAssetModel brIndex = lstBrSearch.get(0);
					CompanyModel comMaster = new CompanyModel();
					comMaster.setCompany_cd(brIndex.getCmpn_master().getCompany_cd());
					CompanySelectDao selectCompany = new CompanySelectDao(comMaster);
					List<CompanyModel> lstMaster = selectCompany.excute();
					if(lstMaster.size()==1)
					{
						mv.addObject("master", lstMaster.get(0));
					}
					
					CompanyModel comClient = new CompanyModel();
					comClient.setCompany_cd(brIndex.getCmpn_client().getCompany_cd());
					selectCompany  =new CompanySelectDao(comClient);
					List<CompanyModel> lstClient = selectCompany.excute();
					if(lstClient.size()==1)
					{
						mv.addObject("client", lstClient.get(0));
					}
					
					UserModel user = new UserModel();
					user.setEmployment_CD(brIndex.getUserInsert());
					
					UserSelectDao SelectUser  =  new UserSelectDao(user);
					
					user = SelectUser.excute().get(0);
					
					
					mv.addObject("userInsert", user);
					
					
					UserModel userApprove = new UserModel();
					userApprove.setEmployee_cd(Common.getSessionValue(request, SessionConstants.SESSION_USER_ID));
					UserSelectDao selectUserApprove = new UserSelectDao(userApprove);
					userApprove = selectUserApprove.excute().get(0);
					mv.addObject("userApprove", userApprove);
					mv.addObject("dateApprove", Common.getDateCurrent("YYYY/MM/dd"));
				}
			}
			
			
					
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		mv.setViewName("views/BorrowAssetApprove.jsp");
		return mv;
	}
	
	@RequestMapping(params = "back",method = RequestMethod.POST)
	public String back()
	{
		return "redirect:/BorrowAssetManagement";
	}
	
//	@RequestMapping(params = "print", method = RequestMethod.POST)
//	public ModelAndView reportPDF(HttpServletRequest request) {
//		String BorrowCD = request.getParameter("borrow_cd_exp");
//		String code_export = request.getParameter("codeExport");
//		
//		BorrowAssetModel bra = new BorrowAssetModel();
//		bra.setId(BorrowCD);
//		bra.getCmpn_master().setCompany_cd(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
//		BorrowAssetSelectDao selectBorrows = new BorrowAssetSelectDao(bra);
//		List<BorrowAssetModel> lstBrs;
//		List<BorrowAssetModel> lstBrSearch =null;
//		List<CompanyModel> lstMaster = null;
//		List<CompanyModel> lstClient = null;
//		try {
//			lstBrs= selectBorrows.excute();	
//			if(lstBrs.size()==1)
//			{
//				//mv.addObject("borrow", lstBr.get(0));
//				
//				BorrowAssetModel brSearch = new BorrowAssetModel();
//				brSearch.setNumber_on(lstBrs.get(0).getNumber_on());
//				brSearch.getCmpn_master().setCompany_cd(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
//				BorrowAssetSelectDao selectSearch = new BorrowAssetSelectDao(brSearch);
//				
//				lstBrSearch = selectSearch.excute();
//				if(lstBrSearch.size() > 0)
//				{
//					//mv.addObject("lstBr", lstBrSearch);
//					//mv.addObject("reason", lstBrSearch.get(0).getReason());
//					//mv.addObject("number_no", lstBrSearch.get(0).getNumber_on());
//					
//					BorrowAssetModel brIndex = lstBrSearch.get(0);
//					CompanyModel comMaster = new CompanyModel();
//					comMaster.setCompany_cd(brIndex.getCmpn_master().getCompany_cd());
//					CompanySelectDao selectCompany = new CompanySelectDao(comMaster);
//					 lstMaster = selectCompany.excute();
//					if(lstMaster.size()==1)
//					{
//						//mv.addObject("master", lstMaster.get(0));
//					}
//					
//					CompanyModel comClient = new CompanyModel();
//					comClient.setCompany_cd(brIndex.getCmpn_client().getCompany_cd());
//					selectCompany  =new CompanySelectDao(comClient);
//					 lstClient = selectCompany.excute();
//					if(lstClient.size()==1)
//					{
//						//mv.addObject("client", lstClient.get(0));
//					}
//					
//					UserModel user = new UserModel();
//					user.setEmployment_CD(brIndex.getUserInsert());
//					
//					UserSelectDao SelectUser  =  new UserSelectDao(user);
//					
//					user = SelectUser.excute().get(0);
//					
//					
//					
//					
//					
//					UserModel userApprove = new UserModel();
//					userApprove.setEmployee_cd(Common.getSessionValue(request, SessionConstants.SESSION_USER_ID));
//					UserSelectDao selectUserApprove = new UserSelectDao(userApprove);
//					userApprove = selectUserApprove.excute().get(0);
//					
//				}
//			}
//			
//			
//					
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		ExportBorrowMove export = new ExportBorrowMove(lstBrSearch, lstMaster.get(0).getCompany_name(), lstClient.get(0).getCompany_name(), "TẤT CẢ CÁC THIẾT BỊ HOẠT ĐỘNG BÌNH THƯỜNG", code_export);
//		return new ModelAndView(new PdfChoMuon(), "object", export);
//	}
	
}
