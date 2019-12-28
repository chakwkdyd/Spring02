package com.example.spring02.controller.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.board.dto.BoardDTO;
import com.example.spring02.service.board.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	
//	테이블
//	create table board(
//			bno number not null, 
//			title varchar2(200) not null,
//			content varchar2(4000),
//			writer varchar2(50) not null,
//			regdate date default sysdate,
//			viewcnt number default 0,
//			primary key(bno)
//			);
	
	
	@Inject // 서버스 객체 주입
	BoardService boardService;
	
	
	
	@RequestMapping("write.do")
		public String write() {
		return "board/write"; 
	}
	
	
	@RequestMapping("insert.do")
	public String insert(@ModelAttribute BoardDTO dto, HttpSession session) 
	throws Exception{
		
		// 로그인한 사용자의 아이디
		String writer= (String)session.getAttribute("userid");
		dto.setWriter(writer);
		   //레코드가 저장됨
		   boardService.create(dto);
		   //목록 갱신
		   return "redirect:/board/list.do";
		
	}
	
	
	@RequestMapping("list.do") 
	public ModelAndView list() throws Exception {
		List<BoardDTO> list=boardService.listAll(0, 0, "", "");// 목록
		ModelAndView mav = new ModelAndView();
		
		Map<String, Object> map = new HashMap<>();
		map.put("list", list); // 맵에 자료 저장
		
		mav.setViewName("board/list");// 이동할 페이지 지정
		mav.addObject("map", map); //데이터 저장
		return mav;// 페이지 이동(출력)
	}
}
