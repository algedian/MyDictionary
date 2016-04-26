package kr.ac.ajou.mydictionary.searchengine;

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
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.Assert;
import kr.ac.ajou.mydictionary.dictionarydata.Dictionary;
import kr.ac.ajou.mydictionary.dictionarydata.DictionaryDataFacade;
import kr.ac.ajou.mydictionary.dictionarymanager.AppConfig;
import kr.ac.ajou.mydictionary.document.DocumentModel;
import kr.ac.ajou.mydictionary.user.UserModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class SearchEngineTest extends AbstractJUnit4SpringContextTests {
	public static final Logger logger = LoggerFactory.getLogger(SearchEngineTest.class);

	@Resource(name = "searchEngine")
	private SearchEngine searchEngine;

	@Resource(name = "dictionaryDataBaseFacade")
	DictionaryDataFacade dictionaryDataFacade;

	private static final String ESCAPE = "__";

	String userId = "testUser";
	String userName = "testName";
	String userEmail = "testEmail";
	String keyword = "testKeyword";
	String document = "testDocument";

	ArrayList<UserModel> friends;
	ArrayList<Dictionary> dictionaries;
	ArrayList<DocumentModel> expected;

	private static final int MAX_SIZE = 5;

	protected Dictionary castDictionary(DocumentModel document) {
		return new Dictionary(document.getUserId() + ESCAPE + document.getKeyword(), document.getCreateTime(),
				document.getUpdateTime(), document.getDocument());
	}

	protected DocumentModel castDocument(Dictionary dictionary) {
		String str[] = dictionary.getKey().split(ESCAPE);
		DocumentModel dm = new DocumentModel(str[0], str[1], dictionary.getCreateTime(), dictionary.getUpdateTime(),
				dictionary.getDocument());

		return dm;
	}

	@Before
	public void setUp() {
		friends = new ArrayList<UserModel>();
		dictionaries = new ArrayList<Dictionary>();
		expected = new ArrayList<DocumentModel>();

		for(int i = 0; i < MAX_SIZE; i++) {
			friends.add(new UserModel(0, userId + i, userName, userEmail, null));

			Dictionary dictionary = new Dictionary(userId + i + ESCAPE + keyword, new Date(), null, document + i);
			expected.add(castDocument(dictionary));
			dictionaries.add(dictionary);
		}
		dictionaries.add(new Dictionary(userId + userId + ESCAPE + keyword, new Date(), null, document));
		dictionaries.add(new Dictionary(userId + ESCAPE + keyword + keyword, new Date(), null, document));
		dictionaries.add(new Dictionary(userId + userId + ESCAPE + keyword + keyword, new Date(), null, document));

		for(Dictionary dictionary : dictionaries) {
			dictionaryDataFacade.setDictionary(dictionary);
		}
	}

	@After
	public void cleanUp() {
		for(Dictionary dictionary : dictionaries) {
			dictionaryDataFacade.deleteDictionaryByKey(dictionary.getKey());
		}
	}

	@Test
	public void getUserDocumentTest() {
		Assert.assertEquals(searchEngine.getUserDocument(userId + 0, keyword).toString(), castDocument(dictionaries.get(0)).toString());
		Assert.assertNull(searchEngine.getUserDocument(userId + MAX_SIZE, keyword));
		Assert.assertEquals(searchEngine.getUserDocument(userId, keyword + MAX_SIZE), null);
	}

	@Test
	public void getFriendDocumentsTest() {
		ArrayList<DocumentModel> result = searchEngine.getFriendDocuments(friends, keyword);
		Assert.assertEquals(result.toString(), expected.toString());

		result = searchEngine.getFriendDocuments(friends, keyword + keyword);
		Assert.assertEquals(result.toString(), new ArrayList<DocumentModel>().toString());
	}
}
