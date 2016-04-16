package kr.ac.ajou.mydictionary.user;

public interface UserService {
	/* UserService 다 정의 되면 javaDoc좀 달아줏셈 */
	/**
	 * Log in by idTokenString given by Google OAuth API. <br>	  
	 * Verify idTokenString and check user's information in mysql DB. <br><br> 
	 * 
	 * if there is an user then return true /
	 * else return false <br><br>
	 * 
	 * 구글 author 하고, mysql db에 사용자 정보 있는지 확인하고 성공 & 실패 그래서 아마도 parameter는 받을 필요
	 * 없을거 같은뎅??=>토큰인증을 위해, idTokenString은 받아와야할 듯 하옵니다.
	 * 
	 * @exception 로그인
	 *                실패
	 * @return boolean - success:true or fail: false
	 */
	public boolean login(String idTokenString) throws Exception;
	
	/**
	 * Get User by email string.<br>
	 * 
	 * if there is no user matching then returns null<br>
	 * else return UserModel object<br>
	 * 
	 * @exception 해당 이메일 주소를 가진 유저가 존재하지 않음.
	 * @return UserModel - success:UserModel object or fail:null
	 * */
	public UserModel getUserByEmail(String email);
	
	public UserModel getCurrentUser();
}
