package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.DTO.AdminProductListPageDTO;
import com.example.demo.service.AdminProductService;

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
		
		AdminProductListPageDTO resultDto = adminProductService.getPageInfo(pageable);
		
		return resultDto;
	}

}
