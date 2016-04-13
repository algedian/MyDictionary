package kr.ac.ajou.mydictionary.userdata;

public class User {
	private Integer index;
	private String userId;
	private String name;
	private String email;
	private String pictureURL;

	public User() {
		super();
	}

	public User(Integer index, String userId, String name, String email, String pictureURL) {
		super();
		this.index = index;
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.pictureURL = pictureURL;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPictureURL() {
		return pictureURL;
	}

	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}

	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder(200);
		buf.append("{index=" + index);
		buf.append(", userId=" + userId);
		buf.append(", name=" + name);
		buf.append(", email=" + email);
		buf.append("}");

		return buf.toString();
	}
}
