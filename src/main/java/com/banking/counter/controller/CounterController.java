package com.banking.counter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.counter.domain.Counter;
import com.banking.counter.service.CounterService;

import io.swagger.annotations.Api;

@Api(value = "/banking/v1",
description = "Counter Controller Endpoint for managing counters")
@RestController
public class CounterController {
	@Autowired
	private CounterService counterService;
	
	@GetMapping(value = "/counter/getAvailablity")
	 public ResponseEntity<List<Counter>> getAvailability() throws Exception {
			return new ResponseEntity<>(counterService.getAvailability(),HttpStatus.OK);
		}
	

}
