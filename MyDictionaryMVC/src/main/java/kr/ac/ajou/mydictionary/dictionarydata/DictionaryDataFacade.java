package kr.ac.ajou.mydictionary.dictionarydata;

import kr.ac.ajou.mydictionary.document.DocumentModel;

public interface DictionaryDataFacade {
	public void setDictionary(Dictionary dictionary);
	public DocumentModel getDictionaryByKey(String key);
	public void updateDictionaryByKey(String key, Dictionary dictionary);
	public void deleteDictionaryByKey(String key);
	
	public long countByKey(String key);
	public long countAll();
}
