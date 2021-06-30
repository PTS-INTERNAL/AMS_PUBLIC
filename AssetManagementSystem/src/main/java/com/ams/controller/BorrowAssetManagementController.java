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

import com.ams.dao.AssetGeneralIncludeSelectDao;
import com.ams.dao.BorrowAssetSelectDao;
import com.ams.helper.ExcelAssetGeneralListReportView;
import com.ams.helper.ExcelBorrowListReportView;
import com.ams.helper.PdfUserListReportView;
import com.ams.model.AssetGeneralFormSearch;
import com.ams.model.AssetGeneralModel;
import com.ams.model.BorrowAssetModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;
import com.ams.util.SystemControl;

@Controller
@RequestMapping("/BorrowAssetManagement")
public class BorrowAssetManagementController {
	String TITLE = "MÀN HÌNH QUẢN LÝ CHO MƯỢN TÀI SẢN SẢN";
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		BorrowAssetModel br  = new BorrowAssetModel();
		br.getCmpn_master().setCompany_cd((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
		BorrowAssetSelectDao BASD = new BorrowAssetSelectDao(br);
		
		try {
			List<BorrowAssetModel> lst = BASD.excute();
			if(lst.size()==0)
			{
				mv.addObject(ParamsConstants.MESSAGE_ERROR, "KHÔNG CÓ THẤY TÀI SẢN MƯỢN NÀO ĐƯỢC TÌM THẤY");
			}
			else
			{
				mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "TÌM THẤY "+lst.size()+ " TÀI SẢN MƯỢN ĐƯỢC TÌM THẤY");
				for(int i =0;i<lst.size();i++)
				{
					if(Common.isNotEmpty(lst.get(i).getDate_start()))
					{
						try {
						String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_start(), "yyyy-mm-dd","dd/mm/yyyy");
						lst.get(i).setDate_start(dateEndConvert);
						} catch (ParseException e1) {
							mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
						}
					}
					if(Common.isNotEmpty(lst.get(i).getDate_end()))
					{
						try {
						String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_end(), "yyyy-mm-dd","dd/mm/yyyy");
						lst.get(i).setDate_end(dateEndConvert);
						} catch (ParseException e1) {
							mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
						}
					}
				}
				mv.addObject("lst", lst);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			mv.addObject(ParamsConstants.MESSAGE_ERROR, "LỖI TÌM DỮ LIỆU : " + e.toString());
		}
		
		
		
		mv.setViewName("views/BorrowAssetManagement.jsp");
		return mv;
	}
	
	@RequestMapping(params = "search",method = RequestMethod.POST)
	public ModelAndView Search(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		BorrowAssetModel borrow = new BorrowAssetModel();
		borrow.getAsset().setName(request.getParameter("text_asset_name"));
		borrow.getAsset().setModel(request.getParameter("text_model"));
		borrow.getAsset().setSeries(request.getParameter("text_series"));
		borrow.getAsset().setRFID(request.getParameter("text_rfid"));
		borrow.setDate_start_end(request.getParameter("text_end_date"));
		borrow.setDate_start(request.getParameter("text_start_date"));
		borrow.getDept_master().setDept_cd(request.getParameter("department_cd_master"));
		borrow.getDept_master().setDept_name(request.getParameter("department_name_master"));
		borrow.getDept_client().setDept_cd(request.getParameter("department_cd_client"));
		borrow.getDept_client().setDept_name(request.getParameter("department_name_client"));
		borrow.setNumber_on(request.getParameter("number_on"));
		borrow.getCmpn_master().setCompany_cd(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		BorrowAssetSelectDao BASD = new BorrowAssetSelectDao(borrow);
		
		try {
			List<BorrowAssetModel> lst = BASD.excute();
			if(lst.size()==0)
			{
				mv.addObject(ParamsConstants.MESSAGE_ERROR, "KHÔNG CÓ THẤY TÀI SẢN MƯỢN NÀO ĐƯỢC TÌM THẤY");
			}
			else
			{
				mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "TÌM THẤY "+lst.size()+ " TÀI SẢN MƯỢN ĐƯỢC TÌM THẤY");
				mv.addObject("lst", lst);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			mv.addObject(ParamsConstants.MESSAGE_ERROR, "LỖI TÌM DỮ LIỆU : " + e.toString());
		}
		mv.addObject("borrow", borrow);
		mv.setViewName("views/BorrowAssetManagement.jsp");
		return mv;
	}
	
	@RequestMapping(params = "create",method = RequestMethod.POST)
	public String Create()
	{
		return "redirect:/BorrowAssetRegister";
	}
	
	@RequestMapping(params = "back",method = RequestMethod.POST)
	public String back()
	{
		return "redirect:/FeatureSystem";
	}
	
	@RequestMapping(params = "reportExcel", method = RequestMethod.POST)
	public ModelAndView excel(ModelMap modelMap, HttpServletRequest request) throws SQLException 
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		BorrowAssetModel borrow = new BorrowAssetModel();
		borrow.getAsset().setName(request.getParameter("text_asset_name"));
		borrow.getAsset().setModel(request.getParameter("text_model"));
		borrow.getAsset().setSeries(request.getParameter("text_series"));
		borrow.getAsset().setRFID(request.getParameter("text_rfid"));
		borrow.setDate_start_end(request.getParameter("text_end_date"));
		borrow.setDate_start(request.getParameter("text_start_date"));
		borrow.getDept_master().setDept_cd(request.getParameter("department_cd_master"));
		borrow.getDept_master().setDept_name(request.getParameter("department_name_master"));
		borrow.getDept_client().setDept_cd(request.getParameter("department_cd_client"));
		borrow.getDept_client().setDept_name(request.getParameter("department_name_client"));
		borrow.setNumber_on(request.getParameter("number_on"));
		borrow.getCmpn_master().setCompany_cd(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		BorrowAssetSelectDao BASD = new BorrowAssetSelectDao(borrow);
		
		try {
			List<BorrowAssetModel> lst = BASD.excute();
			if(lst.size()==0)
			{
				mv.addObject(ParamsConstants.MESSAGE_ERROR, "KHÔNG CÓ THẤY TÀI SẢN MƯỢN NÀO ĐƯỢC TÌM THẤY");
			}
			else
			{
				mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "TÌM THẤY "+lst.size()+ " TÀI SẢN MƯỢN ĐƯỢC TÌM THẤY");
				mv.addObject("lst", lst);
				return new ModelAndView(new ExcelBorrowListReportView(), "userList", lst);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			mv.addObject(ParamsConstants.MESSAGE_ERROR, "LỖI TÌM DỮ LIỆU : " + e.toString());
		}
		mv.addObject("borrow", borrow);
		mv.setViewName("views/AssetManagementGeneral.jsp");
		return mv;
	}
}
