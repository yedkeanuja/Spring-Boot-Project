package com.psms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.psms.entity.Shopkeeper;

public interface ShopKeeperRepository extends  JpaRepository<Shopkeeper, Integer> {
	
	@Query("from Shopkeeper b where b.shopkName like %:a%")
	List<Shopkeeper> findByShopkName(@Param("a") String shopkName);

}
