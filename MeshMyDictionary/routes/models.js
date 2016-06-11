exports.getIdTokenModel = function(idToken) {
	var idTokenModel = {
		idToken : idToken
	};

	return idTokenModel;
};

exports.getUserModel = function(index, userId, name, email, pictureURL) {
	var userModel = {
		index : index,
		userId : userId,
		name : name,
		email : email,
		pictureURL : pictureURL
	};

	return userModel;
};

exports.getSearchModel = function(userIndex, userId, keyword) {
	var searchModel = {
		userIndex : userIndex,
		userId : userId,
		keyword : keyword
	};
	return searchModel;
};

exports.getFriendModel = function(userIndex, friendIndex) {
	var friendModel = {
		userIndex : userIndex,
		friendIndex : friendIndex
	};

	return friendModel;
};

exports.getDocumentJSONModel = function(userId, keyword, document) {
	var documentModel = {
		userId : userId,
		keyword : keyword,
		document : document
	};
	
	return documentModel;
};