package kr.ac.ajou.mydictionary.dictionarymanager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import javax.annotation.Resource;

import kr.ac.ajou.mydictionary.dictionarymanager.model.FriendModel;
import kr.ac.ajou.mydictionary.user.UserModel;
import kr.ac.ajou.mydictionary.userdata.UserDataFacade;

import org.junit.After;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@EnableWebMvc
@ContextConfiguration(classes = AppConfig.class)
public class UserControllerTest extends AbstractJUnit4SpringContextTests {
	public static final Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private static final String GET_USER_URL = "/user/getUserByEmail";

	@Autowired
	private UserController userController;

	@Resource(name = "userDataBaseFacade")
	private UserDataFacade userDataFacade;

	private MockMvc mockMvc;
	UserModel[] testUser;
	static final int MAX_SIZE = 5;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
		// mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		makeUser();
	}

	protected void makeUser() {
		testUser = new UserModel[MAX_SIZE];
		for (int i = 0; i < MAX_SIZE; i++) {
			testUser[i] = new UserModel(i, "testUser" + i, "name" + i, "testUser" + i + "@gmail.com", null);
			userDataFacade.insertUser(testUser[i]);
			testUser[i] = userDataFacade.selectUserById(testUser[i].getUserId());
		}
	}

	@After
	public void cleanUp() {
		for (UserModel um : testUser) {
			userDataFacade.deleteUserById(um.getUserId());
		}
	}

	protected void sendPost(String URL, String json, String expected) throws Exception {
		ResultActions resultAction = mockMvc.perform(post(URL).content(json).contentType(
				APPLICATION_JSON_UTF8));
		// resultAction.andDo(MockMvcResultHandlers.print());
		resultAction.andExpect(status().isOk());
		resultAction.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		resultAction.andExpect(content().string(expected));
	}

	protected String toJson(FriendModel model) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

		return ow.writeValueAsString(model);
	}

	public void loginTest() {
		// 여긴 스텁에 대한 오버헤드가 너무 큼
	}

	@Test
	public void findUserByEmailTest() {
		try {
			String json = "{" + "\"" + "email" + "\"" + ":" + "\"" + testUser[0].getEmail() + "\"" + "}" ;
			sendPost(GET_USER_URL, json, testUser[0].toJson());
			
			json = "{" + "\"" + "email" + "\"" + ":" + "\"" + testUser[1].getEmail() + "\"" + "}" ;
			sendPost(GET_USER_URL, json, testUser[1].toJson());
			
			json = "{" + "\"" + "email" + "\"" + ":" + "\"" + testUser[2].getEmail() + "\"" + "}" ;
			sendPost(GET_USER_URL, json, testUser[2].toJson());
			
			json = "{" + "\"" + "email" + "\"" + ":" + "\"" + testUser[3].getEmail() + "\"" + "}" ;
			sendPost(GET_USER_URL, json, testUser[3].toJson());
			
			json = "{" + "\"" + "email" + "\"" + ":" + "\"" + testUser[4].getEmail() + "\"" + "}" ;
			sendPost(GET_USER_URL, json, testUser[4].toJson());
			
			json = "{" + "\"" + "email" + "\"" + ":" + "\"" + testUser[0].getEmail() + testUser[1].getEmail() + "\"" + "}" ;
			sendPost(GET_USER_URL, json, new UserModel().toJson());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
