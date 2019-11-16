package com.my.project.service;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.springframework.web.multipart.MultipartFile;

public interface WhatDidIEatService {
	public void excelFileUpload(MultipartFile file) throws EncryptedDocumentException, IOException;
}
