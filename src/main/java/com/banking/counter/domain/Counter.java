package com.banking.counter.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "COUNTER")
public class Counter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long counterId;
	@Column(name = "STATUS")
	String status;
	@Column(name = "COUNTER_TYPE")
	String counterType;
	@Column(name = "COUNTER_STATUS")
	String counterStatus;
	
	public String getCounterStatus() {
		return counterStatus;
	}
	public void setCounterStatus(String counterStatus) {
		this.counterStatus = counterStatus;
	}
	public Long getCounterId() {
		return counterId;
	}
	public void setCounterId(Long counterId) {
		this.counterId = counterId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCounterType() {
		return counterType;
	}
	public void setCounterType(String counterType) {
		this.counterType = counterType;
	}
	
	@Override
	public String toString() {
		return "Counter [counterId=" + counterId + ", status=" + status + ", counterType=" + counterType
				+ ", counterStatus=" + counterStatus + "]";
	}

}
