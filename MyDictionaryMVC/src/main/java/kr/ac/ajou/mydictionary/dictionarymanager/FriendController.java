package kr.ac.ajou.mydictionary.dictionarymanager;

import java.util.ArrayList;

import javax.annotation.Resource;

import kr.ac.ajou.mydictionary.friend.FriendService;
import kr.ac.ajou.mydictionary.user.UserModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/friend")
public class FriendController {
	private static final Logger logger = LoggerFactory.getLogger(FriendController.class);
	
	@Resource(name = "friendService")
	private FriendService friendService;
	
	@RequestMapping(value = "/followFriend", method = RequestMethod.POST)
	public @ResponseBody String followFriend(@RequestBody int userIndex, @RequestBody int friendIndex) {
		logger.info("[/followFriend]", "Get in followFriend method");
		logger.info("[/followFriend]", userIndex + ", " + friendIndex);

		if(friendService.followFriend(userIndex, friendIndex)) {
			logger.info("[/followFriend]", userIndex + ", " + friendIndex + " success");
			return "success";
		} else {
			logger.info("[/followFriend]", userIndex + ", " + friendIndex + " fail");
			return "fail";
		}
	}
	
	@RequestMapping(value = "/unfollowFriend", method = RequestMethod.POST)
	public @ResponseBody String unfollowFriend(@RequestBody int userIndex, @RequestBody int friendIndex) {
		logger.info("[/unfollowFriend]", "Get in unfollowFriend method");
		logger.info("[/unfollowFriend]", userIndex + ", " + friendIndex);

		if(friendService.unfollowFriend(userIndex, friendIndex)) {
			logger.info("[/unfollowFriend]", userIndex + ", " + friendIndex + " success");
			return "success";
		} else {
			logger.info("[/unfollowFriend]", userIndex + ", " + friendIndex + " fail");
			return "fail";
		}
	}
	
	@RequestMapping(value = "/getFriendsByUserIndex", method = RequestMethod.POST)
	public @ResponseBody ArrayList<UserModel> getFriendsByUserIndex(@RequestBody int userIndex) {
		logger.info("[/getFriendsByUserIndex]", "Get in getFriendsByUserIndex method");
		logger.info("[/getFriendsByUserIndex]", userIndex);

		ArrayList<UserModel> friends = friendService.getFriendList(userIndex);
		logger.info("[/getUserByEmail]", friends.toString());

		return friends;
	}
}
