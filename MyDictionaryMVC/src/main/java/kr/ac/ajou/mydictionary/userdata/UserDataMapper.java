package kr.ac.ajou.mydictionary.userdata;

import java.util.ArrayList;

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

	static final String INSERT_USER_QUERY = "INSERT INTO user (userId, email, pictureURL) VALUES (#{userId}, #{email}, #{pictureURL})";

	/**
	 * Inserts an user to user table
	 * 
	 * @param
	 * @return int - success:1 or fail:0
	 * */
	@Insert(INSERT_USER_QUERY)
	public int insertUser(User user);

	static final String SELECT_USER_BY_ID_QUERY = "SELECT * FROM user WHERE userId = #{userId}";

	/**
	 * Returns an user from userId
	 * 
	 * @param
	 * @return User fail:null
	 * */
	@Select(SELECT_USER_BY_ID_QUERY)
	public User selectUserById(String userId);

	static final String SELECT_USER_BY_EMAIL_QUERY = "SELECT * FROM user WHERE email = #{email}";

	/**
	 * Returns an user from email
	 * 
	 * @param
	 * @return User fail:null
	 * */
	@Select(SELECT_USER_BY_EMAIL_QUERY)
	public User selectUserByEmail(String email);

	static final String UPDATE_USER_BY_ID_QUERY = "UPDATE user SET userId = #{remplacement.userId}, email = #{email}, pictureURL = #{pictureURL} WHERE userId = #{userId}";

	/**
	 * Updates an user to user table from userId
	 * 
	 * @param
	 * @return int - success:1 or fail:0
	 * */
	@Update(UPDATE_USER_BY_ID_QUERY)
	public int updateUserById(String userId, User replacement);

	static final String UPDATE_USER_BY_EMAIL_QUERY = "UPDATE user SET userId = #{userId}, email = #{remplacement.email}, pictureURL = #{pictureURL} WHERE email = #{remplacement.email}";

	/**
	 * Updates an user to user table from email
	 * 
	 * @param
	 * @return int - success:1 or fail:0
	 * */
	@Update(UPDATE_USER_BY_EMAIL_QUERY)
	public int updateUserByEmail(String email, User replacement);

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

	/* friend transaction은 죄다 Join 들어가야 할듯. */
	@Insert("INSERT INTO friend (userId, friendId) VALUES (#userId, #friendId)")
	public int insertFriend(String userId, String friendId);

	/* 이거 SQL문 따로 맹들어야뎀, Join으로 */
	@Select("SELECT * FROM friend WHERE userId = #userId")
	public ArrayList<User> selectFriend(String userId);

	/* @Update("UPDATE friend ") 안씁니다 */

	@Delete("DELETE FROM friend WHERE userId = #userId AND friendId = #friendId")
	public int deletefriend(String userId, String friendId);

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
