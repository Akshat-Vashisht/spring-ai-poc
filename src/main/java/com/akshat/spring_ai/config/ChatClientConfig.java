package com.akshat.spring_ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient chatClientModel(ChatClient.Builder builder, @Value("${app.ai.model.chat}") String chatModel) {
        return builder.defaultOptions(OllamaChatOptions.builder().model(chatModel)).build();
    }

    @Bean
    public ChatClient imageClientModel(ChatClient.Builder builder, @Value("${app.ai.model.image}") String imageModel) {
        return builder.defaultOptions(OllamaChatOptions.builder().model(imageModel)).build();
    }
}
