/**
 * @project MyDictionaryMVC
 * @package kr.ac.ajou.mydictionary.dictionarymanager
 * @file SearchControllerTest.java
 * @author universe
 * @date 2016. 4. 24. 오후 11:10:36
 *
 */
package kr.ac.ajou.mydictionary.dictionarymanager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.Iterator;

import javax.annotation.Resource;

import junit.framework.Assert;
import kr.ac.ajou.mydictionary.dictionarymanager.model.DocumentJSONModel;
import kr.ac.ajou.mydictionary.dictionarymanager.model.FriendModel;
import kr.ac.ajou.mydictionary.dictionarymanager.model.SearchModel;
import kr.ac.ajou.mydictionary.user.UserModel;
import kr.ac.ajou.mydictionary.userdata.UserDataFacade;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @package kr.ac.ajou.mydictionary.dictionarymanager
 * @type SearchControllerTest.java
 * @author universe
 * @date 2016. 4. 24. 오후 11:10:36
 * @description
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@EnableWebMvc
@ContextConfiguration(classes = AppConfig.class)
public class DocumentSearchControllerTest extends AbstractJUnit4SpringContextTests {
	public static final Logger logger = LoggerFactory.getLogger(DocumentSearchControllerTest.class);

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Resource(name = "userDataBaseFacade")
	private UserDataFacade userDataFacade;

	@Autowired
	private SearchEngineController searchEngineController;

	@Autowired
	private DocumentController documentController;

	@Autowired
	private FriendController friendController;

	private MockMvc mockSearchController;
	private MockMvc mockDocumentController;
	private MockMvc mockFriendController;

	UserModel[] testUser;
	static final int MAX_SIZE = 5;

	@Before
	public void setUp() {
		mockSearchController = MockMvcBuilders.standaloneSetup(searchEngineController).build();
		mockDocumentController = MockMvcBuilders.standaloneSetup(documentController).build();
		// mockUserController =
		// MockMvcBuilders.standaloneSetup(userController).build();
		mockFriendController = MockMvcBuilders.standaloneSetup(friendController).build();
	}

	protected void makeData() {
		// 사용자 정의
		testUser = new UserModel[MAX_SIZE];
		for (int i = 0; i < MAX_SIZE; i++) {
			testUser[i] = new UserModel(i, "testUser" + i, "name" + i, "testUser" + i + "@gmail.com", null);
			userDataFacade.insertUser(testUser[i]);
			testUser[i] = userDataFacade.selectUserById(testUser[i].getUserId());
		}

		// 친구 정의
		// 0 = 1,2,3
		// 1 = 0, 2
		// 2 = 0, 1
		// 3 = 0
		// 4 = null
		try {
			friendRequest("/friend/followFriend", new FriendModel(testUser[0].getIndex(), testUser[1].getIndex()),
					"success");
			friendRequest("/friend/followFriend", new FriendModel(testUser[0].getIndex(), testUser[2].getIndex()),
					"success");
			friendRequest("/friend/followFriend", new FriendModel(testUser[0].getIndex(), testUser[3].getIndex()),
					"success");

			friendRequest("/friend/followFriend", new FriendModel(testUser[1].getIndex(), testUser[0].getIndex()),
					"success");
			friendRequest("/friend/followFriend", new FriendModel(testUser[1].getIndex(), testUser[2].getIndex()),
					"success");

			friendRequest("/friend/followFriend", new FriendModel(testUser[2].getIndex(), testUser[0].getIndex()),
					"success");
			friendRequest("/friend/followFriend", new FriendModel(testUser[2].getIndex(), testUser[1].getIndex()),
					"success");

			friendRequest("/friend/followFriend", new FriendModel(testUser[3].getIndex(), testUser[0].getIndex()),
					"success");

			// 친구 문서 정의
			for (int i = 0; i < MAX_SIZE; i++) {
				documentRequest("/document/setDocument", new DocumentJSONModel(testUser[i].getUserId(), "keyword",
						"document" + "keyword" + i), "success");
			}
			documentRequest("/document/setDocument", new DocumentJSONModel(testUser[0].getUserId(), "keyword" + 0,
					"document" + "keyword" + "00"), "success");
			documentRequest("/document/setDocument", new DocumentJSONModel(testUser[1].getUserId(), "keyword" + 0,
					"document" + "keyword" + "11"), "success");
			documentRequest("/document/setDocument", new DocumentJSONModel(testUser[2].getUserId(), "keyword" + 0,
					"document" + "keyword" + "22"), "success");
			documentRequest("/document/setDocument", new DocumentJSONModel(testUser[1].getUserId(), "keyword" + 11,
					"document" + "keyword" + "11"), "success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cleanUp() {
		// 친구 문서 destroy
		try {
			for (int i = 0; i < MAX_SIZE; i++) {
				documentRequest("/document/deleteDocument", new DocumentJSONModel(testUser[i].getUserId(), "keyword",
						"document" + "keyword" + i), "success");
			}

			documentRequest("/document/deleteDocument", new DocumentJSONModel(testUser[0].getUserId(), "keyword" + 0,
					"document" + "keyword" + "00"), "success");
			documentRequest("/document/deleteDocument", new DocumentJSONModel(testUser[1].getUserId(), "keyword" + 0,
					"document" + "keyword" + "11"), "success");
			documentRequest("/document/deleteDocument", new DocumentJSONModel(testUser[2].getUserId(), "keyword" + 0,
					"document" + "keyword" + "22"), "success");
			documentRequest("/document/deleteDocument", new DocumentJSONModel(testUser[1].getUserId(), "keyword" + 11,
					"document" + "keyword" + "11"), "success");
			
			friendRequest("/friend/unfollowFriend", new FriendModel(testUser[0].getIndex(), testUser[1].getIndex()),
					"success");
			friendRequest("/friend/unfollowFriend", new FriendModel(testUser[0].getIndex(), testUser[2].getIndex()),
					"success");
			friendRequest("/friend/unfollowFriend", new FriendModel(testUser[0].getIndex(), testUser[3].getIndex()),
					"success");

			friendRequest("/friend/unfollowFriend", new FriendModel(testUser[1].getIndex(), testUser[0].getIndex()),
					"success");
			friendRequest("/friend/unfollowFriend", new FriendModel(testUser[1].getIndex(), testUser[2].getIndex()),
					"success");

			friendRequest("/friend/unfollowFriend", new FriendModel(testUser[2].getIndex(), testUser[0].getIndex()),
					"success");
			friendRequest("/friend/unfollowFriend", new FriendModel(testUser[2].getIndex(), testUser[1].getIndex()),
					"success");

			friendRequest("/friend/unfollowFriend", new FriendModel(testUser[3].getIndex(), testUser[0].getIndex()),
					"success");
			
			for (int i = 0; i < MAX_SIZE; i++) {
				userDataFacade.deleteUserById(testUser[i].getUserId());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected String toJson(Object model) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		
		return ow.writeValueAsString(model);
	}

	protected void friendRequest(String URL, FriendModel friendModel, String expected) throws Exception {
		ResultActions resultAction = mockFriendController.perform(post(URL).content(toJson(friendModel)).contentType(
				APPLICATION_JSON_UTF8));
		// resultAction.andDo(MockMvcResultHandlers.print());
		resultAction.andExpect(status().isOk());
		resultAction.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		resultAction.andExpect(content().string(expected));
	}

	protected void documentRequest(String URL, DocumentJSONModel friendModel, String expected) throws Exception {
		ResultActions resultAction = mockDocumentController.perform(post(URL).content(toJson(friendModel)).contentType(
				APPLICATION_JSON_UTF8));
		// resultAction.andDo(MockMvcResultHandlers.print());
		resultAction.andExpect(status().isOk());
		resultAction.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		resultAction.andExpect(content().string(expected));
	}

	@Test
	public void getUserDocumentTest() {
		makeData();
		
		try {
			SearchModel searchModel = new SearchModel(testUser[0].getIndex(), testUser[0].getUserId(), "keyword");
			ResultActions resultAction = mockSearchController.perform(post("/search/getUserDocument").content(toJson(searchModel)).contentType(APPLICATION_JSON_UTF8));
			resultAction.andExpect(status().isOk());
			resultAction.andExpect(content().contentType(APPLICATION_JSON_UTF8));
			MvcResult result = resultAction.andReturn();
			String str = result.getResponse().getContentAsString();
			JSONObject json = (JSONObject) new JSONParser().parse(str);
			
			Assert.assertEquals(json.get("userId"), testUser[0].getUserId());
			Assert.assertEquals(json.get("keyword"), "keyword");
			Assert.assertEquals(json.get("document"), "documentkeyword0");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		cleanUp();
	}

	@Test
	public void getFriendDocumentsTest() {
		makeData();
		
		try {
			SearchModel searchModel = new SearchModel(testUser[0].getIndex(), testUser[0].getUserId(), "keyword");
			ResultActions resultAction = mockSearchController.perform(post("/search/getFriendDocuments").content(toJson(searchModel)).contentType(APPLICATION_JSON_UTF8));
			resultAction.andExpect(status().isOk());
			resultAction.andExpect(content().contentType(APPLICATION_JSON_UTF8));
			MvcResult result = resultAction.andReturn();
			String str = result.getResponse().getContentAsString();
			JSONArray jsonArray = (JSONArray) new JSONParser().parse(str);
			@SuppressWarnings("rawtypes")
			Iterator iter = jsonArray.iterator();
			
			int i = 1;
			while(iter.hasNext()) {
				JSONObject json = (JSONObject) iter.next();
				
				Assert.assertEquals(json.get("userId"), testUser[i].getUserId());
				Assert.assertEquals(json.get("keyword"), "keyword");
				Assert.assertEquals(json.get("document"), "documentkeyword" + i++);
			}
			
			searchModel = new SearchModel(testUser[0].getIndex(), testUser[0].getUserId(), "keyword0");
			resultAction = mockSearchController.perform(post("/search/getFriendDocuments").content(toJson(searchModel)).contentType(APPLICATION_JSON_UTF8));
			resultAction.andExpect(status().isOk());
			resultAction.andExpect(content().contentType(APPLICATION_JSON_UTF8));
			result = resultAction.andReturn();
			str = result.getResponse().getContentAsString();
			jsonArray = (JSONArray) new JSONParser().parse(str);
			@SuppressWarnings("rawtypes")
			Iterator it = jsonArray.iterator();
			
			JSONObject json = (JSONObject) it.next();
			Assert.assertEquals(json.get("userId"), testUser[1].getUserId());
			Assert.assertEquals(json.get("keyword"), "keyword0");
			Assert.assertEquals(json.get("document"), "documentkeyword11");
			
			json = (JSONObject) it.next();
			Assert.assertEquals(json.get("userId"), testUser[2].getUserId());
			Assert.assertEquals(json.get("keyword"), "keyword0");
			Assert.assertEquals(json.get("document"), "documentkeyword22");
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		cleanUp();
	}
	
	@Test
	public void setDocumentDuplicateTest() {
		makeData();
		try {
			SearchModel searchModel = new SearchModel(testUser[0].getIndex(), testUser[0].getUserId(), "keyword" + 0);
			ResultActions resultAction = mockSearchController.perform(post("/search/getUserDocument").content(toJson(searchModel)).contentType(APPLICATION_JSON_UTF8));
			resultAction.andExpect(status().isOk());
			resultAction.andExpect(content().contentType(APPLICATION_JSON_UTF8));
			JSONObject before = (JSONObject) new JSONParser().parse(resultAction.andReturn().getResponse().getContentAsString());
			
			documentRequest("/document/setDocument", new DocumentJSONModel(testUser[0].getUserId(), "keyword" + 0,
					"document" + "keyword" + "replacer"), "success");
	
			searchModel = new SearchModel(testUser[0].getIndex(), testUser[0].getUserId(), "keyword" + 0);
			resultAction = mockSearchController.perform(post("/search/getUserDocument").content(toJson(searchModel)).contentType(APPLICATION_JSON_UTF8));
			resultAction.andExpect(status().isOk());
			resultAction.andExpect(content().contentType(APPLICATION_JSON_UTF8));
			JSONObject after = (JSONObject) new JSONParser().parse(resultAction.andReturn().getResponse().getContentAsString());
			
			Assert.assertEquals(before.get("userId"), after.get("userId"));
			Assert.assertEquals(before.get("keyword"), after.get("keyword"));
			Assert.assertEquals(before.get("createTime"), after.get("createTime"));
			Assert.assertNotNull(after.get("updateTime"));
			Assert.assertEquals(after.get("document"), "document" + "keyword" + "replacer");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cleanUp();
	}
}