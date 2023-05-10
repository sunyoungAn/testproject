package com.example.demo.model.DTO;

import java.util.List;

import lombok.Data;

@Data
public class AdminBuyingResponseDTO {
	
	private List<AdminBuyingInfoDTO> buyingList;
	
	private Long total;

}
