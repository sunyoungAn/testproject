package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.model.DTO.AdminBrandDTO;
import com.example.demo.model.DTO.AdminProductListPageDTO;
import com.example.demo.repository.BrandRepository;
import com.example.demo.repository.ProductRepository;

@Service
public class AdminProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private BrandRepository brandRepository;

	/*
	 * 상품리스트 - 상품관리리스트페이지에 띄울 정보 모두 가져오기
	 */
	public AdminProductListPageDTO getPageInfo(Pageable pageable) {
		
		// 상품정보
		Page<Product> productList = productRepository.findAll(pageable);
		
		// 브랜드정보
		List<AdminBrandDTO> brandList = brandRepository.findBrandCategory();
		
		// 상품의 브랜드 정보
		List<Product> targetProducts = productList.getContent();
		
		HashMap<Long, String> map = new HashMap<Long, String>();
		for(int i = 0; i<targetProducts.size(); i++) {
			map.put(targetProducts.get(i).getId(), targetProducts.get(i).getBrand().getBrandName());
		}
		
		AdminProductListPageDTO resultDto = new AdminProductListPageDTO();
		resultDto.setProductList(productList);
		resultDto.setBrandList(brandList);
		resultDto.setProductBrandMap(map);
		
		return resultDto;
	}

}
