package com.my.project.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.my.project.api.WhatDidIEatDAO;
import com.my.project.model.Account;
import com.my.project.util.excel.ExcelImport;


@Service
@Transactional
public class WhatDidIEatServiceImpl implements WhatDidIEatService {
	@Autowired
	private WhatDidIEatDAO dao;
	
	@Override
	public Map<String, String> excelFileUpload(MultipartFile file)  {
		ExcelImport excelImport = new ExcelImport();
		
		return excelImport.insertAccountList(file);
	}

	@Override
	public List<Account> getAccountList() {
		return dao.getAccountList();
	}
	
	
}
