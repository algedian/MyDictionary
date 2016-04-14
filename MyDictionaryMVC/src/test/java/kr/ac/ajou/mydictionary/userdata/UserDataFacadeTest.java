package kr.ac.ajou.mydictionary.userdata;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import kr.ac.ajou.mydictionary.user.UserModel;

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

	UserModel testUser;
	UserModel replaceUser;

	@Before
	public void setUp() {
		testUser = new UserModel(0, "dcoun233", "name", "dcoun08@gmail.com", null);
		replaceUser = new UserModel(0, "dsfsdf", "name22","dcoun0000@gmail.com", "sdalkjasg;lk");
	}

	public void insertUser(UserModel user) {
		int result = userDataFacade.insertUser(user);
		assertEquals(result, 1);
	}
	
	public void deleteUser(UserModel user) {
		int result = userDataFacade.deleteUserByEmail(user.getEmail());
		assertEquals(result, 1);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void selectUserByIdTest() {
		insertUser(testUser);
		
		UserModel result = userDataFacade.selectUserById(testUser.getUserId());

		assertEquals(result.getUserId(), testUser.getUserId());
		assertEquals(result.getEmail(), testUser.getEmail());
		assertEquals(result.getPictureURL(), testUser.getPictureURL());

		result = userDataFacade.selectUserById("sdasd");
		assertEquals(result, null);
		
		deleteUser(testUser);
	}
	
	@Test
	public void selectUserByEmailTest() {
		insertUser(testUser);
		UserModel result = userDataFacade.selectUserByEmail(testUser.getEmail());
		
		assertEquals(result.getUserId(), testUser.getUserId());
		assertEquals(result.getEmail(), testUser.getEmail());
		assertEquals(result.getPictureURL(), testUser.getPictureURL());
		
		result = userDataFacade.selectUserByEmail("sdasd");
		assertEquals(result, null);
		
		deleteUser(testUser);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void updateUserByIdTest() {
		insertUser(testUser);
		
		int result = userDataFacade.updateUserById(testUser.getUserId(), replaceUser);
		assertEquals(result, 1);
		
		UserModel node = userDataFacade.selectUserByEmail(replaceUser.getEmail());
		assertEquals(node.getUserId(), replaceUser.getUserId());
		assertEquals(node.getName(), replaceUser.getName());
		assertEquals(node.getEmail(), replaceUser.getEmail());
		assertEquals(node.getPictureURL(), replaceUser.getPictureURL());
		
		deleteUser(replaceUser);
	}
	
	@Test
	public void updateUserByEmailTest() {
		insertUser(replaceUser);
		
		int result = userDataFacade.updateUserByEmail(replaceUser.getEmail(), testUser);
		assertEquals(result, 1);
		
		UserModel node = userDataFacade.selectUserByEmail(testUser.getEmail());
		assertEquals(node.getUserId(), testUser.getUserId());
		assertEquals(node.getName(), testUser.getName());
		assertEquals(node.getEmail(), testUser.getEmail());
		assertEquals(node.getPictureURL(), testUser.getPictureURL());
		
		deleteUser(testUser);
	}

	/* 아래는 위에서 커버 가능 */
/*	@SuppressWarnings("deprecation")
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
	}*/
}
