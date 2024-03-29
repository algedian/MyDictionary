package kr.ac.ajou.mydictionary.user;

public class UserModel {
	protected Integer index;
	protected String userId;
	protected String name;
	protected String email;
	protected String pictureURL;

	public UserModel() {
		super();
	}

	public UserModel(Integer index, String userId, String name, String email, String pictureURL) {
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
		buf.append("{");
		buf.append("index=" + index);
		buf.append(", userId=" + userId);
		buf.append(", name=" + name);
		buf.append(", email=" + email);
		buf.append("}");

		return buf.toString();
	}

	public String toJson() {
		//{"index":1471,"userId":"testUser1","name":"name1","email":"testUser1@gmail.com","pictureURL":null}
		String json = "{" + "\"" + "index" + "\"" + ":" + index;

		if (userId == null) {
			json += "," + "\"" + "userId" + "\"" + ":" + "null";
		} else {
			json += "," + "\"" + "userId" + "\"" + ":" + "\"" + userId + "\"";
		}

		if(name == null) {
			json += ","  + "\"" + "name" + "\"" + ":"  + "null";
		} else {
			json += ","  + "\"" + "name" + "\"" + ":"  + "\"" + name + "\"";
		}

		if(email == null) {
			json += ","  + "\"" + "email" + "\"" + ":" + "null";
		} else {
			json += ","  + "\"" + "email" + "\"" + ":"  + "\"" + email + "\"";
		}

		if(pictureURL == null) {
			json += ","  + "\"" + "pictureURL" + "\"" + ":"  + "null";
		} else {
			json += ","  + "\"" + "pictureURL" + "\"" + ":"  + "\"" + pictureURL + "\"";
		}

		json += "}";

		return json;
	}
}
