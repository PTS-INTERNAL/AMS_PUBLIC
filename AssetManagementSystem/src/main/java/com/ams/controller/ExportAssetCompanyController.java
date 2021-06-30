package com.ams.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ams.dao.ExportAssetCompanySelectDao;
import com.ams.model.ExportAssetCompanyModel;
import com.ams.util.ParamsConstants;

@Controller
@RequestMapping("/ExportAssetCompany")
public class ExportAssetCompanyController {
String TITLE = "MÀN HÌNH XUẤT BÁO CÁO TÌNH HÌNH THIẾT BỊ CỦA DOANH NGHIỆP";
	
	@RequestMapping(method =  RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request, RedirectAttributes redirect)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		
		ExportAssetCompanySelectDao select = new ExportAssetCompanySelectDao("");
		
		try {
			List<ExportAssetCompanyModel> lstAsset = select.excute();
			List<ExportAssetCompanyModel> lstNewAsset = new ArrayList<ExportAssetCompanyModel>();
			String keysOld = "";
			int indexKeys = 0;
			for(int i=0;i<lstAsset.size();i++)
			{
				if(lstAsset.get(i).getKeys().trim().equals(keysOld))
				{
					if(lstAsset.get(i).getMode().trim().equals("GEN"))
					{
						lstNewAsset.get(indexKeys).setDefaultCount(Integer.parseInt(lstNewAsset.get(indexKeys).getDefaultCount())+Integer.parseInt(lstAsset.get(i).getCount())+"");
						lstNewAsset.get(indexKeys).setTotal(Integer.parseInt(lstNewAsset.get(indexKeys).getTotal())+Integer.parseInt(lstAsset.get(i).getCount())+"");
					}
					if(lstAsset.get(i).getMode().trim().equals("BORROW"))
					{
						if(lstAsset.get(i).getArea().trim().equals("IN"))
						{
							lstNewAsset.get(indexKeys).setBorrowIn(lstAsset.get(i).getCount());
							lstNewAsset.get(indexKeys).setDefaultCount(Integer.parseInt(lstNewAsset.get(indexKeys).getDefaultCount())+Integer.parseInt(lstAsset.get(i).getCount())+"");
							lstNewAsset.get(indexKeys).setTotal(Integer.parseInt(lstNewAsset.get(indexKeys).getTotal())+Integer.parseInt(lstAsset.get(i).getCount())+"");
						}
						if(lstAsset.get(i).getArea().trim().equals("OUT"))
						{
							lstNewAsset.get(indexKeys).setBorrowOut(lstAsset.get(i).getCount());
							lstNewAsset.get(indexKeys).setDefaultCount(Integer.parseInt(lstNewAsset.get(indexKeys).getDefaultCount())+Integer.parseInt(lstAsset.get(i).getCount())+"");
						}
					}
					if(lstAsset.get(i).getMode().trim().equals("LOAN"))
					{
						if(lstAsset.get(i).getArea().trim().equals("IN"))
						{
							lstNewAsset.get(indexKeys).setLoanIn(lstAsset.get(i).getCount());
							lstNewAsset.get(indexKeys).setDefaultCount(Integer.parseInt(lstNewAsset.get(indexKeys).getDefaultCount())+Integer.parseInt(lstAsset.get(i).getCount())+"");
							lstNewAsset.get(indexKeys).setTotal(Integer.parseInt(lstNewAsset.get(indexKeys).getTotal())+Integer.parseInt(lstAsset.get(i).getCount())+"");

						}
						if(lstAsset.get(i).getArea().trim().equals("OUT"))
						{
							lstNewAsset.get(indexKeys).setLoanOut(lstAsset.get(i).getCount());
							lstNewAsset.get(indexKeys).setTotal(Integer.parseInt(lstNewAsset.get(indexKeys).getTotal())+Integer.parseInt(lstAsset.get(i).getCount())+"");
						}
					}
					if(lstAsset.get(i).getMode().trim().equals("RENT"))
					{
						lstNewAsset.get(indexKeys).setRent(lstAsset.get(i).getCount());
						lstNewAsset.get(indexKeys).setTotal(Integer.parseInt(lstNewAsset.get(indexKeys).getTotal())+Integer.parseInt(lstAsset.get(i).getCount())+"");
					}
				}
				else
				{
					ExportAssetCompanyModel assetTemp = new ExportAssetCompanyModel();
					assetTemp = lstAsset.get(i);
					
					if(lstAsset.get(i).getMode().trim().equals("RENT"))
					{
						assetTemp.setRent(lstAsset.get(i).getCount());
						assetTemp.setDefaultCount(assetTemp.getCount());
						assetTemp.setTotal(assetTemp.getCount());
					}
					if(lstAsset.get(i).getMode().trim().equals("BORROW"))
					{
						if(lstAsset.get(i).getArea().trim().equals("IN"))
						{
							assetTemp.setBorrowIn(lstAsset.get(i).getCount());
							assetTemp.setDefaultCount(assetTemp.getCount());
							assetTemp.setTotal(assetTemp.getCount());

						}
						if(lstAsset.get(i).getArea().trim().equals("OUT"))
						{
							assetTemp.setBorrowOut(lstAsset.get(i).getCount());
							assetTemp.setDefaultCount(assetTemp.getCount());
							assetTemp.setTotal("-"+assetTemp.getCount());
						}
						
					}
					if(lstAsset.get(i).getMode().trim().equals("LOAN"))
					{
						if(lstAsset.get(i).getArea().trim().equals("IN"))
						{
							assetTemp.setLoanIn(lstAsset.get(i).getCount());
							assetTemp.setDefaultCount(assetTemp.getCount());
							assetTemp.setTotal(assetTemp.getCount());


						}
						if(lstAsset.get(i).getArea().trim().equals("OUT"))
						{
							assetTemp.setLoanOut(lstAsset.get(i).getCount());
							assetTemp.setDefaultCount(assetTemp.getCount());
							assetTemp.setTotal(assetTemp.getCount());


						}
					}
					if(lstAsset.get(i).getMode().trim().equals("GEN"))
					{
						assetTemp.setDefaultCount(assetTemp.getCount());
						assetTemp.setTotal(assetTemp.getCount());
					}
					lstNewAsset.add(assetTemp);
					keysOld = assetTemp.getKeys().trim();
					indexKeys = lstNewAsset.size()-1;
				}
			}
			for(int j=0;j<lstNewAsset.size();j++)
			{
				int total = numberOf(lstNewAsset.get(j).getDefaultCount()) + numberOf(lstNewAsset.get(j).getRent()) + numberOf(lstNewAsset.get(j).getLoanOut()) -numberOf(lstNewAsset.get(j).getBorrowOut());
				lstNewAsset.get(j).setTotal(total+"");
			}
			mv.addObject("lstAsset", lstNewAsset);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		mv.setViewName("views/ExportAssetCompany.jsp");

		return mv;
	}
	
	public int numberOf(String s)
	{
		try {
			int num = Integer.parseInt(s.trim());
			return num;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return 0;
	}
}
