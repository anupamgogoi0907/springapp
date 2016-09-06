package com.anupam.app.controller;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/user")
public class UserController {

//	@Autowired
//	private JmsTemplate jmsTemplate;

	
	@RequestMapping(path = "/hello", method = RequestMethod.GET)
	public @ResponseBody String sayHello() {
		return "Hello World";
	}

//	@RequestMapping(path = "/msg", method = RequestMethod.GET)
//	public void sendMsg() throws JMSException {
//		jmsTemplate.send("jms.topic.test", new MessageCreator() {
//
//			@Override
//			public Message createMessage(Session s) throws JMSException {
//				TextMessage txt = s.createTextMessage();
//				txt.setText("Helloo");
//				return txt;
//			}
//		});
//	}
}
