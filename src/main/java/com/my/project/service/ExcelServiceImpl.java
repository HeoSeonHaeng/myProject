package com.my.project.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.my.project.api.WhatDidIEatDAO;
import com.my.project.controller.HomeController;
import com.my.project.model.Account;

@Service
public class ExcelServiceImpl implements ExcelService{
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	WhatDidIEatDAO dao;
	
	public Map<String, String> insertAccountList(MultipartFile file ) { 
		Map<String, String> result = new HashedMap<String, String>();
		Map<String, Object> accountMap = new HashedMap<String, Object>();
		List<Account> accountList = new ArrayList<Account>();
		
		try {
			// 웹상에서 업로드 되어 MultipartFile인 경우 바로 InputStream으로 변경하여 사용. 
			InputStream inputStream = new ByteArrayInputStream(file.getBytes());
			
			// 엑셀 로드
			Workbook workbook = WorkbookFactory.create(inputStream); 
			// 시트 로드 0, 첫번째 시트 로드 
			Sheet sheet = workbook.getSheetAt(0); 
			Iterator<Row> rowItr = sheet.iterator();
			
			while(rowItr.hasNext()) {
				Account excelInfo = new Account(); 
				Row row = rowItr.next(); // 첫번재 행이 해더인 경우 스킵, 2번째 행부터 data 로드 
				
				if(row.getRowNum() == 0) { 
					continue;
				}

				Iterator<Cell> cellItr = row.cellIterator(); 
				
				// 한행이 한열씩 읽기 (셀 읽기)
				while(cellItr.hasNext()) {
					Cell cell = cellItr.next(); 
					int index = cell.getColumnIndex(); 
					
					switch(index) { 
					case 0: // 거래일시 
						excelInfo.setTradeDate((getValueFromCell(cell)).toString()); 
						break; 
					case 1: // 출금액
						excelInfo.setWithdrawAmt((getValueFromCell(cell)).toString()); 
						break; 
					case 2: // 입금액
						excelInfo.setDepositAmt((getValueFromCell(cell)).toString()); 
						break; 
					case 3: // 거래후
						excelInfo.setAfterAmt((getValueFromCell(cell)).toString()); 
						break; 
					case 4: // 거래내용
						excelInfo.setTradeComment((getValueFromCell(cell)).toString());
						break; 
					}
					excelInfo.setMemberId("3");
				}
				accountList.add(excelInfo);

			}
			
			accountMap.put("list", accountList);
			
			dao.insertAccount(accountMap); 
			
			
		} catch (Exception e) {
			result.put("RESULT", "FAIL");
			result.put("msg", "파일 내용 저장시 문제가 발생하였습니다. 관리자에게 문의하세요");
			logger.error(e.toString());
	    } finally {
			result.put("RESULT", "FAIL");
			result.put("msg", "정상적으로 저장되었습니다.");
		}
		
		return result;
	}  
	
	// 셀서식에 맞게 값 읽기 
	private static Object getValueFromCell(Cell cell) {
		 switch(cell.getCellType()) {
		 	case STRING: 
		 		return cell.getStringCellValue(); 
		 	case BOOLEAN: return cell.getBooleanCellValue();
		 	case NUMERIC: 
		 		if(DateUtil.isCellDateFormatted(cell)) { 
		 			return cell.getDateCellValue(); 
		 		} 
		 		return cell.getNumericCellValue(); 
		 	case FORMULA: 
		 		return cell.getCellFormula(); 
		 	case BLANK: 
		 		return ""; 
		 	default: 
		 		return "";
		 }
	}

}
