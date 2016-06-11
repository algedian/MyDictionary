var XMLHttpRequest = require("xmlhttprequest").XMLHttpRequest;

var logYellow = require('./common').logYellow;
var logErr = require('./common').logErr;

var dictionaryServerAddress = 'http://dijkstra.ajou.ac.kr:8080/MyDictionaryMVC';
// var dictionaryServerAddress = 'localhost';
var dictionaryServerPort = '8080';

exports.request = function(path, method, data, success, fail) {
	logYellow('ServiceManager::requestDictionaryServer');

	var xhr = new XMLHttpRequest();
	xhr.open(method, dictionaryServerAddress + path);
	xhr.setRequestHeader('Content-Type', 'application/json');

	xhr.onreadystatechange = function(oEvent) {
		if (xhr.readyState === 4) {
			if (xhr.status === 200) {
				success(xhr.responseText);
			} else {
				var err = {
					status : xhr.status,
					statusText : xhr.statusText
				};
				logErr(err);
				fail(err);
			}
		}
	};

	xhr.send(JSON.stringify(data));
};