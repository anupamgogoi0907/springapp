package com.anupam.app.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

	@MessageMapping("/help")
	@SendTo("/topic/message")
	public String getMessage() throws InterruptedException {
		Thread.sleep(2000);
		return "Help me";
	}

}
