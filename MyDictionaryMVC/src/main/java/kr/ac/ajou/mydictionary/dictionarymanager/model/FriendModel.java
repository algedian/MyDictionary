package kr.ac.ajou.mydictionary.dictionarymanager.model;

public class FriendModel {
	private int userIndex;
	private int friendIndex;

	public FriendModel() {
		super();
	}

	public FriendModel(int userIndex, int friendIndex) {
		super();
		this.userIndex = userIndex;
		this.friendIndex = friendIndex;
	}

	public int getUserIndex() {
		return userIndex;
	}

	public void setUserIndex(int userIndex) {
		this.userIndex = userIndex;
	}

	public int getFriendIndex() {
		return friendIndex;
	}

	public void setFriendIndex(int friendIndex) {
		this.friendIndex = friendIndex;
	}

	@Override
	public String toString() {
		return "FriendModel [userIndex=" + userIndex + ", friendIndex=" + friendIndex + "]";
	}
}
