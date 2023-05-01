package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Notice;
import com.example.demo.model.DTO.AdminNoticeDTO;
import com.example.demo.model.DTO.AdminNoticeSearchConditionDTO;
import com.example.demo.service.AdminNoticeService;
import com.example.demo.specification.NoticeSpecification;

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
	@GetMapping("/api/admin/notice/getall")
	public Page<Notice> getNoticeALL(Pageable pageable) {
		
		Sort sort = Sort.by("id").descending(); 
		pageable = PageRequest.of(pageable.getPageNumber(), 10, sort);
		
//		Page<Notice> noticeList = adminNoticeService.getNoticeALL(pageable);
		
		Specification<Notice> spec = (root, query, criteriaBuilder) -> null;
		
		spec = spec.and(NoticeSpecification.equalDataStatus(1));
		
		Page<Notice> resultList = adminNoticeService.searchNotice(spec, pageable);
		
		return resultList;
	}
	
	/*
	 * 공지사항 검색
	 */
	@PostMapping("/api/admin/notice/search")
	public Page<Notice> searchNotice(Pageable pageable, @RequestBody AdminNoticeSearchConditionDTO dto) {
		
		Sort sort = Sort.by("id").descending(); 
		pageable = PageRequest.of(pageable.getPageNumber(), 10, sort);
		
		Specification<Notice> spec = (root, query, criteriaBuilder) -> null;
		
		if(dto.getSelectNumber() == 1 || dto.getSelectNumber() == 3) {
			spec = spec.or(NoticeSpecification.likeTitle(dto.getSearchCondition()));
		}
		
		if(dto.getSelectNumber() == 2 || dto.getSelectNumber() == 3) {
			spec = spec.or(NoticeSpecification.likeContent(dto.getSearchCondition()));
		}
		
		spec = spec.and(NoticeSpecification.equalDataStatus(1));
		
		Page<Notice> resultList = adminNoticeService.searchNotice(spec, pageable);
		
		return resultList;
	}
	
	/*
	 * 공지사항 상세내용 검색
	 */
	@GetMapping("/api/admin/notice/getone/{id}")
	public AdminNoticeDTO getNoticeOne(@PathVariable("id") Long id) {
		
		AdminNoticeDTO resultDto = adminNoticeService.findById(id);
		return resultDto;
	}
	
	/*
	 * 공지사항 수정
	 */
	@PutMapping("/api/admin/notice/edit/{id}")
	public void editNotice(@PathVariable("id") Long id, @RequestBody AdminNoticeDTO dto) {
		
		adminNoticeService.edit(id, dto.getTitle(), dto.getContent());
	}
	
	/*
	 * 공지사항 삭제
	 */
	@DeleteMapping("/api/admin/notice/delete/{ids}")
	public void deleteNotice(@PathVariable List<Long> ids) {
		
		System.out.println("아이디" + ids.size());

		adminNoticeService.delete(ids);
	}
	
}
