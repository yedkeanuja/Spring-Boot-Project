package com.psms.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psms.entity.Pet;
import com.psms.exception.ResourceNotFoundException;
import com.psms.model.PetDTO;
import com.psms.repository.PetRepository;
import com.psms.service.PetService;
import com.psms.util.PetConverter;

@Service
public class PetServiceImpl implements PetService{
	
	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private PetConverter petconverter;

	@Override
	public String CreatePet(Pet pet) {
		String message=null;
		
		pet.setPetName(pet.getPetName());
		pet.setPetAge(pet.getPetAge());
		pet.setPetWeight(pet.getPetWeight());
		pet.setPetGender(pet.getPetGender());
		pet.setPetBreed(pet.getPetBreed());
		
		petRepository.save(pet);
		
		if(pet!=null)
		{
			message="pet details added successfully";
		}

		return message;
	}

	@Override
	public PetDTO getPetById(int petId) {
		
		Pet pet=petRepository.findById(petId).get();
		
		return petconverter.convertEntityToPetDTO(pet);
	}

	@Override
	public List<PetDTO> getAllPets() {
		
		List<Pet> pets=petRepository.findAll();
		List<PetDTO> petDTO=new ArrayList<>();
		for(Pet p:pets)
		{
			petDTO.add(petconverter.convertEntityToPetDTO(p));
		}
		
		return petDTO;
	}

	@Override
	public PetDTO updatePet(int petId, Pet pet) {
		
		Pet existingPet=petRepository.findById(petId).get();
		
		existingPet.setPetName(pet.getPetName());
		existingPet.setPetAge(pet.getPetAge());
		existingPet.setPetWeight(pet.getPetWeight());
		existingPet.setPetGender(pet.getPetGender());
		existingPet.setPetBreed(pet.getPetBreed());
		
		petRepository.save(existingPet);
		
		return petconverter.convertEntityToPetDTO(existingPet);
	}
	
	@Override
	public String deletePetById(int petId) throws ResourceNotFoundException {
			String msg=null;
		
		Optional<Pet> pets=petRepository.findById(petId);
		if(pets.isPresent())
		{
			petRepository.deleteById(petId);;
		}
		return msg;
	}

	@Override
	public void deleteAllPets() {
		
		petRepository.deleteAll();
		
	}

	@Override
	public List<PetDTO> getPetByName(String petName) {
		List<Pet> pet=petRepository.findByPetName(petName);
		List<PetDTO> petDTO=new ArrayList<>();
		for(Pet p:pet)
		{
			petDTO.add(petconverter.convertEntityToPetDTO(p));
		}
		
		return petDTO;
		}
}
