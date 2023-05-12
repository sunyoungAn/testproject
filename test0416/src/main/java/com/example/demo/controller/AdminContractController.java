package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Contract;
import com.example.demo.model.DTO.AdminContractPaymentDTO;
import com.example.demo.model.DTO.AdminContractResponseDTO;
import com.example.demo.service.AdminContractService;
import com.example.demo.specification.ContractSpecification;

@RestController
public class AdminContractController {
	
	@Autowired
	private AdminContractService adminContractService;
	
	/*
	 * 거래리스트 - 거래관리페이지에 띄울 정보 모두 가져오기
	 */
	@GetMapping("/api/admin/contract/getall")
	public AdminContractResponseDTO getContractAll(Pageable pageable) {
		
		Sort sort = Sort.by("id").descending(); 
		pageable = PageRequest.of(pageable.getPageNumber(), 10, sort);
		
		Specification<Contract> spec = (root, query, criteriaBuilder) -> null;
		
		AdminContractResponseDTO responseDto = adminContractService.getContractInfo(spec, pageable);
		
		return responseDto;
	}
	
	
	/*
	 * 거래검색
	 */
	@GetMapping("/api/admin/contract/search")
	public AdminContractResponseDTO searchContract(@RequestParam(value="id", required=false) Long id,
												@RequestParam(value="productId", required=false) Long productId,
												@RequestParam(value="sellerMemberNumber", required=false) Long sellerMemberNumber,
												@RequestParam(value="buyerMemberNumber", required=false) Long buyerMemberNumber,
												@RequestParam(value="sellingStatus", required=false) Integer sellingStatus,
												@RequestParam(value="buyingStatus", required=false) Integer buyingStatus,
												@RequestParam(value="contractDateStart", required=false) String contractDateStart,
												@RequestParam(value="contractDateEnd", required=false) String contractDateEnd, Pageable pageable) {
	
		Sort sort = Sort.by("id").descending(); 
		pageable = PageRequest.of(pageable.getPageNumber(), 10, sort);
		
		Specification<Contract> spec = (root, query, criteriaBuilder) -> null;
		
		if(id != null) {
			spec = spec.and(ContractSpecification.equalId(id));
		}
		
		if(productId != null) {
			spec = spec.and(ContractSpecification.equalProductId(productId));
		}
		
		if(sellerMemberNumber != null) {
			spec = spec.and(ContractSpecification.equalSellerNumber(sellerMemberNumber));
		}
		
		if(buyerMemberNumber != null) {
			spec = spec.and(ContractSpecification.equalBuyerNumber(buyerMemberNumber));
		}
		
		if(sellingStatus != 0) {
			spec = spec.and(ContractSpecification.equalSellingStatus(sellingStatus));
		}
		
		if(buyingStatus != 0) {
			spec = spec.and(ContractSpecification.equalBuyingStatus(buyingStatus));
		}
		
		if(contractDateStart != null && contractDateStart != "") {
			
			String str = contractDateStart + " 00:00:00.000";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
			LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
			
			spec = spec.and(ContractSpecification.greaterThanOrEqualToContractDateStart(dateTime));
		}
		
		if(contractDateEnd != null && contractDateEnd != "") {
			
			String str = contractDateEnd + " 23:59:59.999";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
			LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
			
			spec = spec.and(ContractSpecification.lessThanOrEqualToContractDateEnd(dateTime));
		}
		
		AdminContractResponseDTO responseDto = adminContractService.getContractInfo(spec, pageable);
		
		return responseDto;
	}
	
	
	/*
	 * 거래상태 변경 TODO
	 */
	
	
	/*
	 * 결제정보 가져오기
	 */
	@GetMapping("/api/admin/contract/getpayment/{targetContractId}")
	public AdminContractPaymentDTO getPayment(@PathVariable("targetContractId") Long targetContractId) {
		
		AdminContractPaymentDTO resultDto = adminContractService.getPayment(targetContractId);
		
		return resultDto;
	}
	
}
