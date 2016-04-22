package kr.ac.ajou.mydictionary.searchengine;

import java.util.ArrayList;

import kr.ac.ajou.mydictionary.document.DocumentModel;
import kr.ac.ajou.mydictionary.user.UserModel;

public interface SearchEngine {

	/**
	 * If there are given userId and keyword, then dictionary DB returns a Document
	 * else returns null
	 *
	 * @param userId
	 * @param keyword
	 * @return DocumentModel - success: DocumentModel / fail: null
	 */
	public DocumentModel getUserDocument(String userId, String keyword);

	/**
	 * If there are list about friends and keyword, then returns documents that are held by friends and equals to keyword
	 * else returns empty ArrayList
	 *
	 * @param friends
	 * @param keyword
	 * @return ArrayList<DocumentModel> - fail:empty ArrayList<DocumentModel>
	 */
	public ArrayList<DocumentModel> getFriendDocuments(ArrayList<UserModel> friends, String keyword);

}
