package com.my.project.service;

import java.util.List;

import com.my.project.model.Account;
import com.my.project.model.WhatDidIEatVo;


public interface WhatDidIEatService {
	public List<Account> getAccountList();
	public List<WhatDidIEatVo> getTargetAccountList();
}
