package kr.ac.ajou.mydictionary.friend;

import java.util.ArrayList;

import kr.ac.ajou.mydictionary.user.UserModel;

public interface FriendService {

	/**
	 * makes user:userIndex to follow user:friendIndex<br>
	 * 
	 * if there is no user matching userIndex or friendIndex then returns false<br>
	 * if user:userIndex is already following user:friendIndex then returns false<br>
	 * else return true<br>
	 * 
	 * @exception UserNotExist, AlreadyFriend
	 * @return boolean - success:true or fail:false
	 * */
	public boolean followFriend(int userIndex, int friendIndex);

	
	/**
	 * make user:userIndex to unfollow user:friendIndex<br>
	 * 
	 * if there is no user matching userIndex or friendIndex then returns false<br>
	 * if user:userIndex is not following user:friendIndex then returns false<br>
	 * else return true<br>
	 * 
	 * @exception UserNotExist, NotFriend
	 * @return boolean - success:true or fail:false
	 * */
	public boolean unfollowFriend(int userIndex, int friendIndex);

	/**
	 * returns friend list of user:userIndex<br>
	 * 
	 * if there is no user matching userIndex then returns null<br>
	 * else return ArrayList<br>
	 * 
	 * @exception
	 * @return ArrayList<UserModel> - success:ArrayList<UserModel> object or fail:null
	 * */
	public ArrayList<UserModel> getFriendList(int userIndex);

}
