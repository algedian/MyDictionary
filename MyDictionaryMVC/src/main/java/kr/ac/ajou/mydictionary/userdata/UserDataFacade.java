package kr.ac.ajou.mydictionary.userdata;

public interface UserDataFacade {
	public int insertUser(String userId);
	public String selectUser(String userId);
	public int updateUser(String userId, String replacement);
	public int deleteUser(String userId);
	
	public int insertFriend(String userId, String friendId);
	public String selectFriend(String userId);
	public int updateFriend(String userId);
	public int deleteFriend(String userId, String friendId);
}
