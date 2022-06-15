package com.banking.counter.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.banking.counter.domain.Counter;

@Service
public interface CounterService {
	
	public List<Counter> getAvailability() throws Exception;

}
