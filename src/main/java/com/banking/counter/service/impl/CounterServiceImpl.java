package com.banking.counter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.counter.domain.Counter;
import com.banking.counter.respository.CounterRepository;
import com.banking.counter.service.CounterService;

@Service
public class CounterServiceImpl implements CounterService {

	@Autowired
	private CounterRepository counterRepository;
	
	public List<Counter> getAvailability() throws Exception {
		return counterRepository.findAll();
	}
}
