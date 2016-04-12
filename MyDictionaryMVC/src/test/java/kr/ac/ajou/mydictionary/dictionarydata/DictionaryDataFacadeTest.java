package kr.ac.ajou.mydictionary.dictionarydata;

import javax.annotation.Resource;

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
	
	@Test
	public void insertTest() {
		repo.setDictionary("dcoun", "keyword", "document");
	}
	
	@Test
	public void selectTest() {
		String result = repo.getDictionary("dcoun", "keyword");
		logger.info(result);
	}
}
