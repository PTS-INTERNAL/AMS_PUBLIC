package com.ams.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.BorrowAssetApproveDao;
import com.ams.dao.BorrowAssetConfirmClientDao;
import com.ams.dao.BorrowAssetConfirmReturnDao;
import com.ams.dao.BorrowAssetReturnClientDao;
import com.ams.dao.BorrowAssetSelectDao;
import com.ams.dao.CompanySelectDao;
import com.ams.dao.LoanAssetConfirmReturnClientDao;
import com.ams.dao.LoanAssetInsertDao;
import com.ams.dao.LoanAssetSelectDao;
import com.ams.dao.UserSelectDao;
import com.ams.model.BorrowAssetModel;
import com.ams.model.CompanyModel;
import com.ams.model.LoanAssetModel;
import com.ams.model.UserModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;

@Controller
@RequestMapping("/BorrowAssetConfirmReturn")
public class BorrowAssetConfirmReturnController {
	String TITLE = "MÀN HÌNH XÁC NHẬN TRẢ TÀI SẢN CHO MƯỢN";
	@RequestMapping(params="view_asset", method=RequestMethod.POST)
	public ModelAndView init(HttpServletRequest request)
	{
		ModelAndView mv =new ModelAndView();
		String BorrowCD = request.getParameter("borrow_cd");
		BorrowAssetModel br = new BorrowAssetModel();
		br.setId(BorrowCD);
		br.getCmpn_master().setCompany_cd(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		BorrowAssetSelectDao selectBorrow = new BorrowAssetSelectDao(br);
		List<BorrowAssetModel> lstBr;
		try {
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
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		mv.setViewName("views/BorrowAssetConfirmReturn.jsp");
		return mv;
	}
	
	@RequestMapping(params="confirm", method=RequestMethod.POST)
	public ModelAndView approve(HttpServletRequest request)
	{
		ModelAndView mv =new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		String borrow_cd = request.getParameter("borrow_cd");
		BorrowAssetModel br = new BorrowAssetModel();
		br.setId(borrow_cd);
		br.setConfirmDt(Common.getDateCurrent("YYYY/MM/dd"));
		br.setUserConfirm(Common.getSessionValue(request, SessionConstants.SESSION_USER_ID));
		BorrowAssetConfirmReturnDao approveDao = new BorrowAssetConfirmReturnDao(br);
		try {
			
			approveDao.excute();
			
			BorrowAssetSelectDao SelectBorrow = new BorrowAssetSelectDao(br);
			BorrowAssetModel borrowAssert =  SelectBorrow.excute().get(0);
			LoanAssetModel loan = new LoanAssetModel();
			
			loan.setCmpn_master(borrowAssert.getCmpn_client());
			loan.setCmpn_client(borrowAssert.getCmpn_master());
			loan.setAsset(borrowAssert.getAsset());
			loan.setStatus("4");
			LoanAssetSelectDao loanSelect = new LoanAssetSelectDao(loan);
			List<LoanAssetModel> lstLoan = loanSelect.excute();
			if(lstLoan.size()==1)
			{
				LoanAssetModel assetLoan = new  LoanAssetModel();
				assetLoan.setId(lstLoan.get(0).getId());
				LoanAssetConfirmReturnClientDao confirm  = new LoanAssetConfirmReturnClientDao(assetLoan);
				confirm.excute();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("redirect:/BorrowAssetManagement");
		return mv;
	}
	
	@RequestMapping(params="disApprove", method=RequestMethod.POST)
	public ModelAndView disApprove(HttpServletRequest request)
	{
		ModelAndView mv =new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		mv.setViewName("views/BorrowAssetApprove.jsp");
		return mv;
	}
}
