package com.psms.service;

import java.util.List;

import com.psms.entity.Pet;
import com.psms.model.PetDTO;

public interface PetService {

	String CreatePet(Pet pet);  //creating pet
	PetDTO getPetById(int petId);		//fetch data of a pet using id 
	List<PetDTO> getAllPets();			//fetch all pets
	PetDTO updatePet(int petId,Pet pet);		//fetch and update the customer details
	
	String deletePetById(int petId);	//deleting pet by id
	
	void deleteAllPets();		//delete all the pets
	
	List<PetDTO> getPetByName(String petName);  //getting the pet by name
}
