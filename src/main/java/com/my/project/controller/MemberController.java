package com.my.project.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

	@GetMapping("/test")
	public String test(Model model, HttpServletRequest request) {
//		Member member = Member.builder().userNo("1").userNm("허선행").userId(UUID.randomUUID().toString().replace("-", "")).build();
		
//		model.addAttribute("member", member);
		
		System.out.println("null ?" + "" == null);
		
		return "test";
	}
	
}
