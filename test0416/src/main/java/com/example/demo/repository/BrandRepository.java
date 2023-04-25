package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Brand;
import com.example.demo.model.DTO.TestQueryDTO;

public interface BrandRepository extends JpaRepository<Brand, Long>{

//	@Query(value="select " +
//			"new com.example.demo.model.DTO.TestQueryDTO(prod.productKorName, bd.brandId) " +
//			"FROM Product prod " +
//			"INNER JOIN Brand bd ON prod.brand = bd.brandId "
//			)
//	List<TestQueryDTO> findData();
	
	

}
