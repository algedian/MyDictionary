package kr.ac.ajou.mydictionary.dictionarymanager.model;

public class SearchModel {
	private int userIndex;
	private String userId;
	private String keyword;

	public SearchModel() {
		super();
	}

	public SearchModel(int userIndex, String userId, String keyword) {
		super();
		this.userIndex = userIndex;
		this.userId = userId;
		this.keyword = keyword;
	}

	public int getUserIndex() {
		return userIndex;
	}

	public void setUserIndex(int userIndex) {
		this.userIndex = userIndex;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "SearchModel [userIndex=" + userIndex + ", userId=" + userId + ", keyword=" + keyword + "]";
	}
}
