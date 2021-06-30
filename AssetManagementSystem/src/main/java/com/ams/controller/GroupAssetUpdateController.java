package com.ams.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.CompanySelectDao;
import com.ams.dao.GroupAssetSelectDao;
import com.ams.dao.GroupAssetUpdateDao;
import com.ams.model.CompanyModel;
import com.ams.model.GroupAsset;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;

@Controller
@RequestMapping("/UpdateGroupAsset")
public class GroupAssetUpdateController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request)
	{
		String TITLE = "MÀN HÌNH CẬP NHẬT NHÓM TÀI SẢN";
		ModelAndView mv = new ModelAndView();
		String groupcd = request.getParameter("group_id");
		GroupAsset group = new GroupAsset();
		group.setGroup_id(groupcd);
		GroupAssetSelectDao seelctGroup = new GroupAssetSelectDao(group);
		try {
			List<GroupAsset> lstGroup = seelctGroup.excute();
			if(lstGroup.size()==1)
			{
				mv.addObject("formSearch", lstGroup.get(0));
			}
			else
			{
				mv.addObject(ParamsConstants.MESSAGE_ERROR, "KHÔNG TÌM THẤY NHÓM TÀI SẢN");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mv.addObject(ParamsConstants.MESSAGE_ERROR, "LỖI TÌM TÀI SẢN");

		}
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		mv.setViewName("views/UpdateGroupAsset.jsp");
		
		return mv;
	}
	@RequestMapping(params="save" ,method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request)
	{
		String TITLE = "MÀN HÌNH CẬP NHẬT NHÓM TÀI SẢN";
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);

		String groupcd = request.getParameter("group_id");
		String group_name = request.getParameter("group_name");
		String group_describe =   request.getParameter("group_describe");
		GroupAsset group = new GroupAsset();
		group.setGroup_id(groupcd);
		group.setGroup_name(group_name);
		group.setGroup_desciption(group_describe);
		if(Common.isNotCheckEmpty(group_name) == false)
		{
			mv.addObject(ParamsConstants.MESSAGE_ERROR, "KHÔNG ĐƯỢC BỎ TRỐNG TÊN NHÓM TÀI SẢN");
			mv.setViewName("views/UpdateGroupAsset.jsp");

		}
		else
		{
			
			GroupAssetUpdateDao upadteDao = new GroupAssetUpdateDao(group);
			try {
				upadteDao.excute();
				mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "CẬP NHẬT NHÓM TÀI SẢN THÀNH CÔNG");
				mv.setViewName("views/UpdateGroupAsset.jsp");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				mv.setViewName("views/UpdateGroupAsset.jsp");
				mv.addObject(ParamsConstants.MESSAGE_ERROR, "LỖI CẬP NHẬT NHÓM TÀI SẢN");
				e.printStackTrace();
			}

		}
		mv.addObject("formSearch", group);

		return mv;
	}
	

}
