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
	 * Insert an user from user
	 * 
	 * @param User (do not use index)
	 * @return int - success:true or fail:false
	 * */
	@Insert(INSERT_USER_QUERY)
	public int insertUser(User user);

	static final String SELECT_USER_QUERY = "SELECT * FROM user WHERE userId = #{userId}";
	/**
	 * Returns an user from userId
	 * 
	 * @param userId
	 * @return User
	 * */
	@Select(SELECT_USER_QUERY)
	public User selectUserById(String userId);

	@Update("UPDATE user SET userId = #replacement WHERE userId = #userId")
	public int updateUser(String userId, String replacement);

	@Delete("DELETE FROM user WHERE userId = #userId")
	public int deleteUserById(String userId);

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
	//  @Args(property = "id", column = "id", id = "true"),
	// })
	// @Results({
	//  @Result(property = "firstName", column = "first_name")
	//  @Result(property = "lastName", column = "last_name")
	// })
	// List<User> getUsers();
}
