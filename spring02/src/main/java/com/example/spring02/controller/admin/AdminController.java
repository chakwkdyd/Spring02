package com.example.spring02.controller.admin;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.member.dto.MemberDTO;
import com.example.spring02.service.admin.AdminService;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
	
//	create table admin(
//			userid VARCHAR(50) not null,
//			passwd VARCHAR(50) not null,
//			name VARCHAR(50) not null,
//			email VARCHAR(100),
//			join_date DATE DEFAULT sysdate,
//			PRIMARY KEY(userid)
//			);
//
//			insert into admin (userid,passwd,name) values('admin','1234','관리자');
	
	
	@Inject
	AdminService adminService;
	
	
//	===========<login페이지>=============
	@RequestMapping("login.do")
	public String login() {
		return "admin/login";
	}
	
//	=============<관리자 로그인>=============
	@RequestMapping("login_check.do")
	public ModelAndView login_check(MemberDTO dto,
									HttpSession session,
									ModelAndView mav) {
		
		String name = adminService.loginCheck(dto);
		
		if(name != null) {// 로그인 성공
			session.setAttribute("admin_userid", dto.getUserid());
			session.setAttribute("admin_name", name);
			session.setAttribute("userid", dto.getUserid());
			session.setAttribute("name", name);
			
			mav.setViewName("admin/admin");
			mav.addObject("message", "success");
		}else {
			mav.setViewName("admin/login");
			mav.addObject("message", "error");
		}
		return mav;
	}


// ===============<관리자 로그아웃>=============
	@RequestMapping("logout.do")
	public String logout(HttpSession session,Model model) {
		session.invalidate();
		model.addAttribute("message", "logout");
		return "admin/login";
	}
	
	
}
