package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Member;
import com.example.demo.service.AdminMemberService;

@RestController
public class AdminMemberController {
	
	@Autowired
	private AdminMemberService adminMemberService;
	
	/*
	 * 멤버리스트 - 멤버 리스트 전체 가져오기
	 */
	@GetMapping("/api/admin/member/getall")
	public Page<Member> getMemberAll(Pageable pageable) {
		
		Sort sort = Sort.by("memberNumber").descending(); 
		pageable = PageRequest.of(pageable.getPageNumber(), 10, sort);
		
		Page<Member> memberList = adminMemberService.getMemberAll(pageable);
		
		return memberList;
	}
	
	/*
	 * 멤버 검색
	 */
	
	
	/*
	 * 멤버 상세내용 검색
	 */
	
	/*
	 * 멤버 수정 
	 */

}
