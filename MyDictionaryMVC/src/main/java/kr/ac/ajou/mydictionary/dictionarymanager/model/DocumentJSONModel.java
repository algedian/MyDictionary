package kr.ac.ajou.mydictionary.dictionarymanager.model;

public class DocumentJSONModel {
	private String userId;
	private String keyword;

	private String document;

	public DocumentJSONModel() {
		super();
	}

	public DocumentJSONModel(String userId, String keyword, String document) {
		super();
		this.userId = userId;
		this.keyword = keyword;
		this.document = document;
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

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

}
