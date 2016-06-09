$.getScript("js/header.js");
// getUserDocumentList

var getDocumentList = function(success) {
	$.ajax({
		url : meshServerAddress + '/getUserDocumentList',
		type : 'post',
		contentType : 'application/json;charset=UTF-8',
		success : function(result) {
			console.log("result: ", result);
			success(result);
		},
	    error: function(err){
	        console.log("err: ", err);
	    }
	});
};

var getFriendList = function(success) {
	$.ajax({
		url : meshServerAddress + '/getFriendList',
		type : 'post',
		contentType : 'application/json;charset=UTF-8',
		success : function(result) {
			console.log("result: ", result);
			success(result);
		},
	    error: function(err){
	        console.log("err: ", err);
	    }
	});
};