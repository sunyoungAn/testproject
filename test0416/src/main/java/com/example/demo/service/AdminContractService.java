package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.model.Contract;
import com.example.demo.model.Member;
import com.example.demo.model.Payment;
import com.example.demo.model.DTO.AdminContractInfoDTO;
import com.example.demo.model.DTO.AdminContractPaymentDTO;
import com.example.demo.model.DTO.AdminContractResponseDTO;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.PaymentRepository;

@Service
public class AdminContractService {

	@Autowired
	private ContractRepository contractRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;

	/*
	 * 거래정보가져오기
	 */
	public AdminContractResponseDTO getContractInfo(Specification<Contract> spec, Pageable pageable) {

		Page<Contract> targetList = contractRepository.findAll(spec, pageable);
		
		AdminContractResponseDTO resultDto = new AdminContractResponseDTO();
		
		List<AdminContractInfoDTO> dtoList = new ArrayList<>();
		// 리스트 갯수반큼 반복하면서 화면에 필요한 정보만 DTO에 설정
		for(Contract target : targetList) {
			// TODO db에 너무 많이 왔다갔다 하는건 좋지 않으므로 쿼리를 직접 작성해 개선하거나 SellerNumber랑 BuyerNumber를 FK 지정하는 것도 좋을 듯
			// 구매자정보 취득
			Member buyerData = memberRepository.findById(target.getBuyerNumber()).get();
			
			AdminContractInfoDTO dto = new AdminContractInfoDTO();
			dto.setId(target.getId());
			dto.setProductId(target.getProduct().getId());
			dto.setProductEngName(target.getProduct().getProductEngName());
			dto.setProductKorName(target.getProduct().getProductKorName());
			
			if (target.getBuying() != null) {
				// 구매입찰 정보가 있는 경우
				
				dto.setTargetBuyingId(target.getBuying().getId());
				// 거래구분 - 입찰거래(2) 설정
				dto.setContractDiv(2);
				
			} else if(target.getSelling() != null) {
				// 판매입찰 정보가 있는 경우
				
				dto.setTargetSellingId(target.getSelling().getId());
				
				
				if(target.getSelling().getInventoryDiv() == 1) {
					// 보관판매인 경우
					dto.setContractDiv(1); // 거래구분 - 보관판매(1) 설정
				} else {
					dto.setContractDiv(2); // 거래구분 - 입찰거래(2) 설정
		
				}
			} else {
				// 판매입찰, 구매입찰 정보가 없는 경우 - 브랜드 배송
				dto.setContractDiv(3);	// 거래구분 - 브랜드배송(3) 설정		
			}
			
			dto.setPrice(target.getPrice());
			dto.setProductSize(target.getProductSize());
			dto.setBuyerNumber(target.getBuyerNumber());
			dto.setBuyerName(buyerData.getName());
			dto.setBuyerEmail(buyerData.getEmail());
			if(target.getSellerNumber() != null) {
				// 판매자정보 취득
				Member sellerData = memberRepository.findById(target.getSellerNumber()).get();
				dto.setSellerNumber(target.getSellerNumber());
				dto.setSellerName(sellerData.getName());
				dto.setSellerEmail(sellerData.getEmail());
			};
			dto.setContractDate(target.getContractDate());
			dto.setSellingStatus(target.getSellingStatus());
			dto.setBuyingStatus(target.getBuyingStatus());
			dto.setPaidPrice(target.getPaidPrice());
			dto.setPaidDate(target.getPaidDate());
			
			// DTO를 DTO리스트에 추가
			dtoList.add(dto);
		}
		
		resultDto.setContractList(dtoList);
		resultDto.setTotal(targetList.getTotalElements());
		
		return resultDto;
	}

	/*
	 * 결제정보 가져오기
	 */
	public AdminContractPaymentDTO getPayment(Long targetContractId) {
		
		Payment targetPayment = paymentRepository.findByContract_Id(targetContractId);
		
		AdminContractPaymentDTO dto = new AdminContractPaymentDTO();
		dto.setPaymentType(targetPayment.getPaymentType());
		dto.setPrice(targetPayment.getPrice());
		dto.setRegistDate(targetPayment.getRegistDate());
		dto.setName(targetPayment.getName());
		dto.setAddress(targetPayment.getAddress());
		dto.setSubAddress(targetPayment.getSubAddress());
		dto.setZipCode(targetPayment.getZipCode());
		dto.setPhoneNumber(targetPayment.getPhoneNumber());
		dto.setMessage(targetPayment.getMessage());
		
		return dto;
	}
	
}
