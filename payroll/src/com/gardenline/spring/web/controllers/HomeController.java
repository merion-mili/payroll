package com.gardenline.spring.web.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	private Logger logger = Logger.getLogger(HomeController.class);
	
	@RequestMapping("/")
	public String showHome(Model model){
		logger.info("Showing home page.......");
		return "home";
	}
	
}
