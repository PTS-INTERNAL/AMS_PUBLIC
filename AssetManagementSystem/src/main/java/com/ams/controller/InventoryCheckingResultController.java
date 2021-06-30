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

import com.ams.dao.AssetTroubleSelectDao;
import com.ams.dao.InventoryCheckingResultSelectDao;
import com.ams.dao.InventoryCheckingSelectRealtimeDao;
import com.ams.helper.PdfAssetTroubleView;
import com.ams.helper.PdfInventorySession;
import com.ams.model.AssetGeneralFormSearch;
import com.ams.model.InventoryCheckingRealtimeModel;
import com.ams.model.TroubleAssetModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;

@Controller
@RequestMapping("/InventoryCheckingResult")
public class InventoryCheckingResultController {

	String TITLE = "MÀN HÌNH XEM KẾT QUẢN KIỂM KÊ";

	@RequestMapping(params = "view_result", method = RequestMethod.POST)
	public ModelAndView init(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		String session_cd = request.getParameter("session_cd");
		if(Common.isNotCheckEmpty(session_cd))
		{
			request.getSession().setAttribute("session_cd", session_cd);
		}
		// ----------------
		InventoryCheckingRealtimeModel asset = new InventoryCheckingRealtimeModel();
		asset.getAsset().setCompany_CD(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		asset.getInventorySession().setInvenotrySessionCD(session_cd);
		asset.setStatus("9999");
	    mv.addObject("session_cd", session_cd);
		mv.addObject("formSearch", asset);
		InventoryCheckingResultSelectDao selectInv = new InventoryCheckingResultSelectDao(asset);
		try {
			List<InventoryCheckingRealtimeModel> lstCheck = selectInv.excute();
			mv.addObject("listAssetSearch", lstCheck);
			int ok = 0;
			int ng = 0;
			int news = 0;
			int notok = 0;

			for (int i = 0; i < lstCheck.size(); i++) {
				if (lstCheck.get(i).getStatus().equals("1")) {
					ok++;
				}
				if (lstCheck.get(i).getStatus().equals("2")) {
					ng++;
				}
				if (lstCheck.get(i).getStatus().equals("3")) {
					news++;
				}
			}

			notok = lstCheck.size() - (ok + ng + news);
			mv.addObject("ok", ok);
			mv.addObject("ng", ng);
			mv.addObject("news", news);
			mv.addObject("notok", notok);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("views/InventoryCheckingResult.jsp");
		return mv;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView initGET(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		String session_cd = Common.getSessionValue(request, "session_cd");
		// ----------------
		InventoryCheckingRealtimeModel asset = new InventoryCheckingRealtimeModel();
		asset.getAsset().setCompany_CD(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		asset.getInventorySession().setInvenotrySessionCD(session_cd);
		asset.setStatus("9999");
		mv.addObject("formSearch", asset);
		 mv.addObject("session_cd", session_cd);
		InventoryCheckingResultSelectDao selectInv = new InventoryCheckingResultSelectDao(asset);
		try {
			List<InventoryCheckingRealtimeModel> lstCheck = selectInv.excute();
			mv.addObject("listAssetSearch", lstCheck);
			int ok = 0;
			int ng = 0;
			int news = 0;
			int notok = 0;

			for (int i = 0; i < lstCheck.size(); i++) {
				if (lstCheck.get(i).getStatus().equals("1")) {
					ok++;
				}
				if (lstCheck.get(i).getStatus().equals("3")) {
					ng++;
				}
				if (lstCheck.get(i).getStatus().equals("2")) {
					news++;
				}
			}

			notok = lstCheck.size() - (ok + ng + news);
			mv.addObject("ok", ok);
			mv.addObject("ng", ng);
			mv.addObject("news", news);
			mv.addObject("notok", notok);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("views/InventoryCheckingResult.jsp");
		return mv;
	}

	@RequestMapping(params = "back", method = RequestMethod.POST)
	public ModelAndView back(ModelMap modelMap, HttpServletRequest request) throws SQLException {
		ModelAndView mv = new ModelAndView();
		request.getSession().setAttribute("inventory_session_cd", request.getParameter("session_cd"));
		mv.setViewName("redirect:/InventorySessionManagement");

		return mv;
	}

	@RequestMapping(params = "reportPDF", method = RequestMethod.POST)
	public ModelAndView reportPDF(HttpServletRequest request) {
		String session_cd = Common.getSessionValue(request, "session_cd");
		
		// ----------------
		InventoryCheckingRealtimeModel asset = new InventoryCheckingRealtimeModel();
		asset.getAsset().setCompany_CD(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		asset.getInventorySession().setInvenotrySessionCD(session_cd);
		asset.getAsset().setRFID(request.getParameter("text_rfid"));
		asset.getAsset().setName(request.getParameter("text_asset_name"));
		asset.getAsset().setModel(request.getParameter("text_model"));
		asset.getAsset().setSeries(request.getParameter("text_series"));
		asset.getAsset().setAccountant_CD(request.getParameter("text_accountant"));
		asset.getAsset().setGroup_id(request.getParameter("group_asset_cd"));
		asset.getAsset().setGroup_name(request.getParameter("group_asset_na"));
		asset.getAsset().setDepartment_cd(request.getParameter("department_cd"));
		asset.getAsset().setDepartment_name(request.getParameter("department_name"));
		asset.getAsset().setPriceEnd(request.getParameter("text_end_price"));
		asset.getAsset().setPriceStart(request.getParameter("text_start_price"));
		String mode  = request.getParameter("optradio");
		asset.setStatus(mode);
		
		InventoryCheckingResultSelectDao selectInv = new InventoryCheckingResultSelectDao(asset);
		List<InventoryCheckingRealtimeModel> lst = null;
		try {
			lst = selectInv.excute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView(new PdfInventorySession(), "lst", lst);
	}

	@RequestMapping(params = "search", method = RequestMethod.POST)
	public ModelAndView search(ModelMap modelMap, HttpServletRequest request) throws SQLException {
		ModelAndView mv = new ModelAndView();
		String session_cd = request.getParameter("session_cd");
		 mv.addObject("session_cd", session_cd);
		InventoryCheckingRealtimeModel asset = new InventoryCheckingRealtimeModel();
		asset.getAsset().setCompany_CD(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		asset.getInventorySession().setInvenotrySessionCD(session_cd);
		asset.getAsset().setRFID(request.getParameter("text_rfid"));
		asset.getAsset().setName(request.getParameter("text_asset_name"));
		asset.getAsset().setModel(request.getParameter("text_model"));
		asset.getAsset().setSeries(request.getParameter("text_series"));
		asset.getAsset().setAccountant_CD(request.getParameter("text_accountant"));
		asset.getAsset().setGroup_id(request.getParameter("group_asset_cd"));
		asset.getAsset().setGroup_name(request.getParameter("group_asset_na"));
		asset.getAsset().setDepartment_cd(request.getParameter("department_cd"));
		asset.getAsset().setDepartment_name(request.getParameter("department_name"));
		asset.getAsset().setPriceEnd(request.getParameter("text_end_price"));
		asset.getAsset().setPriceStart(request.getParameter("text_start_price"));

		String setDateStart_Start = request.getParameter("text_start_date_s");
		String setDateStart_End = request.getParameter("text_start_date_e");
		
		String mode  = request.getParameter("optradio");
		
		asset.setStatus(mode);
		
		String error = "";
		if (setDateStart_Start != null && setDateStart_Start.trim().length() > 0) {
			try {
				String dateEndConvert = Common.ConvertStringToDateStr(setDateStart_Start, "yyyy-mm-dd", "dd/mm/yyyy");
				asset.getAsset().setDateStart_Start(dateEndConvert);
			} catch (ParseException e1) {
				error = error + "Giá trị ngày không hợp lệ <br>";
			}
		}
		if (setDateStart_End != null && setDateStart_End.trim().length() > 0) {
			try {
				String dateEndConvert = Common.ConvertStringToDateStr(setDateStart_End, "yyyy-mm-dd", "dd/mm/yyyy");
				asset.getAsset().setDateStart_End(dateEndConvert);
			} catch (ParseException e1) {
				error = error + "Giá trị ngày không hợp lệ <br>";
			}
		}
		
		
		mv.addObject("formSearch", asset);
		
		
		if (error.trim().length() == 0) {
			InventoryCheckingResultSelectDao selectInv = new InventoryCheckingResultSelectDao(asset);
			try {
				List<InventoryCheckingRealtimeModel> lst = selectInv.excute();
				if(lst.size()==0)
				{
					mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "KHÔNG TÌM THẤY TÀI SẢN PHÙ HỢP ĐIỀU KIỆN TÌM KIẾM");
				}
				else
				{
				mv.addObject("listAssetSearch", lst);
				int ok = 0;
				int ng = 0;
				int news = 0;
				int notok = 0;

				for (int i = 0; i < lst.size(); i++) {
					if (lst.get(i).getStatus().equals("1")) {
						ok++;
					}
					if (lst.get(i).getStatus().equals("2")) {
						ng++;
					}
					if (lst.get(i).getStatus().equals("3")) {
						news++;
					}
				}
				
				notok = lst.size() - (ok + ng + news);
				mv.addObject("ok", ok);
				mv.addObject("ng", ng);
				mv.addObject("news", news);
				mv.addObject("notok", notok);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		mv.setViewName("views/InventoryCheckingResult.jsp");
		return mv;
	}

}
