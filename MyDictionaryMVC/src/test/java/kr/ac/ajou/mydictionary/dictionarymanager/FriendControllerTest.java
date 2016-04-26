package kr.ac.ajou.mydictionary.dictionarymanager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.annotation.Resource;

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

import kr.ac.ajou.mydictionary.dictionarymanager.model.FriendModel;
import kr.ac.ajou.mydictionary.user.UserModel;
import kr.ac.ajou.mydictionary.userdata.UserDataFacade;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@EnableWebMvc
@ContextConfiguration(classes = AppConfig.class)
public class FriendControllerTest extends AbstractJUnit4SpringContextTests {
	public static final Logger logger = LoggerFactory.getLogger(FriendControllerTest.class);

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private static final String FOLLOW_URL = "/friend/followFriend";
	private static final String UNFOLLOW_URL = "/friend/unfollowFriend";
	private static final String GET_FRIENDS_URL = "/friend/getFriendsByUserIndex";

	// @Autowired
	// private WebApplicationContext wac;

	@Autowired
	private FriendController friendController;

	@Resource(name = "userDataBaseFacade")
	private UserDataFacade userDataFacade;

	private MockMvc mockMvc;

	UserModel[] testUser;
	static final int MAX_SIZE = 5;

	public FriendControllerTest() {
		super();
	}

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(friendController).build();
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

	protected String toJson(FriendModel model) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

		return ow.writeValueAsString(model);
	}

	protected void sendPost(String URL, FriendModel friendModel, String expected) throws Exception {
		ResultActions resultAction = mockMvc.perform(post(URL).content(toJson(friendModel)).contentType(
				APPLICATION_JSON_UTF8));
		// resultAction.andDo(MockMvcResultHandlers.print());
		resultAction.andExpect(status().isOk());
		resultAction.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		resultAction.andExpect(content().string(expected));
	}

	@Test
	public void followFriendTest() {
		try {
			FriendModel friendModel = new FriendModel(-10, -1);
			sendPost(FOLLOW_URL, friendModel, "fail");

			friendModel = new FriendModel(-10, testUser[0].getIndex());
			sendPost(FOLLOW_URL, friendModel, "fail");

			friendModel = new FriendModel(testUser[0].getIndex(), -100);
			sendPost(FOLLOW_URL, friendModel, "fail");

			friendModel = new FriendModel(testUser[0].getIndex(), testUser[1].getIndex());
			sendPost(FOLLOW_URL, friendModel, "success");

			sendPost(FOLLOW_URL, friendModel, "fail");

			sendPost(UNFOLLOW_URL, friendModel, "success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void unfollowFriendTest() {
		try {
			FriendModel friendModel = new FriendModel(-10, -1);
			sendPost(UNFOLLOW_URL, friendModel, "fail");

			friendModel = new FriendModel(-10, testUser[0].getIndex());
			sendPost(UNFOLLOW_URL, friendModel, "fail");

			friendModel = new FriendModel(testUser[0].getIndex(), -100);
			sendPost(UNFOLLOW_URL, friendModel, "fail");

			friendModel = new FriendModel(testUser[0].getIndex(), testUser[1].getIndex());

			/* It covers in 'followFriendTest' testcase */
			// sendPost(unfollowURL, friendModel, "success");

			sendPost(UNFOLLOW_URL, friendModel, "fail");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* testcase */
	// case 1: 2,3,4,5
	// 		2: 3,5
	// 		3: 1,2
	// 		4: 2
	// 		5: no friend
	@Test
	public void getFriendsByUserIndexTest() {
		try {
			ArrayList<UserModel> expecte = new ArrayList<UserModel>();

			FriendModel friendModel = new FriendModel(-10, -1);
			// If there is no index, no expectation..
			sendPost(GET_FRIENDS_URL, friendModel, expecte.toString());

			makeData();

			String expected = "[" + testUser[1].toJson();
			expected += "," + testUser[2].toJson();
			expected += "," + testUser[3].toJson();
			expected += "," + testUser[4].toJson() + "]";
			friendModel = new FriendModel(testUser[0].getIndex(), -100);
			sendPost(GET_FRIENDS_URL, friendModel, expected);

			expected = "[" + testUser[2].toJson();
			expected += "," + testUser[4].toJson() + "]";
			friendModel = new FriendModel(testUser[1].getIndex(), -100);
			sendPost(GET_FRIENDS_URL, friendModel, expected);

			expected = "[" + testUser[0].toJson();
			expected += "," + testUser[1].toJson() + "]";
			friendModel = new FriendModel(testUser[2].getIndex(), -100);
			sendPost(GET_FRIENDS_URL, friendModel, expected);

			expecte.clear();
			expected = "[" + testUser[1].toJson() + "]";
			friendModel = new FriendModel(testUser[3].getIndex(), -100);
			sendPost(GET_FRIENDS_URL, friendModel, expected);

			expected = "[" + "]";
			friendModel = new FriendModel(testUser[4].getIndex(), -100);
			sendPost(GET_FRIENDS_URL, friendModel, expected);

			cleanData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void makeData() throws Exception {
		sendPost(FOLLOW_URL, new FriendModel(testUser[0].getIndex(), testUser[1].getIndex()), "success");
		sendPost(FOLLOW_URL, new FriendModel(testUser[0].getIndex(), testUser[2].getIndex()), "success");
		sendPost(FOLLOW_URL, new FriendModel(testUser[0].getIndex(), testUser[3].getIndex()), "success");
		sendPost(FOLLOW_URL, new FriendModel(testUser[0].getIndex(), testUser[4].getIndex()), "success");

		sendPost(FOLLOW_URL, new FriendModel(testUser[1].getIndex(), testUser[2].getIndex()), "success");
		sendPost(FOLLOW_URL, new FriendModel(testUser[1].getIndex(), testUser[4].getIndex()), "success");

		sendPost(FOLLOW_URL, new FriendModel(testUser[2].getIndex(), testUser[0].getIndex()), "success");
		sendPost(FOLLOW_URL, new FriendModel(testUser[2].getIndex(), testUser[1].getIndex()), "success");

		sendPost(FOLLOW_URL, new FriendModel(testUser[3].getIndex(), testUser[1].getIndex()), "success");
	}

	protected void cleanData() throws Exception {
		sendPost(UNFOLLOW_URL, new FriendModel(testUser[0].getIndex(), testUser[1].getIndex()), "success");
		sendPost(UNFOLLOW_URL, new FriendModel(testUser[0].getIndex(), testUser[2].getIndex()), "success");
		sendPost(UNFOLLOW_URL, new FriendModel(testUser[0].getIndex(), testUser[3].getIndex()), "success");
		sendPost(UNFOLLOW_URL, new FriendModel(testUser[0].getIndex(), testUser[4].getIndex()), "success");

		sendPost(UNFOLLOW_URL, new FriendModel(testUser[1].getIndex(), testUser[2].getIndex()), "success");
		sendPost(UNFOLLOW_URL, new FriendModel(testUser[1].getIndex(), testUser[4].getIndex()), "success");

		sendPost(UNFOLLOW_URL, new FriendModel(testUser[2].getIndex(), testUser[0].getIndex()), "success");
		sendPost(UNFOLLOW_URL, new FriendModel(testUser[2].getIndex(), testUser[1].getIndex()), "success");

		sendPost(UNFOLLOW_URL, new FriendModel(testUser[3].getIndex(), testUser[1].getIndex()), "success");
	}
}
