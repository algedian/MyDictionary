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

	@Override
	public int insertFriendByFriendEmail(int userIndex, String friendEmail) {
		return mapper.insertFriendByFriendEmail(userIndex, friendEmail);
	}

	@Override
	public ArrayList<UserModel> selectFriendByUserIndex(int userIndex) {
		return mapper.selectFriendByUserIndex(userIndex);
	}

	@Override
	public int updateFriend(String userId) {
		return 0;
	}

	@Override
	public int deleteFriend(int userIndex, String friendEmail) {
		return mapper.deletefriend(userIndex, friendEmail);
	}

}
