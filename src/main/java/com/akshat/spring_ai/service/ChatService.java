package com.akshat.spring_ai.service;

import com.akshat.spring_ai.exception.custom.EmptyModelResponseException;
import com.akshat.spring_ai.exception.custom.InvalidImageException;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatClient chatClient;

    private String requireContent(String content) {
        if (content == null || content.isBlank()) {
            throw new EmptyModelResponseException("Model returned no content");
        }
        return content;
    }

    public String sendChatMessage(String message) {
        String content = chatClient.prompt().user(message).call().content();
        return requireContent(content);
    }

    public Flux<String> sendChatMessageStream(String message) {
        return chatClient.prompt().user(message).stream().content();
    }

    public String sendImageMessage(String message, MultipartFile image) {
        String contentType = image.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new InvalidImageException("Invalid image type: " + contentType);
        }
        String content =
                chatClient.prompt().user(prompt -> prompt.text(message).media(MediaType.parseMediaType(contentType),
                        new InputStreamResource(image))).call().content();

        return requireContent(content);
    }
}
