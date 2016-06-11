var request = require("request");

var logYellow = require('./common').logYellow;
var logErr = require('./common').logErr;

var metasearchServerAddress = 'http://dijkstra.ajou.ac.kr:8181/MetaSearch-1.0-SNAPSHOT';

exports.request = function(data, success, fail) {
	logYellow('ServiceManager::requestMetasearchServer');	

	request({
		uri : metasearchServerAddress + "/service/metasearch",
		qs: data,
		method : 'GET',
		timeout : 10000,
		followRedirect : true
		// maxRedirects : 10
	}, function(error, response, body) {
		if (!error && response.statusCode === 200) {
			success(body);
	    } else {
	    	logErr(error);
	    	if(fail) {
	    		fail(error);
	    	}
	    }
	});
};