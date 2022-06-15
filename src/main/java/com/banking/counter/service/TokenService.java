package com.banking.counter.service;

import org.springframework.stereotype.Service;

import com.banking.counter.domain.CustomerTokenDetails;
import com.banking.counter.domain.TokenDetails;
import com.banking.counter.exception.BankingException;

@Service
public interface TokenService {
	
	public TokenDetails createAndAssignToken(CustomerTokenDetails custInfo) throws BankingException;
	
	public TokenDetails getToken(Long customerId) throws BankingException;
	
	
}
