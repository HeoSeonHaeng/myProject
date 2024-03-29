package com.my.project.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.my.project.model.Account;
import com.my.project.model.WhatDidIEatVo;
import com.my.project.service.ExcelServiceImpl;
import com.my.project.service.WhatDidIEatServiceImpl;

@Controller
public class WhatDidIEatController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	WhatDidIEatServiceImpl wdieService;
	
	@Autowired
	ExcelServiceImpl excelService;
	
	@GetMapping("/whatDidIEat")
	public String whatDidIEat(Locale locale, Model model) throws EncryptedDocumentException, IOException {
		logger.info("Welcome What Did I Eat? to Get");
		// Excel Upload Data List
		List<Account> accountList = wdieService.getAccountList();
		// Target Account Data List
		List<WhatDidIEatVo> targetAccountList = wdieService.getTargetAccountList();
		
		model.addAttribute("accountList", accountList);
		model.addAttribute("targetAccountList", targetAccountList);
		
		return "/contents/whatDidIEat";
	}
	
	@PostMapping("/excelFileUpload")
	public String excelFileUpload(@RequestParam("file") MultipartFile multipartFile, Model model) throws EncryptedDocumentException, IOException {
		logger.info("Excel File Upload to Post!!");
		
		Map<String, String> map = excelService.insertAccountList(multipartFile);
		model.addAttribute("result", map);
		return "/contents/whatDidIEat";
	}
}
