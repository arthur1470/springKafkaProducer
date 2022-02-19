package com.kafka.producer.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.kafka.producer.controller.BankAccountDTO;

@Service
public class BankAccountProducer {

	private static final Logger logger = LoggerFactory.getLogger(BankAccountProducer.class);
	private final String topic;
	private final KafkaTemplate<String, BankAccountDTO> kafkaTemplate;

	public BankAccountProducer(@Value(value = "${topic.name}") String topic, KafkaTemplate<String, BankAccountDTO> kafkaTemplate) {
		super();
		this.topic = topic;
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void send(BankAccountDTO bankAccountDTO) {
		kafkaTemplate.send(topic, bankAccountDTO).addCallback(
					success -> logger.info("Message send " + success.getProducerRecord().value()),
					failure -> logger.info("Message failure " + failure.getMessage())
				);
	}
}
