package kr.ac.ajou.mydictionary.dictionarymanager;

import java.util.ArrayList;

import javax.annotation.Resource;

import kr.ac.ajou.mydictionary.dictionarymanager.model.FriendModel;
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
	
	private static final String APPLICATION_JSON_UTF8 = "application/json;charset=UTF-8";
	
	@Resource(name = "friendService")
	private FriendService friendService;
	
	@RequestMapping(value = "/followFriend", method = RequestMethod.POST, consumes = APPLICATION_JSON_UTF8, produces = APPLICATION_JSON_UTF8)
	public @ResponseBody String followFriend(@RequestBody FriendModel friendModel) {
		logger.info("[/followFriend]" + " - " + "Get in followFriend method");
		// logger.info("[/followFriend]" + " - " + friendModel.toString());

		if(friendService.followFriend(friendModel.getUserIndex(), friendModel.getFriendIndex())) {
			logger.info("[/followFriend]" + " - " + friendModel.toString() + " success");
			return "success";
		} else {
			logger.info("[/followFriend]" + " - " + friendModel.toString() + " fail");
			return "fail";
		}
	}
	
	@RequestMapping(value = "/unfollowFriend", method = RequestMethod.POST, consumes = APPLICATION_JSON_UTF8, produces = APPLICATION_JSON_UTF8)
	public @ResponseBody String unfollowFriend(@RequestBody FriendModel friendModel) {
		logger.info("[/unfollowFriend]" + " - " + "Get in unfollowFriend method");
		// logger.info("[/unfollowFriend]" + " - " + friendModel.toString());

		if(friendService.unfollowFriend(friendModel.getUserIndex(), friendModel.getFriendIndex())) {
			logger.info("[/unfollowFriend]" + " - " + friendModel.toString() + " success");
			return "success";
		} else {
			logger.info("[/unfollowFriend]" + " - " + friendModel.toString() + " fail");
			return "fail";
		}
	}
	
	@RequestMapping(value = "/getFriendsByUserIndex", method = RequestMethod.POST, consumes = APPLICATION_JSON_UTF8, produces = APPLICATION_JSON_UTF8)
	public @ResponseBody ArrayList<UserModel> getFriendsByUserIndex(@RequestBody FriendModel friendModel) {
		logger.info("[/getFriendsByUserIndex]" + " - " + "Get in getFriendsByUserIndex method");
		// logger.info("[/getFriendsByUserIndex]" + " - " + friendModel.getUserIndex() + ", " + friendModel.getFriendIndex());

		ArrayList<UserModel> friends = friendService.getFriendList(friendModel.getUserIndex());
		logger.info("[/getFriendsByUserIndex]" + " - " + friends.toString());

		return friends;
	}
}
