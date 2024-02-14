package com.awinas.websocket.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ChatMessage {
	private String content;
	private String sender;
	private MessageType type;

	public enum MessageType {
		CHAT, LEAVE, JOIN
	}

}