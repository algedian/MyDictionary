var request = require("request");

var logYellow = require('./common').logYellow;
var logErr = require('./common').logErr;

var metasearchServerAddress = 'http://dijkstra.ajou.ac.kr:8181/MetaSearch-1.0-SNAPSHOT';

exports.request = function(data, success, error) {
	logYellow('ServiceManager::requestMetasearchServer ' + JSON.stringify(data));	

	request({
		uri : metasearchServerAddress + "/service/metasearch",
		qs: data,
		method : 'GET',
		timeout : 10000,
		followRedirect : true
		// maxRedirects : 10
	}, function(e, response, body) {
		if (!e && response.statusCode === 200) {
			success(body);
	    } else {
	    	error(e);
	    }
	});
};