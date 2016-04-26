package kr.ac.ajou.mydictionary.userdata;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.ac.ajou.mydictionary.user.UserModel;

//@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UserDataConfig.class)
public class AbstractUserDataFacadeTest extends AbstractJUnit4SpringContextTests {
	public static final Logger logger = LoggerFactory.getLogger(AbstractUserDataFacadeTest.class);

	@Resource(name = "userDataBaseFacade")
	UserDataFacade userDataFacade;

	UserModel[] testUser;
	UserModel replaceUser;

	static final int MAX_SIZE = 5;

	public void setUp() {
		testUser = new UserModel[MAX_SIZE];

		for(int i = 0; i < MAX_SIZE; i++) {
			testUser[i] = new UserModel(i, "testUser" + i, "name" + i, "testUser" + i + "@gmail.com", null);
		}
		replaceUser = new UserModel(0, "testUserReplace", "nameReplace","testUserReplace@gmail.com", "testPictureUrl");
	}

	public void cleanUp() {
		for(UserModel userModel : testUser) {
			userDataFacade.deleteUserById(userModel.getUserId());
		}
		userDataFacade.deleteUserById(replaceUser.getUserId());
	}

	@Test
	public void test() {
		assertEquals(0, 0);
	}
}
