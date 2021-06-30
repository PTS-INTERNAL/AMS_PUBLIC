package com.ams.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.model.Student;

@Controller
public class TestFormController {
	 @RequestMapping(value = "/student", method = RequestMethod.GET)
	   public ModelAndView student() {
		 Student st = new Student();
		 st.setId(12345);
		 st.setName("PHAN VAN PHUOC THINH");
		 st.setAge(12);
	      return new ModelAndView("views/student.jsp", "command", st);
	   }
	   @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
	  public String addStudent(@ModelAttribute("SpringWeb")Student student, 
	   
	   ModelMap model) {
	      model.addAttribute("name", student.getName());
	      model.addAttribute("age", student.getAge());
	      model.addAttribute("id", student.getId());
	      
	      return "views/result.jsp";
	   }

}
