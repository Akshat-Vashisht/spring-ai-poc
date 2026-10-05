package com.akshat.spring_ai.controller;

import com.akshat.spring_ai.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<String> sendChatMessage(@RequestBody String message) {
        String result = chatService.sendChatMessage(message);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/stream")
    public Flux<String> sendChatMessageStream(@RequestBody String message) {
        return chatService.sendChatMessageStream(message);
    }

    @PostMapping("/image")
    public ResponseEntity<String> sendImageMessage(@RequestPart String message, @RequestPart MultipartFile image) {
        String result = chatService.sendImageMessage(message, image);
        return ResponseEntity.ok(result);
    }
}
