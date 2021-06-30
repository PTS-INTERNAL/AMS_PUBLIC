package com.ams.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ams.dao.AssetGeneralSelectDao;
import com.ams.model.AssetGeneralFormSearch;
import com.ams.model.AssetGeneralModel;
import com.ams.util.SessionConstants;

@Controller
public class AjaxController {
	@RequestMapping("/getAssetBySeries")
	public @ResponseBody String getCountDisplay(HttpServletRequest request)
	{
		String series = request.getParameter("series");
		
		AssetGeneralFormSearch as = new AssetGeneralFormSearch();
	    as.setSeries(series);
		as.setCompany_CD((String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
	    AssetGeneralSelectDao  selectAsset = new AssetGeneralSelectDao(as);
		List<AssetGeneralModel> lst = null;
		try {
			lst = selectAsset.excute();
			if(lst.size()==1)
			{
				return lst.get(0).getName()+"_"+lst.get(0).getModel()+"_"+lst.get(0).getRFID();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "0";
	}

	
}
