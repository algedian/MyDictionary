package kr.ac.ajou.mydictionary.dictionarymanager;

import java.util.ArrayList;

import javax.annotation.Resource;

import kr.ac.ajou.mydictionary.dictionarymanager.model.FriendModel;
import kr.ac.ajou.mydictionary.friend.FriendService;
import kr.ac.ajou.mydictionary.user.UserModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
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
	public @ResponseBody String followFriend(@RequestBody FriendModel friendModel) {
		logger.info("[/followFriend]" + " - " + "Get in followFriend method");
		logger.info("[/followFriend]" + " - " + friendModel.toString());

		if(friendService.followFriend(friendModel.getUserIndex(), friendModel.getFriendIndex())) {
			logger.info("[/followFriend]" + " - " + friendModel.toString() + " success");
			return "success";
		} else {
			logger.info("[/followFriend]" + " - " + friendModel.toString() + " fail");
			return "fail";
		}
	}
	
	@RequestMapping(value = "/unfollowFriend", method = RequestMethod.POST)
	public @ResponseBody String unfollowFriend(@RequestBody FriendModel friendModel) {
		logger.info("[/unfollowFriend]" + " - " + "Get in unfollowFriend method");
		logger.info("[/unfollowFriend]" + " - " + friendModel.toString());

		if(friendService.unfollowFriend(friendModel.getUserIndex(), friendModel.getFriendIndex())) {
			logger.info("[/unfollowFriend]" + " - " + friendModel.toString() + " success");
			return "success";
		} else {
			logger.info("[/unfollowFriend]" + " - " + friendModel.toString() + " fail");
			return "fail";
		}
	}
	
	@RequestMapping(value = "/getFriendsByUserIndex", method = RequestMethod.POST)
	public @ResponseBody ArrayList<UserModel> getFriendsByUserIndex(@RequestBody int userIndex) {
		logger.info("[/getFriendsByUserIndex]" + " - " + "Get in getFriendsByUserIndex method");
		logger.info("[/getFriendsByUserIndex]" + " - " + userIndex);

		ArrayList<UserModel> friends = friendService.getFriendList(userIndex);
		logger.info("[/getFriendsByUserIndex]" + " - " + friends.toString());

		return friends;
	}
	
//	@RequestMapping(value = "/test", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/test", method = RequestMethod.POST, consumes = "appliaton/json",produces = "appliaton/json")
	public @ResponseBody String test(@RequestBody int userIndex) {
		logger.info("[/test]" + " - " + "Get in test method");
		logger.info("[/test]" + " - " + userIndex);

		return userIndex++ + "";
	}	
	
	@RequestMapping(value = "/ttest", method = RequestMethod.POST)
	public @ResponseBody String ttest() {
		logger.info("[/test]" + " - " + "Get in test method");
		logger.info("[/test]" + " - " + 0);

		return 0 + "";
	}	
}
