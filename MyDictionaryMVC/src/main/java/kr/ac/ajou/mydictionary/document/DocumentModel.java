package kr.ac.ajou.mydictionary.document;

import java.util.Date;

public class DocumentModel {	
	private String userId;
	private String keyword;

	private Date createTime;
	private Date updateTime;

	private String document;

	public DocumentModel() {

	}

	public DocumentModel(String userId, String keyword, Date createTime, String document) {
		this.userId = userId;
		this.keyword = keyword;
		this.createTime = createTime;
		this.document = document;
	}

	public DocumentModel(String userId, String keyword, Date createTime, Date updateTime, String document) {
		this.userId = userId;
		this.keyword = keyword;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.document = document;
	}

	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append("{");
		buf.append("userId=" + userId);
		buf.append(", keyword=" + keyword);
		buf.append(", createTime=" + createTime);
		buf.append(", updateTime=" + updateTime);
		buf.append(", document=" + document);
		buf.append("}");
		
		return buf.toString();
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}
}
