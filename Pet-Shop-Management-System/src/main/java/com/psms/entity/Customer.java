package com.psms.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table
@ToString

public class Customer {
	
	//it has fields: customer_id, customerName, address, contactNo and emailId.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customer_id;
	
	@Column(length= 50)
	@NotNull(message= "Customer Name should not be null")
	private String customerName;
	
	@Column(length= 50)
	@NotNull(message= "Customer Address should not be null")
	private String address;
	
	@Column(length= 30)
	@Pattern(regexp="^\\d{10}$", message="Phone number should have 10 digits")
	private String contactNo;
	
	@Column(length= 30)
	@NotNull(message= "Customer EmailId should not be null")
	private String emailId;
	
	
	//it has many-to-one relationship between the customer and Shopkeeper.
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Shopkeeper shopkeeper;
	
	
	//it has one-to-many relationship between the customer and Product.
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn
	private List<Product> product;
	
	
	//it has one-to-many relationship between the customer and Pet.
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn
	private List<Pet> pet;

	@Builder
	public Customer(int customer_id, String customerName, String address, String contactNo, String emailId) {
		super();
		this.customer_id = customer_id;
		this.customerName = customerName;
		this.address = address;
		this.contactNo = contactNo;
		this.emailId = emailId;
	}
		
}
