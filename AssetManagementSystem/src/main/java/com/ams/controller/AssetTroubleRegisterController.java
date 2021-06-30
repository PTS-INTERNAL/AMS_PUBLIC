package com.ams.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ams.dao.AssetGeneralIncludeSelectDao;
import com.ams.dao.AssetGeneralSelectDao;
import com.ams.dao.AssetTroubleInsertDao;
import com.ams.dao.AssetTroubleSelectDao;
import com.ams.helper.PdfUserListReportView;
import com.ams.model.AssetGeneralFormSearch;
import com.ams.model.AssetGeneralModel;
import com.ams.model.TroubleAssetModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;
import com.ams.util.UrlCommon;

@Controller
@RequestMapping("AssetTroubleRegister")
public class AssetTroubleRegisterController {
	
	String TITLESCREEN = "MÀN HÌNH ĐĂNG KÝ SỰ CỐ";
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request)
	{
		ModelAndView mv =new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLESCREEN);
		AssetGeneralModel asset = new AssetGeneralModel();
		asset.setCompany_CD(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		AssetGeneralSelectDao select  =new AssetGeneralSelectDao(asset);
		List<AssetGeneralModel> lst;
		try {
			lst = select.excute();
			mv.addObject("listAssetSearch", lst);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("views/AssetTroubleRegister.jsp");
		return mv;
	}
	
	@RequestMapping(params = "search", method = RequestMethod.POST)
	public ModelAndView search(/* ModelMap modelMap, */HttpServletRequest request) throws SQLException 
	{
		ModelAndView mv = new ModelAndView();
		AssetGeneralFormSearch form = new AssetGeneralFormSearch();
		form.setName(request.getParameter("asset_name"));
		form.setModel(request.getParameter("assetModel"));
		form.setSeries(request.getParameter("asset_series"));
		form.setGroup_name(request.getParameter("group_asset_na"));
		form.setDepartment_name(request.getParameter("department_name"));
		form.setGroup_id(request.getParameter("group_asset_cd"));
		form.setDepartment_cd(request.getParameter("department_cd"));
		
		String error = "";
		
		
		form.setCompany_CD((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
		AssetGeneralIncludeSelectDao AssetSelectDaoSearch = new AssetGeneralIncludeSelectDao(form);
		List<AssetGeneralModel> lstAsset = AssetSelectDaoSearch.excute();
		
		/*
		 * if(lstAsset ==null || lstAsset.size()==0) { modelMap.addAttribute(
		 * "message","Không tìm thấy dữ liệu yêu cầu<br>Xin thay đổi điều kiện tìm kiếm"
		 * ); }
		 */
		
		/*
		 * AssetGeneralModel asset = new AssetGeneralModel();
		 * asset.setCompany_CD(Common.getSessionValue(request,
		 * SessionConstants.SUB_SYSTEM_CD)); AssetGeneralSelectDao select=new
		 * AssetGeneralSelectDao(asset);
		 * 
		 * List<AssetGeneralModel> lst = select.excute();
		 */
		TroubleAssetModel asset=new TroubleAssetModel();
		asset.getAsset().setDepartment_cd(request.getParameter("department_cd"));
		asset.getAsset().setDepartment_name(request.getParameter("department_name"));
		
		asset.getDept().setDept_cd(request.getParameter("department_cd"));
		asset.getDept().setDept_name(request.getParameter("department_name"));
		
		asset.getAsset().setGroup_id(request.getParameter("group_asset_cd"));
		asset.getAsset().setGroup_name(request.getParameter("group_asset_na"));
		asset.getAsset().setName(request.getParameter("asset_name"));
		asset.getAsset().setModel(request.getParameter("assetModel"));
		asset.getAsset().setSeries(request.getParameter("asset_series"));
		
		
		
		
		mv.addObject("asset", asset);
		mv.addObject("listAssetSearch", lstAsset);
		mv.setViewName("views/AssetTroubleRegister.jsp");
		return mv;
	}
	
	@RequestMapping(params="save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, RedirectAttributes att)
	{
		ModelAndView mv =new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLESCREEN);
		
		TroubleAssetModel asset = new TroubleAssetModel();
		asset.getAsset().setName(request.getParameter("asset_name"));
		asset.getAsset().setModel(request.getParameter("assetModel"));
		asset.getAsset().setGroup_id(request.getParameter("group_asset_cd"));
		asset.getAsset().setGroup_name(request.getParameter("group_asset_na"));
		asset.getAsset().setSeries(request.getParameter("asset_series"));
		asset.getAsset().setDepartment_cd(request.getParameter("department_cd"));
		asset.getAsset().setCompany_CD(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		
		asset.setDateTrouble(request.getParameter("asset_date"));
		asset.setTimeTrouble(request.getParameter("time"));
		asset.setReason(request.getParameter("asset_reason"));
		asset.setTrouble(request.getParameter("asset_trouble"));
		asset.setUserUse(request.getParameter("asset_user"));
		asset.setNote(request.getParameter("asset_note"));
		asset.getDept().setDept_cd(request.getParameter("department_cd"));
		asset.getDept().setDept_name(request.getParameter("department_name"));
		
		//validation
		String error = "";
		if(Common.isEmpty(asset.getAsset().getName()))
		{
			error += "TÊN TÀI SẢN LÀ BẮT BUỘC<br>"; 
		}
		if(Common.isEmpty(asset.getAsset().getModel()))
		{
			error += "MODEL SẢN LÀ BẮT BUỘC<br>"; 
		}
		if(Common.isEmpty(asset.getAsset().getSeries()))
		{
			error += "SỐ SERIES SẢN LÀ BẮT BUỘC<br>"; 
		}
		if(Common.isEmpty(asset.getAsset().getGroup_id()))
		{
			error += "NHÓM TÀI SẢN LÀ BẮT BUỘC<br>"; 
		}
		if(Common.isEmpty(asset.getAsset().getDepartment_cd()))
		{
			error += "ĐƠN VỊ SỬ DỤNG TÀI SẢN LÀ BẮT BUỘC<br>"; 
		}
		if(Common.isEmpty(asset.getDateTrouble()))
		{
			error += "SỰ CỐ XẢY RA LÀ BẮT BUỘC<br>"; 
		}
		if(Common.isEmpty(asset.getTimeTrouble()))
		{
			error += "THỜI GIAN XẢY RA SỰ CỐ LÀ BẮT BUỘC<br>"; 
		}
		if(Common.isEmpty(asset.getTrouble()))
		{
			error += "NÊU RÕ SỰ CỐ LÀ BẮT BUỘC<br>"; 
		}
		if(Common.isEmpty(asset.getReason()))
		{
			error += "LÝ DO XẢY RA SỰ CỐ LÀ BẮT BUỘC<br>"; 
		}
		if(Common.isEmpty(asset.getUserUse()))
		{
			error += "NGƯỜI SỬ DỤNG TÀI SẢN LÀ BẮT BUỘC<br>"; 
		}
		
		
				
		if(error.trim().length()==0)
		{
			AssetGeneralSelectDao select = new AssetGeneralSelectDao(asset.getAsset());
			try {
				List<AssetGeneralModel> lst = select.excute();
				if(lst.size()==0)
				{
					mv.addObject("asset", asset);
					mv.addObject(ParamsConstants.MESSAGE_ERROR, "KHÔNG TÌM THẤY TÀI SẢN NÀY");
				}
				else
				{
					if(lst.size()==1)
					{
						//Tiếp tục add
						asset.getAsset().setId(lst.get(0).getId());
						asset.getAsset().setRFID(lst.get(0).getRFID());
						asset.setUser_insert(Common.getSessionValue(request, SessionConstants.SESSION_USER_ID));
						asset.setUser_update(Common.getSessionValue(request, SessionConstants.SESSION_USER_ID));
						asset.setUpdate_dt(Common.getDateCurrent("YYYYMMdd"));
						asset.setInsert_dt(Common.getDateCurrent("YYYYMMdd"));
						AssetTroubleInsertDao insert = new AssetTroubleInsertDao(asset);
						insert.excute();
						mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "ĐĂNG KÝ SỰ CỐ THÀNH CÔNG");
						mv.setViewName("views/AssetTroubleRegister.jsp");
						return mv;
					}
					
					else
					{
						if(lst.size()>1)
						{
							mv.addObject(ParamsConstants.MESSAGE_ERROR, "TÌM THẤY HƠN 1 TÀI SẢN");
							mv.addObject("asset", asset);
						}
						else
						{
							att.addFlashAttribute(ParamsConstants.MESSAGE_ERROR, "LỖI SAI DỮ LIỆU");
							mv.setViewName(UrlCommon.ErrorUrl);
						}
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				att.addFlashAttribute(ParamsConstants.MESSAGE_ERROR, e.toString());
				mv.setViewName(UrlCommon.ErrorUrl);
			}
		}
		else
		{
			mv.addObject("asset", asset);
			mv.addObject(ParamsConstants.MESSAGE_ERROR, error);
		}
				
		mv.setViewName("views/AssetTroubleRegister.jsp");
		return mv;
	}
	
	@RequestMapping(params="back", method = RequestMethod.POST)
	public ModelAndView back(HttpServletRequest request)
	{
		ModelAndView mv =new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLESCREEN);
		
		
		
		mv.setViewName("redirect:/AssetTroubleManagement");
		return mv;
	}


}
