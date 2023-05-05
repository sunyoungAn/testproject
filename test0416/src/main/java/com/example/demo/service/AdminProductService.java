package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.model.DTO.AdminBrandDTO;
import com.example.demo.model.DTO.AdminProductListPageDTO;
import com.example.demo.model.DTO.AdminProductSearchResultDTO;
import com.example.demo.repository.BrandRepository;
import com.example.demo.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class AdminProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private BrandRepository brandRepository;

	/*
	 * 상품리스트 - 상품관리리스트페이지에 띄울 정보 모두 가져오기
	 */
	public AdminProductListPageDTO getPageInfo(Specification<Product> spec, Pageable pageable) {
		
		// 상품정보
		Page<Product> productList = productRepository.findAll(spec, pageable);
		
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

	/*
	 * 상품검색
	 */
	public AdminProductSearchResultDTO searchProduct(Specification<Product> spec, Pageable pageable) {

		// 상품정보
		Page<Product> productList = productRepository.findAll(spec, pageable);
		
		// 상품의 브랜드 정보
		List<Product> targetProducts = productList.getContent();
				
		HashMap<Long, String> map = new HashMap<Long, String>();
		for(int i = 0; i<targetProducts.size(); i++) {
			map.put(targetProducts.get(i).getId(), targetProducts.get(i).getBrand().getBrandName());
		}
		
		AdminProductSearchResultDTO resultDto = new AdminProductSearchResultDTO();
		resultDto.setProductList(productList);
		resultDto.setProductBrandMap(map);
		
		return resultDto;
	}

	/*
	 * 상품삭제
	 */
	@Transactional
	public void delete(List<Long> ids) {
		
		// id 갯수만큼 반복하기
		for(Long id : ids) {
			Product targetProduct = productRepository.findById(id).get();
			targetProduct.setDataStatus(2);
			targetProduct.setModifiedDate(LocalDateTime.now());
			
			productRepository.save(targetProduct) ;
		}
	}

}