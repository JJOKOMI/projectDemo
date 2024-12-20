package com.example.demo.service;


import com.example.demo.dto.Message.Request.MessageRequestDto;
import com.example.demo.dto.Message.Response.MessageResponseDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.entity.Message;
import com.example.demo.principal.PrincipalUser;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MessageService {
    ResponseDto<List<MessageResponseDto>> getAllMessages(Long id);


    ResponseDto<Void> deleteMessage(Long id);

    ResponseDto<MessageResponseDto> createMessage(MessageRequestDto dto, Long id);

    ResponseDto<MessageResponseDto> getMessageById(Long id);

    ResponseDto<List<MessageResponseDto>> getOutGoingMessages(Long id);

    ResponseDto<List<MessageResponseDto>> getReceiveMessages(Long id);
}
