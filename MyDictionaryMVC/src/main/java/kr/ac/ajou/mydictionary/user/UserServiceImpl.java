package kr.ac.ajou.mydictionary.user;

import javax.annotation.Resource;

import kr.ac.ajou.mydictionary.userdata.UserDataFacade;

import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource(name = "userDataBaseFacade")
	private UserDataFacade userDataFacade;
	
	
	
	public UserServiceImpl() {
		super();
	}

	/**
	 * 구글 author 하고, mysql db에 사용자 정보 있는지 확인하고 성공 & 실패
	 * 그래서 아마도 parameter는 받을 필요 없을거 같은뎅??
	 * 
	 * @exception 로그인 실패 
	 * */
	@Override
	public void login() throws Exception {
		
	}

	
	
	/* 여기서부턴 테스트 */
	
	/**
	 * 디비만 테스트
	 * 
	 * @exception 로그인 실패
	 */
	@Override
	public void loginTest(String userId) throws Exception {
		// TODO Auto-generated method stub
		userDataFacade.selectUserById(userId);
	}
}
