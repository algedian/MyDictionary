package kr.ac.ajou.mydictionary.dictionarymanager;

import java.util.ArrayList;

import javax.annotation.Resource;

import kr.ac.ajou.mydictionary.dictionarymanager.model.SearchModel;
import kr.ac.ajou.mydictionary.document.DocumentModel;
import kr.ac.ajou.mydictionary.friend.FriendService;
import kr.ac.ajou.mydictionary.searchengine.SearchEngine;
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
@RequestMapping(value = "/search")
public class SearchEngineController {
	private static final Logger logger = LoggerFactory.getLogger(SearchEngineController.class);

	@Resource(name = "searchEngine")
	private SearchEngine searchEngine;

	@Resource(name = "friendService")
	private FriendService friendService;

	@RequestMapping(value = "/getUserDocument", method = RequestMethod.POST)
	public @ResponseBody DocumentModel getUserDocument(@RequestBody SearchModel searchModel) {
		logger.info("[/getUserDocument]" + " - " + "Get in getUserDocument method");
		logger.info("[/getUserDocument]" + " - " + searchModel.toString());

		DocumentModel dm = searchEngine.getUserDocument(searchModel.getUserId(), searchModel.getKeyword());
		logger.error("[/getUserDocument]" + " - " + "여기 리턴값 널인지 빈오브젝트인지 확인해봐야뎀");
		if (dm != null) {
			logger.info("[/getUserDocument]" + " - " + dm.toString());
			return dm;
		} else {
			logger.info("[/getUserDocument]" + " - " + "fail");
			return dm;
		}
	}

	@RequestMapping(value = "/getFriendDocuments", method = RequestMethod.POST)
	public @ResponseBody ArrayList<DocumentModel> getFriendDocuments(@RequestBody SearchModel searchModel) {
		logger.info("[/getFriendDocuments]" + " - " + "Get in getFriendDocuments method");
		logger.info("[/getFriendDocuments]" + " - " + searchModel.toString());

		ArrayList<UserModel> friends = friendService.getFriendList(searchModel.getUserIndex());
		ArrayList<DocumentModel> result = searchEngine.getFriendDocuments(friends,  searchModel.getKeyword());

		if (!result.isEmpty()) {
			logger.info("[/getFriendDocuments]" + " - " + searchModel.toString() + " success");
			return result;
		} else {
			logger.info("[/getFriendDocuments]" + " - " + searchModel.toString() + " is empty");
			return result;
		}
	}
	
/*	@RequestMapping(value = "/getFriendDocuments", method = RequestMethod.POST)
	public @ResponseBody ArrayList<DocumentModel> getFriendDocuments(@RequestBody int userIndex,
			@RequestBody String keyword, @RequestBody ArrayList<UserModel> friends) {
		logger.info("[/getFriendDocuments]" + " - " + "Get in getFriendDocuments method");
		logger.info("[/getFriendDocuments]" + " - " + userIndex + ", " + keyword + ", " + friends.toString());
		ArrayList<DocumentModel> result = searchEngine.getFriendDocuments(friends, keyword);

		if (!result.isEmpty()) {
			logger.info("[/getFriendDocuments]" + " - " + userIndex + ", " + keyword + " success");
			return result;
		} else {
			logger.info("[/getFriendDocuments]" + " - " + userIndex + ", " + keyword + " is empty");
			return result;
		}
	}*/
}
