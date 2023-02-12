package com.notificationservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class NotificationserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationserviceApplication.class, args);
	}

	@KafkaListener(topics = "notificationTopic")
	public void handleNotification(com.notificationservice.event.OrderPlacedEvent orderPlacedEvent){
		//need to send mail through mail
		log.info("Notification received for order : "+orderPlacedEvent.getOrderNumber());
	}

}
