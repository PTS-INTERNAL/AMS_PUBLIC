package com.ams.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.AssetGeneralSelectDao;
import com.ams.dao.InventoryCheckingSelectRealtimeDao;
import com.ams.dao.InventoryCheckingUhfInsertDao;
import com.ams.dao.InventoryCheckingUhfSelectDao;
import com.ams.dao.InventorySessionInsertDao;
import com.ams.model.AssetGeneralModel;
import com.ams.model.InventoryCheckingRealtimeModel;
import com.ams.model.InventoryCheckingUhf;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;
import com.rscja.deviceapi.RFIDWithUHF;
import com.rscja.deviceapi.RFIDWithUHF.BankEnum;
import com.rscja.deviceapi.exception.ConfigurationException;


import static java.lang.Math.round;


@Controller
@RequestMapping("/InventoryChecking")
public class PDAInventoryCheckingController{
	//public  RFIDWithUHF mReader;

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		
		String Inventory_CD = (String) request.getSession().getAttribute("INVENTORY_CD");
		String Dept_CD = (String) request.getSession().getAttribute("DEPT_CD");
		
		if(Common.isNotCheckEmpty(Inventory_CD) && Common.isNotCheckEmpty(Dept_CD))
		{
			String cmpn_cd = Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD);
			mv.addObject("session",Inventory_CD);
			mv.addObject("dept", Dept_CD);
			mv.addObject("cmpn", cmpn_cd);
			InventoryCheckingRealtimeModel asset  =new InventoryCheckingRealtimeModel();
			asset.getAsset().setCompany_CD(cmpn_cd);
			asset.getAsset().setDepartment_cd(Dept_CD);
			asset.getInventorySession().setInvenotrySessionCD(Inventory_CD);

			InventoryCheckingSelectRealtimeDao selectInv = new InventoryCheckingSelectRealtimeDao(asset); 
			try {
				List<InventoryCheckingRealtimeModel> lstCheck = selectInv.excute();
				mv.addObject("lstCheck", lstCheck);
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		mv.addObject("company", Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_NAME));
		//System.out.println("Start");
		mv.setViewName("views/PDAChecking.jsp");
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView checking(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		String Inventory_CD = request.getParameter("InventorySessionCD");
		String Dept_CD =request.getParameter("select_group");
		String Uhf_rfid = request.getParameter("uhf_rfid");
		if(Common.isNotCheckEmpty(Inventory_CD) && Common.isNotCheckEmpty(Dept_CD))
		{
			String cmpn_cd = Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD);
			mv.addObject("session",Inventory_CD);
			mv.addObject("dept", Dept_CD);
			mv.addObject("cmpn", cmpn_cd);
			//----------------
			if(Common.isNotCheckEmpty(Uhf_rfid))
			{
				InventoryCheckingUhf uhfChecking = new InventoryCheckingUhf();
				uhfChecking.getAsset().setRFID(Uhf_rfid.trim());
				uhfChecking.getCompany().setCompany_cd(cmpn_cd.trim());
				uhfChecking.getDeptChecking().setDept_cd(Dept_CD);
				uhfChecking.getInventorySession().setInvenotrySessionCD(Inventory_CD);
				mv.addObject("uhf_rfid", "");
				InventoryCheckingUhfSelectDao uhfSelect = new InventoryCheckingUhfSelectDao(uhfChecking);
				try {
					List<InventoryCheckingUhf> lstUfh = uhfSelect.excute();
					
					if(lstUfh.size()==0)
					{
						AssetGeneralModel asserChecking = new AssetGeneralModel();
						asserChecking.setRFID(Uhf_rfid.trim());
						
						
						
						AssetGeneralSelectDao selectGenAsset = new AssetGeneralSelectDao(asserChecking);
						List<AssetGeneralModel> lstAsset = selectGenAsset.excute();
						if(lstAsset.size()==1)
						{
							uhfChecking.getAsset().setName(lstAsset.get(0).getName().trim());
							uhfChecking.getAsset().setModel(lstAsset.get(0).getModel().trim());
							uhfChecking.getAsset().setSeries(lstAsset.get(0).getSeries().trim());
							uhfChecking.setInventoryChekingUhf_cd(Common.getDateCurrent(ParamsConstants.CD_FULL_FORMAT));
							uhfChecking.setInsertDt(Common.getDateCurrent(ParamsConstants.CD_FULL_DATE));
							uhfChecking.setUserInsert(Common.getSessionValue(request,SessionConstants.SESSION_USER_ID));
							if(lstAsset.get(0).getDepartment_cd().equals(Dept_CD))
							{						
								uhfChecking.setStatus("1");
							}
							else
							{
								uhfChecking.setStatus("3");
							}
							InventoryCheckingUhfInsertDao insertNew = new InventoryCheckingUhfInsertDao(uhfChecking);
							insertNew.excute();
							mv.addObject("valueX", "");
							mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "KIỂM KÊ THÀNH CÔNG");
						}
						else
						{
							if(lstAsset.size()>1)
							{
								mv.addObject(ParamsConstants.MESSAGE_ERROR, "TÌM THẤY NHIỀU HƠN 1 TÀI SẢN QUẢN LÝ BẰNG MÃ NÀY");
							}
							else
							{	
								mv.addObject(ParamsConstants.MESSAGE_ERROR, "ĐÃ BÁO MỚI");
								uhfChecking.setInventoryChekingUhf_cd(Common.getDateCurrent(ParamsConstants.CD_FULL_FORMAT));
								uhfChecking.setInsertDt(Common.getDateCurrent(ParamsConstants.CD_FULL_DATE));
								uhfChecking.setUserInsert(Common.getSessionValue(request,SessionConstants.SESSION_USER_ID));
								uhfChecking.setStatus("2");
								InventoryCheckingUhfInsertDao insertNew = new InventoryCheckingUhfInsertDao(uhfChecking);
								insertNew.excute();
							}
						}
						
						
					}
					else
					{
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "ĐÃ KIỂM KÊ TRƯỚC ĐÓ");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
			
			
			//----------------
			InventoryCheckingRealtimeModel asset  =new InventoryCheckingRealtimeModel();
			asset.getAsset().setCompany_CD(cmpn_cd);
			asset.getAsset().setDepartment_cd(Dept_CD);
			asset.getInventorySession().setInvenotrySessionCD(Inventory_CD);
			InventoryCheckingSelectRealtimeDao selectInv = new InventoryCheckingSelectRealtimeDao(asset); 
			try {
				List<InventoryCheckingRealtimeModel> lstCheck = selectInv.excute();
				mv.addObject("lstCheck", lstCheck);
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		mv.addObject("valueX", "");
		mv.addObject("company", Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_NAME));
		mv.setViewName("views/PDAChecking.jsp");
		return mv;
	}
	

}
