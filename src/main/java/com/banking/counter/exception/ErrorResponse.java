package com.banking.counter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ErrorResponse {
	
	private int statusCode;
	private String message;

	 public int getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
 
	   
   public ErrorResponse(int statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}


	@ExceptionHandler(value = BankingException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
		public ErrorResponse bankingException(
				BankingException ex)
		{
		  return new ErrorResponse(HttpStatus.CONFLICT.value(),
		                           ex.getMessage());
		}
}
