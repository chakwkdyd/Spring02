package com.example.spring02.controller.member;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.member.dto.MemberDTO;
import com.example.spring02.service.member.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {

//	create table member(
//    userid varchar2(50) not null primary key,
//    passwd varchar2(50) not null,
//    name varchar2(50) not null,
//    email varchar2(50),
//    join_date date default sysdate
//    );
//	
//	insert into member (userid,passwd,name,email) values 
//	('01','01','박다빈','cc@na');
//	
//	insert into member (userid,passwd,name,email) values 
//	('02','02','박유미','c8@na');
	
	private static final Logger logger =
			LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	MemberService memberService;
	
	
//	============<로그인 화면>===============
	@RequestMapping("login.do")
	public String login() {
		return "member/login";
	}
	
//	============<로그인>===============
	@RequestMapping("login_check.do")
	public ModelAndView login_check(@ModelAttribute MemberDTO dto,
									HttpSession session) {
		System.out.println("컨트롤러!!!");
		String name= memberService.loginCheck(dto, session);
		
		ModelAndView mav = new ModelAndView();
		
		if (name != null) {
			mav.setViewName("home");
		}else {
			mav.setViewName("member/login");
			mav.addObject("message", "error");
		}
		return mav;
	}
	
	
//	============<로그아웃>===============
	@RequestMapping("logout.do")
	public ModelAndView logout(
			HttpSession session, ModelAndView mav) {
		memberService.logout(session); // 세션 초기화 작업
		mav.setViewName("member/login");// 이동할 페이지의 이름
		mav.addObject("message", "logout");// 변수 저장
		return mav;
	}
	

	
}
