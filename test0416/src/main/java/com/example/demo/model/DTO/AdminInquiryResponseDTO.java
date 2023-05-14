package com.example.demo.model.DTO;

import java.util.List;

import lombok.Data;

@Data
public class AdminInquiryResponseDTO {

	private List<AdminInquiryInfoDTO> inquiryList;
	
	private Long total;
	
}
