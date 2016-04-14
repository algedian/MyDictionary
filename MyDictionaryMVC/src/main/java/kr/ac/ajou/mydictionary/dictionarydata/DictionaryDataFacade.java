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
	public Dictionary getDictionaryByKey(String key);
	
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
	 * 
	 * @param key
	 * @return
	 */
	public long countByKey(String key);
	
	/**
	 * The number of documents in dictionary DB. <br><br>
	 * @return long 
	 */
	public long countAll();
}
