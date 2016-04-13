package kr.ac.ajou.mydictionary.user;

public interface UserService {
	/* UserService 다 정의 되면 javaDoc좀 달아줏셈 */
	
	public boolean login(String idTokenString) throws Exception;
	
	
	public void loginTest(String userId) throws Exception;
}
