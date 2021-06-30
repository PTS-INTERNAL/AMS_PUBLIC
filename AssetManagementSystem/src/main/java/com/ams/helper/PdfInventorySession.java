package com.ams.helper;

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
import com.ams.model.InventoryCheckingRealtimeModel;
import com.ams.model.TroubleAssetModel;
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
import com.lowagie.text.pdf.PdfWriter;

import javafx.print.Collation;

public class PdfInventorySession extends AbstractPdfView {

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
  
  response.setHeader("Content-Disposition", "attachment; filename=\"PDF ASSET-xx.pdf\"");
  document.addTitle("FILE PDF ASSET TROUBLE");
  document.addSubject("TIEU DE SUBJECT");
  document.addTitle("TIEU DE TITLE");
  //document.setPageSize(PageSize.A4.rotate());

  //Start Header
  Table tblCompanyName = new Table(2);
  tblCompanyName.setWidth(100);
  tblCompanyName.setBorder(0);
  Font font = ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 18);
  Paragraph content;
  Cell cell;
  //--------------
  Phrase phrase = new Phrase();
  phrase.add(new Chunk("TỔNG CÔNG TY MAY VIỆT TIẾN",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 15)));
  phrase.add(new Chunk("\n"));
  phrase.add(new Chunk("ĐƠN VỊ:...........",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
  phrase.add(new Chunk("\n"));
  phrase.add(new Chunk("ĐỊA CHỈ:...........",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
  cell = new Cell(phrase);
  cell.setBorder(0);
  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
  tblCompanyName.addCell(cell);
  //--------------
  phrase = new Phrase();
  phrase.add(new Chunk("Mẫu số S09-DNN\n",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
  phrase.add(new Chunk("(Ban hành theo Thông tư số 133/2016/TT-BTC\nngày 26/8/2016 của Bộ Tài chính)",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_ITALIC, 10)));

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
  phrase.add(new Chunk("SỔ TÀI SẢN CỐ ĐỊNH\n",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 18)));
  phrase.add(new Chunk("NĂM:........",  ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10)));
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
  @SuppressWarnings("unchecked")
  Table table = new Table(11);
  table.setWidth(100);
  table.setPadding(2);
  table.setTop(50);
  List<InventoryCheckingRealtimeModel>  list = (List<InventoryCheckingRealtimeModel>) model.get("lst");
  
  List<String> lstheader = new ArrayList<String>();
  lstheader.add("STT");
  lstheader.add("NHÓM");
  lstheader.add("RFID");
  lstheader.add("TÊN MÁY");
  lstheader.add("MODEL MÁY");
  lstheader.add("SỐ SERI");
  lstheader.add("ĐƠN VỊ KIỂM");
  lstheader.add("ĐƠN VỊ DÙNG");
  lstheader.add("NGÀY KIỂM");
  lstheader.add("TRẠNG THÁI");
  lstheader.add("GIẢI TRÌNH");
  
  for(int i =0;i<11;i++)
  {
	  Paragraph p = new Paragraph(lstheader.get(i),ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_BOLD, 10));
	  p.setAlignment(Element.ALIGN_CENTER);
	  Cell celll = new Cell(p);
	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	  table.addCell(celll);
  }
  int STT=1;
  Font fonts = ItextVietnameseFont.VietNameseFont(ItextVietnameseFont.TIMESNEWROMAN_NORMAL, 10);
  for(InventoryCheckingRealtimeModel asset : list){
	  table.addCell (new Paragraph(""+STT++,fonts));
	  table.addCell(new Paragraph(asset.getGroup().getGroup_name(),fonts));
	  table.addCell(new Paragraph(asset.getAsset().getRFID(),fonts));
	  table.addCell(new Paragraph(asset.getAsset().getName(),fonts));
	  table.addCell(new Paragraph(asset.getAsset().getModel(),fonts));
	  table.addCell(new Paragraph(asset.getAsset().getSeries(),fonts));
	  table.addCell(new Paragraph(asset.getDeptChecking().getDept_name(),fonts));
	  table.addCell(new Paragraph(asset.getDeptUsing().getDept_name(),fonts));
	  table.addCell(new Paragraph(asset.getAsset().getInsert_dt(),fonts));
	  if(asset.getStatus().equals("1")) {
		  table.addCell(new Paragraph("KIỂM KÊ HỢP LỆ",fonts));
	  }
	  else if(asset.getStatus().equals("2")) {
		  table.addCell(new Paragraph("BÁO MỚI",fonts));
	  }
	  else if(asset.getStatus().equals("0")) {
		  table.addCell(new Paragraph("CHƯA KIỂM KÊ",fonts));
	  }
	  else{
		  table.addCell(new Paragraph("KIỂM KÊ KHÔNG HỢP LỆ",fonts));
	  }
	  table.addCell(new Paragraph(asset.getExplain(),fonts));
  }
  document.add(table);
  
 }

}