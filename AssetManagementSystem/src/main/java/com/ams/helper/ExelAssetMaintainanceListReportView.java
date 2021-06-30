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
import com.ams.model.AssetMaintanceModel;


public class ExelAssetMaintainanceListReportView extends AbstractXlsView {

 @Override
 protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
   HttpServletResponse response) throws Exception {
  
  response.setHeader("Content-disposition", "attachment; filename=\"QUAN_LY_BAO_TRI.xls\"");
  
  @SuppressWarnings("unchecked")
  List<AssetMaintanceModel> list = (List<AssetMaintanceModel>) model.get("userList");
  
  Sheet sheet = workbook.createSheet("Sheet1");
  
  Row header = sheet.createRow(2);
  header.createCell(0).setCellValue("STT");
  header.createCell(1).setCellValue("NHÓM");
  header.createCell(2).setCellValue("RFID");
  header.createCell(3).setCellValue("TÊN MÁY");
  header.createCell(4).setCellValue("MODEL MÁY");
  header.createCell(5).setCellValue("SỐ SERI");
  header.createCell(6).setCellValue("ĐƠN VỊ");
  header.createCell(7).setCellValue("NGÀY BẢO TRÌ");
  header.createCell(8).setCellValue("TRẠNG THÁI");

  									

  int rowNum = 3;
  int STT=1;
  for(AssetMaintanceModel user : list){
   Row row = sheet.createRow(rowNum++);
   row.createCell(0).setCellValue(STT++);
   row.createCell(1).setCellValue(user.getAsset().getGroup_name());
   row.createCell(2).setCellValue(user.getAsset().getRFID());
   row.createCell(3).setCellValue(user.getAsset().getName());
   row.createCell(4).setCellValue(user.getAsset().getModel());
   row.createCell(5).setCellValue(user.getAsset().getSeries());
   row.createCell(6).setCellValue(user.getAsset().getDepartment_name());
   row.createCell(7).setCellValue(user.getDay());
   if(user.getKeys()==null)
	   row.createCell(8).setCellValue("CHƯA BẢO TRÌ");
   else
	   row.createCell(8).setCellValue("ĐÃ BẢO TRÌ");
  }
 }

}
