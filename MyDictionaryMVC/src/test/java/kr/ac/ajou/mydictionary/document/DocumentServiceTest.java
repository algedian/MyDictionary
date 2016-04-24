/**
 * @project MyDictionaryMVC
 * @package kr.ac.ajou.mydictionary.document
 * @file DocumentServiceTest.java
 * @author universe
 * @date 2016. 4. 24. 오후 11:11:01
 *
 */
package kr.ac.ajou.mydictionary.document;

import java.util.Date;

import javax.annotation.Resource;

import junit.framework.Assert;
import kr.ac.ajou.mydictionary.dictionarymanager.AppConfig;
import kr.ac.ajou.mydictionary.searchengine.SearchEngine;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @package kr.ac.ajou.mydictionary.document
 * @type DocumentServiceTest.java
 * @author universe
 * @date 2016. 4. 24. 오후 11:11:01
 * @description
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class DocumentServiceTest extends AbstractJUnit4SpringContextTests {
	public static final Logger logger = LoggerFactory.getLogger(DocumentServiceTest.class);

	@Resource(name = "documentService")
	private DocumentService documentService;
	
	@Resource(name = "searchEngine")
	private SearchEngine searchEngine;
	
	String userId = "testUser";
	String userName = "testName";
	String userEmail = "testEmail";
	String keyword = "testKeyword";
	String document = "testDocument";
	
	DocumentModel testDocument;
	
	@Before
	public void setUp() {
		testDocument = new DocumentModel(userId, keyword, new Date(), document);
	}
	
	@Test
	public void setDocumentTest() {
		boolean result = documentService.setDocument(testDocument);
		Assert.assertEquals(result, true);
		
		DocumentModel dm = searchEngine.getUserDocument(testDocument.getUserId(), testDocument.getKeyword());
		Assert.assertEquals(testDocument.toString(), dm.toString());
		
		Date createTime = testDocument.getCreateTime();
		Date updateTime = new Date();
		testDocument.setCreateTime(updateTime);
		
		result = documentService.setDocument(testDocument);
		Assert.assertEquals(result, true);
		
		dm = searchEngine.getUserDocument(testDocument.getUserId(), testDocument.getKeyword());
		testDocument.setCreateTime(createTime);
		testDocument.setUpdateTime(updateTime);
		Assert.assertEquals(testDocument.toString(), dm.toString());
		
		deleteDocumentTest();
	}
	
	public void deleteDocumentTest() {
		boolean result = documentService.deleteDocument(testDocument);
		Assert.assertEquals(result, true);
		DocumentModel dm = searchEngine.getUserDocument(testDocument.getUserId(), testDocument.getKeyword());
		Assert.assertNull(dm);
		
		result = documentService.deleteDocument(testDocument);
		Assert.assertEquals(result, false);
	}
}
