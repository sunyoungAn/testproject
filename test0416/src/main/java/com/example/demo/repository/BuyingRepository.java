package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.model.Buying;

public interface BuyingRepository extends JpaRepository<Buying, Long>,JpaSpecificationExecutor<Buying> {

}
