package com.banking.counter.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.counter.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
