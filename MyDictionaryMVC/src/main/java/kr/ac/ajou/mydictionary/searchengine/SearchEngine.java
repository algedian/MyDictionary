package kr.ac.ajou.mydictionary.searchengine;

import java.util.ArrayList;

import kr.ac.ajou.mydictionary.document.DocumentModel;
import kr.ac.ajou.mydictionary.user.UserModel;

public interface SearchEngine {
	
	/**
	 * userId와 keyword가 주어지면 dictionary DB에서 해당 document를 반환하는 함수
	 * 결과가 없으면, null 리턴
	 * 
	 * @param userId
	 * @param keyword
	 * @return DocumentModel - fail: null
	 */
	public DocumentModel getUserDocument(String userId, String keyword);
	
	/**
	 * 친구들에 대한 리스트와 keyword가 주어지면 내 친구들 중에서 해당 keyword를 가진 document 들을 반환하는 함수
	 * 결과가 없으면, 빈 어레이 리턴
	 * 
	 * @param friends
	 * @param keyword
	 * @return ArrayList<DocumentModel> - fail:ArrayList<DocumentModel> (empty)
	 */
	public ArrayList<DocumentModel> getFriendDocuments(ArrayList<UserModel> friends, String keyword);
	
	//한 사람의 모든 도큐먼트를 긁어오는 건 필요없나 ?
}
