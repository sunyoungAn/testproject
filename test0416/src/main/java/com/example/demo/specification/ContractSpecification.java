package com.example.demo.specification;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.model.Buying;
import com.example.demo.model.Contract;
import com.example.demo.model.Selling;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ContractSpecification {

	public static Specification<Contract> equalId(Long id) {
		return new Specification<Contract>() {
			@Override
			public Predicate toPredicate(Root<Contract> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.get("id"), id);
			}
		};
	}
	
	public static Specification<Contract> equalProductId(Long productId) {
		return new Specification<Contract>() {
			@Override
			public Predicate toPredicate(Root<Contract> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.get("product").get("id"), productId);
			}
		};
	}
	
	public static Specification<Contract> equalBuyerNumber(Long buyerNumber) {
		return new Specification<Contract>() {
			@Override
			public Predicate toPredicate(Root<Contract> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.get("buyerNumber"), buyerNumber);
			}
		};
	}
	
	public static Specification<Contract> equalSellerNumber(Long sellerNumber) {
		return new Specification<Contract>() {
			@Override
			public Predicate toPredicate(Root<Contract> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.get("sellerNumber"), sellerNumber);
			}
		};
	}
	
	public static Specification<Contract> equalSellingStatus(Integer sellingStatus) {
		return new Specification<Contract>() {
			@Override
			public Predicate toPredicate(Root<Contract> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.get("sellingStatus"), sellingStatus);
			}
		};
	}
	
	public static Specification<Contract> equalBuyingStatus(Integer buyingStatus) {
		return new Specification<Contract>() {
			@Override
			public Predicate toPredicate(Root<Contract> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.get("buyingStatus"), buyingStatus);
			}
		};
	}
	
	public static Specification<Contract> greaterThanOrEqualToContractDateStart(LocalDateTime contractDateStart) {
		return new Specification<Contract>() {
			@Override
			public Predicate toPredicate(Root<Contract> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.greaterThanOrEqualTo(root.get("contractDate"), contractDateStart);
			}
		};
	}
	
	public static Specification<Contract> lessThanOrEqualToContractDateEnd(LocalDateTime contractDateEnd) {
		return new Specification<Contract>() {
			@Override
			public Predicate toPredicate(Root<Contract> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.lessThanOrEqualTo(root.get("contractDate"), contractDateEnd);
			}
		};
	}
	
	
	
}
