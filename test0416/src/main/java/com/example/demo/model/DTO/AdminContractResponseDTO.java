package com.example.demo.model.DTO;

import java.util.List;

import lombok.Data;

@Data
public class AdminContractResponseDTO {
	
	private List<AdminContractInfoDTO> contractList;
	
	private Long total;

}
