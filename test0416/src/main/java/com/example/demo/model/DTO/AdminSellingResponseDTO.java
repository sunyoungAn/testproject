package com.example.demo.model.DTO;

import java.util.List;

import lombok.Data;

@Data
public class AdminSellingResponseDTO {
	
	private List<AdminSellingInfoDTO> sellingList;
	
	private Long total;
	
}
