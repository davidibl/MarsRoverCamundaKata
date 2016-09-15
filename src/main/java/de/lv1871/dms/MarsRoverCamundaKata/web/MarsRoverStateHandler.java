package de.lv1871.dms.MarsRoverCamundaKata.web;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.lv1871.dms.MarsRoverCamundaKata.domain.constant.MarsRoverState;

@Component
public class MarsRoverStateHandler extends TextWebSocketHandler {

	WebSocketSession session;

	// This will send only to one client(most recently connected)
	public void sendMarsRoverState(MarsRoverState state) throws JsonProcessingException {

		String valueAsString = new ObjectMapper().writeValueAsString(state);

		if (session != null && session.isOpen()) {
			System.out.println("Trying to send:" + valueAsString);
			try {
				session.sendMessage(new TextMessage(valueAsString));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Don't have open session");
		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		System.out.println("Connection established");
		this.session = session;
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		if ("CLOSE".equalsIgnoreCase(message.getPayload())) {
			session.close();
		} else {
			System.out.println("Received:" + message.getPayload());
		}
	}

}
