package com.ams.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ams.dao.CompanyInsertDao;
import com.ams.model.CompanyForm;
import com.ams.util.ParamsConstants;
import com.sun.net.httpserver.HttpServer;

@Controller
@RequestMapping("CompanyInsetInit")
public class CompanyInsertController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, "MÀN HÌNH THÊM DOANH NGHIỆP");
		mv.setViewName("views/CompanyInsert.jsp");
		return mv;
	}
	@RequestMapping(params="save", method = RequestMethod.POST)
	public String CompanyInsertAction(RedirectAttributes attr,@RequestParam("file-name") MultipartFile excelFile , ModelMap modelMap, HttpServletRequest request) throws  UnsupportedEncodingException
	{
		
		//File file = null;
		//file = UploadFileHelper.simpleUpload(excelFile, request, true, "images",request.getSession());
		// Get form
		PhotoController photo = new PhotoController();
		String urlImage = photo.simpleUpload(modelMap, request, excelFile);
		CompanyForm form = new CompanyForm(request);
		form.setFile_name(urlImage);
		//System.out.println("URRL: " + urlImage);
		//form.setFile_name(file.getName());
		//Valitaion form
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
			attr.addFlashAttribute(ParamsConstants.MESSAGE_ERROR, validationName);
			return "redirect:/CompanyInsetInit";
		}
		else
		{
			CompanyInsertDao companyInstert  = new CompanyInsertDao(form);
			try {
				companyInstert.excute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "redirect:/CompanyManagement"; 
		}
	}
	@RequestMapping(params ="back", method=RequestMethod.POST)
	public String CompanyInsert(ModelMap modelMap, HttpServletRequest request) 
	{
		return "redirect:/CompanyManagement";
	}

}
