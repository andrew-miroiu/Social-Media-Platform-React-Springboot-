package com.andrei.springboot.service.impl;

import java.util.UUID;
import java.util.List;

import org.springframework.stereotype.Service;
import com.andrei.springboot.dto.MessageResponseDTO;
import com.andrei.springboot.model.Message;
import com.andrei.springboot.repository.MessageRepository;
import com.andrei.springboot.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    @Override
    public List<MessageResponseDTO> getMessagesByConversationId(UUID conversationId) {
        List<Message> messages = messageRepository.findByConversation_IdOrderByCreatedAtAsc(conversationId);

        return messages.stream()
                .map(message -> new MessageResponseDTO(
                        message.getId(),
                        message.getConversation().getId(),
                        message.getSenderId(),
                        message.getContent(),
                        message.getCreatedAt()
                ))
                .toList();
        }
}