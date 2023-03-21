package com.psms.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.psms.entity.Customer;
import com.psms.model.CustomerDTO;

@Component
public class CustomerConverter {
	
	//converts from Customer entity to CustomerDTO
	public CustomerDTO convertEntityToCustomerDTO(Customer customers)
	{
		CustomerDTO customerDTO=new CustomerDTO();
		
		if(customers!=null)
		{
			BeanUtils.copyProperties(customers, customerDTO);
			
		}
		
		return customerDTO;
	}
	
	//converts from CustomerDTO to Customer entity
	public Customer convertDTOToCustomer(CustomerDTO customerDTO)
	{
		Customer customers=new Customer();
		if(customerDTO!=null)
		{
			BeanUtils.copyProperties(customerDTO, customers);
		}
		return customers;
		
	}
}
