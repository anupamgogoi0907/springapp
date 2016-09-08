package com.anupam.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class StompController {

	@Autowired
	SimpMessagingTemplate template;

	@MessageMapping("/ping")
	@SendTo("/topic/ping")
	public String ping(String message) throws Exception {
		System.out.println(message);
		return "Hello " + message;
	}
}
