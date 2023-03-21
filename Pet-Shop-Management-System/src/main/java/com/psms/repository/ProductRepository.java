package com.psms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.psms.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("from Product b where b.productName like %:a%")
	List<Product> findByProductName(@Param("a") String productName);
	
}
