package kr.ac.ajou.mydictionary.userdata;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kr.ac.ajou.mydictionary.user.UserModel;

public class UserDataFacadeTest extends AbstractUserDataFacadeTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Override
	@After
	public void cleanUp() {
		super.cleanUp();
	}

	public void insertUser(UserModel user) {
		int result = userDataFacade.insertUser(user);
		assertEquals(result, 1);
	}

	public void deleteUser(UserModel user) {
		int result = userDataFacade.deleteUserById(user.getUserId());
		assertEquals(result, 1);
	}

	@Test
	public void selectUserCountByIndexTest() {
		insertUser(testUser[0]);

		UserModel result = userDataFacade.selectUserById(testUser[0].getUserId());
		assertEquals(userDataFacade.isUserExistByIndex(result.getIndex()), true);

		deleteUser(testUser[0]);
		assertEquals(userDataFacade.isUserExistByIndex(result.getIndex()), false);
	}

	@Test
	public void selectUserByIdTest() {
		insertUser(testUser[0]);

		UserModel result = userDataFacade.selectUserById(testUser[0].getUserId());

		assertEquals(result.getUserId(), testUser[0].getUserId());
		assertEquals(result.getEmail(), testUser[0].getEmail());
		assertEquals(result.getPictureURL(), testUser[0].getPictureURL());

		deleteUser(testUser[0]);

		result = userDataFacade.selectUserById(testUser[0].getUserId());
		assertEquals(result, null);
	}

	@Test
	public void selectUserByEmailTest() {
		insertUser(testUser[0]);

		UserModel result = userDataFacade.selectUserByEmail(testUser[0].getEmail());

		assertEquals(result.getUserId(), testUser[0].getUserId());
		assertEquals(result.getEmail(), testUser[0].getEmail());
		assertEquals(result.getPictureURL(), testUser[0].getPictureURL());

		deleteUser(testUser[0]);

		result = userDataFacade.selectUserByEmail(testUser[0].getEmail());
		assertEquals(result, null);
	}

	@Test
	public void updateUserByIdTest() {
		insertUser(testUser[0]);

		int result = userDataFacade.updateUserById(testUser[0].getUserId(), replaceUser);
		assertEquals(result, 1);

		UserModel node = userDataFacade.selectUserByEmail(replaceUser.getEmail());
		assertEquals(node.getUserId(), replaceUser.getUserId());
		assertEquals(node.getName(), replaceUser.getName());
		assertEquals(node.getEmail(), replaceUser.getEmail());
		assertEquals(node.getPictureURL(), replaceUser.getPictureURL());

		deleteUser(replaceUser);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void updateUserByEmailTest() {
		insertUser(replaceUser);

		int result = userDataFacade.updateUserByEmail(replaceUser.getEmail(), testUser[0]);
		assertEquals(result, 1);

		UserModel node = userDataFacade.selectUserByEmail(testUser[0].getEmail());
		assertEquals(node.getUserId(), testUser[0].getUserId());
		assertEquals(node.getName(), testUser[0].getName());
		assertEquals(node.getEmail(), testUser[0].getEmail());
		assertEquals(node.getPictureURL(), testUser[0].getPictureURL());

		deleteUser(testUser[0]);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void deleteUserByEmailTest() {
		insertUser(testUser[0]);

		int result = userDataFacade.deleteUserByEmail(testUser[0].getEmail());
		assertEquals(result, 1);

		result = userDataFacade.deleteUserByEmail(testUser[0].getEmail());
		assertEquals(result, 0);

	}
}
