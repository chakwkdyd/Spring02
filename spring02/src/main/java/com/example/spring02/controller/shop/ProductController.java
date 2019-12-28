package com.example.spring02.controller.shop;

import java.io.File;

import javax.inject.Inject;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.shop.dto.ProductDTO;
import com.example.spring02.service.shop.ProductService;

@Controller
@RequestMapping("/shop/product/*")
public class ProductController {

//	create table cart (
//			cart_id number not null primary key, // 장바구니 코드(일련번호)
//			userid varchar2(50) not null,		// 누가
//			product_id number not null,			// 어떤상품
//			amount number default 0				// 몇개 
//			);
//	
//	
//	
//	
//	-- foreign key 설정
//	alter table cart add constraint cart_userid_fk 
//	foreign key(userid) references member(userid);// 멤버 유저만가 가입하게
//	
//	--제약조건 삭제
//	alter table cart drop constraint cart_productid_fk;
//	
//	-- 제약조건 추가
//	alter table cart add constraint cart_productid_fk // 상품코드 겹
//	foreign key(product_id) references product(product_id);
//	
//	create sequence seq_cart
//	start with 1 		// 1부터 시작
//	increment by 1;		// 1씩 증가
	
	
	
	@Inject
	ProductService productService; // 스프링에서 만든 서비스 객체를 연결시킴
	
	@RequestMapping("list.do")
	public ModelAndView list(ModelAndView mav) {
		mav.setViewName("/shop/product_list");
		mav.addObject("list", productService.listProduct());//데이터저장
		return mav;
	}
	
	
//	===============<상품 자세히 보기>===============
	@RequestMapping("/detail/{product_id}") // 고유 url로 받음 
	public ModelAndView detail(@PathVariable("product_id") int product_id,
							 	ModelAndView mav) {
		
		mav.setViewName("/shop/product_detail");
		mav.addObject("dto", productService.detailProduct(product_id));
		return mav;
	}
	
	
//	================<상품 추가 페이지 이동>=============
	@RequestMapping("write.do")
	public String write() {
		return "shop/product_write";
	}
	
//	================<상품 추가하기>=============
	@RequestMapping("insert.do")
	public String insert(ProductDTO dto) {
		
		String filename="-";  
			
		// isEmpty() => 비어 있냐?
		if ( !dto.getFile1().isEmpty() ) { // 첨부파일이 존재하면!
			
			filename=dto.getFile1().getOriginalFilename();
			// 내가 지정해 놓은 이미지 파일 경로 \\ or / 둘다 사용 가능
			// 개발디렉토리 배포 디렉토리와 별개임(주소가 다르게 표현됨) 그래서 두개를 합쳐줌!!
			//String path ="D:\\workspace\\spring02\\src\\main\\webapp\\WEB-INF\\views\\images\\"; 
			// 아래 처럼 하면 바로 업로드 성공 (개발디렉토리(주소) + 배포 디렉토리(주소)를 합치고 마지막에 \\ 두개 추가하기!
			String path ="D:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\spring02\\WEB-INF\\views\\images\\"; 
			//  D:\\workspace\\spring02\\src\\main\\webapp\\WEB-INF\\views\\images\\ 		          												=> 개발 디렉토리
			//  D:\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\spring02\ 												=> 배포 디렉토리
			//  D:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\spring02\\WEB-INF\\views\\images\\           	=> 전부 포함된 디렉토리 
			try {
				new File(path).mkdir(); // 디렉토리가 없을수도 있으니 파일 생성!
				dto.getFile1().transferTo(new File(path+filename)); // 생성된 디렉토리에 파일을 집어 넣는다
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		dto.setPicture_url(filename);			// 파일 이름 셋팅하고
		productService.insertProduct(dto);		// db에 값을 집넣는다
		return "redirect:/shop/product/list.do";
	}
	
//	================<상품 편집>=============
	// 상품정보 편집 페이지로 이동
	@RequestMapping("edit/{product_id}")
	public ModelAndView edit(@PathVariable("product_id") int product_id,
								ModelAndView mav) {
		
		mav.setViewName("/shop/product_edit"); // 이동할 페이지의 이름
		mav.addObject("dto", productService.detailProduct(product_id));
		System.out.println("저부 mav ==> "+ mav);
		
		return mav;
	}
	
	
//	================<상품 수정>=============
	@RequestMapping("update.do")
	public String update(ProductDTO dto) {
		
		String filename="-";
		
		if(!dto.getFile1().isEmpty() ) {// 첨부파일이 존재하면
			filename = dto.getFile1().getOriginalFilename();
			String path ="D:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\spring02\\WEB-INF\\views\\images\\";
			try {
				new File(path).mkdir();
				dto.getFile1().transferTo(new File(path+filename));
			} catch (Exception e) {
				e.printStackTrace();
			}
			dto.setPicture_url(filename);
		}else {
			ProductDTO dto2 =
					productService.detailProduct(dto.getProduct_id());
			dto.setPicture_url(dto2.getPicture_url() );
		}
		productService.updateProduct(dto);
		return "redirect:/shop/product/list.do";
	}
	
//	================<상품 삭제>=============
	@RequestMapping("delete.do")
	public String delete(@RequestParam int product_id) {
		
		// 첨부파일의 이름
		String filename = productService.fileInfo(product_id);
		
		// 파일이 첨부 되어 있는 상품을 삭제시(조건문을 따로 줌) 1. 파일을 먼저 지고운다     2.그다음 레코드를 삭제함
		if(filename != null && !filename.equals("-")) { // 초기화를 "-"로 해줬기 때문에 두개의 조건을 걸어줌
			String path = "D:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\spring02\\WEB-INF\\views\\images\\";
			
			File f = new File(path + filename);
			if(f.exists() ) { // 파일이 존재하면
				f.delete();	  // 파일 삭제
			}
		}
		
		productService.deleteProduct(product_id); // 레코드 삭제
		return "redirect:/shop/product/list.do";
	}
	
	
	
}
