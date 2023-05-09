package com.example.demo.specification;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.model.Selling;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class SellingSpecification {
	
	public static Specification<Selling> equalDataStatus(Integer status) {
		return new Specification<Selling>() {
			@Override
			public Predicate toPredicate(Root<Selling> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.get("dataStatus"), status);
			}
		};
	}
	
	public static Specification<Selling> equalInventoryDiv(Integer inventoryDiv) {
		return new Specification<Selling>() {
			@Override
			public Predicate toPredicate(Root<Selling> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.get("inventoryDiv"), inventoryDiv);
			}
		};
	}
	
	public static Specification<Selling> equalId(Long id) {
		return new Specification<Selling>() {
			@Override
			public Predicate toPredicate(Root<Selling> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.get("id"), id);
			}
		};
	}
	
	public static Specification<Selling> equalProductId(Long productId) {
		return new Specification<Selling>() {
			@Override
			public Predicate toPredicate(Root<Selling> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.get("product").get("id"), productId);
			}
		};
	}
	
	public static Specification<Selling> equalMemberNumber(Long memberNumber) {
		return new Specification<Selling>() {
			@Override
			public Predicate toPredicate(Root<Selling> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.get("member").get("memberNumber"), memberNumber);
			}
		};
	}
	
	public static Specification<Selling> equalSellingStatus(Integer sellingStatus) {
		return new Specification<Selling>() {
			@Override
			public Predicate toPredicate(Root<Selling> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.get("sellingStatus"), sellingStatus);
			}
		};
	}
	
	public static Specification<Selling> greaterThanOrEqualToExpiryDateStart(LocalDateTime expiryDateStart) {
		return new Specification<Selling>() {
			@Override
			public Predicate toPredicate(Root<Selling> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.greaterThanOrEqualTo(root.get("expiryDate"), expiryDateStart);
			}
		};
	}
	
	public static Specification<Selling> lessThanOrEqualToExpiryDateEnd(LocalDateTime expiryDateEnd) {
		return new Specification<Selling>() {
			@Override
			public Predicate toPredicate(Root<Selling> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.lessThanOrEqualTo(root.get("expiryDate"), expiryDateEnd);
			}
		};
	}

}
