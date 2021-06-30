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
import com.ams.model.BorrowAssetModel;


public class ExcelBorrowListReportView extends AbstractXlsView {

 @Override
 protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
   HttpServletResponse response) throws Exception {
  
  response.setHeader("Content-disposition", "attachment; filename=\"QUAN_LY_CHO_MUON.xls\"");
  
  @SuppressWarnings("unchecked")
  List<BorrowAssetModel> list = (List<BorrowAssetModel>) model.get("userList");
  
  Sheet sheet = workbook.createSheet("Sheet1");
  
  Row header = sheet.createRow(2);
  header.createCell(0).setCellValue("STT");
  header.createCell(1).setCellValue("MÃ LỆNH");
  header.createCell(2).setCellValue("DN CHO MƯỢN");
  header.createCell(3).setCellValue("ĐƠN VỊ CHO MƯỢN");
  header.createCell(4).setCellValue("NGÀY CHO MƯỢN");
  header.createCell(5).setCellValue("TÊN TÀI SẢN");
  header.createCell(6).setCellValue("RFID");
  header.createCell(7).setCellValue("SERIES");
  header.createCell(8).setCellValue("MODEL");
  header.createCell(9).setCellValue("DN MƯỢN");
  header.createCell(10).setCellValue("ĐƠN VỊ MƯỢN");
  header.createCell(11).setCellValue("NGÀY TRẢ KẾ HOẠCH");
  header.createCell(12).setCellValue("TRẠNG THÁI");
  									

  int rowNum = 3;
  int STT=1;
  for(BorrowAssetModel user : list){
   Row row = sheet.createRow(rowNum++);
   row.createCell(0).setCellValue(STT++);
   row.createCell(1).setCellValue(user.getNumber_on());
   row.createCell(2).setCellValue(user.getCmpn_master().getCompany_shortname());
   row.createCell(3).setCellValue(user.getDept_master().getDept_name());
   row.createCell(4).setCellValue(user.getDate_start());
   row.createCell(5).setCellValue(user.getAsset().getName());
   row.createCell(6).setCellValue(user.getAsset().getRFID());
   row.createCell(7).setCellValue(user.getAsset().getSeries());
   row.createCell(8).setCellValue(user.getAsset().getModel());
   row.createCell(9).setCellValue(user.getCmpn_client().getCompany_shortname());
   row.createCell(10).setCellValue(user.getDept_client().getDept_name());
   row.createCell(11).setCellValue(user.getDate_end());
   if(user.getStatus().equals("1"))
   {
	   row.createCell(12).setCellValue("CHƯA PHÊ DUYỆT");
   }
   if(user.getStatus().equals("2"))
   {
	   row.createCell(12).setCellValue("CHỜ XÁC NHẬN");
   }
   if(user.getStatus().equals("3"))
   {
	   row.createCell(12).setCellValue("ĐANG CHO MƯỢN");
   }
   if(user.getStatus().equals("4"))
   {
	   row.createCell(12).setCellValue("XÁC NHẬN TRẢ");
   }
   if(user.getStatus().equals("5"))
   {
	   row.createCell(12).setCellValue("ĐÃ TRẢ");
   }
   if(user.getStatus().equals("6"))
   {
	   row.createCell(12).setCellValue("KHÔNG DUYỆT");
   }
   
   
  }
  
 }

}
