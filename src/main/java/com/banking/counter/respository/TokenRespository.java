package com.banking.counter.respository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.banking.counter.domain.Token;

@Repository
public interface TokenRespository extends JpaRepository<Token, Long> {
	
	
	@Modifying
	@Transactional
	@Query("update Token t SET t.tokenStatus=:tokenStatus where t.id=:id")
	public int updateTokenStatus(@Param("tokenStatus")String tokenStatus,@Param("id") Long id); 

}
