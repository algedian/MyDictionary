package kr.ac.ajou.mydictionary.dictionarydata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository("dictionaryDataBaseFacade")
public class DictionaryDataFacadeImpl implements DictionaryDataFacade {
	private static final String ESCAPE = "__";
	
	@Autowired
	MongoTemplate mongo;

	private String makeKey(String userId, String keyword) {
		return userId + ESCAPE + keyword;
	}

	private Query makeQueryByKey(String userId, String keyword) {
		return Query.query(Criteria.where("key").is(makeKey(userId, keyword)));
	}
	
	private Query makeQueryByUserId(String userId) {
		return Query.query(Criteria.where("key").regex(userId + ESCAPE + ".*"));
	}

	@Override
	public void setDictionary(String userId, String keyword, String document) {
		Dictionary dictionary = new Dictionary(makeKey(userId, keyword), document);
		mongo.save(dictionary);
	}

	@Override
	public Dictionary getDictionary(String userId, String keyword) {
		return mongo.findOne(makeQueryByKey(userId, keyword), Dictionary.class);
	}

	@Override
	public void deleteDictionary(String userId, String keyword) {
		mongo.remove(makeQueryByKey(userId, keyword), Dictionary.class);
	}

	@Override
	public long countByUserId(String userId) {
		return mongo.count(makeQueryByUserId(userId), Dictionary.class);
	}

	@Override
	public long countAll() {
		return mongo.findAll(Dictionary.class).size();
	}

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
