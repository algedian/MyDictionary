package kr.ac.ajou.mydictionary.dictionarydata;

public interface DictionaryDataFacade {
	public void setDictionary(String userId, String keyword, String document);
	public Dictionary getDictionary(String userId, String keyword);
	public void deleteDictionary(String userId, String keyword);
	
	public long countByUserId(String userId);
	public long countAll();
}
