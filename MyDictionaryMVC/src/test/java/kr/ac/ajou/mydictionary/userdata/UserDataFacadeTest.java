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

//@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UserDataConfig.class)
public class UserDataFacadeTest extends AbstractJUnit4SpringContextTests {
	public static final Logger logger = LoggerFactory.getLogger(UserDataFacadeTest.class);
	
	@Resource(name = "userDataBaseFacade")
	UserDataFacade userDataFacade;
	
	@Test
	public void selectUserTest() {
		try {
			User user = new User(1, "dcoun", "dcoun08@gmail.com", null);

			assertEquals(userDataFacade.selectUser("dcoun"), user.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
