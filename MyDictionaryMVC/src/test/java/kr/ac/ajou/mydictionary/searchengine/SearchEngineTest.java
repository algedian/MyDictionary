package kr.ac.ajou.mydictionary.searchengine;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.Assert;
import kr.ac.ajou.mydictionary.dictionarydata.DictionaryDataConfig;
import kr.ac.ajou.mydictionary.document.DocumentModel;
import kr.ac.ajou.mydictionary.user.UserModel;
import kr.ac.ajou.mydictionary.userdata.UserDataConfig;
import kr.ac.ajou.mydictionary.userdata.UserDataFacade;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DictionaryDataConfig.class, UserDataConfig.class })
public class SearchEngineTest {
	public static final Logger logger = LoggerFactory.getLogger(SearchEngineTest.class);
		
	@Resource(name = "searchEngine")
	private SearchEngine se;
	
	@Resource(name = "userDataBaseFacade")
	UserDataFacade userDataFacade;
		
	
	@Test
	public void getFriendDocumentsTest() {
		UserModel testUser = new UserModel(0, "dcoun", "name", "dcoun08@gmail.com", null);
		UserModel friend = new UserModel(0, "borichyaa", "NAME", "mail", null);
		
		ArrayList<UserModel> friends = new ArrayList<UserModel>();
		friends.add(friend);
		String keyword = "keyword";
		
		
		ArrayList<DocumentModel> dmList = se.getFriendDocuments(friends, keyword);
		Assert.assertEquals(1, dmList.size());
		//System.out.println(dmList.get(0).getDocument());
		
	}
	
	@Test
	public void getUserDocumentTest(){
		String userId = "dcoun";
		String keyword = "keyword";
				
		DocumentModel dm = se.getUserDocument(userId, keyword);
		Assert.assertEquals("my_document", dm.getDocument());
	}
	
}
