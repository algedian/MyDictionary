package kr.ac.ajou.mydictionary.dictionarydata;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository("dictionaryDataBaseFacade")
public class DictionaryDataFacadeImpl implements DictionaryDataFacade {
	@Autowired
	private MongoTemplate mongo;

	@Override
	public void setDictionary(Dictionary dictionary) {
		mongo.save(dictionary);
	}

	@Override
	public Dictionary getDictionaryByKey(String key) {
		return mongo.findOne(Query.query(Criteria.where("key").is(key)), Dictionary.class);
	}

	@Override
	public ArrayList<Dictionary> getDictionaryByKeys(ArrayList<String> key) {
		return (ArrayList<Dictionary>) mongo.find(Query.query(Criteria.where("key").in(key)), Dictionary.class);
	}

	@Override
	public void updateDictionary(Dictionary dictionary) {
		Dictionary current = getDictionaryByKey(dictionary.getKey());
		current.setUpdateTime(dictionary.getUpdateTime());
		current.setDocument(dictionary.getDocument());

		mongo.save(current);
	}

	@Override
	public void deleteDictionaryByKey(String key) {
		mongo.remove(Query.query(Criteria.where("key").is(key)), Dictionary.class);
	}

	@Override
	public long countByKey(String key) {
		return mongo.count(Query.query(Criteria.where("key").regex(key)), Dictionary.class);
	}

	@Override
	public long countAll() {
		return mongo.findAll(Dictionary.class).size();
	}

	/*
	 * private String makeKey(String userId, String keyword) { return userId +
	 * ESCAPE + keyword; }
	 *
	 * private Query makeQueryByKey(String userId, String keyword) { return
	 * Query.query(Criteria.where("key").is(makeKey(userId, keyword))); }
	 *
	 * private Query makeQueryByUserId(String userId) { return
	 * Query.query(Criteria.where("key").regex(userId + ESCAPE + ".*")); }
	 */

	/*
	 * implements MongoRepository<Dictionary, String>
	 *
	 * @Override public boolean exists(String arg0) { return false; }
	 *
	 * @Override public List<Dictionary> findAll() { return null; }
	 *
	 * @Override public List<Dictionary> findAll(Sort arg0) { return null; }
	 *
	 * @Override public Page<Dictionary> findAll(Pageable arg0) { return null; }
	 *
	 * @Override public Dictionary findOne(String arg0) { return null; }
	 *
	 * @Override public Iterable<Dictionary> findAll(Iterable<String> arg0) {
	 * return null; }
	 *
	 * @Override public <S extends Dictionary> S insert(S arg0) { return null; }
	 *
	 * @Override public <S extends Dictionary> List<S> insert(Iterable<S> arg0)
	 * { return null; }
	 *
	 * @Override public <S extends Dictionary> List<S> save(Iterable<S> arg0) {
	 * return null; }
	 *
	 * @Override public <S extends Dictionary> S save(S arg0) { return null; }
	 *
	 * @Override public long count() {
	 *
	 * return 0; }
	 *
	 * @Override public void delete(String arg0) {
	 *
	 * }
	 *
	 * @Override public void delete(Dictionary arg0) {
	 *
	 * }
	 *
	 * @Override public void delete(Iterable<? extends Dictionary> arg0) {
	 *
	 * }
	 *
	 * @Override public void deleteAll() {
	 *
	 * }
	 */
}
