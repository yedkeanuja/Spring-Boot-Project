package com.psms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psms.entity.Pet;
import com.psms.model.PetDTO;
import com.psms.service.PetService;
import com.psms.util.PetConverter;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pet")
public class PetController {

	@Autowired
	private PetService petservice;
	
	@Autowired
	private PetConverter petconverter;
	
	@PostMapping("/createPet")
	public String createPet(@Valid @RequestBody PetDTO petDTO)
	{
		final Pet pet=petconverter.convertDTOToPet(petDTO);
		return petservice.CreatePet(pet);
		
	}
	
	@GetMapping("/getPetById/{petId}")
	public PetDTO getPetById(@PathVariable("petId") int petId)
	{
		return petservice.getPetById(petId);
	}
	public List<PetDTO> getAllPets()
	{
		return petservice.getAllPets();
		
	}
	
	@PutMapping("/updatePet/{petId}")
	public PetDTO updatePet(@PathVariable("petId") int petId,
			@Valid @RequestBody PetDTO petDTO)
	{
		Pet pet1=petconverter.convertDTOToPet(petDTO);
		return petservice.updatePet(petId, pet1);
		
	}
	
	@DeleteMapping("/deletePetById/{petId}")
	public String deletePetById(@PathVariable("petId") int petId)
	{
		return petservice.deletePetById(petId) ;
		
	}
	
	@DeleteMapping("deleteAllPets")
	public ResponseEntity<String> deleteAllPets()
	{
		petservice.deleteAllPets();
		return new ResponseEntity<String>("All pet details got deleted!",
				HttpStatus.OK);
	}
	
	@GetMapping("getPetByName/{petName}")
	public List<PetDTO> getPetByName(@PathVariable("petName") String petName)
	{
		return petservice.getPetByName(petName) ;
		
	}

}
