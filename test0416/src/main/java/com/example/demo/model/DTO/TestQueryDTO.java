package com.example.demo.model.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestQueryDTO {
	
	private Long id;
	
	private String ProductEngName;
	
	private Integer dataStatus;
	
	private LocalDate lanuchingDate;
	
	private String imgagePath;
	
	private Integer mainImageDiv;
	
	private String brandName;
	
	private Integer wishPrice;
	
	private Integer InventoryDiv;

}
