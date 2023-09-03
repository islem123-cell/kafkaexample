package com.example.kafkaexample.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.example.kafkaexample.payload.User;

@Service
public class JsonKafkaProducer {
	
private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);

private KafkaTemplate<String, User> kafkaTemplate;//puisque nous envoyons des objets Java au topic Kafka qui seront automatiquement transformés en un octet JSON

public JsonKafkaProducer(KafkaTemplate<String, User> kafkaTemplate) {
	this.kafkaTemplate = kafkaTemplate;
}


public void sendMessage(User data) {
	LOGGER.info(String.format("Message sent -> %s ", data.toString())); //créé un Message à l'aide de MessageBuilder 
	Message<User> message = MessageBuilder.withPayload(data)
			.setHeader(KafkaHeaders.TOPIC, "testJson")
			.build();

	kafkaTemplate.send(message);
}

}
 