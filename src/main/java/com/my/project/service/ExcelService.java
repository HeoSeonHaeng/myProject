package com.my.project.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface ExcelService {
	public Map<String, String> insertAccountList(MultipartFile file );
}
