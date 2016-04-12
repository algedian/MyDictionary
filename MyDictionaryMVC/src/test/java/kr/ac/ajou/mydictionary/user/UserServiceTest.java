package kr.ac.ajou.mydictionary.user;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {
	@Resource(name = "userService")
	private UserService userService;
	
	@Test
	public void loginTest() {
		try {
			userService.loginTest("dcoun");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
