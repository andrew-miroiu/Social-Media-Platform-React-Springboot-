package com.andrei.springboot.controller;

import org.springframework.web.bind.annotation.*;
import com.andrei.springboot.dto.MessageResponseDTO;
import com.andrei.springboot.service.MessageService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/conversation/{conversationId}")
    public List<MessageResponseDTO> getMessagesByConversationId(@PathVariable UUID conversationId) {
        return messageService.getMessagesByConversationId(conversationId);
    }
}