package com.ams.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.AssetGeneralInsertDao;
import com.ams.dao.DepartmentInsertDao;
import com.ams.dao.DepartmentSelectDao;
import com.ams.dao.GroupAssetInsertDao;
import com.ams.dao.GroupAssetSelectDao;
import com.ams.helper.ExcelHelper;
import com.ams.helper.UploadFileHelper;
import com.ams.model.AssetGeneralModel;
import com.ams.model.Department;
import com.ams.model.ExcelFile;
import com.ams.model.GroupAsset;
import com.ams.model.errorExcel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;
import com.ams.util.SystemControl;

@Controller
@RequestMapping("/ImportCSVAssetGenneral")
public class ImportCSVAssetGenneralController {

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView init()
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, "MÀN HÌNH NHẬP DỮ LIỆU TỪ FILE EXCEL");
		mv.setViewName("views/ImportCSVAssetGenneral.jsp");
		
		return mv;
	}
	
	
	
	@RequestMapping(params="upload", method = RequestMethod.POST)
	public ModelAndView upload(@ModelAttribute(value="excelFile") ExcelFile excelFile,  ModelMap modelMap, HttpServletRequest request) 
	{
		
			File file = UploadFileHelper.simpleUpload(excelFile.getFile(), request, true, "ImageUpload",request.getSession());
			//System.out.println(file.getPath());
			ExcelHelper eh = new ExcelHelper(file.getAbsolutePath());
			ArrayList<errorExcel> lstLineError = new ArrayList<errorExcel>();
			List<AssetGeneralModel> lstAssetObject = null;
			try {
				lstAssetObject = eh.readData(AssetGeneralModel.class.getName(),"Sheet1",3,1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//Đọc được data
			if(lstAssetObject.size() > 0)
			{
				AssetGeneralInsertDao AssetDao = new AssetGeneralInsertDao();
				SystemControl sys = new SystemControl( request);
				String Company_CD = Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD);
				String id = Common.getDateCurrent("YYYYMMddHHmmSS");
				Department department = new Department();
				department.setCompany_cd(Company_CD);
				DepartmentSelectDao dmselect = new DepartmentSelectDao(department);
				List<Department> lst = new ArrayList<Department>();
				GroupAsset group = new GroupAsset();
				group.setCompany_cd(Company_CD);
				GroupAssetSelectDao gased = new GroupAssetSelectDao(group);
				List<GroupAsset> lstGroup = new ArrayList<>();
				
				try {
					lst = dmselect.excute();
					lstGroup = gased.excute();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for(int i=0;i<lstAssetObject.size();i++)
				{
					try
					{
						//Kiểm tra tài sản nằm trong đơn vị 
						lstAssetObject.get(i).setId(id);
						lstAssetObject.get(i).setCompany_CD(Company_CD);
						boolean NotreGet = false;  
						for(int e = 0;e<lst.size();e++)
						{
							if(lstAssetObject.get(i).getDepartment_name().trim().equals(lst.get(e).getDept_name().trim()))
							{
								NotreGet= true;
								lstAssetObject.get(i).setDepartment_cd(lst.get(e).getDept_cd());
							}
						}
						if(NotreGet==false)
						{
							Department dep = new Department();
							dep.setCompany_cd(Company_CD);
							String cd_dept = Common.getDateCurrent("YYYYMMddHHmmSS");
							dep.setDept_cd(cd_dept);
							dep.setDept_name(lstAssetObject.get(i).getDepartment_name().trim());
							DepartmentInsertDao depin = new DepartmentInsertDao(dep);
							depin.excute();
							lst = dmselect.excute();
							
							
							lstAssetObject.get(i).setDepartment_cd(cd_dept);
						}
						else
						{
							NotreGet=false;
						}
						
					    NotreGet = false;  
						for(int e = 0;e<lstGroup.size();e++)
						{
							if(lstAssetObject.get(i).getGroup_name().trim().equals(lstGroup.get(e).getGroup_name().trim()))
							{
								NotreGet= true;
								lstAssetObject.get(i).setGroup_id(lstGroup.get(e).getGroup_id());
							}
						}
						if(NotreGet==false)
						{
							GroupAsset gr = new GroupAsset();
							gr.setCompany_cd(Company_CD);
							String cd_gr = Common.getDateCurrent("YYYYMMddHHmmSS");
							gr.setGroup_id(cd_gr);
							gr.setGroup_name(lstAssetObject.get(i).getGroup_name().trim());
							GroupAssetInsertDao gin = new GroupAssetInsertDao(gr);
							gin.excute();
							lstGroup = gased.excute();			
							lstAssetObject.get(i).setGroup_id(cd_gr);
						}
						else
						{
							NotreGet=false;
						}
						lstAssetObject.get(i).setUser_insert_cd(Common.getSessionValue(request, SessionConstants.SESSION_USER_ID));
						lstAssetObject.get(i).setUser_update_cd(Common.getSessionValue(request, SessionConstants.SESSION_USER_ID));
						lstAssetObject.get(i).setInsert_dt(Common.getDateCurrent("YYYYMMdd"));
						lstAssetObject.get(i).setUpdate_dt(Common.getDateCurrent("YYYYMMdd"));
						AssetDao.excuteData(lstAssetObject.get(i));
					}
					catch (Exception e) 
					{
						// TODO: handle exception
						e.printStackTrace();
						errorExcel er = new errorExcel();
						int line = i+1;
						er.setLine(String.valueOf(line));
						er.setContent(e.toString());
						
						lstLineError.add(er);
					}
				}
				
				
				
				
			}
			
			//AssetGeneralSelectDao AssetSelectDao = new AssetGeneralSelectDao();
			//modelMap.addAttribute("listAssets",AssetSelectDao.excute() );
		
		
		ModelAndView mv = new ModelAndView();
		if(lstLineError.size() !=0)
		{
			mv.addObject(ParamsConstants.TITLE_SCREEN, "MÀN HÌNH NHẬP DỮ LIỆU TỪ FILE EXCEL");
			mv.setViewName("views/ImportCSVAssetGenneral.jsp");
			mv.addObject("lst", lstLineError);
		}
		else
		{
			mv.setViewName("redirect:/AssetManagementGeneral");
		}
		return mv;
	}
	
	@RequestMapping(params="back", method = RequestMethod.POST)
	public String back(HttpServletRequest request) 
	{
		return "redirect:/AssetManagementGeneral";
	}
}
