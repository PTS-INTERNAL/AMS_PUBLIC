package com.ams.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.BorrowAssetSelectDao;
import com.ams.model.BorrowAssetModel;
import com.ams.util.ParamsConstants;


@Controller
@RequestMapping("/BorrowAssetUpdate")
public class BorrowAssetUpdate {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request)
	{
		ModelAndView mv  = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, "MÀN HÌNH CHỈNH SỬA THÔNG TIN TÀI SẢN MƯỢN");
		String id_borrow = request.getParameter("id");
		if(id_borrow != null && id_borrow.length() >0)
		{
			BorrowAssetModel asset = new BorrowAssetModel();
			asset.setId(id_borrow.trim());
			BorrowAssetSelectDao brsd = new BorrowAssetSelectDao(asset);
			try {
				List<BorrowAssetModel> lst = brsd.excute();
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
			mv.addObject(ParamsConstants.MESSAGE_ERROR,"Không truyền mã quản lý mượn cho màn hình");
		}
		mv.setViewName("views/BorrowAssetUpdate.jsp");
		return mv;
	}

}
