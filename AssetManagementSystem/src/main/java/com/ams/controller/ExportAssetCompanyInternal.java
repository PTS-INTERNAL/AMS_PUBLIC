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

import com.ams.dao.AssetGeneralIncludeSelectDao;
import com.ams.dao.AssetMotherAndChildSelectDao;
import com.ams.dao.CompanySelectDao;
import com.ams.dao.DepartmentSelectDao;
import com.ams.dao.ExportAssetCompanyInternalSelectDao;
import com.ams.helper.PdfAssetMotherAndChildView;
import com.ams.helper.PdfExportAssetCompanyInternalView;
import com.ams.model.AssetGeneralFormSearch;
import com.ams.model.AssetGeneralModel;
import com.ams.model.AssetMotherAndChilModel;
import com.ams.model.CompanyModel;
import com.ams.model.Department;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;

@Controller
@RequestMapping("/ExportAssetCompanyInternal")
public class ExportAssetCompanyInternal {
	String TITLE = "MÀN HÌNH BÁO CÁO THIẾT BỊ CÔNG TY NỘI BỘ";

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init() {
		ModelAndView mv   = new ModelAndView();
		String reportName = "BÁO CÁO THIẾT BỊ  CÁC CÔNG TY NỘI BỘ";
	    mv.addObject("reportName", reportName);
		mv.setViewName("views/ExportAssetCompanyInternal.jsp");
		return mv;
	}
	@RequestMapping(params="search", method = RequestMethod.POST)
	public ModelAndView search(HttpServletRequest request)
	{
		ModelAndView mv   = new ModelAndView();
		String reportName = request.getParameter("reportName");
	    mv.addObject("reportName", reportName);
		String company_cd = (String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD);

		AssetGeneralFormSearch form = new AssetGeneralFormSearch();
		form.setCompany_CD(company_cd);
		
		Department department = new Department();
		department.setCompany_cd(company_cd);
		DepartmentSelectDao deptSelect = new DepartmentSelectDao(department );
		List<Department> lstDept = null;
		try {
			lstDept = deptSelect.excute();
			mv.addObject("lst", lstDept);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		ExportAssetCompanyInternalSelectDao select = new ExportAssetCompanyInternalSelectDao(company_cd);
		
		try {
			List<AssetMotherAndChilModel> lstAsset = select.excute();
			if(lstAsset.size()>0)
			{
				List<AssetMotherAndChilModel> lstStandar = new ArrayList<>();
				
				String oldKeys = "";
				String oldGroup = "";
				int indexKeys=-1;
				int countLineApprove = 0;
				//Chạy từng dòng select ra
				for(int i=0;i<lstAsset.size();i++)
				{
					//chạy từng dept
					for(int j=0;j<lstDept.size();j++)
					{
						//đưa thứ tự dept vào danh sách
						if(lstAsset.get(i).getAsset().getDepartment_cd().trim().equals(lstDept.get(j).getDept_cd().trim()))
						{
							lstAsset.get(i).setColumn(j);
							countLineApprove ++;
							
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
					
					
					
					
					
					
					
					
					if(i != 0 && lstAsset.get(i).getAsset().getGroup_id().trim().equals(lstAsset.get(i-1).getAsset().getGroup_id().trim()))
					{
						lstAsset.get(i).getAsset().setGroup_name("");
					}
				}
				System.out.println("so approve: " +countLineApprove);
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
				List<Integer> lstsum = new ArrayList<>();
				for(int i =0; i<lstDept.size(); i++) {
					lstsum.add(0);
				}
				int sumToal = 0;
				for(int j =0; j<lstAsset.size(); j++) {
					lstsum.set(	lstAsset.get(j).getColumn(), lstsum.get(lstAsset.get(j).getColumn()) + Integer.parseInt(lstAsset.get(j).getCount()));
					sumToal = sumToal + lstAsset.get(j).getSum();
				}
				
				mv.addObject("lstsum", lstsum);
				mv.addObject("sumToal", sumToal);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.setViewName("views/ExportAssetCompanyInternal.jsp");
		return mv;
	}
	
	@RequestMapping(params="reportPDF",  method=RequestMethod.POST)
	public ModelAndView reportPDF(HttpServletRequest request, RedirectAttributes redirect)
	{
		ModelAndView mv = new ModelAndView();
	    String reportName = request.getParameter("reportName");
	    mv.addObject("reportName", reportName);
		
	    
		String company_cd = (String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD);

		AssetGeneralFormSearch form = new AssetGeneralFormSearch();
		form.setCompany_CD(company_cd);
		
		Department department = new Department();
		department.setCompany_cd(company_cd);
		DepartmentSelectDao deptSelect = new DepartmentSelectDao(department );
		List<Department> lstDept = null;
		try {
			lstDept = deptSelect.excute();
			mv.addObject("lst", lstDept);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		ExportAssetCompanyInternalSelectDao select = new ExportAssetCompanyInternalSelectDao(company_cd);
		
		try {
			List<AssetMotherAndChilModel> lstAsset = select.excute();
			if(lstAsset.size()>0)
			{
				List<AssetMotherAndChilModel> lstStandar = new ArrayList<>();
				
				String oldKeys = "";
				int indexKeys=-1;
				for(int i=0;i<lstAsset.size();i++)
				{
					for(int j=0;j<lstDept.size();j++)
					{
						if(lstAsset.get(i).getAsset().getDepartment_cd().trim().equals(lstDept.get(j).getDept_cd().trim()))
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
					if(i != 0 && lstAsset.get(i).getAsset().getGroup_id().trim().equals(lstAsset.get(i-1).getAsset().getGroup_id().trim()))
					{
						lstAsset.get(i).getAsset().setGroup_name("");
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
			mv.addObject("lstAsset", lstAsset);
			Map<String, Object> mapData = new HashMap<String, Object>();
			
			mapData.put("lstDept", lstDept);
			mapData.put("lstAsset", lstAsset);
			mapData.put("reportName", reportName);

			return new ModelAndView(new PdfExportAssetCompanyInternalView(), mapData);
			
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
