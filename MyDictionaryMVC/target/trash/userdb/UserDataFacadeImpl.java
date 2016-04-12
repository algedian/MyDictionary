package kr.ac.ajou.mydictionary.userdb;

import kr.ac.ajou.mydictionary.userdb.mapper.UserDataMapper;

import org.springframework.beans.factory.annotation.Autowired;


public class UserDataFacadeImpl implements UserDataFacade {
	@Autowired
	private UserDataMapper userDataMapper;

/*	public UserDataService() {
		super();
	}

	public UserDataService(UserDataMapper userDataMapper) {
		this.userDataMapper = userDataMapper;
	}*/

	@Override
	public int insertUser(String userId) {
		// TODO Auto-generated method stub
		return userDataMapper.insertUserById(userId);
	}

	@Override
	public String selectUser(String userId) {
		// TODO Auto-generated method stub
		return userDataMapper.selectUserById(userId).toString();
	}

	@Override
	public int updateUser(String userId, String replacement) {
		// TODO Auto-generated method stub
		return userDataMapper.updateUser(userId, replacement);
	}

	@Override
	public int deleteUser(String userId) {
		// TODO Auto-generated method stub
		return userDataMapper.deleteUserById(userId);
	}

	@Override
	public int insertFriend(String userId, String friendId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String selectFriend(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateFriend(String userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteFriend(String userId, String friendId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
