package kr.ac.ajou.mydictionary.userdata;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import kr.ac.ajou.mydictionary.user.UserModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FriendDataFacadeTest extends AbstractUserDataFacadeTest {
	@Override
	@Before
	public void setUp() {
		super.setUp();

		int i = 0;
		for (UserModel userModel : testUser) {
			userDataFacade.insertUser(userModel);
			testUser[i++] = userDataFacade.selectUserById(userModel.getUserId());
		}
		/* test cases */
		// 1 = 2,3,4
		// 2 = 1, 3
		// 3 = 1, 2
		// 4 = 1
		// 5 = null
		setFriend(testUser[0].getIndex(), testUser[1].getIndex());
		setFriend(testUser[0].getIndex(), testUser[2].getIndex());
		setFriend(testUser[0].getIndex(), testUser[3].getIndex());

		setFriend(testUser[1].getIndex(), testUser[2].getIndex());
	}

	@Override
	@After
	public void cleanUp() {
		deleteFriend(testUser[0].getIndex(), testUser[1].getIndex());
		deleteFriend(testUser[0].getIndex(), testUser[2].getIndex());
		deleteFriend(testUser[0].getIndex(), testUser[3].getIndex());

		deleteFriend(testUser[1].getIndex(), testUser[2].getIndex());

		super.cleanUp();
	}

	public void setFriend(int userIndex, int friendIndex) {
		assertEquals(userDataFacade.insertFriendByIndex(userIndex, friendIndex), 1);
		assertEquals(userDataFacade.insertFriendByIndex(friendIndex, userIndex), 1);
	}

	public void deleteFriend(int userIndex, int friendIndex) {
		assertEquals(userDataFacade.deleteFriendByIndex(userIndex, friendIndex), 1);
		assertEquals(userDataFacade.deleteFriendByIndex(friendIndex, userIndex), 1);
	}

	/*-----------------------------------------------------------------------------------------------
	 * friend service related methods*/

	@Test
	public void isAlreadyFriendByIndexTest() {
		assertEquals(userDataFacade.isAlreadyFriendByIndex(testUser[0].getIndex(), testUser[1].getIndex()), true);
		assertEquals(userDataFacade.isAlreadyFriendByIndex(testUser[3].getIndex(), testUser[2].getIndex()), false);
	}

	@Test
	public void insertFriendByIndexTest() {
		// 위에서 커버
		// userDataFacade.insertFriendByIndex(testUser[0].getIndex(),
		// testUser[1].getIndex());
	}

	@Test
	public void insertFriendByFriendEmailTest() {
		// 위에서 커버
		// assertEquals(userDataFacade.insertFriendByFriendEmail(testUser[0].getIndex(),
		// testUser[1].getEmail()), 0);
	}

	@Test
	public void selectFriendListByUserIndexTest() {
		ArrayList<UserModel> result = userDataFacade.selectFriendListByUserIndex(testUser[0].getIndex());
		ArrayList<UserModel> friends = new ArrayList<UserModel>();
		friends.add(testUser[1]);
		friends.add(testUser[2]);
		friends.add(testUser[3]);
		assertEquals(friends.toString(), result.toString());
		
		result = userDataFacade.selectFriendListByUserIndex(testUser[1].getIndex());
		friends.clear();
		friends.add(testUser[0]);
		friends.add(testUser[2]);
		assertEquals(friends.toString(), result.toString());
		
		result = userDataFacade.selectFriendListByUserIndex(testUser[2].getIndex());
		friends.clear();
		friends.add(testUser[0]);
		friends.add(testUser[1]);
		assertEquals(friends.toString(), result.toString());
		
		result = userDataFacade.selectFriendListByUserIndex(testUser[3].getIndex());
		friends.clear();
		friends.add(testUser[0]);
		assertEquals(friends.toString(), result.toString());
		
		result = userDataFacade.selectFriendListByUserIndex(testUser[4].getIndex());
		friends.clear();
		assertEquals(friends.toString(), result.toString());
	}
	
	@Test
	public void selectStrangerFriendListByUserIndexTest() {
		UserModel node[] = { new UserModel(0, "user00", "name00", "email00@gmail.com", "pictureURL00"),
				new UserModel(0, "user11", "name11", "email11@gmail.com", "pictureURL11"),
				new UserModel(0, "user22", "name22", "email22@gmail.com", "pictureURL22")};

		int i = 0;
		for(UserModel userModel : node) {
			userDataFacade.insertUser(userModel);
			node[i++] = userDataFacade.selectUserById(userModel.getUserId());
		}
		
		userDataFacade.insertFriendByIndex(node[1].getIndex(), node[0].getIndex());
		userDataFacade.insertFriendByIndex(node[2].getIndex(), node[0].getIndex());
		
		ArrayList<UserModel> result = userDataFacade.selectStrangerFriendListByUserIndex(node[0].getIndex());
		ArrayList<UserModel> expected = new ArrayList<UserModel>();
		expected.add(node[1]);
		expected.add(node[2]);
		
		assertEquals(result.toString(), expected.toString());
		
		userDataFacade.deleteFriendByIndex(node[1].getIndex(), node[0].getIndex());
		userDataFacade.deleteFriendByIndex(node[2].getIndex(), node[0].getIndex());
		
		for(UserModel userModel : node) {
			userDataFacade.deleteUserById(userModel.getUserId());
		}
		
		expected.clear();
		assertEquals(userDataFacade.selectStrangerFriendListByUserIndex(node[1].getIndex()), expected);
	}

	@Test
	public void deleteFriendByIndexTest() {
		UserModel node[] = { new UserModel(0, "userIDDD", "name", "email@gmail.com", "pictureURL"),
				new UserModel(0, "userIDDD22", "name222", "email222@gmail.com", "pictureURL222") };

		int i = 0;
		for(UserModel userModel : node) {
			userDataFacade.insertUser(userModel);
			node[i++] = userDataFacade.selectUserById(userModel.getUserId());
		}
		
		setFriend(node[0].getIndex(), node[1].getIndex());
		deleteFriend(node[0].getIndex(), node[1].getIndex());
		
		assertEquals(userDataFacade.deleteFriendByIndex(node[0].getIndex(), node[1].getIndex()), 0);
		assertEquals(userDataFacade.deleteFriendByIndex(node[1].getIndex(), node[0].getIndex()), 0);
		
		for(UserModel userModel : node) {
			userDataFacade.deleteUserById(userModel.getUserId());
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void deleteFriendByFriendEmailTest() {
		UserModel node[] = { new UserModel(0, "userIDDD", "name", "email@gmail.com", "pictureURL"),
				new UserModel(0, "userIDDD22", "name222", "email222@gmail.com", "pictureURL222") };

		int i = 0;
		for(UserModel userModel : node) {
			userDataFacade.insertUser(userModel);
			node[i++] = userDataFacade.selectUserById(userModel.getUserId());
		}
		
		setFriend(node[0].getIndex(), node[1].getIndex());
		
		assertEquals(userDataFacade.deleteFriendByFriendEmail(node[0].getIndex(), node[1].getEmail()), 1);
		assertEquals(userDataFacade.deleteFriendByFriendEmail(node[1].getIndex(), node[0].getEmail()), 1);
		
		assertEquals(userDataFacade.deleteFriendByFriendEmail(node[0].getIndex(), node[1].getEmail()), 0);
		assertEquals(userDataFacade.deleteFriendByFriendEmail(node[1].getIndex(), node[0].getEmail()), 0);
		
		for(UserModel userModel : node) {
			userDataFacade.deleteUserById(userModel.getUserId());
		}
	}
}
