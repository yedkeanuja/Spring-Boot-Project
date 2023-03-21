package com.psms.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.psms.entity.Customer;
import com.psms.entity.Shopkeeper;
import com.psms.exception.ResourceNotFoundException;
import com.psms.model.CustomerDTO;
import com.psms.repository.CustomerRepository;
import com.psms.repository.ShopKeeperRepository;
import com.psms.service.CustomerService;
import com.psms.util.CustomerConverter;

@Service
public class CustomerServiceImpl implements CustomerService{
	
private static final Logger log = LoggerFactory.getLogger(Customer.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ShopKeeperRepository shopmanagerRepository;
	
	@Autowired
	private CustomerConverter  customerconverter;

	@Override
	public String createCustomer(Customer customer) {
		String message=null;
		
		customer.setCustomerName(customer.getCustomerName());
		customer.setAddress(customer.getAddress());
		customer.setContactNo(customer.getContactNo());
		customer.setEmailId(customer.getEmailId());
		
		customerRepository.save(customer);
		log.info("Creating a new customer "+customer.toString()+" added at "+new Date());
				
		if(customer!=null)
		{
			message="Customer details added successfully";
		}
		return message;
	}

	@Override
	public CustomerDTO getCustomerById(int customer_id) {
		Customer customer= customerRepository.findById(customer_id).orElseThrow(()->
		new ResourceNotFoundException("Customer","ID",customer_id));
		log.info("Fetching customer details with id "+customer_id);
		return customerconverter. convertEntityToCustomerDTO (customer);                             		
	}

	@Override
	public List<CustomerDTO> getAllCustomers() {
		List<Customer>customer=customerRepository.findAll();
		List<CustomerDTO>customerDTO=new ArrayList<>();
		for(Customer cs:customer)
		{
			customerDTO.add(customerconverter.convertEntityToCustomerDTO(cs));			
		}
		
		log.info("Fetching all customers at "+new Date());
		return customerDTO ;
	}

	@Override
	public CustomerDTO updateCustomer(int customer_id, Customer customer) {
		Customer existingCustomer = customerRepository.findById(customer_id).get();
		
		existingCustomer.setCustomerName(customer.getCustomerName());
		existingCustomer.setAddress(customer.getAddress());
		existingCustomer.setContactNo(customer.getContactNo());
		existingCustomer.setEmailId(customer.getEmailId());
		
		customerRepository.save(existingCustomer);
		log.info("Updating customer details of customer_id "+customer_id+ " at "+new Date());
		return customerconverter.convertEntityToCustomerDTO(existingCustomer);
	}

	@Override
	public String deleteCustomerById(int customer_id) throws ResourceNotFoundException {
		String msg=null;
		Optional<Customer> customer=customerRepository.findById(customer_id);
		if(customer.isPresent())
		{
			customerRepository.deleteById(customer_id);
			log.info("Deleting a customer");
			msg="customer deleted successfully";
		}
		else
		{
			log.error("Customer with customer_id "+customer_id+" was not found!");
			throw new ResourceNotFoundException("Customer","Id",customer_id);			
		}
		return msg;
	}
  
	@Override
	public void deleteAllCustomers() {
		customerRepository.deleteAll();
	}
	
	@Override
	public List<CustomerDTO> getCustomerByName (String customerName){
	List<Customer> customer=customerRepository.findByCustomerName(customerName);
	List<CustomerDTO> customerDTO = new ArrayList<>();
	for(Customer c: customer)
	{
		customerDTO.add(customerconverter.convertEntityToCustomerDTO(c));		
	}
	return customerDTO;
	}
	
	
	@Override
	public List<CustomerDTO> findByEmailId (String emailId){
		List<Customer> customer=customerRepository.findByEmailId(emailId);
		List<CustomerDTO> customerDTO = new ArrayList<>();
		for(Customer c:customer)
		{
			customerDTO.add(customerconverter.convertEntityToCustomerDTO(c));
		}
		return customerDTO;				
	}

	@Override
	public CustomerDTO assignCustomerToShopKeeper(int customer_id, int Shopk_id) {
		Customer customer= customerRepository.findById(customer_id).get();
		Shopkeeper shopkeeper= shopmanagerRepository.findById(Shopk_id).get();
		
		customer.setShopkeeper(shopkeeper);
		Customer c= customerRepository.save(customer);
		
		return customerconverter.convertEntityToCustomerDTO(c);
		
		}
	}
