package com.example.spring02.controller.shop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.shop.dto.CartDTO;
import com.example.spring02.service.shop.CartService;

@Controller
@RequestMapping("/shop/cart/*")
public class CartController {
	
	@Inject
	CartService cartService;

//	===============< 장바구니 담기 > ================
	@RequestMapping("insert.do") 
	public String insert(@ModelAttribute CartDTO dto,
						HttpSession session) {
		
		// 로그인 여부를 체크하기 위해 세션에 저장된 아이디 확인  
		String userid = (String)session.getAttribute("userid");
		
		if(userid==null) {// 로그인 하지 않은 상태이면 로그인 화면으로 이동
			return "redirect:/member/login.do";
		}
		
		dto.setUserid(userid);
		cartService.insert(dto); // 장바구니 테이블에 저장됨
		return "redirect:/shop/cart/list.do"; // 장바구니 목록으로 이동
	}
	
	
//	===============< List.do > ================
	@RequestMapping("list.do")
	public ModelAndView list(HttpSession session, ModelAndView mav) {
		
		System.out.println("여기!!!!!!!!!!");
		Map<String, Object> map = new HashMap<>();
		
		String userid=(String)session.getAttribute("userid");
		
		if (userid != null) {// 로그인한 상태
			
			List<CartDTO> list = cartService.listCart(userid); // 장바구니 목록
			// 장바구니 금액 합계
			int sumMoney = cartService.sumMoney(userid);
			//배송료 계산  (3만원 이상이면 배송비 0 : 이하면 2500원 추가)
			int fee = sumMoney >= 30000 ? 0 : 2500; 	
			
			map.put("sumMoney", sumMoney);
			map.put("fee", fee);			// 배송료 값 hashMap에 넣어줌
			map.put("su"
					+ "m", sumMoney+fee); 	//전체 금액
			map.put("list", list); 			// 장바구니 목록
			map.put("count", list.size());	// 레코드 갯수
			
			mav.setViewName("shop/cart_list");	// 이동할 페이지의 이름
			mav.addObject("map", map); 			// 데이터 저장
			
			return mav; // 화면 이동
		}else { // 로그인 하지 않은 상태
			return new ModelAndView("member/login", "", null);
		}
	}
	
//	==================< 삭제 >======================
	@RequestMapping("delete.do")
	public String delete(@RequestParam int cart_id) {
		cartService.delete(cart_id);
		return "redirect:/shop/cart/list.do";
	}
	
	
	
//	==================<장바구니 전체 삭제> ==================
	@RequestMapping("deleteAll.do")
	public String deleteAll(HttpSession session) {
		
		String userid=(String)session.getAttribute("userid");
		// 로그인 중이면 삭제
		if(userid != null) {
			cartService.deleteAll(userid);
		}
		return "redirect:/shop/cart/list.do";
	}
	
	
//	==================<수정 기능> ==================
	@RequestMapping("update.do")
	public String update(int[] amount,
						int[] cart_id,
						HttpSession session) {
		String userid = (String)session.getAttribute("userid");
		
		for (int i = 0; i < cart_id.length; i++) {
			
			// 수량이 0이되면 삭제해라
			if (amount[i] == 0) {
				cartService.delete(cart_id[i]);
			}else {
				CartDTO dto = new CartDTO();
				dto.setUserid(userid);
				dto.setCart_id(cart_id[i]);
				dto.setAmount(amount[i]);
				cartService.modifyCart(dto);
			}
		}
		return "redirect:/shop/cart/list.do";
	}
	
	
	
	
	
	
	
	
	
}
