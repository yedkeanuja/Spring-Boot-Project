package com.psms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.psms.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	@Query("from Customer b where b.customerName like %:a%")
	List<Customer> findByCustomerName(@Param("a") String customerName);	
	List<Customer> findByEmailId(String emailId);

}
