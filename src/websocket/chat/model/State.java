package websocket.chat.model;

import java.util.Set;

public class State {
	private String type;
	// the memberID changing the state
	private String memberID;
	// total memberIDs
	private Set<String> memberIDs;
	
	public State() {};
	
	public State(String type, String memberID, Set<String> memberIDs) {
		super();
		this.type = type;
		this.memberID = memberID;
		this.memberIDs = memberIDs;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public Set<String> getMemberIDs() {
		return memberIDs;
	}

	public void setMemberIDs(Set<String> memberIDs) {
		this.memberIDs = memberIDs;
	}

}
