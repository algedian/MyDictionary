package kr.ac.ajou.mydictionary.dictionarydata;

import java.util.ArrayList;


public interface DictionaryDataFacade {
	/**
	 * Sets dictionary using given dictionary
	 *
	 * @param dictionary
	 */
	public void setDictionary(Dictionary dictionary);

	/**
	 * Gets Dictionary from MongoDB <br><br>
	 *
	 * key format: userId + ESCAPE + keyword
	 *
	 * @param key
	 * @return Dictionary
	 */
	public Dictionary getDictionaryByKey(String key);

	/**
	 * Gets Dictionaries from MongoDB using OR operation  <br><br>
	 *
	 * key format: userId + ESCAPE + keyword
	 *
	 * @param key
	 * @return ArrayList<Dictionary>
	 */
	public ArrayList<Dictionary> getDictionaryByKeys(ArrayList<String> key);

	/**
	 * Updates a dictionary by key<br><br>
	 *
	 * key format: userId + ESCAPE + keyword
	 *
	 * @param key
	 * @param dictionary
	 */
	public void updateDictionary(Dictionary dictionary);

	/**
	 * Deletes a dictionary by key <br><br>
	 *
	 * key format: userId + ESCAPE + keyword
	 *
	 * @param key
	 */
	public void deleteDictionaryByKey(String key);

	/**
	 * The number of key
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
