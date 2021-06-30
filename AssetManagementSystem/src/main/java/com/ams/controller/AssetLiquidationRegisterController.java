package com.ams.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.AssetGeneralSelectDao;
import com.ams.dao.AssetLiquidationInsertDao;
import com.ams.dao.AssetLiquidationModel;
import com.ams.model.AssetGeneralModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;

@Controller
@RequestMapping("/AssetLiquidationRegister")
public class AssetLiquidationRegisterController {
	
	String TTITLE = "MÀN HÌNH ĐĂNG KÝ THANH LÝ TÀI SẢN";
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request)
	{
		ModelAndView mv  =new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TTITLE);
		mv.setViewName("views/AssetLiquidationRegister.jsp");
		
		return mv;
	}
	
	@RequestMapping(params="save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request)
	{
		ModelAndView mv  =new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TTITLE);
		
		AssetGeneralModel asset = new AssetGeneralModel();
		
		asset.setName(request.getParameter("asset_name"));
		asset.setSeries(request.getParameter("asset_series"));
		asset.setModel(request.getParameter("asset_model"));
		asset.setGroup_id(request.getParameter("group_asset_cd"));
		asset.setDepartment_cd(request.getParameter("department_cd"));	
		String reason = request.getParameter("reason");
		String asset_note = request.getParameter("asset_note");	
		String asset_number = request.getParameter("asset_number");
		String group_name = request.getParameter("group_asset_na");
		String dept_name = request.getParameter("department_name");
		
		mv.addObject("asset_name", asset.getName());
		mv.addObject("asset_series", asset.getSeries());
		mv.addObject("asset_model", asset.getModel());
		mv.addObject("group_asset_cd", asset.getGroup_id());
		mv.addObject("department_cd", asset.getDepartment_cd());
		mv.addObject("reason", reason);
		mv.addObject("asset_note", asset_note);
		mv.addObject("asset_number", asset_number);
		mv.addObject("group_asset_na", group_name);
		mv.addObject("department_name", dept_name);
		
		AssetGeneralSelectDao selecAsset = new AssetGeneralSelectDao(asset);
		String message="";
		if(Common.isEmpty(asset.getName()))
		{
			message += "Tên tài sản là bắt buộc<br> ";
		}
		if(Common.isEmpty(asset.getSeries()))
		{
			message += "Số series là bắt buộc<br> ";
		}
		if(Common.isEmpty(asset.getModel()))
		{
			message += "Mã model là bắt buộc<br> ";
		}
		if(message.trim().length()==0)
		{
			try {
				List<AssetGeneralModel> lstAsset = selecAsset.excute();
				
				if(lstAsset.size()==1)
				{
					if(Common.isNotEmpty(reason))
					{
						AssetLiquidationModel assetLiqui = new AssetLiquidationModel();
						assetLiqui.setAsset(lstAsset.get(0));
						assetLiqui.setReason(reason);
						assetLiqui.setNote(asset_note);
						assetLiqui.setNumber(asset_number);
						assetLiqui.getAsset().setUser_insert_cd(Common.getSessionValue(request, SessionConstants.SESSION_USER_ID));
						
						AssetLiquidationInsertDao insertDao = new AssetLiquidationInsertDao(assetLiqui);
						insertDao.excute();
						mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "ĐĂNG KÝ THÀNH CÔNG");
					}
					else
					{
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "Lý do thanh lý tài sản là bắt buộc");
					}
				}
				else
				{
					mv.addObject(ParamsConstants.MESSAGE_ERROR, "Không tìm thấy tài sản");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mv.addObject(ParamsConstants.MESSAGE_ERROR, "LỖI khi đăng ký dữ liệu");

			}
		}
		else
		{
			mv.addObject(ParamsConstants.MESSAGE_ERROR, message);
		}
		
		mv.setViewName("views/AssetLiquidationRegister.jsp");
		
		return mv;
	}
	
	@RequestMapping(params="back", method = RequestMethod.POST)
	public ModelAndView back(HttpServletRequest request)
	{
		ModelAndView mv  =new ModelAndView();
		mv.setViewName("redirect:/AssetLiquidationManagement");
		
		return mv;
	}
}
