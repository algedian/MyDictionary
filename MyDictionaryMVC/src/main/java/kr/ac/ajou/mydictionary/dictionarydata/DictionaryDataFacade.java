package kr.ac.ajou.mydictionary.dictionarydata;

import kr.ac.ajou.mydictionary.document.DocumentModel;

public interface DictionaryDataFacade {
	/**
	 * Sets dictionary
	 * 
	 * @param dictionary
	 */
	public void setDictionary(Dictionary dictionary);
	
	/**
	 * Gets DocumentModel from MongoDB <br><br>
	 * 
	 * key format: userId + ESCAPE + keyword 
	 * 
	 *  
	 * 
	 * @param key
	 * @return DocumentModel
	 */
	public DocumentModel getDictionaryByKey(String key);
	
	/**
	 * Updates a dictionary by key<br><br>
	 * 
	 * key format: userId + ESCAPE + keyword 
	 * 
	 * @param key
	 * @param dictionary
	 */
	public void updateDictionaryByKey(String key, Dictionary dictionary);
	
	/**
	 * Deletes a dictionary by key <br><br>
	 * 
	 * key format: userId + ESCAPE + keyword 
	 *  
	 * @param key
	 */
	public void deleteDictionaryByKey(String key);
	
		
	
	/**
	 * 같은 키워드를 가진 document의 개수 세기? <br><br>
	 * key format: userId + ESCAPE + keyword 
	 * 
	 * @param key
	 * @return long - 
	 */
	public long countByKey(String key);
	
	/**
	 * 갖고 있는 모든 document의 개수 세기? <br><br>
	 * @return long - 
	 */
	public long countAll();
}
