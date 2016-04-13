package kr.ac.ajou.mydictionary.userdata;

import java.util.ArrayList;

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
	public int insertUser(User user) {
		return mapper.insertUser(user);
	}
	
	@Override
	public User selectUserById(String userId) {
		return mapper.selectUserById(userId);
	}
	
	@Override
	public User selectUserByEmail(String email) {
		return mapper.selectUserByEmail(email);
	}
	
	@Override
	public int updateUserById(String userId, User replacement) {
		return mapper.updateUserById(userId, replacement);
	}

	@Override
	public int updateUserByEmail(String email, User replacement) {
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
	public int insertFriend(String userId, String friendId) {
		return 0;
	}

	@Override
	public ArrayList<User> selectFriend(String userId) {
		return null;
	}

	@Override
	public int updateFriend(String userId) {
		return 0;
	}

	@Override
	public int deleteFriend(String userId, String friendId) {
		return 0;
	}

}
