package com.banking.counter.queue;

import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.banking.counter.domain.Counter;
import com.banking.counter.domain.Token;
import com.banking.counter.exception.BankingException;
import com.banking.counter.respository.CounterRepository;
import com.banking.counter.util.TokenUtils;

@Component
public class QueueOperator extends Thread {

	static Logger logger = LoggerFactory.getLogger(QueueOperator.class);
	

	private static CounterRepository counterRepository;
	
	public QueueOperator(CounterRepository counterRepository) {
		QueueOperator.counterRepository = counterRepository;
	}
	
	public static PriorityQueue<Long> premiumCounterQueue = new PriorityQueue<>();
	public static PriorityQueue<Long> normalCounterQueue = new PriorityQueue<>();
	public static PriorityQueue<Long> tokenQueue = new PriorityQueue<>();
	public static PriorityQueue<Token> servingQueue = new PriorityQueue<>();
	
	ExecutorService executor;
	
	@EventListener(ApplicationReadyEvent.class)
    public void initCounters() {
        List<Counter> counterList = counterRepository.findAll();

        if(counterList.isEmpty()) {
            throw new RuntimeException("CounterDetails not available exception");
        }
        executor = Executors.newFixedThreadPool(counterList.size());
        addPremiumCountersToQueue(counterList);
        addNormalCountersToQueue(counterList);
    }
	
	
	public void addPremiumCountersToQueue (List<Counter> counterList) {
		List<Counter> premiumCounterList = counterList.stream().filter(
				c -> c.getStatus().equalsIgnoreCase("active") &&  
				c.getCounterType().equalsIgnoreCase("P") && 
				c.getCounterStatus().equalsIgnoreCase("OPEN")).collect(Collectors.toList());
		premiumCounterList.forEach(p -> {
			if(!premiumCounterQueue.contains(p.getCounterId()))
			premiumCounterQueue.add(p.getCounterId());
		});
		logger.info("Premium Queue:"+premiumCounterQueue);	
		}
	
	public static void addNormalCountersToQueue (List<Counter> counterList) {
		List<Counter> normalCounterList = counterList.stream().filter(
				c -> c.getStatus().equalsIgnoreCase("active") &&  
				c.getCounterType().equalsIgnoreCase("N") &&
				c.getCounterStatus().equalsIgnoreCase("OPEN")).collect(Collectors.toList());
		normalCounterList.forEach(n -> {
			if(!normalCounterQueue.contains(n.getCounterId())) {
				normalCounterQueue.add(n.getCounterId());
			}			
		});
		logger.info("Normal Queue:"+normalCounterQueue);	
	}
	
	public static Long assignCounterForToken(String priority) throws BankingException {
		Long counterId = null;
		if(priority.equalsIgnoreCase("Premium")) {			
			for(Long p:premiumCounterQueue) {
				if(!tokenQueue.contains(p)) {
					counterId = premiumCounterQueue.peek();
					break;
				}
			}
		}else {
			for(Long n:normalCounterQueue) {
				if(!tokenQueue.contains(n)) {
					counterId = normalCounterQueue.peek();
					break;
				}
			}
		} if(counterId== null) {
			throw new BankingException("Queue is full.Please wait!!");
		}
		return counterId;
	}
	
	public static String updateCounterStatus (Token token) {
		counterRepository.updateCounterStatus(token.getTokenStatus(), token.getAssignedCounter());
		return token.getTokenStatus();
	}
	
	public static void addServiceTokentoTokenQueue(Token token) {
		for(Long t:tokenQueue) {
			if(token.getAssignedCounter() != t) {
				tokenQueue.add(token.getAssignedCounter());
				break;
			}
		}
	}
	
	public static void addServiceTokentoServingQueue(Token token) throws BankingException {
		for(Token t:servingQueue) {
			if(t.getId()!=token.getId()) {
				servingQueue.add(token);
				break;
			}
		}
	}
	public static Token fetchToken(Token token) {
		return servingQueue.peek();
	}
	public static void removeFromServingQueue(Token token) {
		servingQueue.poll();
		tokenQueue.poll();
	}
	

	
	@Override
	public void run() {
		while(true) {
			try {
				logger.info("Thread starts");
				Token token = servingQueue.peek();
				token.setTokenStatus("Completed");
				TokenUtils.updateTokenStatus(token);
				updateCounterStatus(token);
				servingQueue.poll();
				tokenQueue.poll();
			} catch (Exception e) {
				logger.error("Empty queue");
			}
		}
		
	}
	
	
}
