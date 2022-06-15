package com.banking.counter.respository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.banking.counter.domain.Counter;

@Repository
public interface CounterRepository extends JpaRepository<Counter, Long> {
	
	
	@Modifying
	@Transactional
	@Query("update Counter c SET c.counterStatus=:counterStatus where c.id=:assignedCounter")
    int updateCounterStatus(@Param("counterStatus") String counterStatus, @Param("assignedCounter") Long assignedCounter);

}
