package com.psms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.psms.entity.Category;

public interface CategoryRepository  extends JpaRepository <Category, Long> {

	@Query("from Category a where a.categoryName like %:c%")
	List<Category> findByName(@Param("c")String categoryName);

}
