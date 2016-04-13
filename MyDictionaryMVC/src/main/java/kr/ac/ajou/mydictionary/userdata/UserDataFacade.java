package kr.ac.ajou.mydictionary.userdata;

import java.util.ArrayList;

public interface UserDataFacade {
	
	/**
	 * Inserts an user to user table
	 * 
	 * @param
	 * @return int - success:1 or fail:0
	 * */
	public int insertUser(User user);
	
	/**
	 * Returns an user from userId
	 * 
	 * @param
	 * @return User fail:null
	 * */
	@Deprecated
	public User selectUserById(String userId);
	
	/**
	 * Returns an user from email
	 * 
	 * @param
	 * @return User fail:null
	 * */
	public User selectUserByEmail(String email);
	
	/**
	 * Updates an user to user table from userId
	 * 
	 * @param
	 * @return int - success:1 or fail:0
	 * */
	@Deprecated
	public int updateUserById(String userId, User replacement);
	
	/**
	 * Updates an user to user table from email
	 * 
	 * @param
	 * @return int - success:1 or fail:0
	 * */
	public int updateUserByEmail(String email, User replacement);
	
	/**
	 * Deletes an user to user table from userId
	 * 
	 * @param
	 * @return int - success:1 or fail:0
	 * */
	@Deprecated
	public int deleteUserById(String userId);
	
	/**
	 * Deletes an user to user table from email
	 * 
	 * @param
	 * @return int - success:1 or fail:0
	 * */
	public int deleteUserByEmail(String email);
	
	public int insertFriend(String userId, String friendId);
	public ArrayList<User> selectFriend(String userId);
	public int updateFriend(String userId);
	public int deleteFriend(String userId, String friendId);
}
