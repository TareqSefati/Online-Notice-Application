package com.testSpring.web.controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.testSpring.web.dao.FormValidationGroup;
import com.testSpring.web.dao.Notice;
import com.testSpring.web.service.NoticesService;

@Controller
public class NoticesController {

	// @RequestMapping("/")
	// public ModelAndView showHome(){
	// ModelAndView mv = new ModelAndView("home");
	//
	// Map<String, Object> model = mv.getModel();
	// model.put("name", "Harison");
	//
	// return mv;
	// }

	private NoticesService noticesService;

	@Autowired
	public void setNoticesService(NoticesService noticesService) {
		this.noticesService = noticesService;
	}

	@RequestMapping("/test")
	public String showTest(Model model, @RequestParam("id") String id) {

		System.out.println("id is: " + id);
		return "home";
	}

	@RequestMapping("/notices")
	public String showNotices(Model model) {
		List<Notice> notices = noticesService.getCurrentNotices();
		model.addAttribute("notices", notices);
		return "notices";
	}

	// Create Notice Form page handler method
	@RequestMapping("/createnotice")
	public String createNotice(Model model, Principal principal) {
		Notice notice = null;
		if(principal != null){
			String username = principal.getName();
			notice = noticesService.getNotice(username);
		}
		
		if(notice == null){
			notice = new Notice();
		}
		
		model.addAttribute(notice);
		return "createnotice";
	}

	// After submitting the Create Notice Form page handler method
	@RequestMapping(value = "/docreate", method = RequestMethod.POST)
	public String doCreateNotice(Model model, @Validated(value=FormValidationGroup.class) Notice notice, BindingResult result,
			Principal principal, @RequestParam(value="delete", required=false) String delete) {
		
		if (result.hasErrors()) {
			return "createnotice";
		}
		
		if(delete == null){
			String username = principal.getName();
			notice.getUser().setUsername(username);
			noticesService.saveOrUpdate(notice);
			return "noticecreated";
		} else {
			noticesService.delete(notice.getId());
			return "noticedeleted";
		}
		
		
	}

}
