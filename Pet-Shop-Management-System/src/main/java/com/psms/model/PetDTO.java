package com.psms.model;

import com.psms.entity.Customer;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetDTO {

private int petId;
	
	@NotNull
	private String petName;
	
	@NotNull
	private int petAge;
	
	@NotNull
	private String petGender;
	
	@NotNull
	private double petWeight;
	
	@NotNull
	private String petBreed;
	
	private Customer customer;
}
