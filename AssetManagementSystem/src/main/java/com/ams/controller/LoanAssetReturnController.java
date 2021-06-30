package com.ams.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.BorrowAssetConfirmClientDao;
import com.ams.dao.BorrowAssetReturnClientDao;
import com.ams.dao.BorrowAssetSelectDao;
import com.ams.dao.CompanySelectDao;
import com.ams.dao.LoanAssetConfirmDao;
import com.ams.dao.LoanAssetReturnDao;
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
@RequestMapping("/LoanAssetReturn")
public class LoanAssetReturnController {
	String TITLE = "MÀN HÌNH ĐĂNG KÝ TRẢ TÀI SẢN";
	@RequestMapping(params="view", method=RequestMethod.POST)
	public ModelAndView init(HttpServletRequest request)
	{
		ModelAndView mv =new ModelAndView();
		String LoanCD = request.getParameter("loan_cd");
		LoanAssetModel loan = new LoanAssetModel();
		loan.setId(LoanCD);
		loan.getCmpn_master().setCompany_cd(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		LoanAssetSelectDao selectLoan = new LoanAssetSelectDao(loan);
		List<LoanAssetModel> lstLoan;
		try {
			lstLoan = selectLoan.excute();	
			if(lstLoan.size()==1)
			{
				mv.addObject("borrow", lstLoan.get(0));
			}
			
			LoanAssetModel LoanIndex = lstLoan.get(0);
			CompanyModel comMaster = new CompanyModel();
			comMaster.setCompany_cd(LoanIndex.getCmpn_master().getCompany_cd());
			CompanySelectDao selectCompany = new CompanySelectDao(comMaster);
			List<CompanyModel> lstMaster = selectCompany.excute();
			if(lstMaster.size()==1)
			{
				mv.addObject("master", lstMaster.get(0));
			}
			CompanyModel comClient = new CompanyModel();
			comClient.setCompany_cd(LoanIndex.getCmpn_client().getCompany_cd());
			selectCompany  =new CompanySelectDao(comClient);
			List<CompanyModel> lstClient = selectCompany.excute();
			if(lstClient.size()==1)
			{
				mv.addObject("client", lstClient.get(0));
			}
			
			UserModel user = new UserModel();
			user.setEmployment_CD(LoanIndex.getUserInsert());
			
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
		mv.setViewName("views/LoanAssetReturn.jsp");
		return mv;
	}
	@RequestMapping(params="assetReturn", method=RequestMethod.POST)
	public ModelAndView confirm(HttpServletRequest request)
	{
		ModelAndView mv =new ModelAndView();
		//Lấy mã mượn tài sản
		String LoanCD = request.getParameter("loan_cd");
		LoanAssetModel loan = new LoanAssetModel();
		loan.setId(LoanCD);
		//Gán giá trị công ty cho mã mượn
		loan.getCmpn_master().setCompany_cd(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		LoanAssetSelectDao selectLoan = new LoanAssetSelectDao(loan);
		List<LoanAssetModel> lstLoan;
		try {
			lstLoan = selectLoan.excute();	
			//Có tồn tại duy nhất một tài sản
			if(lstLoan.size()==1)
			{
				LoanAssetModel loanAss = lstLoan.get(0);
				loanAss.setUserReturn(Common.getSessionValue(request, SessionConstants.SESSION_USER_ID));
				loanAss.setReturnDt(Common.getDateCurrent("YYYY/MM/dd"));
				LoanAssetReturnDao loanReturn =  new LoanAssetReturnDao(loanAss);
				loanReturn.excute();
				
				BorrowAssetModel brow = new BorrowAssetModel();
				brow.setAsset(loanAss.getAsset());
				brow.setDept_client(loanAss.getDept_master());
				brow.setDept_master(loanAss.getDept_client());
				
				BorrowAssetSelectDao brSelect = new BorrowAssetSelectDao(brow);
				List<BorrowAssetModel> lstBr = brSelect.excute();
				if(lstBr.size()==1)
				{
					lstBr.get(0).setUserReturn(Common.getSessionValue(request, SessionConstants.SESSION_USER_ID));
					lstBr.get(0).setReturnDt(Common.getDateCurrent("YYYY/MM/dd"));
					BorrowAssetReturnClientDao brClinet = new BorrowAssetReturnClientDao(lstBr.get(0));
					brClinet.excute();
				}
				else
				{
					//System.out.println(lstBr.size() + "Sai rồi");
				}
				
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		mv.setViewName("redirect:/LoanAssetManagement");
		return mv;
	}
	
	@RequestMapping(params = "back", method = RequestMethod.POST)
	public ModelAndView back(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/LoanAssetManagement");
			
		return mv;
	}
}
