package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Brand;
import com.example.demo.model.Product;
import com.example.demo.model.DTO.MainBrandDTO;
import com.example.demo.model.DTO.MainProductDTO;
import com.example.demo.model.DTO.TestQueryDTO;
import com.example.demo.model.DTO.TestResponseDTO;
import com.example.demo.service.TestService;

@RestController
@CrossOrigin("*")
public class TestController {

	@Autowired
	private TestService ts;
	
	@GetMapping("/api/test")
	public ResponseEntity<TestResponseDTO> getMain() {
		
		// 상품데이터 취득
		List<Product> productList = ts.findAll();
		
		// responseDTO에 담기
		List<MainProductDTO> mainProList = new ArrayList<>();
		for(Product temp : productList) {
			MainProductDTO dto = new MainProductDTO();
			dto.setPrice(temp.getLaunchingPrice());
			dto.setProductName(temp.getProductKorName());
			mainProList.add(dto);
		}
		
		// 브랜드 데이터 취득
		List<Brand> brandList = ts.findBrand();
		
		List<MainBrandDTO> mainBrandList = new ArrayList<>();
		for(Brand temp : brandList) {
			MainBrandDTO dto = new MainBrandDTO();
			dto.setBrandId(temp.getBrandId());
			dto.setName(temp.getBrandName());
			mainBrandList.add(dto);
		}
	
		TestResponseDTO resultDto = new TestResponseDTO();
		resultDto.setBrandDtoList(mainBrandList);
		resultDto.setProductDtoList(mainProList);
		
		return new ResponseEntity<TestResponseDTO>(resultDto, HttpStatus.OK);
		
	}
	
	@GetMapping("/api/test2")
	public ResponseEntity<List<TestQueryDTO>> getTest() {
		
		List<TestQueryDTO> dtoList = ts.findData();
		
		return new ResponseEntity<List<TestQueryDTO>>(dtoList, HttpStatus.OK);
		
	}
	
	
	
}
