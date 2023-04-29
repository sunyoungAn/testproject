package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Notice;
import com.example.demo.model.DTO.AdminNoticeDTO;
import com.example.demo.service.AdminNoticeService;

@RestController
public class AdminNoticeController {

	@Autowired
	private AdminNoticeService adminNoticeService;
	
	/*
	 * 공지사항등록
	 */
	@PostMapping("/api/admin/notice/register")
	public void registerNotice(@RequestBody AdminNoticeDTO dto) {
		
		adminNoticeService.register(dto.getTitle(), dto.getContent());
		
	}
	
	
	/*
	 * 공지사항리스트 - 공지사항 리스트 전체 가져오기
	 */
	@GetMapping("/api/admin/noitce/getall")
	public Page<Notice> getNoticeALL(Pageable pageable) {
		
		Page<Notice> noticeList = adminNoticeService.getNoticeALL(pageable);
		
		return noticeList;
	}
	
	
	
	
	
}
