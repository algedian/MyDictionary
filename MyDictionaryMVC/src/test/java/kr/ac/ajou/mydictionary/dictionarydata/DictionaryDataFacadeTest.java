package kr.ac.ajou.mydictionary.dictionarydata;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DictionaryDataConfig.class })
public class DictionaryDataFacadeTest {
	public static final Logger logger = LoggerFactory.getLogger(DictionaryDataFacadeTest.class);

	@Resource(name = "dictionaryDataBaseFacade")
	DictionaryDataFacade repo;

	private static final String ESCAPE = "__";

	String userId;
	String keyword;
	String document;

	Dictionary dictionary;

	@Before
	public void makeData() {
		userId = "userId";
		keyword = "keyword";
		document = "document";

		dictionary = new Dictionary(userId + ESCAPE + keyword, new Date(), null, document);

		setDictionaryTest();
	}

	@After
	public void cleanUp() {
		deleteDictionaryByKeyTest();
	}

	public void setDictionaryTest() {
		repo.setDictionary(dictionary);
	}

	@Test
	public void getDictionaryByKeyTest() {
		Dictionary result = repo.getDictionaryByKey(dictionary.getKey());
		Assert.assertEquals(result.toString(), dictionary.toString());

		result = repo.getDictionaryByKey("some");
		Assert.assertEquals(result, null);
	}

	@Test
	public void getDictionaryByKeysTest() {
		ArrayList<Dictionary> expected = new ArrayList<Dictionary>();
		ArrayList<Dictionary> dictionaries = new ArrayList<Dictionary>();
		for(int i = 0; i < 5; i++) {
			Dictionary dictionary = new Dictionary(userId + i + ESCAPE + keyword, new Date(), null, document);
			expected.add(dictionary);
			dictionaries.add(dictionary);
		}
		dictionaries.add(new Dictionary(userId + 1 + ESCAPE + keyword + "afs", new Date(), null, document));
		dictionaries.add(new Dictionary(userId + 1 + ESCAPE + "afs" + keyword, new Date(), null, document));

		for(Dictionary dictionary : dictionaries) {
			repo.setDictionary(dictionary);
		}

		ArrayList<String> keyArrary = new ArrayList<String>();
		for(Dictionary dictionary : expected) {
			keyArrary.add(dictionary.getKey());
		}

		ArrayList<Dictionary> result = repo.getDictionaryByKeys(keyArrary);
		Assert.assertEquals(result.toString(), expected.toString());

		for(Dictionary dictionary : dictionaries) {
			repo.deleteDictionaryByKey(dictionary.getKey());
		}
		
		result = repo.getDictionaryByKeys(keyArrary);
		expected.clear();
		Assert.assertEquals(result.toString(), expected.toString());
	}

	@Test
	public void updateDictionaryByKeyTest() {
		Dictionary test = new Dictionary(userId + "111" + ESCAPE + keyword, new Date(), null, document);
		Dictionary replacer = new Dictionary(userId + "111" + ESCAPE + keyword, null, new Date(), document + "2353252");

		repo.setDictionary(test);
		repo.updateDictionary(replacer);

		replacer.setCreateTime(test.getCreateTime());

		Assert.assertEquals(repo.getDictionaryByKey(replacer.getKey()).toString(), replacer.toString());

		repo.deleteDictionaryByKey(replacer.getKey());
	}

	public void deleteDictionaryByKeyTest() {
		repo.deleteDictionaryByKey(dictionary.getKey());
	}

	@Test
	public void countByKeyTest() {
		long result = repo.countByKey(dictionary.getKey());
		Assert.assertEquals(result, 1);
	}

	@Test
	public void countAllTest() {
		long result = repo.countAll();
		Assert.assertEquals(result, 1);
	}
}
