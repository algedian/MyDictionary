package kr.ac.ajou.mydictionary.dictionarydata;

public interface DictionaryDataFacade {
	public void setDictionary(String userId, String keyword, String document);
	public String getDictionary(String userId, String keyword);
}
