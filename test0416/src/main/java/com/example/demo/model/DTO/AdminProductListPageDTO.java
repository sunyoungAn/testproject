package com.example.demo.model.DTO;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.model.Product;

import lombok.Data;

@Data
public class AdminProductListPageDTO {
	
	private List<AdminBrandDTO> brandList;
	
	private Page<Product> productList;
	
	private HashMap<Long, String> productBrandMap;

}
