package kr.ac.ajou.mydictionary.userdb.mapper;

import java.util.ArrayList;

import kr.ac.ajou.mydictionary.userdb.User;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserDataMapper {
	// public int insertUser(@Param("userId") String userId);

	@Insert("INSERT INTO user (userId) VALUES (#userId)")
	public int insertUserById(String userId);

	/**
	 * Returns an user from userId
	 * 
	 * @param userId
	 * @return User 
	 * */
	@Select("SELECT * FROM user WHERE userId = #userId")
	public User selectUserById(String userId);

	@Update("UPDATE user SET userId = #replacement WHERE userId = #userId")
	public int updateUser(String userId, String replacement);

	@Delete("DELETE FROM user WHERE userId = #userId")
	public int deleteUserById(String userId);
	
	/* friend transaction은 죄다 Join 들어가야 할듯.*/
	@Insert("INSERT INTO friend (userId, friendId) VALUES (#userId, #friendId)")
	public int insertFriend(String userId, String friendId);
	
	/* 이거 SQL문 따로 맹들어야뎀, Join으로 */
	@Select("SELECT * FROM friend WHERE userId = #userId")
	public ArrayList<User> selectFriend(String userId);
	
	/*@Update("UPDATE friend ") 안씁니다*/
	
	@Delete("DELETE FROM friend WHERE userId = #userId AND friendId = #friendId")
	public int deletefriend(String userId, String friendId);
	/*
	 * public void userInsert(String userId); public String userSelect(String
	 * userId); 
	 * public String userUpdate(String userId, String replacement);
	 * public void userDelete(String UserId);
	 * 
	 * public void friendInsert(String userId, String friendId); public String
	 * friendSelect(String userId); public String friendUpdate(String userId);
	 * public void friendDelete(String userId, String friendId);
	 */
}
