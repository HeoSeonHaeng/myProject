package com.my.project.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class WhatDidIEatVo {
	/* 공통 */
	int no;					//번호
	String memberId;		//멤버 ID
	int codeId;				//코드 Id
	String regDate;			//등록일
	String updDate;			//수정일
	
	/* Table : account */
	int accountId; 		//account Id 
							//멤버 ID
	String tradeDate; 		//거래일시
	int withdrawAmt; 		//출금액
	int depositAmt; 		//입금액
	int afterAmt;			//거래후
	String tradeComment;	//거래내용
							//등록일
	
	/* Table : target_account */
	String targetAccountId;	//targetAccount Id
							//코드 Id
							//멤버 ID
	int targetBudget;		//목표예산
	String comment;			//초과지출소명
							//등록일
							//수정일
	
	/* Table : code_mst */
							//코드 Id
	String codeType;
	String codeNm;
							//등록일
							//수정일
}
