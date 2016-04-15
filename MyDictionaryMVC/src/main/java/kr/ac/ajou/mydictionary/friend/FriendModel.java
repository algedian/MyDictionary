package kr.ac.ajou.mydictionary.friend;

public class FriendModel {
	protected Integer userIndex;
	protected Integer friendIndex;
	
	public FriendModel() {
		super();
	}
	
	public FriendModel(Integer userIndex, Integer friendIndex) {
		super();
		this.userIndex = userIndex;
		this.friendIndex = friendIndex;
	}

	public Integer getUserIndex() {
		return userIndex;
	}

	public void setUserIndex(Integer userIndex) {
		this.userIndex = userIndex;
	}

	public Integer getFriendIndex() {
		return friendIndex;
	}

	public void setFriendIndex(Integer friendIndex) {
		this.friendIndex = friendIndex;
	}
	
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder(200);
		buf.append("{userIndex=" + userIndex);
		buf.append(", friendIndex=" + friendIndex);
		buf.append("}");

		return buf.toString();
	}
}
