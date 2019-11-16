package com.my.project.service;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.my.project.contents.excelUpt.ExcelInfo;
import com.my.project.dao.WhatDidIEatDAO;
import com.my.project.util.excel.ExcelImport;


@Service
public class WhatDidIEatServiceImpl implements WhatDidIEatService {
	@Autowired
	WhatDidIEatDAO whatEatDAO;
	
	@Autowired
	ExcelImport excelImport;
	
	@Override
	public void excelFileUpload(MultipartFile file) throws EncryptedDocumentException, IOException {
		
		List<ExcelInfo> bcInfoList = excelImport.getExcelInfoList(file);
		
		System.out.println(bcInfoList); 
		
	}
	
	
}
