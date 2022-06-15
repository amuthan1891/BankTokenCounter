package com.banking.counter.exception;

@SuppressWarnings("serial")
public class BankingException extends Exception {

	
	 public BankingException(String errMSG, Exception e) {
	        super(errMSG, e);
	    }

	    public BankingException(String errMSG) {
	        super(errMSG);
	    }
}
