package com.my.project.util.excel;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.my.project.contents.excelUpt.ExcelInfo;

@Service
public class ExcelImport {

	public static List<ExcelInfo> getExcelInfoList(MultipartFile file ) throws EncryptedDocumentException, IOException { 
		List<ExcelInfo> excelInfoList = new ArrayList<ExcelInfo>(); 
		
		// 웹상에서 업로드 되어 MultipartFile인 경우 바로 InputStream으로 변경하여 사용. 
		InputStream inputStream = new ByteArrayInputStream(file.getBytes());

		// 엑셀 로드
		Workbook workbook = WorkbookFactory.create(inputStream); 
		// 시트 로드 0, 첫번째 시트 로드 
		Sheet sheet = workbook.getSheetAt(0); 
		Iterator<Row> rowItr = sheet.iterator();

		 while(rowItr.hasNext()) {
			 ExcelInfo excelInfo = new ExcelInfo(); 
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
				 	case 0: // 번호 
				 		excelInfo.setNo((getValueFromCell(cell)).toString()); 
				 		// 셀이 숫자형인 경우 Double형으로 변환 후 int형으로 변환 
				 		break; 
				 	case 1: // 거래일시 
				 		excelInfo.setTradeDate((getValueFromCell(cell)).toString()); 
				 		break; 
				 	case 2: // 출금액
				 		excelInfo.setWithdrawAmt((getValueFromCell(cell)).toString()); 
				 		break; 
				 	case 3: // 입금액
				 		excelInfo.setDipositAmt((getValueFromCell(cell)).toString()); 
				 		break; 
				 	case 4: // 거래후
				 		excelInfo.setAfterAmt((getValueFromCell(cell)).toString()); 
				 		break; 
				 	case 5: // 거래내용
				 		excelInfo.setTradeComment((getValueFromCell(cell)).toString()); 
				 		break; 
				 }
			 }
			 excelInfoList.add(excelInfo);
		 }
		return excelInfoList;
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
