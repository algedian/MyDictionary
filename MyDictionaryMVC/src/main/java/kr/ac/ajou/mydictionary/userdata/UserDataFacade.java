package kr.ac.ajou.mydictionary.userdata;

import java.util.ArrayList;

import kr.ac.ajou.mydictionary.user.UserModel;

public interface UserDataFacade {
	
	/*-----------------------------------------------------------------------------------------------
	 * normal user service related methods*/
	
	/**
	 * Return boolean value if the user exist or not using userIndex
	 * 
	 * @param
	 * @return boolean - exists:true or not-exists:false
	 * */
	public boolean isUserExistByIndex(int userIndex);
	
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
	 * @return UserModel - success:User or fail:null
	 * */
	public UserModel selectUserById(String userId);
	
	/**
	 * Returns an user from email
	 * 
	 * @param
	 * @return UserModel - success:User or User fail:null
	 * */
	//@Deprecated 친구검색 시 이메일로 유저검색하는 것이 필요하므로 다시 살려보았사옵니다.
	public UserModel selectUserByEmail(String email);
	
	/**
	 * Updates an user to user table from userId
	 * 
	 * @param
	 * @return int - success:1 or fail:0
	 * */
	public int updateUserById(String userId, UserModel replacement);
	
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
	 * Updates an user to user table from email
	 * 
	 * @param
	 * @return int - success:1 or fail:0
	 * */
	@Deprecated
	public int updateUserByEmail(String email, UserModel replacement);
	
	
	/*-----------------------------------------------------------------------------------------------
	 * friend service related methods*/

	/**
	 * Checks if a user is already following the friend whose index is friendIndex
	 * 
	 * @param
	 * @return boolean - following:1 or not following:0
	 * */
	public boolean isAlreadyFriendByIndex(int userIndex, int friendIndex);
	
	/**
	 * Inserts a user-friend relation data to friend table using userIndex and userIndex of friend
	 * 
	 * @param
	 * @return int - success:1 or fail:0
	 * */
	public int insertFriendByIndex(int userIndex, int friendIndex);
	
	/**
	 * Deletes user-friend relation data from Friend table using userIndex and userIndex of friend
	 * 
	 * @param
	 * @return int - success:1 or fail:0
	 * */
	public int deleteFriendByIndex(int userIndex, int friendIndex);
	
	/**
	 * Selects all friends from friend table using userIndex
	 * 
	 * @param
	 * @return ArrayList<User> - success:ArrayList<UserModel> or fail:null
	 * */
	public ArrayList<UserModel> selectFriendListByUserIndex(int userIndex);
	
	
	
	/**
	 * Inserts an friend to friend table from userIndex and friendEmail
	 * 
	 * @param
	 * @return int - success:1 or fail:0
	 * */
	@Deprecated
	public int insertFriendByFriendEmail(int userIndex, String friendEmail);
	
}
