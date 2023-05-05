package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Product;
import com.example.demo.model.DTO.TestQueryDTO;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

	@Query(value="SELECT "
			 + "new com.example.demo.model.DTO.TestQueryDTO(prod.id, prod.productEngName, prod.dataStatus, prod.launchingDate, img.imagePath, "
	         + "img.mainImageDiv, bd.brandName, sel.wishPrice, sel.inventoryDiv) "
	         + "FROM Product prod "
	         + "INNER JOIN Image img ON prod.id = img.targetId "
	         + "INNER JOIN Brand bd ON prod.brand = bd.brandId "
	         + "INNER JOIN Selling sel ON prod.id = sel.product "
	         + "WHERE img.pageDiv = 1 AND img.mainImageDiv = 1 "
	         + "ORDER BY prod.launchingDate desc"
	         )
	List<TestQueryDTO> findData();
	
}
