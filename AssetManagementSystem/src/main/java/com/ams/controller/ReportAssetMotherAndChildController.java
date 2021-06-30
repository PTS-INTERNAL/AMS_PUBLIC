package com.ams.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ams.dao.AssetMotherAndChildSelectDao;
import com.ams.dao.CompanySelectDao;
import com.ams.helper.PdfAssetMotherAndChildView;
import com.ams.helper.PdfUserListReportView;
import com.ams.model.AssetMotherAndChilModel;
import com.ams.model.CompanyModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;

@Controller
@RequestMapping("/AssetMotherAndChild")
public class ReportAssetMotherAndChildController {
String TITLE = "MÀN HÌNH BÁO CÁO THIẾT BỊ CÔNG TY MẸ VÀ CON";
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request, RedirectAttributes redirect)
	{
		ModelAndView mv = new ModelAndView();
	    
		CompanyModel company = new CompanyModel();
		company.setCompany_delete("0");
		
		CompanySelectDao cmpnySelect = new CompanySelectDao(company);
		try {
			List<CompanyModel> lstcompny = cmpnySelect.excute();
			if(lstcompny.size()>0)
			{
				mv.addObject("lstcmpn", lstcompny);
				
				AssetMotherAndChildSelectDao selectAsset = new AssetMotherAndChildSelectDao();
				List<AssetMotherAndChilModel> lstAsset = selectAsset.excute();
				
				if(lstAsset.size()>0)
				{
					List<AssetMotherAndChilModel> lstStandar = new ArrayList<>();
					
					String oldKeys = "";
					int indexKeys=-1;
					for(int i=0;i<lstAsset.size();i++)
					{
						for(int j=0;j<lstcompny.size();j++)
						{
							if(lstAsset.get(i).getCompany().getCompany_shortname().trim().equals(lstcompny.get(j).getCompany_shortname().trim()))
							{
								lstAsset.get(i).setColumn(j);
								
							}
							lstAsset.get(i).getLstColumn().add(j, "");
						}
						
						if(lstAsset.get(i).getKeys().trim().equals(oldKeys))
						{
							//lstAsset.get(indexKeys).getLstColumn().remove(lstAsset.get(i).getColumn());
							lstAsset.get(indexKeys).getLstColumn().set(lstAsset.get(i).getColumn(), lstAsset.get(i).getCount());
							
						}
						else
						{
							indexKeys = i;
							oldKeys = lstAsset.get(i).getKeys().trim();
							//lstAsset.get(indexKeys).getLstColumn().remove(lstAsset.get(i).getColumn());
							lstAsset.get(i).getLstColumn().set(lstAsset.get(i).getColumn(), lstAsset.get(i).getCount());

						}
					}
					int sum=0;
					for(int ex=0;ex<lstAsset.get(0).getLstColumn().size();ex++)
					 {
						 String StrValue = lstAsset.get(0).getLstColumn().get(ex).trim();
						 if(Common.isNotCheckEmpty(StrValue))
						 {
							 int value = Integer.parseInt(StrValue);
							 sum = sum + value;
							 lstAsset.get(0).setSum(sum);

						 }
						 
					 }
					int x=1;
					for(x=1;x<lstAsset.size()+1;x++)
					{
						if(x==0)
						{
							x=1;
						}
						if(x<lstAsset.size())
						{
							 if(lstAsset.get(x).getKeys().trim().equals(lstAsset.get(x-1).getKeys().trim()))
							 {
								 lstAsset.remove(x);
								 x--;
								
							 }
							 else
							 {
								  sum=0;
								 for(int ex=0;ex<lstAsset.get(x).getLstColumn().size();ex++)
								 {
									 String StrValue = lstAsset.get(x).getLstColumn().get(ex).trim();
									 if(Common.isNotCheckEmpty(StrValue))
									 {
										 int value = Integer.parseInt(StrValue);
										 sum = sum + value;
										 lstAsset.get(x).setSum(sum);

									 }
									 
								 }
							 }
						}
					}

					
					
					mv.addObject("lstAsset", lstAsset);

				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		mv.setViewName("views/ExportReportAssetMotherAndChild.jsp");
		return mv;
	}
	
	@RequestMapping(params="search",  method=RequestMethod.POST)
	public ModelAndView search(HttpServletRequest request, RedirectAttributes redirect)
	{
		ModelAndView mv = new ModelAndView();
	    
		CompanyModel company = new CompanyModel();
		company.setCompany_delete("0");
		
		CompanySelectDao cmpnySelect = new CompanySelectDao(company);
		try {
			List<CompanyModel> lstcompny = cmpnySelect.excute();
			if(lstcompny.size()>0)
			{
				mv.addObject("lstcmpn", lstcompny);
				
				AssetMotherAndChildSelectDao selectAsset = new AssetMotherAndChildSelectDao();
				List<AssetMotherAndChilModel> lstAsset = selectAsset.excute();
				
				if(lstAsset.size()>0)
				{
					List<AssetMotherAndChilModel> lstStandar = new ArrayList<>();
					
					String oldKeys = "";
					int indexKeys=-1;
					for(int i=0;i<lstAsset.size();i++)
					{
						for(int j=0;j<lstcompny.size();j++)
						{
							if(lstAsset.get(i).getCompany().getCompany_shortname().trim().equals(lstcompny.get(j).getCompany_shortname().trim()))
							{
								lstAsset.get(i).setColumn(j);
								
							}
							lstAsset.get(i).getLstColumn().add(j, "");
						}
						
						if(lstAsset.get(i).getKeys().trim().equals(oldKeys))
						{
							//lstAsset.get(indexKeys).getLstColumn().remove(lstAsset.get(i).getColumn());
							lstAsset.get(indexKeys).getLstColumn().set(lstAsset.get(i).getColumn(), lstAsset.get(i).getCount());
							
						}
						else
						{
							indexKeys = i;
							oldKeys = lstAsset.get(i).getKeys().trim();
							//lstAsset.get(indexKeys).getLstColumn().remove(lstAsset.get(i).getColumn());
							lstAsset.get(i).getLstColumn().set(lstAsset.get(i).getColumn(), lstAsset.get(i).getCount());

						}
					}
					
					int sum=0;
					for(int ex=0;ex<lstAsset.get(0).getLstColumn().size();ex++)
					 {
						 String StrValue = lstAsset.get(0).getLstColumn().get(ex).trim();
						 if(Common.isNotCheckEmpty(StrValue))
						 {
							 int value = Integer.parseInt(StrValue);
							 sum = sum + value;
							 lstAsset.get(0).setSum(sum);

						 }
						 
					 }
					int x=1;
					for(x=1;x<lstAsset.size()+1;x++)
					{
						if(x==0)
						{
							x=1;
						}
						if(x<lstAsset.size())
						{
							 if(lstAsset.get(x).getKeys().trim().equals(lstAsset.get(x-1).getKeys().trim()))
							 {
								 lstAsset.remove(x);
								 x--;
								
							 }
							 else
							 {
								  sum=0;
								 for(int ex=0;ex<lstAsset.get(x).getLstColumn().size();ex++)
								 {
									 String StrValue = lstAsset.get(x).getLstColumn().get(ex).trim();
									 if(Common.isNotCheckEmpty(StrValue))
									 {
										 int value = Integer.parseInt(StrValue);
										 sum = sum + value;
										 lstAsset.get(x).setSum(sum);

									 }
									 
								 }
							 }
						}
					}


					
					
					mv.addObject("lstAsset", lstAsset);

				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		mv.setViewName("views/ExportReportAssetMotherAndChild.jsp");
		return mv;
	}
	
	@RequestMapping(params="reportPDF",  method=RequestMethod.POST)
	public ModelAndView reportPDF(HttpServletRequest request, RedirectAttributes redirect)
	{
		ModelAndView mv = new ModelAndView();
	    String reportName = request.getParameter("reportName");
	    mv.addObject("reportName", reportName);
		CompanyModel company = new CompanyModel();
		company.setCompany_delete("0");
		
		CompanySelectDao cmpnySelect = new CompanySelectDao(company);
		try {
			List<CompanyModel> lstcompny = cmpnySelect.excute();
			if(lstcompny.size()>0)
			{
				mv.addObject("lstcmpn", lstcompny);
				
				AssetMotherAndChildSelectDao selectAsset = new AssetMotherAndChildSelectDao();
				List<AssetMotherAndChilModel> lstAsset = selectAsset.excute();
				
				if(lstAsset.size()>0)
				{
					List<AssetMotherAndChilModel> lstStandar = new ArrayList<>();
					
					String oldKeys = "";
					int indexKeys=-1;
					for(int i=0;i<lstAsset.size();i++)
					{
						for(int j=0;j<lstcompny.size();j++)
						{
							if(lstAsset.get(i).getCompany().getCompany_shortname().trim().equals(lstcompny.get(j).getCompany_shortname().trim()))
							{
								lstAsset.get(i).setColumn(j);
								
							}
							lstAsset.get(i).getLstColumn().add(j, "");
						}
						
						if(lstAsset.get(i).getKeys().trim().equals(oldKeys))
						{
							//lstAsset.get(indexKeys).getLstColumn().remove(lstAsset.get(i).getColumn());
							lstAsset.get(indexKeys).getLstColumn().set(lstAsset.get(i).getColumn(), lstAsset.get(i).getCount());
							
						}
						else
						{
							indexKeys = i;
							oldKeys = lstAsset.get(i).getKeys().trim();
							//lstAsset.get(indexKeys).getLstColumn().remove(lstAsset.get(i).getColumn());
							lstAsset.get(i).getLstColumn().set(lstAsset.get(i).getColumn(), lstAsset.get(i).getCount());

						}
					}
					int sum=0;
					for(int ex=0;ex<lstAsset.get(0).getLstColumn().size();ex++)
					 {
						 String StrValue = lstAsset.get(0).getLstColumn().get(ex).trim();
						 if(Common.isNotCheckEmpty(StrValue))
						 {
							 int value = Integer.parseInt(StrValue);
							 sum = sum + value;
							 lstAsset.get(0).setSum(sum);

						 }
						 
					 }
					int x=1;
					for(x=1;x<lstAsset.size()+1;x++)
					{
						if(x==0)
						{
							x=1;
						}
						if(x<lstAsset.size())
						{
							 if(lstAsset.get(x).getKeys().trim().equals(lstAsset.get(x-1).getKeys().trim()))
							 {
								 lstAsset.remove(x);
								 x--;
								
							 }
							 else
							 {
								  sum=0;
								 for(int ex=0;ex<lstAsset.get(x).getLstColumn().size();ex++)
								 {
									 String StrValue = lstAsset.get(x).getLstColumn().get(ex).trim();
									 if(Common.isNotCheckEmpty(StrValue))
									 {
										 int value = Integer.parseInt(StrValue);
										 sum = sum + value;
										 lstAsset.get(x).setSum(sum);

									 }
									 
								 }
							 }
						}
					}

					
					
					mv.addObject("lstAsset", lstAsset);
					Map<String, Object> mapData = new HashMap<String, Object>();
					
					mapData.put("lstcmpn", lstcompny);
					mapData.put("lstAsset", lstAsset);
					mapData.put("reportName", reportName);

					return new ModelAndView(new PdfAssetMotherAndChildView(), mapData);
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		mv.setViewName("views/ExportReportAssetMotherAndChild.jsp");
		return mv;
	}
	@RequestMapping(params="back",  method=RequestMethod.POST)
	public ModelAndView back(HttpServletRequest request, RedirectAttributes redirect)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/ExportReportManagement");
		return mv;
	}

}
