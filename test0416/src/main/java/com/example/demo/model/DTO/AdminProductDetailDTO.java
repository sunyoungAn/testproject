package com.example.demo.model.DTO;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class AdminProductDetailDTO {
	
	private String productEngName;
	
	private String productKorName;
	
	private Long brandId;
	
	private Integer category;
	
	private String modelNumber;
	
	private LocalDate launchingDate;
	
	private String color;
	
	private Integer launchingPrice;
	
	private Integer sizeMin;
	
	private Integer sizeMax;
	
	private Integer sizeUnit;
	
	private Integer gender;
	
	private Integer resellTarget;
	
	private String explanation;
	
	private List<AdminImageDTO> imageDtoList;

}
