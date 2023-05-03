package com.example.demo.model.DTO;

import java.util.List;

import lombok.Data;

@Data
public class TestDTO {

	private Integer filter;
	
	private List<Integer> memberStatusList;
	
	private List<Long> numberList;
	
}
