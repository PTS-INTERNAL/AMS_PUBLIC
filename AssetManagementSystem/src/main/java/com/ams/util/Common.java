package com.ams.util;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar; 
public class Common {
	
	public static void showMessageError(ModelAndView mv, String message)
	{
		mv.addObject(ParamsConstants.MESSAGE_ERROR, message);
	}
	
	public static void showMessageNotification(ModelAndView mv, String message)
	{
		mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, message);
	}
	
	
	
	public static boolean isHavePermission(HttpServletRequest request, String role, String service )
	{
		AuthenticationLogin auth = new AuthenticationLogin(request);
		if(auth.isLogin())
		{
			String user_cd = (String) request.getSession().getAttribute(SessionConstants.SESSION_USER_ID);
			String company_cd = (String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD);
			boolean isPermission = auth.CheckPermissionIsTrue(company_cd, user_cd, role, service);
			//Kiểm tra quyền hạn
			if(isPermission)
			{
				return true;
			}
			else
			{
				return false;
			}	
		}
		else
		{
			
			return false;
		}
	
	}
	
	public static boolean CompareDate(String date1, String fmt1, String date2, String fmt2) throws ParseException
	{
		int dateConvert1 = Integer.parseInt(Common.ConvertStringToDateStr(date1, fmt1,ParamsConstants.CD_FULL_DATE));
		int dateConvert2 = Integer.parseInt(Common.ConvertStringToDateStr(date2, fmt2,ParamsConstants.CD_FULL_DATE));
		
		if(dateConvert1 < dateConvert2) return true;
		return false;
	}
	
	
	
	public static boolean isNotCheckEmpty(String str) {
		if(str != null && str.trim().length()>0)
		{
			return true;
		}
		return false;
	}
	public static boolean isEmpty(String str) {
		if(str == null) 
		{
			return true;
		}
		if(str.trim().length()==0)
		{
			return true;
		}
		return false;
		
	}
	
	public static boolean isNotEmpty(String str) {
		if(str != null && str.trim().length()>0) 
		{
			return true;
		}
		return false;
		
	}
	
	public static String getDateCurrent(String format)
	{
	     Date date = Calendar.getInstance().getTime();  
	     DateFormat dateFormat = new SimpleDateFormat(format);  
	     String strDate = dateFormat.format(date);  
			//System.out.println(strDate);
		 return strDate;
	}
	
	public static String convertDateToString(Date date, String format)
	{
	     DateFormat dateFormat = new SimpleDateFormat(format); 
	     String strDate = dateFormat.format(date);  
			//System.out.println(strDate);
		 return strDate;
	}
	
	public static String convertStringToDateString(String dateInString, String format) throws ParseException
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
		Date date = formatter.parse(dateInString);
       // System.out.println(date);
        //System.out.println(formatter.format(date));
        date.setMonth(date.getMonth()+1);
		 return formatter.format(date);
	}
	
	public static Date convertStringToDate(String dateInString, String formatOriginal) throws ParseException
	{
		SimpleDateFormat formatter = new SimpleDateFormat(formatOriginal);
		Date date = formatter.parse(dateInString);
		 return date;
	}
	
	public static String plusDayFromString(String dateInString, String formatOriginal, int Days) throws ParseException
	{
		//Given Date in String format
		String oldDate = dateInString;  
		//System.out.println("Date before Addition: "+oldDate);
		//Specifying date format that matches the given date
		SimpleDateFormat sdf = new SimpleDateFormat(formatOriginal);
		Calendar c = Calendar.getInstance();
		try{
		   //Setting the date to the given date
		   c.setTime(sdf.parse(oldDate));
		}catch(ParseException e){
			e.printStackTrace();
		 }
		   
		//Number of Days to add
		c.add(Calendar.DAY_OF_MONTH, Days);  
		//Date after adding the days to the given date
		String newDate = sdf.format(c.getTime());  
		//Displaying the new Date after addition of Days
		//System.out.println("Date after Addition: "+newDate);
		return newDate;
	}
	
	public static String StandarStringDate(String strDate, String OrigalStr, String NewStr) throws ParseException
	{
		Date date = convertStringToDate(strDate, OrigalStr);
		Calendar cal = Calendar.getInstance(); cal.setTime(date); // don't forget this if date is arbitrary e.g. 01-01-2014
		cal.setTime(date);
		
		// empno = Common.convertDateToString(date, "dd/mm/yyyy");
		int month = cal.get(Calendar.MONTH) + 1;
		String empnos = cal.get(Calendar.DAY_OF_MONTH) +"/" + month + "/" + cal.get(Calendar.YEAR);
		return  Common.convertStringToDateString(empnos, "dd/mm/yyyy");
	}
	
	public static String ConvertStringToDateStr(String date, String fmt_src, String fmt_des) throws ParseException
	{
//		SimpleDateFormat formatter = new SimpleDateFormat(fmt_src);
//		Date DateConvert = formatter.parse(date);
//		SimpleDateFormat formatter2 = new SimpleDateFormat(fmt_des);  
//	    String strDate = formatter2.format(DateConvert);  
	    
	    SimpleDateFormat sdf1 = new SimpleDateFormat(fmt_src);
	    SimpleDateFormat sdf2 = new SimpleDateFormat(fmt_des);
	    String ds2 = sdf2.format(sdf1.parse(date));
	    
	    return ds2;
		
	}
	
	public static String GetCompanyCDAction(HttpServletRequest request)
	{
		return (String)request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD);
	}
	
	public static String getSessionValue(HttpServletRequest request, String param)
	{
		return (String)request.getSession().getAttribute(param);
	}
	public static void removeSessionValue(HttpServletRequest request, String param)
	{
		 request.getSession().setAttribute(param,null);
	}
	


}
