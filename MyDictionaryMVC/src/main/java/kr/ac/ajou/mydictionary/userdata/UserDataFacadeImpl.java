package kr.ac.ajou.mydictionary.userdata;

import java.util.ArrayList;

import kr.ac.ajou.mydictionary.user.UserModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userDataBaseFacade")
public class UserDataFacadeImpl implements UserDataFacade {

	@Autowired
	private UserDataMapper mapper;

	public UserDataFacadeImpl() {
		super();
	}

	/*-----------------------------------------------------------------------------------------------
	 * normal user service related methods*/

	@Override
	public boolean isUserExistByIndex(int userIndex) {
		if (mapper.selectUserCountByIndex(userIndex) == 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public int insertUser(UserModel user) {
		return mapper.insertUser(user);
	}

	@Override
	public UserModel selectUserById(String userId) {
		return mapper.selectUserById(userId);
	}

	@Override
	public UserModel selectUserByEmail(String email) {
		return mapper.selectUserByEmail(email);
	}

	@Override
	public int updateUserById(String userId, UserModel replacement) {
		return mapper.updateUserById(userId, replacement);
	}

	@Override
	public int updateUserByEmail(String email, UserModel replacement) {
		return mapper.updateUserByEmail(email, replacement);
	}

	@Override
	public int deleteUserById(String userId) {
		return mapper.deleteUserById(userId);
	}

	@Override
	public int deleteUserByEmail(String email) {
		return mapper.deleteUserByEmail(email);
	}

	/*-----------------------------------------------------------------------------------------------
	 * friend service related methods*/

	@Override
	public boolean isAlreadyFriendByIndex(int userIndex, int friendIndex) {
		if (mapper.selectFriendCountByIndex(userIndex, friendIndex) == 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public int insertFriendByIndex(int userIndex, int friendIndex) {
		return mapper.insertFriendByIndex(userIndex, friendIndex);
	}

	@Override
	public int insertFriendByFriendEmail(int userIndex, String friendEmail) {
		return mapper.insertFriendByFriendEmail(userIndex, friendEmail);
	}

	@Override
	public ArrayList<UserModel> selectFriendListByUserIndex(int userIndex) {
		return mapper.selectFriendListByUserIndex(userIndex);
	}

	@Override
	public ArrayList<UserModel> selectStrangerFriendListByUserIndex(int userIndex) {
		return mapper.selectStrangerFriendListByUserIndex(userIndex);
	}

	@Override
	public int deleteFriendByIndex(int userIndex, int friendIndex) {
		return mapper.deleteFriendByIndex(userIndex, friendIndex);
	}

	@Override
	public int deleteFriendByFriendEmail(int userIndex, String friendEmail) {
		return mapper.deleteFriendByFriendEmail(userIndex, friendEmail);
	}

	/*
	 * @Override public int updateFriend(String userId) { return 0; }
	 * 
	 * @Override public int deleteFriend(int userIndex, String friendEmail) {
	 * return mapper.deletefriend(userIndex, friendEmail); }
	 */
}
