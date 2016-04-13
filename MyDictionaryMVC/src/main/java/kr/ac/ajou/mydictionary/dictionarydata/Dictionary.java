package kr.ac.ajou.mydictionary.dictionarydata;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dictionary")
public class Dictionary {
	@Id
	private String key;

	private Date createTime;
	private Date updateTime;

	private String document;

	public Dictionary() {

	}

	public Dictionary(String key, String document) {
		this.key = key;
		createTime = new Date();
		updateTime = null;
		this.document = document;
	}

	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append("{key=" + key);
		buf.append(", createTime=" + createTime);
		buf.append(", updateTime=" + updateTime);
		buf.append(", document=" + document);
		buf.append("}");
		
		return buf.toString();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
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
