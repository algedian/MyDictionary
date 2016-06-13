var meshServerAddress = 'http://dijkstra.ajou.ac.kr:3000';
// var meshServerAddress = 'http://localhost:3000';

var call = function(url, type, contentType, data, success, error) {
	if(data) {
		$.ajax({
			timeout: 20000,
			url : url,
			type : type,
			contentType : contentType,
			data: data,
			success : success,
		    error: error
		});
	} else {
		$.ajax({
			url : url,
			type : type,
			contentType : contentType,
			success : success,
		    error: error
		});
	}
};

var commonContentType = 'application/json;charset=UTF-8';
var commonErr = function(err) {
	console.log("err: ", err);
};

var login = function(idToken, success, fail) {
	var url = meshServerAddress + '/endpoint/login';
	
	call(url, 'POST', commonContentType, JSON.stringify({idToken:idToken}), success, fail);
}

var setSignOut = function(success) {
	var url = meshServerAddress + '/endpoint/logout';
	
	call(url, 'POST', commonContentType, null, success, commonErr);
};

var initSession = function(success) {
	var url = meshServerAddress + '/endpoint/initSession';
	
	call(url, 'POST', commonContentType, null, success, commonErr);
};

var getDocumentList = function(success) {
	var url = meshServerAddress + '/endpoint/getUserDocumentList';
	
	call(url, 'POST', commonContentType, null, success, commonErr);
};

var getDocumentRoadMap = function(success) {
	var url = meshServerAddress + '/endpoint/getDocumentRoadMap';
	
	call(url, 'POST', commonContentType, null, success, commonErr);
};


var getFriendList = function(success) {
	var url = meshServerAddress + '/endpoint/getFriendList';
	
	call(url, 'POST', commonContentType, null, success, commonErr);
};

var followFriend = function(email, success, error) {
	var url = meshServerAddress + '/endpoint/followFriend';
	
	call(url, 'POST', commonContentType, JSON.stringify({email:email}), success, error);
};

var unfollowFriend = function(index, success, error) {
	var url = meshServerAddress + '/endpoint/unfollowFriend';
	
	call(url, 'POST', commonContentType, JSON.stringify({index:index}), success, error);
};

var searchKeyword = function(keyword, success) {
	var url = meshServerAddress + '/endpoint/searchDocumentByKeyword';
	
	call(url, 'POST', commonContentType, JSON.stringify({keyword:keyword}), success, commonErr);
};

var documentUpdate = function(keyword, document, success, error) {
	var url = meshServerAddress + '/endpoint/documentUpdate';
	
	call(url, 'POST', commonContentType, JSON.stringify({keyword:keyword, document:document}), success, error);
};

var documentDelete = function(keyword, success) {
	var url = meshServerAddress + '/endpoint/documentDelete';
	
	call(url, 'POST', commonContentType, JSON.stringify({keyword:keyword}), success, commonErr);
};

var search = function(keyword, success, error) {
	var url = meshServerAddress + '/endpoint/searchMetaByKeyword';
	
	call(url, 'POST', commonContentType, JSON.stringify({keyword:keyword}), success, error);
};