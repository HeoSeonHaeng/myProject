package com.my.project.util.excel;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;
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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.my.project.api.WhatDidIEatDAO;
import com.my.project.controller.HomeController;
import com.my.project.model.Account;

@Service
public class ExcelImport {
	
	private JdbcTemplate jdbcTemplate;
    private TransactionTemplate transactionTemplate;
    private PlatformTransactionManager txManager;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	WhatDidIEatDAO dao;
	
	public Map<String, String> insertAccountList(MultipartFile file ) { 
		Map<String, String> result = new HashedMap<String, String>();
		
		boolean success = false;
		int loofIdx = 1;
		
//		DefaultTransactionDefinition td = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);
//		
//		td.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
//		td.setTimeout(10);
//		
//		TransactionStatus status = txManager.getTransaction(td);
		
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
//					result.put("RESULT", "FAIL");
//					result.put("msg", "Excel 파일에 저장할 내용이 없습니다.");
//					break; 
					continue;
				}
				
				Iterator<Cell> cellItr = row.cellIterator(); 
				
				// 한행이 한열씩 읽기 (셀 읽기)
				while(cellItr.hasNext()) {
					Cell cell = cellItr.next(); 
					int index = cell.getColumnIndex(); 
					
					switch(index) { 
					case 0: // 번호 
						excelInfo.setAccountId((getValueFromCell(cell)).toString()); 
						// 셀이 숫자형인 경우 Double형으로 변환 후 int형으로 변환 
						break; 
					case 1: // 거래일시 
						excelInfo.setTradeDate((getValueFromCell(cell)).toString()); 
						break; 
					case 2: // 출금액
						excelInfo.setWithdrawAmt((getValueFromCell(cell)).toString()); 
						break; 
					case 3: // 입금액
						excelInfo.setDepositAmt((getValueFromCell(cell)).toString()); 
						break; 
					case 4: // 거래후
						excelInfo.setAfterAmt((getValueFromCell(cell)).toString()); 
						break; 
					case 5: // 거래내용
						excelInfo.setTradeComment((getValueFromCell(cell)).toString());
						break; 
					}
				}
				success = dao.insertAccount(excelInfo); 
				
				if( !success ) {
					result.put("RESULT", "FAIL");
					result.put("msg", loofIdx + "번째 행 저장시  문제가 발생하였습니다.");
					logger.error(result.get("msg"));
//			        txManager.rollback(status);
					success = false;
					break;
				}
				
				loofIdx++;
			}
			
		} catch (Exception e) {
			result.put("RESULT", "FAIL");
			result.put("msg", "파일 내용 저장시 문제가 발생하였습니다. 관리자에게 문의하세요");
			logger.error(e.toString());
//	        txManager.rollback(status);
	    } finally {
			if(success) {
				result.put("RESULT", "FAIL");
				result.put("msg", "정상적으로 저장되었습니다.");
//				txManager.commit(status);
			}else {
//				txManager.rollback(status);
			}
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
