package kr.ac.ajou.mydictionary.userdata;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.ac.ajou.mydictionary.user.UserModel;

public interface UserDataMapper {
	/*
	 * @Result(property = "userId", column = "userId") })
	 */

	/*-----------------------------------------------------------------------------------------------
	 * general user service related queries and methods*/

	static final String SELECT_USER_COUNT_BY_INDEX_QUERY = "SELECT COUNT(*) FROM user WHERE user.index = #{0}";

	/**
	 * Get the number of users from user table whose user's index is userIndex
	 *
	 * @param
	 * @return int - counts of matching users
	 * */
	@Select(SELECT_USER_COUNT_BY_INDEX_QUERY)
	public int selectUserCountByIndex(int userIndex);

	static final String INSERT_USER_QUERY = "INSERT INTO user (userId, name, email, pictureURL) VALUES (#{0.userId}, #{0.name}, #{0.email}, #{0.pictureURL})";

	/**
	 * Inserts an user to user table
	 *
	 * @param user - UserModel object
	 * @return int - success:1 / fail:0
	 * */
	@Insert(INSERT_USER_QUERY)
	public int insertUser(UserModel user);

	static final String SELECT_USER_BY_ID_QUERY = "SELECT * FROM user WHERE userId = #{0}";

	/**
	 * Returns an user when such user's id is fit to userId
	 *
	 * @param userId
	 * @return UserModel - success:UserModel / fail:null
	 * */
	@Select(SELECT_USER_BY_ID_QUERY)
	public UserModel selectUserById(String userId);

	static final String SELECT_USER_BY_EMAIL_QUERY = "SELECT * FROM user WHERE email = #{0}";

	/**
	 * Returns an user when such user's email is fit to email
	 *
	 * @param email
	 * @return UserModel - success:UserModel / fail:null
	 * */
	@Select(SELECT_USER_BY_EMAIL_QUERY)
	public UserModel selectUserByEmail(String email);

	static final String UPDATE_USER_BY_ID_QUERY = "UPDATE user SET userId = #{1.userId}, name = #{1.name}, email = #{1.email}, pictureURL = #{1.pictureURL} WHERE userId = #{0}";

	/**
	 * Updates an user to user table when such user's id is fit to userId
	 *
	 * @param userId, replacement
	 * @return int - success:1 / fail:0
	 * */
	@Update(UPDATE_USER_BY_ID_QUERY)
	public int updateUserById(String userId, UserModel replacement);

	static final String UPDATE_USER_BY_EMAIL_QUERY = "UPDATE user SET userId = #{1.userId}, name = #{1.name}, email = #{1.email}, pictureURL = #{1.pictureURL} WHERE email = #{0}";

	/**
	 * Updates an user to user table when such user's email is fit to email
	 *
	 * @param
	 * @return int - success:1 / fail:0
	 * */
	@Update(UPDATE_USER_BY_EMAIL_QUERY)
	public int updateUserByEmail(String email, UserModel replacement);

	static final String DELETE_USER_BY_ID_QUERY = "DELETE FROM user WHERE userId = #{0}";

	/**
	 * Deletes an user to user table when such user's id is fit to userId
	 *
	 * @param
	 * @return int - success:1 / fail:0
	 * */
	@Delete(DELETE_USER_BY_ID_QUERY)
	public int deleteUserById(String userId);

	static final String DELETE_USER_BY_EMAIL_QUERY = "DELETE FROM user WHERE email = #{0}";

	/**
	 * Deletes an user to user table when such user's email is fit to email
	 *
	 * @param email
	 * @return int - success:1 / fail:0
	 * */
	@Delete(DELETE_USER_BY_EMAIL_QUERY)
	public int deleteUserByEmail(String email);

	/*-----------------------------------------------------------------------------------------------
	 * friend service related queries and methods*/

	static final String SELECT_FRIEND_COUNT_BY_INDEX = "SELECT COUNT(*) FROM  friend WHERE userIndex = #{0} AND friendIndex = #{1}";

	/**
	 * Get the number of user-friend relation data from friend table with userIndex and friendIndex
	 *
	 * @param userIndex, friendIndex
	 * @return int - the number of matching relations
	 * */
	@Select(SELECT_FRIEND_COUNT_BY_INDEX)
	public int selectFriendCountByIndex(int userIndex, int friendIndex);

	static final String INSERT_FRIEND_BY_INDEX = "INSERT INTO friend(userIndex, friendIndex) VALUES (#{0}, #{1})";

	/**
	 * Inserts a user-friend relation data to friend table using userIndex and friendIdex
	 *
	 * @param userIndex, friendIndex
	 * @return int - success:1 / fail:0
	 * */
	@Insert(INSERT_FRIEND_BY_INDEX)
	public int insertFriendByIndex(int userIndex, int friendIndex);

	static final String INSERT_FRIEND_BY_FRIEND_EMAIL = "INSERT INTO friend(userIndex, friendIndex) VALUES (#{0}, (SELECT user.index FROM user WHERE user.email=#{1}))";

	/**
	 * Inserts a user-friend relation data to friend table using userIndex and friendEmail
	 *
	 * @param userIndex, friendEmail
	 * @return int - success:1 / fail:0
	 * */
	@Insert(INSERT_FRIEND_BY_FRIEND_EMAIL)
	public int insertFriendByFriendEmail(int userIndex, String friendEmail);

	final static String SELECT_FRIEND_BY_USER_INDEX_QUERY = "SELECT user.index, user.userId, user.name, user.email, user.pictureURL FROM friend INNER JOIN user ON friend.friendIndex = user.index WHERE friend.userIndex = #{0}";

	/**
	 * Selects all friends from friend table using userIndex
	 * If there is satisfied result, then returns in the form of ArrayList
	 *
	 * @param userIndex
	 * @return ArrayList<UserModel> - success:many / fail:empty array
	 * */
	@Select(SELECT_FRIEND_BY_USER_INDEX_QUERY)
	public ArrayList<UserModel> selectFriendListByUserIndex(int userIndex);

	final static String SELECT_STRANGER_FRIEND_BY_USER_INDEX_QUERY = "SELECT user.index, user.userId, user.name, user.email, user.pictureURL FROM friend INNER JOIN user ON friend.userIndex = user.index WHERE friend.friendIndex = #{0}";

	/**
	 * Selects all friends added user from friend table using userIndex
	 *
	 * @param userIndex
	 * @return ArrayList<UserModel> - success:many or fail:empty array
	 * */
	@Select(SELECT_STRANGER_FRIEND_BY_USER_INDEX_QUERY)
	public ArrayList<UserModel> selectStrangerFriendListByUserIndex(int userIndex);

	// do not use @Update("UPDATE friend ")

	final static String DELETE_FRIEND_BY_INDEX = "DELETE FROM friend WHERE userIndex = #{0} AND friendIndex = #{1}";

	/**
	 * Deletes a user-friend relation data from friend table using userIndex and
	 * friendIndex
	 *
	 * @param
	 * @return int - success:1 or fail:0
	 * */
	@Delete(DELETE_FRIEND_BY_INDEX)
	public int deleteFriendByIndex(int userIndex, int friendIndex);

	final static String DELETE_FRIEND_BY_FRIEND_EMAIL = "DELETE FROM friend WHERE userIndex=#{0} AND friendIndex=(SELECT user.index FROM user WHERE user.email=#{1})";

	/**
	 * Deletes a user-friend relation data from friend table using userIndex and
	 * friendEmail
	 *
	 * @param
	 * @return int - success:1 or fail:0
	 * */
	@Delete(DELETE_FRIEND_BY_FRIEND_EMAIL)
	public int deleteFriendByFriendEmail(int userIndex, String friendEmail);

	// public void userInsert(String userId);
	// public String userSelect(String userId);
	// public String userUpdate(String userId, String replacement);
	// public void userDelete(String UserId);
	//
	// public void friendInsert(String userId, String friendId);
	// public String friendSelect(String userId);
	// public String friendUpdate(String userId);
	// public void friendDelete(String userId, String friendId);

	// No @Results annotation
	// @Select("select * from users")
	// @ConstructorArgs({
	// @Arg(property = "id", column = "id", id = "true"),
	// @Arg(property = "firstName", column = "first_name")
	// @Arg(property = "lastName", column = "last_name") })
	// List<User> getUsers();

	// @Select("select * from users")
	// @ConstructorArgs({
	// @Args(property = "id", column = "id", id = "true"),
	// })
	// @Results({
	// @Result(property = "firstName", column = "first_name")
	// @Result(property = "lastName", column = "last_name")
	// })
	// List<User> getUsers();
}
