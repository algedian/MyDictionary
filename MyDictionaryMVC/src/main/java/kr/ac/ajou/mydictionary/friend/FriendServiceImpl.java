package kr.ac.ajou.mydictionary.friend;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.ac.ajou.mydictionary.user.UserModel;
import kr.ac.ajou.mydictionary.userdata.UserDataFacade;

@Service("friendService")
public class FriendServiceImpl implements FriendService {

	public static final Logger logger = LoggerFactory.getLogger(FriendServiceImpl.class);
	
	@Resource(name = "userDataBaseFacade")
	private UserDataFacade userDataFacade;
	
	public FriendServiceImpl() {
		super();
	}
	
	/* 음 기능 상 친구요청이 오가는것이 아니니 함수명을 걍 직관적으로 어떻게 동작하는지 알기 쉬우라고 follow라고 씀여.
	 * 다른 의견이 있으면 말씀해주시와요*/
	@Override
	public boolean followFriend(int userIndex, int friendIndex) throws Exception {
		
		if(userDataFacade.isUserExistByIndex(userIndex) && userDataFacade.isUserExistByIndex(friendIndex)) //사용자 존재여부 체크
			if(!userDataFacade.isAlreadyFriendByIndex(userIndex, friendIndex)) //이미 팔로우를 하고 있는지 체크
				if(userDataFacade.insertFriendByIndex(userIndex, friendIndex) == 1) //널 따르겠어
					return true;
		
		//그리고 친구를 팔로우 할 수 없었다고 한다...
		return false;
	}

	@Override
	public boolean unfollowFriend(int userIndex, int friendIndex) throws Exception {
		
		if(userDataFacade.isUserExistByIndex(userIndex) && userDataFacade.isUserExistByIndex(friendIndex)) //사용자 존재여부 체크
			if(userDataFacade.isAlreadyFriendByIndex(userIndex, friendIndex)) //이미 팔로우를 하고 있는지 체크
				if(userDataFacade.deleteFriendByIndex(userIndex, friendIndex)== 1) //이제는 널 그만 떠나보내야 해
					return true;
		
		//아아 그러나 널 떠나보낼수가 없당
		return false;
	}

	/* 일단은 arraylist로 받아 오겠습니당? 어차피 컨트롤러에서 외부로 넘겨줄때 json으로 넘겨줄테니..*/
	@Override
	public ArrayList<UserModel> getFriendList(int userIndex) throws Exception {
		return userDataFacade.selectFriendListByUserIndex(userIndex);
	}

}
