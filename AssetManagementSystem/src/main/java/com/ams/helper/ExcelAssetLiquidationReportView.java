
package com.ams.helper;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.ams.dao.AssetLiquidationModel;





public class ExcelAssetLiquidationReportView  extends AbstractXlsView {

 @Override
 protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
   HttpServletResponse response) throws Exception {
  
  response.setHeader("Content-disposition", "attachment; filename=\"DANHSACHTHANHLY.xls\"");
  
  @SuppressWarnings("unchecked")
  List<AssetLiquidationModel> list = (List<AssetLiquidationModel>) model.get("userList");
  
  Sheet sheet = workbook.createSheet("Sheet1");
  
  Row header = sheet.createRow(2);
  header.createCell(0).setCellValue("STT");
  header.createCell(1).setCellValue("RFID");
  header.createCell(2).setCellValue("MODEL MÁY");
  header.createCell(3).setCellValue("SỐ SERI");
  header.createCell(4).setCellValue("TÊN MÁY");
  header.createCell(5).setCellValue("ĐƠN VỊ");
  header.createCell(6).setCellValue("TRẠNG THÁI");

  									

  int rowNum = 3;
  int STT=1;
  for(AssetLiquidationModel user : list){
   Row row = sheet.createRow(rowNum++);
   row.createCell(0).setCellValue(STT++);
   row.createCell(1).setCellValue(user.getAsset().getRFID());
   row.createCell(2).setCellValue(user.getAsset().getModel());
   row.createCell(3).setCellValue(user.getAsset().getSeries());
   row.createCell(4).setCellValue(user.getAsset().getName());
   row.createCell(5).setCellValue(user.getAsset().getDepartment_name());
   row.createCell(6).setCellValue(user.getStatus());
  }
 }

}
