package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.model.Address;
import com.example.demo.model.Card;
import com.example.demo.model.Member;
import com.example.demo.model.DTO.AdminAddressDTO;
import com.example.demo.model.DTO.AdminCardDTO;
import com.example.demo.model.DTO.AdminMemberInfoDTO;
import com.example.demo.repository.MemberRepository;

@Service
public class AdminMemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	/*
	 * 멤버리스트 - 멤버 리스트 전체 가져오기
	 */
	public Page<Member> getMemberAll(Pageable pageable) {
		return memberRepository.findAll(pageable);
	}

	/*
	 * 멤버 검색
	 */
	public Page<Member> searchMember(Specification<Member> spec, Pageable pageable) {
		return memberRepository.findAll(spec, pageable);
	}

	/*
	 * 회원 상세내용 검색
	 */
	public AdminMemberInfoDTO findById(Long memberNumber) {
		
		Member targetMember = memberRepository.findById(memberNumber).get();
		
		AdminMemberInfoDTO resultDto = new AdminMemberInfoDTO();
		resultDto.setMemberNumber(targetMember.getMemberNumber());
		resultDto.setName(targetMember.getName());
		resultDto.setEmail(targetMember.getEmail());
		resultDto.setPhoneNumber(targetMember.getPhoneNumber());
		resultDto.setGender(targetMember.getGender());
		resultDto.setBirthDate(targetMember.getBirthDate());
		resultDto.setMemberStatus(targetMember.getMemberStatus());
		resultDto.setRegistDate(targetMember.getRegistDate());
		resultDto.setBankName(targetMember.getBankName());
		resultDto.setAccountNumber(targetMember.getAccountNumber());
		resultDto.setDepositor(targetMember.getDepositor());
		
		// 주소정보
		List<AdminAddressDTO> addressDtoList = new ArrayList<>();
		List<Address> targetAddressList = targetMember.getAddresss();
		
		for(Address address : targetAddressList) {
			AdminAddressDTO dto = new AdminAddressDTO();
			dto.setId(address.getId());
			dto.setName(address.getName());
			dto.setAddress(address.getAddress());
			dto.setSubAddress(address.getSubAddress());
			dto.setZipCode(address.getZipCode());
			dto.setPhoneNumber(address.getPhoneNumber());
			dto.setDefaultAddress(address.getDefaultAddress());
			addressDtoList.add(dto);
		}
		resultDto.setAddressDtoList(addressDtoList);
		
		// 카드정보
		List<AdminCardDTO> cardDtoList = new ArrayList<>();
		List<Card> targetCardList = targetMember.getCards();
		for(Card card : targetCardList) {
			if(card.getDataStatus() == 2) {
				continue;
			}
			AdminCardDTO dto = new AdminCardDTO();
			dto.setId(card.getId());
			dto.setCardNumber(card.getCardNumber());
			dto.setExpiryYear(card.getExpiryYear());
			dto.setExpiryMonth(card.getExpiryMonth());
			dto.setName(card.getName());
			cardDtoList.add(dto);
		}
		resultDto.setCardDtoList(cardDtoList);
		
		return resultDto;
	}

}
