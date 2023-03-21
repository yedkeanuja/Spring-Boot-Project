package com.psms.model;


import java.util.List;

import com.psms.entity.Pet;
import com.psms.entity.Product;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {
	
	private int customer_id;
	
	@NotNull(message= "Customer Name should not be null")
	private String customerName;
	
	private String address;
	
	@Pattern(regexp="^\\d{10}$", message="Phone number should have 10 digits")	
	private String contactNo;
	
	@NotNull
	@Email (message="please enter valid email id ")
	private String emailId;
	
	private ShopkeeperDTO shopkeeper;
	private List<Product> product;
	private List<Pet> pet;
	

}
