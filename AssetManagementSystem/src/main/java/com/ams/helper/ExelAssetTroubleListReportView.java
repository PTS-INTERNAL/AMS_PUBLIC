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
import com.ams.model.TroubleAssetModel;


public class ExelAssetTroubleListReportView extends AbstractXlsView {

 @Override
 protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
   HttpServletResponse response) throws Exception {
  
  response.setHeader("Content-disposition", "attachment; filename=\"QUAN_LY_SU_CO.xls\"");
  
  @SuppressWarnings("unchecked")
  List<TroubleAssetModel> list = (List<TroubleAssetModel>) model.get("userList");
  
  Sheet sheet = workbook.createSheet("Sheet1");
  
  Row header = sheet.createRow(2);
  header.createCell(0).setCellValue("STT");
  header.createCell(1).setCellValue("NHÓM");
  header.createCell(2).setCellValue("TÊN MÁY");
  header.createCell(3).setCellValue("MODEL");
  header.createCell(4).setCellValue("SỐ SERIES");
  header.createCell(5).setCellValue("ĐƠN VỊ");
  header.createCell(6).setCellValue("NGÀY SỰ CỐ");
  header.createCell(7).setCellValue("THỜI GIAN");
  header.createCell(8).setCellValue("SỰ CỐ");
  header.createCell(9).setCellValue("TRẠNG THÁI");

  								
  int rowNum = 3;
  int STT=1;
  for(TroubleAssetModel user : list){
   Row row = sheet.createRow(rowNum++);
   row.createCell(0).setCellValue(STT++);
   row.createCell(1).setCellValue(user.getAsset().getGroup_name());
   row.createCell(2).setCellValue(user.getAsset().getName());
   row.createCell(3).setCellValue(user.getAsset().getModel());
   row.createCell(4).setCellValue(user.getAsset().getSeries());
   row.createCell(5).setCellValue(user.getAsset().getDepartment_name());
   row.createCell(6).setCellValue(user.getDateTrouble());
   row.createCell(7).setCellValue(user.getTimeTrouble());
   row.createCell(8).setCellValue(user.getTrouble());
   row.createCell(9).setCellValue(user.getTroubleStatus());
  
   
  }
  
 }

}
