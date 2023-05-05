package com.example.demo.model.DTO;

import java.util.HashMap;

import org.springframework.data.domain.Page;

import com.example.demo.model.Product;

import lombok.Data;

@Data
public class AdminProductSearchResultDTO {
	
	private Page<Product> productList;
	
	private HashMap<Long, String> productBrandMap;

}
