package kr.ac.ajou.mydictionary.friend;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.ac.ajou.mydictionary.dictionarymanager.AppConfig;
import kr.ac.ajou.mydictionary.user.UserModel;
import kr.ac.ajou.mydictionary.userdata.UserDataFacade;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class FriendServiceTest extends AbstractJUnit4SpringContextTests {
	public static final Logger logger = LoggerFactory.getLogger(FriendServiceTest.class);

	@Resource(name = "friendService")
	private FriendService friendService;

	@Resource(name = "userDataBaseFacade")
	private UserDataFacade userDataFacade;

	UserModel[] testUser;
	UserModel fakeUser;
	static final int MAX_SIZE = 5;

	@Before
	public void setUp() {
		testUser = new UserModel[MAX_SIZE];

		for (int i = 0; i < MAX_SIZE; i++) {
			testUser[i] = new UserModel(i, "testUser" + i, "name" + i, "testUser" + i + "@gmail.com", null);
			userDataFacade.insertUser(testUser[i]);
			testUser[i] = userDataFacade.selectUserById(testUser[i].getUserId());
		}

		fakeUser = new UserModel(-100, "fakeUserId", "fakeUserName", "fakeUserEmail", "fakeUserPictureURL");
	}

	@After
	public void cleanUp() {
		for (UserModel userModel : testUser) {
			userDataFacade.deleteUserById(userModel.getUserId());
		}
	}

	@Test
	public void followAndUnfollowTest() {
		followFriendTest();
		unfollowFriendTest();
	}

	public void followFriendTest() {
		// Check whether user exists
		assertEquals(friendService.followFriend(fakeUser.getIndex(), testUser[0].getIndex()), false);
		assertEquals(friendService.followFriend(testUser[0].getIndex(), fakeUser.getIndex()), false);

		assertEquals(friendService.followFriend(testUser[0].getIndex(), testUser[1].getIndex()), true);

		// Check whether user follows/unfollows
		assertEquals(friendService.followFriend(testUser[0].getIndex(), testUser[1].getIndex()), false);
	}

	public void unfollowFriendTest() {
		assertEquals(friendService.unfollowFriend(fakeUser.getIndex(), testUser[0].getIndex()), false);
		assertEquals(friendService.unfollowFriend(testUser[0].getIndex(), fakeUser.getIndex()), false);

		// Check whether user follows/unfollows
		assertEquals(friendService.unfollowFriend(testUser[1].getIndex(), testUser[0].getIndex()), false);

		assertEquals(friendService.unfollowFriend(testUser[0].getIndex(), testUser[1].getIndex()), true);
	}

	@Test
	public void getFriendListTest() {
		ArrayList<UserModel> expected = new ArrayList<UserModel>();

		for(int i = 1; i < MAX_SIZE; i++) {
			friendService.followFriend(testUser[0].getIndex(), testUser[i].getIndex());
			expected.add(testUser[i]);
		}

		ArrayList<UserModel> result = friendService.getFriendList(testUser[0].getIndex());
		assertEquals(result.toString(), expected.toString());

		for(int i = 1; i < MAX_SIZE; i++) {
			friendService.unfollowFriend(testUser[0].getIndex(), testUser[i].getIndex());
		}
	}
}
