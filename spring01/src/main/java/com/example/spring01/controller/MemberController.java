package com.example.spring01.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.spring01.model.dto.MemberDTO;
import com.example.spring01.service.MemberService;

@Controller // 현재 클래스를 Controller Bean으로 등록함
public class MemberController {
	
//	create table member(
//		    userid varchar2(50) not null primary key,
//		    passwd varchar2(50) not null,
//		    name varchar2(50) not null,
//		    email varchar2(50),
//		    join_date date default sysdate
//		    );
	
	
	private static final Logger logger=
			LoggerFactory.getLogger(MemberController.class);
	
	
	@Inject // MemberService 객체 주입됨
	MemberService memberService;
	
//	==========================<출력 페이지>==========================
	@RequestMapping("member/list.do")// 사용자가 요청하는 주소
	public String memberList(Model model) {
		System.out.println("dddd");
		List<MemberDTO> list= memberService.memberList();
		
		logger.info("회원 목록:"+list);
		model.addAttribute("list", list);// 모델에 저장
		return "member/member_list";	 // 출력 페이지로 포워딩
	}
	
	
//	==========================<회원등록 버튼 클릭시>==========================
	@RequestMapping("member/write.do")
	public void write() {
		//return "member/write";
	}
	
	
//	==========================<회원등록 DB로 해줌>==========================
	// 폼에 입력한 데이터가 MemberDTO dto 변수에 저장됨
	// request.getParameter생략(DTO에서 name이 변수명이랑 같으면 dto로 옮길수 있음)
	@RequestMapping("member/insert.do")
	public String insert(@ModelAttribute MemberDTO dto) {
		memberService.insertMember(dto);
		return "redirect:/member/list.do";// 목록 갱신
	}
	
	
//	==========================<사용자한테 얻어온 ID값을 받아와 그ID정보를 DB로 갖고옴>==========================
	@RequestMapping("member/view.do")
	public String view(@RequestParam String userid, Model model) {
		model.addAttribute("dto", memberService.viewMember(userid));
		return "member/view";
	}
	
	
	
//	==========================<사용자 입력값 id,pw를 갖고 DB에 있는지 확인>==========================
	@RequestMapping("member/update.do")
	public String update(@ModelAttribute MemberDTO dto, Model model) {
		
		// result에 반환갑 true 있다 / false 없다 가 들어감 
		boolean result = memberService.checkPw(
							dto.getUserid(), dto.getPasswd());
		logger.info("비밀번호 확인 => "+result);
		
		
		// 비밀번호가 맞으면
		if(result) {
			memberService.updateMember(dto);// 레코드 수정
			return "redirect:/member/list.do";//목록으로 이동
		}else {
			MemberDTO dto2=memberService.viewMember(dto.getUserid());
			dto.setJoin_date(dto2.getJoin_date());// 날짜가 지워지지 않도록(날짜는 없음으로 초기화시켜줌)
			model.addAttribute("dto", dto);
			model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
			return "member/view";// 수정 페이지로 되돌아감
		}
	}
//	==========================<삭제 버튼>==========================
	@RequestMapping("member/delete.do")
	public String delete(@RequestParam String userid, 
						@RequestParam String passwd, Model model) {
		boolean result=memberService.checkPw(userid, passwd);
		
		if (result) {
			memberService.deleteMember(userid);
			return "redirect:/member/list.do";
		}else {
			model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
			model.addAttribute("dto", memberService.viewMember(userid));
			return "member/view";
		}
	}
	
//	==========================<>==========================
//	==========================<>==========================
//	==========================<>==========================
//	==========================<>==========================
}
