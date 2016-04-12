package kr.ac.ajou.mydictionary.userdata;

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
	public int insertUser(String userId) {
		return 0;
	}

	@Override
	public String selectUser(String userId) {
		String result = mapper.selectUserById(userId).toString();
		return result;
	}

	@Override
	public int updateUser(String userId, String replacement) {
		return 0;
	}

	@Override
	public int deleteUser(String userId) {
		return 0;
	}

	@Override
	public int insertFriend(String userId, String friendId) {
		return 0;
	}

	@Override
	public String selectFriend(String userId) {
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
