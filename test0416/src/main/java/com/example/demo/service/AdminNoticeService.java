package com.example.demo.service;


import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Notice;
import com.example.demo.repository.NoticeRepository;

@Service
public class AdminNoticeService {

	@Autowired
	private NoticeRepository noticeRepository;
	
	/*
	 * 공지사항등록
	 */
	public void register(String title, String content) {
		
		Notice newNotice = new Notice();
		newNotice.setTitle(title);
		newNotice.setContent(content);
		newNotice.setDataStatus(1);
		newNotice.setRegistDate(LocalDateTime.now());
		
		noticeRepository.save(newNotice);
	}

	public Page<Notice> getNoticeALL(Pageable pageable) {
		return noticeRepository.findAll(pageable);
	}

	

	
}
