package com.psms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.psms.entity.Pet;

public interface PetRepository extends JpaRepository<Pet, Integer>{
	
	List<Pet> findByPetName(@Param("a") String petName);

}