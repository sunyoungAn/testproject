package com.example.demo.service;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.model.Image;
import com.example.demo.model.Inquiry;
import com.example.demo.model.DTO.AdminInquiryInfoDTO;
import com.example.demo.model.DTO.AdminInquiryResponseDTO;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.InquiryRepository;

import jakarta.transaction.Transactional;

@Service
public class AdminInquiryService {

	@Value("${test0416.upload.path}")
	private String uploadPath;
	
	@Autowired
	private InquiryRepository inquiryRepository;
	
	@Autowired
	private ImageRepository imageRepository;

	
	/*
	 * 문의정보가져오기
	 */
	public AdminInquiryResponseDTO getInquiryInfo(Specification<Inquiry> spec, Pageable pageable) {

		Page<Inquiry> targetList = inquiryRepository.findAll(spec, pageable);
		
		AdminInquiryResponseDTO resultDto = new AdminInquiryResponseDTO();
		
		List<AdminInquiryInfoDTO> dtoList = new ArrayList<>();
		for(Inquiry target : targetList) {
			AdminInquiryInfoDTO dto = new AdminInquiryInfoDTO();
			dto.setId(target.getId());
			dto.setMemberNumber(target.getMember().getMemberNumber());
			dto.setName(target.getMember().getName());
			dto.setEmail(target.getMember().getEmail());
			if(target.getCategory() == 1) {
				dto.setCategory("구매관련");
			} else if (target.getCategory() == 2) {
				dto.setCategory("판매관련");
			} else {
				dto.setCategory("기타");
			}
			dto.setTitle(target.getTitle());
			dto.setInquiryRegistDate(target.getInquiryRegistDate());
			dto.setInquiryStatus(target.getInquiryStatus());
			if(target.getInquiryStatus() == 1) {
				dto.setInquiryStatusName("처리중");
			} else {
				dto.setInquiryStatusName("처리완료");
			}
			dto.setAnswerRegistDate(target.getAnswerRegistDate());
			dtoList.add(dto);
		}
		
		resultDto.setInquiryList(dtoList);
		resultDto.setTotal(targetList.getTotalElements());
		
		return resultDto;
	}


	/*
	 * 문의 삭제
	 */
	@Transactional
	public void delete(List<Long> ids) {
		
		for(Long id : ids) {
			// 문의 삭제
			inquiryRepository.deleteById(id);
			
			// 이미지 조회
			List<Image> targetImageList = imageRepository.findByTargetIdAndPageDiv(id, 3);
						
			if(targetImageList.isEmpty() == false) {
				for(Image targetImage : targetImageList) {
					// 이미지 삭제
					imageRepository.delete(targetImage);
								
					// 로컬데이터도 삭제
					try {
									
						File file = new File(uploadPath + URLDecoder.decode(targetImage.getImagePath(), "UTF-8"));
						file.delete();
									
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
}
