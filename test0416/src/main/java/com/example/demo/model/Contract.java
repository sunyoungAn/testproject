package com.example.demo.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "contract")
public class Contract {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "target_buying_id")
	private Buying buying; 
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "target_selling_id")
	private Selling selling;
	
	@Column(nullable = false)
	private Long buyerNumber;
	
	@Column(nullable = false)
	private Long sellerNumber;
	
	@Column(nullable = false)
	private LocalDateTime contractDate;
	
	@Column(columnDefinition = "TINYINT")
	private Integer sellingStatus;
	
	@Column(columnDefinition = "TINYINT")
	private Integer buyingStatus;
	
	@Column(nullable = false)
	private Integer price;
	
	private LocalDateTime paidDate;
	
	private Integer paidPrice;
	
	@Column(nullable = false)
	private LocalDateTime registDate;
	
	private LocalDateTime modifiedDate;

	
}
