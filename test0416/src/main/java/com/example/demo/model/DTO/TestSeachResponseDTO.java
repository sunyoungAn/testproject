package com.example.demo.model.DTO;

import java.util.List;

import com.example.demo.model.Member;

import lombok.Data;

@Data
public class TestSeachResponseDTO {
	
	private List<Member> memberList;

}
