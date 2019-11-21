package com.my.project.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.my.project.model.Account;


public interface WhatDidIEatService {
	public Map<String, String> excelFileUpload(MultipartFile file);
	public List<Account> getAccountList();
}
