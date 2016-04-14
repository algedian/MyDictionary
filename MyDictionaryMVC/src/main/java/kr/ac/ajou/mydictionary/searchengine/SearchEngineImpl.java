package kr.ac.ajou.mydictionary.searchengine;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.ac.ajou.mydictionary.dictionarydata.Dictionary;
import kr.ac.ajou.mydictionary.dictionarydata.DictionaryDataFacade;
import kr.ac.ajou.mydictionary.document.DocumentModel;
import kr.ac.ajou.mydictionary.user.UserModel;

@Service("searchEngine")
public class SearchEngineImpl implements SearchEngine {
	private static final String ESCAPE = "__";
	
	@Resource(name = "dictionaryDataBaseFacade")
	DictionaryDataFacade dictionaryDataFacade;
	
	public SearchEngineImpl() {
		super();
	}
	
	@Override
	public void searchTest() {
		
	}
	
	public Dictionary castDictionary(DocumentModel document) {
		return new Dictionary(document.getUserId() + ESCAPE + document.getKeyword(), document.getCreateTime(), document.getUpdateTime(), document.getDocument());
	}
	
	public DocumentModel castDocument(Dictionary dictionary) {
		String str[] = dictionary.getKey().split(ESCAPE);
		DocumentModel dm = new DocumentModel(str[0], str[1], dictionary.getCreateTime(), dictionary.getDocument());
		dm.setUpdateTime(dictionary.getUpdateTime());
		
		return dm;
	}

	@Override
	public DocumentModel getUserDocument(String userId, String keyword) {
		String key = userId + ESCAPE + keyword;
		
		Dictionary dic = dictionaryDataFacade.getDictionaryByKey(key);
		DocumentModel dm = castDocument(dic);
		return dm;
	}

	@Override
	public ArrayList<DocumentModel> getFriendDocuments(ArrayList<UserModel> friends, String keyword) {
		ArrayList<DocumentModel> documentModelList = new ArrayList<DocumentModel>();
		for(UserModel um : friends){
			String key = um.getUserId() + ESCAPE + keyword;
			DocumentModel tempdm = castDocument(dictionaryDataFacade.getDictionaryByKey(key));
			documentModelList.add(tempdm);
		}
				
		return documentModelList;
	}
}
