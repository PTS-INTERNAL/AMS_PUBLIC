package com.ams.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.AssetGeneralSelectDao;
import com.ams.dao.BorrowAssetInsertDao;
import com.ams.dao.CompanySelectDao;
import com.ams.dao.LoanAssetInsertDao;
import com.ams.model.AssetGeneralFormSearch;
import com.ams.model.AssetGeneralModel;
import com.ams.model.BorrowAssetModel;
import com.ams.model.CompanyModel;
import com.ams.model.Department;
import com.ams.model.LoanAssetModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;

@Controller
@RequestMapping("/LoanAssetRegister")
public class LoanAssetRegister {

	String TITLE = "MÀN HÌNH ĐĂNG KÝ  MƯỢN TÀI SẢN SẢN";
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		String error = "";
		CompanyModel cmpnModel = new CompanyModel();
		cmpnModel.setCompany_cd(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		CompanySelectDao companySelectDao = new CompanySelectDao(cmpnModel);
		try {
			List<CompanyModel> lstcmp = companySelectDao.excute();
			if(lstcmp.size()>0)
			{
				LoanAssetModel asset = new  LoanAssetModel();
				//mv.addObject("lstCompany", lstcmp);
				asset.setCmpn_master(lstcmp.get(0));
				mv.addObject("asset", asset);
			}
			else
			{
				error = error + "Không tìm thấy công ty nào";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			error = error + e.toString() + "<br>Lỗi tìm kiếm công ty <br>";
		}
		
		String cmpn_cd = (String) request.getSession().getAttribute(SessionConstants.SESSION_USER_CMPN_CD);
		CompanyModel cm = new CompanyModel();
		cm.setCompany_cd(cmpn_cd);
	    CompanySelectDao cmCompanySelectDao = new CompanySelectDao(cm);
	    try {
			List<CompanyModel> lst = cmCompanySelectDao.excute();
			if(lst.size()>0)
			{
				mv.addObject("loan_cmpn_cd", lst.get(0).getCompany_cd());
				mv.addObject("loan_cmpn_na", lst.get(0).getCompany_name());
			}
		} catch (SQLException e) {
			error = error+"Không tìm thấy công ty mượn tài sản<br>";
		}
		if(error.trim().length()>0)
		{
			mv.addObject(ParamsConstants.MESSAGE_ERROR, error);
		}
		mv.setViewName("views/LoanAssetRegister.jsp");
		return mv;
	}
	
	@RequestMapping(params = "save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request) throws UnsupportedEncodingException
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		
		request.setCharacterEncoding("UTF8");
		
		LoanAssetModel bam = new LoanAssetModel();
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


		
		/*
		 * XÉT 2 TRƯỜNG HỢP
		 * I-MƯỢN TÀI SẢN CÙNG 1 CÔNG TY
		 * II-MƯỢN TÀI SẢN KHÁC CÔNG TY
		 */
		
			AssetGeneralFormSearch form = new AssetGeneralFormSearch();
			form.setRFID(bam.getAsset().getRFID());
			form.setModel(bam.getAsset().getModel());
			form.setDepartment_cd(bam.getDept_client().getDept_cd());
			form.setSeries(bam.getAsset().getSeries());
			form.setCompany_CD(bam.getCmpn_client().getCompany_cd());
			AssetGeneralSelectDao assetGeneralSelectDao = new AssetGeneralSelectDao(form);
			
			try {
				List<AssetGeneralModel> lst = assetGeneralSelectDao.excute();
				if(lst.size()==0)
				{
					mv.addObject(ParamsConstants.MESSAGE_ERROR, "Không tìm thấy thông tin tài sản");
					mv.addObject("asset", bam);
				}
				else
				{
					if(lst.size() >1)
					{
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "Tìm thấy nhiều hơn 1 tài sản cùng loại");
						mv.addObject("asset", bam);
					}
					else
					{
						if(lst.size() == 1)
						{
							if(bam.getAsset().getRFID()==null || bam.getAsset().getRFID().length()==0)
							{
								bam.getAsset().setRFID(lst.get(0).getRFID());
								
							}
							bam.getAsset().setId(lst.get(0).getId());
							LoanAssetInsertDao borrowAssetInsertDao = new LoanAssetInsertDao(bam);
							borrowAssetInsertDao.excute();
							mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "ĐĂNG KÝ  MƯỢN THÀNH CÔNG");
						}
					}
				}
			} catch (SQLException e) {
	
				mv.addObject(ParamsConstants.MESSAGE_ERROR, "Lỗi trong lúc tìm dữ liệu tài sản");
				mv.addObject("asset", bam);
				e.printStackTrace();
			}
		
		
		mv.setViewName("views/LoanAssetRegister.jsp");
		return mv;
	}
	
	@RequestMapping(params = "back", method = RequestMethod.POST)
	public String back()
	{
		return "redirect:/LoanAssetManagement";
	}
}
