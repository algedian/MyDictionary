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


exports.index = function(req, res) {
	logYellow('ServiceManager::index');

	res.render('login');
};

exports.main = function(req, res) {
	logYellow('ServiceManager::main');
	if (typeof (req.session.user) === 'undefined') {
		log('session is undefined');

		return res.status(400).send('400 Bad Request!: ' + 'session is undefined');
	} else {
		res.render('main');
	}
};

exports.friend = function(req, res) {
	logYellow('ServiceManager::friend');
	if (typeof (req.session.user) === 'undefined') {
		log('session is undefined');

		return res.status(400).send('400 Bad Request!: ' + 'session is undefined');
	} else {
		res.render('friend');
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

exports.getFriendList = function(req, res) {
	logYellow('ServiceManager::getFriendList');
	var user = req.session.user;
	
	if(!user) {
		return res.status(400).send('400 Bad Request!: ' + 'user is null');
	} else if(req.session.friendList) {
		log(req.session.friendList);
		
		return res.status(200).type('application/json;charset=UTF-8').json({friendList: req.session.friendList});
	} else {
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

exports.getFriendsDocuments = function(req, res) {
	
};

exports.getDocument = function(req, res) {
	logYellow('ServiceManager::main');

	var userId = req.param('userId', null);
	log('userId: ' + userId);

	if (userId !== null) {
		var data = JSON.stringify(models.getSearchModel(-1, userId, ''));
		var option = {
			host : dictionaryServerAddress,
			port : dictionaryServerPort,
			path : '/MyDictionaryMVC/search/getUserDocuments',
			method : 'POST',
			headers : {
				'Content-Type' : 'application/json',
				'Content-Length' : Buffer.byteLength(data)
			}
		};
		var httpRequest = http.request(option, function(response) {
			log('getDictionary status: ' + response.statusCode);
			response.setEncoding('utf8');

			response.on('data', function(chunk) {
				log('dictionaries: ' + chunk);
				res.setHeader('Content-Type', 'application/json');
				res.send(JSON.parse(chunk));
			});
		});

		httpRequest.on('error', function(e) {
			logErr('problem with request: ' + e.message);
			res.status(500).send({
				error : e
			});
		});

		httpRequest.write(data);
		httpRequest.end();
	} else {
		res.status(400).send({
			error : "bad request"
		});
	}
};