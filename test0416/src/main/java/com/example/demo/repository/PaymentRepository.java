package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

	Payment findBySelling_Id(Long targetSellingId);

	Payment findByContract_Id(Long targetContractId);

}
