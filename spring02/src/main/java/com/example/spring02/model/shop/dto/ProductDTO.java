package com.example.spring02.model.shop.dto;

import org.springframework.web.multipart.MultipartFile;

public class ProductDTO {
	
	private int product_id;		// 상품코드
	private String product_name;// 상품이름
	private int price;			// 가격
	private String description; // 설명
	private String picture_url; // 상품 이미지 주소
	private MultipartFile file1;// 첨부파일
	
	
	
	
	@Override
	public String toString() {
		return "ProductDTO [product_id=" + product_id + ", product_name=" + product_name + ", price=" + price
				+ ", description=" + description + ", picture_url=" + picture_url + ", file1=" + file1 + "]";
	}
	
	
	
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPicture_url() {
		return picture_url;
	}
	public void setPicture_url(String picture_url) {
		this.picture_url = picture_url;
	}
	public MultipartFile getFile1() {
		return file1;
	}
	public void setFile1(MultipartFile file1) {
		this.file1 = file1;
	}
	
	
	
	
}
