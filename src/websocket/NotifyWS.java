package websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;

@ServerEndpoint(value="/NotifyWS", configurator=ServletAwareConfig.class)
public class NotifyWS {
	private static final Set<Session> distributor = Collections.synchronizedSet(new HashSet<>());
	private EndpointConfig newConfig;
	@OnOpen
	public void onOpen(Session userSession, EndpointConfig config) throws IOException {
		distributor.add(userSession);
		this.newConfig = config;
		HttpSession httpSession = (HttpSession) newConfig.getUserProperties().get("httpSession");
		httpSession.setAttribute("wsSessions", distributor);
		String text = String.format("Session ID = %s", userSession.getId());
		System.out.println(text);
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		userSession.getAsyncRemote().sendText(message);
		System.out.println("Message received: " + message);
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		distributor.remove(userSession);
		String text = String.format("session ID = %s, disconnected; close code = %d; reason phrase = %s",
				userSession.getId(), reason.getCloseCode().getCode(), reason.getReasonPhrase());
		System.out.println(text);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
		e.printStackTrace();
	}
	
	public void sendNotify(String type, String odno, Set<Session> sessions) {
		JSONObject data = new JSONObject();
		data.put("type", type);
		data.put("odno", odno);
		sessions.forEach(e -> e.getAsyncRemote().sendText(data.toString()));
	}
}

