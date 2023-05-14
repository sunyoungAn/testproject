package com.example.demo.model.DTO;

import java.util.List;

import lombok.Data;

@Data
public class AdminReviewResponseDTO {
	
	private List<AdminReviewInfoDTO> reviewList;
	
	private Long total;

}
