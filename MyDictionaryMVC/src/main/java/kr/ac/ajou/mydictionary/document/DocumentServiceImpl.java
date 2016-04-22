package kr.ac.ajou.mydictionary.document;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.ac.ajou.mydictionary.dictionarydata.Dictionary;
import kr.ac.ajou.mydictionary.dictionarydata.DictionaryDataFacade;
import kr.ac.ajou.mydictionary.document.DocumentService;

@Service("documentService")
public class DocumentServiceImpl implements DocumentService {
	private static final String ESCAPE = "__";

	@Resource(name = "dictionaryDataBaseFacade")
	private DictionaryDataFacade dictionaryDataFacade;

	@Override
	public void documentSetTest() {

	}

	/**
	 * Cast DocumentModel to Dictionary
	 *
	 * @param document - DocumentModel object
	 * @return casted Dictonary object
	 */
	protected Dictionary castDictionary(DocumentModel document) {
		if (document != null) {
			return new Dictionary(document.getUserId() + ESCAPE + document.getKeyword(), document.getCreateTime(),
					document.getUpdateTime(), document.getDocument());
		} else {
			return null;
		}

	}

	/**
	 * Cast Dictionary to DocumentModel
	 *
	 * @param dictionary - Dictionary object
	 * @return casted documentModel object
	 */
	protected DocumentModel castDocumentModel(Dictionary dictionary) {
		if (dictionary != null) {
			String str[] = dictionary.getKey().split(ESCAPE);
			DocumentModel dm = new DocumentModel(str[0], str[1], dictionary.getCreateTime(), dictionary.getUpdateTime(),
					dictionary.getDocument());

			return dm;
		} else {
			return null;
		}
	}

	/**
	 * Make a key with userId and keyword
	 * format is 'userID + ESCAPE_word + keyword'
	 *
	 * @param userId
	 * @param keyword
	 * @return String
	 */
	protected String makeKey(String userId, String keyword) {
		return userId + ESCAPE + keyword;
	}

}
