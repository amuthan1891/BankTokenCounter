package com.banking.counter.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TOKEN")
public class Token {
	
	@Column(name = "id")
	private Long id;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tokenId;
	private Long assignedCounter;
	private String tokenStatus;
	
	public String getTokenStatus() {
		return tokenStatus;
	}
	public void setTokenStatus(String tokenStatus) {
		this.tokenStatus = tokenStatus;
	}
	public Long getAssignedCounter() {
		return assignedCounter;
	}
	public void setAssignedCounter(Long assignedCounter) {
		this.assignedCounter = assignedCounter;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTokenId() {
		return tokenId;
	}
	public void setTokenId(Long tokenId) {
		this.tokenId = tokenId;
	}

	
	@Override
	public String toString() {
		return "Token [id=" + id + ", tokenId=" + tokenId + ", assignedCounter=" + assignedCounter + ", tokenStatus="
				+ tokenStatus + "]";
	}

}
