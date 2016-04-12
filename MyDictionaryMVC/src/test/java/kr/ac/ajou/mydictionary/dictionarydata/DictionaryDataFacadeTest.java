package kr.ac.ajou.mydictionary.dictionarydata;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DictionaryDataConfig.class })
public class DictionaryDataFacadeTest {
	public static final Logger logger = LoggerFactory.getLogger(DictionaryDataFacadeTest.class);

	@Resource(name = "dictionaryDataBaseFacade")
	DictionaryDataFacade repo;

	String userId;
	String keyword;
	String document;

	@Before
	public void makeData() {
		userId = "dcoun";
		keyword = "keyword";
		document = "documentasdfdsgsdg";
	}

	@Test
	public void insertTest() {
		repo.setDictionary(userId, keyword, document);
	}

	@Test
	public void selectTest() {
		Dictionary result = repo.getDictionary(userId, keyword);
		System.out.println(result.toString());
		Assert.assertEquals(result.getKey(), "dcoun" + "-" +"keyword");
		Assert.assertEquals(result.getDocument(), document);
	}
	
	@Test
	public void countByUserIdTest() {
		long result = repo.countByUserId(userId);
		System.out.println(result + "");
	}
	
	@Test
	public void deleteTest() {
		repo.deleteDictionary(userId, keyword);
	}
}
