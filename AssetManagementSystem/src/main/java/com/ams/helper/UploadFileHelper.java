package com.ams.helper;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

public class UploadFileHelper {
	
	public static File simpleUpload(MultipartFile file, HttpServletRequest request, boolean encrypt_file_name, String upload_folder,HttpSession session)
	{
		String filename = null;
		File serverFile=null;
		
		try
		{
			if(!file.isEmpty())
			{
				String applicationPath = request.getServletContext().getRealPath("");
				
				if(encrypt_file_name)
				{
					String currentFileName = file.getOriginalFilename();
					String extension = currentFileName.substring(currentFileName.lastIndexOf(".", currentFileName.length()));
					Long nameRadom = Calendar.getInstance().getTimeInMillis();
					String newfilename = nameRadom + extension;
					filename = newfilename;
				}
				else
				{
					filename = file.getOriginalFilename();
				}
				byte[] bytes = file.getBytes();
				String rootPath = applicationPath;
				File dir = new  File(rootPath + File.separator + upload_folder);
				//File dir = new  File("E:\\2020\\LUANVANTOTNGHIEP\\INDRUINO_ASSET_MANAGEMENT\\PROJECT\\AssetMangement\\src\\main\\webapp\\resources\\images" );
				
				if(dir.exists())
				{
					dir.mkdirs();
					serverFile = new File(dir.getAbsolutePath() + File.separator + filename);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					return serverFile;
				}
				else
				{
					serverFile = null;
				}
			} 
				
			}
			catch (Exception e) {
				// TODO: handle exception
				serverFile = null;
			}
		
		
		
		return serverFile;
	}
	

}
