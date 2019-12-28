package com.example.spring02.controller.upload;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping()
public class UploadController {
//		create table product(
//				product_id number,
//				product_name varchar2(50),
//				price number default 0,
//				description clob,
//				picture_url varchar2(500),
//				primary key(product_id)
//				);
	
//		insert into product values(1, '레몬',1500,'레몬에 포함된.........','lemon.jpg');
//		insert into product values(2, '오렌지',2000,'오렌지에 포함된.........','orange.jpg');
//		insert into product values(3, '키위',3000,'키위에 포함된.........','kiwi.jpg');
//		insert into product values(4, '포도',5000,'포도에 포함된.........','grape.jpg');
//		insert into product values(5, '딸기',8000,'딸기에 포함된.........','strawberry.jpg');
//		insert into product values(6, '귤',7000,'귤에 포함된.........','tangerine.jpg');
//		select * from product;
//		commit;
		
//create sequence seq_product
//		start with 10 // 10부터 시작
//		increment by 1; // 1씩 증가
//seq_product.nextval => 자동으로 설정 값(다음번호) 만큼 증가됨
//insert into product values(seq_product.nextval,'사과',1500,'맛있는 사과에요','apple.jpg');
//commit;
	
	
	private static final Logger logger = 
			LoggerFactory.getLogger(UploadController.class);
		
	// xml에 설정된 리소스 참조
	// bean의 id가 uploadPath인 태그를 참조
	@Resource(name = "uploadPath")
	String uploadPath;
	
//	========================<>========================
	@RequestMapping(value = "/upload/uploadForm", method = RequestMethod.GET)
	public void uploadForm() {
		// void => upload/uploadForm.jsp로 포워딩
	}
	
//	========================<>========================
	// 업로드 버튼 => 임시디렉토리에 업로드
	// => 파일 정보가 file에 저장  => 지정된 디렉토리에 저장 =>
	@RequestMapping(value = "upload/uploadForm", method = RequestMethod.POST)
	public ModelAndView uploadForm(MultipartFile file, ModelAndView mav) throws Exception {
		
		logger.info("파일이름:"+file.getOriginalFilename());
		String savedName = file.getOriginalFilename();
		
		logger.info("파일크기:"+file.getSize());
		logger.info("컨텐트 타입:"+file.getContentType());
		savedName =  uploadFile(savedName, file.getBytes());
		
		mav.setViewName("upload/uploadResult");
		mav.addObject("savedName", savedName);
		return mav;
	}
	
	
//	========================<>========================
	// 파일 이름이 중복되지 않도록 처리
	private String uploadFile(String originalName, byte[] fileData)throws Exception {
		
		// uuid 생성 (Universal Unique Identifier, 범용 고유 식별자)
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_" + originalName;
		File target = new File(uploadPath, savedName);
		// FieCopyUtils.copy (바이트배열, 파일객체)
		FileCopyUtils.copy(fileData, target);
		return savedName;
	}
	
	
//	========================<>========================
//	========================<>========================
//	========================<>========================
		
}
