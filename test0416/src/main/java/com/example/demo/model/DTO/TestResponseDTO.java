package com.example.demo.model.DTO;

import java.util.List;

import lombok.Data;

@Data
public class TestResponseDTO {

	private List<MainBrandDTO> brandDtoList;
	
	private List<MainProductDTO> productDtoList;
	
}
