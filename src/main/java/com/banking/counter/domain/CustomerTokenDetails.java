package com.banking.counter.domain;

import org.springframework.lang.NonNull;

public class CustomerTokenDetails {
	
	@NonNull
	private String name;
	@NonNull
	private String address;
	@NonNull
	private String priority;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	@Override
	public String toString() {
		return "CustomerTokenDetails [customerId=" + ", name=" + name + ", address=" + address
				+ ", priority=" + priority + "]";
	}

}
