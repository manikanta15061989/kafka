package com.kafka.demo.controller;

import java.util.concurrent.ExecutionException;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.demo.model.Book;
import com.kafka.demo.service.KafkaProducerService;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
	
	@Autowired
	KafkaProducerService service ; 
	
	 @Autowired KafkaTemplate<String, Book> kafkaTemplate;
	
	@GetMapping(value = "/produce/{message}")
	public String produceMessage(@PathVariable(value = "message") String message) throws InterruptedException, ExecutionException {
		service.sendMessage(message);
		return "message returnde succcessfully "+message;
		
	}
	
	 // Annotation
    @PostMapping("/publish")
    public String publishMessage(@RequestBody Book book)
    {
        // Sending the message
        kafkaTemplate.send("second-topic", book);
  
        return "Published Successfully";
    }

}
