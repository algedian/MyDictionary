package kr.ac.ajou.mydictionary.dictionarymanager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import kr.ac.ajou.mydictionary.dictionarymanager.model.FriendModel;

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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@EnableWebMvc
@ContextConfiguration(classes = AppConfig.class)
public class FriendControllerTest extends AbstractJUnit4SpringContextTests {
	public static final Logger logger = LoggerFactory.getLogger(FriendControllerTest.class);

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	private final String friendModelJson = "{\"userIndex\":%d,\"friendIndex\":\"%d\"}";
	private final String mediaType = "application/json;charset=UTF-8";

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	public FriendControllerTest() {
		super();
	}

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	public void followFriendTest() {
		logger.info("[followFriendTest]" + " - " + "run");
		try {
			FriendModel model = new FriendModel(0, 1);
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
			ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
			String request = ow.writeValueAsString(model);
			
			System.out.println(APPLICATION_JSON_UTF8);
			System.out.println(request);
			
			byte[] json = String.format(friendModelJson, 1, 2).getBytes();
			ResultActions resultAction = mockMvc.perform(post("/friend/followFriend").contentType(MediaType.APPLICATION_JSON).content(request));
					//.content(String.format(friendModelJson, 1, 2).getBytes())
					//.contentType(MediaType.parseMediaType("application/json;charset=UTF-8")));
			//.contentType(APPLICATION_JSON_UTF8)
			resultAction.andDo(MockMvcResultHandlers.print());
			//.andExpect(content().contentType("application/json;charset=UTF-8"));
			//
			// .andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
			// jsonPath("$.name").value("Lee")
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTest() {
		logger.info("[testTest]" + " - " + "run");
		try {
			FriendModel model = new FriendModel(0, 1);
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
			ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
			String request = ow.writeValueAsString(model.getUserIndex());
			
			System.out.println(APPLICATION_JSON_UTF8);
			System.out.println(request);
			
			String json = "{\"userIndex\":1}";
			
			ResultActions resultAction = mockMvc.perform(post("/friend/test").accept(MediaType.APPLICATION_JSON).content(json).contentType(MediaType.APPLICATION_JSON));
			resultAction.andDo(MockMvcResultHandlers.print());
			resultAction.andExpect(status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testtTest() {
		try {
			ResultActions resultAction = mockMvc.perform(post("/friend/ttest"));
			resultAction.andDo(MockMvcResultHandlers.print());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
