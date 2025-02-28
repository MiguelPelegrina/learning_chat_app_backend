package com.pelegrina.learning_chat_app_backend;

import org.springframework.boot.SpringApplication;

public class TestLearningChatAppBackendApplication {

	public static void main(String[] args) {
		SpringApplication.from(LearningChatAppBackendApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
