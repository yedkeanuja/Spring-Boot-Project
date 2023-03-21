package com.psms.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class Shopkeeper {
		
	//Shopkeeper class has fields: shopk_id and shopkName.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int shopk_id;
	
	@Column(length = 50, nullable=false)
	private String shopkName;
	
	//it has one-to-many relationship between the Shopkeeper and customer.
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn
	private List<Customer> customers;

	@Builder
	public Shopkeeper(int shopk_id, String shopkName) {
		super();
		this.shopk_id = shopk_id;
		this.shopkName = shopkName;
	}

	
}
