package com.kafka.producer.controller;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankAccountDTO {

	private String agency;
	private String accountNumber;
	private Double balance;
}
