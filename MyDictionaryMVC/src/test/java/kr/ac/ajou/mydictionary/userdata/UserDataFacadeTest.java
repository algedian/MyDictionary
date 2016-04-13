package kr.ac.ajou.mydictionary.userdata;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UserDataConfig.class)
public class UserDataFacadeTest extends AbstractJUnit4SpringContextTests {
	public static final Logger logger = LoggerFactory.getLogger(UserDataFacadeTest.class);

	@Resource(name = "userDataBaseFacade")
	UserDataFacade userDataFacade;

	User testUser;
	User replaceUser;

	@Before
	public void setUp() {
		testUser = new User(0, "dcoun233", "dcoun08@gmail.com", null);
		replaceUser = new User(0, "dsfsdf", "dcoun0000@gmail.com", "sdalkjasg;lk");
	}

	@Test
	public void insertUserTest() {
		int result = userDataFacade.insertUser(testUser);
		assertEquals(result, 1);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void selectUserByIdTest() {
		User result = userDataFacade.selectUserById(testUser.getUserId());

		assertEquals(result.getUserId(), testUser.getUserId());
		assertEquals(result.getEmail(), testUser.getEmail());
		assertEquals(result.getPictureURL(), testUser.getPictureURL());

		result = userDataFacade.selectUserById("sdasd");
		assertEquals(result, null);
	}
	
	@Test
	public void selectUserByEmailTest() {
		User result = userDataFacade.selectUserByEmail(testUser.getEmail());
		
		assertEquals(result.getUserId(), testUser.getUserId());
		assertEquals(result.getEmail(), testUser.getEmail());
		assertEquals(result.getPictureURL(), testUser.getPictureURL());
		
		result = userDataFacade.selectUserByEmail("sdasd");
		assertEquals(result, null);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void updateUserByIdTest() {
		int result = userDataFacade.updateUserById(testUser.getUserId(), replaceUser);
		assertEquals(result, 1);
		
		User node = userDataFacade.selectUserByEmail(replaceUser.getEmail());
		assertEquals(node.getUserId(), replaceUser.getUserId());
		assertEquals(node.getEmail(), replaceUser.getEmail());
		assertEquals(node.getPictureURL(), replaceUser.getPictureURL());
	}
	
	@Test
	public void updateUserByEmailTest() {
		int result = userDataFacade.updateUserByEmail(replaceUser.getEmail(), testUser);
		assertEquals(result, 1);
		
		User node = userDataFacade.selectUserByEmail(testUser.getEmail());
		assertEquals(node.getUserId(), testUser.getUserId());
		assertEquals(node.getEmail(), testUser.getEmail());
		assertEquals(node.getPictureURL(), testUser.getPictureURL());
		
		// userDataFacade.deleteUserByEmail(testUser.getEmail());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void deleteUserByIdTest() {		
		int result = userDataFacade.deleteUserById(testUser.getUserId());
		assertEquals(result, 1);
		
		result = userDataFacade.deleteUserById(testUser.getUserId());
		assertEquals(result, 0);
	}
	
	@Test
	public void deleteUserByEmailTest() {
		userDataFacade.insertUser(testUser);
		
		int result = userDataFacade.deleteUserByEmail(testUser.getEmail());
		assertEquals(result, 1);
		
		result = userDataFacade.deleteUserByEmail(testUser.getEmail());
		assertEquals(result, 0);
	}
}
