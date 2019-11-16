package com.my.project.contents.excelUpt;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ExcelInfo {
	String no; 			//번호 
	String tradeDate; 		//거래일시
	String withdrawAmt; 		//출금액
	String dipositAmt; 		//입금액
	String afterAmt;			//거래후
	String tradeComment;	//거래내용

}
