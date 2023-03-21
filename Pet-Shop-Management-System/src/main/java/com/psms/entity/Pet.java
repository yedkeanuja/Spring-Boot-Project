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
public class Pet {

	
	//Pet class has fields: petId, petName, petAge, petGender, petWeight and petBreed. 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int petId;
	
	@Column(length= 50)
	private String petName;
	
	@Column(length= 50)
	private int petAge;
	
	@Column(length= 50)
	private String petGender;
	
	@Column(length= 50)
	private double petWeight;
	
	@Column(length=20)
	private String petBreed;
	
	
	//it has many-to-one relationship between the pet and customer.
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Customer customer;

	@Builder
	public Pet(int petId, String petName, int petAge, String petGender, double petWeight, String petBreed) {
		super();
		this.petId = petId;
		this.petName = petName;
		this.petAge = petAge;
		this.petGender = petGender;
		this.petWeight = petWeight;
		this.petBreed = petBreed;
	}
	
	
}
