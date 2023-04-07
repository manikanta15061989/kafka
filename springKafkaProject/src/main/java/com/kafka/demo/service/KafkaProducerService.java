package com.kafka.demo.service;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
public class KafkaProducerService {
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate ;
	
	public void sendMessage(String message) throws InterruptedException, ExecutionException {
		ListenableFuture<SendResult<String, String>> anme =  kafkaTemplate.send("first-topic", message);
		System.out.println( anme.get().getProducerRecord().value());
	}

}
