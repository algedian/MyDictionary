package kr.ac.ajou.mydictionary.document;

import javax.annotation.Resource;

import kr.ac.ajou.mydictionary.dictionarydata.Dictionary;
import kr.ac.ajou.mydictionary.dictionarydata.DictionaryDataFacade;

import org.springframework.stereotype.Service;

@Service("documentService")
public class DocumentServiceImpl implements DocumentService {
	private static final String ESCAPE = "__";

	@Resource(name = "dictionaryDataBaseFacade")
	private DictionaryDataFacade dictionaryDataFacade;

	/**
	 * Cast DocumentModel to Dictionary
	 *
	 * @param document
	 *            - DocumentModel object
	 * @return casted Dictonary object
	 */
	protected Dictionary castDictionary(DocumentModel document) {
		if (document != null) {
			return new Dictionary(makeKey(document.getUserId(), document.getKeyword()), document.getCreateTime(),
					document.getUpdateTime(), document.getDocument());
		} else {
			return null;
		}

	}

	/**
	 * Cast Dictionary to DocumentModel
	 *
	 * @param dictionary
	 *            - Dictionary object
	 * @return casted documentModel object
	 */
	protected DocumentModel castDocumentModel(Dictionary dictionary) {
		if (dictionary != null) {
			String str[] = dictionary.getKey().split(ESCAPE);
			DocumentModel dm = new DocumentModel(str[0], str[1], dictionary.getCreateTime(),
					dictionary.getUpdateTime(), dictionary.getDocument());

			return dm;
		} else {
			return null;
		}
	}

	/**
	 * Make a key with userId and keyword format is 'userID + ESCAPE_word +
	 * keyword'
	 *
	 * @param userId
	 * @param keyword
	 * @return String
	 */
	protected String makeKey(String userId, String keyword) {
		return userId + ESCAPE + keyword;
	}

	/**
	 * @author universe
	 * @date 2016. 4. 24. 오후 10:53:15
	 *
	 * @param userId
	 * @param keyword
	 * @param document
	 * @return
	 */
	@Override
	public boolean setDocument(DocumentModel documentModel) {
		Dictionary dictionary = castDictionary(documentModel);
		Dictionary selectResult = dictionaryDataFacade.getDictionaryByKey(dictionary.getKey());

		//
		if (selectResult != null) {
			dictionary.setUpdateTime(dictionary.getCreateTime());
			dictionary.setCreateTime(selectResult.getCreateTime());

			dictionaryDataFacade.setDictionary(dictionary);

			return true;
		} else {
			dictionaryDataFacade.setDictionary(dictionary);

			return true;
		}
		// unreachable
		// return false;
	}

	/* (non-Javadoc)
	 * @see kr.ac.ajou.mydictionary.document.DocumentService#deleteDocument(kr.ac.ajou.mydictionary.document.DocumentModel)
	 */
	/**
	 * @author universe
	 * @date 2016. 4. 24. 오후 11:13:37
	 *
	 * @param documentModel
	 * @return
	 */
	@Override
	public boolean deleteDocument(DocumentModel documentModel) {
		Dictionary dictionary = castDictionary(documentModel);
		Dictionary result = dictionaryDataFacade.getDictionaryByKey(dictionary.getKey());
		
		// 
		if(result != null) {
			dictionaryDataFacade.deleteDictionaryByKey(result.getKey());
			return true;
		}
		return false;
	}
}
