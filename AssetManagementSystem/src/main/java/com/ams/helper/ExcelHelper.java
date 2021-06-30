package com.ams.helper;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;


import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook; 
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import com.ams.util.Common;

import org.apache.poi.ss.formula.functions.T;

public class ExcelHelper {
	private List<String> fieldNames = new ArrayList<String>();
	 private Workbook workbook = null;
	 private String workbookName = "";

	 public ExcelHelper(String workbookName) {
	  this.workbookName = workbookName;
	  initialize();
	 }

	 private void initialize() {
	  setWorkbook(new HSSFWorkbook());
	 }

	 public void closeWorksheet() {
	  FileOutputStream fileOut;
	  try {
	   fileOut = new FileOutputStream(getWorkbookName());
	   getWorkbook().write(fileOut);
	   fileOut.close();
	  } catch (FileNotFoundException e) {
	   e.printStackTrace();
	  } catch (IOException e) {
	   e.printStackTrace();
	  }
	 }

	 private boolean setupFieldsForClass(Class<?> clazz) throws Exception {
	  Field[] fields = clazz.getDeclaredFields();
	  for (int i = 0; i < fields.length; i++) {
	   fieldNames.add(fields[i].getName());
	  }
	  return true;
	 }

	 private Sheet getSheetWithName(String name) {
	  Sheet sheet = null;
	  for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
	   if (name.replaceAll("com.asset.management.model.", "").compareTo(workbook.getSheetName(i)) == 0) {
	    sheet = workbook.getSheetAt(i);
	    break;
	   }
	  }
	  return sheet;
	 }

	 private void initializeForRead() throws InvalidFormatException, IOException {
	  InputStream inp = new FileInputStream(getWorkbookName());
	  workbook = WorkbookFactory.create(inp);
	 }

	 //Read file excel
	 @SuppressWarnings("unchecked")
	 public <T> List<T> readData(String classname, String sheetNa, int startLine, int startColumn ) throws Exception {
	  initializeForRead();
	  //Lấy cái sheet
	  Sheet sheet = getSheetWithName(sheetNa);
	  Class<?> clazz = Class.forName(classname);
	  setupFieldsForClass(clazz);
	  List<T> result = new ArrayList<T>();
	  Row row;
	  int rowCount=startLine; 
	  row = sheet.getRow(rowCount);	
	  boolean isRead = true;
	  
	 
	  while (isRead)
	  {
		  T one = (T) clazz.newInstance();
		  int colCount = 0;
		  result.add(one);
		 // for (Cell cell : row) 
		  for (int i= startColumn;i<row.getLastCellNum();i++) 
		  {
			  System.out.println(i);
			  try {
				  Cell cell = row.getCell(i);
				  String value="";
				  String fieldName = fieldNames.get(colCount++);
				  Method method = constructMethod(clazz, fieldName);
				  if(cell!=null)
				  {
					  int type = cell.getCellType();
					  
					  // if (type == Cell.CELL_TYPE_STRING) {
					  //cell.setCellType(Cell.CELL_TYPE_STRING);
					  CellType cells = cell.getCellTypeEnum();
					  String empno="";
					 
					  switch(cells) 
					  {
					  	
				        case NUMERIC:
				        	if(fieldName.equals("DateStart"))
				        	{
				        		Date date = cell.getDateCellValue();
				        		Calendar cal = Calendar.getInstance(); cal.setTime(date); // don't forget this if date is arbitrary e.g. 01-01-2014
				        		cal.setTime(date);
				        		
				        		// empno = Common.convertDateToString(date, "dd/mm/yyyy");
				        		int month = cal.get(Calendar.MONTH) + 1;
				        		String empnos = cal.get(Calendar.DAY_OF_MONTH) +"/" + month + "/" + cal.get(Calendar.YEAR);
				        		empno = Common.convertStringToDateString(empnos, "dd/mm/yyyy");
				        	}
				        		else
				        	{
				        		 int Val = (int) cell.getNumericCellValue();
				        		 empno = String.valueOf(Val);
				        	}
				           
				           
				            break;
				        case STRING:
				        	empno = cell.getStringCellValue();	           
				            break;
					  }
		   
				      value = empno;
				      
				  }
				  else
				  {
					  value = "string";
				  }
				     Object[] values = new Object[1];
				     values[0] = value;
				     method.invoke(one, values);
				  
//		    } else if (type == Cell.CELL_TYPE_NUMERIC) {
//		     Double num = cell.getNumericCellValue();
//		     Class<?> returnType = getGetterReturnClass(clazz, fieldName);
//		     if (returnType == int.class || returnType == Integer.class) {
//		      method.invoke(one, num.intValue());
//		     } else if (returnType == double.class
//		       || returnType == Double.class) {
//		      method.invoke(one, num);
//		     } else if (returnType == float.class
//		       || returnType == Float.class) {
//		      method.invoke(one, num.floatValue());
//		     } else if (returnType == long.class
//		       || returnType == Long.class) {
//		      method.invoke(one, num.longValue());
//		     } else if (returnType == Date.class) {
//		      Date date = HSSFDateUtil.getJavaDate(cell
//		        .getNumericCellValue());
//		      method.invoke(one, date);
//		     }
//		    } else if (type == Cell.CELL_TYPE_BOOLEAN) {
//		     boolean num = cell.getBooleanCellValue();
//		     Object[] values = new Object[1];
//		     values[0] = num;
//		     method.invoke(one, values);
//		    }
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Bị lỗi dòng: " + rowCount);
			}
			  
	   }
		  rowCount++;
		   row = sheet.getRow(rowCount);
		   if(row ==null || row.getCell(1).toString().length()==0)
		   {
			  isRead = false;
		   }
	  }
	   
	  
	  return result;
	 }

	 private Class<?> getGetterReturnClass(Class<?> clazz, String fieldName) {
	  String methodName = "get" + capitalize(fieldName);
	  String methodIsName = "is" + capitalize(fieldName);
	  Class<?> returnType = null;
	  for (Method method : clazz.getMethods()) {
	   if (method.getName().equals(methodName)
	     || method.getName().equals(methodIsName)) {
	    returnType = method.getReturnType();
	    break;
	   }
	  }
	  return returnType;
	 }

	 @SuppressWarnings("unchecked")
	 private Method constructMethod(Class clazz, String fieldName)
	   throws SecurityException, NoSuchMethodException {
	  Class<?> fieldClass = getGetterReturnClass(clazz, fieldName);
	  return clazz.getMethod("set" + capitalize(fieldName), fieldClass);
	 }

	 public <T> void writeData(List<T> data) throws Exception {
	  try {
	   Sheet sheet = getWorkbook().createSheet(
	     data.get(0).getClass().getName());
	   setupFieldsForClass(data.get(0).getClass());
	   int rowCount = 0;
	   int columnCount = 0;
	   Row row = sheet.createRow(rowCount++);
	   for (String fieldName : fieldNames) {
	    Cell cel = row.createCell(columnCount++);
	    cel.setCellValue(fieldName);
	   }
	   Class<? extends Object> classz = data.get(0).getClass();
	   for (T t : data) {
	    row = sheet.createRow(rowCount++);
	    columnCount = 0;
	    for (String fieldName : fieldNames) {
	     Cell cel = row.createCell(columnCount);
	     Method method = classz.getMethod("get"
	       + capitalize(fieldName));
	     Object value = method.invoke(t, (Object[]) null);
	     if (value != null) {
	      if (value instanceof String) {
	       cel.setCellValue((String) value);
	      } else if (value instanceof Long) {
	       cel.setCellValue((Long) value);
	      } else if (value instanceof Integer) {
	       cel.setCellValue((Integer) value);
	      } else if (value instanceof Double) {
	       cel.setCellValue((Double) value);
	      } else if (value instanceof Date) {
	       cel.setCellValue((Date) value);
	       CellStyle styleDate = workbook.createCellStyle();
	       DataFormat dataFormatDate = workbook
	         .createDataFormat();
	       styleDate.setDataFormat(dataFormatDate
	         .getFormat("m/d/yy"));
	       cel.setCellStyle(styleDate);
	      } else if (value instanceof Boolean) {
	       cel.setCellValue((Boolean) value);
	      }
	     }
	     columnCount++;
	    }
	   }

	   // Autofit
	   for (int i = 0; i < fieldNames.size(); i++)
	    sheet.autoSizeColumn(i);

	   FileOutputStream out = new FileOutputStream(new File(workbookName));
	   workbook.write(out);
	   out.close();
	   workbook.close();
	  } catch (Exception e) {
	   //System.out.println(e.getMessage());
	  }
	 }

	 public String capitalize(String string) {
	  String capital = string.substring(0, 1).toUpperCase();
	  return capital + string.substring(1);
	 }

	 public String getWorkbookName() {
	  return workbookName;
	 }

	 public void setWorkbookName(String workbookName) {
	  this.workbookName = workbookName;
	 }

	 void setWorkbook(Workbook workbook) {
	  this.workbook = workbook;
	 }

	 public Workbook getWorkbook() {
	  return workbook;
	 }

	}

