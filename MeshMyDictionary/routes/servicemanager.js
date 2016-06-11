// var fs   = require('fs');
// var url = require('url');
var http = require('http');
var XMLHttpRequest = require("xmlhttprequest").XMLHttpRequest;

var log = require('./common').log;
var logYellow = require('./common').logYellow;
var logBlue = require('./common').logBlue;
var logErr = require('./common').logErr;

var models = require('./models');
var dictionaryServer = require('./dictionaryServer');
var metasearchServer = require('./metasearchServer');


exports.index = function(req, res) {
	logYellow('ServiceManager::index');

	return res.render('login');
};

exports.main = function(req, res) {
	logYellow('ServiceManager::main');
	if (typeof (req.session.user) === 'undefined') {
		log('session is undefined');

		return res.status(400).send('400 Bad Request!: ' + 'session is undefined');
	} else {
		return res.render('main');
	}
};

exports.friend = function(req, res) {
	logYellow('ServiceManager::friend');
	if (typeof (req.session.user) === 'undefined') {
		log('session is undefined');

		return res.status(400).send('400 Bad Request!: ' + 'session is undefined');
	} else {
		return res.render('friend');
	}	
};

exports.documentRoadMap = function(req, res) {
	logYellow('ServiceManager::documentRoadMap');
	if (typeof (req.session.user) === 'undefined') {
		log('session is undefined');

		return res.status(400).send('400 Bad Request!: ' + 'session is undefined');
	} else {
		return res.render('documentRoadMap');
	}	
};

exports.search = function(req, res) {
	logYellow('ServiceManager::search');
	
	if (typeof (req.session.user) === 'undefined') {
		log('session is undefined');

		return res.status(400).send('400 Bad Request!: ' + 'session is undefined');
	} else {
		return res.render('search');
	}
};

/**
 * end points
 * */

exports.login = function(req, res) {
	logYellow('ServiceManager::login');

	if (req.body.idToken) {
		req.session.idToken = req.body.idToken;

		var success = function(result) {
			log(result);
			req.session.user = JSON.parse(result);
			
			return res.status(200).type('application/json;charset=UTF-8').json(JSON.stringify(req.session.user));
		};
		var fail = function(err) {
			var response = {
				err : 'DictionaryServer return: ' + err.status + ", " + err.statusText
			};
			return res.status(500).send(JSON.stringify(response));
		};

		dictionaryServer.request('/user/login', 'POST', models.getIdTokenModel(req.body.idToken), success, fail);
	} else {
		return res.status(400).send('400 Bad Request!: ' + 'idToken is null');
	}
};

exports.initSession = function(req, res) {
	logYellow('ServiceManager::initSession');
	
	var user = req.session.user;
	var friendListSuccess = function(result) {
		req.session.friendList = JSON.parse(result);
		
		return res.status(200).send(JSON.stringify({status:'success'}));
	};
	var fail = function(err) {
		var response = {
			err : 'DictionaryServer return: ' + err.status + ", " + err.statusText
		};
		
		return res.status(500).send(JSON.stringify(response));
	};
	
	var documentSuccess = function(result) {
		req.session.documentList = JSON.parse(result);
		
		dictionaryServer.request('/friend/getFriendsByUserIndex', 'POST', models.getFriendModel(user.index, -1), friendListSuccess, fail);
	};

	dictionaryServer.request('/search/getRecentUserDocuments', 'POST', models.getSearchModel(user.userIndex, user.userId, ''), documentSuccess, fail);
};

exports.logout = function(req, res) {
	logYellow('ServiceManager::logout');

	req.session = null;
	res.clearCookie('fhdlldfdfjfdj');
	log('clear session');

	return res.status(200).type('application/json;charset=UTF-8').json({
		success : 'success'
	});
};

exports.getUserDocumentList = function(req, res) {
	logYellow('ServiceManager::getUserDocumentList');
	if (typeof (req.session.user) === 'undefined') {
		log('session is undefined');

		return res.status(400).send('400 Bad Request!: ' + 'session is undefined');
	} else if(req.session.documentList) {
		return res.status(200).type('application/json;charset=UTF-8').json({
			userName : req.session.user.name,
			documentList : req.session.documentList
		});
	} else {
		// 하지만 이 블록이 작동 되는 경우는 없었다고 한다...
		var user = req.session.user;
		var success = function(result) {
			log(result);
			req.session.documentList = JSON.parse(result);
			
			return res.status(200).type('application/json;charset=UTF-8').json({
				userName : req.session.user.name,
				documentList : req.session.documentList
			});
		};
		var fail = function(err) {
			var response = {
				err : 'DictionaryServer return: ' + err.status + ", " + err.statusText
			};
			
			return res.status(500).send(JSON.stringify(response));
		};

		dictionaryServer.request('/search/getRecentUserDocuments', 'POST', models.getSearchModel(user.userIndex, user.userId, ''), success, fail);
	} 
};

exports.getDocumentRoadMap = function(req, res) {
	logYellow('ServiceManager::getDocumentRoadMap');
	var user = req.session.user;
	var documentList = req.session.documentList;
	var roadMap = [];
	
	if(!user) {
		return res.status(400).send('400 Bad Request!: ' + 'user is null');
	}
	
	for(var i = documentList.length - 1; i >= 0; i--) {
		var item = {
			keyword: documentList[i].keyword
		};
		roadMap.push(item);
	}
	return res.status(200).type('application/json;charset=UTF-8').json({roadMap: roadMap});
};

exports.getFriendList = function(req, res) {
	logYellow('ServiceManager::getFriendList');
	var user = req.session.user;
	
	if(!user) {
		return res.status(400).send('400 Bad Request!: ' + 'user is null');
	} else if(req.session.friendList) {
		log(req.session.friendList);
		
		return res.status(200).type('application/json;charset=UTF-8').json({friendList: req.session.friendList});
	} else {
		// 하지만 이 블록이 작동 되는 경우는 없었다고 한다...
		var success = function(result) {
			log(result);
			req.session.friendList = JSON.parse(result); 
			
			return res.status(200).type('application/json;charset=UTF-8').json({friendList: req.session.friendList});
		};
		var fail = function(err) {
			var response = {
				err : 'DictionaryServer return: ' + err.status + ", " + err.statusText
			};
			
			return res.status(500).send(JSON.stringify(response));
		};

		dictionaryServer.request('/friend/getFriendsByUserIndex', 'POST', models.getFriendModel(user.index, -1), success, fail);
	}
};

var getUserByEmail = function(email, success) {
	logYellow('ServiceManager::getUserByEmail ' + email);
	
	if(email) {
		var fail = function(e) {
			var err = {
				msg : 'DictionaryServer return: ' + e.status + ", " + e.statusText
			};
			logErr(err);
			
			return models.getUserModel(null, null, null, email, null);
		};

		dictionaryServer.request('/user/getUserByEmail', 'POST', models.getUserModel(null, null, null, email, null), success, fail);
	} else {
		var err = {
			msg : 'email is null'	
		};
		
		return models.getUserModel(null, null, null, null, null);
	}
};

exports.followFriend = function(req, res) {
	logYellow('ServiceManager::followFriend');

	//형식 체크는 귀찮으므로 생략
	// /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	
	if(!req.session.user) {
		return res.status(400).send('400 Bad Request!: ' + 'user is null');
	} else if(!req.body.email) {
		return res.status(400).send('400 Bad Request!: ' + 'email is null');
	} else {
		var email = req.body.email;
		
		if(req.session.friendList) {
			var friendList = req.session.friendList;
			for(var i = friendList.length - 1; i >= 0; i--) {
				if(friendList[i].email === email) {
					logErr(email + ' is already friend');
					var response = {
						status: 1,
						err: email + ' is already friend'
					};
					
					return res.status(500).send(JSON.stringify(response));
				}
			}
		}
		
		var success = function(result) {
			log(result);
			var friend = JSON.parse(result);
			
			if(friend.index === null) {
				var response = {
					status : 2,
					err : email + ' is not exist'
				};
					
				return res.status(500).send(JSON.stringify(response));
			}
			
			var success = function(result) {
				log(result);
				req.session.friendList.push(friend);
				
				return res.status(200).type('application/json;charset=UTF-8').json({friendList: req.session.friendList});
			};
			var fail = function(err) {
				var response = {
					err : 'DictionaryServer return: ' + err.status + ", " + err.statusText
				};
				log(response);
				return res.status(500).send(JSON.stringify(response));
			};

			dictionaryServer.request('/friend/followFriend', 'POST', models.getFriendModel(req.session.user.index, friend.index), success, fail);
		};
		getUserByEmail(email, success);
	}
	
};

exports.unfollowFriend = function(req, res) {
	logYellow('ServiceManager::unfollowFriend');
	
	if(!req.session.user) {
		return res.status(400).send('400 Bad Request!: ' + 'user is null');
	} else if(!req.body.index) {
		return res.status(400).send('400 Bad Request!: ' + 'index is null');
	} else {
		var index = req.body.index;
		var friend = null;
		var arrIndex = -1;
		
		if(req.session.friendList) {
			var friendList = req.session.friendList;
			for(var i = friendList.length - 1; i >= 0; i--) {
				if(friendList[i].index === index) {
					log(JSON.stringify(friendList[i]) + 'unfollowed');
					friend = friendList[i];
					arrIndex = i;
					
					break;
				}
			}
		}
		
		if(!friend) {
			return res.status(500).send(JSON.stringify({err : index + ' is not a friend'}));
		}
		
		var success = function(result) {
			log(result);
			req.session.friendList.splice(arrIndex, 1);
			
			return res.status(200).type('application/json;charset=UTF-8').json({friendList: req.session.friendList});
		};
		var fail = function(err) {
			var response = {
				err : 'DictionaryServer return: ' + err.status + ", " + err.statusText
			};
			log(response);
			return res.status(500).send(JSON.stringify(response));
		};

		dictionaryServer.request('/friend/unfollowFriend', 'POST', models.getFriendModel(req.session.user.index, friend.index), success, fail);
	}
	
};

var getSessionDocument = function(documentList, keyword) {
	for(var i = documentList.length - 1; i >= 0; i--) {
		if(documentList[i].keyword === keyword) {
			return documentList[i];
		}
	}
	
	return null;
};

var getSessionFriend = function(friendList, userId) {
	for(var i = friendList.length - 1; i >= 0; i--) {
		if(friendList[i].userId === userId) {
			return friendList[i].email;
		}
	}
	
	return null;
};

var getFriendDocumentSearchModel = function(friendList, keyword) {
	var searchModel = [];
	
	for(var i = friendList.length - 1; i >= 0; i--) {
		searchModel.push(models.getSearchModel(friendList[i].index, friendList[i].userId, keyword));
	}
	
	return searchModel;
};

exports.searchDocumentByKeyword = function(req, res) {
	logYellow('ServiceManager::searchKeyword');
	var keyword = req.body.keyword;
	var user = req.session.user;
	
	// document는 모두 session에 있음
	var document = getSessionDocument(req.session.documentList, keyword);
	
	dictionaryServer.request('/search/getFriendDocuments', 'POST', models.getSearchModel(user.index, user.userId, keyword), function(result) {
		// 콜백 지옥 될꺼 같으니, metaSearch에서 가져오는 부분은 따로 하는게 좋을 거 가틈.
		var friendsDictionaryList = JSON.parse(result);
		for(var i = friendsDictionaryList.length - 1; i >= 0 ; i--) {
			friendsDictionaryList[i].userId = getSessionFriend(req.session.friendList, friendsDictionaryList[i].userId);
		}
		
		var response = {
			dictionary: document,
			friendsDictionaryList: friendsDictionaryList
		};
		
		return res.status(200).type('application/json;charset=UTF-8').json(response);
	}, function(err) {
		var response = {
				err : 'DictionaryServer return: ' + err.status + ", " + err.statusText
			};
			log(response);
			return res.status(500).send(JSON.stringify(response));
		}
	);
};

exports.documentUpdate = function(req, res) {
	logYellow('ServiceManager::documentUpdate');
	
	var user = req.session.user;
	var keyword = req.body.keyword;
	var document = req.body.document;
	
	dictionaryServer.request('/document/setDocument', 'POST', models.getDocumentJSONModel(user.userId, keyword, document), function(result) {
		if(result === 'success') {
			var documentList = req.session.documentList;
			var arrIndex = -1;
			for(var i = documentList.length - 1; i >= 0; i--) {
				if(documentList[i].keyword === keyword) {
					arrIndex = i;
					break;
				}
			}
			
			dictionaryServer.request('/search/getUserDocument', 'POST', models.getSearchModel(user.index, user.userId, keyword), function(result) {
				if(arrIndex !== -1) {
					documentList[arrIndex] = JSON.parse(result);
				} else {
					documentList.push(JSON.parse(result));
				}
				req.session.documentList = documentList;
				
				return res.status(200).type('application/json;charset=UTF-8').json({status:'success'});
			}, function(err) {
				return res.status(500).send(err);
			});
		} else {
			return res.status(500).json({err:'DictionaryServer return: fail'});
		}
	}, function(err) {
		var response = {
				err : 'DictionaryServer return: ' + err.status + ", " + err.statusText
			};
			log(response);
			return res.status(500).send(JSON.stringify(response));
		}
	);
};

exports.documentDelete = function(req, res) {
	logYellow('ServiceManager::documentDelete');
	
	var user = req.session.user;
	var keyword = req.body.keyword;
	var documentList = req.session.documentList;
	var arrIndex = -1;
	
	for(var i = documentList.length - 1; i >= 0; i--) {
		if(documentList[i].keyword === keyword) {
			arrIndex = i;
			break;
		}
	}
	
	dictionaryServer.request('/document/deleteDocument', 'POST', models.getDocumentJSONModel(documentList[i].userId, documentList[i].keyword, documentList[i].document), function(result) {
		req.session.documentList.splice(arrIndex, 1);
		
		return res.status(200).type('application/json;charset=UTF-8').json({status:'success'});
	}, function(err) {
		return res.status(500).send(err);
	});
};

exports.searchMetaByKeyword = function(req, res) {
	logYellow('ServiceManager::searchMetaByKeyword');
	
	var keyword = req.body.keyword;
	
	/**
	 *     BLOG("blog")
	 *     ENCYCLOPEDIA("encyclopedia")
	 *     IMAGE("image")
	 *     NEWS("news")
	 *     VIDEO("video")
	 *     WEB("web")
	 * */
	// 시발 나도 모르겠다 지옥 ㄱ
	var searchModel = {
		q: keyword,
		category: 'blog'
	};
	var result = {
		blog: null,
		encyclopedia: null,
		image: null,
		news: null,
		video: null,
		web: null
	};
	process.nextTick(function() {
		metasearchServer.request({q:keyword, category:'blog'}, function(body) {	result.blog = JSON.parse(body);	});	});
	process.nextTick(function() {
		metasearchServer.request({q:keyword, category:'encyclopedia'}, function(body) {	result.encyclopedia = JSON.parse(body);	});	});
	process.nextTick(function() {
		metasearchServer.request({q:keyword, category:'image'}, function(body) { result.image = JSON.parse(body); });	});
	process.nextTick(function() {
		metasearchServer.request({q:keyword, category:'news'}, function(body) {	result.news = JSON.parse(body);	});	});
	process.nextTick(function() {
		metasearchServer.request({q:keyword, category:'video'}, function(body) {result.video = JSON.parse(body); });	});
	process.nextTick(function() {
		metasearchServer.request({q:keyword, category:'web'}, function(body) {
			result.web = JSON.parse(body);
				logBlue(result.blog === null ? 'null' : 'blog');
				logBlue(result.encyclopedia === null ? 'null' : 'encyclopedia');
				logBlue(result.image === null ? 'null' : 'image');
				logBlue(result.news === null ? 'null' : 'news');
				logBlue(result.video === null ? 'null' : 'video');
				logBlue(result.web === null ? 'null' : 'web');
				
				// video가 계속 null 뜬당
				
				return res.status(200).type('application/json;charset=UTF-8').json(result);
			}, function(err) {
				return res.status(500).json({err:'MetasearchServer return:' + err});
			});
		}); 
};