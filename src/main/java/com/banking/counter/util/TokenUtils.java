package com.banking.counter.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.banking.counter.domain.Token;
import com.banking.counter.respository.TokenRespository;

@Component
public class TokenUtils {
	
	static Logger logger = LoggerFactory.getLogger(TokenUtils.class);
	private static final String QUEUED = "QUEUED";
	
	private static TokenRespository tokenRespository;
	
	public TokenUtils(TokenRespository tokenRespository) {
		TokenUtils.tokenRespository = tokenRespository;
	}
	
	public static Token getToken(Long customerId, String priority) {
		logger.info("customerId {}",customerId);
		Token newToken = new Token();
		newToken.setId(customerId);
		Token createdToken = tokenRespository.save(newToken);
		logger.info("created token{}:", createdToken);		
		return newToken;
	}
	
	public static Token updateTokenStatus(Token createdToken) {	
		tokenRespository.updateTokenStatus(QUEUED, createdToken.getTokenId());
		createdToken.setTokenStatus(QUEUED);
		return createdToken;
		
	}
}
