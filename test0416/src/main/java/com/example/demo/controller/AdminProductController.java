package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.model.DTO.AdminProductListPageDTO;
import com.example.demo.model.DTO.AdminProductSearchResultDTO;
import com.example.demo.service.AdminProductService;
import com.example.demo.specification.ProductSpecification;

@RestController
public class AdminProductController {
	
	@Autowired
	private AdminProductService adminProductService;
	
	/*
	 * 상품리스트 - 상품관리리스트페이지에 띄울 정보 모두 가져오기
	 */
	@GetMapping("/api/admin/product/getall")
	public AdminProductListPageDTO getProductPageInfo(Pageable pageable) {
		
		Sort sort = Sort.by("id").descending(); 
		pageable = PageRequest.of(pageable.getPageNumber(), 10, sort);
		
		Specification<Product> spec = (root, query, criteriaBuilder) -> null;
		
		spec = spec.and(ProductSpecification.equalDataStatus(1));
		
		AdminProductListPageDTO resultDto = adminProductService.getPageInfo(spec, pageable);
		
		return resultDto;
	}
	
	/*
	 * 상품검색
	 */
	@GetMapping("/api/admin/product/search")
	public AdminProductSearchResultDTO searchProduct(@RequestParam(value="id", required=false) Long id,
													@RequestParam(value="name", required=false) String name,
													@RequestParam(value="brandId", required=false) Long brandId,
													@RequestParam(value="category", required=false) Integer category, Pageable pageable) {
		
		Sort sort = Sort.by("id").descending(); 
		pageable = PageRequest.of(pageable.getPageNumber(), 10, sort);
		
		Specification<Product> spec = (root, query, criteriaBuilder) -> null;
		
		spec = spec.and(ProductSpecification.equalDataStatus(1));
		
		if(id != null) {
			spec = spec.and(ProductSpecification.equalId(id));
		}
		
		if(name != null) {
			spec = spec.and(ProductSpecification.likeProductEngName(name));
			spec = spec.and(ProductSpecification.likeProductKorName(name));
		}
		
		if(brandId != null && brandId != 0) {
			spec = spec.and(ProductSpecification.equalBrandId(brandId));
		}
		
		if(category != null && category != 0) {
			spec = spec.and(ProductSpecification.equalCategory(category));
		}
		
		AdminProductSearchResultDTO resultDto = adminProductService.searchProduct(spec, pageable);
		
		return resultDto;
	}
	
	
	/*
	 * 상품삭제
	 */
	
	/*
	 * 상품등록
	 */
	
	/*
	 * 상품수정
	 */

}
