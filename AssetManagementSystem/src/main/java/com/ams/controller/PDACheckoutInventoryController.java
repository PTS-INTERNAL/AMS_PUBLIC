package com.ams.controller;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.AssetGeneralSelectDao;
import com.ams.dao.CheckingInventoryInsertDao;
import com.ams.dao.InventoryCheckingSelectDao;
import com.ams.dao.InventorySessionPermissionSelectDao;
import com.ams.dao.InventorySessionSelectDao;
import com.ams.dao.UserSelectDao;
import com.ams.model.AssetGeneralFormSearch;
import com.ams.model.AssetGeneralModel;
import com.ams.model.InventoryChecking;
import com.ams.model.InventorySession;
import com.ams.model.InventorySessionModel;
import com.ams.model.InventorySessionPermission;
import com.ams.model.PermissionInventorySession;
import com.ams.model.UserModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;



@Controller
public class PDACheckoutInventoryController {

	@RequestMapping("inventory")
	public ModelAndView CompanyInsert(ModelMap modelMap, HttpServletRequest request) throws SQLException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("views/PDAInventory.jsp");

		InventorySessionSelectDao inventorySessionSelectDao = new InventorySessionSelectDao();

		List<InventorySessionModel> lstInventory = new ArrayList<InventorySessionModel>();
		lstInventory = inventorySessionSelectDao.excute();

		if (lstInventory.size() > 0) {
			mv.addObject("lstInventory", lstInventory);
		}

		return mv;
	}

	@RequestMapping("/checkout")
	public ModelAndView init(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		ModelAndView mv = new ModelAndView();
		String Session_ID = request.getParameter("id_session");
		String usn = request.getParameter("username");
		String pwd = request.getParameter("password");

		// Kiểm tra là có đúng password hay không?
		// Kiểm tra tính hợp lệ
		// Kiểm tra username rỗng
		if (usn != null && usn.length() > 0) {
			// Kiểm tra password rỗng
			if (pwd != null && pwd.length() > 0) {

				UserModel user = new UserModel();
				user.setEmployee_cd(usn);
				user.setPasword(pwd);
				UserSelectDao userSelectDao = new UserSelectDao(user);

				List<UserModel> userModel = userSelectDao.excute();

				if (userModel.size() > 0) {
					PermissionInventorySession inventorySessionPermission = new PermissionInventorySession();
					inventorySessionPermission.getUser().setEmployee_cd(userModel.get(0).employee_cd);
					inventorySessionPermission.getInventorySession().setInventory_id(Session_ID);
					InventorySessionPermissionSelectDao inventorySessionPermissionSelectDao = new InventorySessionPermissionSelectDao(
							inventorySessionPermission);
					List<PermissionInventorySession> lst = inventorySessionPermissionSelectDao.excute();
					if (lst.size() > 0) {
						mv.addObject("session_id", Session_ID);
						mv.setViewName("views/PDACheckoutInventory.jsp");
					} else {
						InventorySessionSelectDao inventorySessionSelectDao = new InventorySessionSelectDao();

						List<InventorySessionModel> lstInventory = new ArrayList<InventorySessionModel>();
						lstInventory = inventorySessionSelectDao.excute();

						if (lstInventory.size() > 0) {
							mv.addObject("lstInventory", lstInventory);
						}
						mv.addObject("message", "Bạn không có quyền sử dụng chức năng này!!");
						mv.setViewName("views/PDAInventory.jsp");
					}
				} else {
					InventorySessionSelectDao inventorySessionSelectDao = new InventorySessionSelectDao();

					List<InventorySessionModel> lstInventory = new ArrayList<InventorySessionModel>();
					lstInventory = inventorySessionSelectDao.excute();

					if (lstInventory.size() > 0) {
						mv.addObject("lstInventory", lstInventory);
					}
					mv.addObject("message", "Mật khẩu và tài khoản không đúng");
					mv.setViewName("views/PDAInventory.jsp");
				}
			}
			else
			{
				InventorySessionSelectDao inventorySessionSelectDao = new InventorySessionSelectDao();

				List<InventorySessionModel> lstInventory = new ArrayList<InventorySessionModel>();
				lstInventory = inventorySessionSelectDao.excute();

				if (lstInventory.size() > 0) {
					mv.addObject("lstInventory", lstInventory);
				}
				mv.addObject("message", "Mật khẩu là bắt buộc");
				mv.setViewName("views/PDAInventory.jsp");
			}
		}
		else
		{
			InventorySessionSelectDao inventorySessionSelectDao = new InventorySessionSelectDao();

			List<InventorySessionModel> lstInventory = new ArrayList<InventorySessionModel>();
			lstInventory = inventorySessionSelectDao.excute();

			if (lstInventory.size() > 0) {
				mv.addObject("lstInventory", lstInventory);
			}
			mv.addObject("message", "Tên đăng nhập là bắt buộc");
			mv.setViewName("views/PDAInventory.jsp");
		}
		mv.addObject("session_id", Session_ID);
		return mv;
	}

	@RequestMapping("/PDACheckInventory")
	public ModelAndView check(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String rfid = request.getParameter("rfid");
		String inventory_session_id = request.getParameter("InventorySessionCD");
		AssetGeneralFormSearch assetForm = new AssetGeneralFormSearch();
		assetForm.setRFID(rfid);
		InventoryChecking inventoryChecking = new InventoryChecking();
		inventoryChecking.setAsset_Rfid(rfid);
		inventoryChecking.setInventorySessionCD(inventory_session_id);
		
		inventoryChecking.setCompany_cd((String)request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD));
		AssetGeneralSelectDao assetGeneralSelectDao = new AssetGeneralSelectDao(assetForm);
		
		try {
			List<AssetGeneralModel> lstAsset = assetGeneralSelectDao.excute();
			if (lstAsset.size()  > 0) {
				if(lstAsset.size()==1)
				{
					//Check xem thử nó đã được kiểm kê chưa:
					if(isNotExistInventoryChecking(inventoryChecking))
					{
						inventoryChecking.setInventorySessionChecking_CD(Common.getDateCurrent("YYYYMMddHHmmSS"));
						HttpSession session =  request.getSession();
						String user = (String) session.getAttribute(SessionConstants.SESSION_USER_ID);
						inventoryChecking.setUserChecking(user);
						inventoryChecking.setInventory_Date(Common.getDateCurrent("dd/mm/yyyy"));
						inventoryChecking.setStatus("1");
						CheckingInventoryInsertDao checkingInventoryInsertDao = new CheckingInventoryInsertDao(inventoryChecking);
						try
						{
							checkingInventoryInsertDao.excute();
							mv.addObject("Asset", lstAsset.get(0));
						}
						catch(Exception e)
						{
							mv.addObject(ParamsConstants.MESSAGE_ERROR, "KIỂM KÊ TÀI SẢN LỖI");
						}	
					}
					else
					{
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "TÀI SẢN NÀY ĐÃ ĐƯỢC KIỂM KÊ TRƯỚC ĐÓ");
					}
				}
				else
				{
					mv.addObject(ParamsConstants.MESSAGE_ERROR, "Tìm thấy nhiều hơn 1 tài sản tương tự<br>Hãy kiểm tra lại");
				}
			} else {
				mv.addObject("message", "Không tìm thấy tài sản");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			mv.addObject("message", e1.toString());
		}
		mv.addObject("session_id", inventory_session_id);
		mv.setViewName("views/PDAMenuChecking.jsp");
		return mv;
	}

	private boolean isNotExistInventoryChecking(InventoryChecking inventoryChecking) {
		
		
		InventoryCheckingSelectDao inventoryCheckingSelectDao = new InventoryCheckingSelectDao(inventoryChecking);
		try {
			List<InventoryChecking> lstInvChecking = inventoryCheckingSelectDao.excute();
			if(lstInvChecking.size() > 0)
			{
				return false;
			}
			else
			{
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
