package com.example.spring02.service.shop;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.spring02.model.shop.dao.ProductDAO;
import com.example.spring02.model.shop.dto.ProductDTO;

@Service
public class ProductServiceImpl implements ProductService {

	@Inject
	ProductDAO productDao;
	
//	===========<상품목록>===============
	@Override
	public List<ProductDTO> listProduct() {
		return productDao.listProduct();
	}
//	===========<상품목록 상세정보>===============
	@Override
	public ProductDTO detailProduct(int product_id) {
		return productDao.detailProduct(product_id);
	}
//	===========<상품정보수정>===============
	@Override
	public void updateProduct(ProductDTO dto) {
		productDao.updateProduct(dto);
	}
//	===========<상품정보 삭제>===============
	@Override
	public void deleteProduct(int product_id) {
		productDao.deleteProduct(product_id);
	}
//	===========<상품 추가>===============
	@Override
	public void insertProduct(ProductDTO dto) {
		productDao.insertProduct(dto);
	}
//	===========<상품파일 정보>===============
	@Override
	public String fileInfo(int product_id) {
		return productDao.fileInfo(product_id);
	}

}
