package com.psms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PetShopManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetShopManagementSystemApplication.class, args);
		System.out.println("Pet Shop Management Running Successfully");
	}

}
