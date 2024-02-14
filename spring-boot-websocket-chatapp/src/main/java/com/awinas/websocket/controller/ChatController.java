package com.awinas.websocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.awinas.websocket.model.ChatMessage;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ChatController {

	/*
	 * First the user need to register in the portal then only he can chat
	 */
	@MessageMapping("/chat.register")
	@SendTo("/topic/public")
	public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		log.info("<<ChatController>> <<register>> {} ", chatMessage);
		if (headerAccessor != null && headerAccessor.getSessionAttributes() != null) {
			headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		}
		return chatMessage;
	}

	/*
	 * Send Message in the chat
	 */
	@MessageMapping("/chat.send")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
		log.info("<<ChatController>> <<sendMessage>> {} ", chatMessage);
		return chatMessage;
	}

}
