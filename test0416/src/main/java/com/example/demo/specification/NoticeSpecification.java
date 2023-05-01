package com.example.demo.specification;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.model.Notice;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class NoticeSpecification {
	
	public static Specification<Notice> likeTitle(String title) {
		return new Specification<Notice>() {
			@Override
			public Predicate toPredicate(Root<Notice> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.like(root.get("title"), "%" + title + "%");
			}
		};
	}
	
	public static Specification<Notice> likeContent(String content) {
		return new Specification<Notice>() {
			@Override
			public Predicate toPredicate(Root<Notice> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.like(root.get("content"), "%" + content + "%");
			}
		};
	}
	
	public static Specification<Notice> equalDataStatus(Integer status) {
		return new Specification<Notice>() {
			@Override
			public Predicate toPredicate(Root<Notice> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.get("dataStatus"), status);
			}
		};
	}

}
