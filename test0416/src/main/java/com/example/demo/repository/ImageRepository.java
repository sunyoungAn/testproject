package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

	List<Image> findByTargetId(Long id);

	List<Image> findByTargetIdAndPageDiv(Long id, Integer i);

}
