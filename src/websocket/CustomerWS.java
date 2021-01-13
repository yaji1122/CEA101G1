package websocket;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONArray;
import org.json.JSONObject;

import com.emp.model.EmpService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import websocket.chat.model.ChatMessage;
import websocket.chat.model.State;
import websocket.jedis.JedisHandleMessage;


@ServerEndpoint("/customerWS/{memberOrEmpID}")
public class CustomerWS {
	private static Map<String, Session> sessionsMapForMember = new ConcurrentHashMap<>();
	private static Map<String, Session> sessionsMapForEmp = new ConcurrentHashMap<>();
	Gson gson = new Gson();

	@OnOpen
	public void onOpen(@PathParam("memberOrEmpID") String memberOrEmpID, Session userSession) throws IOException {
		/* save the new user in the map */
		/* Sends all the connected users to the new user */
		/* 只有會員上線才通知後台員工的即時客服系統*/
		if (memberOrEmpID.contains("MEM")) {
			String memberID = memberOrEmpID;
			sessionsMapForMember.put(memberID, userSession);
			Set<String> memberIDs = sessionsMapForMember.keySet();
			Collection<Session> empSessions = sessionsMapForEmp.values();
			Set<String> empIDs = sessionsMapForEmp.keySet();
			if (empSessions.size() > 0) { //如果員工在線就發送通知
				State stateMessage = new State("open", memberID, memberIDs);
				String stateMessageJson = gson.toJson(stateMessage);
				for (Session session : empSessions) {
					if (session.isOpen()) {
						session.getAsyncRemote().sendText(stateMessageJson);
						for (String empID: empIDs) {
							if (sessionsMapForEmp.get(empID).equals(session)) {
								JSONObject jsonObj = new JSONObject();
								EmpService empSvc = new EmpService();
								String empName = empSvc.getOneEmp(empID).getEmp_name();
								jsonObj.put("empID", empID);
								jsonObj.put("empName", empName);
								jsonObj.put("type", "open");
								userSession.getAsyncRemote().sendText(jsonObj.toString());
								break;
							}
						}
					}
				}
			} else { 
				userSession.getAsyncRemote().sendText("目前客服人員忙線中，請稍待片刻");
			}
			String text = String.format("Session ID = %s, connected; memberID = %s%nusers: %s", userSession.getId(),
					memberID, memberIDs);
			System.out.println(text);
		}
		if (memberOrEmpID.contains("EMP")) { //員工上線後發送
			String empID = memberOrEmpID;
			sessionsMapForEmp.put(empID, userSession);
			Set<String> memberIDs = sessionsMapForMember.keySet();
			if (memberIDs.size() > 0) {
				State stateMessage = new State();
				stateMessage.setMemberIDs(memberIDs);
				stateMessage.setType("openEmp");
				userSession.getAsyncRemote().sendText(gson.toJson(stateMessage));
			}
		}
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
		String sender = chatMessage.getSender();
		String receiver = chatMessage.getReceiver();
		
		if ("history".equals(chatMessage.getType())) {
			List<String> historyData = JedisHandleMessage.getHistoryMsg(sender, receiver);
			JSONArray jsonArr = new JSONArray();
			historyData.stream().forEach(e -> jsonArr.put(new JSONObject(e)));
			if(historyData.size() > 0) {
				JSONObject obj = new JSONObject();
				obj.put("type", "history");
				obj.put("message", jsonArr.toString());
				System.out.println(jsonArr.toString());
				if (userSession != null && userSession.isOpen()) {
					userSession.getAsyncRemote().sendText(obj.toString());
					System.out.println("history = " + obj.toString());
					return;
				}
			}
		}
		
		if (receiver.contains("EMP")) { //會員發送給客服人員
			Session empSession = sessionsMapForEmp.get(receiver.split("-")[0]);
			if (empSession != null && empSession.isOpen()) {
				empSession.getAsyncRemote().sendText(message);
				userSession.getAsyncRemote().sendText(message);
				JedisHandleMessage.saveChatMessage(sender, receiver, message);
			}
		} else { //客服人員發送給會員
			Session memberSession = sessionsMapForMember.get(receiver.split("-")[0]);
			if (memberSession != null && memberSession.isOpen()) {
				memberSession.getAsyncRemote().sendText(message);
				userSession.getAsyncRemote().sendText(message);
				JedisHandleMessage.saveChatMessage(sender, receiver, message);
			}
		}
		System.out.println("Message received: " + message);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
		e.printStackTrace();
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		String closeMemberID = null;
		
		if (sessionsMapForMember.values().contains(userSession)) { //使用者離線
			Set<String> memberIDs = sessionsMapForMember.keySet();
			for (String memberID : memberIDs) {
				if (sessionsMapForMember.get(memberID).equals(userSession)) {
					closeMemberID = memberID;
					sessionsMapForMember.remove(memberID);
					break;
				}
			}
			if (closeMemberID != null) {
				State stateMessage = new State("close", closeMemberID, memberIDs);
				String stateMessageJson = gson.toJson(stateMessage);
				Collection<Session> empSessions = sessionsMapForEmp.values();
				for (Session session : empSessions) {
					session.getAsyncRemote().sendText(stateMessageJson);
				}
			}
		} else { //會員離線
			Set<String> empIDs = sessionsMapForEmp.keySet();
			for (String empID : empIDs) {
				if (sessionsMapForEmp.get(empID).equals(userSession)) {
					sessionsMapForEmp.remove(empID);
					break;
				}
			}
		}
		String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
				reason.getCloseCode().getCode(), closeMemberID);
		System.out.println(text);
	}
}
