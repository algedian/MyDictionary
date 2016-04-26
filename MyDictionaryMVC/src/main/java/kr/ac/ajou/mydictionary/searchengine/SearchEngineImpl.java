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
	private DictionaryDataFacade dictionaryDataFacade;

	public SearchEngineImpl() {
		super();
	}

	protected Dictionary castDictionary(DocumentModel document) {
		if (document != null) {
			return new Dictionary(makeKey(document.getUserId(), document.getKeyword()), document.getCreateTime(),
					document.getUpdateTime(), document.getDocument());
		} else {
			return null;
		}

	}

	protected DocumentModel castDocument(Dictionary dictionary) {
		if (dictionary != null) {
			String str[] = dictionary.getKey().split(ESCAPE);
			DocumentModel dm = new DocumentModel(str[0], str[1], dictionary.getCreateTime(),
					dictionary.getUpdateTime(), dictionary.getDocument());

			return dm;
		} else {
			return null;
		}
	}

	protected String makeKey(String userId, String keyword) {
		return userId + ESCAPE + keyword;
	}

	@Override
	public DocumentModel getUserDocument(String userId, String keyword) {
		Dictionary dictionary = dictionaryDataFacade.getDictionaryByKey(makeKey(userId, keyword));
		DocumentModel dm = castDocument(dictionary);

		return dm;
	}

	@Override
	public ArrayList<DocumentModel> getFriendDocuments(ArrayList<UserModel> friends, String keyword) {
		ArrayList<DocumentModel> documentModelList = new ArrayList<DocumentModel>();
		ArrayList<String> keyArray = new ArrayList<String>();

		for (UserModel um : friends) {
			keyArray.add(makeKey(um.getUserId(), keyword));
		}

		ArrayList<Dictionary> result = dictionaryDataFacade.getDictionaryByKeys(keyArray);
		for (Dictionary dictionary : result) {
			documentModelList.add(castDocument(dictionary));
		}

		return documentModelList;
	}
}
