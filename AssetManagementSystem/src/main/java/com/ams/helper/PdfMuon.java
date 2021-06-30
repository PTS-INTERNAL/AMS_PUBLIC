package com.ams.helper;

import java.awt.Color;
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
import com.ams.model.BorrowAssetModel;
import com.ams.model.ExportBorrowMove;
import com.ams.model.ExportLoanMove;
import com.ams.model.InventoryCheckingRealtimeModel;
import com.ams.model.LoanAssetModel;
import com.ams.model.TroubleAssetModel;
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

public class PdfMuon extends AbstractPdfView {

 @Override
	protected boolean generatesDownloadContent() {
		// TODO Auto-generated method stub
		return super.generatesDownloadContent();
	}

	@Override
	protected Document newDocument() {
		// TODO Auto-generated method stub
		return new Document(PageSize.A4,10, 10, 10,10);
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
  String time = Common.getDateCurrent(ParamsConstants.CD_FULL_DATE);
  response.setHeader("Content-Disposition", "attachment; filename=\"LenhDieuDong.pdf\"");
  document.addTitle("FILE PDF ASSET TROUBLE");
  document.addSubject("TIEU DE SUBJECT");
  document.addTitle("TIEU DE TITLE");
  //document.setPageSize(PageSize.A4.rotate());
  ExportLoanMove  exObject = (ExportLoanMove) model.get("object");

  //Start Header
  Table tblCompanyName = new Table(2);
  tblCompanyName.setWidth(100);
  tblCompanyName.setBorder(0);
  Font font = ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 18);
  Paragraph content;
  Cell cell;
  //--------------
  Phrase phrase = new Phrase();
  phrase.add(new Chunk("TỔNG CÔNG TY CP MAY VIỆT TIẾN",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 13)));
  phrase.add(new Chunk("\n"));
  phrase.add(new Chunk("Số:"+exObject.getBorrowCoupon().getNumber_no().trim()+"/CĐ",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_NORMAL, 10)));
  
  
  
  cell = new Cell(phrase);
  cell.setBorder(0);
  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
  tblCompanyName.addCell(cell);
  //--------------
  phrase = new Phrase();
  phrase.add(new Chunk("       Mẫu số: 13/CĐ",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_NORMAL, 10)));
  phrase.add(new Chunk("\n"));
  phrase.add(new Chunk("       Ban hành: 1/0",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_NORMAL, 10)));

  cell = new Cell(phrase);
  cell.setBorder(0);
  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
  tblCompanyName.addCell(cell);
  document.add(tblCompanyName);

  //---------------
  Table tblheader1 = new Table(1);
  tblheader1.setWidth(100);
  tblheader1.setTop(10);
  tblheader1.setBorder(0);
  //-------------------
  font.setSize(18);
  phrase = new Phrase();
  phrase.add(new Chunk("\nLỆNH ĐIỀU ĐỘNG KIÊM BIÊN BẢN XUẤT THIẾT BỊ\n",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 18)));
  cell = new Cell(phrase);
  cell.setBorder(0);
  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
  tblheader1.addCell(cell);
  tblheader1.setBottom(20);
  tblheader1.setPadding(5);
  //-------------------
  document.add(tblheader1);
  
  PdfPTable tablePDF = new PdfPTable(12);

  tablePDF.setWidthPercentage(70);
  tablePDF.setSpacingBefore(0f);
  tablePDF.setSpacingAfter(0f);
  PdfPCell cellPDF;
  
  String[] lstCode = exObject.getBorrowCoupon().getNumber_no().split("/");
  String code_print = lstCode[1].trim();
  
  if(code_print.equals("CM"))
  {
	  // first row
	  phrase = new Phrase();
	  phrase.add(new Chunk("BÀN GIAO",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
	  cellPDF = new PdfPCell(phrase);
	  cellPDF.setColspan(2);
	  cellPDF.setHorizontalAlignment(Element.ALIGN_RIGHT);
	  cellPDF.setPadding(5.0f);
	  cellPDF.setBorder(0);
	  tablePDF.addCell(cellPDF);
	  //------------------------
	  phrase = new Phrase();
	  phrase.add(new Chunk("X",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
	   cellPDF = new PdfPCell(phrase);
	  cellPDF.setColspan(1);
	  cellPDF.setHorizontalAlignment(Element.ALIGN_CENTER);
	  tablePDF.addCell(cellPDF);
	  //------------------------
	  phrase = new Phrase();
	  phrase.add(new Chunk("MƯỢN",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
	  cellPDF = new PdfPCell(phrase);
	  cellPDF.setColspan(2);
	  cellPDF.setHorizontalAlignment(Element.ALIGN_RIGHT);
	  cellPDF.setPadding(5.0f);
	  cellPDF.setBorder(0);
	  tablePDF.addCell(cellPDF);
	  //------------------------
	  tablePDF.addCell("");
	  //------------------------
	  phrase = new Phrase();
	  phrase.add(new Chunk("TRẢ",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
	  cellPDF = new PdfPCell(phrase);
	  cellPDF.setColspan(2);
	  cellPDF.setHorizontalAlignment(Element.ALIGN_RIGHT);
	  cellPDF.setPadding(5.0f);
	  cellPDF.setBorder(0);
	  tablePDF.addCell(cellPDF);
	  //------------------------
	  tablePDF.addCell("");
	  //------------------------
	  phrase = new Phrase();
	  phrase.add(new Chunk("THUÊ",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
	  cellPDF = new PdfPCell(phrase);
	  cellPDF.setColspan(2);
	  cellPDF.setHorizontalAlignment(Element.ALIGN_RIGHT);
	  cellPDF.setPadding(5.0f);
	  cellPDF.setBorder(0);
	  tablePDF.addCell(cellPDF);
	  //------------------------
	  tablePDF.addCell("");
	  //------------------------
	
	  document.add(tablePDF);
  }
  
  if(code_print.equals("M"))
  {
	  // first row
	  phrase = new Phrase();
	  phrase.add(new Chunk("BÀN GIAO",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
	  cellPDF = new PdfPCell(phrase);
	  cellPDF.setColspan(2);
	  cellPDF.setHorizontalAlignment(Element.ALIGN_RIGHT);
	  cellPDF.setPadding(5.0f);
	  cellPDF.setBorder(0);
	  tablePDF.addCell(cellPDF);
	  //------------------------
	  tablePDF.addCell("");
	  //------------------------
	  phrase = new Phrase();
	  phrase.add(new Chunk("MƯỢN",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
	  cellPDF = new PdfPCell(phrase);
	  cellPDF.setColspan(2);
	  cellPDF.setHorizontalAlignment(Element.ALIGN_RIGHT);
	  cellPDF.setPadding(5.0f);
	  cellPDF.setBorder(0);
	  tablePDF.addCell(cellPDF);
	  //------------------------
	  phrase = new Phrase();
	  phrase.add(new Chunk("X",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
	   cellPDF = new PdfPCell(phrase);
	  cellPDF.setColspan(1);
	  cellPDF.setHorizontalAlignment(Element.ALIGN_CENTER);
	  tablePDF.addCell(cellPDF);
	  //------------------------
	  phrase = new Phrase();
	  phrase.add(new Chunk("TRẢ",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
	  cellPDF = new PdfPCell(phrase);
	  cellPDF.setColspan(2);
	  cellPDF.setHorizontalAlignment(Element.ALIGN_RIGHT);
	  cellPDF.setPadding(5.0f);
	  cellPDF.setBorder(0);
	  tablePDF.addCell(cellPDF);
	  //------------------------
	  tablePDF.addCell("");
	  //------------------------
	  phrase = new Phrase();
	  phrase.add(new Chunk("THUÊ",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
	  cellPDF = new PdfPCell(phrase);
	  cellPDF.setColspan(2);
	  cellPDF.setHorizontalAlignment(Element.ALIGN_RIGHT);
	  cellPDF.setPadding(5.0f);
	  cellPDF.setBorder(0);
	  tablePDF.addCell(cellPDF);
	  //------------------------
	  tablePDF.addCell("");
	  //------------------------
	
	  document.add(tablePDF);
  }
  
  if(code_print.equals("TH"))
  {
	  // first row
	  phrase = new Phrase();
	  phrase.add(new Chunk("BÀN GIAO",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
	  cellPDF = new PdfPCell(phrase);
	  cellPDF.setColspan(2);
	  cellPDF.setHorizontalAlignment(Element.ALIGN_RIGHT);
	  cellPDF.setPadding(5.0f);
	  cellPDF.setBorder(0);
	  tablePDF.addCell(cellPDF);
	  //------------------------
	  tablePDF.addCell("");
	  //------------------------
	  phrase = new Phrase();
	  phrase.add(new Chunk("MƯỢN",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
	  cellPDF = new PdfPCell(phrase);
	  cellPDF.setColspan(2);
	  cellPDF.setHorizontalAlignment(Element.ALIGN_RIGHT);
	  cellPDF.setPadding(5.0f);
	  cellPDF.setBorder(0);
	  tablePDF.addCell(cellPDF);
	  //------------------------
	  tablePDF.addCell("");
	  //------------------------
	  phrase = new Phrase();
	  phrase.add(new Chunk("TRẢ",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
	  cellPDF = new PdfPCell(phrase);
	  cellPDF.setColspan(2);
	  cellPDF.setHorizontalAlignment(Element.ALIGN_RIGHT);
	  cellPDF.setPadding(5.0f);
	  cellPDF.setBorder(0);
	  tablePDF.addCell(cellPDF);
	  //------------------------
	  tablePDF.addCell("");
	  //------------------------
	  phrase = new Phrase();
	  phrase.add(new Chunk("THUÊ",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
	  cellPDF = new PdfPCell(phrase);
	  cellPDF.setColspan(2);
	  cellPDF.setHorizontalAlignment(Element.ALIGN_RIGHT);
	  cellPDF.setPadding(5.0f);
	  cellPDF.setBorder(0);
	  tablePDF.addCell(cellPDF);
	  //------------------------
	  phrase = new Phrase();
	  phrase.add(new Chunk("X",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
	   cellPDF = new PdfPCell(phrase);
	  cellPDF.setColspan(1);
	  cellPDF.setHorizontalAlignment(Element.ALIGN_CENTER);
	  tablePDF.addCell(cellPDF);
	  //------------------------
	
	  document.add(tablePDF);
  }
  
  if(code_print.equals("T"))
  {
	  // first row
	  phrase = new Phrase();
	  phrase.add(new Chunk("BÀN GIAO",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
	  cellPDF = new PdfPCell(phrase);
	  cellPDF.setColspan(2);
	  cellPDF.setHorizontalAlignment(Element.ALIGN_RIGHT);
	  cellPDF.setPadding(5.0f);
	  cellPDF.setBorder(0);
	  tablePDF.addCell(cellPDF);
	  //------------------------
	  tablePDF.addCell("");
	  //------------------------
	  phrase = new Phrase();
	  phrase.add(new Chunk("MƯỢN",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
	  cellPDF = new PdfPCell(phrase);
	  cellPDF.setColspan(2);
	  cellPDF.setHorizontalAlignment(Element.ALIGN_RIGHT);
	  cellPDF.setPadding(5.0f);
	  cellPDF.setBorder(0);
	  tablePDF.addCell(cellPDF);
	  //------------------------
	  tablePDF.addCell("");
	  //------------------------
	  phrase = new Phrase();
	  phrase.add(new Chunk("TRẢ",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
	  cellPDF = new PdfPCell(phrase);
	  cellPDF.setColspan(2);
	  cellPDF.setHorizontalAlignment(Element.ALIGN_RIGHT);
	  cellPDF.setPadding(5.0f);
	  cellPDF.setBorder(0);
	  tablePDF.addCell(cellPDF);
	  //------------------------
	  phrase = new Phrase();
	  phrase.add(new Chunk("X",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
	   cellPDF = new PdfPCell(phrase);
	  cellPDF.setColspan(1);
	  cellPDF.setHorizontalAlignment(Element.ALIGN_CENTER);
	  tablePDF.addCell(cellPDF);
	  //------------------------
	  phrase = new Phrase();
	  phrase.add(new Chunk("THUÊ",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
	  cellPDF = new PdfPCell(phrase);
	  cellPDF.setColspan(2);
	  cellPDF.setHorizontalAlignment(Element.ALIGN_RIGHT);
	  cellPDF.setPadding(5.0f);
	  cellPDF.setBorder(0);
	  tablePDF.addCell(cellPDF);
	  //------------------------
	  tablePDF.addCell("");
	  //------------------------
	
	  document.add(tablePDF);
  }
  
  
  tblheader1 = new Table(1);
  tblheader1.setWidth(80);
  tblheader1.setTop(20);
  tblheader1.setBorder(0);
  //-------------------
  font.setSize(18);
  phrase = new Phrase();
  phrase.add(new Chunk("\nLÝ DO ĐIỀU ĐỘNG:  ",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
  phrase.add(new Chunk(exObject.getBorrowCoupon().getReason(),  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_NORMAL, 10)));
  phrase.add(new Chunk("\nĐIỀU TỪ ĐƠN VỊ: ",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
  phrase.add(new Chunk(exObject.getBorrowCoupon().getCmpn_master().getCompany_name(),  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_NORMAL, 10)));
  phrase.add(new Chunk("\nĐẾ ĐƠN VỊ: ",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
  phrase.add(new Chunk(exObject.getBorrowCoupon().getCmpn_client().getCompany_name(),  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_NORMAL, 10)));
  phrase.add(new Chunk("\nTHỜI GIAN TỪ: ",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
  phrase.add(new Chunk(exObject.getBorrowCoupon().getDate_start(),  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_NORMAL, 10)));
  phrase.add(new Chunk("                                                                    ĐẾN : ",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
  phrase.add(new Chunk(exObject.getBorrowCoupon().getDate_end_schedule(),  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_NORMAL, 10)));
  cell = new Cell(phrase);
  cell.setBorder(0);
  tblheader1.addCell(cell);
  //-------------------
  document.add(tblheader1);
  
  
  tblheader1 = new Table(1);
  tblheader1.setWidth(100);
  tblheader1.setBorder(0);
  //-------------------
  font.setSize(18);
  phrase = new Phrase();
  phrase.add(new Chunk("GIÁM ĐỊNH THIẾT BỊ VÀ PHỤ TÙNG KÈM THEO\n",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 13)));
  cell = new Cell(phrase);
  cell.setBorder(0);
  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
  tblheader1.addCell(cell);
  tblheader1.setBottom(20);
  tblheader1.setPadding(5);
  //-------------------
  document.add(tblheader1);
 
  
  
  tablePDF = new PdfPTable(20);
  tablePDF.setWidthPercentage(90);
  tablePDF.setSpacingBefore(0f);
  tablePDF.setSpacingAfter(0f);

  // first row
  phrase = new Phrase();
  phrase.add(new Chunk("STT",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
  cellPDF = new PdfPCell(phrase);
  cellPDF.setColspan(2);
  cellPDF.setHorizontalAlignment(Element.ALIGN_CENTER);
  cellPDF.setPadding(5.0f);
  tablePDF.addCell(cellPDF);
  
  phrase = new Phrase();
  phrase.add(new Chunk("TÊN THIẾT BỊ - KÍ HIỆU",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
  cellPDF = new PdfPCell(phrase);
  cellPDF.setColspan(7);
  cellPDF.setHorizontalAlignment(Element.ALIGN_CENTER);
  cellPDF.setPadding(5.0f);
  tablePDF.addCell(cellPDF);
  phrase = new Phrase();
  phrase.add(new Chunk("SỐ MÁY",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
  cellPDF = new PdfPCell(phrase);
  cellPDF.setColspan(3);
  cellPDF.setHorizontalAlignment(Element.ALIGN_CENTER);
  cellPDF.setPadding(5.0f);
  tablePDF.addCell(cellPDF);
  phrase = new Phrase();
  phrase.add(new Chunk("NGUYÊN GIÁ",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
  cellPDF = new PdfPCell(phrase);
  cellPDF.setColspan(3);
  cellPDF.setHorizontalAlignment(Element.ALIGN_CENTER);
  cellPDF.setPadding(5.0f);
  tablePDF.addCell(cellPDF);
  phrase = new Phrase();
  phrase.add(new Chunk("PHỤ TÙNG KÈM THEO",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
  cellPDF = new PdfPCell(phrase);
  cellPDF.setColspan(5);
  cellPDF.setHorizontalAlignment(Element.ALIGN_CENTER);
  cellPDF.setPadding(5.0f);
  tablePDF.addCell(cellPDF);

  
  for(int i = 0; i <exObject.getLstBorrow().size();i++)
  {
	  LoanAssetModel ex = exObject.getLstBorrow().get(i);
	  phrase = new Phrase();
	  phrase.add(new Chunk(i+1+"",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_NORMAL, 10)));
	  cellPDF = new PdfPCell(phrase);
	  cellPDF.setColspan(2);
	  cellPDF.setHorizontalAlignment(Element.ALIGN_CENTER);
	  cellPDF.setPadding(5.0f);
	  tablePDF.addCell(cellPDF);
	  
	  phrase = new Phrase();
	  phrase.add(new Chunk(ex.getAsset().getName(),  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_NORMAL, 10)));
	  cellPDF = new PdfPCell(phrase);
	  cellPDF.setColspan(7);
	  cellPDF.setHorizontalAlignment(Element.ALIGN_LEFT);
	  cellPDF.setPadding(5.0f);
	  tablePDF.addCell(cellPDF);
	  phrase = new Phrase();
	  phrase.add(new Chunk(ex.getAsset().getSeries(),  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_NORMAL, 10)));
	  cellPDF = new PdfPCell(phrase);
	  cellPDF.setColspan(3);
	  cellPDF.setHorizontalAlignment(Element.ALIGN_CENTER);
	  cellPDF.setPadding(5.0f);
	  tablePDF.addCell(cellPDF);
	  phrase = new Phrase();
	  phrase.add(new Chunk("",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_NORMAL, 10)));
	  cellPDF = new PdfPCell(phrase);
	  cellPDF.setColspan(3);
	  cellPDF.setHorizontalAlignment(Element.ALIGN_CENTER);
	  cellPDF.setPadding(5.0f);
	  tablePDF.addCell(cellPDF);
	  phrase = new Phrase();
	  phrase.add(new Chunk(ex.getAsseseries(),  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_NORMAL, 10)));
	  cellPDF = new PdfPCell(phrase);
	  cellPDF.setColspan(5);
	  cellPDF.setHorizontalAlignment(Element.ALIGN_CENTER);
	  cellPDF.setPadding(5.0f);
	  tablePDF.addCell(cellPDF);
  }
  
  
  document.add(tablePDF);
  
  
  
  
  
  tblheader1 = new Table(1);
  tblheader1.setWidth(90);
  tblheader1.setTop(10);
  tblheader1.setBorder(0);
   
  //-------------------
  font.setSize(18);
  phrase = new Phrase();
  phrase.add(new Chunk("NHẬN XÉT CỦA BAN GIAO NHẬN: ",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
  phrase.add(new Chunk(exObject.getBorrowCoupon().getApprove_comment()+"\n\n",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_NORMAL, 10)));
 
  cell = new Cell(phrase);
  cell.setBorder(0);
  tblheader1.addCell(cell);
  //-------------------
  document.add(tblheader1);
  
  
  
  tablePDF = new PdfPTable(20);

  tablePDF.setWidthPercentage(90);
  tablePDF.setSpacingBefore(0f);
  tablePDF.setSpacingAfter(0f);
 

  // first row
  phrase = new Phrase();
  phrase.add(new Chunk("TỔNG GIÁM ĐỐC",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
  cellPDF = new PdfPCell(phrase);
  cellPDF.setColspan(4);
  cellPDF.setBorder(0);
  cellPDF.setHorizontalAlignment(Element.ALIGN_CENTER);
  cellPDF.setPadding(5.0f);
  tablePDF.addCell(cellPDF);
  
  //first row
 phrase = new Phrase();
 phrase.add(new Chunk("KẾ TOÁN TRƯỞNG",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
 cellPDF = new PdfPCell(phrase);
 cellPDF.setColspan(4);
 cellPDF.setBorder(0);
 cellPDF.setHorizontalAlignment(Element.ALIGN_CENTER);
 cellPDF.setPadding(5.0f);
 tablePDF.addCell(cellPDF);
 
//first row
phrase = new Phrase();
phrase.add(new Chunk("ĐD CÔNG TY",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
cellPDF = new PdfPCell(phrase);
cellPDF.setColspan(4);
cellPDF.setBorder(0);
cellPDF.setHorizontalAlignment(Element.ALIGN_CENTER);
cellPDF.setPadding(5.0f);
tablePDF.addCell(cellPDF);

//first row
phrase = new Phrase();
phrase.add(new Chunk("ĐD BÊN GIAO",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
cellPDF = new PdfPCell(phrase);
cellPDF.setColspan(4);
cellPDF.setBorder(0);
cellPDF.setHorizontalAlignment(Element.ALIGN_CENTER);
cellPDF.setPadding(5.0f);
tablePDF.addCell(cellPDF);


//first row
phrase = new Phrase();
phrase.add(new Chunk("ĐD BÊN NHẬN",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
cellPDF = new PdfPCell(phrase);
cellPDF.setColspan(4);
cellPDF.setBorder(0);
cellPDF.setHorizontalAlignment(Element.ALIGN_CENTER);
cellPDF.setPadding(5.0f);
tablePDF.addCell(cellPDF);


// first row
phrase = new Phrase();
phrase.add(new Chunk("              ",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
cellPDF = new PdfPCell(phrase);
cellPDF.setColspan(4);
cellPDF.setBorder(0);
cellPDF.setHorizontalAlignment(Element.ALIGN_CENTER);
cellPDF.setPadding(5.0f);
cellPDF.setTop(100);
tablePDF.addCell(cellPDF);

//first row
phrase = new Phrase();
phrase.add(new Chunk("(Đã ký)\n"+exObject.getBorrowCoupon().getUser_accountant_name()+"\n",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
cellPDF = new PdfPCell(phrase);
cellPDF.setColspan(4);
cellPDF.setBorder(0);
cellPDF.setTop(100);
cellPDF.setHorizontalAlignment(Element.ALIGN_CENTER);
cellPDF.setPadding(5.0f);
tablePDF.addCell(cellPDF);

//first row
phrase = new Phrase();
phrase.add(new Chunk("(Đã ký)\n"+exObject.getBorrowCoupon().getUser_approve_name()+"\n",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
cellPDF = new PdfPCell(phrase);
cellPDF.setColspan(4);
cellPDF.setTop(100);
cellPDF.setBorder(0);
cellPDF.setHorizontalAlignment(Element.ALIGN_CENTER);
cellPDF.setPadding(5.0f);
tablePDF.addCell(cellPDF);

//first row
phrase = new Phrase();
phrase.add(new Chunk("(Đã ký)\n"+exObject.getBorrowCoupon().getUser_stock_name()+"\n",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
cellPDF = new PdfPCell(phrase);
cellPDF.setColspan(4);
cellPDF.setBorder(0);
cellPDF.setTop(100);
cellPDF.setHorizontalAlignment(Element.ALIGN_CENTER);
cellPDF.setPadding(5.0f);
tablePDF.addCell(cellPDF);


//first row
phrase = new Phrase();
phrase.add(new Chunk("       ",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
cellPDF = new PdfPCell(phrase);
cellPDF.setColspan(4);
cellPDF.setTop(100);
cellPDF.setBorder(0);
cellPDF.setHorizontalAlignment(Element.ALIGN_CENTER);
cellPDF.setPadding(5.0f);
tablePDF.addCell(cellPDF);

  
  

  document.add(tablePDF);
 }

}