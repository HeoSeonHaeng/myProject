package com.my.project.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@GetMapping(value = "/index")
	public ModelAndView index(Locale locale, Model model) {
		logger.info("Welcome index to GET!");
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("index");
		return mv;
	}
	
	@PostMapping(value = "/index")
	public ModelAndView index(Locale locale, HttpSession session) {
		logger.info("Welcome index to POST!");
		ModelAndView mv = new ModelAndView();
//		mv.addObject("authInfoVO", authInfo);
		
		mv.setViewName("index");
		return mv;
	}
	
	@GetMapping("/contact")
	public String contact(Locale locale, Model model) {
//		logger.info("Welcome home! The client locale is {}.", locale);
		
		return "contact";
	}
	
	@GetMapping("/post")
	public String post(Locale locale, Model model) {
//		logger.info("Welcome home! The client locale is {}.", locale);
		
		return "post";
	}
}
