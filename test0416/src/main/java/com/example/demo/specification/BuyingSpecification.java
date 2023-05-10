package com.example.demo.specification;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.model.Buying;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class BuyingSpecification {
	
	public static Specification<Buying> equalDataStatus(Integer status) {
		return new Specification<Buying>() {
			@Override
			public Predicate toPredicate(Root<Buying> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.get("dataStatus"), status);
			}
		};
	}
	
	public static Specification<Buying> equalId(Long id) {
		return new Specification<Buying>() {
			@Override
			public Predicate toPredicate(Root<Buying> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.get("id"), id);
			}
		};
	}
	
	public static Specification<Buying> equalProductId(Long productId) {
		return new Specification<Buying>() {
			@Override
			public Predicate toPredicate(Root<Buying> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.get("product").get("id"), productId);
			}
		};
	}
	
	public static Specification<Buying> equalMemberNumber(Long memberNumber) {
		return new Specification<Buying>() {
			@Override
			public Predicate toPredicate(Root<Buying> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.get("member").get("memberNumber"), memberNumber);
			}
		};
	}
	
	public static Specification<Buying> equalBuyingStatus(Integer buyingStatus) {
		return new Specification<Buying>() {
			@Override
			public Predicate toPredicate(Root<Buying> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.get("buyingStatus"), buyingStatus);
			}
		};
	}
	
	public static Specification<Buying> greaterThanOrEqualToExpiryDateStart(LocalDateTime expiryDateStart) {
		return new Specification<Buying>() {
			@Override
			public Predicate toPredicate(Root<Buying> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.greaterThanOrEqualTo(root.get("expiryDate"), expiryDateStart);
			}
		};
	}
	
	public static Specification<Buying> lessThanOrEqualToExpiryDateEnd(LocalDateTime expiryDateEnd) {
		return new Specification<Buying>() {
			@Override
			public Predicate toPredicate(Root<Buying> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.lessThanOrEqualTo(root.get("expiryDate"), expiryDateEnd);
			}
		};
	}

}
