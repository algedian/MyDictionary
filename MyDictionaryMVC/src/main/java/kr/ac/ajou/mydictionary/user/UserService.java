package kr.ac.ajou.mydictionary.user;

public interface UserService {
	/**
	 * Log in by idTokenString given by Google OAuth API. <br>
	 * Verify idTokenString and check user's information in mysql DB. <br><br>
	 *
	 * if there is an user then return user<br>
	 * else return null <br><br>
	 *
	 * (To verify token, it is need to get idTokenString)
	 *
	 * @return UserModel - success:UserModel / fail:null
	 */
	public UserModel login(String idTokenString) throws Exception;

	/**
	 * Get User by email string.<br>
	 *
	 * if there is no user matching then returns null<br>
	 * else return UserModel object<br>
	 *
	 * @return UserModel - success:UserModel object / fail:null
	 * */
	public UserModel getUserByEmail(String email);
}
