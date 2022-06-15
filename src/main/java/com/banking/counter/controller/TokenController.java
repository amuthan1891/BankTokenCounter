package com.banking.counter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.banking.counter.domain.CustomerTokenDetails;
import com.banking.counter.domain.TokenDetails;
import com.banking.counter.service.TokenService;

import io.swagger.annotations.Api;

@Api(value = "/banking/v1",
description = "Token Controller Endpoint for managing Customer and counters")
@RestController
public class TokenController {
	
	@Autowired
	private TokenService tokenService;
	 
	 @PostMapping(value="/token/create")
	 public ResponseEntity<TokenDetails> createToken(@RequestBody CustomerTokenDetails custInfo) throws Exception {
			return new ResponseEntity<>(tokenService.createAndAssignToken(custInfo),HttpStatus.OK);
		}
	 
	 @GetMapping(value = "/token/get/{customerId}")
	 public ResponseEntity<TokenDetails> getToken(@PathVariable("customerId") Long customerId) throws Exception {
			return new ResponseEntity<>(tokenService.getToken(customerId),HttpStatus.OK);
		}

}


