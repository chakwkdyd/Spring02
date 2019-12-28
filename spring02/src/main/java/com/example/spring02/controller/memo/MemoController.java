package com.example.spring02.controller.memo;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.memo.dto.MemoDTO;
import com.example.spring02.service.memo.MemoService;

@Controller // 현재 클래스를 컨트롤러 bean으로 등록
@RequestMapping("/memo/*")
public class MemoController {
//	@Controller				// 컨트롤러 bean(객체)으로 등록
//	@Service				// Service bean(객체)으로 등록
//	@Repository 			// dao bean(객체)으로 등록
//	.
//	@Inject == @Autowired 	// 의존관계 주입(개발자가 직접X spring에서 자동으로 만들어줌)
//	
//	@RequestMapping			// url mapping
//	@RequestParam			// get,post방식의 개별변수 갖(넘어온 개별 변수)
//	@ModelAttribute			// dto로 전체 묶어서 넘오온 data
//	@ResponseBody			//컨트롤러에서 어떤 메소드를 호출한 다음 page로 넘기는게 아니라 date자체를 넘길때 ex) 제이슨

//	create table memo (
//	idx number not null primary key,
//	writer varchar2(50) not null,
//	memo varchar2(200) not null,
//	post_date date default sysdate
//	);

//insert into memo (idx,writer,memo) values(1,'kim','메모1');
//insert into memo (idx,writer,memo) values(2,'park','메모2');

	@Inject // 의존관계 주입
	MemoService memoService; // 스프링이 만든 서비스 객체가 연결됨
	
// =========================<메모 리스트들 보기>=========================
	@RequestMapping("list.do")
	public ModelAndView list (ModelAndView mav) {
		List<MemoDTO> items = memoService.list(); // 메모 리스트 리턴
		mav.setViewName("memo/memo_list");// 출력 페이지의 이름
		mav.addObject("list", items); //출력 페이지에 전달할 변수
		System.out.println("ㅇㅇmav => "+mav);
		System.out.println("ㅇㅇitems  => "+items);
		return mav; //페이지로 이동
	}
	
	
// =========================<메모 작성>=========================
	@RequestMapping("insert.do") 
	public String insert(@ModelAttribute MemoDTO dto) {
		
		System.out.println("ㅇㅇdto.getWriter() => "+ dto.getWriter());
		System.out.println("ㅇㅇdto.getMemo() => "+ dto.getMemo());
		
		memoService.insert(dto.getWriter(), dto.getMemo() );
		return "redirect:/memo/list.do";
	}
	
	
// =========================<메모 상세보기>=========================
	// http://localhost/spring02/memo/view/6 => @PathVariable
	@RequestMapping("view/{idx}")
	public ModelAndView view(@PathVariable int idx,
							ModelAndView mav) {
		System.out.println("ddddddddddd!!!");
		mav.setViewName("memo/view");// 출력 페이지 지정
		mav.addObject("dto", memoService.memo_view(idx)); //데이터 저장
		return mav; // 출력 페이지로 이동
	}
	
// =========================<메모 수정>=========================
	@RequestMapping("update/{idx}")
	public String update(@PathVariable int idx, MemoDTO dto) {
		memoService.update(dto);
		return "redirect:/memo/list.do";
	}
	
// =========================<메모 삭제>=========================
	@RequestMapping("delete/{idx}")
	public String delete(@PathVariable int idx) {
		System.out.println("삭제!!!");
		memoService.delete(idx);
		return "redirect:/memo/list.do";
	}
	
// =========================<>=========================
// =========================<>=========================
// =========================<>=========================
// =========================<>=========================
// =========================<>=========================
// =========================<>=========================
// =========================<>=========================
// =========================<>=========================

	
	
}
