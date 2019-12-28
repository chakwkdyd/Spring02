package com.example.spring02.service.member;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.example.spring02.model.member.dao.MemberDAO;
import com.example.spring02.model.member.dto.MemberDTO;
import com.mysql.cj.Session;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	MemberDAO memberDao;
	
	
	@Override
	public String loginCheck(MemberDTO dto, HttpSession session) {
		
		System.out.println("서비스Impl");
		// 맞으면 이름이 넘어오고 틀리면 null이 넘어옴
		String name = memberDao.loginCheck(dto);
		System.out.println("sss=>>"+name);
		
		if(name != null) {// 맞으면
			// 세션변수 등록
			System.out.println("로그인 성공!!!");
			System.out.println("서비스 if문!!!들어옴 dto.getUserid() ==> "+dto.getUserid());
			session.setAttribute("userid", dto.getUserid());
			session.setAttribute("name", name);
		}
		return name;
	}

	@Override
	public void logout(HttpSession session) {
		// 세션을 모두 초기화시킴
		session.invalidate();
	}

}
