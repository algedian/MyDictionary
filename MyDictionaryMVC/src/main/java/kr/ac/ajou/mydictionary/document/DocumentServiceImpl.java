package kr.ac.ajou.mydictionary.document;

import javax.annotation.Resource;

import kr.ac.ajou.mydictionary.dictionarydata.Dictionary;
import kr.ac.ajou.mydictionary.dictionarydata.DictionaryDataFacade;

import org.springframework.stereotype.Service;

@Service("documentService")
public class DocumentServiceImpl implements DocumentService {
	private static final String ESCAPE = "__";
	
	@Resource(name = "dictionaryDataBaseFacade")
	DictionaryDataFacade dictionaryDataFacade;

	@Override
	public void documentSetTest() {
		
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

}
