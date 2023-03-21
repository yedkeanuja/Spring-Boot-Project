package com.psms.model;

import com.psms.entity.Product;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {

	private long categoryId;
	
	@NotNull
	private String categoryName;
	
	private Product product;
}
