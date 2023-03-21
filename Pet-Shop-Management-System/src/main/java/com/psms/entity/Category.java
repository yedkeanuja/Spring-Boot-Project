package com.psms.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {

	//it has fields which is categoryId, CategoryName.
	@Id
	@GeneratedValue
	private long categoryId;
	
	private String categoryName;
	
	//it has one-to-many relationship between the Category and Product.
	@OneToMany
	private List<Product>product;

	@Builder
	public Category(long categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
		
}
