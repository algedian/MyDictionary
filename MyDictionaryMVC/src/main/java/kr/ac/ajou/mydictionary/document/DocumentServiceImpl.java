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

	@Override
	public void documentSetTest() {

	}

	protected Dictionary castDictionary(DocumentModel document) {
		if (document != null) {
			return new Dictionary(document.getUserId() + ESCAPE + document.getKeyword(), document.getCreateTime(),
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

}
