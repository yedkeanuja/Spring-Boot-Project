package com.psms.service;

import java.util.List;

import com.psms.entity.Customer;
import com.psms.model.CustomerDTO;

public interface CustomerService {
	String createCustomer(Customer customers); //save a new customer
	CustomerDTO getCustomerById(int customer_id); //fetch data of a customer using id 
	List<CustomerDTO> getAllCustomers();  //fetch all customers
	CustomerDTO updateCustomer(int customer_id,Customer customers); //fetch and update the customer details
	
	String deleteCustomerById(int cutomer_id);  //deleting book by id
	
	void deleteAllCustomers();  //delete all the customers
	
	List<CustomerDTO> getCustomerByName(String customer_Name);
	List<CustomerDTO> findByEmailId(String emailId);
	
	CustomerDTO assignCustomerToShopKeeper(int customerId,int ShopmanagerId);
	

}
