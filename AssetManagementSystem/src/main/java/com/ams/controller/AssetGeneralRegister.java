package com.ams.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Url;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ams.dao.AssetGeneralInsertDao;
import com.ams.dao.UserRoleMappingSelectDao;
import com.ams.model.AssetGeneralModel;
import com.ams.model.PermissionService;
import com.ams.model.UserRoleMapping;
import com.ams.util.AuthenticationLogin;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.RoleCommon;
import com.ams.util.ServicesConstants;
import com.ams.util.SessionConstants;
import com.ams.util.SystemControl;
import com.ams.util.UrlCommon;



@Controller
@RequestMapping("/AssetGeneralRegister")
public class AssetGeneralRegister {
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request, RedirectAttributes redirect)
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
				mv.addObject(ParamsConstants.TITLE_SCREEN, "MÀN HÌNH TẠO MỚI TÀI SẢN");
				AssetGeneralModel asset = (AssetGeneralModel) request.getSession().getAttribute("object");
				if(asset !=null && Common.isNotEmpty(asset.getRFID()))
				{
					mv.addObject("asset", asset);
				}
				mv.setViewName("views/AssetGeneralRegister.jsp");			
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
	@RequestMapping(params="save", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response, RedirectAttributes rediect) throws UnsupportedEncodingException
	{
		AuthenticationLogin auth = new AuthenticationLogin(request);
		ModelAndView mv = new ModelAndView();
		//Kiểm tra đăng nhập
		if(auth.isLogin())
		{
			String user_cd = (String) request.getSession().getAttribute(SessionConstants.SESSION_USER_ID);
			String company_cd = (String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD);
			boolean isPermission = auth.CheckPermissionIsTrue(company_cd, user_cd, RoleCommon.R_WRITE, ServicesConstants.ASSET_MANAGEMENT);
			//Kiểm tra quyền hạn
			if(isPermission)
			{
				request.setCharacterEncoding("UTF-8");
				String CMPN_CD = (String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD);
				
				
				AssetGeneralModel asset = new AssetGeneralModel();
				asset.setCompany_CD(CMPN_CD);
				request.setCharacterEncoding("UTF-8");
				asset.setName(request.getParameter("asset_name"));
				asset.setRFID(request.getParameter("asset_rfid"));
				asset.setAccountant_CD(request.getParameter("asset_accountant"));
				asset.setModel(request.getParameter("asset_model"));
				asset.setSeries(request.getParameter("asset_series"));
				asset.setDepartment_cd(request.getParameter("department_cd"));
				asset.setNote(request.getParameter("asset_note"));
				asset.setPrice(request.getParameter("asset_price"));
				asset.setId(Common.getDateCurrent("YYYYMMddHHmmSS"));
				asset.setGroup_id(request.getParameter("group_asset_cd"));
				asset.setNote(request.getParameter("asset_note"));
				asset.setDepartment_name(request.getParameter("department_name"));
				asset.setGroup_name(request.getParameter("group_asset_na"));
				String dateStart = request.getParameter("asset_date");
				String dateEnd = request.getParameter("asset_date_end");
				asset.setOriginal(request.getParameter("asset_original"));
				asset.setMaintaince(request.getParameter("asset_maintaince"));
				asset.setCustomer(request.getParameter("asset_customer"));
				asset.setContract(request.getParameter("asset_contract"));
				asset.setUser_insert_cd(Common.getSessionValue(request, SessionConstants.SESSION_USER_ID));
				asset.setUser_update_cd(Common.getSessionValue(request, SessionConstants.SESSION_USER_ID));
				asset.setInsert_dt(Common.getDateCurrent("YYYYMMdd"));
				asset.setUpdate_dt(Common.getDateCurrent("YYYYMMdd"));
				
				String error = "";
				//Validate
				if(Common.isEmpty(asset.getName()))
				{
					error = error + "Tên tài sản là bắt buộc <br>";
				}
				if(Common.isEmpty(asset.getRFID()))
				{
					error = error + "Mã quản lý tài sản (RFID) là bắt buộc <br>";
				}
				if(Common.isEmpty(asset.getModel()))
				{
					error = error + "Mã Model sản là bắt buộc <br>";
				}
				if(Common.isEmpty(asset.getSeries()))
				{
					error = error + "Số series tài sản là bắt buộc <br>";
				}
				if(Common.isEmpty(asset.getAccountant_CD()))
				{
					error = error + "Mã kế toán của tài sản là bắt buộc <br>";
				}
				if(Common.isEmpty(asset.getDepartment_cd()))
				{
					error = error + "Đơn vị quản lý tài sản là bắt buộc <br>";
				}
				if(Common.isEmpty(asset.getGroup_id()))
				{
					error = error + "Tên nhóm tài sản là bắt buộc <br>";
				}
				if(Common.isEmpty(asset.getPrice()))
				{
					error = error + "Giá trị tái sản là bắt buộc <br>";
				}
				
				if(Common.isEmpty(asset.getMaintaince()))
				{
					error = error + "Thời gian bảo trì tài sản là bắt buộc <br>";
				}
				
				if(dateStart != null & dateStart.trim().length()>0)
				{
					try {
					String dateStartConvert = Common.ConvertStringToDateStr(dateStart, "yyyy-mm-dd","dd/mm/yyyy");
					asset.setDateStart(dateStartConvert);
					} catch (ParseException e1) {
						error = error + "Giá trị ngày bắt đầu không hợp lệ <br>";
					}
				}
				else
				{
					error = error + "Giá trị ngày bắt đầu là bắt buộc <br>";
				}
				
				if(dateEnd != null & dateEnd.trim().length()>0)
				{
					try {
					String dateEndConvert = Common.ConvertStringToDateStr(dateEnd, "yyyy-mm-dd","dd/mm/yyyy");
					asset.setDateEnd(dateEndConvert);
					} catch (ParseException e1) {
						error = error + "Giá trị kết thúc đầu không hợp lệ <br>";
					}
				}
				else
				{
					asset.setDateStart("");
				}
				
				if(error.trim().length()==0)
				{
					AssetGeneralInsertDao assetGeneralInsertDao = new AssetGeneralInsertDao();
					
					try {
						assetGeneralInsertDao.excuteData(asset);
						mv.addObject("notification", "Thêm tài sản thành công");
						mv.addObject(ParamsConstants.TITLE_SCREEN, "MÀN HÌNH TẠO MỚI TÀI SẢN");
						mv.setViewName("views/AssetGeneralRegister.jsp");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						rediect.addFlashAttribute("message", "Thêm tài sản không thành công");
						e.printStackTrace();
						mv.setViewName("redirect:/error");
					}
				}
				else
				{
					mv.addObject(ParamsConstants.MESSAGE_ERROR, error);
					asset.setDateStart(dateStart);
					asset.setDateEnd(dateEnd);
					mv.addObject("asset", asset);
					mv.addObject(ParamsConstants.TITLE_SCREEN, "MÀN HÌNH TẠO MỚI TÀI SẢN");
					mv.setViewName("views/AssetGeneralRegister.jsp");
					
				}
			}
			else
			{
				rediect.addFlashAttribute(ParamsConstants.MESSAGE_ERROR, "BẠN KHÔNG CÓ QUYỀN THỰC HIỆN CHỨC NĂNG NÀY");
				mv.setViewName(UrlCommon.ErrorUrl);
			}
		}
		else
		{
			mv.setViewName(UrlCommon.LoginUrl);
		}
			
		
		return mv;
	}
	@RequestMapping(params="back", method=RequestMethod.POST)
	public String back(HttpServletRequest request, HttpServletResponse response)
	{
		return "redirect:/AssetManagementGeneral";
	}
}
