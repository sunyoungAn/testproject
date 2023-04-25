package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Brand;
import com.example.demo.model.Product;
import com.example.demo.repository.BrandRepository;
import com.example.demo.repository.ProductRepository;

@Service
public class TestService {

	@Autowired
	private BrandRepository br;
	
	@Autowired
	private ProductRepository pr;

	public List<Product> findAll() {
		return pr.findAll();
	}

	public List<Brand> findBrand() {
		return br.findAll();
	}
	
	
	
}
