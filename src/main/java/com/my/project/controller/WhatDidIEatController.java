package com.my.project.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WhatDidIEatController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@GetMapping("/whatDidIEat")
	public String whatDidIEat(Locale locale, Model model) {
		logger.info("Welcome What Did I Eat? to Get");
		
		return "/contents/whatDidIEat";
	}
}
