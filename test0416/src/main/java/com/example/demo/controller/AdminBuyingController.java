package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Buying;
import com.example.demo.model.DTO.AdminBuyingResponseDTO;
import com.example.demo.model.DTO.AdminBuyingStatusEditDTO;
import com.example.demo.service.AdminBuyingService;
import com.example.demo.specification.BuyingSpecification;

@RestController
public class AdminBuyingController {
	
	@Autowired
	private AdminBuyingService adminBuyingService;

	/*
	 * 구매입찰리스트 - 구매입찰관리페이지에 띄울 정보 모두 가져오기
	 */
	@GetMapping("/api/admin/buying/getall")
	public AdminBuyingResponseDTO getBuyingAll(Pageable pageable) {
		
		Sort sort = Sort.by("id").descending(); 
		pageable = PageRequest.of(pageable.getPageNumber(), 10, sort);
		
		Specification<Buying> spec = (root, query, criteriaBuilder) -> null;
		
		spec = spec.and(BuyingSpecification.equalDataStatus(1));
		
		AdminBuyingResponseDTO reponseDto = adminBuyingService.getBuyingInfo(spec, pageable);
		
		return reponseDto;
	}
	
	/*
	 * 구매입찰검색
	 */
	@GetMapping("/api/admin/buying/search")
	public AdminBuyingResponseDTO searchBuying(@RequestParam(value="id", required=false) Long id,
												@RequestParam(value="productId", required=false) Long productId,
												@RequestParam(value="memberNumber", required=false) Long memberNumber,
												@RequestParam(value="buyingStatus", required=false) Integer buyingStatus,
												@RequestParam(value="expiryDateStart", required=false) String expiryDateStart,
												@RequestParam(value="expiryDateEnd", required=false) String expiryDateEnd, Pageable pageable) {
		
		Sort sort = Sort.by("id").descending(); 
		pageable = PageRequest.of(pageable.getPageNumber(), 10, sort);
		
		Specification<Buying> spec = (root, query, criteriaBuilder) -> null;
		
		spec = spec.and(BuyingSpecification.equalDataStatus(1));
		
		if(id != null) {
			spec = spec.and(BuyingSpecification.equalId(id));
		}
		
		if(productId != null) {
			spec = spec.and(BuyingSpecification.equalProductId(productId));
		}
		
		if(memberNumber != null) {
			spec = spec.and(BuyingSpecification.equalMemberNumber(memberNumber));
		}
		
		if(buyingStatus != 0) {
			spec = spec.and(BuyingSpecification.equalBuyingStatus(buyingStatus));
		}
		
		if(expiryDateStart != null && expiryDateStart != "") {
			
			String str = expiryDateStart + " 00:00:00.000";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
			LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
			
			spec = spec.and(BuyingSpecification.greaterThanOrEqualToExpiryDateStart(dateTime));
		}
		
		if(expiryDateEnd != null && expiryDateEnd != "") {
			
			String str = expiryDateEnd + " 23:59:59.999";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
			LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
			
			spec = spec.and(BuyingSpecification.lessThanOrEqualToExpiryDateEnd(dateTime));
		}
		
		AdminBuyingResponseDTO resultDto = adminBuyingService.getBuyingInfo(spec, pageable);
		
		return resultDto;
	}
	
	/*
	 * 구매입찰상태 변경
	 */
	@PutMapping("/api/admin/buying/edit")
	public void editBuyingStatus(@RequestBody List<AdminBuyingStatusEditDTO> dtoList) {
		
		adminBuyingService.editBuyingStatus(dtoList);
		
	}
	
	/*
	 * 구매입찰 삭제
	 */
	@PutMapping("/api/admin/buying/delete")
	public void deleteBuying(@RequestBody List<Long> ids) {
		
		adminBuyingService.delete(ids);
	}
	
}
