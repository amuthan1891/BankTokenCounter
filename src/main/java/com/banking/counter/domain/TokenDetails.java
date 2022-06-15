package com.banking.counter.domain;

public class TokenDetails {

	private Long customerId;
	private String customerPriority;
	private Long tokenNumber;
	private String tokenStatus;
	private Long counterNumber;
	private String counterStatus;


	public String getCustomerPriority() {
		return customerPriority;
	}
	public void setCustomerPriority(String customerPriority) {
		this.customerPriority = customerPriority;
	}
	public String getTokenStatus() {
		return tokenStatus;
	}
	public void setTokenStatus(String tokenStatus) {
		this.tokenStatus = tokenStatus;
	}
	public String getCounterStatus() {
		return counterStatus;
	}
	public void setCounterStatus(String counterStatus) {
		this.counterStatus = counterStatus;
	}
	public String getStatus() {
		return customerPriority;
	}
	public void String (String customerPriority) {
		this.customerPriority = customerPriority;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getTokenNumber() {
		return tokenNumber;
	}
	public void setTokenNumber(Long tokenNumber) {
		this.tokenNumber = tokenNumber;
	}
	public Long getCounterNumber() {
		return counterNumber;
	}
	public void setCounterNumber(Long counterNumber) {
		this.counterNumber = counterNumber;
	}
	
	public TokenDetails() {
		
	}
	public TokenDetails(Long customerId, java.lang.String customerPriority, Long tokenNumber,
			java.lang.String tokenStatus, Long counterNumber, java.lang.String counterStatus) {
		super();
		this.customerId = customerId;
		this.customerPriority = customerPriority;
		this.tokenNumber = tokenNumber;
		this.tokenStatus = tokenStatus;
		this.counterNumber = counterNumber;
		this.counterStatus = counterStatus;
	}
}
