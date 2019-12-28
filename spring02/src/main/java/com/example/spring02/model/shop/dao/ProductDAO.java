package com.example.spring02.model.shop.dao;

import java.util.List;

import com.example.spring02.model.shop.dto.ProductDTO;

public interface ProductDAO {
	
	// 상품목록
	List<ProductDTO> listProduct();
	// 상품목록 상세정보
	ProductDTO detailProduct(int product_id);
	// 상품정보수정
	void updateProduct(ProductDTO dto);
	// 상품정보 삭제
	void deleteProduct(int product_id);
	// 상품 추가
	void insertProduct(ProductDTO dto);
	// 상품파일 정보
	String fileInfo(int product_id);
}
