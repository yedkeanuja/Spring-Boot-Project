package com.psms.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Product {

	//product class has fields: productId, productName, price and quantity.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productId;
	
	@Column(length= 50)
	private String productName;
	
	@Column(length= 50)
	private long price;
	
	@Column(length= 50)
	private long quantity;
	
	//it has many-to-one relationship between the product and customer.
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Customer customer;
	
	////it has many-to-one relationship between the product and category.
	@ManyToOne
	private Category category;

	@Builder
	public Product(long productId, String productName, long price, long quantity) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
	}
	
	
}
