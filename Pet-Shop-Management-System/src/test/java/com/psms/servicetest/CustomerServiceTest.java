package com.psms.servicetest;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.psms.entity.Customer;
import com.psms.model.CustomerDTO;
import com.psms.repository.CustomerRepository;
import com.psms.service.CustomerService;
import com.psms.util.CustomerConverter;

@SpringBootTest
public class CustomerServiceTest {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerConverter customerConverter;
	
	@MockBean
	private CustomerRepository customerRepository;
	
	@Test
	@Order(1)
	void testCreateCustomer()
	{
		Customer customer=Customer.builder().customerName("Ankita").address("pune").contactNo("7387760479").emailId("Ankita@gmail.com").build();
		Mockito.when(customerRepository.save(customer)).thenReturn(customer);
//		assertEquals("Customer details added successfully!!",
//				customerService.createCustomer(customer));
		
		assertThat(customerService.createCustomer(customer)).isEqualTo("Customer details added successfully");
			
	}
	
	@Test
	@Order(2)
	void testGetAllCustomers()
	{
		Customer customer=Customer.builder().customerName("Gauri").address("ABC Briged").contactNo("9921828725").emailId("Gauri@gmail.com").build();
		Customer customer1=Customer.builder().customerName("Ankita").address("pune").contactNo("7387760479").emailId("Ankita@gmail.com").build();
				List<Customer>list=new ArrayList<Customer>();
				list.add(customer1);
				list.add(customer);
				
				Mockito.when(customerRepository.findAll()).thenReturn(list);
				
				List<CustomerDTO>dto =customerService.getAllCustomers();
				
				List<Customer> customers=new ArrayList<Customer>();
				dto.forEach(customerDto->
				customers.add(customerConverter.convertDTOToCustomer(customerDto))
						);
				assertThat(customers.get(0).getCustomerName()).isEqualTo(list.get(0).getCustomerName());
	}
	
	@Test
	@Order(3)
	void testUpdateCustomer()
	{
		Customer customer=Customer.builder().customerName("Ankita").address("Pune").contactNo("7387760479").emailId("Ankita@gmail.com").build();
		Optional<Customer> opCustomer= Optional.of(customer);
		
		Mockito.when(customerRepository.findById(customer.getCustomer_id())).thenReturn(opCustomer);
		
		Customer c =opCustomer.get();
		customer.setCustomerName("Gauri");
		
		Mockito.when(customerRepository.save(customer)).thenReturn(c);
		
		CustomerDTO dto=customerService.updateCustomer(customer.getCustomer_id(),customer);
		assertEquals(dto.getCustomerName(),c.getCustomerName());
				
	}
	
}
