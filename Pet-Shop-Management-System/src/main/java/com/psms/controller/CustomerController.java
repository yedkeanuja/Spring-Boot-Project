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

import com.psms.entity.Customer;
import com.psms.model.CustomerDTO;
import com.psms.service.CustomerService;
import com.psms.util.CustomerConverter;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerservice;
	
	@Autowired
	private CustomerConverter customerconverter;
	
	@PostMapping("/createCustomer")
	public String createCustomer(@Valid @RequestBody CustomerDTO customerDTO)
	{
		final Customer customers=customerconverter.convertDTOToCustomer(customerDTO);
		return customerservice.createCustomer(customers);
	}
	
	
	@GetMapping("/getCustomerById/{customer_id}")
	public CustomerDTO getCustomerById(@PathVariable("customer_id") int customer_id)
	{
		return customerservice.getCustomerById(customer_id);
	}
	
	
	@GetMapping("/getAllCustomers")
	public List<CustomerDTO> getAllCustomers()
	{
		return customerservice.getAllCustomers();
	}
	
	
	@PutMapping("/updateCustomer/{customer_id}")
	public CustomerDTO updateCustomer(@PathVariable("customer_id") int customer_id,
			@Valid @RequestBody CustomerDTO customerDTO )
	{
		Customer customer1=customerconverter.convertDTOToCustomer(customerDTO);
		return customerservice.updateCustomer(customer_id,customer1 );
	}
	
	@DeleteMapping("/deleteCustomerById/{customer_id}")
	public String deleteCustomerById(@PathVariable("customer_id") int customer_id)
	{		
		return customerservice.deleteCustomerById(customer_id) ;		
	}
	
	
	@DeleteMapping("/deleteAllCustomers")
	public ResponseEntity<String> deleteAllCustomers()
	{
		customerservice.deleteAllCustomers();
		return new ResponseEntity<String>("All customer details got deleted!",
				HttpStatus.OK);
	}
	
	@GetMapping("/getCustomerByName/{customer_name}")
	public List<CustomerDTO> getCustomerByName(@PathVariable("customer_name") String customer_name)
	{
		return customerservice.getCustomerByName(customer_name);
	}
	
	@GetMapping("/getCustomerDTOByemail/{email_id}")
	public List<CustomerDTO> getCustomerDTOByemail(@PathVariable("email_id") String email_id)
	{
		return customerservice.findByEmailId(email_id);		
	}

}
