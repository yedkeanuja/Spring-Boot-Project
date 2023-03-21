package com.psms.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.psms.entity.Pet;
import com.psms.model.PetDTO;

@Component
public class PetConverter {

	//convert from Pet entity to petDTO
		public PetDTO convertEntityToPetDTO(Pet pet)
		{
			PetDTO petDTO=new PetDTO();
			if(pet!=null)
			{
				BeanUtils.copyProperties(pet, petDTO);
			}
			return petDTO;
			
		}
		
		//convert from PetDTO to pet entity Pet
		public Pet convertDTOToPet(PetDTO petDTO)
		{
			Pet pet=new Pet();
			if(petDTO!=null)
			{
				BeanUtils.copyProperties(petDTO, pet);
			}
			return pet;
			
		}

}
