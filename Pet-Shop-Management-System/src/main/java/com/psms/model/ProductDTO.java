package com.psms.model;


import com.psms.entity.Category;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductDTO {

	
	private long productId;
	@NotNull
	private String productName;
	private long price;
	private long quantity;
	
	private CustomerDTO Customer;
	private Category category;
}
