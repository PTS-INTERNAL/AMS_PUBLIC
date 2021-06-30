package com.ams.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ams.dao.CompanyInsertDao;
import com.ams.dao.CompanySelectDao;
import com.ams.dao.CompanyUpdateDao;
import com.ams.dao.CompanyUpdateDeleteDao;
import com.ams.dao.UserDeleteDao;
import com.ams.model.CompanyForm;
import com.ams.model.CompanyModel;
import com.ams.util.AuthenticationLogin;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.RoleCommon;
import com.ams.util.ServicesConstants;
import com.ams.util.SessionConstants;
import com.ams.util.UrlCommon;

@Controller
@RequestMapping("/CompanyImformationView")
public class CompanyImformationViewController {
	
	String TITLE = "MÀN HÌNH XEM THÔNG TIN CÔNG TY";
	
	@RequestMapping(params="view", method=RequestMethod.POST)
	public ModelAndView init(HttpServletRequest request, RedirectAttributes redirect)
	{		
		ModelAndView mv = new ModelAndView();
		AuthenticationLogin auth = new AuthenticationLogin(request);
		if(auth.isLogin())
		{
			String user_cd = (String) request.getSession().getAttribute(SessionConstants.SESSION_USER_ID);
			String company_cd = (String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD);
			boolean isPermission = auth.CheckPermissionIsTrue(company_cd, user_cd, RoleCommon.R_ACCESS, ServicesConstants.COMPANY_MANAGER);
			//Kiểm tra quyền hạn
			if(true)
			{
				mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
				String cmpn_cd = request.getParameter("cmpn_cd");
				CompanyModel cmpn = new CompanyModel();
				cmpn.setCompany_cd(cmpn_cd);
				CompanySelectDao selectCompany = new CompanySelectDao(cmpn);
				try {
					List<CompanyModel> lstCompany = selectCompany.excute();
					if(lstCompany.size()>0)
					{
						mv.addObject("cmpn", lstCompany.get(0));
					}
					else
					{
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "KHÔNG TÌM THẤY CÔNG TY MONG MUỐN");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				mv.setViewName("views/CompanyViewImfor.jsp");
			}
			else
			{
				redirect.addFlashAttribute(ParamsConstants.MESSAGE_ERROR, "BẠN KHÔNG CÓ QUYỀN THỰC HIỆN CHỨC NĂNG NÀY");
				mv.setViewName(UrlCommon.ErrorUrl);
			}	
		}
		else
		{
			
			mv.setViewName(auth.logout());
		}
		return mv;
	}
	
	@RequestMapping(params="update", method = RequestMethod.POST)
	public ModelAndView CompanyInsertAction(RedirectAttributes attr,@RequestParam("file-name") MultipartFile excelFile , ModelMap modelMap, HttpServletRequest request) throws  UnsupportedEncodingException
	{
		ModelAndView mv = new ModelAndView();
		
		//File file = null;
		//file = UploadFileHelper.simpleUpload(excelFile, request, true, "images",request.getSession());
		// Get form
		CompanyForm form = new CompanyForm(request);
		if(Common.isNotEmpty(excelFile.getOriginalFilename()))
		{
			PhotoController photo = new PhotoController();
			String urlImage = photo.simpleUpload(modelMap, request, excelFile);
			form.setFile_name(urlImage);
			//System.out.println("URRL: " + urlImage);
		}
		
		//form.setFile_name(file.getName());
		//Valitaion form
		form.setCmpn_cd(request.getParameter("cmpn_cd"));
		String validationName = "";
		//Tên công ty
		if(form.getName()==null || form.getName().length()==0){
			validationName= validationName + "Tên công ty là bắt  buộc<br>";
		}	
		
		//Tên công ty
		if (form.getShortName()==null || form.getShortName().length()==0){
			validationName=validationName + "Tên viết tắt là bắt buộc<br>";
		}
		//Tên công ty
		if (form.getAddress()==null || form.getAddress().length()==0){
			validationName=validationName + "Địa chỉ công ty là bắt buộc<br>";
		}
		//Validation gặp lỗi
		if(validationName.trim().length()>0)
		{
			mv.addObject(ParamsConstants.MESSAGE_ERROR, validationName);
			return mv;
		}
		else
		{
			CompanyUpdateDao companyUpdate  = new CompanyUpdateDao(form);
			try {
				companyUpdate.excute();
				mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "CẬP NHẬT THÔNG TIN CÔNG TY THÀNH CÔNG");
				CompanyModel cmpn = new CompanyModel();
				cmpn.setCompany_cd(form.getCmpn_cd());
				CompanySelectDao selectCompany = new CompanySelectDao(cmpn);
				try {
					List<CompanyModel> lstCompany = selectCompany.excute();
					if(lstCompany.size()>0)
					{
						mv.addObject("cmpn", lstCompany.get(0));
					}
					else
					{
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "KHÔNG TÌM THẤY CÔNG TY MONG MUỐN");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				mv.setViewName("views/CompanyViewImfor.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return mv; 
		}
	}
	
	@RequestMapping(params ="back", method=RequestMethod.POST)
	public String CompanyInsert(ModelMap modelMap, HttpServletRequest request) 
	{
		return "redirect:/CompanyManagement";
	}
	
	
	

}
