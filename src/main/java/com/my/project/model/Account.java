package com.my.project.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Account {
	int no;
	String accountId; 		//번호 
	String memberId;			//멤버 ID
	String tradeDate; 		//거래일시
	String withdrawAmt; 	//출금액
	String depositAmt; 		//입금액
	String afterAmt;		//거래후
	String tradeComment;	//거래내용
	String regDate;			//등록일
	
}
