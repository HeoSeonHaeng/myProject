package com.my.project.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.my.project.contents.AuthInfo;
import com.my.project.contents.Member;
import com.my.project.service.UserService;


@Controller
public class UserContorller {
	
	@Autowired
    UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@PostMapping(value = "/signUp")
	public String signUp(Model model, HttpServletRequest request, Member vo) {
		
		Integer result = userService.signUp(vo);
		if (result > 0) {
			logger.info("회원가입 성공!!");
			return "redirect:index";
		}else {
			logger.info("회원가입 실패!!");
			return "redirect:index";
			
		}
	}
	
	
	@GetMapping(value = "/idCheck")
	@ResponseBody
	public String idCheck(Model model, HttpServletRequest request) {
		
		String checkId = request.getParameter("id");

		return userService.idCheck(checkId);
	}
	
	@GetMapping(value = "/login")
	public String login(Model model, HttpServletRequest request) {
		
		String type = request.getParameter("isType");
		
		model.addAttribute("isType", type);
		
		return "user/login";
	}
	
	@GetMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:index";
	}
	
	@PostMapping(value = "/loginCheck")
	public ModelAndView loginCheck(Model model, HttpSession session, Member member, HttpServletResponse response) throws Exception {
		logger.info(member.getUserId() + " / " + member.getPassword() + " / " +member.getLoginKeeping());
		
		ModelAndView mv = new ModelAndView();
		
		try {
			AuthInfo authInfo = userService.loginCheck(member);
			session.setAttribute("authInfo", authInfo);
			
			Cookie rememberCookie = new Cookie("loginKeeping", member.getUserId());
			rememberCookie.setPath("user/login");
			if(member.getLoginKeeping().equals("T")) {
				rememberCookie.setMaxAge(60*60*24*7);
			}else {
				rememberCookie.setMaxAge(0);
			}
            response.addCookie(rememberCookie);
            logger.info("로그인 성공!!");
            mv.setViewName("redirect:index");
            return mv;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("로그인 실패");
			
			mv.addObject("msg", "비밀번호 또는 아이디가 일치하지 않습니다.");
			mv.setViewName("user/login");
			return mv;
		}
	}
}
