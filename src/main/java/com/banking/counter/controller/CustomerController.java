package com.banking.counter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.banking.counter.service.CustomerService;

import io.swagger.annotations.Api;

@Api(value = "/banking/v1",
description = "Customer Controller Endpoint for managing customers")
@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	
	@GetMapping(value = "/customer/getAllCustomers")
	 public ResponseEntity<Object>  getAllCustomers() throws Exception {
			return new ResponseEntity<>(customerService.getAllCustomers(),HttpStatus.OK);
		}
	
	@GetMapping(value = "/customer/getCustomerById/{id}")
	 public ResponseEntity<Object>  getCustomerById(@PathVariable Long id) throws Exception {
			return new ResponseEntity<>(customerService.getCustomer(id),HttpStatus.OK);
		}
}
