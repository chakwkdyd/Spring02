package com.example.spring02.service.pdf;

import java.io.FileOutputStream;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.spring02.model.shop.dto.CartDTO;
import com.example.spring02.service.shop.CartService;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


@Service
public class PdfServiceImpl implements PdfService {

	@Inject
	CartService cartService; //  장바구니 목록
	
	@Override
	public String createPdf() {
		
		String result = "";
		
		try {
			Document document = new Document(); // PDF문서를 처리하는 객체
			PdfWriter writer = 
					PdfWriter.getInstance(document, new FileOutputStream("d:/sample.pdf")); // PDF를 아래경로처럼 생성한다
			
			document.open(); // 도큐먼트를 열고 (기본적으로 한글 처리가 안되서 아래처럼 한글 설정을 해준다)
			BaseFont baseFont = BaseFont.createFont(
					"c:/windows/fonts/malgun.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(baseFont, 12); // 폰트 싸이즈 설정
			PdfPTable table = new PdfPTable(4); // 4개의 테이블 `
			Chunk chunk = new Chunk("장바구니", font);// Chunk => 타이틀
			Paragraph ph = new Paragraph(chunk);	// 문단 만들고
			ph.setAlignment(Element.ALIGN_CENTER);	// 가운데 정렬
			document.add(ph);
			
			document.add(Chunk.NEWLINE);	// 줄 바꿈
			document.add(Chunk.NEWLINE);	// 줄 바꿈
			
			PdfPCell cell1 = new PdfPCell(new Phrase("상품명", font));// 셀을 생성하고 어떤 폰트인지 설정
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);		// 셀의 정렬방식 지정(가운데 정렬)
			
			PdfPCell cell2 = new PdfPCell(new Phrase("단가", font));
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			PdfPCell cell3 = new PdfPCell(new Phrase("수량", font));
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			PdfPCell cell4 = new PdfPCell(new Phrase("금액", font));
			cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			table.addCell(cell1);// 테이블에 셀을 붙임
			table.addCell(cell2);
			table.addCell(cell3);
			table.addCell(cell4);
			
			List<CartDTO> items = cartService.listCart("admin");// 장바구니 아이디를 가져온다 !! 아이디로 작성해야 함!!!!!
			for(int i=0; i<items.size(); i++) {
				
				CartDTO dto = items.get(i); // 각각의 레코드를 꺼내서 dto에 저장함

				// Phrase=>는 숫자형이 안돼서 문자로 전부 바꿔줌
				PdfPCell cellProductName = 
						new PdfPCell(new Phrase(dto.getProduct_name(), font));	// 상품이름
				
				PdfPCell cellPrice = 
						new PdfPCell(new Phrase(""+dto.getPrice(), font));		// 상품가격
				
				PdfPCell cellAmount = 
						new PdfPCell(new Phrase(""+dto.getAmount(), font));		// 상품 수량
				
				PdfPCell cellMoney = 
						new PdfPCell(new Phrase(""+dto.getMoney(), font));		// 상품 총 합가격
				
				
				table.addCell(cellProductName); // 테이블에 셀들을 전부 넣는다
				table.addCell(cellPrice);
				table.addCell(cellAmount);
				table.addCell(cellMoney);
			}
			document.add(table);
			document.close();
			result="pdf 파일이 생성되었습니다.";
		} catch (Exception e) {
			e.printStackTrace();
			result="pdf 파일 생성 실패!!!";
			
		}
		return result;
	}

}
