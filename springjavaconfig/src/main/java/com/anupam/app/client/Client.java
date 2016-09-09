package com.anupam.app.client;

import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

public class Client {

	static String url = "ws://localhost:8080/springjavaconfig/ws/websocket";

	public static void main(String[] a) throws InterruptedException {
		WebSocketClient webSocketClient = new StandardWebSocketClient();
		WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
		stompClient.setMessageConverter(new StringMessageConverter());
		StompSessionHandler sessionHandler = new MyStompSessionHandler();
		stompClient.connect(url, sessionHandler);

		// Wait sometime.
		Thread.sleep(5000);
	}

	public static class MyStompSessionHandler extends StompSessionHandlerAdapter {

		@Override
		public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
			System.out.println("Connection successful.");
			session.send("/app/ping", "Hi sexy..");
		}
	}
}
