package kr.ac.ajou.mydictionary.userdata;

import java.util.ArrayList;

import kr.ac.ajou.mydictionary.user.UserModel;

public interface UserDataFacade {
	
	/**
	 * Inserts an user to user table
	 * 
	 * @param
	 * @return int - success:1 or fail:0
	 * */
	public int insertUser(UserModel user);
	
	/**
	 * Returns an user from userId
	 * 
	 * @param
	 * @return User fail:null
	 * */
	public UserModel selectUserById(String userId);
	
	/**
	 * Returns an user from email
	 * 
	 * @param
	 * @return User fail:null
	 * */
	@Deprecated
	public UserModel selectUserByEmail(String email);
	
	/**
	 * Updates an user to user table from userId
	 * 
	 * @param
	 * @return int - success:1 or fail:0
	 * */
	public int updateUserById(String userId, UserModel replacement);
	
	/**
	 * Updates an user to user table from email
	 * 
	 * @param
	 * @return int - success:1 or fail:0
	 * */
	@Deprecated
	public int updateUserByEmail(String email, UserModel replacement);
	
	/**
	 * Deletes an user to user table from userId
	 * 
	 * @param
	 * @return int - success:1 or fail:0
	 * */
	public int deleteUserById(String userId);
	
	/**
	 * Deletes an user to user table from email
	 * 
	 * @param
	 * @return int - success:1 or fail:0
	 * */
	@Deprecated
	public int deleteUserByEmail(String email);
	
	/**
	 * Inserts an friend to friend table from userIndex and friendEmail
	 * 
	 * @param
	 * @return int - success:1 or fail:0
	 * */
	public int insertFriendByFriendEmail(int userIndex, String friendEmail);
	
	/**
	 * Selects all friends to friend table from userIndex
	 * 
	 * @param
	 * @return ArrayList<User> - success:many or fail:null?
	 * */
	public ArrayList<UserModel> selectFriendByUserIndex(int userIndex);
	
	/**
	 * 안쓸꺼라능
	 * */
	@Deprecated
	public int updateFriend(String userId);
	
	/**
	 * Deletes an friend to friend table from userIndex and friendEmail
	 * 
	 * @param
	 * @return int - success:1 or fail:0
	 * */
	public int deletefriend(int userIndex, String friendEmail);
}
