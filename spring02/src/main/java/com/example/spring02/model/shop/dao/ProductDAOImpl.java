package com.example.spring02.model.shop.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring02.model.shop.dto.ProductDTO;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Inject
	SqlSession sqlSession;
	
//	===========<상품목록>===============
	@Override
	public List<ProductDTO> listProduct() {
		return sqlSession.selectList("product.list_product");
	}

//	===========<상품목록 상세정보>===============
	@Override
	public ProductDTO detailProduct(int product_id) {
		return sqlSession.selectOne("product.detail_product", product_id);
	}
//	===========<상품정보수정>===============
	@Override
	public void updateProduct(ProductDTO dto) {
		sqlSession.update("product.update_product", dto);
	}
//	===========<상품정보 삭제>===============
	@Override
	public void deleteProduct(int product_id) {
		sqlSession.delete("product.product_delete", product_id);
	}
//	===========<상품 추가>===============
	@Override
	public void insertProduct(ProductDTO dto) {
		sqlSession.insert("product.insert", dto);
	}
//	===========<상품파일 정보>===============
	@Override
	public String fileInfo(int product_id) {
		return sqlSession.selectOne("product.file_info", product_id);
	}

}
