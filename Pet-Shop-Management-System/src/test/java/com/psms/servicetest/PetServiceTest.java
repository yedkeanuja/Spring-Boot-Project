package com.psms.servicetest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.psms.entity.Pet;
import com.psms.model.PetDTO;
import com.psms.repository.PetRepository;
import com.psms.service.PetService;
import com.psms.util.PetConverter;

@SpringBootTest
public class PetServiceTest {

	@Autowired
	private PetService petService;
	
	@Autowired
	private PetConverter petConverter;
	
	@MockBean
	private PetRepository petRepository;
	
	@Test
	@Order(1)
	void testCreatePet()
	{
		Pet pet = Pet.builder().petName("bruno").petAge(5).petWeight(10).petBreed("Golden Retriever").petGender("female").build();
		Mockito.when(petRepository.save(pet)).thenReturn(pet);
		assertEquals("pet details added successfully",petService.CreatePet(pet));
		
		assertThat(petService.CreatePet(pet)).isEqualTo("pet details added successfully");
							
	}
	
	@Test
	@Order(2)
	void testgetAllPets()
	{
		Pet pet=Pet.builder().petName("bruno").petAge(5).petWeight(10).petBreed("Golden Retriever").petGender("female").build();
		
		Pet pet1=Pet.builder().petName("Bella").petAge(5).petWeight(10).petBreed("Beagle").petGender("female").build();
		List<Pet> list=new ArrayList<Pet>();
		list.add(pet1);
		list.add(pet);
		
		Mockito.when(petRepository.findAll()).thenReturn(list);
				
		List<PetDTO> dto = petService.getAllPets();
		List<Pet> pets = new ArrayList<>();
		dto.forEach(petDto->
		pets.add(petConverter.convertDTOToPet(petDto)));
		
		assertThat(pets.get(0).getPetName()).isEqualTo(list.get(0).getPetName());
	}
	
	@Test
	@Order(3)
	void testupdatePet()
	{
		Pet pet=Pet.builder().petName("Max").petAge(1).petBreed("Beagle").petGender("male").petWeight(6).build();
		Optional<Pet> opPet = Optional.of(pet);
		Mockito.when(petRepository.findById(pet.getPetId())).thenReturn(opPet);
		
		Pet p=opPet.get();
		pet.setPetName("Neo");
		Mockito.when(petRepository.save(pet)).thenReturn(p);
		
		PetDTO dto=petService.updatePet(pet.getPetId(), pet);
		
		assertEquals(dto.getPetName(),p.getPetName());
				
	}
}
