package com.example.demo.model.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AdminSellingPaymentDTO {

	private Integer paymentType;
	
	private Integer price;
	
	private LocalDateTime registDate;
	
}
