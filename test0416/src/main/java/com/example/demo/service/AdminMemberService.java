package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Member;
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

}
