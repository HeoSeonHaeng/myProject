package com.my.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.project.api.WhatDidIEatDAO;
import com.my.project.model.Account;
import com.my.project.model.WhatDidIEatVo;


@Service
@Transactional
public class WhatDidIEatServiceImpl implements WhatDidIEatService {
	@Autowired
	private WhatDidIEatDAO dao;
	
	@Override
	public List<Account> getAccountList() {
		return dao.getAccountList();
	}

	@Override
	public List<WhatDidIEatVo> getTargetAccountList() {
		return dao.getTargetAccountList();
	}
	
	
}
