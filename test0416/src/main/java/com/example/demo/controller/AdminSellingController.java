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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Selling;
import com.example.demo.model.DTO.AdminSellingPaymentDTO;
import com.example.demo.model.DTO.AdminSellingResponseDTO;
import com.example.demo.model.DTO.AdminSellingStatusEditDTO;
import com.example.demo.service.AdminSellingService;
import com.example.demo.specification.SellingSpecification;

@RestController
public class AdminSellingController {
	
	@Autowired
	private AdminSellingService adminSellingService;
	
	/*
	 * 판매입찰리스트 - 판매입찰관리페이지에 띄울 정보 모두 가져오기
	 */
	@GetMapping("/api/admin/selling/getall")
	public AdminSellingResponseDTO getSellingAll(Pageable pageable) {
		
		Sort sort = Sort.by("id").descending(); 
		pageable = PageRequest.of(pageable.getPageNumber(), 10, sort);
		
		Specification<Selling> spec = (root, query, criteriaBuilder) -> null;
		
		spec = spec.and(SellingSpecification.equalDataStatus(1));
		spec = spec.and(SellingSpecification.equalInventoryDiv(2));
		
		AdminSellingResponseDTO responseDto = adminSellingService.getSellingInfo(spec, pageable);
		
		return responseDto;
	}
	
	/*
	 * 판매입찰검색
	 */
	@GetMapping("/api/admin/selling/search")
	public AdminSellingResponseDTO searchSelling(@RequestParam(value="id", required=false) Long id,
												@RequestParam(value="productId", required=false) Long productId,
												@RequestParam(value="memberNumber", required=false) Long memberNumber,
												@RequestParam(value="sellingStatus", required=false) Integer sellingStatus,
												@RequestParam(value="expiryDateStart", required=false) String expiryDateStart,
												@RequestParam(value="expiryDateEnd", required=false) String expiryDateEnd, Pageable pageable) {
		
		Sort sort = Sort.by("id").descending(); 
		pageable = PageRequest.of(pageable.getPageNumber(), 10, sort);
		
		Specification<Selling> spec = (root, query, criteriaBuilder) -> null;
		
		spec = spec.and(SellingSpecification.equalDataStatus(1));
		spec = spec.and(SellingSpecification.equalInventoryDiv(2));
		
		if(id != null) {
			spec = spec.and(SellingSpecification.equalId(id));
		}
		
		if(productId != null) {
			spec = spec.and(SellingSpecification.equalProductId(productId));
		}
		
		if(memberNumber != null) {
			spec = spec.and(SellingSpecification.equalMemberNumber(memberNumber));
		}
		
		if(sellingStatus != 0) {
			spec = spec.and(SellingSpecification.equalSellingStatus(sellingStatus));
		}
		
		if(expiryDateStart != null && expiryDateStart != "") {
			
			String str = expiryDateStart + " 00:00:00.000";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
			LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
			
			spec = spec.and(SellingSpecification.greaterThanOrEqualToExpiryDateStart(dateTime));
		}
		
		if(expiryDateEnd != null && expiryDateEnd != "") {
			
			String str = expiryDateEnd + " 23:59:59.999";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
			LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
			
			spec = spec.and(SellingSpecification.lessThanOrEqualToExpiryDateEnd(dateTime));
		}
		
		AdminSellingResponseDTO resultDto = adminSellingService.getSellingInfo(spec, pageable);
		
		return resultDto;
	}

	
	/*
	 * 판매입찰상태 변경
	 */
	@PutMapping("/api/admin/selling/edit")
	public void editSellingStatus(@RequestBody List<AdminSellingStatusEditDTO> dtoList) {
		
		adminSellingService.editSellingStatus(dtoList);
		
	}
	
	/*
	 * 판매입찰 삭제
	 */
	@PutMapping("/api/admin/selling/delete")
	public void deleteSelling(@RequestBody List<Long> ids) {
		
		adminSellingService.delete(ids);
	}
	
	/*
	 * 보관판매리스트 - 보관판매관리페이지에 띄울 정보 모두 가져오기
	 */
	@GetMapping("/api/admin/inventory/getall")
	public AdminSellingResponseDTO getInventoryAll(Pageable pageable) {
		
		Sort sort = Sort.by("id").descending(); 
		pageable = PageRequest.of(pageable.getPageNumber(), 10, sort);
		
		Specification<Selling> spec = (root, query, criteriaBuilder) -> null;
		
		spec = spec.and(SellingSpecification.equalDataStatus(1));
		spec = spec.and(SellingSpecification.equalInventoryDiv(1));
		
		AdminSellingResponseDTO reponseDto = adminSellingService.getSellingInfo(spec, pageable);
		
		return reponseDto;
	}
	
	/*
	 * 보관판매상품검색
	 */
	@GetMapping("/api/admin/inventory/search")
	public AdminSellingResponseDTO searchInventory(@RequestParam(value="id", required=false) Long id,
												@RequestParam(value="productId", required=false) Long productId,
												@RequestParam(value="memberNumber", required=false) Long memberNumber,
												@RequestParam(value="sellingStatus", required=false) Integer sellingStatus,
												@RequestParam(value="expiryDateStart", required=false) String expiryDateStart,
												@RequestParam(value="expiryDateEnd", required=false) String expiryDateEnd, Pageable pageable) {
		
		Sort sort = Sort.by("id").descending(); 
		pageable = PageRequest.of(pageable.getPageNumber(), 10, sort);
		
		Specification<Selling> spec = (root, query, criteriaBuilder) -> null;
		
		spec = spec.and(SellingSpecification.equalDataStatus(1));
		spec = spec.and(SellingSpecification.equalInventoryDiv(1));
		
		if(id != null) {
			spec = spec.and(SellingSpecification.equalId(id));
		}
		
		if(productId != null) {
			spec = spec.and(SellingSpecification.equalProductId(productId));
		}
		
		if(memberNumber != null) {
			spec = spec.and(SellingSpecification.equalMemberNumber(memberNumber));
		}
		
		if(sellingStatus != 0) {
			spec = spec.and(SellingSpecification.equalSellingStatus(sellingStatus));
		}
		
		if(expiryDateStart != null && expiryDateStart != "") {
			
			String str = expiryDateStart + " 00:00:00.000";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
			LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
			
			spec = spec.and(SellingSpecification.greaterThanOrEqualToExpiryDateStart(dateTime));
		}
		
		if(expiryDateEnd != null && expiryDateEnd != "") {
			
			String str = expiryDateEnd + " 23:59:59.999";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
			LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
			
			spec = spec.and(SellingSpecification.lessThanOrEqualToExpiryDateEnd(dateTime));
		}
		
		AdminSellingResponseDTO resultDto = adminSellingService.getSellingInfo(spec, pageable);
		
		return resultDto;
	}
	
	/*
	 * 보관판매결제정보 가져오기
	 */
	@GetMapping("/api/admin/inventory/getpayment/{targetSellingId}")
	public AdminSellingPaymentDTO getPayment(@PathVariable("targetSellingId") Long targetSellingId) {
		
		AdminSellingPaymentDTO resultDto = adminSellingService.getPayment(targetSellingId);
		
		return resultDto;
	}
	
}
