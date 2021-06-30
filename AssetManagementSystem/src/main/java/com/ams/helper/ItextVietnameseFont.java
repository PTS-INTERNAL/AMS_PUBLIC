package com.ams.helper;

import java.io.File;
import java.io.IOException;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;

public class ItextVietnameseFont {
	
	public static String TIMESNEWROMAN_NORMAL = "vuTimes";
	public static String TIMESNEWROMAN_BOLD = "vuTimesBold";
	public static String TIMESNEWROMAN_ITALIC = "vuTimesItalic";
	public static String TIMESNEWROMAN_BOLD_ITALIC = "vuTimesBoldItalic";
	static Font font = null;
	
	
	public static Font VietNameseFont(String fontName , int Size) throws DocumentException, IOException
	{
		String  FontString = "fonts/"+fontName+".ttf";
		File fontFile = new File(FontString);
		BaseFont bf = BaseFont.createFont("http://113.161.80.144:8081/AssetManagementSystem/resources/"+FontString,BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	    
		font = new Font(bf,Size);
	    return font;
	}

}
