package kr.ac.ajou.mydictionary.friend;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.ac.ajou.mydictionary.user.UserModel;
import kr.ac.ajou.mydictionary.userdata.UserDataFacade;

//@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Service("friendService")
public class FriendServiceImpl implements FriendService {
	public static final Logger logger = LoggerFactory.getLogger(FriendServiceImpl.class);

	@Resource(name = "userDataBaseFacade")
	private UserDataFacade userDataFacade;

	public FriendServiceImpl() {
		super();
	}

	@Override
	public boolean followFriend(int userIndex, int friendIndex) {
		if (userDataFacade.isUserExistByIndex(userIndex) && userDataFacade.isUserExistByIndex(friendIndex)) {
			// Check - if user exists
			if (!userDataFacade.isAlreadyFriendByIndex(userIndex, friendIndex)) {
				// Check - if already follow
				if (userDataFacade.insertFriendByIndex(userIndex, friendIndex) == 1) {
					// follow friend
					return true;
				}
			}
		}

		// cannot follow friend
		return false;
	}

	@Override
	public boolean unfollowFriend(int userIndex, int friendIndex) {
		if (userDataFacade.isUserExistByIndex(userIndex) && userDataFacade.isUserExistByIndex(friendIndex)) {
			// Check - if user exists
			if (userDataFacade.isAlreadyFriendByIndex(userIndex, friendIndex)) {
				// Check - if already follow
				if (userDataFacade.deleteFriendByIndex(userIndex, friendIndex) == 1) {
					// unfollow friend
					return true;
				}
			}
		}

		// cannot unfollow friend
		return false;
	}

	@Override
	public ArrayList<UserModel> getFriendList(int userIndex) {
		return userDataFacade.selectFriendListByUserIndex(userIndex);
	}

}
