package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.model.Selling;

public interface SellingRepository extends JpaRepository<Selling, Long>, JpaSpecificationExecutor<Selling>{

}
