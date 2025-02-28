package com.pelegrina.learning_chat_app_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableCaching
@EnableJpaAuditing
@EnableScheduling
@SpringBootApplication
public class LearningChatAppBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningChatAppBackendApplication.class, args);
	}

}
