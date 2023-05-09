package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.model.Selling;
import com.example.demo.model.DTO.AdminSellingInfoDTO;
import com.example.demo.model.DTO.AdminSellingResponseDTO;
import com.example.demo.model.DTO.AdminSellingStatusEditDTO;
import com.example.demo.repository.SellingRepository;

import jakarta.transaction.Transactional;

@Service
public class AdminSellingService {

	@Autowired
	private SellingRepository sellingRepository;

	/*
	 * 판매입찰리스트 - 판매입찰관리페이지에 띄울 정보 모두 가져오기
	 */
//	public AdminSellingResponseDTO getSellingAll(Specification<Selling> spec, Pageable pageable) {
//		Page<Selling> targetList = sellingRepository.findAll(spec, pageable);
//		
//		AdminSellingResponseDTO resultDto = new AdminSellingResponseDTO();
//		
//		List<AdminSellingInfoDTO> dtoList = new ArrayList<>();
//		for(Selling target : targetList) {
//			AdminSellingInfoDTO dto = new AdminSellingInfoDTO();
//			dto.setId(target.getId());
//			dto.setProductId(target.getProduct().getId());
//			dto.setProductEngName(target.getProduct().getProductEngName());
//			dto.setProductKorName(target.getProduct().getProductKorName());
//			dto.setProductSize(target.getProductSize());
//			dto.setWishPrice(target.getWishPrice());
//			dto.setMemberNumber(target.getMember().getMemberNumber());
//			dto.setName(target.getMember().getName());
//			dto.setEmail(target.getMember().getEmail());
//			dto.setSellingStatus(target.getSellingStatus());
//			dto.setExpiryDate(target.getExpiryDate());
//			dtoList.add(dto);
//		}
//		
//		resultDto.setSellingList(dtoList);
//		resultDto.setTotal(targetList.getTotalElements());
//		
//		return resultDto;
//	}

	/*
	 * 판매입찰정보가져오기
	 */
	public AdminSellingResponseDTO getSellingInfo(Specification<Selling> spec, Pageable pageable) {
		Page<Selling> targetList = sellingRepository.findAll(spec, pageable);
		
		AdminSellingResponseDTO resultDto = new AdminSellingResponseDTO();
		
		List<AdminSellingInfoDTO> dtoList = new ArrayList<>();
		for(Selling target : targetList) {
			AdminSellingInfoDTO dto = new AdminSellingInfoDTO();
			dto.setId(target.getId());
			dto.setProductId(target.getProduct().getId());
			dto.setProductEngName(target.getProduct().getProductEngName());
			dto.setProductKorName(target.getProduct().getProductKorName());
			dto.setProductSize(target.getProductSize());
			dto.setWishPrice(target.getWishPrice());
			dto.setMemberNumber(target.getMember().getMemberNumber());
			dto.setName(target.getMember().getName());
			dto.setEmail(target.getMember().getEmail());
			dto.setSellingStatus(target.getSellingStatus());
			dto.setExpiryDate(target.getExpiryDate());
			dtoList.add(dto);
		}
		
		resultDto.setSellingList(dtoList);
		resultDto.setTotal(targetList.getTotalElements());
		
		return resultDto;
	}

	/*
	 * 판매입찰상태 변경
	 */
	@Transactional
	public void editSellingStatus(List<AdminSellingStatusEditDTO> dtoList) {
		
		for(AdminSellingStatusEditDTO dto : dtoList) {
			// 변경대상을 조회
			Selling targetSelling = sellingRepository.findById(dto.getId()).get();
			
			// 변경할 상태와 변경일을 설정
			targetSelling.setSellingStatus(dto.getSellingStatus());
			targetSelling.setModifiedDate(LocalDateTime.now());
			
			sellingRepository.save(targetSelling);
		}
		
	}
	
	/*
	 * 판매입찰 삭제
	 */
	@Transactional
	public void delete(List<Long> ids) {
		
		for(Long id : ids) {
			Selling targetSelling = sellingRepository.findById(id).get();
			targetSelling.setDataStatus(2);
			targetSelling.setModifiedDate(LocalDateTime.now());
			
			sellingRepository.save(targetSelling);
		}
	}
	
}
