package com.banking.counter.service.impl;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.counter.domain.Customer;
import com.banking.counter.domain.CustomerTokenDetails;
import com.banking.counter.domain.Token;
import com.banking.counter.domain.TokenDetails;
import com.banking.counter.exception.BankingException;
import com.banking.counter.queue.QueueOperator;
import com.banking.counter.service.CustomerService;
import com.banking.counter.service.TokenService;
import com.banking.counter.util.TokenUtils;

@Service
public class TokenServiceImpl implements TokenService {
	Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

	@Autowired
	private CustomerService customerService;
		
	@Autowired(required = true)
	QueueOperator queueOperator ;
	
	public TokenDetails createAndAssignToken(CustomerTokenDetails custInfo) throws BankingException {
		
		String priority = custInfo.getPriority();
		Long counterId;
		Long customerId;
		if (StringUtils.isNotEmpty(custInfo.getName()) &&
				StringUtils.isNotEmpty(custInfo.getAddress()) &&
						StringUtils.isNotEmpty(custInfo.getPriority())){
			Customer customer = new Customer();
			customer.setAddress(custInfo.getAddress());
			customer.setName(custInfo.getName());
			if(priority.equalsIgnoreCase("Premium") || priority.equalsIgnoreCase("Normal")) {
				customer.setPriority(priority);
			} else  {
				throw new BankingException("Please enter valid Priority(Normal|Premum)");
			}
			logger.info("creating customer {}",customer);
			Customer createdCustomer = customerService.createCustomer(customer);
			customerId = createdCustomer.getId();
		} else {
			throw new BankingException("Please enter Name,Address and Priority to create customer");
		}
		Token createdToken = TokenUtils.getToken(customerId, priority);
		createdToken = TokenUtils.updateTokenStatus(createdToken);
		counterId = QueueOperator.assignCounterForToken(priority);
		createdToken.setAssignedCounter(counterId);
		String counterStatus = QueueOperator.updateCounterStatus(createdToken);
		TokenDetails tokenDetails = new TokenDetails(customerId,priority, createdToken.getTokenId(), 
				createdToken.getTokenStatus(),counterId,counterStatus);
	
		return tokenDetails;
	}

	
	public TokenDetails getToken(Long customerId) throws BankingException {
		
		String priority = "";
		Long counterId;
		if (customerId > 0 && customerService.isCustomerExists(customerId)) {
			Optional<Customer> customer = customerService.getCustomer(customerId);
			if(customer.isPresent()) {
				priority = customer.get().getPriority();
			} 			
		} else {
			throw new BankingException("Customer not available in the banking records."
					+ "Please create new customer and proceed");
		}
		Token createdToken = TokenUtils.getToken(customerId, priority);
		counterId = QueueOperator.assignCounterForToken(priority);
		createdToken.setAssignedCounter(counterId);
		createdToken = TokenUtils.updateTokenStatus(createdToken);
		String counterStatus = QueueOperator.updateCounterStatus(createdToken);
		TokenDetails tokenDetails = new TokenDetails(customerId,priority, createdToken.getTokenId(), 
				createdToken.getTokenStatus(),counterId,counterStatus);
		QueueOperator.addServiceTokentoTokenQueue(createdToken);
		QueueOperator.addServiceTokentoServingQueue(createdToken);
		return tokenDetails;
	}

}
