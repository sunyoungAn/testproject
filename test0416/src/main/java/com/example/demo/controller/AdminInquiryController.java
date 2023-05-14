package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Inquiry;
import com.example.demo.model.DTO.AdminInquiryResponseDTO;
import com.example.demo.service.AdminInquiryService;
import com.example.demo.specification.InquirySpecification;

@RestController
public class AdminInquiryController {
	
	@Autowired
	private AdminInquiryService adminInquiryService;
	
	/*
	 *  문의리스트 - 문의관리페이지에 띄울 정보 모두 가져오기
	 */
	@GetMapping("/api/admin/inquiry/getall")
	public AdminInquiryResponseDTO getInquiryAll(Pageable pageable) {
		
		Sort sort = Sort.by("id").descending(); 
		pageable = PageRequest.of(pageable.getPageNumber(), 10, sort);
		
		Specification<Inquiry> spec = (root, query, criteriaBuilder) -> null;
		
		AdminInquiryResponseDTO responseDto = adminInquiryService.getInquiryInfo(spec, pageable);
		
		return responseDto;
	}
	
	/*
	 * 문의검색
	 */
	@GetMapping("/api/admin/inquiry/search")
	public AdminInquiryResponseDTO searchinquiry(@RequestParam(value="id", required=false) Long id,
											@RequestParam(value="category", required=false) Integer category,
											@RequestParam(value="memberNumber", required=false) Long memberNumber,
											@RequestParam(value="inquiryStatus", required=false) Integer inquiryStatus, Pageable pageable) {
	
		Sort sort = Sort.by("id").descending(); 
		pageable = PageRequest.of(pageable.getPageNumber(), 10, sort);
		
		Specification<Inquiry> spec = (root, query, criteriaBuilder) -> null;
		
		if(id != null) {
			spec = spec.and(InquirySpecification.equalId(id));
		}
		
		if(category != 0) {
			spec = spec.and(InquirySpecification.equalCategory(category));
		}
		
		if(memberNumber != null) {
			spec = spec.and(InquirySpecification.equalMemberNumber(memberNumber));
		}
		
		if(inquiryStatus != 0) {
			spec = spec.and(InquirySpecification.equalInquiryStatus(inquiryStatus));
		}
		
		AdminInquiryResponseDTO responseDto = adminInquiryService.getInquiryInfo(spec, pageable);
		
		return responseDto;
	}
	
	/*
	 * 문의 삭제
	 */
	@DeleteMapping("/api/admin/inquiry/delete/{ids}")
	public void deleteInquiry(@PathVariable List<Long> ids) {
		
		adminInquiryService.delete(ids);
	}
	
	/*
	 * 문의상세내용 가져오기
	 */
//	@GetMapping("/api/admin/inquiry/getone/{id}")
	
	
	/*
	 * 답변등록, 수정하기
	 */

}
