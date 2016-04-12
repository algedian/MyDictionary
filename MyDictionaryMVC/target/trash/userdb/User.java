package kr.ac.ajou.mydictionary.userdb;

public class User {

	private int index;
	private String userId;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public String getId() {
		return userId;
	}

	public void setId(String id) {
		this.userId = id;
	}

	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder(50);
		buf.append("{index=");
		buf.append(index);
		buf.append(", userId=");
		buf.append(userId);
		buf.append("}");
		
		return buf.toString();
	}

	public String toSql() {
		return new String("userId='" + userId + "'");
	}
}
