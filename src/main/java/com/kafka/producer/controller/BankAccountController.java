package com.kafka.producer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.producer.producer.BankAccountProducer;

@RestController
@RequestMapping("/bank")
public class BankAccountController {

	private BankAccountProducer bankAccountProducer;	
	
	public BankAccountController(BankAccountProducer bankAccountProducer) {
		this.bankAccountProducer = bankAccountProducer;
	}

	@PostMapping
	public ResponseEntity<BankAccountDTO> send(@RequestBody BankAccountDTO bankAccountDTO) {
		BankAccountDTO bankAccount = BankAccountDTO.builder().agency(bankAccountDTO.getAgency()).accountNumber(bankAccountDTO.getAccountNumber()).balance(bankAccountDTO.getBalance()).build();
		
		bankAccountProducer.send(bankAccount);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(bankAccount);
	}
	
	@GetMapping
	public void receive() {
		System.out.println("OK");
	}
}
