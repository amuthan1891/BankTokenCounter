package com.banking.counter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banking.counter.domain.Customer;
import com.banking.counter.exception.BankingException;

@Service
public interface CustomerService {
	public Customer createCustomer(Customer customer) throws BankingException;
	public Boolean isCustomerExists(Long id) throws BankingException;
	public Optional<Customer> getCustomer(Long id) throws BankingException;
	public List<Customer> getAllCustomers();
}
