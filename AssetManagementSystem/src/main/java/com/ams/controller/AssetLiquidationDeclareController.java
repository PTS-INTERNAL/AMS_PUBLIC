package com.ams.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.COMM_FAILURE;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.AssetGeneralIncludeSelectDao;
import com.ams.dao.AssetGeneralSelectDao;
import com.ams.dao.AssetLiquidationDeleteDao;
import com.ams.dao.AssetLiquidationInsertDao;
import com.ams.dao.AssetLiquidationModel;
import com.ams.dao.AssetLiquidationSelectDao;
import com.ams.dao.AssetLiquidationUpdateStatusDao;
import com.ams.dao.BorrowAssetInsertDao;
import com.ams.dao.BorrowAssetSelectDao;
import com.ams.dao.CompanySelectDao;
import com.ams.dao.InventoryCheckingResultSelectDao;
import com.ams.dao.ListLiquidationSelectDao;
import com.ams.dao.LoanAssetInsertDao;
import com.ams.dao.UserSelectDao;
import com.ams.dao.borrow.BorrowAssetUpdateDao;
import com.ams.dao.borrow.BorrowAssetUpdateDeleteDao;
import com.ams.dao.borrow.BorrowCouponSelectDao;
import com.ams.helper.ExcelAssetGeneralListReportView;
import com.ams.helper.ExcelAssetLiquidationReportView;
import com.ams.helper.PdfChoMuon;
import com.ams.helper.PdfInventorySession;
import com.ams.helper.PdfUserListReportView;
import com.ams.model.AssetGeneralFormSearch;
import com.ams.model.AssetGeneralModel;
import com.ams.model.BorrowAssetModel;
import com.ams.model.BorrowCouponModel;
import com.ams.model.CompanyModel;
import com.ams.model.ExportBorrowMove;
import com.ams.model.InventoryCheckingRealtimeModel;
import com.ams.model.LiquidationListModel;
import com.ams.model.LoanAssetModel;
import com.ams.model.UserModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;
import com.ams.util.SystemControl;

@Controller
@RequestMapping("/LiquidAssetDeclare")
public class AssetLiquidationDeclareController {
	String TITLE = "MÀN HÌNH KÊ KHAI DANH SÁCH THANH LÝ";

	@RequestMapping(params = "declare", method = RequestMethod.POST)
	public ModelAndView init(HttpServletRequest request) throws SQLException {
		ModelAndView mv = new ModelAndView();
		String lst_cd = request.getParameter("List_id");
		
		System.out.println("######     /"+lst_cd);
		
		HttpSession session= request.getSession();
		session.setAttribute("lstcd", lst_cd);
		LiquidationListModel lst = new LiquidationListModel();
		lst.setList_id(lst_cd);
		
		ListLiquidationSelectDao select = new ListLiquidationSelectDao(lst);
		List<LiquidationListModel> Listlst = null;
		Listlst=select.excute();
		mv.addObject("List",Listlst.get(0));
		
		
		SystemControl system = new SystemControl(request);
		String number_row = system.getParameter(ParamsConstants.ROW_BORROW_NUMBER);
		mv.addObject("number_row", number_row.trim());
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		
		
		
		AssetLiquidationModel asslquid=new AssetLiquidationModel();
		asslquid.setList_ID(request.getParameter("List_id"));
		asslquid.getAsset().setCompany_CD(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		AssetLiquidationSelectDao assliquidSelect =new AssetLiquidationSelectDao(asslquid);
		List<AssetLiquidationModel> listAssetLiquidation= new ArrayList<>();
		
		try {
			listAssetLiquidation=assliquidSelect.excute();
			if(listAssetLiquidation.size()< Integer.parseInt( number_row.trim())) {
				while(listAssetLiquidation.size()< Integer.parseInt( number_row.trim()))
				{
					AssetLiquidationModel item = new AssetLiquidationModel();
					listAssetLiquidation.add(item);
				}
			}
			mv.addObject("lstLiquid",listAssetLiquidation);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		mv.setViewName("views/AssetLiquidationDeclare.jsp");
		return mv;
	}
	@RequestMapping(params = "GetImfor", method = RequestMethod.POST)
	public ModelAndView getImfor(HttpServletRequest request) throws SQLException {
		
		ModelAndView mv = new ModelAndView();
		HttpSession session= request.getSession();
		
		String List_cd = request.getParameter("List_id");
		System.out.println("HHHHHHHHHHHHhhhh");
		System.out.println(List_cd);
		AssetLiquidationModel AssetLiquid = new AssetLiquidationModel();
		AssetLiquid.setList_ID(List_cd);

		AssetLiquidationSelectDao selectlquid = new AssetLiquidationSelectDao(AssetLiquid);
		
		List<AssetLiquidationModel> lst = null;

		try {
			lst = selectlquid.excute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			Common.showMessageError(mv, "LỖI TÌM DANH SÁCH THANH LÝ");
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("HHHHHHHHHHHHhhhh");
		//Set view layout
		SystemControl system = new SystemControl(request);
		String number_row = system.getParameter(ParamsConstants.ROW_BORROW_NUMBER);
		
		mv.addObject("number_row", number_row.trim());
		
		System.out.println("HHHHHHHHHHHHhhhh"+number_row.trim());
		/*
		 * if(lst.size()>0) {
		 */
		int limt_row = Integer.parseInt(number_row.trim());
		List<AssetLiquidationModel> lstAssetLiquid = new ArrayList<>();
		System.out.println("HHHHHHHHHHHHhhhh"+number_row.trim());
		for(int i=1; i<=limt_row; i++)
		{
			AssetLiquidationModel ass = new AssetLiquidationModel();
			ass.setLiquidation_Cd(request.getParameter("Liquid["+i+"]_cd"));		
			ass.getAsset().setRFID(request.getParameter("rfid["+i+"]"));
			ass.getAsset().setModel(request.getParameter("model["+i+"]"));
			ass.getAsset().setSeries(request.getParameter("series["+i+"]"));
			ass.getAsset().setName(request.getParameter("name["+i+"]"));
			ass.getAsset().setDepartment_name(request.getParameter("dept["+i+"]"));
			ass.setStatus(request.getParameter("status["+i+"]"));
			ass.setInsertDT(request.getParameter("insertDT["+i+"]"));
			ass.getAsset().setCompany_CD(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
			lstAssetLiquid.add(ass);
		}
		System.out.println("HHHHHHHHHHHHhhhh");
		String Error = "";
		
		if(lstAssetLiquid.size()>0)
		{
			for(int i =0; i<lstAssetLiquid.size(); i++)
			{					
				if(lstAssetLiquid.get(i).getAsset().isEmpty() == false)
				{
					AssetGeneralSelectDao assSelect = new AssetGeneralSelectDao(lstAssetLiquid.get(i).getAsset());
					
					try {
						List<AssetGeneralModel> lstSearch = assSelect.excute();
						
						if(lstSearch.size()!=0)
						{
							lstAssetLiquid.get(i).setAsset(lstSearch.get(0));
							
						} 
						else {
							Error += "KHÔNG TÌM THẤY TÀI SẢN DÒNG: " + (i+1);
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 			
			}
			if(Error.trim().length()>0)
			{
				Common.showMessageError(mv, Error);
			}
			mv.addObject("lstLiquid", lstAssetLiquid);
		}
		
		LiquidationListModel lst1 = new LiquidationListModel();
		lst1.setList_id(List_cd);
		ListLiquidationSelectDao select = new ListLiquidationSelectDao(lst1);
		List<LiquidationListModel> Listlst = null;
		Listlst=select.excute();
		mv.addObject("List",Listlst.get(0));
		
		mv.addObject("listLiquidSearch", lstAssetLiquid);
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		mv.setViewName("views/AssetLiquidationDeclare.jsp");
		return mv;
	}
	@RequestMapping(params = "save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request) throws SQLException, ParseException {
		
		ModelAndView mv = new ModelAndView();
		HttpSession session= request.getSession();
		
		String List_cd = request.getParameter("List_id");
		
		System.out.println(List_cd);
		AssetLiquidationModel AssetLiquid = new AssetLiquidationModel();
		AssetLiquid.setList_ID(List_cd);

		AssetLiquidationSelectDao selectlquid = new AssetLiquidationSelectDao(AssetLiquid);
		
		List<AssetLiquidationModel> lst = null;

		try {
			lst = selectlquid.excute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			Common.showMessageError(mv, "LỖI TÌM DANH SÁCH THANH LÝ");
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		//Set view layout
		SystemControl system = new SystemControl(request);
		String number_row = system.getParameter(ParamsConstants.ROW_BORROW_NUMBER);
		
		mv.addObject("number_row", number_row.trim());
		
		
		/*
		 * if(lst.size()>0) {
		 */
		int limt_row = Integer.parseInt(number_row.trim());
		List<AssetLiquidationModel> lstAssetLiquid = new ArrayList<>();
		System.out.println("################");
		for(int i=1; i<=limt_row; i++)
		{
			AssetLiquidationModel ass = new AssetLiquidationModel();
			ass.setLiquidation_Cd(request.getParameter("Liquid["+i+"]_cd").trim());
			ass.getAsset().setRFID(request.getParameter("rfid["+i+"]"));
			ass.getAsset().setModel(request.getParameter("model["+i+"]"));
			ass.getAsset().setSeries(request.getParameter("series["+i+"]"));
			ass.getAsset().setName(request.getParameter("name["+i+"]"));
			ass.getAsset().setDepartment_name(request.getParameter("dept["+i+"]"));
			ass.setStatus(request.getParameter("status["+i+"]"));
			ass.setInsertDT(request.getParameter("insertDT["+i+"]"));
			ass.setUserInsert(request.getParameter("user_insert"));
			ass.getAsset().setCompany_CD(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
			ass.setList_ID(List_cd);
			ass.setUserApprove(request.getParameter("user_insert"));
		
			lstAssetLiquid.add(ass);
			System.out.println("################");
			System.out.println(request.getParameter("status["+i+"]"));
			System.out.println(ass.getStatus());
			
		}
	
		String Error = "";
		
		if(lstAssetLiquid.size()>0)
		{
			for(int i =0; i<lstAssetLiquid.size(); i++)
			{			
				
				if(lstAssetLiquid.get(i).getAsset().isEmpty() == false)
				{
					AssetGeneralSelectDao assSelect = new AssetGeneralSelectDao(lstAssetLiquid.get(i).getAsset());
					
					try {
						List<AssetGeneralModel> lstSearch = assSelect.excute();
						
						if(lstSearch.size()!=0)
						{
							lstAssetLiquid.get(i).setAsset(lstSearch.get(0));
							
		
							try {
								
								AssetLiquidationInsertDao assetliquidinsert=new AssetLiquidationInsertDao(lstAssetLiquid.get(i));
								assetliquidinsert.excute();
								mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "LƯU DỮ LIỆU THÀNH CÔNG");
							}
							catch (Exception e) {
								// TODO: handle exception
								try {
									
									
									lstAssetLiquid.get(i).setApproveDT(Common.getDateCurrent("YYYYMMdd"));
									System.out.println("ABC "+lstAssetLiquid.get(i).getStatus());
									AssetLiquidationUpdateStatusDao assupdate=new AssetLiquidationUpdateStatusDao(lstAssetLiquid.get(i));
									assupdate.excute();
									mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "CẬP NHẬT THÀNH CÔNG");
								}
								catch(Exception e1)
								{
									mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "CẬP NHẬT KHÔNG THÀNH CÔNG");
								}
							}
							
						} 
						else {
							Error += "KHÔNG TÌM THẤY TÀI SẢN DÒNG: " + (i+1);
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 			
			}
			
			if(Error.trim().length()>0)
			{
				Common.showMessageError(mv, Error);
			}
			mv.addObject("lstLiquid", lstAssetLiquid);
		}
		
		LiquidationListModel lst1 = new LiquidationListModel();
		lst1.setList_id(List_cd);
		ListLiquidationSelectDao select = new ListLiquidationSelectDao(lst1);
		List<LiquidationListModel> Listlst = null;
		Listlst=select.excute();
		mv.addObject("List",Listlst.get(0));
		System.out.println("H5");
		mv.addObject("listLiquidSearch", lstAssetLiquid);
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		mv.setViewName("views/AssetLiquidationDeclare.jsp");
		return mv;
	}
	
	
	@RequestMapping(params = "delete", method = RequestMethod.POST)
	public ModelAndView delete(HttpServletRequest request) throws SQLException {
		
		ModelAndView mv = new ModelAndView();
		HttpSession session= request.getSession();
		
		String List_cd = request.getParameter("List_id");
		System.out.println("HHHHHHHHHHHHhhhh");
		System.out.println(List_cd);
		AssetLiquidationModel AssetLiquid = new AssetLiquidationModel();
		AssetLiquid.setList_ID(List_cd);

		AssetLiquidationSelectDao selectlquid = new AssetLiquidationSelectDao(AssetLiquid);
		
		List<AssetLiquidationModel> lst = null;

		try {
			lst = selectlquid.excute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			Common.showMessageError(mv, "LỖI TÌM DANH SÁCH THANH LÝ");
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("HHHHHHHHHHHHhhhh");
		//Set view layout
		SystemControl system = new SystemControl(request);
		String number_row = system.getParameter(ParamsConstants.ROW_BORROW_NUMBER);
		
		mv.addObject("number_row", number_row.trim());
		
		System.out.println("HHHHHHHHHHHHhhhh"+number_row.trim());
		/*
		 * if(lst.size()>0) {
		 */
		
		String deleteliquid_id=request.getParameter("delete_id");
		
		int limt_row = Integer.parseInt(number_row.trim());
		List<AssetLiquidationModel> lstAssetLiquid = new ArrayList<>();
		System.out.println("HHHHHHHHHHHHhhhh"+number_row.trim());
		for(int i=1; i<=limt_row; i++)
		{
			AssetLiquidationModel ass = new AssetLiquidationModel();
			ass.setLiquidation_Cd(request.getParameter("Liquid["+i+"]_cd"));	

		
			System.out.println("TRUE");
			ass.getAsset().setRFID(request.getParameter("rfid["+i+"]"));
			ass.getAsset().setModel(request.getParameter("model["+i+"]"));
			ass.getAsset().setSeries(request.getParameter("series["+i+"]"));
			ass.getAsset().setName(request.getParameter("name["+i+"]"));
			ass.getAsset().setDepartment_name(request.getParameter("dept["+i+"]"));
			ass.setStatus(request.getParameter("status["+i+"]"));
			ass.setInsertDT(request.getParameter("insertDT["+i+"]"));
			ass.getAsset().setCompany_CD(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
			if(!(""+i).trim().equals(deleteliquid_id.trim()))
			{
				lstAssetLiquid.add(ass);
			}
			else
			{
				AssetLiquidationDeleteDao delete=new AssetLiquidationDeleteDao();
				delete.excuteDeleteLiquidation(request.getParameter("Liquid["+i+"]_cd"));
			}
		}
		System.out.println("HHHHHHHHHHHHhhhh");
		String Error = "";
	
		
		if(lstAssetLiquid.size()>0)
		{
			for(int i =0; i<lstAssetLiquid.size(); i++)
			{					
				if(lstAssetLiquid.get(i).getAsset().isEmpty() == false)
				{
					AssetGeneralSelectDao assSelect = new AssetGeneralSelectDao(lstAssetLiquid.get(i).getAsset());
					
					try {
						List<AssetGeneralModel> lstSearch = assSelect.excute();
						
						if(lstSearch.size()!=0)
						{
							
						
								lstAssetLiquid.get(i).setAsset(lstSearch.get(0));
											
						} 
						else {
							Error += "KHÔNG TÌM THẤY TÀI SẢN DÒNG: " + (i+1);
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 			
			}
			
			if(Error.trim().length()>0)
			{
				Common.showMessageError(mv, Error);
			}
			if(lstAssetLiquid.size()< Integer.parseInt( number_row.trim())) {
				while(lstAssetLiquid.size()< Integer.parseInt( number_row.trim()))
				{
					AssetLiquidationModel item = new AssetLiquidationModel();
					lstAssetLiquid.add(item);
				}
			}
			mv.addObject("lstLiquid", lstAssetLiquid);
		}
		
		LiquidationListModel lst1 = new LiquidationListModel();
		lst1.setList_id(List_cd);
		ListLiquidationSelectDao select = new ListLiquidationSelectDao(lst1);
		List<LiquidationListModel> Listlst = null;
		Listlst=select.excute();
		mv.addObject("List",Listlst.get(0));
		System.out.println("HHHHHHHHHHHHhhhh");
		mv.addObject("listLiquidSearch", lstAssetLiquid);
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		mv.setViewName("views/AssetLiquidationDeclare.jsp");
		return mv;
	
	}
	
	@RequestMapping(params = "reportExcel", method = RequestMethod.POST)
	public ModelAndView reportExcel(HttpServletRequest request) throws SQLException {
		
		ModelAndView mv = new ModelAndView();
		String lst_cd = request.getParameter("List_id");
		
		System.out.println("######     /"+lst_cd);
		
		HttpSession session= request.getSession();
		session.setAttribute("lstcd", lst_cd);
		LiquidationListModel lst = new LiquidationListModel();
		lst.setList_id(lst_cd);
		
		ListLiquidationSelectDao select = new ListLiquidationSelectDao(lst);
		List<LiquidationListModel> Listlst = null;
		Listlst=select.excute();
		mv.addObject("List",Listlst.get(0));
		
		
		SystemControl system = new SystemControl(request);
		String number_row = system.getParameter(ParamsConstants.ROW_BORROW_NUMBER);
		mv.addObject("number_row", number_row.trim());
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		
		
		
		AssetLiquidationModel asslquid=new AssetLiquidationModel();
		asslquid.setList_ID(request.getParameter("List_id"));
		asslquid.getAsset().setCompany_CD(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		AssetLiquidationSelectDao assliquidSelect =new AssetLiquidationSelectDao(asslquid);
		List<AssetLiquidationModel> listAssetLiquidation= new ArrayList<>();
		
		try {
			listAssetLiquidation=assliquidSelect.excute();
			if(listAssetLiquidation.size()< Integer.parseInt( number_row.trim())) {
				while(listAssetLiquidation.size()< Integer.parseInt( number_row.trim()))
				{
					AssetLiquidationModel item = new AssetLiquidationModel();
					listAssetLiquidation.add(item);
				}
			}
			if(listAssetLiquidation ==null || listAssetLiquidation.size()==0)
			{
				Common.showMessageError(mv, "Không tìm thấy dữ liệu yêu cầu<br>");
				}
			else
			{
				if(request.getParameter("reportExcel")!=null)
				{
					System.out.println("vô rồi nè");
					
					return new ModelAndView(new ExcelAssetLiquidationReportView(), "userList", listAssetLiquidation);
				}
				
			}
			mv.addObject("lstLiquid",listAssetLiquidation);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		mv.setViewName("views/AssetLiquidationDeclare.jsp");
		return mv;
	}
	@RequestMapping(params = "back",method = RequestMethod.POST)
	public String back()
	{
		return "redirect:/AssetLiquidationManagement";
	}
	

}
