package kr.ac.ajou.mydictionary.userdata;

import java.util.ArrayList;

import kr.ac.ajou.mydictionary.user.UserModel;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserDataMapper {
	// public int insertUser(@Param("userId") String userId);
	/*
	 * @Results({
	 * 
	 * @Result(property = "index", column = "index"),
	 * 
	 * @Result(property = "userId", column = "userId") })
	 */

	static final String INSERT_USER_QUERY = "INSERT INTO user (userId, name, email, pictureURL) VALUES (#{userId}, #{name}, #{email}, #{pictureURL})";

	/**
	 * Inserts an user to user table
	 * 
	 * @param
	 * @return int - success:1 or fail:0
	 * */
	@Insert(INSERT_USER_QUERY)
	public int insertUser(UserModel user);

	static final String SELECT_USER_BY_ID_QUERY = "SELECT * FROM user WHERE userId = #{userId}";

	/**
	 * Returns an user from userId
	 * 
	 * @param
	 * @return User fail:null
	 * */
	@Select(SELECT_USER_BY_ID_QUERY)
	public UserModel selectUserById(String userId);

	static final String SELECT_USER_BY_EMAIL_QUERY = "SELECT * FROM user WHERE email = #{email}";

	/**
	 * Returns an user from email
	 * 
	 * @param
	 * @return User fail:null
	 * */
	@Select(SELECT_USER_BY_EMAIL_QUERY)
	public UserModel selectUserByEmail(String email);

	static final String UPDATE_USER_BY_ID_QUERY = "UPDATE user SET userId = #{1.userId}, name = #{1.name}, email = #{1.email}, pictureURL = #{1.pictureURL} WHERE userId = #{0}";

	/**
	 * Updates an user to user table from userId
	 * 
	 * @param
	 * @return int - success:1 or fail:0
	 * */
	@Update(UPDATE_USER_BY_ID_QUERY)
	public int updateUserById(String userId, UserModel replacement);

	static final String UPDATE_USER_BY_EMAIL_QUERY = "UPDATE user SET userId = #{1.userId}, name = #{1.name}, email = #{1.email}, pictureURL = #{1.pictureURL} WHERE email = #{0}";

	/**
	 * Updates an user to user table from email
	 * 
	 * @param
	 * @return int - success:1 or fail:0
	 * */
	@Update(UPDATE_USER_BY_EMAIL_QUERY)
	public int updateUserByEmail(String email, UserModel replacement);

	static final String DELETE_USER_BY_ID_QUERY = "DELETE FROM user WHERE userId = #{userId}";

	/**
	 * Deletes an user to user table from userId
	 * 
	 * @param
	 * @return int - success:1 or fail:0
	 * */
	@Delete(DELETE_USER_BY_ID_QUERY)
	public int deleteUserById(String userId);

	static final String DELETE_USER_BY_EMAIL_QUERY = "DELETE FROM user WHERE email = #{email}";

	/**
	 * Deletes an user to user table from email
	 * 
	 * @param
	 * @return int - success:1 or fail:0
	 * */
	@Delete(DELETE_USER_BY_EMAIL_QUERY)
	public int deleteUserByEmail(String email);

	static final String INSERT_FRIEND_BY_FRIEND_EMAIL = "INSERT INTO friend(userIndex, friendIndex) VALUES (#{0}, (SELECT user.index FROM user WHERE user.email=#{1}))";

	/**
	 * Inserts an friend to friend table from userIndex and friendEmail
	 * 
	 * @param
	 * @return int - success:1 or fail:0
	 * */
	@Insert(INSERT_FRIEND_BY_FRIEND_EMAIL)
	public int insertFriendByFriendEmail(int userIndex, String friendEmail);

	final static String SELECT_FRIEND_BY_USER_INDEX_QUERY = "SELECT user.userId, user.name, user.email, user.pictureURL FROM friend INNER JOIN user ON friend.friendIndex = user.index WHERE friend.userIndex = #{userIndex}";

	/**
	 * Selects all friends to friend table from userIndex
	 * 
	 * @param
	 * @return ArrayList<User> - success:many or fail:null?
	 * */
	@Select(SELECT_FRIEND_BY_USER_INDEX_QUERY)
	public ArrayList<UserModel> selectFriendByUserIndex(int userIndex);

	/* @Update("UPDATE friend ") 안씁니다 */

	final static String DELETE_FRIEND_BY_FRIEND_EMAIL = "DELETE FROM friend WHERE userIndex=#{0} AND friendIndex=(SELECT user.index FROM user WHERE user.email=#{1})";

	/**
	 * Deletes an friend to friend table from userIndex and friendEmail
	 * 
	 * @param
	 * @return int - success:1 or fail:0
	 * */
	@Delete(DELETE_FRIEND_BY_FRIEND_EMAIL)
	public int deletefriend(int userIndex, String friendEmail);

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
