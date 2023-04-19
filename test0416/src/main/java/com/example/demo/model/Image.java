package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "image")
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "TINYINT", nullable = false)
	private Integer page_div;
	
	@Column(nullable = false)
	private Long targetId;
	
	@Column(length = 200, nullable = false)
	private String imagePath;
	
	@Column(columnDefinition = "TINYINT", nullable = false)
	private Integer mainImageDiv;
	
	@Column(nullable = false)
	private LocalDateTime registDate;

}
