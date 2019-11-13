package com.my.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.project.dao.WhatDidIEatDAO;


@Service
public class WhatDidIEatServiceImpl implements WhatDidIEatService {
	@Autowired
	WhatDidIEatDAO whatEatDAO;
	
	
}
