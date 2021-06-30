package com.ams.helper;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.ams.model.AssetGeneralModel;
import com.ams.model.AssetMotherAndChilModel;
import com.ams.model.CompanyModel;
import com.ams.model.Department;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Header;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javafx.print.Collation;
import javafx.scene.text.TextAlignment;
import sun.font.FontFamily;




public class PdfExportAssetCompanyInternalView extends AbstractPdfView {

 @Override
	protected boolean generatesDownloadContent() {
		// TODO Auto-generated method stub
		return super.generatesDownloadContent();
	}

	@Override
	protected Document newDocument() {
		// TODO Auto-generated method stub
		return new Document(PageSize.A4.rotate(),10, 10, 10,10);
	}

	@Override
	protected PdfWriter newWriter(Document document, OutputStream os) throws DocumentException {
		// TODO Auto-generated method stub
		return super.newWriter(document, os);
	}

	@Override
	protected void prepareWriter(Map<String, Object> model, PdfWriter writer, HttpServletRequest request)
			throws DocumentException {
		// TODO Auto-generated method stub
		super.prepareWriter(model, writer, request);
	}

	@Override
	protected int getViewerPreferences() {
		// TODO Auto-generated method stub
		return super.getViewerPreferences();
	}

	@Override
	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
		// TODO Auto-generated method stub
		super.buildPdfMetadata(model, document, request);
	}

@Override
 protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request,
   HttpServletResponse response) throws Exception {
  String dateTimeString  = Common.getDateCurrent(ParamsConstants.CD_FULL_FORMAT);
  String attribute = "attachment; filename=\""+ dateTimeString+".pdf";
  response.setHeader("Content-Disposition", attribute);
  document.addTitle("FILE PDF ASSET");
  document.addSubject("TIEU DE SUBJECT");
  document.addTitle("TIEU DE TITLE");

  //Start Header
  Table tblCompanyName = new Table(2);
  tblCompanyName.setWidth(100);
  tblCompanyName.setBorder(0);
  Font font = ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 18);
  Paragraph content;
  Cell cell;
  //--------------
  String reportName = (String) model.get("reportName");
  String date = Common.getDateCurrent(ParamsConstants.DATE_DB_FORMAT);
  String year = date.substring(0,4);
  String month = date.substring(4,6);
  Phrase phrase = new Phrase();
  phrase.add(new Chunk("TỔNG CÔNG TY CP MAY VIỆT TIẾN",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 12)));
  phrase.add(new Chunk("\n"));
  phrase.add(new Chunk("PHÒNG CƠ ĐIỆN",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
  phrase.add(new Chunk("\n"));
  phrase.add(new Chunk(reportName+"_"+month +"_"+ year,  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 15)));
  phrase.add(new Chunk("\n"));
  cell = new Cell(phrase);
  cell.setBorder(0);
  cell.setHorizontalAlignment(Element.ALIGN_LEFT);
  tblCompanyName.addCell(cell);
  document.add(tblCompanyName);

//  //---------------
  Table tblheader1 = new Table(1);
  tblheader1.setWidth(100);
  tblheader1.setTop(10);
  tblheader1.setBorder(0);
//  //-------------------
  font.setSize(18);
  phrase = new Phrase();
  phrase.add(new Chunk("",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 15)));
  cell = new Cell(phrase);
  cell.setBorder(0);
  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
  tblheader1.addCell(cell);
  tblheader1.setBottom(20);
  tblheader1.setPadding(5);
  //-------------------
  document.add(tblheader1);
  Table tableEmpty = new Table(1);
  tableEmpty.addCell(new Paragraph(" ",ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
  document.add(tableEmpty);
  //-------------------
  List<Department>  lstDept = (List<Department>) model.get("lstDept");
  List<AssetMotherAndChilModel> lstAsset = (List<AssetMotherAndChilModel>) model.get("lstAsset");

  @SuppressWarnings("unchecked")
  Table table = new Table(lstDept.size()+5);
  table.setWidth(100);
  table.setPadding(2);
  table.setTop(120);
  
  int[] columnArray = new int[lstDept.size()+5];
  
  columnArray[0] = 50;
  columnArray[1] = 80;
  columnArray[2] = 300;
  columnArray[3] = 100;
  for(int i = 4; i<4+lstDept.size();i++) {
	  columnArray[i] = 80;
  }
  columnArray[4+lstDept.size()] = 80;
  
  table.setWidths(columnArray);


  
  Paragraph p = new Paragraph("STT",ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10));
  Cell sttCell = new Cell(p);
  sttCell.setRowspan(2);
  sttCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
  sttCell.setHorizontalAlignment(Element.ALIGN_CENTER);
  table.addCell(sttCell); 
  
  p = new Paragraph("NHÓM",ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10));
  Cell groupCell = new Cell(p);
  groupCell.setRowspan(2);
  groupCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
  groupCell.setHorizontalAlignment(Element.ALIGN_CENTER);
  table.addCell(groupCell); 
  
  p = new Paragraph("TÊN THIẾT BỊ",ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10));
  Cell nameCell = new Cell(p);
  nameCell.setRowspan(2);
  nameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
  nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
  table.addCell(nameCell); 
  
  p = new Paragraph("KÝ HIỆU",ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10));
  Cell signCell = new Cell(p);
  signCell.setRowspan(2);
  signCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
  signCell.setHorizontalAlignment(Element.ALIGN_CENTER);
  table.addCell(signCell); 
  
  p = new Paragraph("SỐ LƯỢNG",ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10));
  Cell numberCell = new Cell(p);
  numberCell.setColspan(lstDept.size());
  numberCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
  numberCell.setHorizontalAlignment(Element.ALIGN_CENTER);
  table.addCell(numberCell); 
  
  p = new Paragraph("TỔNG CỘNG",ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10));
  Cell totalCell = new Cell(p);
  totalCell.setRowspan(2);
  totalCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
  totalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
  table.addCell(totalCell); 
  for(int i =0;i<lstDept.size();i++)
  {
	  Paragraph p1 = new Paragraph(lstDept.get(i).getDept_name(),ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10));
	  Cell celll = new Cell(p1);
	  celll.setVerticalAlignment(Element.ALIGN_MIDDLE);
	  celll.setHorizontalAlignment(Element.ALIGN_CENTER);
     
	  table.addCell(celll);
  }


  
  for(int i =0;i<lstAsset.size();i++)
  {
	  p = new Paragraph(String.valueOf(i+1),ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_NORMAL, 10));
	  Cell sttCellData = new Cell(p);
	  sttCellData.setVerticalAlignment(Element.ALIGN_MIDDLE);
	  sttCellData.setHorizontalAlignment(Element.ALIGN_CENTER);
	  table.addCell(sttCellData); 
	  
	  p = new Paragraph(lstAsset.get(i).getAsset().getGroup_name().trim(),ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_NORMAL, 10));
	  Cell groupCellData = new Cell(p);
	  groupCellData.setVerticalAlignment(Element.ALIGN_MIDDLE);
	  groupCellData.setHorizontalAlignment(Element.ALIGN_LEFT);
	  table.addCell(groupCellData); 
	  
	  p = new Paragraph(lstAsset.get(i).getAsset().getName().trim(),ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_NORMAL, 10));
	  Cell nameCellData = new Cell(p);
	  nameCellData.setVerticalAlignment(Element.ALIGN_MIDDLE);
	  nameCellData.setHorizontalAlignment(Element.ALIGN_LEFT);
	  table.addCell(nameCellData); 
	  
	  p = new Paragraph(lstAsset.get(i).getAsset().getModel(),ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_NORMAL, 10));
	  Cell signCellData = new Cell(p);
	  signCellData.setVerticalAlignment(Element.ALIGN_MIDDLE);
	  signCellData.setHorizontalAlignment(Element.ALIGN_CENTER);
	  table.addCell(signCellData); 
	  
	 
	  
	
	  for(int j =0;j<lstAsset.get(i).getLstColumn().size();j++)
	  {
		  Paragraph p1 = new Paragraph(lstAsset.get(i).getLstColumn().get(j),ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_NORMAL, 10));
		  Cell celll = new Cell(p1);
		  celll.setVerticalAlignment(Element.ALIGN_MIDDLE);
		  celll.setHorizontalAlignment(Element.ALIGN_CENTER);
	     
		  table.addCell(celll);
	  }
	  
	  p = new Paragraph(lstAsset.get(i).getSum()+"", ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_NORMAL, 10));
	  Cell totalCellData = new Cell(p);
	  totalCellData.setVerticalAlignment(Element.ALIGN_MIDDLE);
	  totalCellData.setHorizontalAlignment(Element.ALIGN_CENTER);
	  table.addCell(totalCellData); 
  }
  
  
  document.add(table);
  
 }

}