package kr.ac.ajou.mydictionary.friend;

import java.util.ArrayList;

import kr.ac.ajou.mydictionary.user.UserModel;

public interface FriendService {

	/**
	 * Follow friend using userIndex and friendIndex<br>
	 *
	 * If there is no user matching userIndex or friendIndex / user is already following friend then returns false<br>
	 * else return true<br>
	 *
	 * @return boolean - success:true / fail:false
	 */
	public boolean followFriend(int userIndex, int friendIndex);

	/**
	 * Unfollow friend using userIndex and friendIndex<br>
	 *
	 * If there is no user matching userIndex or friendIndex | user is not following friend then returns false <br>
	 * else return true<br>
	 *
	 * @return boolean - success:true / fail:false
	 */
	public boolean unfollowFriend(int userIndex, int friendIndex);

	/**
	 * returns friend list of user:userIndex<br>
	 *
	 * if there is no user matching userIndex then returns null<br>
	 * else return ArrayList<br>
	 *
	 * @return ArrayList<UserModel> - success:ArrayList<UserModel> object / fail:null
	 */
	public ArrayList<UserModel> getFriendList(int userIndex);

}
