package com.banking.counter.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.counter.domain.Customer;
import com.banking.counter.exception.BankingException;
import com.banking.counter.respository.CustomerRepository;
import com.banking.counter.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	Predicate<Long> checkIdPredicate = i-> i<0;
	
	public Customer createCustomer(Customer customer) {
		
		return customerRepository.save(customer);
	}
	
	public Boolean isCustomerExists(Long id) throws BankingException {
		if(checkIdPredicate.test(id)) {
			throw new BankingException("Invalid ID:"+id);
		}		
		return customerRepository.existsById(id);
	}
	
	public Optional<Customer> getCustomer(Long id) throws BankingException {
		
	   return Optional.of(customerRepository.findById(id)
			   .orElseThrow(() -> new BankingException("Customer not found:"+id)));		
	}
	
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
	
	

}
