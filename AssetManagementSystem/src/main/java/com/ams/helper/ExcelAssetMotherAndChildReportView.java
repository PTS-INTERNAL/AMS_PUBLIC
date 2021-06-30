package com.ams.helper;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.ams.model.AssetGeneralModel;


public class ExcelAssetMotherAndChildReportView extends AbstractXlsView {

 @Override
 protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
   HttpServletResponse response) throws Exception {
  
  response.setHeader("Content-disposition", "attachment; filename=\"CONG_TY_ME_CON.xls\"");
  
  @SuppressWarnings("unchecked")
  List<AssetGeneralModel> list = (List<AssetGeneralModel>) model.get("userList");
  
  Sheet sheet = workbook.createSheet("Sheet1");
  
  Row header = sheet.createRow(2);
  header.createCell(0).setCellValue("STT");
  header.createCell(1).setCellValue("NHÓM");
  header.createCell(2).setCellValue("RFID");
  header.createCell(3).setCellValue("TÊN MÁY");
  header.createCell(4).setCellValue("MODEL MÁY");
  header.createCell(5).setCellValue("SỐ SERI");
  header.createCell(6).setCellValue("ĐƠN VỊ");
  header.createCell(7).setCellValue("MÃ SỐ KẾ TOÁN");
  header.createCell(8).setCellValue("THỜI ĐIỂM TĂNG");
  header.createCell(9).setCellValue("ĐƠN GIÁ");
  header.createCell(10).setCellValue("GHI CHÚ");
  									

  int rowNum = 3;
  int STT=1;
  for(AssetGeneralModel user : list){
   Row row = sheet.createRow(rowNum++);
   row.createCell(0).setCellValue(STT++);
   row.createCell(1).setCellValue(user.getGroup_name());
   row.createCell(2).setCellValue(user.getRFID());
   row.createCell(3).setCellValue(user.getName());
   row.createCell(4).setCellValue(user.getModel());
   row.createCell(5).setCellValue(user.getSeries());
   row.createCell(6).setCellValue(user.getDepartment_name());
   row.createCell(7).setCellValue(user.getAccountant_CD());
   row.createCell(8).setCellValue(user.getDateStart());
   row.createCell(9).setCellValue(user.getPrice());
   row.createCell(10).setCellValue(user.getNote());
   
  }
  
 }

}
