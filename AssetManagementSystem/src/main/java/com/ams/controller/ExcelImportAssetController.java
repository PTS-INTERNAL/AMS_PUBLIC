package com.ams.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ams.helper.ExcelHelper;
import com.ams.helper.UploadFileHelper;
import com.ams.model.ExcelFile;
import com.ams.model.Product;
import com.ams.util.AuthenticationLogin;
import com.ams.util.ParamsConstants;
import com.ams.util.RoleCommon;
import com.ams.util.ServicesConstants;
import com.ams.util.SessionConstants;
import com.ams.util.UrlCommon;


@Controller
@RequestMapping(value="excel")
public class ExcelImportAssetController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, RedirectAttributes redirect)
	{
		ModelAndView mv = new ModelAndView();
		AuthenticationLogin auth = new AuthenticationLogin(request);
		if(auth.isLogin())
		{
			String user_cd = (String) request.getSession().getAttribute(SessionConstants.SESSION_USER_ID);
			String company_cd = (String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD);
			boolean isPermission = auth.CheckPermissionIsTrue(company_cd, user_cd, RoleCommon.R_WRITE, ServicesConstants.ASSET_MANAGEMENT);
			//Kiểm tra quyền hạn
			if(isPermission)
			{
				mv.setViewName("views/uploadFile.jsp");	
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
	
	@RequestMapping(value="importexcel", method = RequestMethod.GET)
	public String importexcel(ModelMap modelMap)
	{
		modelMap.put("excelFile", new ExcelFile());
		return "/views/uploadFile.jsp";
	}
	
	@RequestMapping(value="importexcel", method = RequestMethod.POST)
	public String importexcel(@ModelAttribute(value="excelFile") ExcelFile excelFile,  ModelMap modelMap, HttpServletRequest request)
	{
		try
		{
		File file = UploadFileHelper.simpleUpload(excelFile.getFile(), request, true, "images", request.getSession());
		//System.out.println(file.getPath());
		ExcelHelper eh = new ExcelHelper(file.getAbsolutePath());
		modelMap.addAttribute("ten","THINH");
		modelMap.addAttribute("listProducts", eh.readData(Product.class.getName(),"vattu",3,1));
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "/views/display.jsp";
	}

}
