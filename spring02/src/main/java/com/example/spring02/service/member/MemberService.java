package com.example.spring02.service.member;

import javax.servlet.http.HttpSession;

import com.example.spring02.model.member.dto.MemberDTO;

public interface MemberService {
	// 로그인 체크 메소드
	public String loginCheck(MemberDTO dto, HttpSession sesssion);
	// 로그아웃 메소드
	public void logout(HttpSession session);
	
}
