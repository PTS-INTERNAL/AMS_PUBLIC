package com.ams.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PhotoController extends UploadImageController {
	 public static final String PARAM_LATESTPHOTO = "LATEST_PHOTO_PARAM";
     
	    @RequestMapping(value = "/uploadPhoto", method = RequestMethod.GET)
	    public String uploadPhotoForm(ModelMap model, HttpServletRequest request){
	        model.addAttribute(PARAM_BASE_URL, getBaseURL(request));
	        return "views/uploadImage.jsp";
	    }
	     
	    @RequestMapping(value = "/uploadimgctlr", method = RequestMethod.POST)
	    public String uploadImageCtlr(ModelMap model,
	            HttpServletRequest request, 
	            @RequestParam MultipartFile file){
	        String latestUploadPhoto = "";
	        String rootPath = request.getSession().getServletContext().getRealPath("/");
	        File dir = new File(rootPath + File.separator + "img");
	        if (!dir.exists()) {
	            dir.mkdirs();
	        }
	         
	        File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
	        latestUploadPhoto = file.getOriginalFilename();
	         
	    //write uploaded image to disk
	        try {
	            try (InputStream is = file.getInputStream(); BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
	                int i;
	                while ((i = is.read()) != -1) {
	                    stream.write(i);
	                }
	                stream.flush();
	            }
	        } catch (IOException e) {
	            //System.out.println("error : " + e.getMessage());
	        }
	         
	    //send baseURL to jsp
	        model.addAttribute(PARAM_BASE_URL, getBaseURL(request));
	    //send photo name to jsp
	        model.addAttribute(PARAM_LATESTPHOTO, latestUploadPhoto);
	        return "views/uploadImage.jsp";
	    }  
	    
	    public String simpleUpload(ModelMap model, HttpServletRequest  request, @RequestParam MultipartFile file){
	        String latestUploadPhoto = "";
	        String rootPath = request.getSession().getServletContext().getRealPath("/");
	        File dir = null;
	        
	         String urlimae_abc ="";
	        String image_name = "";
	        if(file.getOriginalFilename()!=null && file.getOriginalFilename().trim().length()>0)
	        {
	        	image_name=file.getOriginalFilename();
	        	dir = new File(rootPath + File.separator + "img");
	        	urlimae_abc = getBaseURL(request)+"/"+ "img" + "/"+image_name;
	        }
	        else
	        {
	        	image_name="resources/images/AVARTA.png";
	        	dir = new File(rootPath + File.separator);
	        	urlimae_abc=getBaseURL(request)+"/"+image_name;
	        }
	        if (!dir.exists()) {
	            dir.mkdirs();
	        }
	        
	        File serverFile = new File(dir.getAbsolutePath() + File.separator + image_name);
	        latestUploadPhoto = image_name;
	         
	    //write uploaded image to disk
	        try {
	            try (InputStream is = file.getInputStream(); BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
	                int i;
	                while ((i = is.read()) != -1) {
	                    stream.write(i);
	                }
	                stream.flush();
	            }
	        } catch (IOException e) {
	            //System.out.println("error : " + e.getMessage());
	        }
	         
	    //send baseURL to jsp
	        model.addAttribute(PARAM_BASE_URL, getBaseURL(request));
	    //send photo name to jsp
	        model.addAttribute(PARAM_LATESTPHOTO, latestUploadPhoto);
	        return urlimae_abc;
	    }  

}
