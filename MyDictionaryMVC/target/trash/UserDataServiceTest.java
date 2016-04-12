package kr.ac.ajou.mydictionary.userdb;

import kr.ac.ajou.mydictionary.userdb.config.UserDataConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { UserDataConfig.class })
public class UserDataServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(UserDataServiceTest.class);

	@Autowired
	UserDataFacadeImpl service;

	public void before() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(UserDataConfig.class);
		ctx.refresh();
		// service = new UserDataService(ctx.getBean(UserDataMapper.class));
	}

	@Test
	public void test() {
		try {
			String result = service.selectUser("dcoun");

			logger.info(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
