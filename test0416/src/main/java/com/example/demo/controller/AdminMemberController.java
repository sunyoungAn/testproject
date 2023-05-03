package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Member;
import com.example.demo.model.Notice;
import com.example.demo.model.DTO.AdminMemberDTO;
import com.example.demo.model.DTO.AdminMemberInfoDTO;
import com.example.demo.service.AdminMemberService;
import com.example.demo.specification.MemberSpecification;

@RestController
public class AdminMemberController {
	
	@Autowired
	private AdminMemberService adminMemberService;
	
	/*
	 * 회원리스트 - 회원 리스트 전체 가져오기
	 */
	@GetMapping("/api/admin/member/getall")
	public Page<Member> getMemberAll(Pageable pageable) {
		
		Sort sort = Sort.by("memberNumber").descending(); 
		pageable = PageRequest.of(pageable.getPageNumber(), 10, sort);
		
		Page<Member> memberList = adminMemberService.getMemberAll(pageable);
		
		return memberList;
	}
	
	/*
	 * 회원 검색
	 */
	@GetMapping("/api/admin/member/search")
	public Page<Member> searchMember(@RequestParam(value="memberNumber", required=false) Long memberNumber,
									@RequestParam(value="name", required=false) String name,
									@RequestParam(value="email", required=false) String email,
									@RequestParam(value="phoneNumber", required=false) String phoneNumber, Pageable pageable) {
		
		
		System.out.println("회원번호 : " +  memberNumber);
		System.out.println("이름 : " +  name);
		System.out.println("이메일 : " +  email);
		System.out.println("폰번호 : " +  phoneNumber);
		
		
		Sort sort = Sort.by("memberNumber").descending(); 
		pageable = PageRequest.of(pageable.getPageNumber(), 10, sort);
		
		Specification<Member> spec = (root, query, criteriaBuilder) -> null;
		
		if(memberNumber != null) {
			spec = spec.and(MemberSpecification.equalMemberNumber(memberNumber));
		}
		
		if(name != null) {
			spec = spec.and(MemberSpecification.likeName(name));
		}
		
		if(email != null) {
			spec = spec.and(MemberSpecification.likeEmail(email));
		}
		
		if(phoneNumber != null) {
			spec = spec.and(MemberSpecification.likePhoneNumber(phoneNumber));
		}

		Page<Member> resultList = adminMemberService.searchMember(spec, pageable);
		
		return resultList;
	}
	
	
	/*
	 * 회원 상세내용 검색
	 */
	@GetMapping("/api/admin/member/getone/{memberNumber}")
	public AdminMemberInfoDTO getmemberOne(@PathVariable("memberNumber") Long memberNumber) {
		
		AdminMemberInfoDTO resultDto = adminMemberService.findById(memberNumber);
		return resultDto;
	}
	
	
	/*
	 * 회원 수정 
	 */
	@PutMapping("/api/admin/member/edit/{memberNumber}")
	public void editMember(@PathVariable("memberNumber") Long memberNumber, @RequestBody AdminMemberDTO dto) {
		
		adminMemberService.edit(memberNumber, dto);
	}
	
	
	/*
	 * 회원 탈퇴
	 */

}
