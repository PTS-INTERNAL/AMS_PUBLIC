package com.ams.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ams.dao.AssetGeneralInsertDao;
import com.ams.dao.AssetGeneralSelectDao;
import com.ams.dao.AssetGeneralUpdateDao;
import com.ams.dao.HistoryAssetUpdateInsertDao;
import com.ams.model.AssetGeneralFormSearch;
import com.ams.model.AssetGeneralModel;
import com.ams.model.HistoryAssetUpdateModel;
import com.ams.util.AuthenticationLogin;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.RoleCommon;
import com.ams.util.ServicesConstants;
import com.ams.util.SessionConstants;
import com.ams.util.UrlCommon;


@Controller
@RequestMapping("/AssetGeneralUpdate")
public class AssetGeneralUpdateController {
	String TITLESCREEN = "MÀN HÌNH CHỈNH SỬA THÔNG TIN TÀI SẢN";
	@RequestMapping( method=RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request) throws ParseException
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLESCREEN);
		//LẤY RFID
		String RFID_CD = Common.getSessionValue(request, "RFID_CD");
		if(RFID_CD != null && RFID_CD.length() >0)
		{
			Common.removeSessionValue(request, "RFID_CD");
			AssetGeneralFormSearch asset = new AssetGeneralFormSearch();
			asset.setRFID(RFID_CD.trim());
			asset.setCompany_CD((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
			AssetGeneralSelectDao assetGeneralSelectDao = new AssetGeneralSelectDao(asset);
			try {
				List<AssetGeneralModel> lst = assetGeneralSelectDao.excute();
				if(lst.size() == 0)
				{
					mv.addObject(ParamsConstants.MESSAGE_ERROR,"Không tìm thấy tài sản khớp với mã");
				}
				else
				{
					if(lst.size()>1)
					{
						mv.addObject(ParamsConstants.MESSAGE_ERROR,"Tìm thấy 2 tài sản tương tự<br>Hãy kiểm tra lại");
					}
					else
					{
						if(!"null".equals(lst.get(0).getDateStart()) && lst.get(0).getDateStart()!=null && lst.get(0).getDateStart().trim().length()>0)
						{
							String date = lst.get(0).getDateStart();
							String dateNew = Common.ConvertStringToDateStr(date, "dd/mm/yyyy", "yyyy-mm-dd");
							lst.get(0).setDateStart(dateNew);
						}
						if(!"null".equals(lst.get(0).getDateEnd()) &&  lst.get(0).getDateEnd()!=null && lst.get(0).getDateEnd().trim().length()>0)
						{
							String date = lst.get(0).getDateEnd();
							String dateNew = Common.ConvertStringToDateStr(date, "dd/mm/yyyy", "yyyy-mm-dd");
							lst.get(0).setDateEnd(dateNew);
						}
						mv.addObject("asset",lst.get(0));
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				mv.addObject(ParamsConstants.MESSAGE_ERROR,"Lỗi trong lúc tìm tài sản");
				e.printStackTrace();
				
			}
		}
		else
		{
			mv.addObject(ParamsConstants.MESSAGE_ERROR,"Không truyền mã quản lý RFID cho màn hình");
		}
		
		mv.setViewName("views/AssetGeneralUpdate.jsp");
		
		return mv;
	}
	@RequestMapping(params="save", method=RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirect) throws UnsupportedEncodingException, ParseException
	{
		AuthenticationLogin auth = new AuthenticationLogin(request);
		ModelAndView mv = new ModelAndView();
		//Kiểm tra đăng nhập
		if(auth.isLogin())
		{
			String user_cd = (String) request.getSession().getAttribute(SessionConstants.SESSION_USER_ID);
			String company_cd = (String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD);
			boolean isPermission = auth.CheckPermissionIsTrue(company_cd, user_cd, RoleCommon.R_UPDATE, ServicesConstants.ASSET_MANAGEMENT);
			//Kiểm tra quyền hạn
			if(isPermission)
			{
				String error = "";
				request.setCharacterEncoding("UTF-8");
				String CMPN_CD = (String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD);
				
				//LẤY DỮ LIỆU TÀI SẢN
				AssetGeneralModel asset = new AssetGeneralModel();
				asset.setCompany_CD(CMPN_CD);
				asset.setId(request.getParameter("asset_id"));
				asset.setName(request.getParameter("asset_name"));
				asset.setRFID(request.getParameter("asset_rfid"));
				asset.setAccountant_CD(request.getParameter("asset_accountant"));
				asset.setModel(request.getParameter("asset_model"));
				asset.setSeries(request.getParameter("asset_series"));
				asset.setDepartment_cd(request.getParameter("department_cd"));
				asset.setNote(request.getParameter("asset_note"));
				asset.setPrice(request.getParameter("asset_price"));
				asset.setGroup_id(request.getParameter("group_asset_cd"));
				asset.setNote(request.getParameter("asset_note"));
				asset.setDepartment_name(request.getParameter("department_name"));
				asset.setGroup_name(request.getParameter("group_asset_na"));		
				asset.setUser_update_cd(Common.getSessionValue(request, SessionConstants.SESSION_USER_ID));
				asset.setUpdate_dt(Common.getDateCurrent("YYYYMMdd"));
				asset.setOriginal(request.getParameter("asset_original"));
				asset.setMaintaince(request.getParameter("asset_maintaince"));
				asset.setCustomer(request.getParameter("asset_customer"));
				asset.setContract(request.getParameter("asset_contract"));
				String dateStart = request.getParameter("asset_date");
				String dateEnd = request.getParameter("asset_date_end");
				
				//LẤY DỮ LIỆU LOG
				HistoryAssetUpdateModel hisAssetUpdate = new HistoryAssetUpdateModel();
				hisAssetUpdate.setUpdate_reason(request.getParameter("update_reason"));
				if(hisAssetUpdate.getUpdate_reason() !=null && hisAssetUpdate.getUpdate_reason().trim().length()>0)
				{
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
//					if(Common.isEmpty(asset.getMaintaince()))
//					{
//						error = error + "Thời gian bảo trì tài sản là bắt buộc <br>";
//					}
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
					
					if(!"null".equals(dateStart) && dateStart != null & dateStart.trim().length()>0)
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
					
					if(!"null".equals(dateEnd) &&  dateEnd != null & dateEnd.trim().length()>0)
					{
						try {
						String dateEndConvert = Common.ConvertStringToDateStr(dateEnd, "yyyy-mm-dd","dd/mm/yyyy");
						asset.setDateEnd(dateEndConvert);
						} catch (ParseException e1) {
							error = error + "Giá trị kết thúc đầu không hợp lệ <br>";
						}
					}
				}
				else
				{
					error = error + "GIẢI TRÌNH LÝ DO LÀ MỘT ĐIỀU RẤT QUAN TRỌNG";
				}
				
				if(error.trim().length()==0)
				{
					AssetGeneralUpdateDao assetGeneralUpdateDao = new AssetGeneralUpdateDao();
					
					try {
						assetGeneralUpdateDao.excuteData(asset);
						request.getSession().setAttribute("RFID_CD", asset.getRFID());
						redirect.addFlashAttribute(ParamsConstants.MESSAGE_NOTIFICATION, "CẬP NHẬT THÔNG TIN THÀNH CÔNG");
						mv.setViewName("redirect:/AssetGeneralView");
						
						hisAssetUpdate.setUpdate_cd(Common.getDateCurrent("YYYYMMddHHmmSS"));
						hisAssetUpdate.setUpdate_asset_rfid(asset.getRFID());
						hisAssetUpdate.setUpdate_asset_cd(asset.getId());
						hisAssetUpdate.setUpdate_cmpn_cd(asset.getCompany_CD());
						hisAssetUpdate.setUpdate_insert_dt(Common.getDateCurrent("YYYYMMdd"));
						hisAssetUpdate.setUpdate_user_insert(asset.getUser_update_cd());
						
						HistoryAssetUpdateInsertDao hisInsert = new HistoryAssetUpdateInsertDao(hisAssetUpdate);
						hisInsert.excute();
						
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						redirect.addFlashAttribute("message", e.toString());
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
					if(hisAssetUpdate.getUpdate_reason().trim().length()>0)
					{
						mv.addObject("reason", hisAssetUpdate.getUpdate_reason().trim());
					}
					mv.addObject(ParamsConstants.TITLE_SCREEN,TITLESCREEN );
					mv.setViewName("views/AssetGeneralUpdate.jsp");
					
				}
			}
			else
			{
				redirect.addFlashAttribute(ParamsConstants.MESSAGE_ERROR, "BẠN KHÔNG CÓ QUYỀN THỰC HIỆN CHỨC NĂNG NÀY");
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
	public String back(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, ParseException
	{
		String RFID_CD = request.getParameter("rfid_code");
		request.getSession().setAttribute("RFID_CD", RFID_CD);
		return "redirect:/AssetGeneralView" ;
	}

}
