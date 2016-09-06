package com.anupam.app.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class SpringWebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic");
		config.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/chat").withSockJS();
	}

	@Controller
	public class MyMessageController {
		@MessageMapping("/chat")
		@SendTo("/topic/messages")
		public Greeting send(HelloMessage message) throws Exception {
			String time = new SimpleDateFormat("HH:mm").format(new Date());
			return new Greeting(time);
		}
	}
	public class HelloMessage {

	    private String name;

	    public HelloMessage() {
	    }

	    public HelloMessage(String name) {
	        this.name = name;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }
	}
	public class Greeting {

		private String content;

		public Greeting() {
		}

		public Greeting(String content) {
			this.content = content;
		}

		public String getContent() {
			return content;
		}

	}

}
