package com.example.spring01.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring01.model.dto.ProductDTO;

@Controller
public class MainController {

	private static final Logger logger =
			LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping("/")
	public String main(Model model) {
		model.addAttribute("message", "홈페이지 방문을 환영합니다.");
		return "main";
	}
	
	
	
//	====================<구구단 페이지>========================
	@RequestMapping(value="gugu.do", method=RequestMethod.GET)
	public String gugu(@RequestParam int dan, Model model) {
		
		//int dan=7;
		String result="";
		for (int i = 1; i <= 9; i++) {
			result +=dan + "X" + i + "=" +dan * i + "<br>";
		}
		model.addAttribute("result", result);
		return "test/gugu";
	}
	
	
	
	
//	====================<테스트 페이지>========================	
	// 리턴 타입이 void인 경우 RequestMapping과 동일한 페이지로 넘어감
	@RequestMapping("test")
	public void test() { }

	
	
	
//	====================<test/doA~doF>========================
	@RequestMapping("test/doA")// url pattern
	public String doA(Model model) { 
		logger.info("내 로그 => doA called...");//로그 출력
		
		model.addAttribute("message", "홈페이지 방문을 환영합니다");
		// 리턴타입이 void이면 method가 종료된 후 doA.jsp로 포워드됨
		return "test/doB";// doB.jsp로 포워딩됨
	}
	
	
	
	@RequestMapping("test/doB")// url pattern
	public void doB() { 
		logger.info("내 로그 => doB called...");//로그 출력
		// method가 종료된 후 doB.jsp로 포워드 됨
	}
	
	
//	ModelAndView : Model - 데이터 저장소, View 화면 (값을 하나 밖에 못 보냄)
//	데이터와 포워드할 페이지의 정보
//	forward : 주소 그대로 화면 전환, 대량의 데이터 전달
//	redirect: 주소 바뀜, 화면 전환, 소량의 get 방식 데이터
	@RequestMapping("test/doC")
	public ModelAndView doC() {
		
		Map<String, Object> map = new HashMap<>();
		// 맵에 객체 저장
		map.put("product", new ProductDTO("샤프", 1000));
		
		// new ModelAndView("view의 이름", "맵변수명", 맵);		
		return new ModelAndView("test/doC", "map", map);
	}
	
	
	
	@RequestMapping("test/doD")
	public String doD() {
		// redirect의 경우 return type을 String으로 설정
		// doE.jsp로 리다이렉트됨
		return "redirect:/test/doE";//포워드
	}
	
	@RequestMapping("test/doE")
	public void doE() {}

	
	
}
